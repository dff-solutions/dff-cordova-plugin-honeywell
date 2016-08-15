package com.dff.cordova.plugin.honeywell.barcode;

import org.json.JSONException;

import com.dff.cordova.plugin.common.AbstractPluginListener;
import com.dff.cordova.plugin.honeywell.json.model.JsonBarcodeEvent;
import com.honeywell.aidc.BarcodeReadEvent;

public class BarcodeCallbackHandler extends AbstractPluginListener {
	
	public void onBarcodeEvent(BarcodeReadEvent event) {
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
