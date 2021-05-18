package org.twitter.model;

public class Data {
    private String id;
    private String author_id;    
    private String text;
    private String created_at; 

    
    /** 
     * @return String
     */
    public String getMessageId() {
      return id;
    }

    
    /** 
     * @param id
     */
    public void setMessageId(String id) {
      this.id = id;
    }

    
    /** 
     * @return String
     */
    public String getAuthorId() {
      return author_id;
    }

    
    /** 
     * @param author_id
     */
    public void setAuthorId(String author_id) {
      this.author_id = author_id;
    }

    
    /** 
     * @return String
     */
    public String getText() {
      return text;
    }

    
    /** 
     * @param text
     */
    public void settext(String text) {
      this.text = text;
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
      return "{ \"MessageId\":" + "\"" + getMessageId() + "\", \"AuthorId\":" + "\"" + getAuthorId() + "\",  \"Text\":"
          + "\"" + getText() + "\",  \"Created_at\":" + "\"" + getCreated_at() + "\"}";

    }

}

    
