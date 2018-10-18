# Usage

# AidcManager

The plugin initializes the AidcManager which manages the BarcodeReader devices.

The DataCollection API callbacks addBarcodeDeviceListener() and removeBarcodeDeviceListener() are not exposed to the plugin user. Internally the plugin takes care of currently connected barcode devices and proper ressource management. 

## listBarcodeDevices
Returns a list of names of devices which were connected to the device at some point. You can only connect to devices which are listed in listConnectedBarCodeDevices().

```javascript
/**
 * @name listBarcodeDevices
 * @function
 * @param {function} success Callback for success
 * @param {function} error Callback for error
 * @param {Object} args Named arguments
 */
Honeywell
    .listBarcodeDevices(function (data) {
        console.log(data);
    }, function (reason) {
        console.error(reason);
    });
```

## listConnectedBarcodeDevices
Returns a list of names with the currently connected barcode devices. You can use createBarcodeReader() to connect to one. 

```javascript
/**
 * @name listConnectedBarcodeDevices
 * @function
 * @param {function} success Callback for success
 * @param {function} error Callback for error
 * @param {Object} args Named arguments
 */
Honeywell
    .listConnectedBarcodeDevices(function (data) {
        console.log(data);
    }, function (reason) {
        console.error(reason);
    });
```

## createBarcodeReader
Call without name to connect to the internal barcodereader. Call with name to connect to a specific device.

Please note that the name has to be valid and that device has to be listed in the listConnectedBarCodeDevices() call. 

```javascript
/**
 * @name createBarcodeReader
 * @function
 * @param {function} success Callback for success
 * @param {function} error Callback for error
 * @param {Object} args Named arguments
 */
Honeywell
    .createBarcodeReader(function (data) {
        console.log(data);
    }, function (reason) {
        console.error(reason);
    });
```

## closeBarcodeReader
Call to close the connection to the currently connected barcode reader device. Before opening a new device the previous has to be closed by the plugin user.

```javascript
/**
 * @name closeBarcodeReader
 * @function
 * @param {function} success Callback for success
 * @param {function} error Callback for error
 * @param {Object} args Named arguments
 */
Honeywell
    .closeBarcodeReader(function (data) {
        console.log(data);
    }, function (reason) {
        console.error(reason);
    });
```

# BarcodeReader

# createBarcodeReader
# closeBarcodeReader

## getProfileNames
## loadProfile

## getProperties
Get one or more properties with the argument of a JSON array.

For all supported properties see the doumentation ?.

A valid example argument is
```json
[
	{"id": "PROPERTY_AZTEC_MAXIMUM_LENGTH"},
	{"id": "DEC_ID_PROP_USE_ROI_DPM_AIMER_CENTERED"},
	{"id": "TRIGGER_CONTROL_MODE_CLIENT_CONTROL"}
];
```

## setProperties
Set one or more properties of the BarcodeReader with the argument as a JSON array. Please make sure to supply the rigth type of value.

If a property can not be set due to an invalid id or invalid value an exception is thrown.

For all supported properties see the doumentation ?.

For String-Propertys use:
```json
{"id": "PROPERTY_STRING", "value": "VALUE"}```

For Integer-Propertys use:
```json
{"id": "PROPERTY_INTEGER", "value": 0}```

For Boolean-Propertys use:
```json
{"id": "PROPERTY_BOOLEAN", "value": false}```

A valid example JSON is
```json
[
	{"id": "PROPERTY_AZTEC_MAXIMUM_LENGTH", "value": 23},
	{"id": "DEC_ID_PROP_USE_ROI_DPM_AIMER_CENTERED", "value": true},
	{"id": "TRIGGER_CONTROL_MODE_CLIENT_CONTROL", "value": true}
];
```

## getAllProperties
Use to get the values of all current properties.

## getAllDefaultProperties
Use to get the values of all deafault properties.

# aim
# decode
# light

## addBarcodeListener
## addTriggerListener
## removeBarcodeListener
## removeTriggerListener

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
