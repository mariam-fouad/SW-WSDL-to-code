import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;


public class WsdlManager {
	
	private AppConfig appConfig ;
	
	public ArrayList <String> processRequest (int function , String updated )
	{
		
		
		ArrayList <String> Result = new ArrayList <String> ();
		if (function == 1) //use URL to get a list of functionNames
		{
			WsdlParser parser = new WsdlParser();
			
			///rest of function Implementation
			
		}
		else if (function ==2) // open Code To Modify
		{
			FileHelper file = new FileHelper ();
			InputStream IS =  new ByteArrayInputStream((appConfig.getFilePath()).getBytes(StandardCharsets.UTF_8)); ;
			Result.add(file.getContents(IS));
		}
		
		else if (function == 3) // updated the content of the file 
		{
			FileHelper file = new FileHelper ();
			file.WriteClassTextToFile (appConfig.getFilePath() , updated);
		}
		return Result;
	}
	
	public void Generate (ArrayList <Boolean> functionToGenrate)
	{
		ArrayList <String> code = new ArrayList <String>();
		ArrayList <String> filenames = new ArrayList <String> ();
		String filePath = new String ();
		//function  Implementation
		FileHelper file = new FileHelper ();
		for (int i=0;i<code.size();i++)
		{
			file.WriteClassTextToFile ((filePath+filenames.get(i)+".java") , code.get(i));
		}
	}
	public AppConfig getAppConfig() {
		return appConfig;
	}

	public void setAppConfig(AppConfig appConfig) {
		this.appConfig = appConfig;
	}
	
	

}
