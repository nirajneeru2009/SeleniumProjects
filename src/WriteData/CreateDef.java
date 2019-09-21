package WriteData;

import java.io.PrintWriter;

public class CreateDef {

	public static void createDef(String[] fieldDescription, String[] variable,
			String documentName, int i, PrintWriter outFile) {
		try {

			// for(int i=3;i<=fieldDescription.length-1;i++)
			// {

			int j = i - 1;
			if ("SDC".equals(variable[i])) {
				outFile.println("##" + j + "-prompt");
			}
				else if("DE1:30,DE2:30".equals(variable[i])){
					outFile.println(j+"-4,0,"+"\"{@DE1:30,@DE2:30}\",\"\",\""+fieldDescription[i]);
					
				}
				else if("DE1:10,DE1:32".equals(variable[i])){
					outFile.println(j+"-4,0,"+"\"{@DE1:10,@DE1:32}\",\"\",\""+fieldDescription[i]);
				}
				else if(fieldDescription[i].contains("Date")){
					outFile.println(j + "," + variable[i] + ",0," + "\"\",\"\","
							+ fieldDescription[i]);
					
				} 
			 else
				outFile.println(j + "," + variable[i] + ",0," + "\"\",\"\","
						+ fieldDescription[i]);
			

			// }

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
   
}
