package com.ford.vinlookup.managers;

import io.reactivex.Maybe;

public interface VinLookupProviderInterface {
    Maybe isHybridElectricVehicle(String vin);
}
