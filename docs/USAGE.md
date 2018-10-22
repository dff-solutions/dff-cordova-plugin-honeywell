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
    }, {
        name: "scanner_name"
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

## getProfileNames
Get a list of all available profile names.

```javascript
/**
 * @name getProfileNames
 * @function
 * @param {function} success Callback for success
 * @param {function} error Callback for error
 * @param {Object} args Named arguments
 */
Honeywell
    .getProfileNames(function (data) {
        console.log(data);
    }, function (reason) {
        console.error(reason);
    });
```

## loadProfile
Load a profile by name. An invalid profile name throws an exception.

```javascript
/**
 * @name loadProfile
 * @function
 * @param {function} success Callback for success
 * @param {function} error Callback for error
 * @param {Object} args Named arguments
 */
Honeywell
    .loadProfile(function (data) {
        console.log(data);
    }, function (reason) {
        console.error(reason);
    }, {
        name: "profile_name"
    });
```

## getProperties
Get one or more properties with the argument as a JSON array.

A valid example argument is
```json
[
	{"id": Honeywell.Properties.PROPERTY_AZTEC_MAXIMUM_LENGTH},
	{"id": Honeywell.Properties.DEC_ID_PROP_USE_ROI_DPM_AIMER_CENTERED},
	{"id": Honeywell.Properties.TRIGGER_CONTROL_MODE_CLIENT_CONTROL}
];
```

All supported properties are listed [here](#properties).

```javascript
/**
 * @name getProperties
 * @function
 * @param {function} success Callback for success
 * @param {function} error Callback for error
 * @param {Object} args Named arguments
 */
Honeywell
    .getProperties(function (data) {
        console.log(data);
    }, function (reason) {
        console.error(reason);
    }, {
        data: json_properties
    });
```

## setProperties
Set one or more properties of the BarcodeReader with the argument as a JSON array. Please make sure to supply the right type of value. All supported properties are listed [here](#properties). If a property can not be set due to an invalid id or invalid value an exception is thrown.

For String-Properties use:
```json
{"id": "PROPERTY_STRING", "value": "VALUE"}
```

For Integer-Properties use:
```json
{"id": "PROPERTY_INTEGER", "value": 0}
```

For Boolean-Properties use:
```json
{"id": "PROPERTY_BOOLEAN", "value": false}
```

A valid example JSON is:
```json
[
	{"id": Honeywell.Properties.PROPERTY_AZTEC_MAXIMUM_LENGTH, "value": 23}, 
	{"id": Honeywell.Properties.DEC_ID_PROP_USE_ROI_DPM_AIMER_CENTERED, "value": true},
	{"id": Honeywell.Properties.TRIGGER_CONTROL_MODE_CLIENT_CONTROL, "value": true}
];
```

All supported properties are listed [here](#properties).

```javascript
/**
 * @name setProperties
 * @function
 * @param {function} success Callback for success
 * @param {function} error Callback for error
 * @param {Object} args Named arguments
 */
Honeywell
    .setProperties(function (data) {
        console.log(data);
    }, function (reason) {
        console.error(reason);
    }, {
        data: json_properties
    });
```

## getAllProperties
Use to get the values of all current properties.

```javascript
/**
 * @name getAllProperties
 * @function
 * @param {function} success Callback for success
 * @param {function} error Callback for error
 */
Honeywell
    .getAllProperties(function (data) {
        console.log(data);
    }, function (reason) {
        console.error(reason);
    });
```

## getAllDefaultProperties
Use to get the values of all default properties.

```javascript
/**
 * @name getAllDefaultProperties
 * @function
 * @param {function} success Callback for success
 * @param {function} error Callback for error
 */
Honeywell
    .getAllDefaultProperties(function (data) {
        console.log(data);
    }, function (reason) {
        console.error(reason);
    });
```

## aim
Enable or disable aim.

## decode
Enable or disable decode.

## light
Enable or disable light.

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

# <a name="properties"></a>BarcodeReader Properties

A list of all valid properties from DataCollection API version 1.9.
