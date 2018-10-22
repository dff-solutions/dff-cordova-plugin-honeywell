package com.dff.cordova.plugin.honeywell.barcode.action;

import com.dff.cordova.plugin.common.log.CordovaPluginLog;
import com.dff.cordova.plugin.honeywell.barcode.BarcodeListener;
import com.dff.cordova.plugin.honeywell.common.BarcodeReaderManager;
import com.dff.cordova.plugin.honeywell.common.GsonNamingStrategy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.honeywell.aidc.AidcManager;
import com.honeywell.aidc.BarcodeReader;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

public class BarcodeReaderLoadProfile extends HoneywellAction {

    private static final String TAG = "com.dff.cordova.plugin.honeywell.barcode.action.barcodeReaderLoadProfile";
    public static final String ACTION_NAME = "barcodeReaderLoadProfile";

    public BarcodeReaderLoadProfile(String action, JSONArray args, CallbackContext callbackContext,
                                    CordovaInterface cordova, BarcodeReaderManager barcodeReaderManager, AidcManager aidcManager,
                                    BarcodeListener barcodeListener) {
        super(action, args, callbackContext, cordova, barcodeReaderManager, aidcManager, barcodeListener);
    }

    public static final String JSON_ARGS_NAME = "name";
    public static final String[] JSON_ARGS = { JSON_ARGS_NAME };

    @Override
    public void run() {
        try {

            if(this.barcodeReaderManager.getInstance() != null) {
                // get name parameter
                JSONObject jsonArgs = super.checkJsonArgs(this.args, JSON_ARGS);
                String name = jsonArgs.getString(JSON_ARGS_NAME);

                this.barcodeReaderManager.getInstance().loadProfile(name);

                this.callbackContext.success(PROFILE_LOADED);
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