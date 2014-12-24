
class SoapClassProperty 
{
	String m_Name;
	String m_Type;
	boolean m_isComplexType;
	boolean m_IsArray;
	public SoapClassProperty(String Name)
	{
		this.m_Name = Name;
	}
	public String getPropertyName()
    {
        return m_Name;
    }
    public String getPropertyClassType()
    {
        return m_Type;
    }

    public boolean getIsComplexType()
    {
        return m_isComplexType;
    }

    public boolean getIsArray()
    {
        return m_IsArray;
    }
    public void SetIsArray(boolean isArray)
    {
        m_IsArray = isArray;
    }
	public void SetPropertyClassType(String classType )
	{
		//process class type to get datatype and determine if complex type
		//find out what the xml ns is
		String[] strings = classType.split(":");

		String ns = strings[0];

		if (ns.equals("s"))
		{
			//not a complex type.  convert to java datatypes
			m_Type = convertToJavaType(strings[1]);
			m_isComplexType = false;
		}
		else if(ns.equals("tns"))
		{
			//complex type
			m_Type = strings[1];
			m_isComplexType = true;
		}
		else
		{
			System.out.println(classType);
		}

     
	}
	private String convertToJavaType(String soapType)
	{
	     //check type
	
		//one check for acceptable types
		String[] acceptables = {"boolean","int","float","double","String"};
 
		for (String val: acceptables)
		{
			//if accpetable type then return
			if (soapType.equals(val))
				return soapType;
		}

		//check for string
		if (soapType.equals("string"))
			return "String";

		//check for decimal and convert to float
		if (soapType.equals("decimal"))
			return "float";
    
		return soapType;
	}

}
