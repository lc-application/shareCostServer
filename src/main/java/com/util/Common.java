package com.util;// Created by xuanyuli on 4/27/19.

import org.json.simple.JSONObject;

public class Common {
  static public int getIntFieldFromRequest(JSONObject jsonObject, String fieldName){
    if (jsonObject == null){
      throw new IllegalArgumentException();
    }

    Object obj = jsonObject.get(fieldName);

    if (obj == null) {
      throw new IllegalArgumentException();
    }

    return Integer.parseInt(obj.toString());
  }

  static public String getStringFieldFromRequest(JSONObject jsonObject, String fieldName) {
    if (jsonObject == null) {
      throw new IllegalArgumentException();
    }

    Object obj = jsonObject.get(fieldName);

    if (obj == null) {
      throw new IllegalArgumentException();
    }

    return obj.toString();
  }

  public Double getDoubleFieldFromRequest(JSONObject jsonObject, String fieldName) {
    if (jsonObject == null) {
      throw new IllegalArgumentException();
    }

    Object obj = jsonObject.get(fieldName);

    if (obj == null) {
      throw new IllegalArgumentException();
    }

    return Double.parseDouble(obj.toString());
  }
}
