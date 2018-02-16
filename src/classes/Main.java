package classes;

import java.io.IOException;
/**
 * 
 * @author Maxime
 */
import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import exceptions.AucunIngredientDisponibleException;
import exceptions.AucuneBoissonDisponibleException;
import exceptions.BoissonDoublonException;
import exceptions.EntreeInvalideException;
import exceptions.MaximumBoissonAtteintException;
import exceptions.PrixInvalideException;
import exceptions.StocksIngredientsInsuffisantsException;

public class Main
{
	private ManagerMachine manager;
	
	private String menu;
	
	private Scanner sc;
	// TODO: Faire un sytème de log
	// TODO: Utiliser un enum ou un moyen propre de stocker les infos du menu
	
	public Main()
	{
		manager = new ManagerMachine();
		sc = new Scanner(System.in);
		this.init();
		
		menu = "-- Menu prinicpal de la machine à café --\n"
				+ "Quelle action souhaitez vous effectuer ?\n"
				+ "1) Acheter une boisson\n"
				+ "2) Modifier une boisson\n"
				+ "3) Supprimer une boisson\n"
				+ "4) Ajouter une boisson\n"
				+ "5) Vérifier le stock d'ingrédients\n"
				+ "6) Ajouter un ingrédient\n"
				+ "7) Lister les boissons disponibles\n"
				+ "8) Quitter";

	}
	
	private Boisson choisirBoisson() throws AucuneBoissonDisponibleException, EntreeInvalideException
	{
		System.out.println("Veuillez saisir le numéro de la boisson souhaitée :");
		System.out.println(manager);
		
		int index = lireEntier() - 1;
		Boisson boisson = null;
		try
		{
			 boisson = manager.getBoisson(index);
		}
		catch(IndexOutOfBoundsException ie)
		{
			System.out.println("Veuillez saisir un numéro de boisson valide svp");
			boisson = choisirBoisson();
		}
		catch (AucuneBoissonDisponibleException ae)
		{
			System.out.println("Plus aucune boisson n'est disponible");
			throw new EntreeInvalideException();
		}
		
		return boisson;
	}
	
	private void ajouterBoisson() throws EntreeInvalideException
	{
		// Donner un prix à la boisson
		System.out.println("Donner un prix à votre boisson");
		int prix = lireEntier();
		
		// Nommer la boisson
		System.out.println("Donner un nom à votre boisson");
		String nom = sc.nextLine();
		
		try {
			Boisson boisson = manager.ajoutBoisson(nom, prix);
			
			// Afficher boisson ajoutée
			if (boisson != null)
				System.out.println("Boisson ajoutée");
			else
				System.out.println("Problème lors de l'ajout de la boisson");
			
			modifierBoisson(boisson);
		}
		catch (BoissonDoublonException e)
		{
			System.out.println("Ce nom de boisson est déjà utilisé");
			ajouterBoisson();
		}
		catch (MaximumBoissonAtteintException e)
		{
			System.out.println("le nombre maximal de boissons à été atteint pour cette machine");
			ajouterBoisson();
		}
		catch (PrixInvalideException e)
		{
			System.out.println("Le prix donné à cette boisson est invalide");
			ajouterBoisson();
		}
	}
	
	private void modifierBoisson(Boisson boisson) throws EntreeInvalideException
	{
		System.out.println("Comment souhaitez vous modifier cette boisson ? "+ boisson + "\n"
				+ "1) Ajouter un ingrédient\n"
				+ "2) Supprimer un ingrédient\n"
				+ "0) Quitter");
		
		int res = lireEntier();
		
		try {	
			switch (res)
			{
			case 1:
				ajouterIngredient(boisson);
				break;
				
			case 2:
				supprimerIngredient(boisson);
				break;
				
			default:
				throw new EntreeInvalideException();
			}
		}
		catch (AucunIngredientDisponibleException a)
		{
			System.out.println("Attention : Cette boisson ne possède aucun ingrédient");
		}
	}
	
	private void ajouterIngredient(Boisson boisson) throws EntreeInvalideException
	{
		String menu = "N\tIngrédient\n";
		ArrayList<String> ingredients = new ArrayList<String>();
		
		for(String ingredient : manager.getNomIngredients())
			if (!ingredient.equals("sucre"))
				ingredients.add(ingredient);
		
		for (int i = 0; i < ingredients.size(); i++)
		{
			menu += (i+1) + ") " + ingredients.get(i) + "\n";
		}
		menu += "0) Quitter";
		
		System.out.println(menu);
		int res = lireEntier() - 1;
		
		if (res < 0 || res > ingredients.size())
			throw new EntreeInvalideException();
		
		System.out.println("Donner la quantité nécessaire pour cet ingrédient");
		int quantite = lireEntier();
		String ingredient = ingredients.get(res);
		System.out.println("Ingrédient " + ingredient + " ajouté à " + boisson.getNom() + " au nombre de " + quantite);
		manager.ajoutIngredientBoisson(boisson, ingredient, quantite);
	}
	
