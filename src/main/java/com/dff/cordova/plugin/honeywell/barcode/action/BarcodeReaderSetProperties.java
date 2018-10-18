package com.dff.cordova.plugin.honeywell.barcode.action;

import com.dff.cordova.plugin.common.log.CordovaPluginLog;
import com.dff.cordova.plugin.honeywell.barcode.BarcodeListener;
import com.dff.cordova.plugin.honeywell.common.GsonNamingStrategy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.honeywell.aidc.AidcManager;
import com.honeywell.aidc.BarcodeReader;
import com.honeywell.aidc.UnsupportedPropertyException;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class BarcodeReaderSetProperties extends HoneywellAction {

    private static final String TAG = "com.dff.cordova.plugin.honeywell.barcode.action.barcodeReaderGetProfileNames";
    public static final String ACTION_NAME = "barcodeReaderSetProperties";

    public static final String JSON_ARGS_DATA = "data";
    public static final String[] JSON_ARGS = { JSON_ARGS_DATA };

    public BarcodeReaderSetProperties(String action, JSONArray args, CallbackContext callbackContext,
                                      CordovaInterface cordova, BarcodeReader barcodeReader, AidcManager aidcManager,
                                      BarcodeListener barcodeListener) {
        super(action, args, callbackContext, cordova, barcodeReader, aidcManager, barcodeListener);
    }

    @Override
    public void run() {
        try {
            if(this.barcodeReader != null) {
                JSONObject jsonArgs = super.checkJsonArgs(this.args, JSON_ARGS);
                JSONArray data = jsonArgs.getJSONArray(JSON_ARGS_DATA);

                setPropertysFromJSONArray(data);
            }
            else {
                this.callbackContext.error(returnJSONObject(BARCODE_READER_NOT_INIT));
            }
        }
        catch (Exception e) {
            CordovaPluginLog.e(TAG, e.getMessage(), e);
            this.callbackContext.error(e.getMessage());
        }
    }

    public void setPropertysFromJSONArray(JSONArray jsonArray) throws JSONException, UnsupportedPropertyException {

        // set all properties, if one throws an exception the rest will not be set (!)
        for (int i = 0; i < jsonArray.length(); i++) {
            setPropertyFromJSONObject(jsonArray.getJSONObject(i));
        }

        this.callbackContext.success(returnJSONObject(PROPERTIES_SET));
    }

    public void setPropertyFromJSONObject(JSONObject json) throws JSONException, UnsupportedPropertyException {
            String identifier = json.getString("id");
            Object value = json.get("value");

            if(value instanceof String) {
                this.barcodeReader.setProperty(identifier, (String) value);
            } else if (value instanceof Integer) {
                this.barcodeReader.setProperty(identifier, (int) value);
            } else if (value instanceof Boolean) {
                this.barcodeReader.setProperty(identifier, (boolean) value);
            } else {
                throw new IllegalArgumentException(String.format("Invalid Type: \"%s\" as value for identifier \"%s\"", value.getClass(), identifier));
            }
        }
}
