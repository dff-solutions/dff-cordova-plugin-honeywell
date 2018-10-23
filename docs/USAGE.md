# Usage

# AidcManager

The plugin initializes the AidcManager which manages the BarcodeReader devices. Internally the plugin takes care of currently connected barcode device and proper ressource management. 

## listBarcodeDevices
Returns a list of names of devices which were connected to the device at some point. You can only connect to devices which are listed in listConnectedBarCodeDevices().

```javascript
/**
 * @name listBarcodeDevices
 * @function
 * @param {function} success Callback for success
 * @param {function} error Callback for error
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
 * @param {Object} args.name The scanner name to create the BarcodeReader object for
 */
Honeywell
    .createBarcodeReader(function (data) {
        console.log(data);
    }, function (reason) {
        console.error(reason);
    }, {
        name: "scannerName"
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
 * @param {Object} args.name The name of the profile
 */
Honeywell
    .loadProfile(function (data) {
        console.log(data);
    }, function (reason) {
        console.error(reason);
    }, {
        name: "profileName"
    });
```

## getProperties
Get one or more properties. Unsupported properties will not be read by DataCollection API and will be missing in the returned JSON.

A valid example argument is
```json
[
	Honeywell.Properties.PROPERTY_AZTEC_MAXIMUM_LENGTH,
	Honeywell.Properties.DEC_ID_PROP_USE_ROI_DPM_AIMER_CENTERED,
	Honeywell.Properties.TRIGGER_CONTROL_MODE_CLIENT_CONTROL
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
 * @param {Object} args.properties The properties as JSON array
 */
Honeywell
    .getProperties(function (data) {
        console.log(data);
    }, function (reason) {
        console.error(reason);
    }, {
        properties: json_properties
    });
```

## setProperties
Set one or more properties. Please make sure to supply the right type of value. All supported properties are listed [here](#properties). If a property can not be set due to an invalid `name` or invalid `value` an exception is thrown.

For String-Properties use:
```json
{"name": "PROPERTY_STRING", "value": "VALUE"}
```

For Integer-Properties use:
```json
{"name": "PROPERTY_INTEGER", "value": 0}
```

For Boolean-Properties use:
```json
{"name": "PROPERTY_BOOLEAN", "value": false}
```

A valid example JSON is:
```json
[
	{"name": Honeywell.Properties.PROPERTY_AZTEC_MAXIMUM_LENGTH, "value": 23}, 
	{"name": Honeywell.Properties.DEC_ID_PROP_USE_ROI_DPM_AIMER_CENTERED, "value": true},
	{"name": Honeywell.Properties.TRIGGER_CONTROL_MODE_CLIENT_CONTROL, "value": true}
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
 * @param {Object} args.properties The properties as JSON array
 */
Honeywell
    .setProperties(function (data) {
        console.log(data);
    }, function (reason) {
        console.error(reason);
    }, {
        properties: json_properties
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

```javascript
/**
 * @name barcodeReaderAim
 * @function
 * @param {function} success Callback for success
 * @param {function} error Callback for error
 * @param {Object} args Named arguments
 * @param {Object} args.enabled If aiming is enabled
 */
Honeywell
    .barcodeReaderAim(function (data) {
        console.log(data);
    }, function (reason) {
        console.error(reason);
    }, {
        enabled: true
    });
```

## decode
Enable or disable decode.

```javascript
/**
 * @name barcodeReaderDecode
 * @function
 * @param {function} success Callback for success
 * @param {function} error Callback for error
 * @param {Object} args Named arguments
 * @param {Object} args.enabled If decode is enabled
 */
Honeywell
    .barcodeReaderDecode(function (data) {
        console.log(data);
    }, function (reason) {
        console.error(reason);
    }, {
        enabled: true
    });
```

## light
Enable or disable light.

```javascript
/**
 * @name barcodeReaderLight
 * @function
 * @param {function} success Callback for success
 * @param {function} error Callback for error
 * @param {Object} args Named arguments
 * @param {Object} args.enabled If light is enabled
 */
Honeywell
    .barcodeReaderLight(function (data) {
        console.log(data);
    }, function (reason) {
        console.error(reason);
    }, {
        enabled: true
    });
```

## onBarcodeDeviceConnectionEvent

```javascript
/**
 * @name onBarcodeDeviceConnectionEvent
 * @function
 * @param {function} success Callback for success
 * @param {function} error Callback for error
 */
Honeywell
    .onBarcodeDeviceConnectionEvent(function (data) {
        console.log(data);
    }, function (reason) {
        console.error(reason);
    });
```

## onBarcodeEvent

```javascript
/**
 * @name onBarcodeEvent
 * @function
 * @param {function} success Callback for success
 * @param {function} error Callback for error
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

All valid BarcodeReader properties from DataCollection API version 1.9 can be found under Honeywell.Properties.[PROPERTY_NAME].
