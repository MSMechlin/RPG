import java.util.ArrayList;

public class MovePool {


		  private static int[][] ins = InstructionsLoad();
		  private static Move[] movepool = loadMoves();
		 public static Move[] loadMoves()
		  {
		    Move[] m = new Move[32];
		    m[0] = new Bash("Bash",LoadIns(0),0,"");
		    m[1] = new RegularMove("Guard",LoadIns(1),300," was blocked from potential threats.");
		    m[2] = new RegularMove("Seran Wrap",LoadIns(2),300," was encumbered by multiple layers of plastic wrap.");
		    m[3] = new RegularMove("Axe drop",LoadIns(3),300," was crushed under the weight of a greataxe.");
		    m[4] = new RegularMove("Tackle",LoadIns(4),450," was thrusted to the ground violently.");
		    m[5] = new RegularMove("Mind melt",LoadIns(5),50," started laughing profusely.");
		    m[6] = new Item("Minor Healing Potion",LoadIns(6),80," drank a vial of thin red fluid.");
		    m[7] = new Item("Major Healing Potion",LoadIns(7),100," drank a vial of viscous blue fluid.");
		    m[8] = new RegularMove("Fire Storm",LoadIns(8),800," were engulfed by all-consuming showers of fire from the sky.");
		    m[9] = new Item("Elixir",LoadIns(9),80," drank a vial of chunky purple liquid.");
		    m[10] = new RegularMove("HeadButt",LoadIns(10),200," was headbutted savageley");		   
		    m[11] = new RegularMove("Stop Sign",LoadIns(11),50," instinctively stopped upon sight of the sign.");
		    m[12] = new RegularMove("Animal Crossing Sign",LoadIns(12),100," was trampled by crossing animals.");
		    m[13] = new RegularMove("Blizzard",LoadIns(13),600," were nearly frozen solid.");
		    m[14] = new RegularMove("Lightning",LoadIns(14),400," was impaled by a heavenly pillar of light.");
		    m[15] = new RegularMove("Speed Limit Sign",LoadIns(15),100," instinctively slowed to match the speed limit.");
		    m[16] = new RegularMove("Barrier",LoadIns(16),200," was surrounded by a prism of light.");
		    m[17] = new RegularMove("War Dance",LoadIns(17),400," performed a variety of imposing dances.");
		    m[18] = new RegularMove("Battle chant",LoadIns(18),300," were rallied into a round of revitalizing chanting.");
		    m[19] = new RegularMove("Screech",LoadIns(19),100," was washed over by a cascade of high-pitched audial obstructions.");
		    m[20] = new RegularMove("Jab",LoadIns(20),240," was jabbed with a pike.");
		    m[21] = new RegularMove("Drain",LoadIns(21),200," had the life force drained out of them.");		
		    m[22] = new RegularMove("Allheal",LoadIns(22),600," were inveloped in a aura of warm, golden light.");
		    m[23] = new RegularMove("Life-Share",LoadIns(23),70," was given a share of their life force.");
		    m[24] = new Item("Grease spill",LoadIns(24),240," slipped and fell.");
		    m[25] = new RegularMove("Spicy Meatball",LoadIns(25),450," was nailed by a scalding ball of supersonic justice.");
		    m[26] = new RegularMove("Punch",LoadIns(26),100," was hit by a normal bare knuckle punch");
		    m[27] = new RegularMove("Heal",LoadIns(27),200," looks visibly better.");
		    m[28] = new RegularMove("Oven mitts",LoadIns(28),360," was singed by mitts that had recently handled trays of roasted chicken");
		    m[29] = new RegularMove("Wooden Spoon",LoadIns(29),150," was blasted by a wooden spoon.");
		    m[30] = new RegularMove("Brace",LoadIns(30),240," planted their feet firmly.");
		    m[31] = new RegularMove("Potato Salad",LoadIns(31),150," looks visibly better.");
		   
		    return m;
		  }
		  public static Move getMove(int i)
		  {
		    return movepool[i];
		  }  
		  public static int[][] LoadIns(int i)
		  {	  
			
				
			int[][] r = new int[ins[i].length/6][6];	
			
		    for(int i2 = 0;i2<ins[i].length;i2 = i2 + 6)
		    {
		    	
		      r[i2/6][0] = ins[i][i2];
		      
		      r[i2/6][1] =ins[i][i2+1];
		      
		      r[i2/6][2] =ins[i][i2+2];
		      
		      r[i2/6][3] =ins[i][i2+3];
		      
              r[i2/6][4] =ins[i][i2+4];
		      
		      r[i2/6][5] =ins[i][i2+5];
		      
		    }
		    return r;
		  }
		  public static void setPrint(ArrayList<Integer> a,int speed)
		  {
			  
		    for(int i = 0;i<a.size();i++)
		    {
		      System.out.println(i+ ". " + movepool[a.get(i)].getName()+"\tWait time: "+movepool[a.get(i)].getCapacity()/speed);
		    }
		    System.out.println(a.size()+". <-back");
		  }   
		  public static int[][] InstructionsLoad()
		  {
			  /*key for move instructions
			   * r = {0,1,2,3,4,5, 0,1,2,3,4,5, ....}
			   * Each move is defined by one or more effects. Each effect is defined
			   * by a line of 6 integer values:
			   * 0: Determines whether or not this effect applies to the user or to their target 
			   * Each effect utilizes the stats of the user and/or the target to calculate the changes 
			   * the move has on the target or the user
			   * 1: Determines the formula that will calculate the effect
			   * 2: Determines the potency inherent to the effect 
			   * 3: Determines which stat the user will provide to the calculation
			   * 4: Determines which stat the target will provide to the calculation
			   * 5: Determines the which stat the effect will change
			   * */
		   int[][] r ={{0,1,0,2,3,0},//0
		             {0,3,-15,3,3,3},//Guard1
		             {0,4,15,4,4,4},//2
		             {0,1,300,2,3,0},//3
		             {0,1,150,2,3,0},//Tackle4
		             {0,4,8,0,7,2,  0,4,8,0,8,3},//5
		             {0,2,-100,0,0,0},//6
		             {0,2,-100,0,0,1},//7
		             {2,2,300,0,0,0},//8
		             {1,2,50,0,0,0},//9
		             {0,1,200,2,3,0, 1,3,25,0,0,0},//10
		             {0,2,100,0,0,1},//11
		             {0,1,100,2,3,0},//12
		             
		             {2,2,100,0,0,0, 2,3,10,9,9,4},//Blizzard 13		    
		             {0,2,400,0,0,0},//Lightning 14
		             {0,2,4,0,0,4},//15
		             {0,3,-25,8,8,3},//Barrier 16
		             {1,3,-10,2,2,2, 1,3,-10,4,4,4},//War dance17
		             {3,3,-15,2,2,2},//Battle chant 18
		             {0,4,15,0,8,3},//19
		             {0,1,100,2,3,0},//Jab20
		             {0,1,80,2,3,0, 1,3,-10,0,0,0},//21
		             {3,4,-25,5,5,0},//AllHeal22
		             {0,4,-12,0,0,0, 1,3,100,0,0,0},//23
		             {0,2,80,0,0,0, 0,4,10,3,3,3},//Grease Spill24
		             {0,1,400,2,3,0},//25
		             {0,1,100,2,3,0},//26
		             {0,2,-300,0,0,0},//Heal 27
		             {0,1,250,2,3,0},//28
		             {0,1,100,2,3,0},//29
		             {1,3,-15,3,3,3},//Brace30
		             {1,3,-30,0,0,0}};//31	   
		   return r;              
		  }
}
abstract class Operation
{
  abstract public int calculate(int a,int b,int c);
}
class NullOp extends Operation
{
  public int calculate(int a,int b,int c)
  {
    return 0;
  }
}
//affect is determined by a ratio of a users stat to its targets stat times the potency of the effect
class PowXRatio extends Operation
{
  public int calculate(int a,int b,int c)
  {    
    return (((100*b)/c)*a)/100;
  }
}
//The amount of a stat lost/gained is determined solely by the potency of the effect
class FixedDamage extends Operation
{
  public int calculate(int a,int b,int c)
  {
    return a;
  }
}
//Affect is based on a percentage of a user's stat 
class PercentOfU extends Operation
{
  public int calculate(int a,int b,int c)
  {
    return (a*b)/100;
  }
}
//Affect is based on a percentage of a target's stat 
class PercentOfT extends Operation
{	
  public int calculate(int a,int b,int c)
  {
    return (a*c)/100;
  }
}
