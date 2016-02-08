package com.dasheck.model.models;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Stefan Neidig
 */
public class ValueSet {

  private List<String> xValues;
  private List<Float> yValues;
  private float minValue;
  private float maxValue;

  public ValueSet() {
    this.xValues = new ArrayList<>();
    this.yValues = new ArrayList<>();
  }

  public ValueSet(List<String> xValues, List<Float> yValues, float minValue, float maxValue) {
    this.xValues = xValues;
    this.yValues = yValues;
    this.minValue = minValue;
    this.maxValue = maxValue;
  }

  public List<String> getXValues() {
    return xValues;
  }

  public void setXValues(List<String> xValues) {
    this.xValues = xValues;
  }

  public List<Float> getYValues() {
    return yValues;
  }

  public void setYValues(List<Float> yValues) {
    this.yValues = yValues;
  }

  public float getMinValue() {
    return minValue;
  }

  public void setMinValue(float minValue) {
    this.minValue = minValue;
  }

  public float getMaxValue() {
    return maxValue;
  }

  public void setMaxValue(float maxValue) {
    this.maxValue = maxValue;
  }
}
