package com.ford.androidutils.ui.glide;

import com.bumptech.glide.DrawableTypeRequest;

public interface GlideProviderInterface {
    DrawableTypeRequest<Object> load(Object generateVehicleImageUrl);
}
