package com.dff.cordova.plugin.honeywell.barcode.action;

import com.dff.cordova.plugin.common.log.CordovaPluginLog;
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
            // get complete info class and convert to JSON array with GSON
            BarcodeReaderInfo info = this.barcodeReader.getInfo();

            Gson gson = new Gson();
            String json = gson.toJson(info);

            this.callbackContext.success(json);
        }
        catch (Exception e) {
            CordovaPluginLog.e(TAG, e.getMessage(), e);
            this.callbackContext.error(e.getMessage());
        }
    }

}