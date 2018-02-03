package src;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.ArrayList;
import java.util.HashMap;

import exceptions.BoissonDoublonException;
import exceptions.MaximumBoissonAtteintException;
import exceptions.PrixInvalideException;

/**
 *
 * @author julien, Maxime
 */
public class ManagerMachine {
    private HashMap<String,Integer> ingredients = new HashMap<String,Integer>();
    private ArrayList<Boisson> boissons = new ArrayList<Boisson>();
    private final int MAXIMUM_BOISSON = 5;
    private final int MAXIMUM_INGREDIENT = 200;
    
    public ManagerMachine()
    {
        ingredients.put("cafe", 200);
        ingredients.put("lait", 200);
        ingredients.put("chocolat", 200);
        ingredients.put("sucre", 200);
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
        for ( String key : ingredients.keySet() ) 
        {
            if(nom.toLowerCase() != key.toLowerCase())
            {
                if(quantite > 0)
                {
                    if((quantite + getQuantiteIngredient(nom)) < MAXIMUM_INGREDIENT)
                    {
                        ingredients.put(nom.toLowerCase(), quantite); 
                    }
                    else 
                    {
                        System.out.println("Quantité Overflow");
                    }
                }
                else
                {
                    System.out.println("Quantité insuffisante");
                }
            }
            else
            {
                System.out.println("Ingrédient déjà présent.");
            }
        }
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
    
    /**
     * ajouterIngredient() ajoute un ingredient à la machine (on parle de la quantité)
     * @param ingredient
     * @param quantite
     * @return
     */
    public boolean ajouterIngredient(String ingredient, int quantite)
    {
    		boolean reponse = false;
    		
    		if(this.verifierQuantiteIngredient(ingredient, quantite))
		{
			quantite = this.getQuantiteIngredient(ingredient) + quantite;
			this.ingredients.put(ingredient, quantite);
			reponse = true;
		}
    	
    		return reponse;
    }
    
    /**
     * verifierQuantiteIngredient() Vérifie que la quantité ne dépasse pas 200
     * @param ingredient
     * @param quantite
     * @return
     */
    public boolean verifierQuantiteIngredient(String ingredient, int quantite)
    {
    		boolean reponse = false;
    		if((this.getQuantiteIngredient(ingredient) + quantite) > 200)
    		{
    			reponse = true;
    		}
    		
    		return reponse;
    }
}
