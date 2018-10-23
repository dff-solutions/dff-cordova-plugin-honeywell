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

Return type is a JSON Array.

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

Return type is a JSON Array.

## createBarcodeReader
Call without name to connect to the default internal barcodereader. Call with name to connect to a specific device. The plugin will claim the device and register the BarcodeListener.

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
	
	
Honeywell
    .createBarcodeReader(function (data) {
        console.log(data);
    }, function (reason) {
        console.error(reason);
    }, {
        // supply no argument for default scanner
    });
```

Return type is a String with success or error message.

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

Return type is a String with success or error message.

# BarcodeReader

## barcodeReaderGetProfileNames
Get a list of all available profile names.

```javascript
/**
 * @name barcodeReaderGetProfileNames
 * @function
 * @param {function} success Callback for success
 * @param {function} error Callback for error
 */
Honeywell
    .barcodeReaderGetProfileNames(function (data) {
        console.log(data);
    }, function (reason) {
        console.error(reason);
    });
```

Return type is a JSON Array.

## barcodeReaderLoadProfile
Load a profile by name. An invalid profile name throws an exception.

```javascript
/**
 * @name barcodeReaderLoadProfile
 * @function
 * @param {function} success Callback for success
 * @param {function} error Callback for error
 * @param {Object} args Named arguments
 * @param {Object} args.name The name of the profile
 */
Honeywell
    .barcodeReaderLoadProfile(function (data) {
        console.log(data);
    }, function (reason) {
        console.error(reason);
    }, {
        name: "profileName"
    });
```

Return type is a String with success or error message.

## barcodeReaderGetProperties
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
 * @name barcodeReaderGetProperties
 * @function
 * @param {function} success Callback for success
 * @param {function} error Callback for error
 * @param {Object} args Named arguments
 * @param {Object} args.properties The properties as JSON array
 */
Honeywell
    .barcodeReaderGetProperties(function (data) {
        console.log(data);
    }, function (reason) {
        console.error(reason);
    }, {
        properties: json_properties
    });
```

Return type is a JSON Object.

## barcodeReaderSetProperties
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
 * @name barcodeReaderSetProperties
 * @function
 * @param {function} success Callback for success
 * @param {function} error Callback for error
 * @param {Object} args Named arguments
 * @param {Object} args.properties The properties as JSON array
 */
Honeywell
    .barcodeReaderSetProperties(function (data) {
        console.log(data);
    }, function (reason) {
        console.error(reason);
    }, {
        properties: json_properties
    });
```

Return type is a String with success or error message.

## barcodeReaderGetAllProperties
Use to get the values of all current properties.

```javascript
/**
 * @name barcodeReaderGetAllProperties
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

Return type is a JSON Object.

## barcodeReaderGetAllDefaultProperties
Use to get the values of all default properties.

```javascript
/**
 * @name barcodeReaderGetAllDefaultProperties
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

Return type is a JSON Object.

## barcodeReaderAim
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

Return type is a String with success or error message.

## barcodeReaderDecode
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

Return type is a String with success or error message.

## barcodeReaderLight
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

Return type is a String with success or error message.

## onBarcodeDeviceConnectionEvent

This event is dispatched by the AidcManager when a USB device change occurs.

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

Return type is a JSON Object of `BarcodeDeviceConnectionEvent` with the identifiers:
* `barcodeDevice - A complete BarcodeReader object, see getBarcodeReaderGetInfo()`
* `status - integer with value of Honeywell.BARCODE_DEVICE_DISCONNECTED or Honeywell.BARCODE_DEVICE_CONNECTED`


## onBarcodeEvent

Called when a bar code label is successfully scanned.

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

Return type is a JSON Object of `BarcodeReadEvent` with the identifiers:
* `data - Bar code data as a String.`
* `charset - Character encoding of the bar code data.`
* `codeId - Unique characters defined by Honeywell to identify symbologies.`
* `aimId - AIM identifier of the bar code.`
* `timestamp - Date/time in ISO 8601 format.`
* `bounds - List of 4 points representing the polygon that approximates the boundary of the bar code.`


## onFailureEvent

Called when a bar code label is not successfully scanned.

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

Return type is a JSON Object of `BarcodeReadEvent` with the identifiers:
* `timestamp - Retrieves the date/time in ISO 8601 format.`

## barcodeReaderPressSoftwareTrigger

Sends a trigger up/down action.

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

Return type is a String with success or error message.

# <a name="properties"></a>BarcodeReader Properties

All valid BarcodeReader properties from DataCollection API version 1.9 can be found under Honeywell.Properties.[PROPERTY_NAME].
