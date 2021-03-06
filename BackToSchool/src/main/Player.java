package main;


public class Player {
	double sciRigor;
	double creativity;
	double quantReasoning;
	
	// player starts with 1.0 GPA statistics (debatable)
	public Player(){
		sciRigor=1.0;
		creativity=1.0;
		quantReasoning=1.0;
	}
	
	// increase the statistic
	public void increaseSciRigor(double inc){
		// add only if GPA is less than a 4.0
		if(sciRigor<4.0)
			sciRigor+=inc;
		
		// if GPA was added and it is more than 4.0. Set the GPA to 4.0
		if(sciRigor>4.0)
			sciRigor=4.0;
		
		sciRigor = truncStat(sciRigor);
	}
	
	// increase the statistic
	public void increaseCreativit(double inc){
		if(creativity<4.0)
			creativity+=inc;
		
		if(creativity>4.0)
			creativity=4.0;
		
		creativity = truncStat(creativity);
	}
	
	// increase the statistic
	public void increaseQuantReasoning(double inc){
		if(quantReasoning<4.0)
			quantReasoning+=inc;
		
		if(quantReasoning>4.0)
			quantReasoning=4.0;
		
		quantReasoning = truncStat(quantReasoning);
	}
	
	//return the statistic
	public double getSciRigor(){
		return sciRigor;
	}
	
	//return the statistic
	public double getCreativity(){
		return creativity;
	}
	
	//return the statistic
	public double getQuantReasoning(){
		return quantReasoning;
	}
	
	private double truncStat(double stat)
	{
		return Math.ceil(stat * 100)/100;
	}

	public String toString()
	{
		String output = "Stats:\n";
		output += "Math: " + quantReasoning + "\n";
		output += "Science: " + sciRigor + "\n";
		output += "Creativity: " + creativity + "\n";
		return output;
	}
}
