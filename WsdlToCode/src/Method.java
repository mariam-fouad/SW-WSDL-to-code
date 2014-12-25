
public class Method extends WSDLElement{
	 String SoapAction;
    //Paramater[] InputParameters;
    type OutputType;
    type InputType;
    
    public Method()
    {
    	SoapAction = new String();
    	OutputType = new type();
    	InputType = new type();
    }
	public String getAction() {
		return SoapAction;
	}
	public void setAction(String action) {
		SoapAction = action;
	}
	
	public type getOutputType() {
		return OutputType;
	}
	public void setOutputType(type outputType) {
		OutputType = outputType;
	}

}
