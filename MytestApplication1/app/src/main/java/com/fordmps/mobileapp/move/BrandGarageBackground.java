package com.fordmps.mobileapp.move;

import android.widget.ImageView;

import com.fordmps.viewutils.R;

public interface BrandGarageBackground {

    default int getBackgroundForVehicle(String modelName, boolean hasVehicleImage, BaseGarageVehiclePagerAdapter.PagerItemTypes pagerItemTypes, boolean isTcuEnabled) {
        if (modelName == null || !hasVehicleImage) {
            return R.drawable.empty_garage_background;
        }
        Integer backgroundImage = LincolnBackgroundImage.get(modelName);
        return null != backgroundImage ? backgroundImage : R.drawable.empty_garage_background;
    }

    default int getBackgroundForVehicleDetail(String modelName, boolean hasVehicleImage, BaseGarageVehiclePagerAdapter.PagerItemTypes pagerItemTypes, boolean isTcuEnabled) {
        return getBackgroundForVehicle(modelName, hasVehicleImage, pagerItemTypes, isTcuEnabled);
    }

    default ImageView.ScaleType getLandingBackgroundScaleType() {
        return ImageView.ScaleType.CENTER_CROP;
    }
}
