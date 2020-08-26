/*
 * CONFIDENTIAL FORD MOTOR COMPANY
 * This is an unpublished work of authorship, which contains confidential information and/or trade secrets, created in 2019. Ford Motor Company owns all rights to this work and intends to maintain it in confidence to preserve its trade secret status. Ford Motor Company reserves all rights, under the copyright laws of the United States or those of any other country that may have jurisdiction, including the right to protect this work as an unpublished work, in the event of an inadvertent or deliberate unauthorized publication. Use of this work constitutes an agreement to maintain the confidentiality of the work, and to refrain from any reverse engineering, decompilation, or disassembly of this work.
 * Ford Motor Company also reserves its rights under all copyright laws to protect this work as a published work, when appropriate. Those having access to this work may not copy it, use it, modify it, or disclose the information contained in it without the written authorization of Ford Motor Company.
 * Copyright 2019, Ford Motor Company.
 *
 */

package com.ford.ngsdnuser.providers;

//import android.annotation.SuppressLint;
//
//import androidx.annotation.NonNull;
//import androidx.annotation.Nullable;
//
//import com.ford.androidutils.CacheUtil;
//import com.ford.androidutils.SharedPrefsUtil;
//import com.ford.locale.ServiceLocaleProvider;
//import com.ford.networkutils.utils.StatusCodes;
//import com.ford.ngsdnuser.repositories.AccountInfoRepository;
//import com.ford.rxutils.CacheTransformerProvider;
//import com.ford.rxutils.RxSchedulerProvider;
//import com.ford.userservice.devicemanagement.models.BaseUserServicesResponse;
//import com.ford.userservice.updateprofile.models.AccountProfile;
//import com.ford.userservice.updateprofile.models.PrivacyPreference;
//import com.ford.userservice.updateprofile.models.UpdateMarketingLanguageRequest;
//import com.ford.userservice.updateprofile.models.UpdateProfileRequest;
//import com.ford.userservice.updateprofile.models.UpdateUnitOfMeasureRequest;
//import com.ford.utils.TextUtils;
//import com.google.common.base.Optional;
//
//import java.util.ArrayList;
//import java.util.Iterator;
//import java.util.List;
//import java.util.concurrent.Callable;
//
//import javax.inject.Inject;
//import javax.inject.Singleton;
//
//import dagger.Lazy;
//import io.reactivex.Observable;
//import io.reactivex.ObservableSource;
//import io.reactivex.subjects.PublishSubject;
//
//import static com.ford.rxutils.CacheTransformerProvider.Policy.LOCAL_THEN_NETWORK_IF_CACHE_STALE;

