package tests;

import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import classes.ManagerMachine;
import exceptions.BoissonDoublonException;
import exceptions.PrixInvalideException;


/**
 * 
 * @author Maxime
 */
class TestMachine
{

	ManagerMachine manager;
	
	@BeforeEach
	void init() throws Exception
	{
		manager = new ManagerMachine();
		
		manager.ajoutBoisson("Café", 10);
		manager.ajoutBoisson("Chocolat au lait", 12);
	}
	
	@Test
	public void testAjoutNormal() throws Exception
	{
		manager.ajoutBoisson("Mojito", 10);
	}
	
	@Test
	public void testAjoutDoublon() throws Exception
	{
		try {
			manager.ajoutBoisson("Café", 10);
			fail("Une exception aurait du être lancée");
		}
		catch (BoissonDoublonException e)
		{
		}
	}
	
	@Test
	public void testPrixNégatif() throws Exception
	{
		try {
			manager.ajoutBoisson("Chocolat", -10);
			fail("Une exception aurait du être lancée");
		}
		catch (PrixInvalideException e)
		{
		}
	}

}
