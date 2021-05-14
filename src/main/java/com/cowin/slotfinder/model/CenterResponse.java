package com.cowin.slotfinder.model;

import lombok.Data;

import java.util.List;

@Data
public class CenterResponse {
    private List<VaccinationCenter> centers;

    public CenterResponse() {
    }
}
