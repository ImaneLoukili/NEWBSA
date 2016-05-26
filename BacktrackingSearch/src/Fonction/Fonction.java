package Fonction;

import java.util.Vector;

public abstract class Fonction {
	public abstract double fitness(Vector<Double> tab);
	public double LowerLimit;
	public double UpperLimit;
	
	public Fonction(double lo,double up) {
		// TODO Auto-generated constructor stub
		LowerLimit = lo;
		UpperLimit = up;
	}
	
	
}