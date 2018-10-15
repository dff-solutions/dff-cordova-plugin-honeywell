package com.dff.cordova.plugin.honeywell;

import com.dff.cordova.plugin.honeywell.barcode.action.*;
import com.honeywell.aidc.*;
import org.apache.cordova.CallbackContext;
import org.json.JSONArray;
import org.json.JSONException;

import com.dff.cordova.plugin.common.CommonPlugin;
import com.dff.cordova.plugin.common.action.CordovaAction;
import com.dff.cordova.plugin.common.log.CordovaPluginLog;
import com.dff.cordova.plugin.honeywell.barcode.BarcodeListener;
import com.honeywell.aidc.AidcManager.CreatedCallback;

public class HoneywellPlugin extends CommonPlugin {

    private static final String LOG_TAG = "com.dff.cordova.plugin.honeywell.HoneywellPlugin";
    private AidcManager aidcManager;
    private BarcodeReader barcodeReader;
    private BarcodeListener barcodeListener;

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

        AidcManager.create(this.cordova.getActivity(), new CreatedCallback() {

            @Override
            public void onCreated(AidcManager aidcManager) {

                CordovaPluginLog.d(LOG_TAG, "AidcManager created");
                HoneywellPlugin.this.aidcManager = aidcManager;

                // listener for new devices
                HoneywellPlugin.this.aidcManager.addBarcodeDeviceListener(new AidcManager.BarcodeDeviceListener() {
                    @Override
                    public void onBarcodeDeviceConnectionEvent(BarcodeDeviceConnectionEvent barcodeDeviceConnectionEvent) {
                        // barcode device was added, nothing to do.
                        // plugin user can use it at some point.
                    }
                });

                // listener for removed devices
                HoneywellPlugin.this.aidcManager.removeBarcodeDeviceListener(new AidcManager.BarcodeDeviceListener() {
                    @Override
                    public void onBarcodeDeviceConnectionEvent(BarcodeDeviceConnectionEvent barcodeDeviceConnectionEvent) {
                        // a barcode devie was removed

                        // check for connected barcode reader
                        if(HoneywellPlugin.this.barcodeReader != null)
                        {
                            try {
                                // get name of currently connected device
                                String name = HoneywellPlugin.this.barcodeReader.getInfo().getName();

                                // check for lost connection
                                if(barcodeDeviceConnectionEvent.getBarcodeReaderInfo().getName().equals(name))
                                {
                                    // remove listener and close barcode reader
                                    CordovaPluginLog.d(LOG_TAG, "lost connection to connected barcode reader.");
                                    HoneywellPlugin.this.barcodeReader.removeBarcodeListener(HoneywellPlugin.this.barcodeListener);
                                    HoneywellPlugin.this.barcodeReader.close();
                                    HoneywellPlugin.this.barcodeReader = null;
                                }
                            }
                            catch (Exception e)
                            {
                                e.printStackTrace();
                            }
                        }
                    }
                });
            }
        });
    }

    private void releaseAndNullBarCodeReader()
    {
        // TODO: implement, may be used at multiple locations
    }

    /**
     * Called when the activity will start interacting with the user.
     *
     * @param multitasking Flag indicating if multitasking is turned on for app
     */
    @Override
    public void onResume(boolean multitasking) {
        super.onResume(multitasking);

        if (this.barcodeReader != null) {
            CordovaPluginLog.d(LOG_TAG, "claim barcode reader");

            try {
                this.barcodeReader.claim();
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
        if (this.barcodeReader != null) {
            CordovaPluginLog.d(LOG_TAG, "release barcode reader");
            // release the scanner claim so we don't get any scanner
            // notifications while paused.
            this.barcodeReader.release();
        }
    }

    /**
     * The final call you receive before your activity is destroyed.
     */
    @Override
    public void onDestroy() {
        super.onDestroy();

        if (this.barcodeReader != null) {
            // unregister barcode event listener
            this.barcodeReader.removeBarcodeListener(this.barcodeListener);

            // close BarcodeReader to clean up resources.
            // once closed, the object can no longer be used.
            this.barcodeReader.close();
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
        } else if (BarcodeReaderPressSoftwareTrigger.ACTION_NAME.equals(action)) {
            cordovaAction = new BarcodeReaderPressSoftwareTrigger(action, args, callbackContext, this.cordova,
                    this.barcodeReader, this.aidcManager, this.barcodeListener);
        } else if (BarcodeReaderGetInfo.ACTION_NAME.equals(action)) {
            cordovaAction = new BarcodeReaderGetInfo(action, args, callbackContext, this.cordova,
                    this.barcodeReader, this.aidcManager, this.barcodeListener);
        } else if (ListBarcodeDevices.ACTION_NAME.equals(action)) {
            cordovaAction = new ListBarcodeDevices(action, args, callbackContext, this.cordova,
                    this.barcodeReader, this.aidcManager, this.barcodeListener);
        } else if (ListConnectedBarcodeDevices.ACTION_NAME.equals(action)) {
            cordovaAction = new ListConnectedBarcodeDevices(action, args, callbackContext, this.cordova,
                    this.barcodeReader, this.aidcManager, this.barcodeListener);
        } else if (CreateBarcodeReader.ACTION_NAME.equals(action)) {
            cordovaAction = new CreateBarcodeReader(action, args, callbackContext, this.cordova,
                this.barcodeReader, this.aidcManager, this.barcodeListener);
        } else if (CloseBarCodeReader.ACTION_NAME.equals(action)) {
            cordovaAction = new CloseBarCodeReader(action, args, callbackContext, this.cordova,
                this.barcodeReader, this.aidcManager, this.barcodeListener);
        }

        if (cordovaAction != null) {
            this.cordova.getThreadPool().execute(cordovaAction);
            return true;
        }

        return super.execute(action, args, callbackContext);
    }
}
