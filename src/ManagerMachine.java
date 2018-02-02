/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 *
 * @author julien
 */
public class ManagerMachine {
	private static int NB_BOISSONS_MAX = 3;
    private HashMap<String,Integer> ingredients = new HashMap<String,Integer>();
    private ArrayList<Boisson> boissons = new ArrayList<Boisson>();
    
    public ManagerMachine()
    {
        ingredients.put("cafe", 200);
        ingredients.put("lait", 200);
        ingredients.put("chocolat", 200);
        ingredients.put("sucre", 200);
    }
    
    /**
     * ajoutBoisson() Ajoute une boisson dans la machine (max 3 boissons)
     * @param <String> nom, <int> prix
     * @return <Boisson> boisson
     */
    public Boisson ajoutBoisson (String nom, int prix)
    {
    		Boisson boisson = null;
    		
    		/*for()
    		{
    			
    		}*/
    		
    		if(boissons.size() < 3)
    		{
    			boisson = new Boisson(nom, prix);
    			boissons.add(boisson);
    		}
    		else
    		{
    			System.out.println("Impossible d'ajouter une boisson: Déjà trop de boisson");
    		}
    		
    		return boisson;
    }
    
    /**
     * supprimerBoisson() Suppression de la boisson à l'index indiqué
     * @param index
     * @return <Boolean>
     */
    public boolean supprimerBoisson(int index)
    {
    		boolean reponse = false;
    	
    		if(!(index < 0 || index > 2))
    		{
    			boissons.remove((index - 1));
    			reponse = true;
    		}
    		
    		return reponse;
    }
    
    /**
     * getListeBoissons() Affiche les boissons présentent dans la machine
     * @return retourne un String avec la liste des boissons dans lama chine
     */
    public String getListeBoissons() 
    {
    		String listeBoissons = "N°:\tPrix \tNom \n";
    		
    		int i = 1;
    		for(Boisson boisson : boissons) 
    		{
    			if(boisson != null)
    			{
    				listeBoissons += i + "\t" + boisson.getPrix() + "\t" + boisson.getNom() + "\n";
    				i++;
    			}
    		}
    		
    		return listeBoissons;
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
                    if((quantite + getQuantiteIngredient(nom)) < 200)
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
        
        String listeIngredients = "n°: \t Ingrédient \t Quantité \n";
        
        for ( String ingredient : ingredients.keySet() ) 
        {
            listeIngredients += ingredient + ": " + ingredients.get(ingredient) + "\n";
        }
        

		return listeIngredients;
    }
}
