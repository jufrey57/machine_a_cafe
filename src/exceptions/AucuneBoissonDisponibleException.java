package exceptions;

public class AucuneBoissonDisponibleException extends Exception {
	
	private static final long serialVersionUID = 1L;

	public AucuneBoissonDisponibleException()
	{
		super("Erreur : Plus aucune boisson n'est disponible");
	}
}
