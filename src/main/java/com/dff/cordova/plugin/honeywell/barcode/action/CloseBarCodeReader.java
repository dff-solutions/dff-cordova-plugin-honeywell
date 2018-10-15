package com.dff.cordova.plugin.honeywell.barcode.action;

import com.dff.cordova.plugin.common.log.CordovaPluginLog;
import com.dff.cordova.plugin.honeywell.barcode.BarcodeListener;
import com.honeywell.aidc.AidcManager;
import com.honeywell.aidc.BarcodeReader;
import com.honeywell.aidc.ScannerUnavailableException;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.json.JSONArray;

public class CloseBarcodeReader extends HoneywellAction {

    private static final String TAG = "com.dff.cordova.plugin.honeywell.barcode.action.closeBarcodeReader";
    public static final String ACTION_NAME = "closeBarcodeReader";

    public CloseBarcodeReader(String action, JSONArray args, CallbackContext callbackContext,
                               CordovaInterface cordova, BarcodeReader barcodeReader, AidcManager aidcManager,
                               BarcodeListener barcodeListener) {
        super(action, args, callbackContext, cordova, barcodeReader,  aidcManager, barcodeListener);
    }

    @Override
    public void run() {
        try {
            // check aidc manager (really necessary?)
            if(this.aidcManager != null) {

                // check for already claimed barcode reader
                if(this.barcodeReader != null) {

                    // unregister listernner and close the reader completely
                    this.barcodeReader.removeBarcodeListener(this.barcodeListener);
                    this.barcodeReader.close();
                }
                else
                {
                    // a reader is already connected
                    this.callbackContext.success("No barcode reader is connected.");
                }
            }
            else
            {
                // aidc manager is initialized from pluginInitialize method.
                // this error below should never occur.
                callbackContext.error("aidcManager not initialized");
            }
        }
        catch (Exception e) {
            CordovaPluginLog.e(TAG, e.getMessage(), e);
            this.callbackContext.error(e.getMessage());
        }
    }
}


