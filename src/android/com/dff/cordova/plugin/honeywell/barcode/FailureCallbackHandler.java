package com.dff.cordova.plugin.honeywell.barcode;

import org.json.JSONException;

import com.dff.cordova.plugin.common.AbstractPluginListener;
import com.dff.cordova.plugin.common.log.CordovaPluginLog;
import com.dff.cordova.plugin.honeywell.json.model.JsonFailureEvent;
import com.honeywell.aidc.BarcodeFailureEvent;

public class FailureCallbackHandler extends AbstractPluginListener {
	private static String LOG_TAG = "com.dff.cordova.plugin.honeywell.barcode.FailureCallbackHandler";
	
	public void onFailureEvent(BarcodeFailureEvent event) {
		CordovaPluginLog.d(LOG_TAG, "failure event: " + event.getTimestamp());
		
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
