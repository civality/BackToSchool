package main;


public class Day {
	public enum Course {HUMANITIES, MATH, SCIENCE};
	private int day;
	private double midtermScore;
	private boolean humMidterm, mathMidterm, sciMidterm, finalBattle;
	private boolean humAttend, mathAttend, sciAttend;
	private Course nextCourse;
	
	public Day(int d)
	{
		day=d-1;
		nextDay();		
	}
	
	private void nextDay()
	{
		//TODO: store data from completed day if wanted
		
		day++;
		
		humMidterm = (day==4)? true : false;
		mathMidterm = (day==5) ? true : false;
		sciMidterm = (day==6) ? true : false;
		finalBattle = (day==10) ? true : false;
		
		humAttend = mathAttend = sciAttend = false;
		
		nextCourse = Course.HUMANITIES;
	}
	
	public int getDay()
	{
		return day;	
	}
	
	public Course getNextCourse()
	{
		return nextCourse;
	}
	
	public String getNextCourseName()
	{
		switch (nextCourse)
		{
			case HUMANITIES:
				return "Humanities";
			case MATH: 
				return "Math";
			default: 
				return "Science";
		}
	}
	
	public void attendClass()
	{
		switch (nextCourse)
		{
			case HUMANITIES:
				humAttend = true;
				nextCourse = Course.MATH;
				break;
			case MATH:
				mathAttend = true;
				nextCourse = Course.SCIENCE;
				break;
			case SCIENCE:
				sciAttend = true;
				nextDay();
				break;
		}
	}
	
	public void missClass()
	{
		switch (nextCourse)
		{
			case HUMANITIES:
				nextCourse = Course.MATH;
				break;
			case MATH:
				nextCourse = Course.SCIENCE;
				break;
			case SCIENCE:
				nextDay();
				break;
		}
	}
	
	public boolean isMidtermNext()
	{
		switch (nextCourse)
		{
			case HUMANITIES:
				return humMidterm;
			case MATH:
				return  mathMidterm;
			default:
				return sciMidterm;
		}
	}
	
	public boolean isHumMidtermDay()
	{
		return humMidterm;
	}
	
	public boolean isMathMidtermDay()
	{
		return mathMidterm;
	}
	
	public boolean isSciMidtermDay()
	{
		return sciMidterm;
	}
	
	public boolean isFinalDay()
	{
		return finalBattle;
	}
}