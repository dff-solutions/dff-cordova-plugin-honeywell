package com.dff.cordova.plugin.honeywell.barcode.action;

import com.dff.cordova.plugin.common.log.CordovaPluginLog;
import com.google.gson.GsonBuilder;
import com.honeywell.aidc.BarcodeReader;
import com.honeywell.aidc.BarcodeReaderInfo;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.json.JSONArray;
import org.json.JSONObject;
import com.google.gson.Gson;

public class BarcodeReaderGetInfo extends HoneywellAction {

    private static final String TAG = "com.dff.cordova.plugin.honeywell.barcode.action.barcodeReaderGetInfo";
    public static final String ACTION_NAME = "barcodeReaderGetInfo";

    public BarcodeReaderGetInfo(String action, JSONArray args, CallbackContext callbackContext,
                                             CordovaInterface cordova, BarcodeReader barcodeReader) {
        super(action, args, callbackContext, cordova, barcodeReader);
    }

    @Override
    public void run() {
        try {

            if(this.barcodeReader != null) {

                // get complete info class and convert to JSON array with GSON
                BarcodeReaderInfo info = this.barcodeReader.getInfo();

                // Gson conversion code
                Gson gson = new GsonBuilder().setFieldNamingStrategy(new GsonNamingStrategy()).create();
                String json = gson.toJson(info);

                /*
                explicit JSON generation with put() method

                JSONObject json = new JSONObject();
                json.put("controlLogicVersion", info.getControlLogicVersion());
                json.put("fastDecodeVersion", info.getFastDecodeVersion());
                json.put("frameHeight", info.getFrameHeight());
                json.put("frameWidth", info.getFrameWidth());
                json.put("friendlyName", info.getFriendlyName());
                json.put("fullDecodeVersion", info.getFullDecodeVersion());
                json.put("name", info.getName());
                json.put("scannerId", info.getScannerId());
                json.put("scannerVersionNumber", info.getScannerVersionNumber());

                */

                this.callbackContext.success(json);
            }
            else {
                callbackContext.error("barcodereader not initialized");
            }
        }
        catch (Exception e) {
            CordovaPluginLog.e(TAG, e.getMessage(), e);
            this.callbackContext.error(e.getMessage());
        }
    }

}