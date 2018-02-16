package classes;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;

/** TODO
 * 5 boissons
 * +/- sucre
 * persistance des ingrédients/boissons
 */

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

import exceptions.AucuneBoissonDisponibleException;
import exceptions.BoissonDoublonException;
import exceptions.MaximumBoissonAtteintException;
import exceptions.PrixInvalideException;
import exceptions.StocksIngredientsInsuffisantsException;

/**
 *
 * @author julien, Maxime
 */
public class ManagerMachine {
    private HashMap<String,Integer> ingredients = new HashMap<String,Integer>();
    private ArrayList<Boisson> boissons = new ArrayList<Boisson>();
    private final int MAXIMUM_BOISSON = 5;
    private final int MAXIMUM_INGREDIENT = 200;
    
    public HashMap<String,Integer> getIngredients(){
    		return this.ingredients;
    }
    
    /**
     * Vérifie que le nom passé en paramètre est bien disponible
     * @param nom String
     * @return boolean
     */
    public boolean verifierNom(String nom)
    {
    		boolean res = true;
    		for (Boisson boisson : boissons)
    			if (boisson.getNom() == nom)
    				res = false;
    		return res;
    }
    
    /**
     * ajoutBoisson() Ajoute une boisson dans la machine (max 3 boissons)
     * @param <String> nom, <int> prix
     * @return <Boisson> boisson
     * @throws MaximumBoissonAtteintException 
     * @throws PrixInvalideException 
     * @throws BoissonDoublonException
     */
    public Boisson ajoutBoisson (String nom, int prix) throws BoissonDoublonException, MaximumBoissonAtteintException, PrixInvalideException
    {
    		Boisson boisson = null;
    		
    		if (boissons.size() > MAXIMUM_BOISSON)
    			throw new MaximumBoissonAtteintException();
    		else {
    			if (prix < 0)
    				throw new PrixInvalideException();
    			else {
    				if (verifierNom(nom))
    				{
    					boisson = new Boisson(nom, prix);
    					boissons.add(boisson);
    				}
    				else {
    					throw new BoissonDoublonException();
    				}
    			}
    		}
    		
    		return boisson;
    }
    
    /**
     * Supprimer la boisson passée en paramètre
     * @param Boisson boisson
     */
    public void supprimerBoisson(Boisson boisson)
    {
    		boissons.remove(boisson);
    }
    
    /**
     * Getter pour boissons
     * @return ArrayList<Boisson> 
     */
    public ArrayList<Boisson> getListeBoissons() 
    {
    		return boissons;
    }
    
    /**
     * getBoisson() Recherche une boisson et revoie l'objet
     * @param nom
     * @return <Boisson> Objet boisson
     */
    public Boisson getBoisson(String nom)
    {
    		Boisson boisson = null;
    		int i = -1;
    		boolean trouve = false;
    		while(i < boissons.size() - 1 && !trouve)
    		{
    			i++;
    			if(this.boissons.get(i).getNom().equals(nom))
    			{
    				boisson = this.boissons.get(i);
    				trouve = true;
    			}
    		}

    		return boisson;
    }
    
    /**
     * getQuantiteIngredient() 
     * @param nom
     * @return la quantité de cet ingrédient
     */
    public int getQuantiteIngredient(String nom)
    {
    		if(ingredients.containsKey(nom))
    		{
    			return ingredients.get(nom);
    		}
    		else
    		{
    			return -1;
    		}
    }
    
    /**
     * ajoutIngredient() Ajoute l'ingrédient dans le HashMap. Vérification si le nom 
     * n'est pas prix, puis la quantité
     * @param nom
     * @param quantite
     */
    public void ajoutIngredient(String nom, Integer quantite)
    {
    		int stock = quantite;
	    	if (ingredients.containsKey(nom))
	    		 stock += ingredients.get(nom);
			
		if(stock <= MAXIMUM_INGREDIENT)
		{
			System.out.println("Nouveau stock de l'ingrédient " + nom +" : "+ stock);
		} 
		else
		{
			System.out.println("Stock de \"" + nom + "\" plein. "+ (stock - MAXIMUM_INGREDIENT) + " n'ont pu être ajoutés");
			stock = MAXIMUM_INGREDIENT;
		}
			ingredients.put(nom, stock);
    }
    
    /** getListeIngredients()
     *  Affiche tous les ingrédients dans la console. Puis demande d'appuyer sur une touche pour continuer
     *  @return Renvoie la liste des ingrédients
     */
    public String getListeIngredients()
    {
        
        String listeIngredients = "Ingrédient \tQuantité \n";
        
        for ( String ingredient : ingredients.keySet() ) 
        {
            listeIngredients += ingredient + "\t\t" + ingredients.get(ingredient) + "\n";
        }
        

		return listeIngredients;
    }
    
