package com.dff.cordova.plugin.honeywell.barcode.action;

import com.dff.cordova.plugin.common.log.CordovaPluginLog;
import com.dff.cordova.plugin.honeywell.barcode.BarcodeListener;
import com.dff.cordova.plugin.honeywell.common.BarcodeReaderManager;
import com.honeywell.aidc.AidcManager;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.json.JSONArray;
import org.json.JSONObject;

public class BarcodeReaderLight extends HoneywellAction {
    private static final String TAG = "com.dff.cordova.plugin.honeywell.barcode.action.BarcodeReaderAim";
    public static final String ACTION_NAME = "barcodeReaderLight";

    public static final String JSON_ARGS_ENABLE = "enable";
    public static final String[] JSON_ARGS = { JSON_ARGS_ENABLE };

    public BarcodeReaderLight(String action, JSONArray args, CallbackContext callbackContext,
                               CordovaInterface cordova, BarcodeReaderManager barcodeReaderManager, AidcManager aidcManager,
                               BarcodeListener barcodeListener) {
        super(action, args, callbackContext, cordova, barcodeReaderManager, aidcManager, barcodeListener);
    }

    @Override
    public void run() {
        try {
            if(this.barcodeReaderManager.getInstance() != null) {
                JSONObject jsonArgs = super.checkJsonArgs(this.args, JSON_ARGS);
                boolean enable = jsonArgs.getBoolean(JSON_ARGS_ENABLE);

                this.barcodeReaderManager.getInstance().light(enable);

                this.callbackContext.success();
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



