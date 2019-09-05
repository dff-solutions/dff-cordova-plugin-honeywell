package com.dff.cordova.plugin.honeywell.json.model;

import com.honeywell.aidc.BarcodeReadEvent;
import org.json.JSONException;
import org.json.JSONObject;
import android.util.Base64;

public class JsonBarcodeEvent {
	public static JSONObject toJson(BarcodeReadEvent event) throws JSONException {
		JSONObject jsonEvent = new JSONObject();
		
		if (event != null) {
			jsonEvent.put("aimId", event.getAimId());
			jsonEvent.put("barcodeData", event.getBarcodeData());
			jsonEvent.put("barcodeDataByteArr", Base64.encodeToString(event.getBarcodeData().getBytes(event.getCharset()), Base64.DEFAULT));	
			jsonEvent.put("charset", event.getCharset().toString());
			jsonEvent.put("codeId", event.getCodeId());
			jsonEvent.put("timestamp", event.getTimestamp());
			jsonEvent.put("barcodeBounds", JsonPoint.toJson(event.getBarcodeBounds()));
		}
		
		return jsonEvent;
	}
}
