package com.ford.applink.managers;

import com.ford.vehiclehealth.models.VehicleAlertResponse;

import io.reactivex.Observable;

public interface ActiveVhaAlertsManager {
    Observable<VehicleAlertResponse> getActiveAlertsFromCacheThenNetwork(String vin, String source);
}
