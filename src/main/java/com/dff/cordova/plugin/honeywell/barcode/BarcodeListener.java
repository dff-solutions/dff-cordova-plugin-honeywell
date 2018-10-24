package com.dff.cordova.plugin.honeywell.barcode;

import com.honeywell.aidc.BarcodeFailureEvent;
import com.honeywell.aidc.BarcodeReadEvent;
import com.honeywell.aidc.BarcodeReader;
import org.apache.cordova.CallbackContext;

public class BarcodeListener implements BarcodeReader.BarcodeListener {
	BarcodeCallbackHandler barcodeCallbackHandler = new BarcodeCallbackHandler();
	FailureCallbackHandler failureCallbackHandler = new FailureCallbackHandler();

	public void setBarcodeCallback(CallbackContext callbackContext) {
		this.barcodeCallbackHandler.setCallBack(callbackContext);
	}
	
	public void setFailureCallback(CallbackContext callbackContext) {
		this.failureCallbackHandler.setCallBack(callbackContext);
	}

	@Override
	public void onBarcodeEvent(BarcodeReadEvent event) {
		this.barcodeCallbackHandler.onBarcodeEvent(event);
	}

	@Override
	public void onFailureEvent(BarcodeFailureEvent event) {
		this.failureCallbackHandler.onFailureEvent(event);
	}

}
