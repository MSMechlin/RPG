class Event
{
	private static int[][] e = loadE();
	public static int[][] loadE()
	{
		int[][] e1 = {{6,5,8},{4},{5,5,5},{6},{7},{0,2},{8}};
		return e1;
	}
	public static int[] getEvent(int i)
	{
		return e[i];
	}
}
class Utility
{
  private static Operation[] Operations = loadOps();
  public static Operation getOps(int i)
  {
    return Operations[i];
  }
  public static Operation[] loadOps()
  {
    Operation[] r = new Operation[5];
    r[0] = new NullOp();
    r[1] = new PowXRatio();
    r[2] = new FixedDamage();
    r[3] = new PercentOfU();
    r[4] = new PercentOfT();
	 return r;    
  }
  public static void arrPrint(String[] p)
  {
	for(int i = 0;i<p.length;i++)
	{
		System.out.println(i + ". " + p[i]);
	}	
  }
}
class Story {
	  	public static void main(String[] args)
	  	{
	  		int[] a = {0,1,2};
	  		Party party = new Party(a);
	  		System.out.print("You are confronted by the king and his imp guards, prepare for a fierce combat!");	 
	  		if(party.Battle(Event.getEvent(0)))
	  		{
	  			System.out.println("You have succeeded brave warrior, and have won not only glory but peace for the coming decades!");
	  		}
	  		else
	  		{	
	  			System.out.println("You have fallen in battle! Though you in may pass in your physical form, your story will nevertheless live on!");
	  		}
	  		
	  	}
	  
  }
	

