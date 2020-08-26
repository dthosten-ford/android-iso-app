package com.ford.androidutils.ui.glide;

import com.bumptech.glide.DrawableTypeRequest;

public interface GlideProvider {
    DrawableTypeRequest<Object> load(Object generateVehicleImageUrl);
}
