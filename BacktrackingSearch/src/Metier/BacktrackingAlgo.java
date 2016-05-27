package Metier;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;
import java.util.Vector;

import Config.Configuration;
import Fonction.Fonction;

public class BacktrackingAlgo {
	private double _upper_cost;
//	private double _lower_cost;
	private Fonction fct = Config.Configuration.fct;
	private double globalMin;
	private int randomMutants;
	//private int tempMut;
	double min;
	double max;
	double[] fitBest=new double[Configuration.nbItRun];
	int i, j;
	int indexBest;
	int indexWorst;
	int id;
	double UpperLimit = fct.UpperLimit;
	double LowerLimit = fct.LowerLimit;
	Vector<Double> TabLine = new Vector<Double>();
	double[][] oldPop = new double[Configuration.popsize][Configuration.dim];
	double[][] P = new double[Configuration.popsize][Configuration.dim];
	double[][] Mutant = new double[Configuration.popsize][Configuration.dim];
	double[][] Map = new double[Configuration.popsize][Configuration.dim];
	double[][] T;
	Vector<Double> fitP = new Vector<Double>(Configuration.popsize);
	private Vector<Double> globalMinimizer = new Vector<Double>();
	private Vector<Integer> mutT = new Vector<Integer>();
	Vector<Double> fitT = new Vector<Double>(Configuration.popsize);

	public Vector<Double> Tri(Vector<Double> amodifier) {

		Double[] Intermediaire = new Double[amodifier.size()];
		for (int i = 0; i < amodifier.size(); i++) {
			Intermediaire[i] = amodifier.get(i);
		}
		Arrays.sort(Intermediaire, new Comparator<Double>() {

			public int compare(Double o1, Double o2) {
				// TODO Auto-generated method stub
				return (o1 < o2) ? -1 : (o1 > o2) ? 1 : 0;
			}

		});
		amodifier.clear();
		for (Double d : Intermediaire) {
			amodifier.add(d);
		}
		return amodifier;
	}

//	public Vector<Double> GetBestValues(Vector<Double> fitP,Vector<Double> fitT){
//		Vector<Double> Intermediaire = new Vector<Double>();
//		for(Double d : fitP){
//			Intermediaire.add(d);
//		}
//		for(Double d : fitT){
//			Intermediaire.add(d);
//		}
//		
//		Intermediaire = Tri(Intermediaire);
//		
//		Vector<Double> Retour = new Vector<Double>();
//		
//		for(int i = 0; i<Config.Configuration.popsize;i++){
//			Retour.add(Intermediaire.elementAt(i));
//		}
//		return fitP;
//	}
	
