package src;


import java.util.ArrayList;
import java.util.Scanner;

public class Main
{
	
	private static ArrayList<String> menus;
	
	private ManagerMachine manager;
	
	private Scanner sc;
	
	public Main()
	{
		menus = new ArrayList<String>();
		manager = new ManagerMachine();
		sc = new Scanner(System.in);

		// Accueil
		menus.add("-- Menu prinicpal de la machine à café --"
				+ "Quelle action souhaitez vous effectuer ?"
				+ "1) Acheter une boisson"
				+ "2) Modifier une boisson"
				+ "3) Supprimer une boisson"
				+ "4) Ajouter une boisson"
				+ "5) Vérifier le stock d'ingrédients"
				+ "6) Ajouter un ingrédient");
		
		menus.add("");

	}
	
	private Boisson choisirBoisson()
	{
		ArrayList<Boisson> boissons = manager.getListeBoissons();
		
		String listeBoissons = "N°:\tPrix \tNom \n";
		for(int i = 0; i < boissons.size(); i++) 
		{
			Boisson boisson = boissons.get(i);
			listeBoissons += i + "\t" + boisson.getPrix() + "\t" + boisson.getNom() + "\n";
		}
		
		System.out.println("Veuillez choisir votre boisson :");
		System.out.println(manager.getListeBoissons());
		
		int index = sc.nextInt();
		// FIXME: Vérifier la validité de l'index
		Boisson boisson = boissons.get(index);
		
		return boisson;
	}
	
	private void ajouterBoisson()
	{
		// Nommer la boisson
		System.out.println("Donner un nom à votre boisson");
		String nom = sc.nextLine();
		// Donner un prix à la boisson
		System.out.println("Donner un prix à votre boisson");
		int prix = sc.nextInt();
		
		Boisson boisson = manager.ajoutBoisson(nom, prix);
		
		// Afficher boisson ajoutée
		if (boisson != null)
			System.out.println("Boisson ajoutée");
		else
			System.out.println("Problème lors de l'ajout de la boisson");
		
		manager.modifierBoisson(boisson);
	}
	
	private String effectuerAction(int action)
	{
		String res = "";
		if (action > 0)
		{			
			Boisson boisson;
			if (action < 4)
				boisson = choisirBoisson();
			
			switch (action)
			{
			// Acheter une boisson
			case 1:
				manager.acheterBoisson(boisson);
				break;
			
			// Modifier une boisson	
			case 2:
				manager.modifierBoisson(boisson);
				break;
			
			// Supprimer une boisson
			case 3:
				manager.supprimerBoisson(boisson);
				break;
			
			// Ajouter une boisson
			case 4:
				this.ajouterBoisson();
				break;
			
			// Vérifier le stock d'ingrédients
			case 5:
				res = this.manager.getListeIngredients();
				System.out.println(res);
				break;
			
			// Ajouter un ingrédient à une boisson
			case 6:
				
				break;
	
			// Erreur, action inconnue
			default:
				// TODO: 
				break;
			}
		}
		else
			res = "Erreur lors de l'ajout";
		
		return res;
	}
	
	public static void main(String[] args)
	{	
		Main main = new Main();
		
		
		
		Integer res = sc.nextInt();
		
		main.effectuerAction(res);
		
		sc.close();
	}
	
}
