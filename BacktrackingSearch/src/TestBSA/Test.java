package TestBSA;

import java.io.File;
import java.io.FileWriter;

import Config.Configuration;
import Metier.BacktrackingAlgo;

public class Test {
	public static double[] BestFit = new double[Configuration.nbRun];

	public static void main(String[] args) {
		BacktrackingAlgo bsa = new BacktrackingAlgo();
		for(int i=0;i<Configuration.nbRun;i++){
			double best =bsa.BacktrackingAlgoMain();
			BestFit[i] = best;
		}
		
		try {
			File f = new File(Configuration.fctname + ".txt");
			FileWriter fw = new FileWriter(f);
			for (int i = 0; i < Configuration.nbRun; i++) {
				fw.write(String.valueOf(BestFit[i]) + "\n");
			}
			fw.write("\n\n");
			fw.write("Moyenne : " + CalculMoyenne(BestFit) + "\n");
			fw.write("Variance : " + CalculVariance(BestFit) + "\n");
			fw.write("Ecart Type : " + CalculET(BestFit) + "\n");
			fw.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	

	public static double CalculET(double[] tab) {
		double Variance = CalculVariance(tab);
		return Math.sqrt(Variance);
	}

	public static double CalculVariance(double[] tab) {
		double Moyenne = CalculMoyenne(tab);
		double ET = 0;
		for (int i = 0; i < tab.length; i++) {
			ET += Math.pow(tab[i] - Moyenne, 2);
		}
		return ET / (tab.length - 1);
	}

	public static double CalculMoyenne(double[] tab) {
		double Moyenne = 0;
		for (int i = 0; i < tab.length; i++) {
			Moyenne += tab[i];
		}
		return Moyenne / tab.length;
	}

}
