package my.WsdlToCode;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;


public class WsdlManager {
	
	public  AppConfig appConfig ;
	
	public WsdlManager ()
	{
		
	}
	public WsdlManager (AppConfig appConfig)
	{
		this.appConfig = appConfig ;
	}
	
	public void processRequest (int function , String updated  , String filePath) throws SAXException, IOException, ParserConfigurationException
	{
		
		
		
		if (function == 1) //use URL to parser
		{
			WsdlParser parser = new WsdlParser();
			WsdlParser.processWSDL(appConfig.URL);
		}
		
		else if (function == 2) // updated the content of the file 
		{
			FileHelper.WriteClassTextToFile(filePath, updated);
		}
		
	}
	
	public void Generate () throws Exception
	{
		//folder structure created  continue processing
        // create service file
        Generator.CreateServiceClass(appConfig.PackageName);
        //prepare baseobject
        Generator.CreateBaseObjectFile(appConfig.PackageName);
        //prepare array object
        Generator.CreateLiteralVectorArrayFile(appConfig.PackageName);
        //create classes
        Generator.CreateClasess(appConfig.PackageName);
        //create paramter and return class
        Generator.CreateMethodClasses(appConfig.PackageName);
	}
	public AppConfig getAppConfig() {
		return appConfig;
	}

	public void setAppConfig(AppConfig appConfig) {
		this.appConfig = appConfig;
	}
	
	

}
