package com.dff.cordova.plugin.honeywell.barcode.action;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.FieldNamingStrategy;

import java.lang.reflect.Field;

class GsonNamingStrategy implements FieldNamingStrategy
{
    public String translateName(Field field)
    {
        String fieldName = FieldNamingPolicy.UPPER_CAMEL_CASE.translateName(field);
        if (fieldName.startsWith("M"))
        {
            // remove prefix letter M
            fieldName = fieldName.substring(1);
        }

        // convert first letter to lower case
        fieldName = fieldName.substring(0,1).toLowerCase() + fieldName.substring(1);

        return fieldName;
    }
}