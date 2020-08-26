///*
// * CONFIDENTIAL FORD MOTOR COMPANY
// * This is an unpublished work of authorship, which contains confidential information and/or trade secrets, created in 2019. Ford Motor Company owns all rights to this work and intends to maintain it in confidence to preserve its trade secret status. Ford Motor Company reserves all rights, under the copyright laws of the United States or those of any other country that may have jurisdiction, including the right to protect this work as an unpublished work, in the event of an inadvertent or deliberate unauthorized publication. Use of this work constitutes an agreement to maintain the confidentiality of the work, and to refrain from any reverse engineering, decompilation, or disassembly of this work.
// * Ford Motor Company also reserves its rights under all copyright laws to protect this work as a published work, when appropriate. Those having access to this work may not copy it, use it, modify it, or disclose the information contained in it without the written authorization of Ford Motor Company.
// * Copyright 2019, Ford Motor Company.
// *
// */
//
//package com.fordmps.mobileapp.shared.events;
//
//import com.fordmps.mobileapp.move.StartActivityEvent;
//
//import javax.inject.Inject;
//import javax.inject.Singleton;
//
//import io.reactivex.Observable;
//import io.reactivex.subjects.BehaviorSubject;
//import io.reactivex.subjects.PublishSubject;
//
//@Singleton
//public class UnboundViewEventBus implements UnboundViewEventBusInterface {
//    @Override
//    public void send(StartActivityEvent event) {
//
//    }
////
////    private final PublishSubject<ToastEvent> toastSubject = PublishSubject.create();
////    private final PublishSubject<SnackbarEvent> snackbarSubject = PublishSubject.create();
////    private final PublishSubject<DismissSnackbarEvent> dismissSnackbarSubject = PublishSubject.create();
////    private final PublishSubject<StartActivityEvent> startActivitySubject = PublishSubject.create();
////    private final PublishSubject<StartPillarFragmentEvent> startPillarFragmentSubject = PublishSubject.create();
////    private final PublishSubject<ReplaceFragmentEvent> replaceFragmentEventPublishSubject = PublishSubject.create();
////    private final PublishSubject<FinishActivityEvent> finishActivitySubject = PublishSubject.create();
////    private final PublishSubject<DialogEvent> dialogSubject = PublishSubject.create();
////    private final PublishSubject<DialerEvent> dialerSubject = PublishSubject.create();
////    private final PublishSubject<PhoneNumberPickerEvent> phoneNumberPickerSubject = PublishSubject.create();
////    private final PublishSubject<MessageEvent> messageSubject = PublishSubject.create();
////    private final PublishSubject<FordDialogEvent> fordDialogSubject = PublishSubject.create();
////    private final PublishSubject<DismissFordDialogEvent> dismissFordDialogSubject = PublishSubject.create();
////    private final PublishSubject<DatePickerEvent> datePickerSubject = PublishSubject.create();
////    private final PublishSubject<TimePickerEvent> timePickerSubject = PublishSubject.create();
////    private final PublishSubject<ChargeTimePickerEvent> chargeTimePickerSubject = PublishSubject.create();
////    private final PublishSubject<RequestPermissionEvent> requestPermissionSubject = PublishSubject.create();
////    private final PublishSubject<RequestPermissionRationaleEvent> requestPermissionRationaleSubject = PublishSubject.create();
////    private final PublishSubject<RadioGroupDialogEvent> radioGroupDialogSubject = PublishSubject.create();
////    private final PublishSubject<CheckboxDialogEvent> checkboxDialogSubject = PublishSubject.create();
////    private final PublishSubject<StartResolutionEvent> startResolutionSubject = PublishSubject.create();
////    private final PublishSubject<HideKeyboardEvent> hideKeyboardSubject = PublishSubject.create();
////    private final PublishSubject<ClearClipboardEvent> clearClipboardSubject = PublishSubject.create();
////    private final PublishSubject<LaunchExternalBrowserEvent> browserUtilSubject = PublishSubject.create();
////    private final PublishSubject<NotificationEvent> notificationSubject = PublishSubject.create();
////    private final PublishSubject<ComponentStateEvent> enableComponentSubject = PublishSubject.create();
////    private final PublishSubject<CancelNotificationEvent> cancelNotificationSubject = PublishSubject.create();
////    private final PublishSubject<PopupMenuEvent> popupMenuEventSubject = PublishSubject.create();
////    private final BehaviorSubject<DeepLinkEvent> deepLinkEventPublishSubject = BehaviorSubject.create();
////    private final PublishSubject<StartServiceEvent> startServiceEventPublishSubject = PublishSubject.create();
////    private final PublishSubject<StopServiceEvent> stopServiceEventPublishSubject = PublishSubject.create();
////    private final PublishSubject<BindServiceEvent> bindServiceEventPublishSubject = PublishSubject.create();
////    private final PublishSubject<UnbindServiceEvent> unbindServiceEventPublishSubject = PublishSubject.create();
////    private final PublishSubject<CopyToClipboardEvent> copyToClipboardEventPublishSubject = PublishSubject.create();
////    private final PublishSubject<ToolbarLogoClickEvent> toolbarLogoClickEventPublishSubject = PublishSubject.create();
////    private final PublishSubject<AddTabSectionsEvent> addTabSectionsEventPublishSubject = PublishSubject.create();
////    private final PublishSubject<ShowBottomSheetEvent> showBottomSheetEventPublishSubject = PublishSubject.create();
////    private final PublishSubject<RemoveBottomSheetEvent> removeBottomSheetEventPublishSubject = PublishSubject.create();
////
////    @Inject
////    UnboundViewEventBus() {
////    }
////
////    public void send(ToastEvent event) {
////        toastSubject.onNext(event);
////    }
////
////    public void send(SnackbarEvent event) {
////        snackbarSubject.onNext(event);
////    }
////
////    public void send(DismissSnackbarEvent event) {
////        dismissSnackbarSubject.onNext(event);
////    }
////
////    public void send(StartActivityEvent event) {
////        startActivitySubject.onNext(event);
////    }
////
////    public void send(StartResolutionEvent event) {
////        startResolutionSubject.onNext(event);
////    }
////
////    public void send(StartPillarFragmentEvent event) {
////        startPillarFragmentSubject.onNext(event);
////    }
////
////    public void send(ReplaceFragmentEvent event) {
////        replaceFragmentEventPublishSubject.onNext(event);
////    }
////
////    public void send(FinishActivityEvent event) {
////        finishActivitySubject.onNext(event);
////    }
////
////    public void send(DialogEvent event) {
////        dialogSubject.onNext(event);
////    }
////
////    public void send(DialerEvent event) {
////        dialerSubject.onNext(event);
////    }
////
////    public void send(PhoneNumberPickerEvent event) {
////        phoneNumberPickerSubject.onNext(event);
////    }
////
////    public void send(MessageEvent event) {
////        messageSubject.onNext(event);
////    }
////
////    public void send(FordDialogEvent event) {
////        fordDialogSubject.onNext(event);
////    }
////
////    public void send(DismissFordDialogEvent event) {
////        dismissFordDialogSubject.onNext(event);
////    }
////
////    public void send(DatePickerEvent event) {
////        datePickerSubject.onNext(event);
////    }
////
////    public void send(TimePickerEvent event) {
////        timePickerSubject.onNext(event);
////    }
////
////    public void send(ChargeTimePickerEvent event) {
////        chargeTimePickerSubject.onNext(event);
////    }
////
////    public void send(RequestPermissionEvent event) {
////        requestPermissionSubject.onNext(event);
////    }
////
////    public void send(RequestPermissionRationaleEvent event) {
////        requestPermissionRationaleSubject.onNext(event);
////    }
////
////    public void send(RadioGroupDialogEvent event) {
////        radioGroupDialogSubject.onNext(event);
////    }
////
////    public void send(CheckboxDialogEvent event) {
////        checkboxDialogSubject.onNext(event);
////    }
////
////    public void send(HideKeyboardEvent event) {
////        hideKeyboardSubject.onNext(event);
////    }
////
////    public void send(ClearClipboardEvent event) {
////        clearClipboardSubject.onNext(event);
////    }
////
////    public void send(LaunchExternalBrowserEvent event) {
////        browserUtilSubject.onNext(event);
////    }
////
////    public void send(NotificationEvent event) {
////        notificationSubject.onNext(event);
////    }
////
////    public void send(ComponentStateEvent event) {
////        enableComponentSubject.onNext(event);
////    }
////
////    public void send(CancelNotificationEvent event) {
////        cancelNotificationSubject.onNext(event);
////    }
////
////    public void send(PopupMenuEvent event) {
////        popupMenuEventSubject.onNext(event);
////    }
////
////    public void send(StartServiceEvent event) {
////        startServiceEventPublishSubject.onNext(event);
////    }
////
////    public void send(StopServiceEvent event) {
////        stopServiceEventPublishSubject.onNext(event);
////    }
////
////    public void send(BindServiceEvent event) {
////        bindServiceEventPublishSubject.onNext(event);
////    }
////
////    public void send(UnbindServiceEvent event) {
////        unbindServiceEventPublishSubject.onNext(event);
////    }
////
////    public void send(DeepLinkEvent event) {
////        deepLinkEventPublishSubject.onNext(event);
////    }
////
////    public void send(CopyToClipboardEvent event) {
////        copyToClipboardEventPublishSubject.onNext(event);
////    }
////
////    public void send(ToolbarLogoClickEvent event) {
////        toolbarLogoClickEventPublishSubject.onNext(event);
////    }
////
////    public void send(AddTabSectionsEvent event) {
////        addTabSectionsEventPublishSubject.onNext(event);
////    }
////
////    public void send(ShowBottomSheetEvent event) {
////        showBottomSheetEventPublishSubject.onNext(event);
////    }
////
////    public void send(RemoveBottomSheetEvent event) {
////        removeBottomSheetEventPublishSubject.onNext(event);
////    }
////
////    public Observable<ToastEvent> toast(Object viewModel) {
////        return toast(viewModel.getClass());
////    }
////
////    public Observable<ToastEvent> toast(Class viewModelClass) {
////        return toastSubject.filter(event -> fromEmitter(event, viewModelClass));
////    }
////
////    public Observable<SnackbarEvent> snackbar(Object viewModel) {
////        return snackbar(viewModel.getClass());
////    }
////
////    public Observable<SnackbarEvent> snackbar(Class viewModelClass) {
////        return snackbarSubject.filter(event -> fromEmitter(event, viewModelClass));
////    }
////
////    public Observable<DismissSnackbarEvent> dismissSnackbar(Class viewModelClass) {
////        return dismissSnackbarSubject.filter(event -> fromEmitter(event, viewModelClass));
////    }
////
////    public Observable<StartActivityEvent> startActivity(Object viewModel) {
////        return startActivity(viewModel.getClass());
////    }
////
////    public Observable<StartActivityEvent> startActivity(Class viewModelClass) {
////        return startActivitySubject.filter(event -> fromEmitter(event, viewModelClass));
////    }
////
////    public Observable<StartResolutionEvent> resolveLocation(Class viewModelClass) {
////        return startResolutionSubject.filter(event -> fromEmitter(event, viewModelClass));
////    }
////
////    // StartPillarFragment events don't get filtered because the TabBarActivity
////    // should listen to all pillar fragments that want to start other pillar fragments
////    public Observable<StartPillarFragmentEvent> startPillarFragment() {
////        return startPillarFragmentSubject.hide();
////    }
////
////    public Observable<ReplaceFragmentEvent> replaceFragment(Class viewModelClass) {
////        return replaceFragmentEventPublishSubject.filter(event -> fromEmitter(event, viewModelClass));
////    }
////
////    public Observable<FinishActivityEvent> finishActivity(Object viewModel) {
////        return finishActivity(viewModel.getClass());
////    }
////
////    public Observable<FinishActivityEvent> finishActivity(Class viewModelClass) {
////        return finishActivitySubject.filter(event -> fromEmitter(event, viewModelClass));
////    }
////
////    public Observable<HideKeyboardEvent> hideKeyboard(Object viewModel) {
////        return hideKeyboard(viewModel.getClass());
////    }
////
////    public Observable<HideKeyboardEvent> hideKeyboard(Class viewModelClass) {
////        return hideKeyboardSubject.filter(event -> fromEmitter(event, viewModelClass));
////    }
////
////    public Observable<ClearClipboardEvent> clearClipboard(Object viewModel) {
////        return clearClipboard(viewModel.getClass());
////    }
////
////    public Observable<ClearClipboardEvent> clearClipboard(Class viewModelClass) {
////        return clearClipboardSubject.filter(event -> fromEmitter(event, viewModelClass));
////    }
////
////    public Observable<DialogEvent> dialog(Object viewModel) {
////        return dialog(viewModel.getClass());
////    }
////
////    public Observable<DialogEvent> dialog(Class viewModelClass) {
////        return dialogSubject.filter(event -> fromEmitter(event, viewModelClass));
////    }
////
////    public Observable<DialerEvent> dialer(Object viewModel) {
////        return dialer(viewModel.getClass());
////    }
////
////    public Observable<DialerEvent> dialer(Class viewModelClass) {
////        return dialerSubject.filter(event -> fromEmitter(event, viewModelClass));
////    }
////
////    public Observable<LaunchExternalBrowserEvent> launchExternalBrowser(Object viewModel) {
////        return launchExternalBrowser(viewModel.getClass());
////    }
////
////    public Observable<LaunchExternalBrowserEvent> launchExternalBrowser(Class viewModelClass) {
////        return browserUtilSubject.filter(event -> fromEmitter(event, viewModelClass));
////    }
////
////    public Observable<PhoneNumberPickerEvent> phoneNumberPicker() {
////        return phoneNumberPickerSubject.hide();
////    }
////
////    public Observable<MessageEvent> message(Class viewModelClass) {
////        return messageSubject.filter(event -> fromEmitter(event, viewModelClass));
////    }
////
////    public Observable<FordDialogEvent> fordDialog(Object viewModel) {
////        return fordDialog(viewModel.getClass());
////    }
////
////    public Observable<FordDialogEvent> fordDialog(Class viewModelClass) {
////        return fordDialogSubject.filter(event -> fromEmitter(event, viewModelClass));
////    }
////
////    public Observable<DismissFordDialogEvent> dismissFordDialog(Object viewModel) {
////        return dismissFordDialog(viewModel.getClass());
////    }
////
////    public Observable<DismissFordDialogEvent> dismissFordDialog(Class viewModelClass) {
////        return dismissFordDialogSubject.filter(event -> fromEmitter(event, viewModelClass));
////    }
////
////    public Observable<RequestPermissionEvent> requestPermission(Object viewModel) {
////        return requestPermission(viewModel.getClass());
////    }
////
////    public Observable<RequestPermissionEvent> requestPermission(Class viewModelClass) {
////        return requestPermissionSubject.filter(event -> fromEmitter(event, viewModelClass));
////    }
////
////    public Observable<RequestPermissionRationaleEvent> requestPermissionRationale(Object viewModel) {
////        return requestPermissionRationale(viewModel.getClass());
////    }
////
////    public Observable<RequestPermissionRationaleEvent> requestPermissionRationale(Class viewModelClass) {
////        return requestPermissionRationaleSubject.filter(event -> fromEmitter(event, viewModelClass));
////    }
////
////    public Observable<DatePickerEvent> datePicker() {
////        return datePickerSubject.hide();
////    }
////
////    public Observable<TimePickerEvent> timePicker() {
////        return timePickerSubject.hide();
////    }
////
////    public Observable<ChargeTimePickerEvent> chargeTimePicker(Class viewModelClass) {
////        return chargeTimePickerSubject.filter(events -> fromEmitter(events, viewModelClass));
////    }
////
////    public Observable<RadioGroupDialogEvent> radioGroupDialog() {
////        return radioGroupDialogSubject.hide();
////    }
////
////    public Observable<CheckboxDialogEvent> checkboxDialog() {
////        return checkboxDialogSubject.hide();
////    }
////
////    public Observable<NotificationEvent> notification(Object viewModel) {
////        return notification(viewModel.getClass());
////    }
////
////    public Observable<NotificationEvent> notification(Class viewModelClass) {
////        return notificationSubject.filter(event -> fromEmitter(event, viewModelClass));
////    }
////
////    public Observable<NotificationEvent> notification() {
////        return notificationSubject.hide();
////    }
////
////    public Observable<ComponentStateEvent> componentState(Class viewModelClass) {
////        return enableComponentSubject.filter(event -> fromEmitter(event, viewModelClass));
////    }
////
////    public Observable<ComponentStateEvent> componentState() {
////        return enableComponentSubject.hide();
////    }
////
////    public Observable<CancelNotificationEvent> cancelNotification() {
////        return cancelNotificationSubject.hide();
////    }
////
////    public Observable<PopupMenuEvent> popupMenu(Object viewModel) {
////        return popupMenu(viewModel.getClass());
////    }
////
////    public Observable<PopupMenuEvent> popupMenu(Class viewModelClass) {
////        return popupMenuEventSubject.filter(event -> fromEmitter(event, viewModelClass));
////    }
////
////    public Observable<StartServiceEvent> startService(Class viewModelClass) {
////        return startServiceEventPublishSubject.filter(event -> fromEmitter(event, viewModelClass));
////    }
////
////    public Observable<StopServiceEvent> stopService(Class viewModelClass) {
////        return stopServiceEventPublishSubject.filter(event -> fromEmitter(event, viewModelClass));
////    }
////
////    public Observable<BindServiceEvent> bindService(Class viewModelClass) {
////        return bindServiceEventPublishSubject.filter(event -> fromEmitter(event, viewModelClass));
////    }
////
////    public Observable<UnbindServiceEvent> unbindService(Class viewModelClass) {
////        return unbindServiceEventPublishSubject.filter(event -> fromEmitter(event, viewModelClass));
////    }
////
////    public Observable<CopyToClipboardEvent> copyToClipboard(Class viewModelClass) {
////        return copyToClipboardEventPublishSubject.filter(event -> fromEmitter(event, viewModelClass));
////    }
////
////    public Observable<ToolbarLogoClickEvent> toolbarLogoClicked(Class viewModelClass) {
////        return toolbarLogoClickEventPublishSubject.filter(event -> fromEmitter(event, viewModelClass));
////    }
////
////    public Observable<AddTabSectionsEvent> addTabSection(Class viewModelClass) {
////        return addTabSectionsEventPublishSubject.filter(event -> fromEmitter(event, viewModelClass));
////    }
////
////    public Observable<DeepLinkEvent> deepLink() {
////        return deepLinkEventPublishSubject.hide();
////    }
////
////    public Observable<ShowBottomSheetEvent> showBottomSheet(Class viewModelClass) {
////        return showBottomSheetEventPublishSubject.filter(event -> fromEmitter(event, viewModelClass));
////    }
////
////    public Observable<RemoveBottomSheetEvent> removeBottomSheet(Class viewModelClass) {
////        return removeBottomSheetEventPublishSubject.filter(event -> fromEmitter(event, viewModelClass));
////    }
////
////    private boolean fromEmitter(BaseUnboundViewEvent event, Class viewModelClass) {
////        return viewModelClass.getName().equals((event).getEmitter().getClass().getName());
////    }
//}
