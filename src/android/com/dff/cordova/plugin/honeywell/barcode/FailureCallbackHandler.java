package com.dff.cordova.plugin.honeywell.barcode;

import org.json.JSONException;

import com.dff.cordova.plugin.common.AbstractPluginListener;
import com.dff.cordova.plugin.honeywell.json.model.JsonFailureEvent;
import com.honeywell.aidc.BarcodeFailureEvent;

public class FailureCallbackHandler extends AbstractPluginListener {
	
	public void onFailureEvent(BarcodeFailureEvent event) {
		try {
			this.sendPluginResult(JsonFailureEvent.toJson(event));
		}
		catch(JSONException e) {
			this.sendPluginResult(e);
		}
		catch(Exception e) {
			this.sendPluginResult(e);
		}
	}
}
