package Fonction;

import java.util.Vector;

public class Weierstrass extends Fonction{

	private double a;
	private double b;
	private double kmax;
	private double constant;
	double LowerLimit = -100;
	double UpperLimit = 100;
	public Weierstrass (){
		a=0.5;
		b=3.0;
		kmax=20;
		computeConstant();
	}
	
	public void setA(double a) {
		this.a = a;
		computeConstant();
	}
	
	public void setB(double b) {
		this.b = b;
		computeConstant();
	}
	
	public void setKmax(double kmax) {
		this.kmax = kmax;
		computeConstant();
	}
	
	@Override
	public double fitness(Vector<Double>tab) {
		
		double somme = 0;
		
		for(int i = 0;i<tab.size();i++){
		
			for(int k=0; k<=kmax;k++){
				somme += Math.pow(a, k) * Math.cos(2 * Math.PI * Math.pow(b, k) * (tab.get(i) + 0.5));
			}
		}
		
		return somme - tab.size() * constant;
	}
	
	private void computeConstant() {

		        double tmp = 0.0;

		        for(int k = 0; k <= kmax; k++) {

		            tmp += Math.pow(a, k) * Math.cos(2 * Math.PI * Math.pow(b, k) * 0.5);

		        }

		        constant = tmp;

	}

}