//@Singleton
//public class AccountInfoProvider extends AccountInfoProviderInterface {
//
//    private final Lazy<NgsdnUserProvider> ngsdnUserProvider;
//    private final AccountInfoRepository accountInfoRepository;
//    private final CacheUtil cacheUtil;
//    private final CustomerCredentialsStorageProvider customerCredentialsStorageProvider;
//    private final CacheTransformerProvider cacheTransformerProvider;
//    private final SharedPrefsUtil sharedPrefsUtil;
//    private final RxSchedulerProvider rxSchedulerProvider;
//    private final Lazy<ServiceLocaleProvider> serviceLocaleProviderLazy;
//    private final PublishSubject<AccountProfile> accountProfilePublishSubject = PublishSubject.create();
//    private final int TERMS_ACCEPTED = 1;
//
//    @Inject
//    public AccountInfoProvider(Lazy<NgsdnUserProvider> ngsdnUserProvider,
//                               Lazy<ServiceLocaleProvider> serviceLocaleProvider,
//                               AccountInfoRepository accountInfoRepository,
//                               CacheUtil cacheUtil,
//                               CustomerCredentialsStorageProvider customerCredentialsStorageProvider,
//                               CacheTransformerProvider cacheTransformerProvider,
//                               SharedPrefsUtil sharedPrefsUtil,
//                               RxSchedulerProvider rxSchedulerProvider) {
//        this.ngsdnUserProvider = ngsdnUserProvider;
//        this.serviceLocaleProviderLazy = serviceLocaleProvider;
//        this.accountInfoRepository = accountInfoRepository;
//        this.cacheUtil = cacheUtil;
//        this.customerCredentialsStorageProvider = customerCredentialsStorageProvider;
//        this.cacheTransformerProvider = cacheTransformerProvider;
//        this.sharedPrefsUtil = sharedPrefsUtil;
//        this.rxSchedulerProvider = rxSchedulerProvider;
//    }
//
//    //region public methods
//
//    @NonNull
//    public Observable<AccountProfile> getAccountInfo(CacheTransformerProvider.Policy cachePolicy) {
//        return accountInfoObservable.compose(
//                cacheTransformerProvider.get(cachePolicy, getCacheAccountInfoObservable(), cacheUtil::isAccountProfileCacheStale));
//    }
//
//    @NonNull
//    public Observable<AccountProfile> updateAccountInfo(final AccountProfile profile) {
//        Observable<BaseUserServicesResponse> updateAccountInfoObservable = ngsdnUserProvider.get().updateAccountInformation(profile);
//        return updateAccountInfoInCache(updateAccountInfoObservable, profile);
//    }
//
//    @NonNull
//    public Observable<AccountProfile> updateAccountInfo(final UpdateUnitOfMeasureRequest updateUnitOfMeasureRequest, final AccountProfile profile) {
//        Observable<BaseUserServicesResponse> updateAccountInfoObservable = ngsdnUserProvider.get().updateAccountInformation(updateUnitOfMeasureRequest);
//        return updateAccountInfoInCache(updateAccountInfoObservable, profile);
//    }
//
//    @NonNull
//    public Observable<AccountProfile> updateAccountInfo(final UpdateMarketingLanguageRequest updateMarketingLanguageRequest, final AccountProfile profile) {
//        Observable<BaseUserServicesResponse> updateAccountInfoObservable = ngsdnUserProvider.get().updateAccountInformation(updateMarketingLanguageRequest);
//        return updateAccountInfoInCache(updateAccountInfoObservable, profile);
//    }
//
//    @NonNull
//    public Observable<AccountProfile> updateAccountInfo(final UpdateProfileRequest updateProfileRequest, final AccountProfile profile) {
//        Observable<BaseUserServicesResponse> updateAccountInfoObservable = ngsdnUserProvider.get().updateAccountInformation(updateProfileRequest);
//        return updateAccountInfoInCache(updateAccountInfoObservable, profile);
//    }
//
//    @SuppressLint("CheckResult")
//    @NonNull
//    public void checkAndUpdateMarketingLanguage() {
//        getAccountInfo(LOCAL_THEN_NETWORK_IF_CACHE_STALE)
//                .flatMap(accountProfile -> {
//                    String applicationLanguage = serviceLocaleProviderLazy.get().getApplicationDisplayLanguage();
//                    if (!accountProfile.getLanguage().equalsIgnoreCase(applicationLanguage)) {
//                        accountProfile.setLanguage(applicationLanguage);
//                        UpdateMarketingLanguageRequest languageRequest = new UpdateMarketingLanguageRequest(applicationLanguage);
//                        return updateAccountInfo(languageRequest, accountProfile);
//                    } else {
//                        return Observable.just(accountProfile);
//                    }
//                })
//                .subscribeOn(rxSchedulerProvider.getIoScheduler())
//                .subscribe(accountProfile -> {}, Throwable::printStackTrace);
//    }
//
//    @NonNull
//    public Observable<AccountProfile> updateAccountInfoCache(final BaseUserServicesResponse baseUserServicesResponse, final AccountProfile profile) {
//        if (baseUserServicesResponse.getStatus() == StatusCodes.SUCCESS) {
//            return Observable.fromCallable(() -> {
//                persistProfileAndNotifyListener(profile);
//                cacheUtil.clearAccountProfileLastUpdatedTimestamp();
//                return accountInfoRepository.getAccountInfo(profile.getUserId());
//            });
//        } else return Observable.error(new Throwable());
//    }
//
//    public Observable<AccountProfile> getAccountProfileUpdatePassiveObservable() {
//        return accountProfilePublishSubject.hide();
//    }
//
//    public Observable<String> getAccountCountry() {
//        return Observable.fromCallable(() -> !TextUtils.isEmpty(sharedPrefsUtil.getAccountCountry()) ? sharedPrefsUtil.getAccountCountry() : sharedPrefsUtil.getSelectedCountry());
//    }
//
//    public String getPhoneNumber() {
//        return sharedPrefsUtil.getAccountPhoneNumber();
//    }
//
//    //endregion
//
//    //region private methods
//
//    private final Observable<AccountProfile> accountInfoObservable = Observable.defer(new Callable<ObservableSource<? extends AccountProfile>>() {
//        @Override
//        public ObservableSource<? extends AccountProfile> call() {
//            return ngsdnUserProvider.get().getAccountInformation()
//                    .observeOn(rxSchedulerProvider.getIoScheduler())
//                    .map(ngsdnAccountInformationResponse -> {
//                        AccountProfile profile = ngsdnAccountInformationResponse.getProfile();
//                        persistProfileAndNotifyListener(profile);
//                        cacheUtil.updateAccountProfileLastUpdatedTimestamp();
//                        return accountInfoRepository.getAccountInfo(customerCredentialsStorageProvider.getUsername());
//                    })
//                    .observeOn(rxSchedulerProvider.getMainThreadScheduler());
//        }
//    }).replay().refCount();
//
//    @NonNull
//    private Observable<AccountProfile> updateAccountInfoInCache(Observable<BaseUserServicesResponse> observable, final AccountProfile profile) {
//        return observable.flatMap(baseUserServicesResponse -> updateAccountInfoCache(baseUserServicesResponse, profile));
//    }
//
//    @NonNull
//    private Observable<Optional<AccountProfile>> getCacheAccountInfoObservable() {
//        final String username = customerCredentialsStorageProvider.getUsername();
//        return Observable.defer(() -> Observable.just(Optional.fromNullable(accountInfoRepository.getAccountInfo(username))));
//    }
//
//    private void persistProfileAndNotifyListener(AccountProfile profile) {
//        accountInfoRepository.updateAccountInfo(profile);
//        accountProfilePublishSubject.onNext(profile);
//    }
//
//    //endregion
//
//    public Observable<AccountProfile> updatePrivacyPreferences(List<PrivacyPreference> preferenceList) {
//        return getAccountInfo(LOCAL_THEN_NETWORK_IF_CACHE_STALE)
//                .flatMap(accountProfile -> {
//                    updateProfilePrivacyPreferences(accountProfile, preferenceList);
//                    Observable<BaseUserServicesResponse> updateTermsObservable = ngsdnUserProvider.get().updateMarketingPreferences(preferenceList).toObservable();
//                    return updateAccountInfoInCache(updateTermsObservable, accountProfile);
//                });
//    }
//
//    public Observable<Boolean> getPreferencesStatus(String preferenceType) {
//        return getAccountInfo(LOCAL_THEN_NETWORK_IF_CACHE_STALE)
//                .flatMap(accountProfile -> {
//                    if (accountProfile.getPrivacyPreferences() != null) {
//                        PrivacyPreference privacyPreference = findPrivacyPreferenceByType(accountProfile.getPrivacyPreferences(), preferenceType);
//                        if (privacyPreference != null) {
//                            return Observable.just(privacyPreference.isAllowed());
//                        }
//                    }
//                    return Observable.just(false);
//                });
//    }
//
//    public Observable<List<PrivacyPreference>> getPrivacyPreferences() {
//        return getAccountInfo(LOCAL_THEN_NETWORK_IF_CACHE_STALE).map(accountProfile -> accountProfile.getPrivacyPreferences() != null ? accountProfile.getPrivacyPreferences() : new ArrayList<>());
//    }
//
//    private void updateProfilePrivacyPreferences(AccountProfile accountProfile, List<PrivacyPreference> privacyPreferences) {
//        List<PrivacyPreference> accountProfilePrivacyPreferences = accountProfile.getPrivacyPreferences();
//        if (accountProfilePrivacyPreferences == null) {
//            accountProfilePrivacyPreferences = new ArrayList<>();
//        }
//
//        Iterator<PrivacyPreference> iterator = privacyPreferences.iterator();
//        while (iterator.hasNext()) {
//            PrivacyPreference currentPreference = iterator.next();
//            PrivacyPreference accountProfilePreference = findPrivacyPreferenceByType(accountProfilePrivacyPreferences, currentPreference.getPreferenceType());
//            if (accountProfilePreference != null) {
//                accountProfilePrivacyPreferences.remove(accountProfilePreference);
//            }
//            accountProfilePrivacyPreferences.add(currentPreference);
//        }
//    }
//
//    @Nullable
//    private PrivacyPreference findPrivacyPreferenceByType(List<PrivacyPreference> privacyPreferences, String preferenceType) {
//        for (PrivacyPreference privacyPreference : privacyPreferences) {
//            if (privacyPreference.isSameType(preferenceType))
//                return privacyPreference;
//        }
//        return null;
//    }
//}

