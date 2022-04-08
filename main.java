package de.ipbhalle.metfrag.spectraPrediction;
import java.io.*;
import java.util.Vector;

import de.ipbhalle.metfrag.massbankParser.NewMassbankParser;
import de.ipbhalle.metfrag.massbankParser.Peak;
import de.ipbhalle.metfrag.massbankParser.Spectrum;
public class CosineSimilarity {
	public static void main(String[] args) throws IOException {
		
		if(args == null || args.length == 0) {
			System.out.println("No value given.");
			return;
		}
		File file = new File(args[0]);
		if(!file.exists() || !file.canRead()) {
			System.out.println("Can not read file.");
			return;
		}
		Vector<Spectrum> specs = NewMassbankParser.Read(args[0]);
		if(specs == null || specs.size() == 0) return;
		
		PredictedSpectrum prespec=new PredictedSpectrum(args[1]);
		
		double a=prespec.getSimilarity(specs.get(0));
	    
	   System.out.println(a);
	   
	    
	    
	    
		
	}

}

