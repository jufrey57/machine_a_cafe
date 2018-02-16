package classes;

import java.io.Serializable;
import java.util.HashMap;

/**
 * Classe qui représente une boisson ainsi que ses ingrédients
 * 
 * @author Maxime
 */
public class Boisson implements java.io.Serializable {
	
	/** Nom de cette Boisson */
	private String nom;
	
	/** Prix de cette Boisson */
	private int prix;
	
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
		listeIngredient = new HashMap<String, Integer>();
	}
	
	/**
	 * Modifie l'ingrédient donné de cette Boisson dans listeIngredients
	 * @param ingredient	String
	 * @param nombre		Integer
	 * @return Integer	Dernière valeur associée à ingredient, null sinon
	 */
	public boolean modifierIngredient(String ingredient, Integer nombre)
	{
		boolean reponse = false;
		if(this.listeIngredient.put(ingredient, nombre) != null)
		{
			reponse = true;
		}
		
		return reponse;	
	}
	
	public void supprimerIngredient(String ingredient)
	{
		this.listeIngredient.remove(ingredient);
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
		String desc = this.nom + " ( ";
		for(HashMap.Entry ingredient : this.listeIngredient.entrySet())
		{
			desc += ingredient.getKey() + " (" + ingredient.getValue() + ") ";
		}
		desc += " ) ";
		return desc;
	}
	
	/**
	 * Getter for nom
	 * @return String
	 */
	public String getNom() {
		return nom;
	}

	/**
	 * Setter for nom
	 * @param nom String
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}

	/**
	 * Getter for prix
	 * @return int
	 */
	public int getPrix() {
		return prix;
	}

	/**
	 * Setter for prix
	 * @param prix int
	 */
	public void setPrix(int prix) {
		this.prix = prix;
	}

	/**
	 * Getter for listeIngredient
	 * @return HaskMap<String, Integer>
	 */
	public HashMap<String, Integer> getListeIngredient() {
		return listeIngredient;
	}

	/**
	 * Setter for listeIngredient
	 * @param listeIngredient Hashmap<String, Integer>
	 */
	public void setListeIngredient(HashMap<String, Integer> listeIngredient) {
		this.listeIngredient = listeIngredient;
	}
}
