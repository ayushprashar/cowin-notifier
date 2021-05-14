package com.cowin.slotfinder.scheduler;


import com.cowin.slotfinder.service.EmailServiceImpl;
import com.cowin.slotfinder.service.MetaInfoAPI;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CoWinHitter {
    @Autowired
    private MetaInfoAPI metaInfoAPI;
    @Autowired
    private EmailServiceImpl emailService;
    @Value("${recipient.email}")
    private String recipientEmail;

    @Scheduled(fixedDelayString = "${scheduler.delay}")
    public void hitCoWin() throws InterruptedException {
        Integer DELHI_STATE_CODE = 9;
        StringBuilder builder = new StringBuilder();
        metaInfoAPI.getSessionsByStateCode(DELHI_STATE_CODE)
                .subscribe(session -> builder.append("\n").append(session).append("\n"),
                        error -> log.info(error.getLocalizedMessage()),
                        () -> emailService.sendSimpleMessage(
                                recipientEmail,
                                "Test",
                                builder.toString()));

    }
}
