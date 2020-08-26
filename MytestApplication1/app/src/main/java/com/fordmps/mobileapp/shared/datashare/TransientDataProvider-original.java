/*
 * CONFIDENTIAL FORD MOTOR COMPANY
 * This is an unpublished work of authorship, which contains confidential information and/or trade secrets, created in 2019. Ford Motor Company owns all rights to this work and intends to maintain it in confidence to preserve its trade secret status. Ford Motor Company reserves all rights, under the copyright laws of the United States or those of any other country that may have jurisdiction, including the right to protect this work as an unpublished work, in the event of an inadvertent or deliberate unauthorized publication. Use of this work constitutes an agreement to maintain the confidentiality of the work, and to refrain from any reverse engineering, decompilation, or disassembly of this work.
 * Ford Motor Company also reserves its rights under all copyright laws to protect this work as a published work, when appropriate. Those having access to this work may not copy it, use it, modify it, or disclose the information contained in it without the written authorization of Ford Motor Company.
 * Copyright 2019, Ford Motor Company.
 *
 */

package com.fordmps.mobileapp.shared.datashare;

//import com.fordmps.mobileapp.shared.datashare.usecases.UseCase;
//
//import java.util.concurrent.ConcurrentHashMap;
//import java.util.concurrent.ConcurrentMap;
//
//import javax.inject.Inject;
//import javax.inject.Singleton;
//
//import io.reactivex.Observable;
//import io.reactivex.subjects.PublishSubject;

//@Singleton
//public class TransientDataProvider extends TransientDataProviderInterface {
//    private ConcurrentMap<String, Object> transientData = new ConcurrentHashMap<>();
//    private PublishSubject<String> dataSubject = PublishSubject.create();
//    ConcurrentMap<Class, UseCase> transientDataNew = new ConcurrentHashMap<>();
//    PublishSubject<Class> dataSubjectNew = PublishSubject.create();
//
//    @Inject
//    public TransientDataProvider() {
//    }
//
//    public void save(UseCase useCase) {
//        final Class<? extends UseCase> useCaseClass = useCase.getClass();
//        transientDataNew.put(useCaseClass, useCase);
//        dataSubjectNew.onNext(useCaseClass);
//    }
//
//    public <T extends UseCase> T remove(Class<T> useCaseClass) {
//        final UseCase removedUseCase = transientDataNew.remove(useCaseClass);
//        return useCaseClass.cast(removedUseCase);
//    }
//
//    public <T extends UseCase> boolean containsUseCase(Class<T> useCaseClass) {
//        return transientDataNew.containsKey(useCaseClass);
//    }
//
//    public Observable<Class> observeUseCase(Class useCaseClass) {
//        return dataSubjectNew.hide().filter(clazz -> clazz.equals(useCaseClass));
//    }
//
//    public <T extends UseCase> Observable<T> useCaseObservable(Class<T> useCaseClass) {
//        return observeUseCase(useCaseClass).map(key -> transientData.remove(useCaseClass)).cast(useCaseClass);
//    }
//}