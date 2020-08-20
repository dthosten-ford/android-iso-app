/*
 * CONFIDENTIAL FORD MOTOR COMPANY
 * This is an unpublished work of authorship, which contains confidential information and/or trade secrets, created in 2019. Ford Motor Company owns all rights to this work and intends to maintain it in confidence to preserve its trade secret status. Ford Motor Company reserves all rights, under the copyright laws of the United States or those of any other country that may have jurisdiction, including the right to protect this work as an unpublished work, in the event of an inadvertent or deliberate unauthorized publication. Use of this work constitutes an agreement to maintain the confidentiality of the work, and to refrain from any reverse engineering, decompilation, or disassembly of this work.
 * Ford Motor Company also reserves its rights under all copyright laws to protect this work as a published work, when appropriate. Those having access to this work may not copy it, use it, modify it, or disclose the information contained in it without the written authorization of Ford Motor Company.
 * Copyright 2019, Ford Motor Company.
 *
 */

package com.fordmps.mobileapp.shared.utils;

public interface ErrorMessageUtil {

//    private final NetworkingErrorUtil networkingErrorUtil;
//    private TransientDataProvider transientDataProvider;
//
//    @Inject
//    public ErrorMessageUtil(NetworkingErrorUtil networkingErrorUtil, TransientDataProvider transientDataProvider) {
//        this.networkingErrorUtil = networkingErrorUtil;
//        this.transientDataProvider = transientDataProvider;
//    }
//
//    public void showNetworkError(Throwable throwable) {
//        showNetworkError(getErrorStatusCode(throwable));
//    }
//
//    public int getErrorStatusCode(Throwable throwable) {
//        return networkingErrorUtil.getErrorStatusCode(throwable);
//    }
//
//    public void showNetworkError(int statusCode) {
//        final int errorMessage = getErrorMessage(statusCode);
//        showErrorMessage(errorMessage);
//    }
//
//    public void showPersistentErrorMessage(@StringRes int message) {
//        transientDataProvider.save(new InfoMessageBannerUseCase(new InfoMessage(InfoMessage.ERROR, message), false));
//    }
//
//    public void showErrorMessage(@StringRes int message) {
//        transientDataProvider.save(new InfoMessageBannerUseCase(new InfoMessage(InfoMessage.ERROR, message), true));
//    }
//
//    public void showErrorMessage(@StringRes int message, int duration) {
//        transientDataProvider.save(new InfoMessageBannerUseCase(new InfoMessage(InfoMessage.ERROR, message, duration), true));
//    }
//
//    public void closeErrorMessage() {
//        transientDataProvider.save(new HideInfoMessageBannerUseCase());
//    }
//
//    private int getErrorMessage(int statusCode) {
//        return networkingErrorUtil.isConnectivityError(statusCode) ? R.string.common_error_checkConnection : R.string.common_error_something_went_wrong;
//    }
}