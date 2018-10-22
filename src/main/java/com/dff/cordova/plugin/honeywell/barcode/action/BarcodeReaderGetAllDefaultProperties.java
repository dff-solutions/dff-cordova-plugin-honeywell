package com.dff.cordova.plugin.honeywell.barcode.action;

import com.dff.cordova.plugin.common.log.CordovaPluginLog;
import com.dff.cordova.plugin.honeywell.barcode.BarcodeListener;
import com.dff.cordova.plugin.honeywell.common.GsonNamingStrategy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.honeywell.aidc.AidcManager;
import com.honeywell.aidc.BarcodeReader;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Map;

public class BarcodeReaderGetAllDefaultProperties extends HoneywellAction {

    private static final String TAG = "com.dff.cordova.plugin.honeywell.barcode.action.barcodeReaderGetAllDefaultProperties";
    public static final String ACTION_NAME = "barcodeReaderGetAllDefaultProperties";

    public BarcodeReaderGetAllDefaultProperties(String action, JSONArray args, CallbackContext callbackContext,
                                         CordovaInterface cordova, BarcodeReader barcodeReader, AidcManager aidcManager,
                                         BarcodeListener barcodeListener) {
        super(action, args, callbackContext, cordova, barcodeReader, aidcManager, barcodeListener);
    }

    @Override
    public void run() {
        try {
            if(this.barcodeReader != null) {
                Map<String, Object> mapAllProperties = this.barcodeReader.getAllDefaultProperties();

                Gson gson = new GsonBuilder().setFieldNamingStrategy(new GsonNamingStrategy()).create();
                String json = gson.toJson(mapAllProperties);
                JSONObject jsonObj = new JSONObject(json);

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
