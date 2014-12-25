package my.WsdlToCode;

import java.awt.List;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.*;
import org.xml.sax.*;




public class WsdlParser {
	
	private static ArrayList <Element> elementList;
    public static String ServiceName;
    public static String WSDLAddress;
    public static String Namespace;

    public static ArrayList<Method> Methods = new ArrayList<Method>();

    public static ArrayList<Class> Classes = new ArrayList<Class>();
    public static ArrayList<Class> ComplexTypes = new ArrayList<Class>();
    public WsdlParser()
    {
            elementList = new ArrayList<Element>();
    }
    public static String GetSoapPortName()
    {
        return ServiceName + "Soap";
    }

    public static String GetSoap12PortName()
    {
        return ServiceName + "Soap12";
    }
    
    public static String GetHttpPostPortName()
    {
        return ServiceName + "HttpPost";
    }

    ///Get Class with name
    public static Class GetClassWithName(String name)
    {
        //first try the Classes array
        for (Class spC : Classes)
        {
            if (spC.Name.equals(name))
            {
                return spC;
            }
        }

        //then try ComplexTypes
        for (Class spC : ComplexTypes)
        {
            if (spC.Name.equals(name))
            {
                return spC;
            }
        }
        return null;
    }
    public static ArrayList <String> GetAllNames()
    {
        //first try the Classes array
    	ArrayList <String> result = new ArrayList <String> ();
        for (Class spC : Classes)
        {
           result.add(spC.Name);
            
        }

	    for (Class spC : ComplexTypes)
	    {
	        result.add(spC.Name);
	       
	    }
	    return result ;
    }
    public static void reset()
    {
        Classes = new ArrayList<Class>();
        ComplexTypes = new ArrayList<Class>();
    }
    

