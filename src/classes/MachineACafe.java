package classes;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import exceptions.BoissonDoublonException;
import exceptions.MaximumBoissonAtteintException;
import exceptions.PrixInvalideException;

/**
 *
 * @author julien
 */
public class MachineACafe {

    /**
     * @param args the command line arguments
     * @throws PrixInvalideException 
     * @throws MaximumBoissonAtteintException 
     * @throws BoissonDoublonException 
     */
    public static void main(String[] args) throws BoissonDoublonException, MaximumBoissonAtteintException, PrixInvalideException {
        ManagerMachine machine = new ManagerMachine();
        
        machine.ajoutBoisson("cafe latte", 4);
        machine.ajoutBoisson("cafe creme", 3);
        machine.ajoutBoisson("chocolat", 2);

        machine.ajoutIngredientBoisson("chocolat", "chocolat", 20);
        machine.ajoutIngredientBoisson("chocolat", "sucre", 40);
        
        machine.ajoutIngredientBoisson("cafe latte", "sucre", 40);
        
        System.out.println(machine.getListeIngredients());
        
        System.out.println(machine.acheterUneBoisson("cafe latte", 5));
        
        System.out.println(machine.modifierIngredient("sucre", 50));
        
        System.out.println(machine.getListeIngredients());
    }
    
}
