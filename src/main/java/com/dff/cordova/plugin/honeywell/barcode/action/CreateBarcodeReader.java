package com.dff.cordova.plugin.honeywell.barcode.action;

import com.dff.cordova.plugin.common.log.CordovaPluginLog;
import com.dff.cordova.plugin.honeywell.barcode.BarcodeListener;
import com.dff.cordova.plugin.honeywell.common.BarcodeReaderManager;
import com.honeywell.aidc.AidcManager;
import com.honeywell.aidc.ScannerUnavailableException;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.json.JSONArray;
import org.json.JSONObject;

public class CreateBarcodeReader extends HoneywellAction {

    private static final String TAG = "com.dff.cordova.plugin.honeywell.barcode.action.CreateBarcodeReader";
    public static final String ACTION_NAME = "createBarcodeReader";

    public CreateBarcodeReader(String action, JSONArray args, CallbackContext callbackContext,
                               CordovaInterface cordova, BarcodeReaderManager barcodeReaderManager, AidcManager aidcManager,
                               BarcodeListener barcodeListener) {
        super(action, args, callbackContext, cordova, barcodeReaderManager,  aidcManager, barcodeListener);
    }

    public static final String JSON_ARGS_NAME = "name";
    public static final String[] JSON_ARGS = { JSON_ARGS_NAME };

    @Override
    public void run() {
        try {
            // check aidc manager (really necessary?)
            if(this.aidcManager != null) {

                // check for already connected barcode reader
                if(this.barcodeReaderManager.getInstance() == null) {

                    // get optional name parameter
                    String[] emptyArray = {};
                    JSONObject jsonArgs = super.checkJsonArgs(args, emptyArray);
                    String name = jsonArgs.optString(JSON_ARGS_NAME, null);

                    try
                    {
                        // create new barcode reader with no properties set
                        if(name == null) {
                            this.barcodeReaderManager.setInstance(this.aidcManager.createBarcodeReader());
                        }
                        else
                        {
                            this.barcodeReaderManager.setInstance(this.aidcManager.createBarcodeReader(name));
                        }

                        this.barcodeReaderManager.getInstance().claim();
                        this.barcodeReaderManager.getInstance().addBarcodeListener(this.barcodeListener);

                        // return barcode reader info
                        this.callbackContext.success(BarcodeReaderGetInfo.getBarCodeReaderInfoAsJSOnObject(this.barcodeReaderManager));
                    }
                    catch (ScannerUnavailableException e) {
                        this.callbackContext.error(e.getMessage());
                        CordovaPluginLog.e(TAG, e.getMessage(), e);
                    }
                    catch (Exception e)
                    {
                        this.callbackContext.error(e.getMessage());
                        CordovaPluginLog.e(TAG, e.getMessage(), e);
                    }
                }
                else
                {
                    // a reader is already connected
                    this.callbackContext.error(BARCODE_READER_ALREADY_ADDED);
                }
            }
            else
            {
                // aidc manager is initialized from pluginInitialize method.
                // this error below should never occur.
                callbackContext.error(AICD_NOT_INIT);
            }
        }
        catch (Exception e) {
            CordovaPluginLog.e(TAG, e.getMessage(), e);
            this.callbackContext.error(e.getMessage());
        }
    }
}
