package com.ford.dashboard.models;

import com.ford.vehiclecommon.models.VehicleDetails;
import com.ford.vehiclecommon.models.VehicleStatus;

import java.util.Map;
import java.util.Optional;

public class VehicleInfoImpl implements VehicleInfo{

    @Override
    public void setVehicleSource(int vehicleSource) {

    }

    @Override
    public void setVehicleStatus(VehicleStatus vehicleStatus) {

    }

    @Override
    public void setVehicleAuthStatus(VehicleAuthStatus vehicleAuthStatus) {

    }

    @Override
    public void setIsPendingReset(boolean isPendingReset) {

    }

    @Override
    public boolean isPendingReset() {
        return false;
    }

    @Override
    public void setTcuEnabled(boolean isTcuEnabled) {

    }

    @Override
    public void setMasterResetAvailability(boolean resetAvailable) {

    }

    @Override
    public boolean isMasterResetAvailable() {
        return false;
    }

    @Override
    public String getModelYear() {
        return null;
    }

    @Override
    public String getVin() {
        return null;
    }

    @Override
    public String getModelName() {
        return null;
    }

    @Override
    public boolean isTcuEnabled() {
        return false;
    }

    @Override
    public String getJointVenture() {
        return null;
    }

    @Override
    public Optional<String> getNickname() {
        return Optional.empty();
    }

    @Override
    public Optional<String> getLocalizedModelName() {
        return Optional.empty();
    }

    @Override
    public int getSDNSourceForTCU() {
        return 0;
    }

    @Override
    public boolean isAuthorized() {
        return false;
    }

    @Override
    public Map<VehicleStatus, VehicleStatus> getVehicleStatus() {
        return null;
    }

    @Override
    public VehicleDetails getVehicleDetails() {
        return null;
    }

    @Override
    public String getAuthorization() {
        return null;
    }
}
