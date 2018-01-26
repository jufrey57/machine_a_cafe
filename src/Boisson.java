

import java.util.HashMap;

/**
 * Classe qui représente une boisson ainsi que ses ingrédients
 * 
 * @author bleacks (Maxime Dolet)
 */
public class Boisson {
	
	/** Nom de cette Boisson */
	public String nom;
	
	/** Prix de cette Boisson */
	public int prix;
	
	/** Ingrédients utilisés dans cette Boisson */
	private HashMap<String, Integer> listeIngredient;
	
	/**
	 * Constructeur de Boisson
	 * @param nom	String
	 * @param prix	Integer
	 */
	public Boisson(String nom, Integer prix)
	{
		this.nom = nom;
		this.prix = prix;
	}
	
	/**
	 * Modifie l'ingrédient donné de cette Boisson dans listeIngredients
	 * @param ingredient	String
	 * @param nombre		Integer
	 * @return Integer	Dernière valeur associée à ingredient, null sinon
	 */
	public int modifierIngredient(String ingredient, Integer nombre)
	{
		return this.listeIngredient.put(ingredient, nombre);
	}
	
	/**
	 * Retourne la liste des ingrédients ainsi que leur coût associé
	 * @return
	 */
	public HashMap<String, Integer> getCoutIngredients()
	{
		HashMap<String, Integer> res = null;
		return res;
	}
	
	/**
	 * Retourne une chaine descriptive de cette Boisson
	 * @return String 
	 */
	@Override
	public String toString()
	{
		String desc = nom + " ( ";
		for(HashMap.Entry ingredient : this.listeIngredient.entrySet())
		{
			desc += ingredient.getKey() + " (" + ingredient.getValue() + ") ";
		}
		return desc;
	}
}