    public static ArrayList <Element> processWSDL (String URL) throws SAXException, IOException, ParserConfigurationException
    {
    	Methods.clear();
        Document doc = getWsdl (URL);
        WSDLAddress = URL;
        if (verifyWsdl (doc))
        {
                parser (doc);
        }
        return elementList;
    }
    public static boolean parser (Document doc)
    {
            doc.getDocumentElement ().normalize ();
	    Namespace = doc.getDocumentElement().getAttribute("targetNamespace");
	    NodeList nodes = doc.getElementsByTagName("wsdl:service");
	    Node serviceNode = nodes.item(0);
	    if(serviceNode.getNodeType() == Node.ELEMENT_NODE)
	    {
	        Element serviceElement = (Element)serviceNode;
	        //serviceNode
	        ServiceName = ((org.w3c.dom.Element) serviceElement).getAttribute("name");
	        //System.out.println("Service Name: " + ServiceName);
	        //Get Bindings
	        NodeList bindings = doc.getElementsByTagName("wsdl:binding");
	        //get binding and then the Methods
	        for(int s=0; s<bindings.getLength() ; s++)
	        {
	            Node bindingNode = bindings.item(s);
	            if(bindingNode.getNodeType() == Node.ELEMENT_NODE)
	            {
	                Element bindingElement = (Element)bindingNode;
	                if (((org.w3c.dom.Element) bindingElement).getAttribute("name").equals(ServiceName + "Soap"))
	                {
	                    String portTypeNS = ((org.w3c.dom.Element) bindingElement).getAttribute("type").replaceFirst("tns:", "");
	                    //System.out.println("Type : " + portTypeNS);
	                    //get operations
	                    NodeList operations = (bindingElement).getElementsByTagName("wsdl:operation");
	                    //System.out.println("Total no of Operations : " + operations.getLength());
	                    //instatiate the Method array now we know how many Methods there are.
	                    //Loop through Methods
	                    for(int op=0; op<operations.getLength() ; op++)
	                    {
	                        Node operationNode = operations.item(op);
	                        if(bindingNode.getNodeType() == Node.ELEMENT_NODE)
	                        {
	                            //get element
	                            Element operationElement = (Element)operationNode;
	                            //create new instance
	                            Method newFunc = new Method();
	                            newFunc.Name = operationElement.getAttribute("name");
	                            //get operation list and element
	                            NodeList soapOpList = operationElement.getElementsByTagName("soap:operation");
	                            Element soapOpElement = (Element)soapOpList.item(0);
	                            newFunc.SoapAction = soapOpElement.getAttribute("soapAction");
	                            //get port types
	                            NodeList portTypes = doc.getElementsByTagName("wsdl:portType");
	                            //loop through elements
	                            for(int pt=0; pt<portTypes.getLength() ; pt++)
	                            {
	                                Node portTypeNode = portTypes.item(pt);
	                                if(portTypeNode.getNodeType() == Node.ELEMENT_NODE)
	                                {
	                                    Element portTypeElement = (Element)portTypeNode;
	                                    //check to see if it is the same as the current operation
	                                    if (portTypeElement.getAttribute("name").equals(portTypeNS))
	                                    {
	                                        //get the operations for this portType
	                                        NodeList operationList = portTypeElement.getElementsByTagName("wsdl:operation");
	                                        //loop through operation list
	                                        for(int ol=0; ol<operationList.getLength() ; ol++)
	                                        {
	                                            Node operationListNode = operationList.item(ol);
	                                            if(operationListNode.getNodeType() == Node.ELEMENT_NODE)
	                                            {
	                                                Element operationListElement = (Element)operationListNode;
	                                                //see if the element matches the Method anem
	                                                if (operationListElement.getAttribute("name").equals(newFunc.Name))
	                                                {
	                                                    //is this Method - get input parameter type
	                                                    NodeList inputList = operationListElement.getElementsByTagName("wsdl:input");
	                                                    String inputMessage = ((Element)inputList.item(0)).getAttribute("message").replaceFirst("tns:", "");
	                                                    //is this Method - get return type
	                                                    NodeList outputList = operationListElement.getElementsByTagName("wsdl:output");
	                                                    String outputMessage = ((Element)outputList.item(0)).getAttribute("message").replaceFirst("tns:", "");
	                                                    //now need to get the Types of the return and property value
	                                                    //get port types
	                                                    NodeList messages = doc.getElementsByTagName("wsdl:message");
	                                                    for(int ml=0; ml<messages.getLength() ; ml++)
	                                                    {
	                                                        Node messageNode = messages.item(ml);
	                                                        if(messageNode.getNodeType() == Node.ELEMENT_NODE)
	                                                        {
	                                                            //convert to element
	                                                            Element messageElement = (Element)messageNode;
	                                                            //compare against inputMessage name
	                                                            if (messageElement.getAttribute("name").equals(inputMessage))
	                                                            {
	                                                                newFunc.InputType.nameOFType = ((Element)messageElement.getElementsByTagName("wsdl:part").item(0)).getAttribute("element").replaceFirst("tns:", "");
	                                                                //System.out.println("In " + newFunc.InputType);
	                                                            }
	                                                            else //compare against inputMessage name
	                                                            if (messageElement.getAttribute("name").equals(outputMessage))
	                                                            {
	                                                                //get response type
	                                                                //get input param type
	                                                                newFunc.OutputType.nameOFType = ((Element)messageElement.getElementsByTagName("wsdl:part").item(0)).getAttribute("element").replaceFirst("tns:", "");
	                                                            }
	                                                        }
	                                                    }
	                                                }
	                                            }
	                                        }
	                                    }
	                                }
	                            }
	                            //add newFunc to Methods array
	                            Methods.add(newFunc);
	                        }
	                    }
	                }
//	                //Soap12
//	                if ((bindingElement).getAttribute("name").equals(ServiceName + "Soap12"))
//	                {
//	                    String portTypeNS = (bindingElement).getAttribute("type").replaceFirst("tns:", "");
//	                    //System.out.println("Type : " + portTypeNS);
//	                    //get operations
//	                    NodeList operations = (bindingElement).getElementsByTagName("wsdl:operation");
//	                    //System.out.println("Total no of bindings : " + operations.getLength());
//	                    //instatiate the Method array now we know how many Methods there are.
//	                    //Loop through Methods
//	                    for(int op=0; op<operations.getLength() ; op++)
//	                    {
//	                        Node operationNode = operations.item(op);
//	                        if(bindingNode.getNodeType() == Node.ELEMENT_NODE)
//	                        {
//	                            //get element
//	                            Element operationElement = (Element)operationNode;
//	                            //create new instance
//	                            Method newFunc = new Method();
//	                            newFunc.Name = operationElement.getAttribute("name");
//	                            //get operation list and element
//	                            NodeList soapOpList = operationElement.getElementsByTagName("soap12:operation");
//	                            Element soapOpElement = (Element)soapOpList.item(0);
//	                            newFunc.SoapAction = ((Element)soapOpElement).getAttribute("soapAction");
//	                            //get port types
//	                            NodeList portTypes = doc.getElementsByTagName("wsdl:portType");
//	                            //loop through elements
//	                            for(int pt=0; pt<portTypes.getLength() ; pt++)
//	                            {
//	                                Node portTypeNode = portTypes.item(pt);
//	                                if(portTypeNode.getNodeType() == Node.ELEMENT_NODE)
//	                                {
//	                                    Element portTypeElement = (Element)portTypeNode;
//	                                    //check to see if it is the same as the current operation
//	                                    if (portTypeElement.getAttribute("name").equals(portTypeNS))
//	                                    {
//	                                        //get the operations for this portType
//	                                        NodeList operationList = portTypeElement.getElementsByTagName("wsdl:operation");
//	                                        //loop through operation list
//	                                        for(int ol=0; ol<operationList.getLength() ; ol++)
//	                                        {
//	                                            Node operationListNode = operationList.item(ol);
//	                                            if(operationListNode.getNodeType() == Node.ELEMENT_NODE)
//	                                            {
//	                                                Element operationListElement = (Element)operationListNode;
//	                                                //see if the element matches the Method anem
//	                                                if (operationListElement.getAttribute("name").equals(newFunc.Name))
//	                                                {
//	                                                    //is this Method - get input parameter type
//	                                                    NodeList inputList = operationListElement.getElementsByTagName("wsdl:input");
//	                                                    String inputMessage = ((Element)inputList.item(0)).getAttribute("message").replaceFirst("tns:", "");
//	                                                    //is this Method - get return type
//	                                                    NodeList outputList = operationListElement.getElementsByTagName("wsdl:output");
//	                                                    String outputMessage = ((Element)outputList.item(0)).getAttribute("message").replaceFirst("tns:", "");
//	                                                    //now need to get the Types of the return and property value
//	                                                    //get port types
//	                                                    NodeList messages = doc.getElementsByTagName("wsdl:message");
//	                                                    for(int ml=0; ml<messages.getLength() ; ml++)
//	                                                    {
//	                                                        Node messageNode = messages.item(ml);
//	                                                        if(messageNode.getNodeType() == Node.ELEMENT_NODE)
//	                                                        {
//	                                                            //convert to element
//	                                                            Element messageElement = (Element)messageNode;
//	                                                            //compare against inputMessage name
//	                                                            if (messageElement.getAttribute("name").equals(inputMessage))
//	                                                            {
//	                                                                newFunc.InputType.nameOFType = ((Element)messageElement.getElementsByTagName("wsdl:part").item(0)).getAttribute("element").replaceFirst("tns:", "");
//	                                                                //System.out.println("In " + newFunc.InputType);
//	                                                            }
//	                                                            else //compare against inputMessage name
//	                                                            if (messageElement.getAttribute("name").equals(outputMessage))
//	                                                            {
//	                                                                //get response type
//	                                                                //get input param type
//	                                                                newFunc.OutputType.nameOFType = ((Element)messageElement.getElementsByTagName("wsdl:part").item(0)).getAttribute("element").replaceFirst("tns:", "");
//	                                                            }
//	                                                        }
//	                                                    }
//	                                                }
//	                                            }
//	                                        }
//	                                    }
//	                                }
//	                            }
//	                            //add newFunc to Methods array
//	                            Methods.add(newFunc);
//	                        }
//	                    }
//	                }
//	                if (((org.w3c.dom.Element) bindingElement).getAttribute("name").equals(ServiceName + "HttpPost"))
//	                {
//	                    String portTypeNS = ((org.w3c.dom.Element) bindingElement).getAttribute("type").replaceFirst("tns:", "");
//	                    //System.out.println("Type : " + portTypeNS);
//	                    //get operations
//	                    NodeList operations = ((Document) bindingElement).getElementsByTagName("wsdl:operation");
//	                    //System.out.println("Total no of Methods : " + operations.getLength());
//	                    //instatiate the Method array now we know how many Methods there are.
//	                    //Loop through Methods
//	                    for(int op=0; op<operations.getLength() ; op++)
//	                    {
//	                        Node operationNode = operations.item(op);
//	                        if(bindingNode.getNodeType() == Node.ELEMENT_NODE)
//	                        {
//	                            //get element
//	                            Element operationElement = (Element)operationNode;
//	                            //create new instance
//	                            Method newFunc = new Method();
//	                            newFunc.Name = operationElement.getAttribute("name");
//	                            //get operation list and element
//	                            NodeList soapOpList = operationElement.getElementsByTagName("http:operation");
//	                            Element soapOpElement = (Element)soapOpList.item(0);
//	                            newFunc.SoapAction = soapOpElement.getAttribute("soapAction");
//	                            //get port types
//	                            NodeList portTypes = doc.getElementsByTagName("wsdl:portType");
//	                            //loop through elements
//	                            for(int pt=0; pt<portTypes.getLength() ; pt++)
//	                            {
//	                                Node portTypeNode = portTypes.item(pt);
//	                                if(portTypeNode.getNodeType() == Node.ELEMENT_NODE)
//	                                {
//	                                    Element portTypeElement = (Element)portTypeNode;
//	                                    //check to see if it is the same as the current operation
//	                                    if (portTypeElement.getAttribute("name").equals(portTypeNS))
//	                                    {
//	                                        //get the operations for this portType
//	                                        NodeList operationList = portTypeElement.getElementsByTagName("wsdl:operation");
//	                                        //loop through operation list
//	                                        for(int ol=0; ol<operationList.getLength() ; ol++)
//	                                        {
//	                                            Node operationListNode = operationList.item(ol);
//	                                            if(operationListNode.getNodeType() == Node.ELEMENT_NODE)
//	                                            {
//	                                                Element operationListElement = (Element)operationListNode;
//	                                                //see if the element matches the Method anem
//	                                                if (operationListElement.getAttribute("name").equals(newFunc.Name))
//	                                                {
//	                                                    //is this Method - get input parameter type
//	                                                    NodeList inputList = operationListElement.getElementsByTagName("wsdl:input");
//	                                                    String inputMessage = ((Element)inputList.item(0)).getAttribute("message").replaceFirst("tns:", "");
//	                                                    //is this Method - get return type
//	                                                    NodeList outputList = operationListElement.getElementsByTagName("wsdl:output");
//	                                                    String outputMessage = ((Element)outputList.item(0)).getAttribute("message").replaceFirst("tns:", "");
//	                                                    //now need to get the Types of the return and property value
//	                                                    //get port types
//	                                                    NodeList messages = doc.getElementsByTagName("wsdl:message");
//	                                                    for(int ml=0; ml<messages.getLength() ; ml++)
//	                                                    {
//	                                                        Node messageNode = messages.item(ml);
//	                                                        if(messageNode.getNodeType() == Node.ELEMENT_NODE)
//	                                                        {
//	                                                            //convert to element
//	                                                            Element messageElement = (Element)messageNode;
//	                                                            //compare against inputMessage name
//	                                                            if (messageElement.getAttribute("name").equals(inputMessage))
//	                                                            {
//	                                                                newFunc.InputType.nameOFType = ((Element)messageElement.getElementsByTagName("wsdl:part").item(0)).getAttribute("element").replaceFirst("tns:", "");
//	                                                                //System.out.println("In " + newFunc.InputType);
//	                                                            }
//	                                                            else //compare against inputMessage name
//	                                                            if (messageElement.getAttribute("name").equals(outputMessage))
//	                                                            {
//	                                                                //get response type
//	                                                                //get input param type
//	                                                                newFunc.OutputType.nameOFType = ((Element)messageElement.getElementsByTagName("wsdl:part").item(0)).getAttribute("element").replaceFirst("tns:", "");
//	                                                            }
//	                                                        }
//	                                                    }
//	                                                }
//	                                            }
//	                                        }
//	                                    }
//	                                }
//	                            }
//	                            //add newFunc to Methods array
//	                            Methods.add(newFunc);
//	                        }
//	                    }
//	                }
	            }
	        }
	    }
	    // now process the datatypes and classes
	    NodeList typenodes = doc.getElementsByTagName("wsdl:types");
	    Node typesNode = typenodes.item(0);
	    if(serviceNode.getNodeType() == Node.ELEMENT_NODE)
	    {
	        Element typesElement = (Element)typesNode;
	         //now get schema element
	        Node typeschemaNode = typesElement.getElementsByTagName("s:schema").item(0);
	        //serviceElement.getAttribute("name");
	        if(typeschemaNode.getNodeType() == Node.ELEMENT_NODE)
	        {
	            Element typeschemaElement = (Element)typeschemaNode;
	            //get elements
	            NodeList elementNodes = typeschemaElement.getElementsByTagName("s:element");
	            //get complexTypes
	            NodeList complexTypesNodes = typeschemaElement.getElementsByTagName("s:complexType");
	            //process element nodes and get class information
	            //create Classes container - make it big
	            //PropertyContainer.Classes = new ArrayList<SoapClass>();
	            //iterate through s:element objects
	            for (int elLoop = 0;elLoop < elementNodes.getLength();elLoop++)
	            {
	                Node elementNode = elementNodes.item(elLoop);
	                if(elementNode.getNodeType() == Node.ELEMENT_NODE)
	                {
	                    Element elementElement = (Element)elementNode;
	                    //get header elements from list - as the list contains all s:elements from the schema nodes
	                    if (elementNode.hasChildNodes())
	                    {
	                        //check to see not has name
	                        Class newClass = new Class(elementElement.getAttribute("name"));
	                        //set classtype to unknown
	                        newClass.Type = Class.ClassType.Unknown;
	                        //now get properties from class
	                        //get elements
	                        NodeList propertyNodes = elementElement.getElementsByTagName("s:element");
	                        //iterate through properties
	                        for (int propLoop = 0; propLoop < propertyNodes.getLength();propLoop++)
	                        {
	                            Node propertyNode = propertyNodes.item(propLoop);
	                            if(propertyNode.getNodeType() == Node.ELEMENT_NODE)
	                            {
	                                Element propertyElement = (Element)propertyNode;
	                                //create new property class
	                                SoapClassProperty newProp = new SoapClassProperty(propertyElement.getAttribute("name"));
	                                newProp.SetPropertyClassType(propertyElement.getAttribute("type"));
	                                //check to see if is array of objects
	                                if (propertyElement.getAttribute("maxOccurs").equals("unbounded"))
	                                {
	                                    //yes is array
	                                    newProp.m_IsArray= true;
	                                }
	                                newClass.Properties.add(newProp);
	                            }
	                        }
	                        //System.out.println("Element Class: " + newClass.Name +  newClass.Properties.size());
	                        Classes.add(newClass);
	                    }
	                }
	            }
	            //iterate through s:comlextypes objects
	            for (int ctLoop = 0;ctLoop < complexTypesNodes.getLength();ctLoop++)
	            {
	                Node ctypeNode = complexTypesNodes.item(ctLoop);
	                if(ctypeNode.getNodeType() == Node.ELEMENT_NODE)
	                {
	                    Element ctypeElement = (Element)ctypeNode;
	                    //get header elements from list - as the list contains all s:elements from the schema nodes
	                    if (ctypeNode.hasChildNodes())
	                    {
	                        //s:elements show up for some reason but have no name
	                        if (!ctypeElement.getAttribute("name").isEmpty())
	                        {
	                            Class newClass = new Class(ctypeElement.getAttribute("name"));
	                            //set class type to complex type
	                            newClass.Type = Class.ClassType.ComplexType;
	                            //check for base super class
	                            //NodeList spBaseNode = ctypeElement.getChildNodes();
	                            NodeList spBaseNode = ctypeElement.getElementsByTagName("s:extension");
	                            //Node ctNode = spBaseNode.item(1);
	                            if (spBaseNode.getLength() != 0)
	                            {
	                                Node sbBaseNode = spBaseNode.item(0);
	                                if(sbBaseNode.getNodeType() == Node.ELEMENT_NODE)
	                                {
	                                    Element sbBaseElement = (Element)sbBaseNode;
	                                    newClass.SuperClassType = sbBaseElement.getAttribute("base").replaceAll("tns:", "");
	                                }
	                            }
	                            System.out.println("SuperClass: " + newClass.SuperClassType);
	                             //get elements
	                            NodeList propertyNodes = ctypeElement.getElementsByTagName("s:element");
	                            //iterate through properties
	                            for (int propLoop = 0; propLoop < propertyNodes.getLength();propLoop++)
	                            {
	                                Node propertyNode = propertyNodes.item(propLoop);
	                                if(propertyNode.getNodeType() == Node.ELEMENT_NODE)
	                                {
	                                    Element propertyElement = (Element)propertyNode;
	                                    //create new property class
	                                    Class c = new Class ();
	                                    SoapClassProperty newProp = new SoapClassProperty(propertyElement.getAttribute("name"));
	                                    newProp.SetPropertyClassType(propertyElement.getAttribute("type"));
	                                    //check to see if is array of objects
	                                    if (propertyElement.getAttribute("maxOccurs").equals("unbounded"))
	                                    {
	                                        //yes is array
	                                        newProp.m_IsArray=true;
	                                        newClass.ElementType = newProp.m_Type;
	                                        newClass.isArray = true;
	                                    }
	                                    newClass.Properties.add(newProp);
	                                }
	                            }
	                            System.out.println("Complex Type: " + newClass.Name + " Properties: " + newClass.Properties.size());
	                            ComplexTypes.add(newClass);
	                        }
	                    }
	                }
	            }
	            //all class should have been created now - trim array
	            //System.out.println("Class Count: " + Classes.size());
	            //System.out.println("Complex Types: " + ComplexTypes.size());
	        }
	    }
	    return true;
    }

    public static Document getWsdl (String URL) throws SAXException, IOException, ParserConfigurationException
    {
        DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
	    DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
	    Document doc = docBuilder.parse(URL);
	
	    return doc;
    }
    public static boolean verifyWsdl (Document WSDL)
    {
            boolean verify = true ;
            return verify ;
    }
}
