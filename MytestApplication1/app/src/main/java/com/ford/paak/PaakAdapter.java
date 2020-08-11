/*
 * CONFIDENTIAL FORD MOTOR COMPANY
 * This is an unpublished work of authorship, which contains confidential information and/or trade secrets, created in 2019. Ford Motor Company owns all rights to this work and intends to maintain it in confidence to preserve its trade secret status. Ford Motor Company reserves all rights, under the copyright laws of the United States or those of any other country that may have jurisdiction, including the right to protect this work as an unpublished work, in the event of an inadvertent or deliberate unauthorized publication. Use of this work constitutes an agreement to maintain the confidentiality of the work, and to refrain from any reverse engineering, decompilation, or disassembly of this work.
 * Ford Motor Company also reserves its rights under all copyright laws to protect this work as a published work, when appropriate. Those having access to this work may not copy it, use it, modify it, or disclose the information contained in it without the written authorization of Ford Motor Company.
 * Copyright 2019, Ford Motor Company.
 *
 */

package com.ford.paak;

//import com.ford.paak.bluetooth.ActiveCommands;
//import com.ford.paak.bluetooth.events.BleEvents;
//import com.ford.paak.bluetooth.service.BleService;
//import com.ford.paak.data.key.status.VehicleKey;
//import com.ford.paak.data.key.status.VehicleKeyStatesResponse;
//import com.ford.paak.models.ActiveVehicleKey;
//
//import java.util.List;
//
//import io.reactivex.Completable;
//import io.reactivex.Observable;
//import io.reactivex.Single;
//import io.reactivex.functions.Function;

