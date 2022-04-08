package de.ipbhalle.metfrag.spectraPrediction;

public class PredictedPeak {
	
	private double peakMass;
	private String peakSmile;
	public PredictedPeak(){
		
	}
	public PredictedPeak(double peakMass, String peakSmile){
		this.peakMass=peakMass;
		this.peakSmile=peakSmile;
	
	}
	public double peakMas(){
		return peakMass;
	}
	public String peaksmil(){
		return peakSmile;
	}
	public String toString(){
		return peakMass+" "+peakSmile;
	}
	public int indexOfMass(){
		double rpeak =(Math.round(peakMass*10)/10.0d)*10;
		int index=(int) rpeak;
		return index;
	}


}

