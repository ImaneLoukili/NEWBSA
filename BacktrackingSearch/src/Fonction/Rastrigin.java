package Fonction;

import java.util.Vector;

public class Rastrigin extends Fonction{
	double LowerLimit = -5.12;
	double UpperLimit = 5.12;
	
	public Rastrigin() {
		super(-5.12, 5.12);
		// TODO Auto-generated constructor stub
	}
	@Override
	public double fitness(Vector<Double>tab) {
		int d = tab.size();
		int A = 10;
		double sum = 0;
		for (int i = 0; i < d; i++) {
		sum += Math.pow(tab.get(i), 2) - A*Math.cos(2*Math.PI*tab.get(i));
		}
		return A*d + sum;
	}
}