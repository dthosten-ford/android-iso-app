package com.ford.ngsdnvehicle.providers;

import android.util.Pair;

import io.reactivex.Observable;

public interface NgsdnVehicleProvider {
//    Object getVehicleLockStatusObservable(String vin);
    public Observable<Pair<String, NgsdnVehicleStatusResponse>> getVehicleLockStatusObservable(final String vin);

    Observable<Boolean> getVehiclePaakCapability(String anyString);
}
