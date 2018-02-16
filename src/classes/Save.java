package classes;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import ini.Ini;
import ini.exceptions.*;

public class Save {
	
	private final static String CHEMIN = "sauvegarde.ini";
	
	public static HashMap<String,Integer> importationIngredients() throws IniParserException, IOException {
		HashMap<String,Integer> ingredients = null;
	    Path input;
	    File file = new File(CHEMIN);
	    
	    if(	file.exists()) {
	    		input = Paths.get(CHEMIN);
		    	Ini ini = new Ini().read(input);
		    Map<String, Map<String, String>> sections = ini.getSections();
		    
		    if(sections.size() > 0 && sections.containsKey("ingredients")) {
		    		ingredients = new HashMap<String,Integer>();
		    		
		    		for(String ingredient : sections.get("ingredients").keySet()) 
		    		{
		    			ingredients.put(ingredient, Integer.parseInt(sections.get("ingredients").get(ingredient)));
		    		}
		    }
	    }
	    
	    
	    return ingredients;
	}
	
	public static ArrayList<Boisson> importationBoissons() throws IniParserException, IOException {
		ArrayList<Boisson> boissons = new ArrayList<Boisson>();
    		Path input = Paths.get(CHEMIN);
	    	Ini ini = new Ini().read(input);
	    Map<String, Map<String, String>> sections = ini.getSections();
	    
    		for(String boissonString : sections.keySet()) 
    		{
    			if(!boissonString.equals("ingredients")) 
    			{
    				Boisson boisson = new Boisson(boissonString, Integer.parseInt(sections.get(boissonString).get("prix")));
    				for(String ingredient : sections.get(boissonString).keySet())
    				{
    					boisson.modifierIngredient(ingredient, Integer.parseInt(sections.get(boissonString).get(ingredient)));
    				}
    				boissons.add(boisson);
    			}
    			
    		}
	    
	    return boissons;
	}
	
	public static void exportation(ManagerMachine machine) throws IniParserException, IOException {
		
		File file = new File (CHEMIN);
        file.delete();
        file.createNewFile();
		
        
	    Path input = Paths.get(CHEMIN);
	    Ini ini = new Ini().read(input);
	    Map<String, Map<String, String>> sections = ini.getSections();
	    
	    HashMap<String,String> ingredients = new HashMap<String,String>();
	    HashMap<String,String> elementsBoisson = new HashMap<String,String>();
	    ArrayList<Boisson> boissons = machine.getListeBoissons();
	    
	    for(String ingredient : machine.getIngredients().keySet()) {
	    		ingredients.put(ingredient, machine.getIngredients().get(ingredient).toString());
	    }
	    
	   sections.put("ingredients", ingredients);
	   
	   for(Boisson boisson : boissons) {
		   //System.out.println(boisson.getNom());
		   elementsBoisson.put("prix", String.valueOf(boisson.getPrix()));
		   for(String ingredient : boisson.getListeIngredient().keySet()) {
			   //System.out.println(ingredient +"=>"+boisson.getListeIngredient().get(ingredient).toString());
			   elementsBoisson.put(ingredient, boisson.getListeIngredient().get(ingredient).toString());
		   }
		   sections.put(boisson.getNom(), elementsBoisson);
	   }
	   
	   Path output = Paths.get(CHEMIN);
       ini.write(output);
	}
	
}
