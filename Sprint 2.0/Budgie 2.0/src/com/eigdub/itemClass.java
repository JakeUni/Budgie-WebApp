package com.eigdub;
public class itemClass {
      int itemID;
	  int itemPrice;  
	  int useritemsid;
	  String itemName;  
	  String dateTime;  
	  public itemClass(int itemPrice,String itemName,String dateTime,int itemID,int useritemsid)
	  {  
		  this.itemID = itemID;
		  this.itemPrice=itemPrice;  
		  this.itemName=itemName;  
		  this.dateTime=dateTime;  
		  this.useritemsid =useritemsid;
	  }  
	  public int getItemPrice() {
	        return this.itemPrice;
	    }
	  public int getuseritemsid() {
	        return this.useritemsid;
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
