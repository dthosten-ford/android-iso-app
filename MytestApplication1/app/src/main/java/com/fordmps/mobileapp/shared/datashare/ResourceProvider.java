package com.fordmps.mobileapp.shared.datashare;

import android.graphics.drawable.Drawable;

public interface ResourceProvider {
    public String getString( int resId);

    Drawable getDrawable(int ic_ev_unplugged);
}
