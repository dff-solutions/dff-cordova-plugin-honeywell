package com.dff.cordova.plugin.honeywell.barcode;

import com.dff.cordova.plugin.common.AbstractPluginListener;
import com.dff.cordova.plugin.common.log.CordovaPluginLog;
import com.dff.cordova.plugin.honeywell.common.GsonNamingStrategy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.honeywell.aidc.BarcodeDeviceConnectionEvent;
import org.json.JSONException;
import org.json.JSONObject;

public class BarcodeDeviceCallbackHandler extends AbstractPluginListener {

    private static String LOG_TAG = "com.dff.cordova.plugin.honeywell.barcode.BarcodeDeviceCallbackHandler";

    public void onDeviceConnectionEvent(BarcodeDeviceConnectionEvent event) {
        CordovaPluginLog.d(LOG_TAG, "device connection event: " + event.getConnectionStatus()
                + " on device " + event.getBarcodeReaderInfo().getName());

        try {

            Gson gson = new GsonBuilder().setFieldNamingStrategy(new GsonNamingStrategy()).create();
            String json = gson.toJson(event);
            JSONObject jsonObj = new JSONObject(json);
            this.sendPluginResult(jsonObj);
        }
        catch(JSONException e) {
            this.sendPluginResult(e);
        }
        catch(Exception e) {
            this.sendPluginResult(e);
        }
    }

}
