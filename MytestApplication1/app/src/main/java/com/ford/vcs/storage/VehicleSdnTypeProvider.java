package com.ford.vcs.storage;

public interface VehicleSdnTypeProvider{
    boolean hasVehicleSdnTypeCached(String vin);

    void setVehicleSdnType(Object any, Object any1);
}
