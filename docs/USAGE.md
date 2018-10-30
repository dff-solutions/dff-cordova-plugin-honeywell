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
    frameHeight: number;
    frameWidth: number;
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
    value: boolean | number | boolean;
}
```

## BarcodeReaderPropertyMap

```javascript
interface BarcodeReaderPropertyMap {
    [name: string]: boolean | number | string
}
```

## BarcodeDeviceConnectionEvent
```javascript
interface BarcodeDeviceConnectionEvent {
    barcodeDevice: BarcodeReaderInfo
    status: number;
}
```

## BarcodeReadEvent
```javascript
interface BarcodeReadEvent {
    data: string;
    charset: string;
    codeId: string;
    aimId: string;
    imestamp: string;
    bounds: Point[];
}
```

## Point

```javascript
interface Point {
    x: number;
    y: number
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
 * 
 * @return { BarcodeReaderInfo }
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

## barcodeReaderGetInfo
Get info of current barcode reader

```javascript
/**
 * @name barcodeReaderGetInfo
 * @function
 * @param {function} success Callback for success
 * @param {function} error Callback for error
  * 
 * @return { BarcodeReaderInfo } Info of current barcode reader
 */
Honeywell
    .barcodeReaderGetInfo(function (data) {
        console.log(data);
    }, function (reason) {
        console.error(reason);
    });
```

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
 * @return { BarcodeReaderPropertyMap } The map of properties
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
 * @return { BarcodeReaderPropertyMap } The map of properties
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
 * @return { BarcodeReaderPropertyMap } The map of default properties
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
 * @param {boolean} args.enable If aiming is enabled
 */
Honeywell
    .barcodeReaderAim(function () {
        console.log("success");
    }, function (reason) {
        console.error(reason);
    }, {
        enable: true
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
 * @param {boolean} args.enable If decode is enabled
 */
Honeywell
    .barcodeReaderDecode(function () {
        console.log("success");
    }, function (reason) {
        console.error(reason);
    }, {
        enable: true
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
 * @param {boolean} args.enable If light is enabled
 */
Honeywell
    .barcodeReaderLight(function () {
        console.log("success");
    }, function (reason) {
        console.error(reason);
    }, {
        enable: true
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

# Properties

All valid BarcodeReader properties from DataCollection API version 1.9 can be found under
 
```javascript
Honeywell.Properties.[PROPERTY_NAME]
```

This map contains all valid properties and possible values.
Please see [DataCollection javadoc](https://github.com/dff-solutions/dff-cordova-plugin-honeywell/tree/dev/docs/Honeywell_MobilitySDK_Android_v1.00.00.0054/honeywell-android-data-collection-sdk/docs/api)
for more information.

```javascript
{
    "PROPERTY_IMAGER_LIGHT_INTENSITY": "IMG_ILLUM_INTENSITY",
    "PROPERTY_IMAGER_EXPOSURE": "IMG_EXPOSURE",
    "PROPERTY_IMAGER_GAIN": "IMG_GAIN",
    "PROPERTY_IMAGER_MAXIMUM_EXPOSURE": "IMG_MAX_EXPOSURE",
    "PROPERTY_IMAGER_MAXIMUM_GAIN": "IMG_MAX_GAIN",
    "PROPERTY_IMAGER_TARGET_VALUE": "IMG_TARGET_VALUE",
    "PROPERTY_IMAGER_TARGET_ACCEPTABLE_OFFSET": "IMG_TARGET_ACCEPTABLE_OFFSET",
    "PROPERTY_IMAGER_REJECTION_LIMIT": "IMG_REJECTION_LIMIT",
    "PROPERTY_IMAGER_TARGET_PERCENTILE": "IMG_TARGET_PERCENTILE",
    "PROPERTY_IMAGER_EXPOSURE_MODE": "IMG_EXPOSURE_MODE",
    "PROPERTY_IMAGER_SAMPLE_METHOD": "IMG_SAMPLE_METHOD",
    "IMAGER_EXPOSURE_MODE_FIXED": "fixed",
    "IMAGER_EXPOSURE_MODE_AUTO_SENSOR": "autoSensor",
    "IMAGER_EXPOSURE_MODE_AUTO_EXPOSURE": "autoExposure",
    "IMAGER_EXPOSURE_MODE_CONTEXT_SENSITIVE": "contextSensitive",
    "IMAGER_SAMPLE_METHOD_UNIFORM": "uniform",
    "IMAGER_SAMPLE_METHOD_CENTER": "center",
    "IMAGER_SAMPLE_METHOD_CENTER_WEIGHTED": "centerWeighted",
    "PROPERTY_NOTIFICATION_GOOD_READ_ENABLED": "NTF_GOOD_READ_ENABLED",
    "PROPERTY_NOTIFICATION_BAD_READ_ENABLED": "NTF_BAD_READ_ENABLED",
    "PROPERTY_NOTIFICATION_VIBRATE_ENABLED": "NTF_VIBRATE_ENABLED",
    "PROPERTY_TRIGGER_CONTROL_MODE": "TRIG_CONTROL_MODE",
    "PROPERTY_TRIGGER_AUTO_MODE_TIMEOUT": "TRIG_AUTO_MODE_TIMEOUT",
    "TRIGGER_CONTROL_MODE_DISABLE": "disable",
    "TRIGGER_CONTROL_MODE_AUTO_CONTROL": "autoControl",
    "TRIGGER_CONTROL_MODE_CLIENT_CONTROL": "clientControl",
    "PROPERTY_TRIGGER_SCAN_DELAY": "TRIG_SCAN_DELAY",
    "PROPERTY_TRIGGER_SCAN_MODE": "TRIG_SCAN_MODE",
    "TRIGGER_SCAN_MODE_ONESHOT": "oneShot",
    "TRIGGER_SCAN_MODE_CONTINUOUS": "continuous",
    "TRIGGER_SCAN_MODE_READ_ON_RELEASE": "readOnRelease",
    "TRIGGER_SCAN_MODE_READ_ON_SECOND_TRIGGER_PRESS": "readOnSecondTriggerPress",
    "PROPERTY_TRIGGER_SCAN_SAME_SYMBOL_TIMEOUT_ENABLED": "TRIG_SCAN_SAME_SYMBOL_TIMEOUT_ENABLED",
    "PROPERTY_TRIGGER_SCAN_SAME_SYMBOL_TIMEOUT": "TRIG_SCAN_SAME_SYMBOL_TIMEOUT",
    "PROPERTY_CODE_128_ENABLED": "DEC_CODE128_ENABLED",
    "PROPERTY_CODE_128_MINIMUM_LENGTH": "DEC_CODE128_MIN_LENGTH",
    "PROPERTY_CODE_128_MAXIMUM_LENGTH": "DEC_CODE128_MAX_LENGTH",
    "PROPERTY_CODE_128_SHORT_MARGIN": "DEC_C128_SHORT_MARGIN",
    "SHORT_MARGIN_DISABLED": "disabled",
    "SHORT_MARGIN_ENABLED": "partial",
    "SHORT_MARGIN_ENABLE_BOTH_ENDS": "full",
    "PROPERTY_GS1_128_ENABLED": "DEC_GS1_128_ENABLED",
    "PROPERTY_GS1_128_MINIMUM_LENGTH": "DEC_GS1_128_MIN_LENGTH",
    "PROPERTY_GS1_128_MAXIMUM_LENGTH": "DEC_GS1_128_MAX_LENGTH",
    "PROPERTY_ISBT_128_ENABLED": "DEC_C128_ISBT_ENABLED",
    "PROPERTY_CODE_39_ENABLED": "DEC_CODE39_ENABLED",
    "PROPERTY_CODE_39_MINIMUM_LENGTH": "DEC_CODE39_MIN_LENGTH",
    "PROPERTY_CODE_39_MAXIMUM_LENGTH": "DEC_CODE39_MAX_LENGTH",
    "PROPERTY_CODE_39_CHECK_DIGIT_MODE": "DEC_CODE39_CHECK_DIGIT_MODE",
    "PROPERTY_CODE_39_FULL_ASCII_ENABLED": "DEC_CODE39_FULL_ASCII_ENABLED",
    "PROPERTY_CODE_39_START_STOP_TRANSMIT_ENABLED": "DEC_CODE39_START_STOP_TRANSMIT",
    "CODE_39_CHECK_DIGIT_MODE_NO_CHECK": "noCheck",
    "CODE_39_CHECK_DIGIT_MODE_CHECK": "check",
    "CODE_39_CHECK_DIGIT_MODE_CHECK_AND_STRIP": "checkAndStrip",
    "PROPERTY_CODE_39_BASE_32_ENABLED": "DEC_CODE39_BASE32_ENABLED",
    "PROPERTY_DATAMATRIX_ENABLED": "DEC_DATAMATRIX_ENABLED",
    "PROPERTY_DATAMATRIX_MINIMUM_LENGTH": "DEC_DATAMATRIX_MIN_LENGTH",
    "PROPERTY_DATAMATRIX_MAXIMUM_LENGTH": "DEC_DATAMATRIX_MAX_LENGTH",
    "PROPERTY_GRIDMATRIX_ENABLED": "DEC_GRIDMATRIX_ENABLED",
    "PROPERTY_GRIDMATRIX_MINIMUM_LENGTH": "DEC_GRIDMATRIX_MIN_LENGTH",
    "PROPERTY_GRIDMATRIX_MAXIMUM_LENGTH": "DEC_GRIDMATRIX_MAX_LENGTH",
    "PROPERTY_UPC_A_ENABLE": "DEC_UPCA_ENABLE",
    "PROPERTY_UPC_A_TRANSLATE_EAN13": "DEC_UPCA_TRANSLATE_TO_EAN13",
    "PROPERTY_UPC_A_COUPON_CODE_MODE_ENABLED": "DEC_COUPON_CODE_MODE",
    "PROPERTY_UPC_A_COMBINE_COUPON_CODE_MODE_ENABLED": "DEC_COMBINE_COUPON_CODES",
    "PROPERTY_UPC_A_CHECK_DIGIT_TRANSMIT_ENABLED": "DEC_UPCA_CHECK_DIGIT_TRANSMIT",
    "PROPERTY_UPC_A_NUMBER_SYSTEM_TRANSMIT_ENABLED": "DEC_UPCA_NUMBER_SYSTEM_TRANSMIT",
    "PROPERTY_UPC_A_TWO_CHAR_ADDENDA_ENABLED": "DEC_UPCA_2CHAR_ADDENDA_ENABLED",
    "PROPERTY_UPC_A_FIVE_CHAR_ADDENDA_ENABLED": "DEC_UPCA_5CHAR_ADDENDA_ENABLED",
    "PROPERTY_UPC_A_ADDENDA_REQUIRED_ENABLED": "DEC_UPCA_ADDENDA_REQUIRED",
    "PROPERTY_UPC_A_ADDENDA_SEPARATOR_ENABLED": "DEC_UPCA_ADDENDA_SEPARATOR",
    "PROPERTY_UPC_E_ENABLED": "DEC_UPCE0_ENABLED",
    "PROPERTY_UPC_E_E1_ENABLED": "DEC_UPCE1_ENABLED",
    "PROPERTY_UPC_E_EXPAND_TO_UPC_A": "DEC_UPCE_EXPAND",
    "PROPERTY_UPC_E_CHECK_DIGIT_TRANSMIT_ENABLED": "DEC_UPCE_CHECK_DIGIT_TRANSMIT",
    "PROPERTY_UPC_E_NUMBER_SYSTEM_TRANSMIT_ENABLED": "DEC_UPCE_NUMBER_SYSTEM_TRANSMIT",
    "PROPERTY_UPC_E_TWO_CHAR_ADDENDA_ENABLED": "DEC_UPCE_2CHAR_ADDENDA_ENABLED",
    "PROPERTY_UPC_E_FIVE_CHAR_ADDENDA_ENABLED": "DEC_UPCE_5CHAR_ADDENDA_ENABLED",
    "PROPERTY_UPC_E_ADDENDA_REQUIRED_ENABLED": "DEC_UPCE_ADDENDA_REQUIRED",
    "PROPERTY_UPC_E_ADDENDA_SEPARATOR_ENABLED": "DEC_UPCE_ADDENDA_SEPARATOR",
    "PROPERTY_EAN_8_ENABLED": "DEC_EAN8_ENABLED",
    "PROPERTY_EAN_8_CHECK_DIGIT_TRANSMIT_ENABLED": "DEC_EAN8_CHECK_DIGIT_TRANSMIT",
    "PROPERTY_EAN_8_TWO_CHAR_ADDENDA_ENABLED": "DEC_EAN8_2CHAR_ADDENDA_ENABLED",
    "PROPERTY_EAN_8_FIVE_CHAR_ADDENDA_ENABLED": "DEC_EAN8_5CHAR_ADDENDA_ENABLED",
    "PROPERTY_EAN_8_ADDENDA_REQUIRED_ENABLED": "DEC_EAN8_ADDENDA_REQUIRED",
    "PROPERTY_EAN_8_ADDENDA_SEPARATOR_ENABLED": "DEC_EAN8_ADDENDA_SEPARATOR",
    "PROPERTY_EAN_13_ENABLED": "DEC_EAN13_ENABLED",
    "PROPERTY_EAN_13_CHECK_DIGIT_TRANSMIT_ENABLED": "DEC_EAN13_CHECK_DIGIT_TRANSMIT",
    "PROPERTY_EAN_13_TWO_CHAR_ADDENDA_ENABLED": "DEC_EAN13_2CHAR_ADDENDA_ENABLED",
    "PROPERTY_EAN_13_FIVE_CHAR_ADDENDA_ENABLED": "DEC_EAN13_5CHAR_ADDENDA_ENABLED",
    "PROPERTY_EAN_13_ADDENDA_REQUIRED_ENABLED": "DEC_EAN13_ADDENDA_REQUIRED",
    "PROPERTY_EAN_13_ADDENDA_SEPARATOR_ENABLED": "DEC_EAN13_ADDENDA_SEPARATOR",
    "PROPERTY_AZTEC_ENABLED": "DEC_AZTEC_ENABLED",
    "PROPERTY_AZTEC_MINIMUM_LENGTH": "DEC_AZTEC_MIN_LENGTH",
    "PROPERTY_AZTEC_MAXIMUM_LENGTH": "DEC_AZTEC_MAX_LENGTH",
    "PROPERTY_CHINA_POST_ENABLED": "DEC_HK25_ENABLED",
    "PROPERTY_CHINA_POST_MINIMUM_LENGTH": "DEC_HK25_MIN_LENGTH",
    "PROPERTY_CHINA_POST_MAXIMUM_LENGTH": "DEC_HK25_MAX_LENGTH",
    "PROPERTY_CODABAR_ENABLED": "DEC_CODABAR_ENABLED",
    "PROPERTY_CODABAR_MINIMUM_LENGTH": "DEC_CODABAR_MIN_LENGTH",
    "PROPERTY_CODABAR_MAXIMUM_LENGTH": "DEC_CODABAR_MAX_LENGTH",
    "PROPERTY_CODABAR_START_STOP_TRANSMIT_ENABLED": "DEC_CODABAR_START_STOP_TRANSMIT",
    "PROPERTY_CODABAR_CHECK_DIGIT_MODE": "DEC_CODABAR_CHECK_DIGIT_MODE",
    "PROPERTY_CODABAR_CONCAT_ENABLED": "DEC_CODABAR_CONCAT_ENABLED",
    "CODABAR_CHECK_DIGIT_MODE_NO_CHECK": "noCheck",
    "CODABAR_CHECK_DIGIT_MODE_CHECK": "check",
    "CODABAR_CHECK_DIGIT_MODE_CHECK_AND_STRIP": "checkAndStrip",
    "PROPERTY_CODABLOCK_A_ENABLED": "DEC_CODABLOCK_A_ENABLED",
    "PROPERTY_CODABLOCK_A_MINIMUM_LENGTH": "DEC_CODABLOCK_A_MIN_LENGTH",
    "PROPERTY_CODABLOCK_A_MAXIMUM_LENGTH": "DEC_CODABLOCK_A_MAX_LENGTH",
    "PROPERTY_CODABLOCK_F_ENABLED": "DEC_CODABLOCK_F_ENABLED",
    "PROPERTY_CODABLOCK_F_MINIMUM_LENGTH": "DEC_CODABLOCK_F_MIN_LENGTH",
    "PROPERTY_CODABLOCK_F_MAXIMUM_LENGTH": "DEC_CODABLOCK_F_MAX_LENGTH",
    "PROPERTY_CODE_11_ENABLED": "DEC_CODE11_ENABLED",
    "PROPERTY_CODE_11_MINIMUM_LENGTH": "DEC_CODE11_MIN_LENGTH",
    "PROPERTY_CODE_11_MAXIMUM_LENGTH": "DEC_CODE11_MAX_LENGTH",
    "PROPERTY_CODE_11_CHECK_DIGIT_MODE": "DEC_CODE11_CHECK_DIGIT_MODE",
    "CODE_11_CHECK_DIGIT_MODE_DOUBLE_DIGIT_CHECK": "doubleDigitCheck",
    "CODE_11_CHECK_DIGIT_MODE_SINGLE_DIGIT_CHECK": "singleDigitCheck",
    "CODE_11_CHECK_DIGIT_MODE_DOUBLE_DIGIT_CHECK_AND_STRIP": "doubleDigitCheckAndStrip",
    "CODE_11_CHECK_DIGIT_MODE_SINGLE_DIGIT_CHECK_AND_STRIP": "singleDigitCheckAndStrip",
    "PROPERTY_CODE_93_ENABLED": "DEC_CODE93_ENABLED",
    "PROPERTY_CODE_93_MINIMUM_LENGTH": "DEC_CODE93_MIN_LENGTH",
    "PROPERTY_CODE_93_MAXIMUM_LENGTH": "DEC_CODE93_MAX_LENGTH",
    "PROPERTY_DEC_CODE93_HIGH_DENSITY": "DEC_CODE93_HIGH_DENSITY",
    "PROPERTY_COMPOSITE_ENABLED": "DEC_COMPOSITE_ENABLED",
    "PROPERTY_COMPOSITE_MINIMUM_LENGTH": "DEC_COMPOSITE_MIN_LENGTH",
    "PROPERTY_COMPOSITE_MAXIMUM_LENGTH": "DEC_COMPOSITE_MAX_LENGTH",
    "PROPERTY_COMPOSITE_WITH_UPC_ENABLED": "DEC_COMPOSITE_WITH_UPC_ENABLED",
    "PROPERTY_COMBINE_COMPOSITES": "DEC_COMBINE_COMPOSITES",
    "PROPERTY_CODE_DOTCODE_ENABLED": "DEC_DOTCODE_ENABLED",
    "PROPERTY_CODE_DOTCODE_MINIMUM_LENGTH": "DEC_DOTCODE_MIN_LENGTH",
    "PROPERTY_CODE_DOTCODE_MAXIMUM_LENGTH": "DEC_DOTCODE_MAX_LENGTH",
    "PROPERTY_EANUCC_EMULATION_MODE": "DEC_EANUCC_EMULATION_MODE",
    "EANUCC_EMULATION_MODE_GS1_EMULATION_OFF": "gs1EmulationOff",
    "EANUCC_EMULATION_MODE_GS1_128_EMULATION": "gs1128Emulation",
    "EANUCC_EMULATION_MODE_GS1_DATABAR_EMULATION": "gs1DatabarEmulation",
    "EANUCC_EMULATION_MODE_GS1_CODE_EXPANSION_OFF": "gs1CodeExpansionOff",
    "EANUCC_EMULATION_MODE_GS1_EAN8_TO_EAN13_CONVERSION": "gs1EAN8toEAN13Conversion",
    "PROPERTY_HAX_XIN_ENABLED": "DEC_HANXIN_ENABLED",
    "PROPERTY_HAX_XIN_MINIMUM_LENGTH": "DEC_HANXIN_MIN_LENGTH",
    "PROPERTY_HAX_XIN_MAXIMUM_LENGTH": "DEC_HANXIN_MAX_LENGTH",
    "PROPERTY_IATA_25_ENABLED": "DEC_IATA25_ENABLED",
    "PROPERTY_IATA_25_MINIMUM_LENGTH": "DEC_IATA25_MIN_LENGTH",
    "PROPERTY_IATA_25_MAXIMUM_LENGTH": "DEC_IATA25_MAX_LENGTH",
    "PROPERTY_INTERLEAVED_25_ENABLED": "DEC_I25_ENABLED",
    "PROPERTY_INTERLEAVED_25_MINIMUM_LENGTH": "DEC_I25_MIN_LENGTH",
    "PROPERTY_INTERLEAVED_25_MAXIMUM_LENGTH": "DEC_I25_MAX_LENGTH",
    "PROPERTY_INTERLEAVED_25_CHECK_DIGIT_MODE": "DEC_I25_CHECK_DIGIT_MODE",
    "INTERLEAVED_25_CHECK_DIGIT_MODE_NO_CHECK": "noCheck",
    "INTERLEAVED_25_CHECK_DIGIT_MODE_CHECK": "check",
    "INTERLEAVED_25_CHECK_DIGIT_MODE_CHECK_AND_STRIP": "checkAndStrip",
    "PROPERTY_KOREAN_POST_ENABLED": "DEC_KOREA_POST_ENABLED",
    "PROPERTY_KOREAN_POST_MINIMUM_LENGTH": "DEC_KOREA_POST_MIN_LENGTH",
    "PROPERTY_KOREAN_POST_MAXIMUM_LENGTH": "DEC_KOREA_POST_MAX_LENGTH",
    "PROPERTY_MATRIX_25_ENABLED": "DEC_M25_ENABLED",
    "PROPERTY_MATRIX_25_MINIMUM_LENGTH": "DEC_M25_MIN_LENGTH",
    "PROPERTY_MATRIX_25_MAXIMUM_LENGTH": "DEC_M25_MAX_LENGTH",
    "PROPERTY_MAXICODE_ENABLED": "DEC_MAXICODE_ENABLED",
    "PROPERTY_MAXICODE_MINIMUM_LENGTH": "DEC_MAXICODE_MIN_LENGTH",
    "PROPERTY_MAXICODE_MAXIMUM_LENGTH": "DEC_MAXICODE_MAX_LENGTH",
    "PROPERTY_MICRO_PDF_417_ENABLED": "DEC_MICROPDF_ENABLED",
    "PROPERTY_MICRO_PDF_417_MINIMUM_LENGTH": "DEC_MICROPDF_MIN_LENGTH",
    "PROPERTY_MICRO_PDF_417_MAXIMUM_LENGTH": "DEC_MICROPDF_MAX_LENGTH",
    "PROPERTY_MSI_ENABLED": "DEC_MSI_ENABLED",
    "PROPERTY_MSI_MINIMUM_LENGTH": "DEC_MSI_MIN_LENGTH",
    "PROPERTY_MSI_MAXIMUM_LENGTH": "DEC_MSI_MAX_LENGTH",
    "PROPERTY_MSI_CHECK_DIGIT_MODE": "DEC_MSI_CHECK_DIGIT_MODE",
    "PROPERTY_MSI_SHORT_MARGIN": "DEC_MSIP_SHORT_MARGIN",
    "PROPERTY_MSI_OUT_OF_SPEC_SYMBOL": "DEC_PROP_MSIP_OUT_OF_SPEC_SYMBOL",
    "MSI_CHECK_DIGIT_MODE_NO_CHECK": "noCheck",
    "MSI_CHECK_DIGIT_MODE_SINGLE_MOD_10_CHECK": "singleMod10Check",
    "MSI_CHECK_DIGIT_MODE_SINGLE_MOD_11_PLUS_MOD_10_CHECK": "singleMod11PlusMod10Check",
    "MSI_CHECK_DIGIT_MODE_DOUBLE_MOD_10_CHECK": "doubleMod10Check",
    "MSI_CHECK_DIGIT_MODE_SINGLE_MOD_10_CHECK_AND_STRIP": "singleMod10CheckAndStrip",
    "MSI_CHECK_DIGIT_MODE_SINGLE_MOD_11_PLUS_MOD_10_CHECK_AND_STRIP": "singleMod11PlusMod10CheckAndStrip",
    "MSI_CHECK_DIGIT_MODE_DOUBLE_MOD_10_CHECK_AND_STRIP": "doubleMod10CheckAndStrip",
    "PROPERTY_PDF_417_ENABLED": "DEC_PDF417_ENABLED",
    "PROPERTY_PDF_417_MINIMUM_LENGTH": "DEC_PDF417_MIN_LENGTH",
    "PROPERTY_PDF_417_MAXIMUM_LENGTH": "DEC_PDF417_MAX_LENGTH",
    "PROPERTY_QR_CODE_ENABLED": "DEC_QR_ENABLED",
    "PROPERTY_QR_CODE_MINIMUM_LENGTH": "DEC_QR_MIN_LENGTH",
    "PROPERTY_QR_CODE_MAXIMUM_LENGTH": "DEC_QR_MAX_LENGTH",
    "PROPERTY_RSS_ENABLED": "DEC_RSS_14_ENABLED",
    "PROPERTY_RSS_LIMITED_ENABLED": "DEC_RSS_LIMITED_ENABLED",
    "PROPERTY_RSS_EXPANDED_ENABLED": "DEC_RSS_EXPANDED_ENABLED",
    "PROPERTY_RSS_EXPANDED_MINIMUM_LENGTH": "DEC_RSS_EXPANDED_MIN_LENGTH",
    "PROPERTY_RSS_EXPANDED_MAXIMUM_LENGTH": "DEC_RSS_EXPANDED_MAX_LENGTH",
    "PROPERTY_STANDARD_25_ENABLED": "DEC_S25_ENABLED",
    "PROPERTY_STANDARD_25_MINIMUM_LENGTH": "DEC_S25_MIN_LENGTH",
    "PROPERTY_STANDARD_25_MAXIMUM_LENGTH": "DEC_S25_MAX_LENGTH",
    "PROPERTY_TELEPEN_ENABLED": "DEC_TELEPEN_ENABLED",
    "PROPERTY_TELEPEN_MINIMUM_LENGTH": "DEC_TELEPEN_MIN_LENGTH",
    "PROPERTY_TELEPEN_MAXIMUM_LENGTH": "DEC_TELEPEN_MAX_LENGTH",
    "PROPERTY_TELEPEN_OLD_STYLE_ENABLED": "DEC_TELEPEN_OLD_STYLE",
    "PROPERTY_TLC_39_ENABLED": "DEC_TLC39_ENABLED",
    "PROPERTY_TRIOPTIC_ENABLED": "DEC_TRIOPTIC_ENABLED",
    "PROPERTY_POSTAL_2D_MODE": "DEC_POSTAL_ENABLED",
    "PROPERTY_POSTAL_2D_POSTNET_CHECK_DIGIT_TRANSMIT_ENABLED": "DEC_POSTNET_CHECK_DIGIT_TRANSMIT",
    "PROPERTY_POSTAL_2D_PLANET_CHECK_DIGIT_TRANSMIT_ENABLED": "DEC_PLANETCODE_CHECK_DIGIT_TRANSMIT",
    "POSTAL_2D_MODE_NONE": "none",
    "POSTAL_2D_MODE_AUSTRALIA": "australia",
    "POSTAL_2D_MODE_INFOMAIL": "infomail",
    "POSTAL_2D_MODE_JAPAN": "japan",
    "POSTAL_2D_MODE_CANADA": "canada",
    "POSTAL_2D_MODE_DUTCH": "dutch",
    "POSTAL_2D_MODE_PLANET": "planet",
    "POSTAL_2D_MODE_POSTNET": "postnet",
    "POSTAL_2D_MODE_BPO": "bpo",
    "POSTAL_2D_MODE_INFOMAIL_AND_BPO": "infomailAndBpo",
    "POSTAL_2D_MODE_UPU": "upu",
    "POSTAL_2D_MODE_USPS": "usps",
    "POSTAL_2D_MODE_POSTNET_PLUS_BNB": "postnetPlusBnB",
    "POSTAL_2D_MODE_PLANET_AND_POSTNET": "planetAndPostnet",
    "POSTAL_2D_MODE_PLANET_AND_UPU": "planetAndUpu",
    "POSTAL_2D_MODE_POSTNET_AND_UPU": "postnetAndUpu",
    "POSTAL_2D_MODE_PLANET_AND_USPS": "planetAndUsps",
    "POSTAL_2D_MODE_POSTNET_AND_USPS": "postnetAndUsps",
    "POSTAL_2D_MODE_UPU_AND_USPS": "upuAndUsps",
    "POSTAL_2D_MODE_PLANET_AND_POSTNET_PLUS_BNB": "planetAndPostnetPlusBnB",
    "POSTAL_2D_MODE_POSTNET_AND_UPU_PLUS_BNB": "postnetAndUpuPlusBnB",
    "POSTAL_2D_MODE_POSTNET_AND_USPS_PLUS_BNB": "postnetAndUspsPlusBnB",
    "POSTAL_2D_MODE_PLANET_AND_POSTNET_AND_UPU": "planetAndPostnetAndUpu",
    "POSTAL_2D_MODE_PLANET_AND_POSTNET_AND_USPS": "planetAndPostnetAndUsps",
    "POSTAL_2D_MODE_PLANET_AND_UPU_AND_USPS": "planetAndUpuAndUsps",
    "POSTAL_2D_MODE_POSTNET_AND_UPU_AND_USPS": "postnetAndUpuAndUsps",
    "POSTAL_2D_MODE_PLANET_AND_POSTNET_AND_UPU_PLUS_BNB": "planetAndPostnetAndUpuPlusBnB",
    "POSTAL_2D_MODE_PLANET_AND_POSTNET_AND_USPS_PLUS_BNB": "planetAndPostnetAndUspsPlusBnB",
    "POSTAL_2D_MODE_POSTNET_AND_UPU_AND_USPS_PLUS_BNB": "postnetAndUpuAndUspsPlusBnB",
    "POSTAL_2D_MODE_PLANET_AND_POSTNET_AND_UPU_AND_USPS": "planetAndPostnetAndUpuAndUsps",
    "POSTAL_2D_MODE_PLANET_AND_POSTNET_AND_UPU_AND_USPS_PLUS_BNB": "planetAndPostnetAndUpuAndUspsPlusBnB",
    "PROPERTY_DATA_PROCESSOR_CHARSET": "DPR_CHARSET",
    "PROPERTY_DATA_PROCESSOR_PREFIX": "DPR_PREFIX",
    "PROPERTY_DATA_PROCESSOR_SUFFIX": "DPR_SUFFIX",
    "PROPERTY_DATA_PROCESSOR_SYMBOLOGY_PREFIX": "DPR_SYMBOLOGY_PREFIX",
    "PROPERTY_DATA_PROCESSOR_EDIT_DATA_PLUGIN": "DPR_EDIT_DATA_PLUGIN",
    "DATA_PROCESSOR_SYMBOLOGY_ID_NONE": "none",
    "DATA_PROCESSOR_SYMBOLOGY_ID_HONEYWELL": "honeywell",
    "DATA_PROCESSOR_SYMBOLOGY_ID_AIM": "aim",
    "PROPERTY_DATA_PROCESSOR_LAUNCH_BROWSER": "DPR_LAUNCH_BROWSER",
    "PROPERTY_DATA_PROCESSOR_SCAN_TO_INTENT": "DPR_SCAN_TO_INTENT",
    "PROPERTY_DATA_PROCESSOR_LAUNCH_EZ_CONFIG": "DPR_LAUNCH_EZ_CONFIG",
    "PROPERTY_DATA_PROCESSOR_DATA_INTENT": "DPR_DATA_INTENT",
    "PROPERTY_DATA_PROCESSOR_DATA_INTENT_ACTION": "DPR_DATA_INTENT_ACTION",
    "PROPERTY_DATA_PROCESSOR_DATA_INTENT_CATEGORY": "DPR_DATA_INTENT_CATEGORY",
    "PROPERTY_DATA_PROCESSOR_DATA_INTENT_PACKAGE_NAME": "DPR_DATA_INTENT_PACKAGE_NAME",
    "PROPERTY_DATA_PROCESSOR_DATA_INTENT_CLASS_NAME": "DPR_DATA_INTENT_CLASS_NAME",
    "PROPERTY_GROUP_SYMBOLOGY": "SYMBOLOGY_SETTINGS",
    "PROPERTY_GROUP_IMAGER": "IMAGER_SETTINGS",
    "PROPERTY_GROUP_TRIGGER": "TRIGGER_SETTINGS",
    "PROPERTY_GROUP_NOTIFICATION": "NOTIFICATION_SETTINGS",
    "PROPERTY_GROUP_DATA_PROCESSING": "DATA_PROCESSING_SETTINGS",
    "PROPERTY_OCR_MODE": "DEC_OCR_MODE",
    "POSTAL_OCR_MODE_OFF": "off",
    "POSTAL_OCR_MODE_NORMAL": "normalVideo",
    "POSTAL_OCR_MODE_INVERSE": "inverseVideo",
    "POSTAL_OCR_MODE_NORMAL_AND_INVERSE": "normalAndInverseVideo",
    "PROPERTY_OCR_ACTIVE_TEMPLATE": "DEC_OCR_ACTIVE_TEMPLATES",
    "PROPERTY_OCR_TEMPLATE": "DEC_OCR_TEMPLATE",
    "PROPERTY_DEC_ID_PROP_USE_ROI": "DEC_ID_PROP_USE_ROI",
    "DEC_ID_PROP_USE_ROI_DISABLE": "Disable",
    "DEC_ID_PROP_USE_ROI_STANDARD": "Standard",
    "DEC_ID_PROP_USE_ROI_STANDARD_AIMER_CENTERED": "Standard, Aimer centered",
    "DEC_ID_PROP_USE_ROI_DPM_AIMER_CENTERED": "DPM, Aimer centered",
    "DEC_ID_PROP_USE_ROI_KIOSK_OR_PRESENTATION": "Kiosk/Presentation application",
    "PROPERTY_CENTER_DECODE": "DEC_WINDOW_MODE",
    "PROPERTY_DECODE_WINDOW_TOP": "DEC_WINDOW_TOP",
    "PROPERTY_DECODE_WINDOW_BOTTOM": "DEC_WINDOW_BOTTOM",
    "PROPERTY_DECODE_WINDOW_LEFT": "DEC_WINDOW_LEFT",
    "PROPERTY_DECODE_WINDOW_RIGHT": "DEC_WINDOW_RIGHT",
    "PROPERTY_LINEAR_DAMAGE_IMPROVEMENTS": "DEC_LINEAR_DAMAGE_IMPROVEMENTS",
    "PROPERTY_VIDEO_REVERSE_ENABLED": "DEC_VIDEO_REVERSE_ENABLED",
    "VIDEO_REVERSE_ENABLED_NORMAL": "normal",
    "VIDEO_REVERSE_ENABLED_INVERSE": "inverse",
    "VIDEO_REVERSE_ENABLED_BOTH": "both"
}
```
