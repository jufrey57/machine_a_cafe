package exceptions;

public class PrixInvalideException extends Exception{

	private static final long serialVersionUID = 1L;

	public PrixInvalideException()
	{
		super("Erreur : Prix invalide");
	}
	
}
