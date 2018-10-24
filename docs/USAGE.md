# Usage

All important functions from the Honeywell DataCollection API
or the objects AidcManager and BarcodeReader are wrapped and exposed to the Plugin user.
Naming conventions are taken from the [DataCollection API](Honeywell_MobilitySDK_Android_v1.00.00.0054/honeywell-android-data-collection-sdk/docs/api/index.html).

The example code provided as JavaScript snippets for each function is runnable over the
Browser Inspector. `@return` describes the data the success callback is called with.

# Interfaces

## BarcodeReaderInfo

```javascript
interface BarcodeReaderInfo {	
	controlLogicVersion: string;
	fastDecodeVersion: string;
	frameHeight: int;
	frameWidth: int;
	friendlyName: string;
	fullDecodeVersion: string
	name: string;
	scannerId: string;
	scannerVersionNumber: string;
}
```

## BarcodeReaderProperty

```javascript
interface BarcodeReaderProperty {	
	name: string;
	value: boolean | int | boolean;
}
```

## BarcodeReaderProperties

```javascript
interface BarcodeReaderProperties {
    [name: string]: boolean | int | string
}
```

## BarcodeDeviceConnectionEvent
```javascript
interface BarcodeDeviceConnectionEvent {
	barcodeDevice: BarcodeReaderInfo
	status: int;
}
```

## BarcodeReadEvent
```javascript
interface BarcodeDeviceConnectionEvent {
	data: string;
	charset: string;
	codeId: string;
	aimId: string;
	imestamp: string;
	bounds: List<Point>;
}
```

## BarcodeFailureEvent
```javascript
interface BarcodeFailureEvent {
	timestamp: string;
}
```

# AidcManager

## listBarcodeDevices
Returns a list of names of devices which were connected to the device at some point. You can only connect to devices which are listed in listConnectedBarCodeDevices().

```javascript
/**
 * @name listBarcodeDevices
 * @function
 * @param {function} success Callback for success
 * @param {function} error Callback for error
 * 
 * @return { BarcodeReaderInfo[] }
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
 * 
 * @return { BarcodeReaderInfo[] }
 */
Honeywell
    .listConnectedBarcodeDevices(function (data) {
        console.log(data);
    }, function (reason) {
        console.error(reason);
    });
```

## createBarcodeReader
Call without name to connect to the default BarcodeReader. Call with name to connect to a specific device. The plugin will claim the device and register the to the BarcodeListener.

Please note that the name has to be valid and that device has to be listed in the listConnectedBarcodeDevices() call. 

```javascript
/**
 * @name createBarcodeReader
 * @function
 * @param {function} success Callback for success
 * @param {function} error Callback for error
 * @param {Object} args Named arguments
 * @param {String} args.name? The optional scanner name to create the BarcodeReader object for.
 *                            The internal scanner is used as default.
 */
 
// without name argument	
Honeywell
    .createBarcodeReader(function (data) {
        console.log(data);
    }, function (reason) {
        console.error(reason);
    }); 
 
// with name argument
Honeywell
    .createBarcodeReader(function (data) {
        console.log(data);
    }, function (reason) {
        console.error(reason);
    }, {
        name: "dcs.scanner.imager"
    });
```

## closeBarcodeReader
Call to close the connection to the currently connected BarcodeReader device. Before opening a new BarcodeReader the previous has to be closed by the plugin user.

```javascript
/**
 * @name closeBarcodeReader
 * @function
 * @param {function} success Callback for success
 * @param {function} error Callback for error
 * 
 * @return {String} String with success or error message.
 */
Honeywell
    .closeBarcodeReader(function (data) {
        console.log(data);
    }, function (reason) {
        console.error(reason);
    });
```

# BarcodeReader

## barcodeReaderGetProfileNames
Get the names of all the existing profiles.

```javascript
/**
 * @name barcodeReaderGetProfileNames
 * @function
 * @param {function} success Callback for success
 * @param {function} error Callback for error
  * 
 * @return { string[] } a list containing all the profile names
 */
Honeywell
    .barcodeReaderGetProfileNames(function (data) {
        console.log(data);
    }, function (reason) {
        console.error(reason);
    });
```

## barcodeReaderLoadProfile
Set profile properties based on the profile name.

```javascript
/**
 * @name barcodeReaderLoadProfile
 * @function
 * @param {function} success Callback for success
 * @param {function} error Callback for error
 * @param {Object} args Named arguments
 * @param {string} args.name The name of the profile
 * 
 * @return { boolean } True if properties were loaded from the specified profile.
 *                     False if profile does not exist and no property will be loaded
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

## barcodeReaderGetProperties
Get one or more properties. Unsupported properties will not be read by DataCollection API and will be missing in the returned data.

All supported properties are listed [here](#properties).

A valid example argument is

```javascript
var jsonProperties = [
	Honeywell.Properties.PROPERTY_AZTEC_MAXIMUM_LENGTH,
	Honeywell.Properties.TRIGGER_CONTROL_MODE_CLIENT_CONTROL
];

/**
 * @name barcodeReaderGetProperties
 * @function
 * @param {function} success Callback for success
 * @param {function} error Callback for error
 * @param {Object} args Named arguments
 * @param {[ string ]} args.properties
 * 
 * @return { BarcodeReaderProperties } The map of properties
 */
Honeywell
    .barcodeReaderGetProperties(function (data) {
        console.log(data);
    }, function (reason) {
        console.error(reason);
    }, {
        properties: jsonProperties
    });
