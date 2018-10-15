package com.dff.cordova.plugin.honeywell.barcode.action;

import com.dff.cordova.plugin.honeywell.barcode.BarcodeListener;
import com.honeywell.aidc.AidcManager;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.json.JSONArray;

import com.dff.cordova.plugin.common.action.CordovaAction;
import com.honeywell.aidc.BarcodeReader;

public abstract class HoneywellAction extends CordovaAction {
	protected BarcodeReader barcodeReader;
	protected AidcManager aidcManager;
	protected BarcodeListener barcodeListener;

	public HoneywellAction(String action, JSONArray args, CallbackContext callbackContext, CordovaInterface cordova,
	        BarcodeReader barcodeReader,  AidcManager aidcManager, BarcodeListener barcodelistener) {
		super(action, args, callbackContext, cordova);

		this.barcodeReader = barcodeReader;
		this.aidcManager = aidcManager;
		this.barcodeListener = barcodelistener;
	}
}
