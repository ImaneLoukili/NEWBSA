package Config;

import Fonction.Ackley;
import Fonction.Fonction;
import Fonction.Griewank;
import Fonction.Rastrigin;
import Fonction.Rosenbrock;
import Fonction.Weierstrass;

public class Configuration {

	//BSA search Initialisation
	
	public static final int nbItRun=40000;
	public static final int nbRun=30;
	public static final int popsize=50;
	public static final int dim=30;
	public static final double mixrate=0.5;
	//End of BSA search Initialisation
	
	
	public static String fctname = "Rastrigin";	//Rastrigin / Rosenbrock / Griewank / Ackley / Weierstrass
	public static Fonction fct = new Rastrigin();	
}
