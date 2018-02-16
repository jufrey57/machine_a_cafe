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
    public static void main(String[] args){
        Object machine = null;
        machine = Save.deserialize("./sauvegarde");
        
    }
    
}
