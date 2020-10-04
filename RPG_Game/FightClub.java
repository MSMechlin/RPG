import java.util.ArrayList;
public class FightClub {

	  private static int[][] st = StatsLoad();
	  private static int[][] mv = LoadMoves();
	  public static Fighter[] fighters = ConstructF();
	 public static int[][] LoadMoves()
	  {
	    int[][] m =
	    {{29,24,28,2,31,25},{26,10,3,17,18,19},{16,13,14,8,27,22},{22,10,18,29},{27,26,25},{19,20,10,0,29},{30,20,4,1},{30,20,4,1},{26,21,3,14,27}};
	    
	    return m;
	  }
	 public static ArrayList<Integer> mvLoad(int i)
	 {
		 ArrayList<Integer> in = new ArrayList<Integer>();
		 for(int i2 = 0;i2<mv[i].length;i2++)
		 {
			 in.add(mv[i][i2]);
		 }
		 return in;
	 }
	  public static int[][] StatsLoad()
	  {	 
		  //Current stats      Default stats
		  //           HP,CH,ATT,DEF,SPD|HP,CH,......
		int[][] r = {{700,0,0,0,0,700,0,100,100,30},
					 {1000,0,0,0,0,1000,0,150,150,20},  
		             {500,0,0,0,0,500,0,90,90,40},
		             {500,0,0,0,0,500,0,100,80,40},
		             {200,0,0,0,0,200,0,80,80,50},
		             {400,0,0,0,0,400,0,90,70,40},
		             {1000,0,0,0,0,1000,0,100,100,20},
		             {1000,0,0,0,0,1000,0,100,100,20},
		             {2000,0,0,0,0,2000,0,150,150,30}};		             		                 
		return r;
	  }
	  
	  public static Fighter[] ConstructF()
	  {
		String[] nm = {"Jeff","Broyalf","Greida","Kreeyash","Blibly","Goblin","Imp Guard A","Imp Guard B","Ogre King"};
		Fighter[] f = new Fighter[9];
		ArrayList<Integer> in = new ArrayList<Integer>();
		in.add(7);
		in.add(7);
		in.add(6);
		in.add(6);
		in.add(9);
		in.add(9);
		in.add(24);
		
		for(int i = 0;i<9;i++)
		{
			
			if(i<3)
			f[i] = new Ally(nm[i],st[i],mvLoad(i),in);
			else
			f[i] = new Enemy(nm[i],st[i],mvLoad(i),in);	
		}
		
	    return f;                        
	  }
	    public static Fighter getFighter(int i)
	  {
	    return 	fighters[i];
	  }  
	}


