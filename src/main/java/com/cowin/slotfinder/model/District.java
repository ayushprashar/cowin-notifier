package com.cowin.slotfinder.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.ToString;

@ToString
public class District {
    @JsonProperty("district_id")
    private int districtId;
    @JsonProperty("district_name")
    private String districtName;

    public District() {
    }

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }

    public int getDistrictId() {
        return districtId;
    }

    public void setDistrictId(int districtId) {
        this.districtId = districtId;
    }
}
