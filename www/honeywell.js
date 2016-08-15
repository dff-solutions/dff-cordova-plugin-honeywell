/**
 * JavaScript interface to abstract
 * the usage of the cordova honeywell plugin.
 *
 * @module com/dff/cordova/plugin/honeywell
 */

'use strict';

var cordova = require('cordova');
var feature = "HoneywellPlugin";
var self = {};

var actions = ["onLog", "onBarcodeEvent", "onFailureEvent"];

function createActionFunction (action) {
    return function (success, error, args) {
        cordova.exec(success, error, feature, action, [args]);
    }
}

actions.forEach(function (action) {
    self[action] = createActionFunction(action);
});

module.exports = self;
