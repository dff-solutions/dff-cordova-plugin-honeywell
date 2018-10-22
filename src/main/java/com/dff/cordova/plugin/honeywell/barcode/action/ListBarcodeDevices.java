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

import java.util.List;

public class ListBarcodeDevices extends HoneywellAction {

    private static final String TAG = "com.dff.cordova.plugin.honeywell.barcode.action.listBarcodeDevices";
    public static final String ACTION_NAME = "listBarcodeDevices";

    public ListBarcodeDevices(String action, JSONArray args, CallbackContext callbackContext,
                              CordovaInterface cordova, BarcodeReaderManager barcodeReaderManager, AidcManager aidcManager,
                              BarcodeListener barcodeListener) {
        super(action, args, callbackContext, cordova, barcodeReaderManager,  aidcManager, barcodeListener);
    }

    @Override
    public void run() {
        try {

            if(this.aidcManager != null) {

                // get complete info class and convert to JSON array with GSON
                List<BarcodeReaderInfo> listOfBarcodeReaders = this.aidcManager.listBarcodeDevices();

                // Gson conversion code
                Gson gson = new GsonBuilder().setFieldNamingStrategy(new GsonNamingStrategy()).create();
                String json = gson.toJson(listOfBarcodeReaders);

                this.callbackContext.success(json);
            }
            else
            {
                // aidc manager is initialized in plugin init method.
                // this error below should never occur.
                callbackContext.error(AICD_NOT_INIT);
            }
        }
        catch (Exception e) {
            CordovaPluginLog.e(TAG, e.getMessage(), e);
            this.callbackContext.error(e.getMessage());
        }
    }
}
