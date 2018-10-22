package com.dff.cordova.plugin.honeywell.common;

import com.honeywell.aidc.BarcodeReader;

// This class manages the instance of the BarcodeReader
public class BarcodeReaderManager {

    private BarcodeReader mBarcodeReader;

    public void setInstance(BarcodeReader barcodeReader)
    {
        mBarcodeReader = barcodeReader;
    }

    public BarcodeReader getInstance()
    {
        return mBarcodeReader;
    }
}
