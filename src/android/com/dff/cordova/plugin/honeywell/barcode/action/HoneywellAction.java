package com.dff.cordova.plugin.honeywell.barcode.action;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.json.JSONArray;

import com.dff.cordova.plugin.common.action.CordovaAction;
import com.honeywell.aidc.BarcodeReader;

public class HoneywellAction extends CordovaAction {
	protected BarcodeReader barcodeReader;

	public HoneywellAction(String action, JSONArray args, CallbackContext callbackContext, CordovaInterface cordova,
	        BarcodeReader barcodeReader) {
		super(action, args, callbackContext, cordova);

		this.barcodeReader = barcodeReader;
	}
}
