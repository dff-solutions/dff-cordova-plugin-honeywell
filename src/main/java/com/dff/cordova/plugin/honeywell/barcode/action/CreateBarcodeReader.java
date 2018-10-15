package com.dff.cordova.plugin.honeywell.barcode.action;

import com.dff.cordova.plugin.common.log.CordovaPluginLog;
import com.dff.cordova.plugin.honeywell.HoneywellPlugin;
import com.dff.cordova.plugin.honeywell.barcode.BarcodeListener;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.honeywell.aidc.AidcManager;
import com.honeywell.aidc.BarcodeReader;
import com.honeywell.aidc.BarcodeReaderInfo;
import com.honeywell.aidc.ScannerUnavailableException;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.json.JSONArray;

import java.util.List;

public class CreateBarcodeReader extends HoneywellAction {

    private static final String TAG = "com.dff.cordova.plugin.honeywell.barcode.action.CreateBarcodeReader";
    public static final String ACTION_NAME = "createBarcodeReader";

    public CreateBarcodeReader(String action, JSONArray args, CallbackContext callbackContext,
                                       CordovaInterface cordova, BarcodeReader barcodeReader, AidcManager aidcManager,
                               BarcodeListener barcodeListener) {
        super(action, args, callbackContext, cordova, barcodeReader,  aidcManager, barcodeListener);
    }

    // TODO: optional param for name, check for invalid argument

    @Override
    public void run() {
        try {
            // check aidc manager (really necessary?)
            if(this.aidcManager != null) {

                // check for alredy connected barcode reader
                if(this.barcodeReader == null) {

                    // create new barcode reader with no properties set
                    this.barcodeReader = this.aidcManager.createBarcodeReader();

                    try
                    {
                        this.barcodeReader.claim();
                        this.barcodeReader.addBarcodeListener(this.barcodeListener);
                    }
                    catch (ScannerUnavailableException e) {
                        CordovaPluginLog.e(TAG, e.getMessage(), e);
                    }
                }
                else
                {
                    // a reader is already connected
                    this.callbackContext.success("A barcode reader is already connected.");
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