    public Set<String> getNomIngredients()
    {
    		return ingredients.keySet();
    }
    
    /**
     * acheterUneBoisson() L'achat de boisson inclut le fait de retirer la quantité d'ingrédient demandé par la boisson puis de rendre la monnaie
     * @param nom
     * @param argent
     * @return La monnaie à rendre. Si aRendre < 0 alors pas assez d'argent ou 
     * @throws StocksIngredientsInsuffisantsException 
     */
    public Integer acheterBoisson(Boisson boisson, int argent, int quantiteSucre) throws StocksIngredientsInsuffisantsException {
    	
    		int prix = boisson.getPrix();
    		HashMap<String, Integer> listeIngredientsBoisson = new HashMap<String, Integer>();
    		listeIngredientsBoisson = boisson.getListeIngredient();
    		Integer aRendre =  argent - prix;
    		
    		if(aRendre >= 0)
    		{
    			if(this.vérifierAssezDIngredient(boisson)){
    				retirerIngredient("sucre", quantiteSucre);
    				for(String ingredient : listeIngredientsBoisson.keySet())
					retirerIngredient(ingredient, listeIngredientsBoisson.get(ingredient));
    			}
    			else
    			{
    				throw new StocksIngredientsInsuffisantsException();
    			}
    		}
    	
    		return aRendre;
    }
    
    /**
     * ajoutIngredientBoisson() Ajout d'un ingrédient à une boisson
     * @param nom
     * @param ingredient
     * @param quantite
     * @return true si ça c'est bien passé, sinon false
     */
    public boolean ajoutIngredientBoisson(String nom, String ingredient, Integer quantite)
    {
           boolean reponse = this.getBoisson(nom) != null && this.getBoisson(nom).modifierIngredient(ingredient, quantite);     
           return reponse;
	}
    
    /**
     * ajoutIngredientBoisson() Ajout d'un ingrédient à une boisson
     * @param Boisson boisson boisson à modifier
     * @param String ingredient ingrédient à MAJ
     * @param quantite nouvelle quantité pour cet ingrédient
     * @return boolean true si ça c'est bien passé, sinon false
     */
    public boolean ajoutIngredientBoisson(Boisson boisson, String ingredient, Integer quantite)
    {
    		boolean res = boisson != null && boisson.modifierIngredient(ingredient, quantite); 
    		return res;
    }
    
    /**
     * retirerIngredient() retire une quantité à un ingredient de la machine
     * @param ingredient
     * @param quantite
     * @return Retourne true si ça c'est bien passé, sinon false
     * @throws StocksIngredientsInsuffisantsException 
     */
    private void retirerIngredient(String ingredient, int quantite) throws StocksIngredientsInsuffisantsException
    {
    		int stock = this.getQuantiteIngredient(ingredient) - quantite;
    		if (stock< 0)
    			throw new StocksIngredientsInsuffisantsException();
		this.ingredients.put(ingredient, stock);
		
    }
    
    /**
     * checkAssezDIngredient() Vérifier qu'il y a assez d'ingrédient dans la machine
     * @param ingredient
     * @param quantite
     * @return Si vrai, il y a assez d'ingrédient
     */
    private boolean vérifierAssezDIngredient(Boisson boisson)
    {
    		boolean reponse = true;
    		HashMap<String, Integer> listeIngredients = new HashMap<String, Integer>(); 
    		listeIngredients = boisson.getListeIngredient();
    		
    		for(String ingredient : listeIngredients.keySet()) 
    		{
    			if((this.getQuantiteIngredient(ingredient) - listeIngredients.get(ingredient)) < 0)
    			{
    				reponse = false;
    			}
    		}
	
		return reponse;
    }
    
    public Boisson getBoisson(int index) throws IndexOutOfBoundsException, AucuneBoissonDisponibleException
    {
    		if (boissons.size() < 1)
    			throw new AucuneBoissonDisponibleException();
    		else
    			return this.boissons.get(index);
    }
    
    /**
     * Retourne une chaîne descriptive des différentes boissons de la machine
     * @return string liste de boissons
     */
    public String toString()
    {
    		String res = "N°:\tPrix \tBoisson (ingrédients)\n";
    		Boisson boisson;
    		for (int i = 0; i < boissons.size(); i++)
    		{
    			boisson = boissons.get(i);
    			res += (i+1) + ")\t" + boisson.getPrix() + "\t" + boisson + "\n";
    		}
    		return res;
    }
}
