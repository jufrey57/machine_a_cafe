package exceptions;

public class StocksIngredientsInsuffisantsException extends Exception {

	private static final long serialVersionUID = 1L;

	public StocksIngredientsInsuffisantsException()
	{
		super("Erreur : Plus assez d'ingrédients en stock pour cette boisson");
	}
	
}
