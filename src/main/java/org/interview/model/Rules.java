package org.interview.model;

public class Rules {
  private String tag;
  private String id;

  
  /** 
   * @return String
   */
  public String getTag() {
    return tag;
  }

  
  /** 
   * @param tag
   */
  public void setTag(String tag) {
    this.tag = tag;
  }

  
  /** 
   * @return String
   */
  public String getId() {
    return id;
  }

  
  /** 
   * @param id
   */
  public void setId(String id) {
    this.id = id;
  }

  
  /** 
   * @return String the format chosen is json compatible 
   */
  @Override
  public String toString() {
    return "{ \"Tag\":" + "\"" + getTag() + "\", \"Id\":" + "\"" + getId() + "\"}";
  }
 
}
