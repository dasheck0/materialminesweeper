package com.dasheck.model.models;

import java.util.List;

/**
 * @author Stefan Neidig
 */
public class ChartValues {

  private List<ValueSet> valueSets;

  public ChartValues() {
  }

  public ChartValues(List<ValueSet> valueSets) {
    this.valueSets = valueSets;
  }

  public List<ValueSet> getValueSets() {
    return valueSets;
  }

  public void setValueSets(List<ValueSet> valueSets) {
    this.valueSets = valueSets;
  }
}
