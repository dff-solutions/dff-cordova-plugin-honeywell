/**
 * JavaScript interface to abstract
 * the usage of the cordova honeywell plugin.
 *
 * @module com/dff/cordova/plugin/honeywell
 */

'use strict';

var cordova = require('cordova');
var feature = "Honeywell";
var self = {};

var actions = [
    "onLog",
    "onBarcodeEvent",
    "onFailureEvent",
    "barcodeReaderPressSoftwareTrigger",
    "barcodeReaderGetInfo",
    "listBarcodeDevices",
    "listConnectedBarcodeDevices",
    "createBarcodeReader",
    "closeBarcodeReader",
    "barcodeReaderGetProfileNames",
    "barcodeReaderLoadProfile",
    "barcodeReaderSetProperties",
    "barcodeReaderGetProperties",
    "barcodeReaderGetAllProperties",
    "barcodeReaderGetAllDefaultProperties"
];

function createActionFunction (action) {
    return function (success, error, args) {
        cordova.exec(success, error, feature, action, [args]);
    }
}

actions.forEach(function (action) {
    self[action] = createActionFunction(action);
});

module.exports = self;
