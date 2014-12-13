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
			ArrayList <Element> elmentList = parser.processWSDL(appConfig.getURL());
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
		//function  Implementation
	}
	public AppConfig getAppConfig() {
		return appConfig;
	}

	public void setAppConfig(AppConfig appConfig) {
		this.appConfig = appConfig;
	}
	
	

}
