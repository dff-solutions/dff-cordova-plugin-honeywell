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
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class BarcodeReaderGetProperties extends HoneywellAction {

    private static final String TAG = "com.dff.cordova.plugin.honeywell.barcode.action.barcodeReaderGetProfileNames";
    public static final String ACTION_NAME = "barcodeReaderGetProperties";

    public BarcodeReaderGetProperties(String action, JSONArray args, CallbackContext callbackContext,
                                        CordovaInterface cordova, BarcodeReader barcodeReader, AidcManager aidcManager,
                                        BarcodeListener barcodeListener) {
        super(action, args, callbackContext, cordova, barcodeReader, aidcManager, barcodeListener);
    }

    public static final String JSON_ARGS_NAME = "data";
    public static final String[] JSON_ARGS = { JSON_ARGS_NAME };

    @Override
    public void run() {
        try {
            if(this.barcodeReader != null) {
                JSONObject jsonArgs = super.checkJsonArgs(this.args, JSON_ARGS);
                JSONArray data = jsonArgs.getJSONArray(JSON_ARGS_NAME);

                Set<String> set = setFromJSON(data);
                Map<String, Object> properties = this.barcodeReader.getProperties(set);

                // convert to JSON
                Gson gson = new GsonBuilder().setFieldNamingStrategy(new GsonNamingStrategy()).create();
                String json = gson.toJson(properties);
                JSONObject jsonObj = new JSONObject(json);

                this.callbackContext.success(jsonObj);
            } else {
                this.callbackContext.error(BARCODE_READER_NOT_INIT);
            }
        }
        catch (Exception e) {
            CordovaPluginLog.e(TAG, e.getMessage(), e);
            this.callbackContext.error(e.getMessage());
        }
    }

    public Set<String> setFromJSON(JSONArray jsonArray) throws JSONException {
        Set<String> set = new HashSet<>();

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonEntry = jsonArray.getJSONObject(i);

            String identifier = jsonEntry.getString("id");
            set.add(identifier);
        }

        return set;
    }
}
