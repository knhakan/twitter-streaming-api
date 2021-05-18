package org.twitter.model;

public class Users {
  private String name;
  private String username;
  private String id;
  private String created_at;

  
  /** 
   * @return String
   */
  public String getName() {
    return name;
  }

  
  /** 
   * @param name
   */
  public void setName(String name) {
    this.name = name;
  }

  
  /** 
   * @return String
   */
  public String getUsername() {
    return username;
  }

  
  /** 
   * @param username
   */
  public void setUsername(String username) {
    this.username = username;
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
   * @return String
   */
  public String getCreated_at() {
    return created_at;
  }

  
  /** 
   * @param created_at
   */
  public void setCreated_at(String created_at) {
    this.created_at = created_at;
  }

  
  /** 
   * @return String the format chosen is json compatible 
   */
  @Override
  public String toString() {
    return "{ \"Name\":" + "\"" + getName() + "\", \"Username\":" + "\"" + getUsername() + "\",  \"Id\":" + "\""
        + getId() + "\",  \"Created_at\":" + "\"" + getCreated_at() + "\"}";

  }

}
