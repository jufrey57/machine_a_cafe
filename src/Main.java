

import java.util.ArrayList;
import java.util.Scanner;

public class Main
{
	
	private static ArrayList<String> menus;
	
	private ManagerMachine manager;
	
	public Main()
	{
		menus = new ArrayList<String>();
		manager = new ManagerMachine();

		// Accueil
		menus.add("-- Menu prinicpal de la machine à café --"
				+ "Quelle action souhaitez vous effectuer ?"
				+ "1) Acheter une boisson"
				+ "2) Modifier une boisson"
				+ "3) Supprimer une boisson"
				+ "4) Ajouter une boisson"
				+ "5) Vérifier le stock d'ingrédients"
				+ "6) Ajouter un ingrédient");

	}
	
	private String effectuerAction(int action)
	{
		String res = "";
		
		switch (action)
		{
		// Acheter une boisson
		case 1:
			//this.manager.acheterBoisson();
			break;
		
		// Ajouter une boisson
		case 2:
			break;
		
		// Modifier une boisson	
		case 3:
			break;
		
		// Supprimer une boisson
		case 4:
			break;
		
		// Ajouter un ingrédient à une boisson
		case 5:
			break;
		
		// Vérifier le stock d'ingrédients
		case 6:
			res = this.manager.getListeIngredients();
			break;

		// Erreur, action inconnue
		default:
			// TODO: 
			break;
		}
		
		return res;
	}
	
	public static void main(String[] args)
	{	
		Main main = new Main();
		
		Scanner sc = new Scanner(System.in);
		
		Integer res = sc.nextInt();
		
		main.effectuerAction(res);
		
		sc.close();
	}
	
}
