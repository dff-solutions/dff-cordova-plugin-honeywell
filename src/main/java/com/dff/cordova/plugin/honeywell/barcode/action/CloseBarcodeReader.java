package com.dff.cordova.plugin.honeywell.barcode.action;

import com.dff.cordova.plugin.common.log.CordovaPluginLog;
import com.dff.cordova.plugin.honeywell.barcode.BarcodeListener;
import com.dff.cordova.plugin.honeywell.common.BarcodeReaderManager;
import com.honeywell.aidc.AidcManager;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.json.JSONArray;

public class CloseBarcodeReader extends HoneywellAction {

    private static final String TAG = "com.dff.cordova.plugin.honeywell.barcode.action.closeBarcodeReader";
    public static final String ACTION_NAME = "closeBarcodeReader";

    public CloseBarcodeReader(String action, JSONArray args, CallbackContext callbackContext,
                              CordovaInterface cordova, BarcodeReaderManager barcodeReaderManager, AidcManager aidcManager,
                              BarcodeListener barcodeListener) {
        super(action, args, callbackContext, cordova, barcodeReaderManager,  aidcManager, barcodeListener);
    }

    @Override
    public void run() {
        try {
            // check aidc manager (really necessary?)
            if(this.aidcManager != null) {

                // check for already claimed barcode reader
                if(this.barcodeReaderManager.getInstance() != null) {

                    // unregister listener and close the reader completely
                    this.barcodeReaderManager.getInstance().removeBarcodeListener(this.barcodeListener);
                    this.barcodeReaderManager.getInstance().close();
                    this.barcodeReaderManager.setInstance(null);
                    this.callbackContext.success(BARCODE_CLOSED_SUCCESS);
                }
                else
                {
                    // a reader is already connected
                    this.callbackContext.success(NO_BARCODE_READER_CONNECTED);
                }
            }
            else
            {
                // aidc manager is initialized from pluginInitialize method.
                // this error below should never occur.
                callbackContext.error(AICD_NOT_INIT);
            }
        }
        catch (Exception e) {
            CordovaPluginLog.e(TAG, e.getMessage(), e);
            this.callbackContext.error(e.getMessage());
        }
    }
}


