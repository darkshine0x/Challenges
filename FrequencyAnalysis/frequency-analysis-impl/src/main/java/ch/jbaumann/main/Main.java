package ch.jbaumann.main;

import ch.jbaumann.freqanalysis.api.FrequencyAnalysis;
import ch.jbaumann.freqanalysis.factory.AnalysisType;
import ch.jbaumann.freqanalysis.factory.FrequencyAnalysisFactory;

public class Main {
	
	public static void main(String[] args) {
		FrequencyAnalysis fa = FrequencyAnalysisFactory.get(AnalysisType.DEFAULT, args[0]);
		System.out.println(fa.analyze());
	}

}
