import java.util.ArrayList;
import java.util.Random;
public class Enemy extends Fighter {
	private static Random Rand = new Random();
	  public Enemy(String n,int[] s, ArrayList<Integer> m, ArrayList<Integer> in)
	  {
	  	 super(n,s,m,in);  
		 Name = n;
	  }
	  public void choose(String[] p,String d)
	  {
	    int rando = Rand.nextInt(Moveset.size());
		int nextMove = Moveset.get(rando);
		NextAttack = MovePool.getMove(nextMove);
		if(NextAttack.getIn(0, 0)==0)
		{	
			System.out.print("Start-"+(NextAttack.getIn(0,2)>0));
			Target = Party.setTarget(NextAttack.getIn(0,2)>0);
			System.out.println("end");
		}	
		else
			Target = 0;
	     if(NextAttack instanceof Item)
	     {
	       Inventory.remove(nextMove);
	     }
	  }
}
