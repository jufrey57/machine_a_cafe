/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.HashMap;
import java.util.Scanner;

/**
 *
 * @author julien
 */
public class ManagerMachine {
	private static int NB_BOISSONS_MAX = 3;
    private HashMap<String,Integer> ingredients = new HashMap<String,Integer>();
    private Boisson boissons[] = new Boisson[NB_BOISSONS_MAX];
    
    public ManagerMachine()
    {
        ingredients.put("cafe", 200);
        ingredients.put("lait", 200);
        ingredients.put("chocolat", 200);
        ingredients.put("sucre", 200);
    }
    
    /**
     * ajoutBoisson() Ajoute une boisson dans la machine (max 3 boissons)
     * @param boisson
     */
    public void ajoutBoisson (Boisson boisson)
    {
    		boolean disponible = false;
    		int i = 0;
    		
    		while(i < NB_BOISSONS_MAX && disponible) 
    		{
    			if(!(boissons[i] instanceof Boisson)) 
    			{
    				boissons[i] = boisson;
    				disponible = true;
    			}
    			i++;
    		}
    		
    		if(!disponible) 
    		{
    			System.out.println("Impossible d'ajouter une boisson: Déjà trop de boisson");
    		}
    }
    
    /**
     * afficheBoissons() Affiche les boissons présentent dans la machine
     */
    public void afficheBoissons() 
    {
    		System.out.println("Liste des boissons:");
    		for(Boisson boisson : boissons) 
    		{
    			if(boisson != null)
    			{
    				System.out.println(boisson.toString());
    			}
    		}
    }
    
    /**
     * getQuantiteIngredient() 
     * @param nom
     * @return la quantité de cet ingrédient
     */
    public int getQuantiteIngredient(String nom)
    {
        return ingredients.get(nom);
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
        
        String listeIngredients = "";
        
        for ( String ingredient : ingredients.keySet() ) 
        {
            listeIngredients += ingredient + ": " + ingredients.get(ingredient) + "\n";
        }
        

		return listeIngredients;
    }
}
