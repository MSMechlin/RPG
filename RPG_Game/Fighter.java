import java.util.ArrayList;

public abstract class Fighter {

	private final String[] StatNames = {"HP","Charge","Attack","Defense","Speed"};
    protected ArrayList<Integer> Moveset; 
    protected static ArrayList<Integer> Inventory;
    protected int[] Stats;
    protected Move NextAttack;
    protected int Target;
	protected String Name; 
	protected boolean Down;
	protected double WaitTime;
	protected int alliance;
    public Fighter(String n,int[] s, ArrayList<Integer> m, ArrayList<Integer> in)
    {
        Stats = s;
        Moveset = m;
        Inventory = in;
		Name = n;
		Down = false;
    }
    abstract void choose(String[] p,String c,int b); 
    public void setMove(String[] p,String c,int b)
    {
    	choose(p,c,b);
    	setWait();
    }
    public static ArrayList<Integer> getInventory()
    {
    		return Inventory;
    }
    public int affect(int e,int i)
    {                          
      if(NextAttack instanceof Guard)
      {
        e = e/2;
      }
      int old = Stats[i];
      Stats[i] = Stats[i] - e;
      //If stat is not HP or old charge stat and it as a quarter of its default value set back to quarter
      if(Stats[i] < Stats[i+5]/4 && i>1)
      {
        System.out.println(StatNames[i] + " can not go any lower!");
        Stats[i] = Stats[i+5]/4;
      }
      //If any stat that is not HP or defunct charge stat is more than double its default value set back to double
      else if(Stats[i] > Stats[i+5]*2 && i>1)
      {
    	  Stats[i] = Stats[i+5]*2;
    	  System.out.println(StatNames[i] + " is maxed out");
      }
      //if HP is higher than max
      else if(Stats[i] > Stats[i+5] && i == 0)
      {          
        Stats[i] = Stats[i+5];
        System.out.println(StatNames[i] + " is maxed out");
      }
      else
      {
        if(e > 0)
        {
          System.out.println(Name + " lost " + e +" "+ StatNames[i]);
        }
        else if(e < 0)
        {
          System.out.println(Name + " gained " + -e +" "+ StatNames[i]); 
        }
       
      }
      if(i == 4)
      {
    	  setWait(old,Stats[i]);
      }
      return e; 
    }
    public String getName()
    {
    	
    	return Name;
    }
    public void reset()
    {
      for(int i=1;i<5;i++)
      {
        Stats[i] = Stats[i+5];
      }
      NextAttack = MovePool.getMove(0);
    }    
    public void setCharge()
    {    	
    	Stats[1]=0;
    }
    public int getStat(int i)
    {    
        return Stats[i];    
    }
    public Move getNextAtt()
    {    
        return NextAttack;        
    }
    public int getTarget()
    {
        return Target;        
    }  
    public void Charge()
    {     
        Stats[1] = Stats[1] + Stats[4]/5;   
    } 
    public void Defeat()
    {
    	Down = true;
    }
    public boolean getDown()
    {
    	return Down;
    }
    public void setWait()
    {
    	WaitTime = NextAttack.getCapacity()/Stats[4];
    }
    public void setWait(double Old,double New)
    {
    	WaitTime = (Old/New)*(WaitTime);
    }
    public double getWait()
    {
    	return WaitTime;
    }
    public void Wait(double inc)
    {
    	WaitTime -= inc;
    }
    public int getAll()
    {
    	return alliance;
    }
    public void setAll(int newAll)
    {
    	alliance = newAll;
    }
}


