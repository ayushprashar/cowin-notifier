package com.cowin.slotfinder;

import com.cowin.slotfinder.model.StateResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@RestController
public class CowinController {
    @Autowired
    WebClient webClient;

    /**
     * Used to retrieve state codes as per the CoWin App
     * */

    @GetMapping("/state-id")
    Mono<StateResponse> getStateIds() {
        return webClient
                .get()
                .uri("/v2/admin/location/states")
                .retrieve()
                .bodyToMono(StateResponse.class);
    }
}
