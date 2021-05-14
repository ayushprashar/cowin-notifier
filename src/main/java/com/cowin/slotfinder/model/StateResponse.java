package com.cowin.slotfinder.model;

import lombok.Data;

import java.util.List;

@Data
public class StateResponse {
    private List<State> states;
    private int ttl;

    public StateResponse() {
    }
}
