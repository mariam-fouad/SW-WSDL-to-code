package my.WsdlToCode;

import java.awt.*;
import java.sql.Struct;
import java.util.ArrayList;


public class Class extends WSDLElement{
	 	public enum ClassType { Parameter, Response, ComplexType, Unknown};
	 	
	    public ClassType Type;
	    public boolean isArray;
	    public String ElementType;
	    public String SuperClassType = "BaseObject";

	    
	    public ArrayList<SoapClassProperty> Properties  ;

	    public Class ()
	    {
	    	
	    }

	    public Class(String Name)
	    {
	        this.Name = Name;
	        this.Properties = new ArrayList<SoapClassProperty>();
	    }
}
