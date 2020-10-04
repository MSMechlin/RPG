

public class Bash extends Move {

	  public Bash(String n,int[][] in, int c,String s)
	  {
	    super(n,in,c,s);
	  }      
	  public void initiate(int s,int c)
	  {     
	      Instructions[0][2] = (c*s)/3;
	    		  
	      Capacity = c*s;          
	  }

}
