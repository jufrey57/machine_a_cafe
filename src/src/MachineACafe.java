package src;
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
        
        machine.ajoutBoisson("cafe latte", 100);
        machine.ajoutBoisson("cafe latte", 100);
        //System.out.println(machine.getQuantiteIngredient("coke"));
        
        System.out.println(machine.getListeBoissons());
    }
    
}
