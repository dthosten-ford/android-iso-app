/*
 * CONFIDENTIAL FORD MOTOR COMPANY
 * This is an unpublished work of authorship, which contains confidential information and/or trade secrets, created in 2019. Ford Motor Company owns all rights to this work and intends to maintain it in confidence to preserve its trade secret status. Ford Motor Company reserves all rights, under the copyright laws of the United States or those of any other country that may have jurisdiction, including the right to protect this work as an unpublished work, in the event of an inadvertent or deliberate unauthorized publication. Use of this work constitutes an agreement to maintain the confidentiality of the work, and to refrain from any reverse engineering, decompilation, or disassembly of this work.
 * Ford Motor Company also reserves its rights under all copyright laws to protect this work as a published work, when appropriate. Those having access to this work may not copy it, use it, modify it, or disclose the information contained in it without the written authorization of Ford Motor Company.
 * Copyright 2019, Ford Motor Company.
 *
 */

package com.ford.rxutils.schedulers;

import com.ford.rxutils.schedulers.rx2.SchedulingHelperRx2;

import javax.inject.Inject;

import io.reactivex.CompletableTransformer;
import io.reactivex.FlowableTransformer;
import io.reactivex.MaybeTransformer;
import io.reactivex.ObservableTransformer;
import io.reactivex.SingleTransformer;

//public class RxSchedulingHelper extends RxSchedulingHelperInterface {
//
//    private final SchedulingHelperRx2 schedulersRx2;
//
//    @Inject
//    public RxSchedulingHelper(SchedulingHelperRx2 schedulersRx2) {
//        this.schedulersRx2 = schedulersRx2;
//    }
//
//    public <T> ObservableTransformer<T, T> observableSchedulers(@Threads int thread) {
//        return schedulersRx2.observableSchedulers(thread);
//    }
//
//    public <T> FlowableTransformer<T, T> flowableSchedulers(@Threads int thread) {
//        return schedulersRx2.flowableSchedulers(thread);
//    }
//
//    public <T> SingleTransformer<T, T> singleSchedulers(@Threads int thread) {
//        return schedulersRx2.singleSchedulers(thread);
//    }
//
//    public <T> MaybeTransformer<T, T> maybeSchedulers(@Threads int thread) {
//        return schedulersRx2.maybeSchedulers(thread);
//    }
//
//    public CompletableTransformer completableSchedulers(@Threads int thread) {
//        return schedulersRx2.completableSchedulers(thread);
//    }
//}