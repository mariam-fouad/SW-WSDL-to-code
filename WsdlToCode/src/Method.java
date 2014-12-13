
public class Method extends Element{
	private String Action;
    private Paramater[] InputParameters;
    private String OutputType;
	public String getAction() {
		return Action;
	}
	public void setAction(String action) {
		Action = action;
	}
	public Paramater[] getInputParameters() {
		return InputParameters;
	}
	public void setInputParameters(Paramater[] inputParameters) {
		InputParameters = inputParameters;
	}
	public String getOutputType() {
		return OutputType;
	}
	public void setOutputType(String outputType) {
		OutputType = outputType;
	}

}
