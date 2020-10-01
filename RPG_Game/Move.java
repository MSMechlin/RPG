
public abstract class Move {
	protected String Script;
	protected int Capacity;
	  protected String Name;
	  protected int[][] Instructions;  
	  public Move(String n,int[][] in,int c,String s)
	  {
	    Name = n;
	    Instructions = in;
	    Capacity = c;
	    Script = s;
	  }   
	  public String getName()
	  {
	    return Name;
	  }
	  public int[][] getIns()
	  {
	    return Instructions;
	  }
	  public int getIn(int a,int b)
	  {
		  return Instructions[a][b];
	  }
	  public String getScript()
	  {
		  return Script;
	  }
	  public int getCapacity()
	  {
	    return Capacity;
	  }
	  public abstract void initiate(int s,int c);
	}
	


