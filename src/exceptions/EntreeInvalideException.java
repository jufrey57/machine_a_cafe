package exceptions;

public class EntreeInvalideException extends Exception {

	private static final long serialVersionUID = 1L;

	public EntreeInvalideException()
	{
		super("Erreur : Entrée invalide");
	}
}
