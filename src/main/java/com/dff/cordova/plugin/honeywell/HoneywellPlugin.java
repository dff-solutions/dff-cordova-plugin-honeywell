package com.dff.cordova.plugin.honeywell;

import com.dff.cordova.plugin.common.CommonPlugin;
import com.dff.cordova.plugin.common.action.CordovaAction;
import com.dff.cordova.plugin.common.log.CordovaPluginLog;
import com.dff.cordova.plugin.honeywell.barcode.BarcodeDeviceListener;
import com.dff.cordova.plugin.honeywell.barcode.BarcodeListener;
import com.dff.cordova.plugin.honeywell.barcode.action.*;
import com.dff.cordova.plugin.honeywell.common.BarcodeReaderManager;
import com.honeywell.aidc.AidcManager;
import com.honeywell.aidc.AidcManager.CreatedCallback;
import com.honeywell.aidc.ScannerUnavailableException;
import org.apache.cordova.CallbackContext;
import org.json.JSONArray;
import org.json.JSONException;

public class HoneywellPlugin extends CommonPlugin {

    private static final String LOG_TAG = "com.dff.cordova.plugin.honeywell.HoneywellPlugin";
    private AidcManager aidcManager;
    private BarcodeReaderManager barcodeReaderManager;
    private BarcodeListener barcodeListener;
    private BarcodeDeviceListener barcodeDeviceListener;

    public HoneywellPlugin() {
        super(LOG_TAG);
    }

    /**
     * Called after plugin construction and fields have been initialized.
     */
    @Override
    public void pluginInitialize() {
        super.pluginInitialize();

        this.barcodeListener = new BarcodeListener();
        this.barcodeDeviceListener = new BarcodeDeviceListener();
        this.barcodeReaderManager = new BarcodeReaderManager();

        AidcManager.create(this.cordova.getActivity(), new CreatedCallback() {

            @Override
            public void onCreated(AidcManager aidcManager) {

                CordovaPluginLog.d(LOG_TAG, "AidcManager created");
                HoneywellPlugin.this.aidcManager = aidcManager;

                // register barcode device listener
                HoneywellPlugin.this.aidcManager.addBarcodeDeviceListener(HoneywellPlugin.this.barcodeDeviceListener);
            }
        });
    }

    /**
     * Called when the activity will start interacting with the user.
     *
     * @param multitasking Flag indicating if multitasking is turned on for app
     */
    @Override
    public void onResume(boolean multitasking) {
        super.onResume(multitasking);

        if (barcodeReaderManager.getInstance() != null) {
            CordovaPluginLog.d(LOG_TAG, "claim barcode reader");

            try {
                barcodeReaderManager.getInstance().claim();
            } catch (ScannerUnavailableException e) {
                CordovaPluginLog.e(LOG_TAG, e.getMessage(), e);
            }
        }
    }

    /**
     * Called when the system is about to start resuming a previous activity.
     *
     * @param multitasking Flag indicating if multitasking is turned on for app
     */
    @Override
    public void onPause(boolean multitasking) {
        super.onPause(multitasking);
        if (barcodeReaderManager.getInstance() != null) {
            CordovaPluginLog.d(LOG_TAG, "release barcode reader");
            // release the scanner claim so we don't get any scanner
            // notifications while paused.
            barcodeReaderManager.getInstance().release();
        }
    }

    /**
     * The final call you receive before your activity is destroyed.
     */
    @Override
    public void onDestroy() {
        super.onDestroy();

        if (barcodeReaderManager.getInstance() != null) {
            // unregister barcode event listener
            barcodeReaderManager.getInstance().removeBarcodeListener(this.barcodeListener);

            // close BarcodeReader to clean up resources.
            // once closed, the object can no longer be used.
            barcodeReaderManager.getInstance().close();
        }
        if (this.aidcManager != null) {
            // close AidcManager to disconnect from the scanner service.
            // once closed, the object can no longer be used.
            this.aidcManager.close();
        }
    }