public interface PaakAdapter {
//    /**
//     * Method : init
//     *
//     * Pre Conditions : User Has Logged In for the first time and PaaK is enabled in the Shared Prefs
//     *
//     * Post Conditions : RSA key has been created, and the public key has been published to PaakFi
//     * Any keys from previous installations have been re-activated
//     *
//     * Role: Initialize dependencies like the RSA key and Vehicle Key Metadata
//     */
//    Completable init();
//
//    /**
//     * Method : reinstateKeys
//     *
//     * Pre Conditions : User Has Logged In And has delivered a CAK in the past
//     *
//     * Post Conditions : Any keys from previous installations have been re-activated
//     *
//     * Role: return existing CAK(s) to users
//     */
//    Completable reinstateKeys();
//
//    /**
//     * Method : Logout
//     *
//     * Pre Conditions : User is attempting to log out of app
//     *
//     * Post Conditions : All data is cleared on logout that applies to PaaK
//     */
//    void logout();
//
//    /**
//     * Method : setSelectedVehicle
//     *
//     * Pre Conditions : PaaK has been initialized and a PaaK capable Vehicle Exists
//     *
//     * Post Conditions : All necessary information has been set in Shared Prefs to distinguish between vehicles
//     *
//     * Role : Set variables needed in querying vehicle specific data and identifying which vehicle we are communicating with
//     * @param vin = Vin of vehicle we want to communicate with
//     */
//    void setSelectedVehicle(String vin);
//
//    /**
//     * Method : getSelectedVin
//     *
//     * Pre Conditions : VehicleHasBeenSetBy {@link PaakAdapter#setSelectedVehicle}
//     *
//     * Role : Return Vin
//     */
//    String getSelectedVin();
//
//    /**
//     * Method : requestConsumerAccessKeyAndPairingPin
//     *
//     * Pre Conditions : RSA Key has been initialized by {@link PaakAdapter#init}
//     *
//     * Post Conditions : Key request has been sent to PaakFi and Pairing Pin has been returned to UX layer
//     *
//     * Role : Send a request to download a key, generate a pairing pin based on the metadata for that key, and store the key locally
//     * @param opCode = 0
//     * @param packageCode = Server defined feature code (PAAK_PK00001 as of time of writing)
//     * @param vin = vehicle request is for
//     * @param keyName = user set name of key
//     * @param role = ??? TODO : Ask Steve
//     */
//    Single<String> requestConsumerAccessKeyAndPairingPin(int opCode, String packageCode, String vin, String keyName, String role);
//
//    /**
//     * Method : deliverKey
//     *
//     * Pre Conditions : key has been requested and has successfully downloaded in {@link PaakAdapter#requestConsumerAccessKeyAndPairingPin}
//     *
//     * Post Conditions : Key delivery has been attempted
//     *
//     * Role : Start the key delivery Service and observe the result
//     * @param vin = vehicle we would like to deliver key to
//     */
//    Completable deliverKey(String vin);
//
//    /**
//     * Method : hasActiveKeys
//     *
//     * Pre Conditions : None
//     *
//     * Role : Make a database query to see if there are active vehicle keys on this device
//     */
//    Single<Boolean> hasActiveKeys();
//
//    /**
//     * Method : hasActiveKeysForVin
//     *
//     * PreConditions : None
//     *
//     * Role : Make a database query to see if there are active vehicle keys on this device for a specific vin
//     *
//     * @param vin
//     */
//    Single<Boolean> hasActiveKeysForVin(String vin);
//
//    /**
//     * Method : getLocalActiveVehicleKeys
//     *
//     * Pre Conditions : Keys have been successfully delivered to a vehicle with {@link PaakAdapter#deliverKey}
//     *
//     * Role : Return currently active keys for display to the user
//     */
//    Single<List<ActiveVehicleKey>> getLocalActiveVehicleKeys();
//
//    /**
//     * Method : sendActiveCommand
//     *
//     * Pre Conditions : Key has been delivered to a vehicle and we have established a session using {@link BleService.ServerType#AUTH}
//     *
//     * Post Conditions : Command has been queued for connected vehicle
//     *
//     * Role : Queue a known command based on known {@link ActiveCommands} constants
//     * @param command = known ActiveCommand
//     * @param bleService = Currently running BleService (post successful dsk delivery)
//     */
//    void sendActiveCommand(ActiveCommands command, BleService bleService);
//
//    /**
//     * Method : getActiveCommandsCompletable
//     *
//     * Pre Conditions : None
//     *
//     * Post Condition : Generates a hidden PublishSubject that propagates the success or failure of a command
//     *
//     * Role : Wait for a success/fail for a command sent in {@link PaakAdapter#sendActiveCommand}
//     * @param command = command we are listening for the result of
//     */
//    Completable getActiveCommandsCompletable(ActiveCommands command);
//
//    /**
//     * Method : revokeKey
//     *
//     * Pre Condition : Key already downloaded {@link PaakAdapter#requestConsumerAccessKeyAndPairingPin}
//     *
//     * Role : Revokes the requested key
//     * @param keyId = key to be revoked
//     */
//    Completable revokeKey(String keyId);
//
//    /**
//     * Method : revokeKeyForSelectedVehicle
//     *
//     * Pre Condition : Key already downloaded {@link PaakAdapter#requestConsumerAccessKeyAndPairingPin}
//     *
//     * Role : Revokes the requested key
//     * @param keyId = key to be revoked
//     */
//    Completable revokeKeyForSelectedVehicle(String keyId);
//
//    /**
//     * Method : getKeyStatus
//     *
//     * Pre Conditions : RSA Key has been initialized by {@link PaakAdapter#init}
//     *
//     * Post Condition : Returns a list of keys
//     *
//     * Role : Get a list of keys for a given vehicle across all accounts
//     * Deprecated : This method is virtually the same as {@link PaakAdapter#getKeyStates}
//     * @param vin
//     */
//    @Deprecated
//    Single<List<VehicleKey>> getKeyStatus(String vin);
//
//    /**
//     * Method : getKeyStates
//     *
//     * Pre Conditions : RSA Key has been initialized by {@link PaakAdapter#init}
//     *
//     * Post Condition : Returns a list of keys, a count of active keys, and a max number of keys allowed
//     *
//     * Role : Get a list of keys for a given vehicle across all accounts and a limit of max keys allowed
//     * @param vin
//     */
//    Single<VehicleKeyStatesResponse> getKeyStates(String vin);
//
//    /**
//     * Method : getActiveVehicleKeysFromNetwork
//     *
//     * Pre Conditions : RSA Key has been initialized by {@link PaakAdapter#init}
//     *
//     * Post Condition : Returns a list of keys
//     *
//     * Role : Return the list of keys using an object that is accessible in LW
//     * @param vin
//     */
//    Single<List<ActiveVehicleKey>> getActiveVehicleKeysFromNetwork(String vin);
//
//    /**
//     * Method : connectionObservable
//     *
//     * Pre Conditions : None
//     *
//     * Post Condition : Returns a listener for the connection state of the phone to the blem
//     *
//     * Role : Give the app a way to monitor connection states after starting the {@link BleService}
//     */
//    Observable<BleEvents.Connection> connectionObservable();
//
//    /**
//     * Method : vehicleStatusObservable
//     *
//     * Pre Conditions : None
//     *
//     * Post Condition : Returns a listener for the vehicle state to the phone
//     *
//     * Role : Give the app a way to monitor vehicle states after starting the {@link BleService}
//     */
//    Observable<BleEvents.VehicleStatus> vehicleStatusObservable();
//
//    /**
//     * Method : hasDeliveredBlemCakPacketByVin
//     *
//     * Pre Condition : Key has been downloaded with {@link PaakAdapter#requestConsumerAccessKeyAndPairingPin}
//     *
//     * Role : check to see if blem delivery packet has been removed (key delivery has been attempted)
//     * @param vin
//     */
//    Single<Boolean> hasDeliveredBlemCakPacketByVin(String vin);
//
//    /**
//     * Method : getPairingPin
//     *
//     * Pre Condition : Key has been downloaded
//     *
//     * Post Condition : Pin has been generated
//     *
//     * Role : Retrieve bluetooth pairing pin for a downloaded cak
//     *
//     * Deprecated : Replaced with {@link PaakAdapter#requestConsumerAccessKeyAndPairingPin}, only use for test implementations
//     * @param vin
//     */
//    @Deprecated
//    Single<String> getPairingPin(String vin);
//
//    /**
//     * Method : hasConsumerAccessKeyByVin
//     *
//     * Pre Condition : None
//     *
//     * Role : check to see if any cak metadata data is available for a given vehicle
//     * @param vin
//     */
//    Single<Boolean> hasConsumerAccessKeyByVin(String vin);
//
//    /**
//     * Method : revokeLocalKey
//     *
//     * Pre Condition : A key has been downloaded and the metadata still exists
//     *
//     * Post Condition : An attempt is made to revoke the key on PaakFi
//     *
//     * Role : Attempt to revoke all keys for this vin and delete metadata on successful network revoke
//     * @param vin
//     */
//    Single<Boolean> revokeLocalKey(String vin);
//
//    /**
//     * Method : getServiceMessageCenterUpdates
//     *
//     * Pre Condition : User is logged in
//     *
//     * Post Condition : Deletes all metadata for keys revoked from the server
//     *
//     * Role : Check for remotely revoked keys; delete a key from the device if the key is revoked from some other device.
//     *
//     * @param muid = unique identifier set on first login (installation specific)
//     * @param messageId = the last received message id (The service should return messages which has a message id greater than this)
//     */
//    Completable getServiceMessageCenterUpdates(String muid, int messageId);
//
//    /**
//     * Method : exportLogs
//     *
//     * Pre Condition : Logs related to communications related to BLEM and captured in database
//     *
//     * Post Condition : Logs will be saved to Downloads folder with file name as current timestamp
//     *
//     * Role : Downloading the logs related to BLEM communication to local directory
//     */
//    Completable exportLogs();
//
//    /**
//     * Method : clearLogs
//     *
//     * Pre Condition : Logs related to communications related to BLEM and captured in database
//     *
//     * Post Condition : The logs are cleared
//     *
//     * Role : Clearing the logs related to BLEM communication from database
//     */
//    void clearLogs();
//
//    /**
//     * Method : startBeaconListening
//     *
//     * Pre Condition : Key is delivered to vehicle and beaconId exists
//     *
//     * Post Condition : service is registered with OS
//     *
//     * Role : Start beacon to manage paak background service
//     */
//    Completable startBeaconListening(String vin);
//
//    /**
//     * Method : sendVehicleStatusRequest
//     *
//     * Pre Conditions : Connected to vehicle via Bluetooth
//     *
//     * Post Condition : sends a command request for the vehicle state to the blem
//     *
//     * Role : Give the app a way to issue a command request for vehicle states
//     */
//    void sendVehicleStatusRequest(ActiveCommands activeCommand, BleService bleService);
//
//    /**
//     * Method : isCalibrationDataAvailable
//     *
//     * PreConditions : None
//     *
//     * Role : Make a shared preference query to see if there is calibration data for this device
//     */
//    boolean isCalibrationDataAvailable();
//
//    /**
//     * Method : isForeGroundServiceRunning
//     *
//     * Pre Conditions : Connected to vehicle via Bluetooth
//     *
//     * Post Condition : if foreground service is not running , start the service
//     *
//     * Role : Returns whether foreground service is running
//     */
//    Observable<Boolean> isForeGroundServiceRunning();
//
//    /**
//     * Method : handleAuthServer
//     *
//     * Pre Condition : current selected vehicle vin. Logic for if the auth server should be restarted
//     *
//     * Role : Check for active key for vin, check if auth server is currently running for vin, apply delay for auth server if needed.
//     *
//     */
//
//    Single<Boolean> handleAuthServer(String currentVin, Function<Boolean, Boolean> shouldRestartAuthServer);
}