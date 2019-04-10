package com.eigdub;
public class incomeClass {
      int incomeID;
	  float amount;  
	  int iduserincome;
	  String incomeName;  
	  String dateTime;  
	  public incomeClass(float amount,String incomeName,String dateTime,int incomeID,int iduserincome)
	  {  
		  this.incomeID = incomeID;
		  this.amount=amount;  
		  this.incomeName=incomeName;  
		  this.dateTime=dateTime;  
		  this.iduserincome =iduserincome;
	  }  
	  public float getAmount() {
	        return this.amount;
	    }
	  public int getIDUI() {
	        return this.iduserincome;
	    }
	  public String getIncomeName() {
	        return this.incomeName;
	    }
	  public String getDate() {
	        return this.dateTime;
	    }
	  public int getIncomeID() {
	        return this.incomeID;
	    }
	  
}
