package com.dff.cordova.plugin.honeywell.barcode.action;

import com.dff.cordova.plugin.honeywell.barcode.BarcodeListener;
import com.google.gson.JsonObject;
import com.honeywell.aidc.AidcManager;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.json.JSONArray;

import com.dff.cordova.plugin.common.action.CordovaAction;
import com.honeywell.aidc.BarcodeReader;
import org.json.JSONException;
import org.json.JSONObject;

public abstract class HoneywellAction extends CordovaAction {
	protected BarcodeReader barcodeReader;
	protected AidcManager aidcManager;
	protected BarcodeListener barcodeListener;

	// all return strings
	public static final String BARCODE_READER_NOT_INIT = "Barcode reader not initialized";
	public static final String BARCODE_CLOSED_SUCCESS = "Barcode reader is closed successfully";
	public static final String NO_BARCODE_READER_CONNECTED = "No barcode reader is connected.";
	public static final String AICD_NOT_INIT = "AidcManager not initialized";
	public static final String BARCODE_READER_ALREADY_ADDED = "A barcode reader is already connected.";
	public static final String NO_CONNECTED_DEVICES = "No connected devices.";
	public static final String LOST_DEVICE_CONNECTION = "Lost connection to connected barcode reader.";

	public JSONObject returnJSONObject(String msg)
	{
		JSONObject json = new JSONObject();

		try
		{
			json.put("message", msg);
		}
		catch (JSONException e)
		{
			e.printStackTrace();
		}
		return json;
	}

	public HoneywellAction(String action, JSONArray args, CallbackContext callbackContext, CordovaInterface cordova,
	        BarcodeReader barcodeReader,  AidcManager aidcManager, BarcodeListener barcodelistener) {
		super(action, args, callbackContext, cordova);

		this.barcodeReader = barcodeReader;
		this.aidcManager = aidcManager;
		this.barcodeListener = barcodelistener;
	}
}
