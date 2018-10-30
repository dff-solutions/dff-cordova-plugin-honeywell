package com.dff.cordova.plugin.honeywell.barcode.action;

import com.dff.cordova.plugin.common.log.CordovaPluginLog;
import com.dff.cordova.plugin.honeywell.barcode.BarcodeListener;
import com.dff.cordova.plugin.honeywell.common.BarcodeReaderManager;
import com.dff.cordova.plugin.honeywell.common.GsonNamingStrategy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.honeywell.aidc.AidcManager;
import com.honeywell.aidc.BarcodeReaderInfo;
import com.honeywell.aidc.ScannerUnavailableException;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class BarcodeReaderGetInfo extends HoneywellAction {

    private static final String TAG = "com.dff.cordova.plugin.honeywell.barcode.action.barcodeReaderGetInfo";
    public static final String ACTION_NAME = "barcodeReaderGetInfo";

    public BarcodeReaderGetInfo(String action, JSONArray args, CallbackContext callbackContext,
                                CordovaInterface cordova, BarcodeReaderManager barcodeReaderManager, AidcManager aidcManager,
                                BarcodeListener barcodeListener) {
        super(action, args, callbackContext, cordova, barcodeReaderManager, aidcManager, barcodeListener);
    }

    @Override
    public void run() {
        try {

            if(this.barcodeReaderManager.getInstance() != null) {

                this.callbackContext.success(getBarCodeReaderInfoAsJSOnObject(this.barcodeReaderManager));
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

    public static JSONObject getBarCodeReaderInfoAsJSOnObject(BarcodeReaderManager manager) throws JSONException, ScannerUnavailableException
    {
        // get complete info class and convert to JSON array with GSON
        BarcodeReaderInfo info = manager.getInstance().getInfo();

        // Gson conversion code
        Gson gson = new GsonBuilder().setFieldNamingStrategy(new GsonNamingStrategy()).create();
        String json = gson.toJson(info);
        JSONObject jsonObj = new JSONObject(json);

        return jsonObj;
    }
}