package org.interview.model;

import java.util.List;

public class Include {
   private List<Users> users;

  
  /** 
   * @return List<Users>
   */
  public List<Users> getUser() {
    return users;
  }

  
  /** 
   * @param users
   */
  public void setUser(List<Users> users) {
    this.users = users;
  }

  
  /** 
   * @return String the format chosen is json compatible 
   */
  @Override
  public String toString() {
    return "{\"Users\":" + getUser() + "}";
  }
}
