package com.ford.vinlookup.managers;

import io.reactivex.Maybe;

public interface VinLookupProvider {
    Maybe isHybridElectricVehicle(String vin);
}
