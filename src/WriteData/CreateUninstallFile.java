package WriteData;

import java.io.PrintWriter;

public class CreateUninstallFile {
	
	public static void createUninstallFile(String formName,String applicationName, PrintWriter outFile3)
	{
		outFile3.println("#GPMOD:FORMS");
		if(applicationName.equals("Lending"))
		{
			outFile3.println("#LOANS#");
			outFile3.println("D,FORMNAME,"+formName);
		}
		else if (applicationName.equals("Deposit"))
		{
			outFile3.println("#DEPOSITS#");
			outFile3.println("D,FORMNAME,"+formName);
		}
		else if(applicationName.equals("Lending and Deposit"))
		{
			outFile3.println("#LOANS#");
			outFile3.println("D,FORMNAME,"+formName);
			outFile3.println("#DEPOSITS#");
			outFile3.println("D,FORMNAME,"+formName);
		}
		
	}

}
