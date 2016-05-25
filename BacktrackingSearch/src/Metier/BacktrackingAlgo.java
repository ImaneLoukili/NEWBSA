package Metier;

import java.util.Random;
import java.util.Vector;

import Config.Configuration;
import Fonction.Fonction;

public class BacktrackingAlgo {
	private double _upper_cost;
	private double _lower_cost;
	private Fonction fct;
	private double globalMin;
	private int randomMutants;
	private int tempMut;
	double min;
	double max;
	double fitBest;
	int i, j;
	int indexBest;
	int indexWorst;
	int id;
	
	Vector<Double>  TabLine = new Vector<Double>();
	double[][]oldPop=new double[Configuration.popsize][Configuration.dim];
	double[][]P=new double[Configuration.popsize][Configuration.dim];
	double[][]Mutant=new double[Configuration.popsize][Configuration.dim];
	double[][]Map=new double[Configuration.popsize][Configuration.dim];
	double[][]T=new double[Configuration.popsize][Configuration.dim];
	Vector<Double> fitP=new Vector<Double>();
	private Vector<Double> globalMinimizer=new Vector<Double>();
	private Vector<Integer> mutT=new Vector<Integer>();
	Vector<Double> fitT=new Vector<Double>();
	

	
	
	
	
	public  double BacktrackingAlgoMain() {
		//Initialisation
		
		globalMin=Double.MAX_VALUE;
//		_lower_cost=fct.fitness(TabLine);
//		_upper_cost=_lower_cost;
		for(int i=0;i<Configuration.popsize;i++){
			for(int j=0;j<Configuration.dim;j++){
				P[i][j]=random(fct.UpperLimit, fct.LowerLimit);
				oldPop[i][j]=random(fct.UpperLimit, fct.LowerLimit);
				TabLine.setElementAt(P[i][j], j);
			}
			fitP.setSize(Configuration.popsize); 
			fitP.setElementAt(fct.fitness(TabLine), i);
		}
		
			
		
		for(int r=1;r<Configuration.nbItRun;r++){
			min=fitP.elementAt(0);
			max=fitP.elementAt(0);
			int flag=0;
			for(i=0;i<Configuration.popsize;i++){
				
				if(fitP.elementAt(i)==fitP.elementAt(0)){
					_lower_cost=fitP.elementAt(0);
					_upper_cost=fitP.elementAt(0);
					flag=1;
					id=i;
				}
				else if(fitP.elementAt(i)<min){
					_lower_cost=fitP.elementAt(i);
					min=best_cost();
					flag=2;
					id=i;
				}
				else if(fitP.elementAt(i)>max){
					_upper_cost=fitP.elementAt(i);
					max=worst_cost();
					flag=3;
					id=i;
				}
				else {}
				if (flag == 1)
				{
					indexBest=id+1;
					indexWorst=id+1;
					
				}
				else if (flag == 2)
				{
					indexBest = id + 1;
				}
				else if (flag == 3)
				{
					indexWorst = id + 1;
				}
				else{}
				
			}
			
			
			
			//Selection1

			Random rnd=new Random();
			double a=rnd.nextDouble();
			double b=rnd.nextDouble();
			double c=rnd.nextDouble();
			double d=rnd.nextDouble();
			for (int i = 0; i < Configuration.popsize; i++)
			{
				for (int j = 0; j <Configuration.dim; j++)
				{
			if (a < b)
			{
				oldPop[i][j] = P[i][j];
			}

			int randA=rnd.nextInt(Configuration.popsize-1);
			int randB=rnd.nextInt(Configuration.popsize-1);

			double temp;
			
			temp=oldPop[randA][j];
			oldPop[randA][j]=oldPop[randB][j];
			oldPop[randB][j]=temp;
		}
			}
			
			//Mutation 
			double F;
			
			
			for (int i = 0; i < Configuration.popsize; i++)
			{
				for (int j = 0; j < Configuration.dim; j++)
				{
					F = 3 * rnd.nextDouble();
					Mutant[i][j] = P[i][j] + ((oldPop[i][j] - P[i][j]) * F);
				}
			}
			
			//Crossover
			
			for (int i = 0; i < Configuration.popsize; i++)
			{
				for (int j = 0; j < Configuration.dim; j++)
				{
					Map[i][j] = 1;
				}
			}
			
			randomMutants = (int)Math.ceil(Configuration.mixrate *Configuration.dim * a);
			
			if (c < d)
			{
				for (int i = 0; i < Configuration.popsize; i++)
				{
					
					tempMut =rnd.nextInt(Configuration.dim-1);
					for (int j = 1; j <= randomMutants; j++)
					{
						while (Exists(tempMut)&&mutT!=null)
						{
							tempMut =rnd.nextInt(Configuration.dim-1);
						}
						mutT.setElementAt(tempMut, j);
						int m=mutT.get(j);
						Map[i][m]=0;
					}
				}
			}
			////////////////////////////
			
			else{
				int randi;
				for(int i=0;i<Configuration.popsize;i++){
					randi=rnd.nextInt(Configuration.dim-1);  // else petite (Pseudo-code)
					Map[i][randi]=0;
					
				}	
			}
			/////////  
			for (int i = 0; i < Configuration.popsize; i++)
			{
				for (int j = 0; j < Configuration.dim; j++)
				{
					T[i][j] = Mutant[i][j];
				}
			}
			
			for (int i = 0; i < Configuration.popsize; i++)
			{
				for (int j = 0; j < Configuration.dim; j++)
				{
					if (Map[i][j] == 1)
					{
						T[i][j] = P[i][j];
					}
				}
			}
			
//Boundary Control
			for (int i = 0; i < Configuration.popsize; i++)
			{
				TabLine.clear();

				for (int j = 0; j < Configuration.dim; j++)
				{
					if (T[i][j] < fct.LowerLimit || T[i][j] > fct.UpperLimit)
					{
						T[i][j] = fct.LowerLimit + (fct.UpperLimit - fct.LowerLimit) * rnd.nextDouble();
					}
					TabLine.add(T[i][j]);
					
				}
				fitT.setSize(Configuration.popsize);
				fitT.setElementAt(fct.fitness(TabLine), i);
			}
//Selection 2
			for (int i = 0; i < Configuration.popsize; i++)
			{
				for (int j = 0; j < Configuration.dim; j++)
				{

					if (fitT.elementAt(i) < fitP.elementAt(i))
					{
						P[i][j] = T[i][j];
						fitP.setElementAt(fitT.elementAt(i), i);
					}
				}

			}
			
			fitBest = best_cost();
			
			if (fitBest <= globalMin)
			{
				globalMin = fitBest;
				for (i = 0; i < Configuration.popsize; i++)
				{
					for (j = 0; j < Configuration.dim; j++)
					{
						if (i == indexBest)
						{
							globalMinimizer.setElementAt(P[i][j], j);
						}
					}
				}
			}
			
			
		}
		return globalMin;
	}
	
	
	public double random(double upper, double lower) {
		Random rnd = new Random();		
			return (lower + (upper - lower) * rnd.nextDouble());
		}
	public boolean Exists(int tempM)
	{
		int j;
		for (j = 1; j <= randomMutants; j++)
		{
			if (mutT.elementAt(j).equals(tempM))
				return true;
		}
		return false;
	}
	public double best_cost(){
		return _lower_cost;
	}
	public double worst_cost(){
		return _upper_cost;
	}
	
}
