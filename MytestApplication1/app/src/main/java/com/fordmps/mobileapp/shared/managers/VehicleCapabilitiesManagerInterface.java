package com.fordmps.mobileapp.shared.managers;

import com.fordmps.mobileapp.move.VehicleCapabilitiesResponse;

import io.reactivex.Completable;
import io.reactivex.Maybe;
import io.reactivex.Observable;

public interface VehicleCapabilitiesManagerInterface {
    Completable getVhaTypeFromVehicleCapabilityService(String vin);

    Observable<VehicleCapabilitiesResponse> getVehicleCapabilities();

    boolean isValidVcsResponse(VehicleCapabilitiesResponse o);

    Maybe<Object> getVehicleSdnType(String vin);
}
