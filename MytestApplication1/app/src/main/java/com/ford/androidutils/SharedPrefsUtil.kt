/*
 * CONFIDENTIAL FORD MOTOR COMPANY
 * This is an unpublished work of authorship, which contains confidential information and/or trade secrets, created in 2019. Ford Motor Company owns all rights to this work and intends to maintain it in confidence to preserve its trade secret status. Ford Motor Company reserves all rights, under the copyright laws of the United States or those of any other country that may have jurisdiction, including the right to protect this work as an unpublished work, in the event of an inadvertent or deliberate unauthorized tion. Use of this work constitutes an agreement to maintain the confidentiality of the work, and to refrain from any reverse engineering, decompilation, or disassembly of this work.
 * Ford Motor Company also reserves its rights under all copyright laws to protect this work as a published work, when appropriate. Those having access to this work may not copy it, use it, modify it, or disclose the information contained in it without the written authorization of Ford Motor Company.
 * Copyright 2019, Ford Motor Company.
 *
 */

package com.ford.androidutils

//import com.ford.androidutils.preferences.*

interface SharedPrefsUtil :
//        AccountPreferences,
//        AirQualityPreferences,
//        BikesharePreferences,
//        ConsentPreferences,
//        FilterPreferences
{

//    var numIncorrectPinAttempts: Int
//    val vehicleParkingLocations: String?
//    val lastAppLinkMessageId: Int
//    val isPreLoginOnboardingCompleted: Boolean
//    var latestFetchedTNCVersion: String?
//    var ASDNVehiclesLastUpdatedTimestamp: Long
//    var isNgsdnVehicleDatabaseInitialized: Boolean
//    var applinkGatewayUuid: String?
//    val fuelSortingPreference: Int
//    var findDisclaimerPreference: Boolean
//    val fuelGradePreference: Int
//    var isDestinationSentFirstTime: Boolean
//    var currentVehicleVin: String
//    var overrideEnvironment: String?
//    var persistentChat: Boolean
//    var guidesContext: Int
//    var selectedTab: String?
//    var fordCreditUserName: String?
//    var weChatOpenId: String?
//    var weChatAccessToken: String?
//    var amoWechatRedirectUrl: String?
//    var warrantyLastUpdatedTimestamp: Long
//    var appCatalogLastUpdatedTimestamp: Long
//    val userConsentUserResponse: String?
//    var weatherCacheResponse: String?
//    var weatherLastUpdatedTime: Long
//    var cmsTimeZoneLastUpdatedTime: Long
//    var smartChargingLastUpdatedTime: Long
//    var weatherLastUpdatedLocale: String?
//    var isDistractedWarningShownForNonConsent: Boolean
//    var lastViewedPageOnDashBoard: String?
//    val featureFlags: Set<String>
//    var dealerCurrencyCode: String?
//    var jointVentureFlag: String?
//    var firstImpressionViewStatus: Boolean
//    var applinkConnectedVin: String?
//    var onboardingScreensDisplayed: Boolean
//    var rememberUsernameStatus: Boolean
//    val isDoNotDisplayExtendedWarrantyChecked: Boolean
//    var messagesLastFetchedDate: String?
//    var doNotShowJourneyDeleteDialog: Boolean
//    var hasSeenJourneysOnboarding: Boolean
//    var hasUploadedJourneys: Boolean
//    var isBoundaryAlertTutorialShown: Boolean
//    var isAppDiscoveryTutorialShown: Boolean
//    var isAppCategoryDetailsTutorialShown: Boolean
//    var optFlagCorrelationId: String
//    var optFlagCorrelationTimeRemaining: Long
//    var whatsNewScreenStatus: Boolean
//    var journeyNotificationsEnabled: Boolean
//    var doNotShowLightsAndHornModalAgain: Boolean
//    var doNotShowCabinUnlockModalAgain: Boolean
//    var doNotShowCargoUnlockModalAgain: Boolean
//    var forceUpdateVersion: String
//    var forceUpdateRequired: Boolean
//    var versionCheckLastUpdatedTime : Long
//    var rocketSetupKeyShared: Boolean
//    var rocketSetupMuid: String
//    var globalStartStopCorrelationId: Int
//    var globalStartStopCorrelationTimeRemaining: Long
//    var doNotShowDoubleUnlockModalAgain: Boolean
//    var doNotShowDoubleLockModalAgain: Boolean
//    var doNotShowReducedSecurityModalAgain: Boolean
//    var hasSeenRegistrationSuccessScreen: Boolean
//    val authorizedActionMessageIds: Set<String>
//    var hasNotSeenVSHOnboardingScreen: Boolean
//    var hasSeenSendToVehicleOnBoardingScreen: Boolean
//
//    fun clearLoginDependentValues()
//    fun clearDatabaseFlags()
//    fun clearSavedParkingSpotImage()
//    fun setSavedParkingSpotImageLocation(imageLocation: String)
//    fun setOptInFlagCorrelationId(correlationId: String)
//    fun setOptInFlagCorrelationTimeRemaining(timeStamp: Long)
//    fun setLastAppLinkMessageId(messageId: Int)
//    fun setHasLoggedIntoFordCreditPreviously(hasSeenFordCreditOnboarding: Boolean)
//    fun hasLoggedIntoFordCreditPreviously(): Boolean
//    fun setHasSeenFuelOnboarding(hasSeenFuelOnboarding: Boolean)
//    fun hasSeenFuelOnboarding(): Boolean
//    fun hasSeenPickupDeliveryTNCScreens(): Boolean
//    fun setHasSeenPickupDeliveryTNCScreens(enabled: Boolean)
//    fun setKeySalt(salt: String?)
//    fun setFuelSortingPreference(sortingPreference: Int)
//    fun setFuelGradePreference(gradePreference: Int)
//    fun hasWeChatLogin(): Boolean
//    fun setWeChatLogin(hasLogin: Boolean)
//    fun setHasSeenParkWalkThroughScreens(enabled: Boolean)
//    fun hasSeenParkWalkThroughScreens(): Boolean
//    fun setCurrentVehicleRegistrationState(vin: String?, state: String?)
//    fun getCurrentVehicleRegistrationState(vin: String?): String?
//    fun hasShowResetPasswordBanner(): Boolean
//    fun setShowResetPasswordBanner(enabled: Boolean)
//    fun setLatestFetchedPrivacyAndPolicyVersion(version: String?)
//    fun setHasCheckedDoNotShowAndroidAutoWarningAgain(hasCheckedDoNotShowAndroidAutoWarningAgain: Boolean)
//    fun hasCheckedDoNotShowAndroidAutoWarningAgain(): Boolean
//    fun clearCachedCustomerData()
//    fun hasGivenCookieContent(): Boolean
//    fun clearWeatherLastUpdatedTime()
//    fun clearFordCreditLoginDetails()
//    fun setFeatureFLags(flags: Set<String>)
//    fun shouldMigratePin(): Boolean
//    fun setShouldMigratePin(value: Boolean)
//    fun setDoNotDisplayExtendedWarrantyPopup(isChecked: Boolean)
//    fun isVehicleCommandStatusError(vin: String?): Boolean
//    fun setVehicleCommandStatusError(vin: String?, value: Boolean)
//    fun isAuthorizationStatusIsInitialized(vin: String?): Boolean
//    fun setAuthorizationStatusIsInitialized(vin: String?, value: Boolean)
//    fun isPrimaryAuthorizationRequest(vin: String?): Boolean
//    fun setIsPrimaryAuthorizationRequest(vin: String?, value: Boolean)
//    fun setHasSeenPaakEducationFlow(value: Boolean)
//    fun hasSeenPaakEducationFlow(): Boolean
//    fun setVinForCurrentForegroundService(value: String = "")
//    fun setVehicleNickNameForCurrentForegroundService(value: String = "")
//    fun getVehicleNickNameForPaakKeyDelivered(): String?
//    fun getVinForCurrentForegroundService(): String?
//    fun setIsRetailUbiActive(vin: String?, value: Boolean)
//    fun isRetailUbiActive(vin: String?): Boolean
//    fun getCurrentUserName(): String
//    fun setVehicleNickName(value: String = "")
//    fun getVehicleNickName(): String
//    fun getHasNewJourneys(): Boolean
//    fun setHasNewJourneys(value: Boolean)
//    fun setNextServiceDate(vin: String?, value: String?)
//    fun getNextServiceDate(vin: String?): String?
//    fun setCurrentTabVehicleVin(vin: String)
//    fun getCurrentTabVehicleVin(): String
//    fun setCurrentTabVehicleData(vehicleDataJson: String?)
//    fun getCurrentTabVehicleData(): String
//    fun hasZoneLightingEducationSeen(): String?
//    fun setZoneLightingEducationSeen()
//    fun setFnolEcallConfirmationStatus(eCallConfirmationStatus: String)
//    fun getFnolEcallConfirmationStatus(): String
//    fun setComesFromEvTripPlanner(value: Boolean)
//    fun getComesFromEvTripPlanner(): Boolean
//    fun setSmartChargingStatusForVin(vin: String, onBoarded: String)
//    fun getSmartChargingStatusForVin(vin: String): String
//    fun getPinHash(): String
//    fun getPinSalt(): String
//    fun clearSavedPinValues()
//    fun setdisplayName(displayName: String?)
//    fun getdisplayName(): String?
//    fun clearAppcatalogLastUpdatedTime()
//    fun setAuthorizationRequestList(list: Set<String>)
//
//    enum class Key {
//        //Permanent
//        APP_HAS_LAUNCHED_BEFORE,
//        APP_LINK_LAST_MESSAGE_ID,
//        APP_LINK_GATEWAY_UUID,
//
//        // Database Flags - must be removed in clearDatabaseFlags();
//        ASDN_VEHICLES_LAST_UPDATED_TIMESTAMP,
//        NGSDN_VEHICLE_DATABASE_INITIALIZED,
//        OSB_BOOKING_DATABASE_INITIALIZED,
//
//        //User-specific
//        ENCRYPTION_SALT,
//        CURRENT_USERNAME,
//        CURRENT_PASSWORD,
//        CURRENT_PASSWORD_IV,
//        PIN_HASH,
//        ENCRYPTION_HASH,
//        PIN_SALT,
//        IS_USING_PIN_TO_ENCRYPT,
//        SELECTED_COUNTRY,
//        ACCOUNT_COUNTRY,
//        ACCOUNT_STATE,
//        FIRST_NAME,
//        LAST_NAME,
//        MEMBER_ID,
//        NUMBER_OF_PIN_ATTEMPTS,
//        VEHICLE_PARKING_LOCATIONS,
//        APPLET_ORDERING,
//        APP_LINK_AUTH_TOKEN,
//        APP_LINK_HAS_PREVIOUSLY_CONNECTED_VEHICLE,
//        HAS_CHECKED_DO_NOT_SHOW_ANDROID_AUTO_WARNING_AGAIN,
//        HAS_CHECKED_DO_NOT_SHOW_REDUCED_SECURITY_MODAL_AGAIN,
//        HAS_CHECKED_DO_NOT_SHOW_DOUBLE_UNLOCK_MODAL_AGAIN,
//        HAS_CHECKED_DO_NOT_SHOW_DOUBLE_LOCK_MODAL_AGAIN,
//        HAS_LOGGED_INTO_FORD_CREDIT_PREVIOUSLY,
//        HAS_SEEN_FORD_CREDIT_ON_BOARDING,
//        HAS_SEEN_DEALER_ONBOARDING,
//        HAS_SEEN_VEHICLE_CONTROLS_ONBOARDING,
//        HAS_SEEN_MY_VEHICLES_ON_BOARDING,
//        HAS_SEEN_PARK_ONBOARDING,
//        HAS_SEEN_APPRECIATION_ONBOARDING,
//        HAS_SEEN_FUEL_ONBOARDING,
//        HAS_SEEN_DESTINATIONS_ONBOARDING,
//        HAS_CONFIRMED_GPS_TRACKING_CONSENT,
//        HAS_SEEN_CAR_SHARE_ONBOARDING,
//        HAS_SEEN_ANDROID_AUTO_WARNING,
//        FORD_CREDIT_USERNAME,
//        FORD_CREDIT_PASSWORD,
//        FORD_CREDIT_PASSWORD_IV,
//        AMO_WECHAT_REDIRECT_URL,
//        PUSH_NOTIFICATION_ENABLED,
//        LATEST_FETCHED_TNC_VERSION,
//        ASDN_AUTH_TOKEN,
//        ASDN_AUTH_TOKEN_IV,
//        TIMESTAMP_OF_LAST_SUCCESSFUL_RESET_PASSWORD_CODE_REQUEST,
//        PRELOGIN_ONBOARDING_COMPLETED,
//        POSTLOGIN_ONBOARDING_COMPLETED,
//        HAS_SEEN_DASHBOARD_COACHMARK,
//        HAS_SEEN_SYNC_ONBOARDING,
//        CURRENT_BORROW_PASSWORD,
//        CURRENT_BORROW_PASSWORD_IV,
//        CURRENT_BORROW_CUSTOMER_NUMBER,
//        CURRENT_BORROW_CARD_NUMBER,
//        PICKUP_AND_DELIVERY_LAST_VIN,
//        HAS_SEEN_BORROW_ONBOARDING,
//        HAS_SEEN_DESTINATION_QUEUE_COACHMARK,
//        PARK_ONBOARDING_VERSION,
//        HAS_TRIGGERED_BIKE_SHARE_NOTIFICATION,
//        BIKE_SHARE_LAST_CHECKED_TIME_IN_MILLIS,
//        BIKE_SHARE_USERNAME,
//        BIKE_SHARE_IS_LOGGED_IN,
//        PARK_FILTER_PREFERENCES,
//        FUEL_FILTER_PREFERENCES,
//        CHARGING_FILTER_PREFERENCES,
//        DEALER_FILTER_PREFERENCES,
//        HAS_SEEN_PARK_PIN_PARKING_COACHMARK,
//        HAS_SEEN_BIKE_SHARE_ONBOARDING,
//        HAS_CONFIRMED_BIKE_SHARE_THIRD_PARTY_WARNING,
//        LIGHTHOUSE_TOKEN,
//        FORD_PASS_USER_LIGHTHOUSE_TOKEN,
//        LIGHTHOUSE_REFRESH_TOKEN,
//        HAS_WECHAT_ACCOUNT,
//        FIRST_TIME_SENT_DESTINATION_TO_VEHICLE,
//        PARKING_IMAGE_LOCATION,
//        PARKING_WALKTHROUGH_COMPLETED,
//        FUEL_SORTING_PREFERENCE,
//        FUEL_GRADE_PREFERENCE,
//        FIND_SEARCH_DISCLAIMER_PREFERENCE,
//        EXTERIOR_AIR_QUALITY_CLOUD_QUERY_INTERVAL,
//        AIR_QUALITY_SYNC_TRANSMIT_RATE,
//        OVERRIDE_ENVIRONMENT,
//        PICKUP_AND_DELIVERY_ONBOARDING,
//        PICKUP_AND_DELIVERY_TNC,
//        SELECTED_TAB,
//        HAS_SHOW_RESET_PASSWORD_BANNER,
//        LATEST_FETCHED_PRIVACY_AND_POLICY_VERSION,
//        NGSDN_AUTH_TOKEN,
//        CURRENT_VEHICLE_DETAIL_VIN,
//        CURRENT_VEHICLE_REGISTRATION_STATE,
//        ACCOUNT_PHONE_NUMBER,
//        ACCOUNT_MOBILE_PHONE_NUMBER,
//        HAS_ACCEPTED_COOKIE_CONSENT,
//        HAS_GIVEN_COOKIE_CONSENT,
//        BIKE_SHARE_CONFIRMATION,
//        BIKE_SHARE_PENALTY_WARNING_DONOT_SHOW_FLAG,
//        WARRANTY_LAST_UPDATED_TIMESTAMP,
//        APPCATALOG_LAST_UPDATED_TIMESTAMP,
//        CONSENT_PROFILE,
//        VERSION_CHECK_LAST_UPDATE,
//        FORCE_UPDATE_VERSION,
//        FORCE_UPDATE_REQUIRED,
//        WEATHER_LAST_UPDATED_TIMESTAMP,
//        CMS_TIMEZONE_LAST_UPDATED_TIMESTAMP,
//        WEATHER_RESPONSE_CACHE,
//        DISTRACTED_WARNING_SHOWN_NO_CONSENT,
//        DEVICE_LOCALE_FOR_WEATHER,
//        DEALER_CURRENCY_CODE,
//        FEATURE_FLAGS,
//        WECHAT_OPENID,
//        WECHAT_ACCESS_TOKEN,
//        JOINT_VENTURE_FLAG,
//        INTERIOR_AIR_QUALITY,
//        EXTERIOR_AIR_QUALITY,
//        EXTERIOR_AIR_QUALITY_IS_INITIALIZED,
//        FIRST_IMPRESSION_SHOWN,
//        HAS_WECHAT_LOGIN,
//        INTERIOR_AIR_QUALITY_COLOR_THRESHOLD,
//        APPLINK_CONNECTED_VIN,
//        LAST_VIEWED_PAGE_ON_DASHBOARD,
//        DIGITAL_COPILOT_ONBOARDING_DISPLAYED,
//        DIGITAL_COPILOT_INRIX_SEARCH_RADIUS,
//        REMEMBER_USERNAME,
//        DO_NOT_SHOW_EXTENDED_WARRANTY_POPUP,
//        VEHICLE_COMMAND_ERROR,
//        CONSENT_LAST_UPDATED_TIME_STAMP,
//        PRIVACY_LAST_UPDATED_TIME_STAMP,
//        AUTHORIZATION_STATUS_IS_INITIALIZED,
//        IS_PRIMARY_AUTHORIZATION_REQUEST,
//        MESSAGE_LAST_FETCHED_DATE,
//        HAS_SEEN_PAAK_EDUCATION_FLOW,
//        PERSISTENT_CHAT,
//        GUIDES_CONTEXT,
//        DO_NOT_SHOW_JOURNEYS_DELETE_DIALOG,
//        FOREGROUND_SERVICE_CURRENT_VIN,
//        FOREGROUND_SERVICE_CURRENT_NICK_NAME,
//        IS_RETAIL_UBI_ACTIVE,
//        VEHICLE_NICK_NAME,
//        NEXT_SERVICE_DATE,
//        HAS_SEEN_JOURNEYS_ONBOARDING,
//        HAS_NOT_SEEN_SERVICE_HISTORY_ONBOARDING,
//        HAS_UPLOADED_JOURNEYS,
//        IS_BOUNDARY_ALERT_TUTORIAL_SHOWN,
//        CURRENT_TAB_VEHICLE_VIN,
//        CURRENT_TAB_VEHICLE_DATA,
//        HAS_ZONE_LIGHTING_EDUCATION_SEEN,
//        FNOL_ECALL_CONFIRMATION_STATUS,
//        COMES_FROM_EV_TRIP_PLANNER,
//        IS_APP_DISCOVERY_TUTORIAL_SHOWN,
//        OPT_IN_FLAG_CORRELATION_ID,
//        OPT_IN_FLAG_CORRELATION_TIME_REMAINING,
//        GLOBAL_START_STOP_CORRELATION_ID,
//        GLOBAL_START_STOP_CORRELATION_TIME_REMAINING,
//        HAS_SEEN_REGISTRATION_SUCCESS_SCREEN,
//        DISPLAY_NAME,
//        IS_APP_CATEGORY_DETAILS_TUTORIAL_SHOWN,
//        SMART_CHARGING_LAST_UPDATED_TIMESTAMP,
//        SMART_CHARGING_ONBOARDED_VINS,
//        WHATS_NEW_SCREEN_SHOWN,
//        JOURNEY_NOTIFICATIONS_ENABLED,
//        IS_APP_CATALOG_VALID,
//        APP_CATALOG_SYNC_VERSION,
//        DO_NOT_SHOW_LIGHTS_AND_HORN_MODAL_AGAIN,
//        DO_NOT_SHOW_CABIN_UNLOCK_MODAL_AGAIN,
//        DO_NOT_SHOW_CARGO_UNLOCK_MODAL_AGAIN,
//        IS_ROCKET_SETUP_KEY_SHARED,
//        ROCKET_SETUP_MUID,
//        AUTHORIZED_ACTION_MESSAGE_IDS,
//        HAS_SEEN_SEND_TO_VEHICLE_ON_BOARDING_SCREEN
//    }
}
