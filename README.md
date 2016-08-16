# dff-cordova-plugin-honeywell
Cordova plugin for Honeywell CT50 etc. devices.

## Supported platforms
- Android

## Installation

    cordova plugin add https://github.com/dff-solutions/dff-cordova-plugin-honeywell.git
    
    
## Usage
### Listen to barcodes

    HoneywellPlugin.onBarcodeEvent(function (data) {
        console.log(data);
    }, function (reason) {
        console.error(reason);
    });
    
    HoneywellPlugin.onFailureEvent(function (failure) {
        console.log(failure);
    }, function (reason) {
        console.error(reason);
    });
    
