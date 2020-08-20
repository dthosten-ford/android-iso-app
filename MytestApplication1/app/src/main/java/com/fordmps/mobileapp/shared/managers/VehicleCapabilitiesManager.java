package com.fordmps.mobileapp.shared.managers;

import com.fordmps.data.enums.SdnType;
import com.fordmps.mobileapp.move.VehicleCapabilitiesResponse;

import java.util.List;

import javax.ws.rs.core.Feature;

import io.reactivex.Completable;
import io.reactivex.Maybe;
import io.reactivex.Observable;

public interface VehicleCapabilitiesManager {
    Completable getVhaTypeFromVehicleCapabilityService(String vin);

    Observable<VehicleCapabilitiesResponse> getVehicleCapabilities();

    boolean isValidVcsResponse(VehicleCapabilitiesResponse o);

    Maybe<SdnType> getVehicleSdnType(String vin);

    boolean getFeatureEligibility(List<Feature> vehicleCapabilitiesResponse, int userReset);
}