	private void supprimerIngredient(Boisson boisson) throws EntreeInvalideException, AucunIngredientDisponibleException
	{
		String menu = "N\tIngrédient\n";
		HashMap<String, Integer> ingredients = boisson.getListeIngredient();
		
		if (ingredients.size() <= 0)
			throw new AucunIngredientDisponibleException();
		
		ArrayList<String> menuIngredients = new ArrayList<String>();
		
		Iterator<Entry<String, Integer>> it = ingredients.entrySet().iterator();
		int i = 1;
	    while (it.hasNext()) {
	        Map.Entry<String, Integer> pair = (Map.Entry<String, Integer>)it.next();
	        menuIngredients.add((String) pair.getKey());
	        menu += i + ") "+ pair.getKey() + " : " + pair.getValue() + "\n";
	        i++;
	        //it.remove();
	    }
		
		menu += "0) Quitter";
		System.out.println(menu);
		
		int choix = lireEntier() - 1;
		boolean valid = false;
		String ingredient = "";
		
		if (choix == -1)
			throw new EntreeInvalideException();
		
		while (!valid)
		{
			try {
				ingredient = menuIngredients.get(choix);
				valid = true;
			}
			catch (IndexOutOfBoundsException e)
			{
				System.out.println("Choix invalide, veuillez réessayer");	
				sc.nextLine();
			}
		}
		
		System.out.println("Voulez-vous vraiment supprimer l'ingredient : \""+ ingredient +"\" de la boisson : "+ boisson + " ? (y/N)");
		String res = sc.nextLine();
		
		if (res.toLowerCase().equals("y"))
		{
			boisson.supprimerIngredient(ingredient);
			System.out.println("Ingrédient \""+ ingredient +"\" supprimé de la boisson : " + boisson);
		}
		else
			System.out.println("Suppression annulée");
	}
	
	private void ajouterIngredient() throws EntreeInvalideException {
		String menu = "Quel ingrédient voulez modifier ?\n\n";
		menu += "N\tnom\n\n";
		ArrayList<String> ingredients = new ArrayList<String>();
		
		for(String ingredient : manager.getNomIngredients())
			ingredients.add(ingredient);
		
		for (int i = 0; i < ingredients.size(); i++)
		{
			menu += (i+1) + ")\t" + ingredients.get(i) + "\n";
		}
		
		menu += "0)\tQuitter";
		
		System.out.println(menu);
		int res = lireEntier() - 1;

		if (res < 0 || res > ingredients.size())
			throw new EntreeInvalideException();
		
		System.out.println("Donner la quantité à ajouter pour cet ingrédient");
		int quantite = lireEntier();

		String ingredient = ingredients.get(res);
		manager.ajoutIngredient(ingredient, quantite);
	}
	
	private void acheterBoisson(Boisson boisson) throws EntreeInvalideException
	{
		System.out.println("Veuillez insérer de la monnaie svp");
		int argent = lireEntier();
		
		int quantiteSucre = 5;
		
		if (argent < boisson.getPrix())
		{
			System.out.println("Erreur : Fonds insuffisants, reprenez votre monnaie : " + argent);
		}
		else
		{
			do {
				System.out.println("Quelle quantité de sucre souhaitez-vous ? minimum - 0, maxmum - 10");
				quantiteSucre = lireEntier();
				
				if(quantiteSucre < 0) {
					System.out.println("Quantité de sucre incorrect\n");
				} else if(quantiteSucre > 10){
					System.out.println("Quantité de sucre trop élevé\n");
				}
			} while(quantiteSucre < 0 || quantiteSucre > 10);
			
			try 
			{
				Integer reste = manager.acheterBoisson(boisson, argent, quantiteSucre);
				
				if (reste > 0)
					System.out.println("Veuillez récupérez votre monnaie : " + reste);
				System.out.println("Merci, dégustez bien votre breuvage");				
			}
			catch (StocksIngredientsInsuffisantsException e)
			{
				System.out.println("Certains ingrédients nécessaires à cette boisson ne sont plus en stock, veuillez récupérer votre monnaie " + argent);
			}
		}
	}
	
	private void supprimerBoisson(Boisson boisson)
	{
		System.out.println("Voulez-vous vraiment supprimer cette boisson : "+ boisson + " ? (y/N)");
		String res = sc.nextLine();
		
		if (res.toLowerCase().equals("y"))
		{
			manager.supprimerBoisson(boisson);
			System.out.println("Vous avez supprimé la boisson : " + boisson +" avec succès");
		}
		else
			System.out.println("Suppression annulée");
	}
	
