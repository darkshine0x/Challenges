package ch.jbaumann.freqanalysis.impl;

import java.util.HashMap;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import ch.jbaumann.freqanalysis.api.FrequencyAnalysis;
import ch.jbaumann.freqanalysis.factory.AnalysisType;
import ch.jbaumann.freqanalysis.factory.FrequencyAnalysisFactory;

class FrequencyAnalysisImplTest {
	
	@Test
	void testAnalyze() {
		// Arrange
		FrequencyAnalysis fa = FrequencyAnalysisFactory.get(AnalysisType.DEFAULT, "src/test/java/ch/jbaumann/freqanalysis/impl/text.txt");
		
		// Act
		HashMap<Character, Integer> map = fa.analyze();
		
		// Assert
		Assertions.assertEquals(3, map.get('a'));
		Assertions.assertEquals(1, map.get('b'));
		Assertions.assertEquals(1, map.get('c'));
		Assertions.assertEquals(1, map.get('d'));
	}

}
