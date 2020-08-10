/*
 * CONFIDENTIAL FORD MOTOR COMPANY
 * This is an unpublished work of authorship, which contains confidential information and/or trade secrets, created in 2019. Ford Motor Company owns all rights to this work and intends to maintain it in confidence to preserve its trade secret status. Ford Motor Company reserves all rights, under the copyright laws of the United States or those of any other country that may have jurisdiction, including the right to protect this work as an unpublished work, in the event of an inadvertent or deliberate unauthorized publication. Use of this work constitutes an agreement to maintain the confidentiality of the work, and to refrain from any reverse engineering, decompilation, or disassembly of this work.
 * Ford Motor Company also reserves its rights under all copyright laws to protect this work as a published work, when appropriate. Those having access to this work may not copy it, use it, modify it, or disclose the information contained in it without the written authorization of Ford Motor Company.
 * Copyright 2019, Ford Motor Company.
 *
 */

package com.fordmps.mobileapp.shared.utils;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.util.Log;

//public final class BitmapImageUtil implements BitmapImageUtilInterface {
//
//    private BitmapImageUtil() {
//    }
//
//    public static Bitmap cropTransparentPadding(final Bitmap source) {
//        if (null == source) {
//            return source;
//        }
//
//        try {
//            int firstX = 0, firstY = 0;
//            int lastX = source.getWidth();
//            int lastY = source.getHeight();
//            int[] pixels = new int[source.getWidth() * source.getHeight()];
//            source.getPixels(pixels, 0, source.getWidth(), 0, 0, source.getWidth(), source.getHeight());
//            loop:
//            for (int x = 0; x < source.getWidth(); x++) {
//                for (int y = 0; y < source.getHeight(); y++) {
//                    if (pixels[x + (y * source.getWidth())] != Color.TRANSPARENT) {
//                        firstX = x;
//                        break loop;
//                    }
//                }
//            }
//            loop:
//            for (int y = 0; y < source.getHeight(); y++) {
//                for (int x = firstX; x < source.getWidth(); x++) {
//                    if (pixels[x + (y * source.getWidth())] != Color.TRANSPARENT) {
//                        firstY = y;
//                        break loop;
//                    }
//                }
//            }
//            loop:
//            for (int x = source.getWidth() - 1; x >= firstX; x--) {
//                for (int y = source.getHeight() - 1; y >= firstY; y--) {
//                    if (pixels[x + (y * source.getWidth())] != Color.TRANSPARENT) {
//                        lastX = x;
//                        break loop;
//                    }
//                }
//            }
//            loop:
//            for (int y = source.getHeight() - 1; y >= firstY; y--) {
//                for (int x = source.getWidth() - 1; x >= firstX; x--) {
//                    if (pixels[x + (y * source.getWidth())] != Color.TRANSPARENT) {
//                        lastY = y;
//                        break loop;
//                    }
//                }
//            }
//            return Bitmap.createBitmap(source, firstX, firstY, lastX - firstX, lastY - firstY);
//        } catch (Exception e) {
//            Log.e("FordCropImageView", "Error converting subImage : " + e.getMessage());
//            return source;
//        }
//    }
//}
