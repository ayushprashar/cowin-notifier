package com.cowin.slotfinder.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class State {
    @JsonProperty("state_id")
    private String stateId;
    @JsonProperty("state_name")
    private String stateName;

    public State() {
    }
}
