/*
 * CONFIDENTIAL FORD MOTOR COMPANY
 * This is an unpublished work of authorship, which contains confidential information and/or trade secrets, created in 2019. Ford Motor Company owns all rights to this work and intends to maintain it in confidence to preserve its trade secret status. Ford Motor Company reserves all rights, under the copyright laws of the United States or those of any other country that may have jurisdiction, including the right to protect this work as an unpublished work, in the event of an inadvertent or deliberate unauthorized publication. Use of this work constitutes an agreement to maintain the confidentiality of the work, and to refrain from any reverse engineering, decompilation, or disassembly of this work.
 * Ford Motor Company also reserves its rights under all copyright laws to protect this work as a published work, when appropriate. Those having access to this work may not copy it, use it, modify it, or disclose the information contained in it without the written authorization of Ford Motor Company.
 * Copyright 2019, Ford Motor Company.
 *
 */

package com.ford.androidutils.ui.glide;

public class GlideProvider implements GlideProviderInterface {
//
//    private static final String FILE_URI_PREFIX = "file:///";
//    private static final String CONTENT_URI_PREFIX = "content://";
//    private final Context mContext;
//
//    public GlideProvider(@ForApplication Context context) {
//        mContext = context;
//    }
//
//    public DrawableTypeRequest load(String url) {
//        if (!TextUtils.isEmpty(url) && (url.startsWith(FILE_URI_PREFIX) || url.startsWith(CONTENT_URI_PREFIX)) ) {
//            // Note: Glide does not support loading local assets via String uri, so we need to parse a Uri object.
//            return Glide.with(mContext).load(Uri.parse(url));
//        } else {
//            return Glide.with(mContext).load(url);
//        }
//    }
//
//    public DrawableTypeRequest load(File file) {
//        return Glide.with(mContext).load(file);
//    }
//
//    public DrawableTypeRequest<Uri> load(Uri uri) {
//        return Glide.with(mContext).load(uri);
//    }
//
//    public DrawableTypeRequest load(int resId) {
//        return Glide.with(mContext).load(resId);
//    }
//
//    public static class GlideBitmapImageViewTarget extends BitmapImageViewTarget {
//        public GlideBitmapImageViewTarget(ImageView view) {
//            super(view);
//        }
//
//        @Override
//        protected void setResource(Bitmap resource) {
//            RoundedBitmapDrawable circularBitmapDrawable =
//                    RoundedBitmapDrawableFactory.create(view.getResources(), resource);
//            circularBitmapDrawable.setCircular(true);
//            view.setImageDrawable(circularBitmapDrawable);
//        }
//    }
}