```

## barcodeReaderSetProperties
Set one or more properties. Please make sure to supply the right type of value. If a property can not be set due to an invalid `name` or invalid `value` an exception is thrown.

All supported properties are listed [here](#properties).

For String-Properties use:
```json
{ "name": "PROPERTY_STRING", "value": "VALUE" }
```

For Integer-Properties use:
```json
{ "name": "PROPERTY_INTEGER", "value": 0 }
```

For Boolean-Properties use:
```json
{ "name": "PROPERTY_BOOLEAN", "value": false }
```

A valid example JSON is:
```javascript
var jsonProperties = [
	{"name": Honeywell.Properties.PROPERTY_AZTEC_MAXIMUM_LENGTH, "value": 23}, 
	{"name": Honeywell.Properties.DEC_ID_PROP_USE_ROI_DPM_AIMER_CENTERED, "value": true},
	{"name": Honeywell.Properties.TRIGGER_CONTROL_MODE_CLIENT_CONTROL, "value": true}
];

/**
 * @name barcodeReaderSetProperties
 * @function
 * @param {function} success Callback for success
 * @param {function} error Callback for error
 * @param {Object} args Named arguments
 * @param { BarcodeReaderProperty[] } args.properties The properties as JSON array
 */
Honeywell
    .barcodeReaderSetProperties(function (data) {
        console.log(data);
    }, function (reason) {
        console.error(reason);
    }, {
        properties: jsonProperties
    });
```

## barcodeReaderGetAllProperties
Use to get all properties (the map of the properties not the values).

```javascript
/**
 * @name barcodeReaderGetAllProperties
 * @function
 * @param {function} success Callback for success
 * @param {function} error Callback for error
 * 
 * @return { BarcodeReaderProperties } The map of properties
 */
Honeywell
    .getAllProperties(function (data) {
        console.log(data);
    }, function (reason) {
        console.error(reason);
    });
```

## barcodeReaderGetAllDefaultProperties
Use to get all default properties.

```javascript
/**
 * @name barcodeReaderGetAllDefaultProperties
 * @function
 * @param {function} success Callback for success
 * @param {function} error Callback for error
 * 
 * @return {[ BarcodeReaderProperties ]} The map of default properties
 */
Honeywell
    .getAllDefaultProperties(function (data) {
        console.log(data);
    }, function (reason) {
        console.error(reason);
    });
```

## barcodeReaderAim
Enable or disable aim.

```javascript
/**
 * @name barcodeReaderAim
 * @function
 * @param {function} success Callback for success
 * @param {function} error Callback for error
 * @param {Object} args Named arguments
 * @param {boolean} args.enabled If aiming is enabled
 */
Honeywell
    .barcodeReaderAim(function () {
        console.log("success");
    }, function (reason) {
        console.error(reason);
    }, {
        enabled: true
    });
```

## barcodeReaderDecode
Enable or disable decode.

```javascript
/**
 * @name barcodeReaderDecode
 * @function
 * @param {function} success Callback for success
 * @param {function} error Callback for error
 * @param {Object} args Named arguments
 * @param {boolean} args.enabled If decode is enabled
 */
Honeywell
    .barcodeReaderDecode(function () {
        console.log("success");
    }, function (reason) {
        console.error(reason);
    }, {
        enabled: true
    });
```

## barcodeReaderLight
Enable or disable light.

```javascript
/**
 * @name barcodeReaderLight
 * @function
 * @param {function} success Callback for success
 * @param {function} error Callback for error
 * @param {Object} args Named arguments
 * @param {boolean} args.enabled If light is enabled
 */
Honeywell
    .barcodeReaderLight(function () {
        console.log("success");
    }, function (reason) {
        console.error(reason);
    }, {
        enabled: true
    });
```

## onBarcodeDeviceConnectionEvent

This event is dispatched by the AidcManager when a USB device change occurs (a device gets disconnected or new device gets connected). Plugin user can register to such events.

```javascript
/**
 * @name onBarcodeDeviceConnectionEvent
 * @function
 * @param {function} success Callback for success
 * @param {function} error Callback for error
 * 
 * @return {BarcodeDeviceConnectionEvent} Dispatched when a new scanner is connected or disconnected
 */
Honeywell
    .onBarcodeDeviceConnectionEvent(function (event) {
        console.log(event);
    }, function (reason) {
        console.error(reason);
    });
```

## onBarcodeEvent

Called when a barcode label is successfully scanned.

```javascript
/**
 * @name onBarcodeEvent
 * @function
 * @param {function} success Callback for success
 * @param {function} error Callback for error
 * 
 * @return {BarcodeReadEvent} This event is dispatched by the BarcodeReader when a bar code label is successfully decoded.
 */
Honeywell
    .onBarcodeEvent(function (data) {
        console.log(data);
    }, function (reason) {
        console.error(reason);
    });
```

## onFailureEvent

Called when a bar code label is not successfully scanned.

```javascript
/**
 * @name onFailureEvent
 * @function
 * @param {function} success Callback for success
 * @param {function} error Callback for error
 * 
 * @return {BarcodeFailureEvent} This event is dispatched by the BarcodeReader when a bar code label is not successfully decoded.
 */
Honeywell
    .onFailureEvent(function (failure) {
        console.log(failure);
    }, function (reason) {
        console.error(reason);
    });
```

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
        console.log("success");
    }, function (reason) {
        console.error(reason);
    }, {
        press: true
    });
```

# <a name="properties"></a>BarcodeReader Properties

All valid BarcodeReader properties from DataCollection API version 1.9 can be found under Honeywell.Properties.[PROPERTY_NAME].
