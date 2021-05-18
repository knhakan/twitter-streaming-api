package org.twitter.model;

import java.util.List;

public class Tweet {
 
  private Data data;
  private Include includes;
  private List<Rules> matching_rules;

  
  /** 
   * @return Data
   */
  public Data getData() {
    return data;
  }

  
  /** 
   * @param data
   */
  public void setData(Data data) {
    this.data = data;
  }

  
  /** 
   * @return Include
   */
  public Include getInclude() {
    return includes;
  }

  
  /** 
   * @param includes
   */
  public void setUser(Include includes) {
    this.includes = includes;
  }

  
  /** 
   * @return List<Rules>
   */
  public List<Rules> getRules() {
    return matching_rules;
  }

  
  /** 
   * @param matching_rules
   */
  public void setRules(List<Rules> matching_rules) {
    this.matching_rules = matching_rules;
  }

  
  /** 
   * @return String the format chosen is json compatible 
   */
  @Override
  public String toString() {
    return "{\"Data\":" + getData() + ",\"Includes\":" + getInclude() + ",\"Matching_rules\":" + getRules() + "}";
  }

}
