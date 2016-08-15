package com.dff.cordova.plugin.honeywell.json.model;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.graphics.Point;

public class JsonPoint {
	public static JSONObject toJson(Point point) throws JSONException {
		JSONObject jsonPoint = new JSONObject();
		
		if (point != null) {
			jsonPoint.put("x", point.x);
			jsonPoint.put("y", point.y);
		}
		
		return jsonPoint;
	}
	
	public static JSONArray toJson(List<Point> points) throws JSONException {
		JSONArray jsonPoints = new JSONArray();
		
		if (points != null) {
			for (Point p : points) {
				jsonPoints.put(toJson(p));
			}
		}
		
		return jsonPoints;
	}
}
