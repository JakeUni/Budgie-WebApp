package com.eigdub;
public class itemClass {
      int itemID;
	  int itemPrice;  
	  String itemName;  
	  String dateTime;  
	  itemClass(int itemPrice,String itemName,String dateTime,int itemID)
	  {  
		  this.itemID = itemID;
		  this.itemPrice=itemPrice;  
		  this.itemName=itemName;  
		  this.dateTime=dateTime;  
		  
	  }  
	  public int getItemPrice() {
	        return this.itemPrice;
	    }
	  public String getItemName() {
	        return this.itemName;
	    }
	  public String getItemDate() {
	        return this.dateTime;
	    }
	  public int getItemID() {
	        return this.itemID;
	    }
	  
}
