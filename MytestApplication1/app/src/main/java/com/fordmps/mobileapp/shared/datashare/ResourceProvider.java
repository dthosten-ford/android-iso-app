/*
 * CONFIDENTIAL FORD MOTOR COMPANY
 * This is an unpublished work of authorship, which contains confidential information and/or trade secrets, created in 2019. Ford Motor Company owns all rights to this work and intends to maintain it in confidence to preserve its trade secret status. Ford Motor Company reserves all rights, under the copyright laws of the United States or those of any other country that may have jurisdiction, including the right to protect this work as an unpublished work, in the event of an inadvertent or deliberate unauthorized publication. Use of this work constitutes an agreement to maintain the confidentiality of the work, and to refrain from any reverse engineering, decompilation, or disassembly of this work.
 * Ford Motor Company also reserves its rights under all copyright laws to protect this work as a published work, when appropriate. Those having access to this work may not copy it, use it, modify it, or disclose the information contained in it without the written authorization of Ford Motor Company.
 * Copyright 2019, Ford Motor Company.
 *
 */

package com.fordmps.mobileapp.shared.datashare;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.net.Uri;

import androidx.annotation.ArrayRes;
import androidx.annotation.ColorRes;
import androidx.annotation.DimenRes;
import androidx.annotation.DrawableRes;
import androidx.annotation.IntegerRes;
import androidx.annotation.RawRes;
import androidx.annotation.StringRes;
import androidx.core.content.FileProvider;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ResourceProvider implements ResourceProviderInterface {

    private final Context context;

    public ResourceProvider(Context context) {
        this.context = context;
    }
//
//    public Resources getResources() { return context.getResources(); }
//
    public String getString(@StringRes int resId) {
        return context.getString(resId);
    }
//
//    public String getString(@StringRes int resId, Integer value) {
//        return context.getString(resId, value);
//    }
//
//    public String getString(@StringRes int resId, int value1, String value2) {
//        return context.getString(resId, value1, value2);
//    }
//
//    public String getString(@StringRes int resId, String ... values) {
//        return context.getString(resId, values);
//    }
//
//    public int getColor(@ColorRes int resId) {
//        return context.getResources().getColor(resId);
//    }
//
//    public int getInteger(@IntegerRes int resId) {
//        return context.getResources().getInteger(resId);
//    }
//
//    public Drawable getDrawable(@DrawableRes int resId) {
//        return context.getResources().getDrawable(resId);
//    }
//
//    public int getResourceId(String drawableName) {
//        String defaultImage = "ic_na";
//        try {
//            return context.getResources().getIdentifier(drawableName, "drawable", context.getPackageName());
//        } catch (Exception e) {
//            return context.getResources().getIdentifier(defaultImage, "drawable", context.getPackageName());
//        }
//    }
//
//    public int getResource(String arrayName) {
//        return context.getResources().getIdentifier(arrayName, "array",context.getPackageName());
//    }
//
//    public DisplayMetrics getDisplayMetrics() {
//        return context.getResources().getDisplayMetrics();
//    }
//
//    public float getDimension(@DimenRes int resId) {
//        return context.getResources().getDimension(resId);
//    }
//
//    public TypedValue getDimensionTypedValue(@DimenRes int resId) {
//        TypedValue typedValue = new TypedValue();
//        context.getResources().getValue(resId, typedValue, true);
//        return typedValue;
//    }
//
//    public String[] getStringArray(@ArrayRes int resId) {
//        return context.getResources().getStringArray(resId);
//    }
//
//    public int getStringArrayResourceIdentifier(String name) {
//        return context.getResources().getIdentifier(name , "array", context.getPackageName());
//    }
//
//    public Uri getFileResourceUri(String fileName) {
//        File file = new File(fileName);
//        return FileProvider.getUriForFile(context, context.getPackageName() + ".provider", file);
//    }
//
//    public String getRawResource(@RawRes int resId) {
//        try {
//            InputStream inputStream = context.getResources().openRawResource(resId);
//            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
//            StringBuilder builder = new StringBuilder();
//            String line;
//            while ((line = bufferedReader.readLine()) != null) {
//                builder.append(line).append("\n");
//            }
//            return builder.toString();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return "";
//    }
//
//    public boolean isRtl() {
//       return View.LAYOUT_DIRECTION_RTL == context.getResources().getConfiguration().getLayoutDirection();
//    }
}