    /**
     * Executes the request.
     * <p>
     * This method is called from the WebView thread. To do a non-trivial amount
     * of work, use: cordova.getThreadPool().execute(runnable);
     * <p>
     * To run on the UI thread, use:
     * cordova.getActivity().runOnUiThread(runnable);
     *
     * @param action          The action to execute.
     * @param args            The exec() arguments.
     * @param callbackContext The callback context used when calling back into JavaScript.
     * @return Whether the action was valid.
     */
    @Override
    public boolean execute(String action, final JSONArray args, final CallbackContext callbackContext)
            throws JSONException {

        CordovaPluginLog.i(LOG_TAG, "call for action: " + action + "; args: " + args);
        CordovaAction cordovaAction = null;

        // CordovaAction cordovaAction = null;

        if ("onBarcodeEvent".equals(action)) {
            this.barcodeListener.setBarcodeCallback(callbackContext);
            return true;
        } else if ("onFailureEvent".equals(action)) {
            this.barcodeListener.setFailureCallback(callbackContext);
            return true;
        } else if ("onBarcodeDeviceConnectionEvent".equals(action)) {
            this.barcodeDeviceListener.setEventCallback(callbackContext);
            return true;
        } else if (BarcodeReaderPressSoftwareTrigger.ACTION_NAME.equals(action)) {
            cordovaAction = new BarcodeReaderPressSoftwareTrigger(action, args, callbackContext, this.cordova,
                    this.barcodeReaderManager, this.aidcManager, this.barcodeListener);
        } else if (BarcodeReaderGetInfo.ACTION_NAME.equals(action)) {
            cordovaAction = new BarcodeReaderGetInfo(action, args, callbackContext, this.cordova,
                    this.barcodeReaderManager, this.aidcManager, this.barcodeListener);
        } else if (ListBarcodeDevices.ACTION_NAME.equals(action)) {
            cordovaAction = new ListBarcodeDevices(action, args, callbackContext, this.cordova,
                    this.barcodeReaderManager, this.aidcManager, this.barcodeListener);
        } else if (ListConnectedBarcodeDevices.ACTION_NAME.equals(action)) {
            cordovaAction = new ListConnectedBarcodeDevices(action, args, callbackContext, this.cordova,
                    this.barcodeReaderManager, this.aidcManager, this.barcodeListener);
        } else if (CreateBarcodeReader.ACTION_NAME.equals(action)) {
            cordovaAction = new CreateBarcodeReader(action, args, callbackContext, this.cordova,
                this.barcodeReaderManager, this.aidcManager, this.barcodeListener);
        } else if (CloseBarcodeReader.ACTION_NAME.equals(action)) {
            cordovaAction = new CloseBarcodeReader(action, args, callbackContext, this.cordova,
                this.barcodeReaderManager, this.aidcManager, this.barcodeListener);
        } else if (BarcodeReaderGetProfileNames.ACTION_NAME.equals(action)) {
            cordovaAction = new BarcodeReaderGetProfileNames(action, args, callbackContext, this.cordova,
                    this.barcodeReaderManager, this.aidcManager, this.barcodeListener);
        } else if (BarcodeReaderLoadProfile.ACTION_NAME.equals(action)) {
            cordovaAction = new BarcodeReaderLoadProfile(action, args, callbackContext, this.cordova,
                    this.barcodeReaderManager, this.aidcManager, this.barcodeListener);
        } else if (BarcodeReaderSetProperties.ACTION_NAME.equals(action)) {
            cordovaAction = new BarcodeReaderSetProperties(action, args, callbackContext, this.cordova,
                    this.barcodeReaderManager, this.aidcManager, this.barcodeListener);
        } else if (BarcodeReaderGetProperties.ACTION_NAME.equals(action)) {
            cordovaAction = new BarcodeReaderGetProperties(action, args, callbackContext, this.cordova,
                    this.barcodeReaderManager, this.aidcManager, this.barcodeListener);
        } else if (BarcodeReaderGetAllProperties.ACTION_NAME.equals(action)) {
            cordovaAction = new BarcodeReaderGetAllProperties(action, args, callbackContext, this.cordova,
                    this.barcodeReaderManager, this.aidcManager, this.barcodeListener);
        } else if (BarcodeReaderGetAllDefaultProperties.ACTION_NAME.equals(action)) {
            cordovaAction = new BarcodeReaderGetAllDefaultProperties(action, args, callbackContext, this.cordova,
                    this.barcodeReaderManager, this.aidcManager, this.barcodeListener);
        } else if (BarcodeReaderAim.ACTION_NAME.equals(action)) {
            cordovaAction = new BarcodeReaderAim(action, args, callbackContext, this.cordova,
                    this.barcodeReaderManager, this.aidcManager, this.barcodeListener);
        } else if (BarcodeReaderDecode.ACTION_NAME.equals(action)) {
            cordovaAction = new BarcodeReaderDecode(action, args, callbackContext, this.cordova,
                    this.barcodeReaderManager, this.aidcManager, this.barcodeListener);
        } else if (BarcodeReaderLight.ACTION_NAME.equals(action)) {
            cordovaAction = new BarcodeReaderLight(action, args, callbackContext, this.cordova,
                    this.barcodeReaderManager, this.aidcManager, this.barcodeListener);
        }


        if (cordovaAction != null) {
            this.cordova.getThreadPool().execute(cordovaAction);
            return true;
        }

        return super.execute(action, args, callbackContext);
    }
}
