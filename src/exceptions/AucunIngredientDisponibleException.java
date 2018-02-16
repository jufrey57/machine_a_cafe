package exceptions;

public class AucunIngredientDisponibleException extends Exception {

	private static final long serialVersionUID = 1L;

	public AucunIngredientDisponibleException()
	{
		super("Attention : Cette boisson ne possède aucun ingrédient");
	}
	
}
