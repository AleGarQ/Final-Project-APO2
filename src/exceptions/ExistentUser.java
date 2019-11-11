package exceptions;

public class ExistentUser extends Exception{
	public ExistentUser(String error) {
		super(error);
	}
}
