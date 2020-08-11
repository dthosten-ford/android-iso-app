package com.fordmps.mobileapp.shared.managers;

import io.reactivex.Completable;

public interface VehicleCapabilitiesManagerInterface {
    Completable getVhaTypeFromVehicleCapabilityService(String vin);
}
