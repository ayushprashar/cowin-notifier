package com.cowin.slotfinder.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class VaccinationCenter {
    @JsonProperty("center_id")
    private Integer centerId;
    private String name;
    @JsonProperty("state_name")
    private String stateName;
    @JsonProperty("district_name")
    private String districtName;
    @JsonProperty("block_name")
    private String blockName;
    @JsonProperty("pincode")
    private Integer pincode;
    private String from;
    private String to;
    @JsonProperty("fee_type")
    private String feeType;
    private List<Session> sessions;
}
