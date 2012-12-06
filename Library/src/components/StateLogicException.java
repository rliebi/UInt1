package components;


public class StateLogicException extends Exception {

	private static final long serialVersionUID = 2348446652246880076L;

	public StateLogicException() {
		super("This Method should not be reached from this State");
	}

}
