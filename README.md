# dff-cordova-plugin-honeywell

Cordova plugin for Honeywell CT50 etc. devices.

Please see [Honeywell Mobility SDK for Android](https://www.honeywellaidc.com/products/software/developer-library/mobility-sdk-for-android) for further details. Honeywell SDK for Android can be downloaded at [Android Data Collection SDK](https://hsmftp.honeywell.com/)

## Supported platforms
- Android

## Installation

`cordova plugin add https://github.com/dff-solutions/dff-cordova-plugin-honeywell.git`


## Usage

### onBarcodeEvent

```javascript
/**
 *
 *
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

### onFailureEvent

```javascript
/**
 *
 *
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

### barcodeReaderPressSoftwareTrigger

```javascript
/**
 *
 *
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
