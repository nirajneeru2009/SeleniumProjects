package WriteData;

import java.io.PrintWriter;

public class CreateGpMod 
{

	 public static void createGpModfile(String documentName,String formName,String phaseName,String applicationName,PrintWriter outFile4)
	    {
		 	System.out.println("gpmod creation started");
	    	outFile4.println("#GPMOD: FORMS");
	    	if(applicationName.equals("Deposit"))
	    	{
	    		outFile4.println("#DEPOSITS#");
	    		outFile4.println("AB,FORMNAME,"+formName+"\",FRM,"+formName+","+documentName+",0,3,1,"+documentName+"-01.uff"+",1,0,OK,,1,,,,,,,,0,0,0,0,,,");
	    	}
	    	else if(applicationName.equals("Lending"))	    
	    	{
	    		if(phaseName.equals("Closing"))
	    		{
	    		outFile4.println("#LOANS#");
	    		outFile4.println("AB,FORMNAME,"+formName+"\",FRM,"+formName+","+documentName+",0,3,1,"+documentName+"-01.uff"+",1,0,OK,,1,,,,,,,,0,0,0,0,,,");
	    		}
	    		else if(phaseName.equals("Application"))	    
	    		{
	    		outFile4.println("#APPS#");
	    		outFile4.println("AB,FORMNAME,"+formName+"\",FRM,"+formName+","+documentName+",0,3,1,"+documentName+"-01.uff"+",1,0,OK,,1,,,,,,,1,0,0,0,0,,,");
	    		}
	    		else if(phaseName.equals("Application and Closing"))
	    		{
	    			outFile4.println("#LOANS#");
		    		outFile4.println("AB,FORMNAME,"+formName+"\",FRM,"+formName+","+documentName+",0,3,1,"+documentName+"-01.uff"+",1,0,OK,,1,,,,,,,,0,0,0,0,,,");
		    		outFile4.println("#APPS#");
		    		outFile4.println("AB,FORMNAME,"+formName+"\",FRM,"+formName+","+documentName+",0,3,1,"+documentName+"-01.uff"+",1,0,OK,,1,,,,,,,1,0,0,0,0,,,");
		    	}
	    	}
	    	
	    }
}
