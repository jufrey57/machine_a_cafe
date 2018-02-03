package exceptions;

public class MaximumBoissonAtteintException extends Exception{

	private static final long serialVersionUID = 1L;

	public MaximumBoissonAtteintException()
	{
		super("Erreur : le nombre maximale de boisson est atteint");
	}
	
}
