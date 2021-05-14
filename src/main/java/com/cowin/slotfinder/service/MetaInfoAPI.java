package com.cowin.slotfinder.service;

import com.cowin.slotfinder.model.CenterResponse;
import com.cowin.slotfinder.model.District;
import com.cowin.slotfinder.model.DistrictResponse;
import com.cowin.slotfinder.model.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.stream.Collectors;

import static com.cowin.slotfinder.config.Constants.MINIMUM_AGE;

@Service
public class MetaInfoAPI {
    @Autowired
    private WebClient webClient;

    private Flux<Integer> getDistrictIdsByStateId(Integer stateId) {
        return webClient
                .get()
                .uri("/v2/admin/location/districts/" + stateId)
                .retrieve().bodyToMono(DistrictResponse.class)
                .map(DistrictResponse::getDistricts)
                .flatMapMany(Flux::fromIterable)
                .log()
                .map(District::getDistrictId);

    }

    private Flux<Session> getSessionsForDistrict(Integer districtId) {
        return getSessionsForDistrict(districtId, null);
    }

    private Flux<Session> getSessionsForDistrict(Integer districtId, String date) {
        if (date == null) {
            date = LocalDate.now()
                    .format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        }
        return webClient
                .get()
                .uri("/v2/appointment/sessions/public/calendarByDistrict?district_id=" +
                        districtId + "&date=" + date)
                .retrieve().bodyToMono(CenterResponse.class)
                .map(CenterResponse::getCenters)
                .flatMapMany(Flux::fromIterable)
                .map(vaccinationCenter -> vaccinationCenter
                        .getSessions()
                        .stream()
                        .peek(session -> {
                            session.setCenterId(vaccinationCenter.getCenterId());
                            session.setName(vaccinationCenter.getName());
                            session.setStateName(vaccinationCenter.getStateName());
                            session.setBlockName(vaccinationCenter.getBlockName());
                            session.setPincode(vaccinationCenter.getPincode());
                            session.setDistrictName(vaccinationCenter.getDistrictName());
                            session.setFrom(vaccinationCenter.getFrom());
                            session.setTo(vaccinationCenter.getTo());
                        })
                        .collect(Collectors.toList()))
                .flatMap(Flux::fromIterable);
    }

    public Flux<Session> getSessionsByStateCode(Integer stateCode) {
        return getDistrictIdsByStateId(stateCode)
                .map(this::getSessionsForDistrict)
                .flatMap(sessionFlux -> sessionFlux)
                .filter(session -> session.getAvailableCapacity() > 0)
                .filter(session -> session.getMinAgeLimit().equals(MINIMUM_AGE))
                .sort((session1, session2) -> session2.getAvailableCapacity() - session1.getAvailableCapacity());
    }


}
