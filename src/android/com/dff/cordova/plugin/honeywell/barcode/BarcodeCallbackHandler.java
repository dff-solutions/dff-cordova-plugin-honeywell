package com.dff.cordova.plugin.honeywell.barcode;

import org.json.JSONException;

import com.dff.cordova.plugin.common.AbstractPluginListener;
import com.dff.cordova.plugin.common.log.CordovaPluginLog;
import com.dff.cordova.plugin.honeywell.json.model.JsonBarcodeEvent;
import com.honeywell.aidc.BarcodeReadEvent;

public class BarcodeCallbackHandler extends AbstractPluginListener {
	private static String LOG_TAG = "com.dff.cordova.plugin.honeywell.barcode.BarcodeCallbackHandler";
	
	public void onBarcodeEvent(BarcodeReadEvent event) {
		CordovaPluginLog.d(LOG_TAG, "barcode event: " + event.getBarcodeData());
		
		try {
			this.sendPluginResult(JsonBarcodeEvent.toJson(event));
		}
		catch(JSONException e) {
			this.sendPluginResult(e);
		}
		catch(Exception e) {
			this.sendPluginResult(e);
		}
	}
}
