package ch.jbaumann.freqanalysis.impl;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;

import ch.jbaumann.freqanalysis.api.FrequencyAnalysis;

public class FrequencyAnalysisImpl implements FrequencyAnalysis {
	
	public String path;
	
	public FrequencyAnalysisImpl(String path) {
		this.path = path;
	}
	
	public HashMap<Character, Integer> analyze() {
		HashMap<Character, Integer> map = new HashMap<>();
		try (BufferedReader r = new BufferedReader(new InputStreamReader(new FileInputStream(this.path), StandardCharsets.UTF_8))) {
			int charInt;
			while ((charInt = r.read()) != -1) {
				char sign = (char) charInt;
				if (sign == '\n' || sign == '\r') {
					continue;
				}
				if (map.containsKey(sign)) {
					map.put(sign, map.get(sign) + 1);
					continue;
				}
				map.put(sign, 1);
			}
			return map;
						
		} catch (IOException i) {
			System.out.println("Could not read or find file");
			return map;
		}
	}

}
