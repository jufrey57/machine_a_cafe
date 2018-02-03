package classes;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author julien
 */
public class MachineACafe {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ManagerMachine machine = new ManagerMachine();
        
        machine.ajoutBoisson("cafe latte", 4);
        machine.ajoutBoisson("cafe creme", 3);
        machine.ajoutBoisson("chocolat", 2);

        machine.ajoutIngredientBoisson("chocolat", "chocolat", 20);
        machine.ajoutIngredientBoisson("chocolat", "sucre", 40);
        
        System.out.println(machine.getBoisson("chocolat").getListeIngredient());
    }
    
}
