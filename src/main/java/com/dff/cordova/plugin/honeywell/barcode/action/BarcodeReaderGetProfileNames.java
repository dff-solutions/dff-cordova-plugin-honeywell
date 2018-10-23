package com.dff.cordova.plugin.honeywell.barcode.action;

import com.dff.cordova.plugin.common.log.CordovaPluginLog;
import com.dff.cordova.plugin.honeywell.barcode.BarcodeListener;
import com.dff.cordova.plugin.honeywell.common.BarcodeReaderManager;
import com.dff.cordova.plugin.honeywell.common.GsonNamingStrategy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.honeywell.aidc.AidcManager;
import com.honeywell.aidc.BarcodeReader;
import com.honeywell.aidc.BarcodeReaderInfo;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

public class BarcodeReaderGetProfileNames extends HoneywellAction {

    private static final String TAG = "com.dff.cordova.plugin.honeywell.barcode.action.barcodeReaderGetProfileNames";
    public static final String ACTION_NAME = "barcodeReaderGetProfileNames";

    public BarcodeReaderGetProfileNames(String action, JSONArray args, CallbackContext callbackContext,
                                        CordovaInterface cordova, BarcodeReaderManager barcodeReaderManager, AidcManager aidcManager,
                                        BarcodeListener barcodeListener) {
        super(action, args, callbackContext, cordova, barcodeReaderManager, aidcManager, barcodeListener);
    }

    @Override
    public void run() {
        try {
            if(this.barcodeReaderManager.getInstance() != null) {
                List<String> list = this.barcodeReaderManager.getInstance().getProfileNames();

                Gson gson = new GsonBuilder().setFieldNamingStrategy(new GsonNamingStrategy()).create();
                String json = gson.toJson(list);
                JSONArray jsonArray = new JSONArray(json);

                this.callbackContext.success(jsonArray);
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


