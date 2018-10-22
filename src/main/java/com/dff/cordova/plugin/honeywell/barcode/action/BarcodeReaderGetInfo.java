package com.dff.cordova.plugin.honeywell.barcode.action;

import com.dff.cordova.plugin.common.log.CordovaPluginLog;
import com.dff.cordova.plugin.honeywell.HoneywellPlugin;
import com.dff.cordova.plugin.honeywell.barcode.BarcodeListener;
import com.dff.cordova.plugin.honeywell.common.GsonNamingStrategy;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.honeywell.aidc.AidcManager;
import com.honeywell.aidc.BarcodeReader;
import com.honeywell.aidc.BarcodeReaderInfo;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.json.JSONArray;
import com.google.gson.Gson;
import org.json.JSONObject;

public class BarcodeReaderGetInfo extends HoneywellAction {

    private static final String TAG = "com.dff.cordova.plugin.honeywell.barcode.action.barcodeReaderGetInfo";
    public static final String ACTION_NAME = "barcodeReaderGetInfo";

    public BarcodeReaderGetInfo(String action, JSONArray args, CallbackContext callbackContext,
                                CordovaInterface cordova, BarcodeReader barcodeReader, AidcManager aidcManager,
                                BarcodeListener barcodeListener) {
        super(action, args, callbackContext, cordova, barcodeReader, aidcManager, barcodeListener);
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
                JSONObject jsonObj = new JSONObject(json);

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

                this.callbackContext.success(jsonObj);
            }
            else {
                this.callbackContext.error(BARCODE_READER_NOT_INIT);
            }
        }
        catch (Exception e) {
            CordovaPluginLog.e(TAG, e.getMessage(), e);
            this.callbackContext.error(e.getMessage());
        }
    }
}