# Usage

# AidcManager

The plugin initializes the AidcManager which manages the BarcodeReader devices.

The DataCollection API callbacks addBarcodeDeviceListener() and removeBarcodeDeviceListener() are not exposed to the plugin user. Internally the plugin takes care of currently connected barcode devices and proper ressource management. 

## listBarcodeDevices
Returns a list of names of devices which were connected to the device at some point. You can only connect to devices which are listed in listConnectedBarCodeDevices().

## listConnectedBarcodeDevices
Returns a list of names with the currently connected barcode devices. You can use createBarcodeReader() to connect to one. 

## createBarcodeReader
Call without name to connect to the internal barcodereader. Call with name to connect to a specific device.

Please note that the name has to be valid and that device has to be listed in the listConnectedBarCodeDevices() call. 

## closeBarcodeReader
Call to close the connection to the currently connected barcode reader device.

# BarcodeReader

## onBarcodeEvent

```javascript
/**
 * @name onBarcodeEvent
 * @function
 * @param {function} success Callback for success
 * @param {function} error Callback for error
 * @param {Object} args Named arguments
 */
Honeywell
    .onBarcodeEvent(function (data) {
        console.log(data);
    }, function (reason) {
        console.error(reason);
    });
```

## onFailureEvent

```javascript
/**
 * @name onFailureEvent
 * @function
 * @param {function} success Callback for success
 * @param {function} error Callback for error
 * @param {Object} args Named arguments
 */
Honeywell
    .onFailureEvent(function (failure) {
        console.log(failure);
    }, function (reason) {
        console.error(reason);
    });
```

## barcodeReaderPressSoftwareTrigger

```javascript
/**
 * @name barcodeReaderPressSoftwareTrigger
 * @function
 * @param {function} success Callback for success
 * @param {function} error Callback for error
 * @param {Object} args Named arguments
 * @param {Object} args.press If software trigger is pressed or not
 */
Honeywell
    .barcodeReaderPressSoftwareTrigger(function () {
        console.log("pressed");
    }, function (reason) {
        console.error(reason);
    }, {
        press: true
    });
```
