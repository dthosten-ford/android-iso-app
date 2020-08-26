package com.ford.vinlookup.managers;


import io.reactivex.Single;

public interface VinLookupProvider {
    Single<Boolean> isHybridElectricVehicle(String vin);
}
