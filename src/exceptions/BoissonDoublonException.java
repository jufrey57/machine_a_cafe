package exceptions;

public class BoissonDoublonException extends Exception {

	private static final long serialVersionUID = 1L;

	public BoissonDoublonException()
	{
		super("Erreur : Cette boisson existe déjà");
	}
}
