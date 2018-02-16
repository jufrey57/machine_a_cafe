package classes;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

import exceptions.AucuneBoissonDisponibleException;
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
    public boolean ajoutIngredient(String nom, Integer quantite)
    {
    		boolean res = false;
    	
        for ( String key : ingredients.keySet() ) 
        {
            if(nom.toLowerCase() != key.toLowerCase())
            {
                if(quantite > 0)
                {
                    if((quantite + this.getQuantiteIngredient(nom)) < MAXIMUM_INGREDIENT)
                    {
                        ingredients.put(nom.toLowerCase(), quantite); 
                        res = true;
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
        
        return res;
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
     * modifierIngredient() ajoute un ingredient à la machine (on parle de la quantité)
     * @param ingredient
     * @param quantite
     * @return
     */
    public boolean modifierIngredient(String ingredient, int quantite)
    {
    		boolean reponse = false;
    		
    		if(this.verifierQuantiteIngredient(ingredient, quantite))
		{
			quantite = this.getQuantiteIngredient(ingredient) + quantite;
			this.ingredients.put(ingredient, quantite);
			reponse = true;
		} else {
			System.out.println("Quantité overflow: maximum 200\n");
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
    		if((this.getQuantiteIngredient(ingredient) + quantite) < 200)
    		{
    			reponse = true;
    		}
    		
    		return reponse;
    }
    
    
    /**
     * acheterUneBoisson() L'achat de boisson inclut le fait de retirer la quantité d'ingrédient demandé par la boisson puis de rendre la monnaie
     * @param nom
     * @param argent
     * @return La monnaie à rendre. Si aRendre < 0 alors pas assez d'argent ou 
     */
    public Integer acheterBoisson(Boisson boisson, int argent, int quantiteSucre) {
    	
    		int prix = boisson.getPrix();
    		HashMap<String, Integer> listeIngredientsBoisson = new HashMap<String, Integer>();
    		listeIngredientsBoisson = boisson.getListeIngredient();
    		Integer aRendre =  argent - prix;
    		
    		if(aRendre >= 0) 
    		{
    			if(this.vérifierAssezDIngredient(boisson)){
    				for(String ingredient : listeIngredientsBoisson.keySet())
        			{
    					if(ingredient.equals("sucre")) {
    						this.retirerIngredient(ingredient, quantiteSucre);
    					} else {
    						this.retirerIngredient(ingredient, listeIngredientsBoisson.get(ingredient));
    					}
        			}	
    			}
    			else
    			{
    				aRendre = null;
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
     */
    private void retirerIngredient(String ingredient, int quantite)
    {
    		
		quantite = this.getQuantiteIngredient(ingredient) - quantite;
		this.ingredients.put(ingredient, quantite);
		
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
