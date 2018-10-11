package com.dff.cordova.plugin.honeywell.barcode.action;

import com.dff.cordova.plugin.common.log.CordovaPluginLog;
import com.honeywell.aidc.BarcodeReader;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.json.JSONArray;
import org.json.JSONObject;

public class BarcodeReaderGetInfo extends HoneywellAction {

    private static final String TAG = "com.dff.cordova.plugin.honeywell.barcode.action.BarcodeReaderGetInfo";
    public static final String ACTION_NAME = "BarcodeReaderGetInfo";

    //public static final String JSON_ARGS_PRESS = "press";
    public static final String[] JSON_ARGS = { };

    public BarcodeReaderGetInfo(String action, JSONArray args, CallbackContext callbackContext,
                                             CordovaInterface cordova, BarcodeReader barcodeReader) {
        super(action, args, callbackContext, cordova, barcodeReader);
    }

    @Override
    public void run() {
        try {
            JSONObject jsonArgs = super.checkJsonArgs(this.args, JSON_ARGS);
            //boolean press = jsonArgs.getBoolean(JSON_ARGS_PRESS);

            /*
            this.barcodeReader.aim(press);
            this.barcodeReader.light(press);
            this.barcodeReader.decode(press);
            */

            this.callbackContext.success();
        }
        catch (Exception e) {
            CordovaPluginLog.e(TAG, e.getMessage(), e);
            this.callbackContext.error(e.getMessage());
        }
    }

}