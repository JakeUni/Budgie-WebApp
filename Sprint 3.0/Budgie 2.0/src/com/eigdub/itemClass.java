package com.eigdub;
public class itemClass {
      int itemID;
	  float itemPrice;  
	  int useritemsid;
	  String itemName;  
	  String dateTime;  
	  String type;
	  public itemClass(float itemPrice,String itemName,String dateTime,int itemID,int useritemsid,String type)
	  {  
		  this.itemID = itemID;
		  this.itemPrice=itemPrice;  
		  this.itemName=itemName;  
		  this.dateTime=dateTime;  
		  this.useritemsid =useritemsid;
		  this.type =type;

	  }  
	  public float getItemPrice() {
	        return this.itemPrice;
	    }
	  public int getuseritemsid() {
	        return this.useritemsid;
	    }
	  public String getItemName() {
	        return this.itemName;
	    }
	  public String getType() {
	        return this.type;
	    }
	  public String getItemDate() {
	        return this.dateTime;
	    }
	  public int getItemID() {
	        return this.itemID;
	    }
	  
}
