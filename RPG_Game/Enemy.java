import java.util.ArrayList;
import java.util.Random;

public class Enemy extends Fighter {

	 private static Random Rand = new Random();
	  public Enemy(String n,int[] s, ArrayList<Integer> m, ArrayList<Integer> in)
	  {
	  	 super(n,s,m,in);  
		 Name = n;
	  }
	  public void choose(String[] p,String d,int Allies)
	  {
	    int c = Rand.nextInt(Moveset.size());
		c = Moveset.get(c);
	    NextAttack = MovePool.getMove(c);
	     
	     Target = Rand.nextInt(Allies);
	     if(NextAttack instanceof Item)
	     {
	       Inventory.remove(c);
	     }
	  }
}
