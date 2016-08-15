package com.dff.cordova.plugin.honeywell.json.model;

import org.json.JSONException;
import org.json.JSONObject;

import com.honeywell.aidc.BarcodeFailureEvent;

public class JsonFailureEvent {
	public static JSONObject toJson(BarcodeFailureEvent event) throws JSONException {
		JSONObject jsonEvent = new JSONObject();
		
		if (event != null) {
			jsonEvent.put("timestamp", event.getTimestamp());
		}
		
		return jsonEvent;
	}
}
