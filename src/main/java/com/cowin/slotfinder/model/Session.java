package com.cowin.slotfinder.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Session {
    @JsonProperty("session_id")
    private String sessionId;
    private String date;
    @JsonProperty("available_capacity")
    private Integer availableCapacity;
    @JsonProperty("min_age_limit")
    private Integer minAgeLimit;
    private String vaccine;
    private List<String> slots;

    private Integer centerId;
    private String name;
    private String stateName;
    private String districtName;
    private String blockName;
    private Integer pincode;
    private String from;
    private String to;
}
