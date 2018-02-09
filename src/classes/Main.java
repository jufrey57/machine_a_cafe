package classes;

import java.io.IOException;
/**
 * 
 * @author Maxime
 */
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import exceptions.BoissonDoublonException;
import exceptions.MaximumBoissonAtteintException;
import exceptions.PrixInvalideException;

public class Main
{
	/** ArrayList<String> Contient les lignes du menus **/
	private static ArrayList<String> menus;
	
	/** ManagerMachine Contient les  **/
	private ManagerMachine manager;
	
	private Scanner sc;
	// TODO: Faire un sytème de log
	// TODO: Utiliser un enum ou un moyen propre de stocker les infos du menu
	// TODO: Changer le toString de machinemanager
	
	public Main()
	{
		menus = new ArrayList<String>();
		manager = new ManagerMachine();
		sc = new Scanner(System.in);
		this.init();

		// Accueil
		menus.add("-- Menu prinicpal de la machine à café --\n"
				+ "Quelle action souhaitez vous effectuer ?\n"
				+ "1) Acheter une boisson\n"
				+ "2) Modifier une boisson\n"
				+ "3) Supprimer une boisson\n"
				+ "4) Ajouter une boisson\n"
				+ "5) Vérifier le stock d'ingrédients\n"
				+ "6) Ajouter un ingrédient\n"
				+ "7) Quitter");
		
		menus.add("");

	}
	
	private Boisson choisirBoisson()
	{
		System.out.println("Veuillez saisir le numéro de la boisson souhaitée :");
		System.out.println(manager);
		
		int index = lireEntier() - 1;
		Boisson boisson = manager.getBoisson(index);
		
		return boisson;
	}
	
	private void ajouterBoisson()
	{
		// Nommer la boisson
		System.out.println("Donner un nom à votre boisson");
		String nom = sc.nextLine();
		// Donner un prix à la boisson
		System.out.println("Donner un prix à votre boisson");
		int prix = lireEntier();
		
		//Boisson boisson = manager.ajoutBoisson(nom, prix);
		
		// Afficher boisson ajoutée
		if (boisson != null)
			System.out.println("Boisson ajoutée");
		else
			System.out.println("Problème lors de l'ajout de la boisson");
		
		//manager.modifierBoisson(boisson);
	}
	
	private String effectuerAction(int action)
	{
		String res = "";
		if (action > 0)
		{
			Boisson boisson;
			
			switch (action)
			{
			// Acheter une boisson
			case 1:
				boisson = choisirBoisson();
				System.out.println("Veuillez insérer de la monnaie svp");
				int argent = lireEntier();
				
				if (argent <= 0)
					System.out.println("Error : Veuillez insérer de la monnaie");
				else
				{			
					Integer reste = manager.acheterBoisson(boisson, argent);
					
					if (reste == null)
						System.out.println("Erreur lors de la préparation, veuillez récupérer votre monnaie " + argent);
	
					else if (reste < 0)
						System.out.println("Erreur : Fonds insuffisants");
					
					else if (reste > 0)
						System.out.println("Veuillez récupérez votre monnaie : " + reste);
					
					else if (reste == 0)
						System.out.println("Merci, dégustez bien votre breuvage");
					
					break;
				}
				
				// Modifier une boisson	
			case 2:
				//manager.modifierBoisson(boisson);
				break;
				
				// Supprimer une boisson
			case 3:
				//manager.supprimerBoisson(boisson);
				break;
				
				// Ajouter une boisson
			case 4:
				this.ajouterBoisson();
				break;
				
				// Vérifier le stock d'ingrédients
			case 5:
				res = this.manager.getListeIngredients();
				System.out.println(res);
				System.out.println("Appuyer sur entrée pour revenir au menu principal");
				try {
					System.in.read();
				} catch (IOException e) {
					e.printStackTrace();
				}
				break;
				
				// Ajouter un ingrédient à une boisson
			case 6:
				
				break;
				
			case 7:
				System.out.println("Merci de votre visite");
				System.exit(0);
				break;
				
				// Erreur, action inconnue
			default:
				System.out.println("valeur non définie");
				break;
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
		int argent = 0;
		do {
			System.out.println(menus.get(0));
			res = lireEntier();
			effectuerAction(res);
		} 
		while (true);
	}
	
	public void init()
	{
		try {
			manager.ajoutBoisson("expresso", 4);
			manager.ajoutBoisson("cafe long", 3);
			manager.ajoutBoisson("chocolat", 2);
			manager.ajoutBoisson("cappuccino", 3);
		} catch (BoissonDoublonException | MaximumBoissonAtteintException | PrixInvalideException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args)
	{	
		Main main;
		main = new Main();
		main.run();
	}
	
}
