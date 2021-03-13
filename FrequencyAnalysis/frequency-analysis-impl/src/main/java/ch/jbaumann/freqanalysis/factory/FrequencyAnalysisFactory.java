package ch.jbaumann.freqanalysis.factory;

import ch.jbaumann.freqanalysis.api.FrequencyAnalysis;
import ch.jbaumann.freqanalysis.impl.FrequencyAnalysisImpl;

public class FrequencyAnalysisFactory {
	
	private FrequencyAnalysisFactory() {
		
	}
	
	public static FrequencyAnalysis get(AnalysisType type, String path) {
		switch (type) {
		case DEFAULT:
			return new FrequencyAnalysisImpl(path);
		}
		return null;
	}

}
