package Fonction;

import java.util.Vector;

public class Griewank extends Fonction{

	double LowerLimit = -600;
	double UpperLimit = 600;
	@Override
	public double fitness(Vector<Double> tab) {

		return 1+Sum(tab)+Mult(tab);
		
	}
	
	public Griewank() {
		// TODO Auto-generated constructor stub
		super(-600,600);
	}
	
	private double Sum(Vector<Double> tab){
		double somme = 0;
		for (int i = 0;i<=tab.size()-1;i++){
			
			somme=somme+Math.pow(tab.get(i), 2);
			
		}
		
		somme = 1/4000 * somme;
		
		return somme;
	}
	
	private double Mult(Vector<Double> tab){
		double somme = 1;
		
		for (int i = 0;i<=tab.size() -1;i++ ){
			somme = somme * Math.cos(tab.get(i)/Math.sqrt(i+1));
		}
		
		return somme;
	}
}