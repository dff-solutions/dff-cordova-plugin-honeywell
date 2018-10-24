package com.dff.cordova.plugin.honeywell.barcode.action;

import com.dff.cordova.plugin.common.log.CordovaPluginLog;
import com.dff.cordova.plugin.honeywell.barcode.BarcodeListener;
import com.dff.cordova.plugin.honeywell.common.BarcodeReaderManager;
import com.honeywell.aidc.AidcManager;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.json.JSONArray;
import org.json.JSONObject;

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

                boolean success = this.barcodeReaderManager.getInstance().loadProfile(name);

                // can not return boolean in callback success() directly
                if(success) {
                    this.callbackContext.success(PROFILE_LOADED);
                } else {
                    this.callbackContext.success(PROFILE_NOT_LOADED);
                }
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