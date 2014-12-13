import java.util.ArrayList;


public class WsdlParser {
	
	private ArrayList <Element> elementList;
	
	WsdlParser ()
	{
		elementList = new ArrayList<Element>();
	}
	
	public ArrayList <Element> processWSDL (String URL)
	{
		String WSDL = getWsdl (URL);
		if (verifyWsdl (WSDL))
		{
			parser (WSDL);
		}
		return elementList;
	}
	public void parser (String WSDL)
	{
		//function Implementation
	}
	public String getWsdl (String URL)
	{
		String WSDL = "" ;
	    /////function Implementation
		return WSDL;
	}
	public boolean verifyWsdl (String WSDL)
	{
		boolean verify = true ;
		/////function Implementation
		
		return verify ;
	}
	

}
