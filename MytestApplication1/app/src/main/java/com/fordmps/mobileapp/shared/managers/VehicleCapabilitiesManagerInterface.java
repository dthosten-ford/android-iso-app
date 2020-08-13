package com.fordmps.mobileapp.shared.managers;

import io.reactivex.Completable;
import io.reactivex.Maybe;
import io.reactivex.Observable;

public interface VehicleCapabilitiesManagerInterface {
    Completable getVhaTypeFromVehicleCapabilityService(String vin);

    Observable<Object> getVehicleCapabilities();

    boolean isValidVcsResponse(Object o);

    Maybe<Object> getVehicleSdnType(String vin);
}
