package com.dff.cordova.plugin.honeywell.barcode;

import com.honeywell.aidc.AidcManager;
import com.honeywell.aidc.BarcodeDeviceConnectionEvent;
import org.apache.cordova.CallbackContext;

public class BarcodeDeviceListener implements AidcManager.BarcodeDeviceListener {

    BarcodeDeviceCallbackHandler barcodeDeviceCallbackHandler = new BarcodeDeviceCallbackHandler();

    public void setEventCallback(CallbackContext callbackContext) {
        this.barcodeDeviceCallbackHandler.setCallBack(callbackContext);
    }

    @Override
    public void onBarcodeDeviceConnectionEvent(BarcodeDeviceConnectionEvent event)
    {
        this.barcodeDeviceCallbackHandler.onDeviceConnectionEvent(event);
    }


}


