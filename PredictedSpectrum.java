package de.ipbhalle.metfrag.spectraPrediction;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Vector;

import de.ipbhalle.metfrag.massbankParser.NewMassbankParser;
import de.ipbhalle.metfrag.massbankParser.Peak;
import de.ipbhalle.metfrag.massbankParser.Spectrum;

public class PredictedSpectrum {
	
   
	private Vector<PredictedPeak> predictedspec=new Vector<PredictedPeak>();
	/* constructor for initializing objects  Reading predicted spectra files 
	 * and splitting each line of spectra and storing them /into vector of type 
	 * predicted peak.
	 */
	public PredictedSpectrum(String x) throws IOException{
		
			BufferedReader br = new BufferedReader(new FileReader(x));

			String line;
	        
			while ((line = br.readLine()) != null)
			{
				 String delime="[ ]+";
				 String[] tokens=line.split(delime);
				 PredictedPeak objPrePeak=new PredictedPeak(Double.parseDouble(tokens[0]),tokens[1]);
				 predictedspec.add(objPrePeak);

			}
			
	         
	}
	// Method the returns Vector of type predicted peak.
	public Vector<PredictedPeak> getPreSpec(){
		
		
		return predictedspec;
	}
	// method the prints the Vector of Predicted spectra 
	public void printPreSpec(){
		for(int i=0;i<predictedspec.size();i++){
			System.out.println(predictedspec.get(i));
		}
	}
	/*Method that gets mass spectra from mass bank class of type spectrum and
	 *  convert it into vector of Index of type integer. same it do with predicted spectra peak masses. 
	 *  after conversion into index, passes both vectors to method for comparison 
	*/
	public double getSimilarity(Spectrum massbankSpec) {
		Vector <Peak> peaksMass=massbankSpec.getPeaks();
		Vector<Double> massVec=new Vector<Double>();
	    Vector<Integer> massVecInt2=new  Vector<Integer>();
	    for (int i=0;i<peaksMass.size();i++){
	    massVec.add((peaksMass.get(i).getMass()));
	    }
	    Vector<Integer> massVecInt1=covertIntVector(massVec);
	    for (int i=0;i<predictedspec.size();i++){
	    	int j=predictedspec.get(i).indexOfMass();
	    	if (!massVecInt2.contains(j)){
	    		massVecInt2.add(j);
	    	}
	    }
	   /* printVec(massVecInt1);
	    System.out.println("###########");
	    printVec(massVecInt2);*/
	   
	    double z=cosSimilaritySpec(massVecInt1,massVecInt2 ,0 );
	    return z;
	}
	/* methods for the determination of cosine similarity of predicted and mass bank (Measured spectra)
	 * */
	private static int peakSimilarity(int x, int y, int deviation){
		if (Math.abs(x-y)>deviation){
			return 0;
			
		}else{
		
		return 1;
		}
	}



	private static double cosSimilaritySpec(Vector <Integer> preVec, Vector <Integer> massVec, int deviation)
	{

		
		int z=0;
		for (int i=0;i<preVec.size();i++)  {
			for (int j=0;j<massVec.size();j++){
				if (peakSimilarity(preVec.get(i),massVec.get(j), deviation )==1){
					z+=1;
					break;
				}
			}
		}
		double a= preVec.size()*massVec.size(); 
		double sqrA=Math.sqrt(a); 
		double result=Math.acos((z/sqrA));
		//scale down the results into range of 0-1 rather than in range if 0 to 1.57.
		double b=(Math.PI)/2;
		
		double c=(b-result)/b;
		return c;
		
		
	}
	private static Vector<Integer> covertIntVector(Vector<Double> mases){
		
		Vector <Double> v1=new Vector <Double>();
		Vector <Integer> v2=new Vector <Integer>();
		for (int i=0;i<mases.size();i++)
		{
			double rpeak =(Math.round(mases.get(i)*10)/10.0d)*10;
			v1.add(rpeak);
	      //System.out.println(v1.get(i));
		}
		for (int j=0;j<v1.size();j++)
		{
			double y= v1.get(j);
			int z=(int) y;
			if (!v2.contains(new Integer(z)))
			{
				v2.add(z);
				
			}
		}
		return v2;
	}
	
	private static void printVec(Vector<Integer> vec) {
		for(int i = 0; i < vec.size(); i++)
			System.out.println(vec.get(i));
	}
}

