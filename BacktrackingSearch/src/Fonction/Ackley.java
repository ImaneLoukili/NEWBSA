package Fonction;

import java.util.Vector;

public class Ackley extends Fonction{

			double LowerLimit = -32.768;
			double UpperLimit = 32.768;
	private int a = 20;
	private double b= 0.2;
	private double c = Math.PI; 

	@Override
	public double fitness(Vector<Double> tab) {
		// TODO Auto-generated method stub
		int d = tab.size();
		double sum1 = 0;
		double sum2 = 0;
		for (int i = 0; i < d-1; i++) {
		sum1 += Math.pow(tab.get(i), 2);
		sum2 += Math.cos(c*tab.get(i));
		}
		return  - a*Math.exp(-b*Math.sqrt(sum1/d)) - Math.exp(sum2/d) + a +Math.E;
	}
}
	
	
	