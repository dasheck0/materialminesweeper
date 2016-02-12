package com.dasheck.model.models;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Stefan Neidig
 */
public class ChartValues {

  private Map<String, ValueSet> valueSets;

  public ChartValues() {
    this.valueSets = new HashMap<>();
  }

  public ChartValues(Map<String, ValueSet> valueSets) {
    this.valueSets = valueSets;
  }

  public Map<String, ValueSet> getValueSets() {
    return valueSets;
  }

  public void setValueSets(Map<String, ValueSet> valueSets) {
    this.valueSets = valueSets;
  }
}