	private String effectuerAction(int action)
	{
		String res = "";
		
		if (action > 0)
		{
			Boisson boisson;
			
			try
			{
				switch (action)
				{
				case 1:
					boisson = choisirBoisson();
					acheterBoisson(boisson);
					break;
						
				case 2:
					boisson = choisirBoisson();
					modifierBoisson(boisson);
					break;
					
				case 3:
					boisson = choisirBoisson();
					supprimerBoisson(boisson);
					break;
					
				case 4:
					ajouterBoisson();
					break;
					
				case 5:
					res = manager.getListeIngredients();
					System.out.println(res);
					break;
					
				case 6:
					ajouterIngredient();
					break;
					
				case 7:
					res = manager.getListeBoissons().toString();
					System.out.println(res);
					break;
					
				case 8:
					try {
						Save.exportation(manager);
						System.out.println("Merci de votre visite");
						System.exit(0);
					} catch (IOException e) {
						e.printStackTrace();
					}
					break;
					
				default:
					System.out.println("Action inconnue");
					break;
				}
				System.out.println("Appuyer sur entrée pour revenir au menu principal");
				try {
					System.in.read();
				} catch (IOException e) {
					e.printStackTrace();
				}
			} catch (EntreeInvalideException ex)
			{
				System.out.println("Retour au menu principal\n");
			} catch (AucuneBoissonDisponibleException e1) {
				e1.printStackTrace();
			}
		}
		else
			res = "Erreur lors de l'ajout";
		
		return res;
	}
	
	private int lireEntier()
	{
		int res = 0;
		boolean valid = false;
		while (!valid)
		{
			try {
				res = sc.nextInt();
				sc.nextLine();
				valid = true;
			}
			catch (InputMismatchException e)
			{
				System.out.println("Valeur invalide, entier demandé, veuillez réessayer");
				res = -1;
				sc.nextLine();
			}
		}
		return res;
	}
	
	public void run()
	{
		int res = -1;
		do {
			System.out.println(menu);
			res = lireEntier();
			effectuerAction(res);
		} 
		while (true);
	}
	
	public void init()
	{
		/*try {
			if(Save.importationIngredients() != null)
			{
				ingredients = Save.importationIngredients();
				boissons = Save.importationBoissons();
			} else {
				ingredients.put("cafe", 200);
			    ingredients.put("lait", 200);
			    ingredients.put("chocolat", 200);
			    ingredients.put("sucre", 200);
			    
			    try {
					ajoutBoisson("expresso", 4);
					ajoutBoisson("cafe long", 3);
					ajoutBoisson("chocolat", 2);
					ajoutBoisson("cappuccino", 3);
				} catch (BoissonDoublonException | MaximumBoissonAtteintException | PrixInvalideException e) {
					e.printStackTrace();
				}
				
				ajoutIngredientBoisson("expresso", "cafe", 30);
				
				ajoutIngredientBoisson("cafe long", "cafe", 10);
				
				ajoutIngredientBoisson("chocolat", "chocolat", 5);
				ajoutIngredientBoisson("chocolat", "lait", 10);
				
				ajoutIngredientBoisson("cappuccino", "chocolat", 10);
				ajoutIngredientBoisson("cappuccino", "cafe", 10);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
<<<<<<< HEAD
		try {
			manager.ajoutBoisson("expresso", 4);
			manager.ajoutBoisson("cafe long", 3);
			manager.ajoutBoisson("chocolat", 2);
			manager.ajoutBoisson("cappuccino", 3);
			
	        manager.ajoutIngredient("cafe", 200);
	        manager.ajoutIngredient("lait", 200);
	        manager.ajoutIngredient("chocolat", 200);
	        manager.ajoutIngredient("sucre", 5);
			
			//manager.ajoutIngredientBoisson("expresso", "sucre", 5);
			//manager.ajoutIngredientBoisson("expresso", "cafe", 30);
>>>>>>> 73f965eb970cd1311c65b593be46ab8b2d5f61ad
			
<<<<<<< HEAD
			manager.ajoutIngredientBoisson("cafe long", "cafe", 10);
			
			manager.ajoutIngredientBoisson("chocolat", "chocolat", 10);
			
			manager.ajoutIngredientBoisson("cappuccino", "chocolat", 10);
			manager.ajoutIngredientBoisson("cappuccino", "cafe", 10);
=======
>>>>>>> c23d381833f91acb32a4abf7f363d928715c7a67
		} catch (BoissonDoublonException | MaximumBoissonAtteintException | PrixInvalideException e) {
			e.printStackTrace();
		}*/
	}
	
	public static void main(String[] args)
	{
		Main main;
		main = new Main();
		main.run();
	}
	
}
