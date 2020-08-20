package com.fordmps.mobileapp.shared.providers;

import com.ford.blanco.models.Vehicle;

public interface VehicleImageUrlProviderInterface {
    public String generateVehicleImageUrl(Vehicle vehicle) ;

    Object generateVehicleImageUrl(String modelName, String modelYear, String vin);
}
