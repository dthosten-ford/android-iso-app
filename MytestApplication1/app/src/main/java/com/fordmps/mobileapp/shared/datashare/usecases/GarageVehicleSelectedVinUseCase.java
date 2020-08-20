/*
 * CONFIDENTIAL FORD MOTOR COMPANY
 * This is an unpublished work of authorship, which contains confidential information and/or trade secrets, created in 2019. Ford Motor Company owns all rights to this work and intends to maintain it in confidence to preserve its trade secret status. Ford Motor Company reserves all rights, under the copyright laws of the United States or those of any other country that may have jurisdiction, including the right to protect this work as an unpublished work, in the event of an inadvertent or deliberate unauthorized publication. Use of this work constitutes an agreement to maintain the confidentiality of the work, and to refrain from any reverse engineering, decompilation, or disassembly of this work.
 * Ford Motor Company also reserves its rights under all copyright laws to protect this work as a published work, when appropriate. Those having access to this work may not copy it, use it, modify it, or disclose the information contained in it without the written authorization of Ford Motor Company.
 * Copyright 2019, Ford Motor Company.
 *
 */

package com.fordmps.mobileapp.shared.datashare.usecases;

import android.graphics.Bitmap;

public class GarageVehicleSelectedVinUseCase implements UseCase {
    private String vin;
    private Bitmap vehicleImage;
    private String myVehiclePrefix;
    private String vehicleNickname;
    private String vehicleYearModel;
    private String vehicleCompatibility;
    private final String modelName;

    public GarageVehicleSelectedVinUseCase(String vin, Bitmap vehicleImage, String myVehiclePrefix, String vehicleNickname, String vehicleYearModel, String vehicleCompatibility, String modelName) {
        this.vin = vin;
        this.vehicleImage = vehicleImage;
        this.myVehiclePrefix = myVehiclePrefix;
        this.vehicleNickname = vehicleNickname;
        this.vehicleYearModel = vehicleYearModel;
        this.vehicleCompatibility = vehicleCompatibility;
        this.modelName = modelName;
    }

    public Bitmap getVehicleImage() {
        return vehicleImage;
    }

    public String getVin() {
        return vin;
    }

    public String getMyVehiclePrefix() {
        return myVehiclePrefix;
    }

    public String getVehicleNickname() {
        return vehicleNickname;
    }

    public String getVehicleYearModel() {
        return vehicleYearModel;
    }

    public String getVehicleCompatibility() {
        return vehicleCompatibility;
    }

    public String getModelName() {
        return modelName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GarageVehicleSelectedVinUseCase useCase = (GarageVehicleSelectedVinUseCase) o;

        if (vin != null ? !vin.equals(useCase.vin) : useCase.vin != null) return false;
        if (vehicleImage != null ? !vehicleImage.equals(useCase.vehicleImage) : useCase.vehicleImage != null)
            return false;
        if (myVehiclePrefix != null ? !myVehiclePrefix.equals(useCase.myVehiclePrefix) : useCase.myVehiclePrefix != null)
            return false;
        if (vehicleNickname != null ? !vehicleNickname.equals(useCase.vehicleNickname) : useCase.vehicleNickname != null)
            return false;
        if (vehicleYearModel != null ? !vehicleYearModel.equals(useCase.vehicleYearModel) : useCase.vehicleYearModel != null)
            return false;
        if (vehicleCompatibility != null ? !vehicleCompatibility.equals(useCase.vehicleCompatibility) : useCase.vehicleCompatibility != null)
            return false;
        return modelName != null ? modelName.equals(useCase.modelName) : useCase.modelName == null;
    }

    @Override
    public int hashCode() {
        int result = vin != null ? vin.hashCode() : 0;
        result = 31 * result + (vehicleImage != null ? vehicleImage.hashCode() : 0);
        result = 31 * result + (myVehiclePrefix != null ? myVehiclePrefix.hashCode() : 0);
        result = 31 * result + (vehicleNickname != null ? vehicleNickname.hashCode() : 0);
        result = 31 * result + (vehicleYearModel != null ? vehicleYearModel.hashCode() : 0);
        result = 31 * result + (vehicleCompatibility != null ? vehicleCompatibility.hashCode() : 0);
        result = 31 * result + (modelName != null ? modelName.hashCode() : 0);
        return result;
    }
}