	public double BacktrackingAlgoMain() {
		// Initialisation

		globalMin = Double.MAX_VALUE;
		//_lower_cost = fct.fitness(TabLine);
		//_upper_cost = _lower_cost;
		for (int i = 0; i < Configuration.popsize; i++) {
			TabLine.clear();
			for (int j = 0; j < Configuration.dim; j++) {
				P[i][j] = random(UpperLimit, LowerLimit);
				oldPop[i][j] = random(UpperLimit, LowerLimit);
				TabLine.add(P[i][j]);
			}
			//System.out.println(fct.fitness(TabLine));
			fitP.add(fct.fitness(TabLine));
		}
		
		//fitP = Tri(fitP);
		//System.out.println(fitP);
		//System.out.println(fitP.elementAt(0));
//		min = fitP.elementAt(0);
//		max = fitP.elementAt(fitP.size() - 1);
//		indexBest = 0;
//		indexWorst = fitP.size() - 1;

		for (int r = 0; r < Configuration.nbItRun; r++) {

			

			// Selection1

			Random rnd = new Random();
			double a = rnd.nextDouble();
			double b = rnd.nextDouble();
			double c = rnd.nextDouble();
			double d = rnd.nextDouble();
			if (a < b) {
				oldPop = P.clone();
			}
			double[] temp;
		
			for (int i = 0; i < Configuration.popsize; i=i+2) {
				
					

		// la permutation ne doit peut etre pa se faire with random , on doit juste permuter 
		//les individus de old p 2 par 2 !!
					
					temp=oldPop[i+1];
//					temp = oldPop[randA][j];
					oldPop[i+1] = oldPop[i];
					oldPop[i] = temp;
				}
			

			// Mutation
			double F =3 * rnd.nextDouble();;
			for (int i = 0; i < Configuration.popsize; i++) {
				for (int j = 0; j < Configuration.dim; j++) {
					
					Mutant[i][j] = P[i][j] + ((oldPop[i][j] - P[i][j]) * F);
				}
			}

			// Crossover

			for (int i = 0; i < Configuration.popsize; i++) {
				for (int j = 0; j < Configuration.dim; j++) {
					Map[i][j] = 1;
				}
			}

			randomMutants = (int) Math.ceil(Configuration.mixrate * Configuration.dim * a);

			if (c < d) {
				for (int i = 0; i < Configuration.popsize; i++) {
					//
					// tempMut =rnd.nextInt(Configuration.dim-1);
					// for (int j = 0; j < randomMutants; j++)
					// {
					// while (Exists(tempMut)&&mutT!=null)
					// {
					// tempMut =rnd.nextInt(Configuration.dim-1);
					// }
					// mutT.setElementAt(tempMut, j);
					// int m=mutT.get(j);
					
					Map[i][randomMutants] = 0;
					// }
				}
			}
			////////////////////////////

			else {
				int randi;
				for (int i = 0; i < Configuration.popsize; i++) {
					randi = rnd.nextInt(Configuration.dim - 1); // else petite
																// (Pseudo-code)
					Map[i][randi] = 0;

				}
			}
			
			T = Mutant.clone();

			for (int i = 0; i < Configuration.popsize; i++) {
				for (int j = 0; j < Configuration.dim; j++) {
					if (Map[i][j] == 1) {
						T[i][j] = P[i][j];
					}
				}
			}
			
			fitT.clear();
			// Boundary Control
			for (int i = 0; i < Configuration.popsize; i++) {
				TabLine.clear();

				for (int j = 0; j < Configuration.dim; j++) {
					if (T[i][j] < LowerLimit || T[i][j] > UpperLimit) {
						T[i][j] = LowerLimit + (UpperLimit - LowerLimit) * rnd.nextDouble();
					}
					TabLine.add(T[i][j]);

				}
				fitT.add(fct.fitness(TabLine));
			}
			
//			fitP = GetBestValues(fitP,fitT);
//			_lower_cost = fitP.elementAt(0);
//			_upper_cost = fitP.elementAt(fitP.size()-1);
//			System.out.println(fitP.elementAt(fitP.size()-1));
//			min = fitP.elementAt(0);
//			max = fitP.elementAt(fitP.size() - 1);
//			indexBest = 0;
//			indexWorst = fitP.size() - 1;
			// Selection 2
//			for (int i = 0; i < Configuration.popsize; i++) {
//				for (int j = 0; j < Configuration.popsize; j++) {
//
//					if (fitT.elementAt(i) < fitP.elementAt(j)) {
//						double X = fitP.elementAt(j);
//						fitP.setElementAt(fitT.elementAt(i), j);
//						fitT.setElementAt(X, i);
//						double[] Inter = P[j].clone();
//						P[j] = T[i].clone();
//						T[i] = Inter.clone();
//						j=0;
//						i=0;
//					}
//				}
//
//			}
			for(int i=0;i<Configuration.popsize;i++){
				if(fitT.elementAt(i) < fitP.elementAt(i)){
					fitP.setElementAt(fitT.elementAt(i), i);
					P[i] = T[i].clone();
				}
			}

			fitBest[i] = best_cost(fitP,indexBest);

			if (fitBest[i] <= globalMin) {
				globalMin = fitBest[i];
				for (int i = 0; i < Configuration.popsize; i++) {
					for (int j = 0; j < Configuration.dim; j++) {
						if (i == indexBest) {
							globalMinimizer.add(P[i][j]);
						}
					}
				}
			}
			fitT.clear();

			System.out.println("Iteration : " + r + " -> Best Value : " + fitBest[i]);
			//Recalibrate();
		}
		fitT.clear();
		fitP.clear();
		return globalMin;
	}

	public double random(double upper, double lower) {
		return (lower + (upper - lower) * Math.random());
	}

	public boolean Exists(int tempM) {
		int j;
		for (j = 0; j < randomMutants; j++) {
			if (mutT.elementAt(j).equals(tempM))
				return true;
		}
		return false;
	}

	public double best_cost(Vector<Double> fitP,int bestVal) {
		Double Best = fitP.elementAt(0);
		bestVal=0;
		for(int i=1;i<fitP.size();i++){
			if(fitP.elementAt(i) < Best)
			{
				Best = fitP.elementAt(i);
				bestVal = i;
			}
			
		}
		return Best;
	}

	
//	public void Recalibrate(){
//		UpperLimit = UpperLimit*0.995;
//		LowerLimit = LowerLimit*0.995;
//	}
	public double worst_cost() {
		return _upper_cost;
	}
}