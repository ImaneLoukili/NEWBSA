package Fonction;

import java.util.Vector;

public class Rosenbrock extends Fonction{
	double LowerLimit = -2.048;
	double UpperLimit = 2.048;
	@Override
	public double fitness(Vector<Double> tab) {
		
		double Somme =0;
		
		for(int i = 0;i<tab.size()-1;i++){
			
		
			Somme=Somme+100*Math.pow((tab.get(i+1)-Math.pow(tab.get(i), 2)),2)+Math.pow((1-tab.get(i)),2);
			
		}
		
		return Somme;
	}
}