
import java.text.DecimalFormat;
import java.util.Random;
import java.util.Scanner;
public class Party {
	private DecimalFormat df;
	private Scanner s = new Scanner(System.in);
	private String ans;
	private static int Cursor;
	private static int Remaining;
	private static int Allies;
	private static int Enemies;
	private int[] BaseParty;
	private static int[] Order;
    private static Fighter[] fighters; 
    private boolean winner;
    private static Random Rand = new Random();
    public Party(int[] a)
    {      
       df = new DecimalFormat("##.##");
       BaseParty = a;   
    } 
    public void Load(int[] Opponents)
    {      
    	Remaining = BaseParty.length+Opponents.length;
    	fighters = new Fighter[Remaining];
    	Allies = BaseParty.length;
    	Enemies = Opponents.length;
    	System.out.println(Enemies);
    	for(int i = 0;i<BaseParty.length;i++)
    	{
    		fighters[i] =  FightClub.getFighter(i);
    		fighters[i].setAll(true);
    	}
    	System.out.print("You are confronted by ");
    		
    	  for(int i = 0;i<Opponents.length;i++)
    	  {	  
    		  if(i == 0)
    		  {
    			  	System.out.print(FightClub.getFighter(Opponents[i]).getName());
    		  }
    		  else if(i == 1)
    		  {
    			  System.out.print(" and his goons!");
    		  }
    		  fighters[i+BaseParty.length] = FightClub.getFighter(Opponents[i]);
    		  fighters[i+BaseParty.length].setAll(false);
    	  }
    }
    //Once fighter runs out of HP
    public void Unload(int index)
    {
    	int tmp = Order[index];
        fighters[tmp].Defeat();
        //Put defeated fighter at end 
        for(int i = index;i<(Remaining-1);i++)
        {
        	Order[i] = Order[i+1];
        }
        if(index<Cursor)
        	Cursor--;
        Order[Remaining-1] = tmp;
        Remaining--;
    }
    public void Reset()
    {
    	for(Fighter f: fighters)
    	{
    		f.reset();
    	}    	
    }
    public void Initiate()
    {
    	Order = new int[Remaining];
    	for(Cursor = Remaining-1;Cursor>-1;Cursor--)
    	{
    		Order[Cursor] = Cursor;
    	}
    	//Everyone decides their move in the beginning	
    	for(Cursor = Remaining-1;Cursor>-1;Cursor--)
    	{
    		fighters[Cursor].setMove(Targets(),"\n");
    	}
    	Shuffle();
    	Cursor = 0;
    }
    //Determine order of moves depending on wait time
    public void Shuffle()
    {
    	int tmp;
    	for(int i=0;i<Remaining;i++)
    	{
    		for(int j=0;j<Remaining-1;j++)
    		{
    			if(fighters[Order[j]].getWait()>fighters[Order[j+1]].getWait())
    			{
    				tmp = Order[j];
    				Order[j] = Order[j+1];
    				Order[j+1] = tmp;
    			}
    		}
    	}
    }
    //After fighter's wait time changes
    public void Replace(int index)
    {
    	int newPos = 0;
    	int oldPos = index;
    	int tmp = Order[index];
    	if(index<=Cursor)
    		oldPos=index+Remaining;
    	boolean found = false;
    	int i = (Cursor+1);
    	//Find first fighter after cursor to have higher wait time
    	while(i<Cursor+Remaining&&!found)
    	{
    		if((!found)&&fighters[Order[index]].getWait()<fighters[Order[i%Remaining]].getWait())
    		{
    			
    			newPos = i;
    			found = true;
    		}
    		if(Cursor!=index&&i==Cursor+Remaining-1)
    		{
    			newPos = i+1;
    			found = true;
    		}
    		i++;
    	}
    	if(found)
    	{
    		if(oldPos>newPos)
    		{	
    			for(i = oldPos;i>newPos;i--)
    				Order[i%Remaining] = Order[(i-1)%Remaining];
    		}	
    		else
    		{	
    			newPos--;
    			for(i = oldPos;i<newPos;i++)
    				Order[i%Remaining]=Order[(i+1)%Remaining];
    		}
    		Order[newPos%Remaining] = tmp;
    	}	
    }
    public void WaitListPrint()
    {
    	System.out.println("Position start:"+Cursor);
    	for(int i = 0; i<Remaining;i++)
    		System.out.print(Order[i]+", ");
    	System.out.println();
    	for(int i = 0; i<Remaining;i++)
    		System.out.print(fighters[Order[i]].getWait()+", ");
    	System.out.println("\n");
    }
    public boolean Battle(int[] opponents)
    { 
      Load(opponents);
      Reset();
      Initiate();
      boolean End = Tally();
      while(!End)
      {	  
    	  while(Cursor<Remaining&&!End)
    	  {
    		  allWait(fighters[Order[Cursor]].getWait());
    		  Act();
    		  End = Tally();
    		  if(!End)
    		  {  
    			  fighters[Order[Cursor]].setMove(Targets(),showCharge());
    		  	  Replace(Cursor);
    		  	  Cursor++;
    		  }		
    	  }
    	  Cursor = 0;
      }	  
      return winner;
    } 
    public static int setTarget(int newTar)
    {    	
    	newTar += Cursor;
    	if(newTar<Cursor&&newTar>Remaining+Cursor)
    	{
    		System.out.println("That is not a valid target");
    		return -1;
    	}
    	return Order[newTar%Remaining];
    }
    public static int setTarget(boolean all)
    {
    	int i = -1;
    	int search = 0;
    	
    	if(all)
    		search = Rand.nextInt(Allies);
    	else
    		search = Rand.nextInt(Enemies);
    	while(search>=0)
    	{
    		i++;
    		if(fighters[i].getAll() == all&&!fighters[i].getDown())
    			search--;
    	}
    	System.out.println(i);
    	return i;
    }
    public String[] Targets()
    {
      String[] t = new String[Remaining];
      for(int i=Cursor;i<Cursor+Remaining;i++)
      {  
    	if(i == Cursor)
    	{
    		t[i-Cursor] = fighters[Order[i%Remaining]].getName();
    	}
    	else
    	{
    		t[i-Cursor] = fighters[Order[i%Remaining]].getName() + " Wait time: "+ df.format(fighters[Order[i%Remaining]].getWait());
    	}
      }
      return t;
    }
    public boolean Tally()
    {
      boolean end = false;
      for(int i = 0;i< Remaining;i++)
      {
        if(fighters[Order[i]].getStat(0) <= 0)
        {
        	//check to see who has run out of HP and remove them
        	System.out.println(fighters[Order[i]].getName() + " hit the floor!");  
        	if(fighters[Order[i]].getAll())
        		Allies--;
        	else
        		Enemies--;
        	Unload(i);
        } 
      }
      if(Enemies == 0)
      {
    	  winner = true;
    	  end = true;
      }
      if(Allies == 0)
      {
    	  winner = false;
    	  end = true;
      }
      
      //if one side has run out of fighters tally returns false and the battle ends
      return end;
    }
    public void allWait(double increment)
    {
      for(int i=0;i<Remaining;i++)
      {
        fighters[Order[i]].Wait(increment);
      }      
    }
    public String showCharge()
    {
    		String p = "";	
    		int j = 0;
    		while(fighters[j] instanceof Ally)
    		{
    			p = p + (fighters[j].getName() + " Wait: " + fighters[j].getWait() + "  ");
    			j++;
    		}
    		p = p+ "\n";
    		return p;
    }
    public void Act()
    {
      int user = Order[Cursor];
      boolean setWait = false;	
      int[] a = {0,0,0,0,0,0};
      int[][] ins = fighters[user].getNextAtt().getIns();   
      int target = fighters[user].getTarget();
      //Announce who is acting what move they are using and who their target is
      System.out.println(fighters[user].getName() + " used " + fighters[user].getNextAtt().getName());
      //Flavor text describing what happens to their target
      if(!fighters[target].getDown()||ins[0][0]!=0)
      {
    	  switch(ins[0][0])
    	  {
    	  	case 0:
    	  		System.out.println(fighters[target].getName() + fighters[user].getNextAtt().getScript());
    	  		break;
    	  	case 1:
    	  		System.out.println(fighters[user].getName() + fighters[user].getNextAtt().getScript());
    	  		break;
    	  	case 2:	
    	  		System.out.println("All opponents"+fighters[user].getNextAtt().getScript());
    	  		break;
    	  	case 3:
    	  		System.out.println("All allies"+fighters[user].getNextAtt().getScript());
    	  }
    	  for(int i=0;i<ins.length;i++)
    	  {   
    		  switch(ins[i][0])
    		  {
    		  case 0:
    			  //[target].affect() calculates and announces the effects of the user's move on the target
    			  fighters[target].affect(Utility.getOps(ins[i][1]).calculate((ins[i][2]),fighters[user].getStat(ins[i][3]),fighters[target].getStat(ins[i][4])),ins[i][5]);
    			  break;
    		  case 1:
    				//this line invokes the collateral affects of a move on the user	
    			  fighters[user].affect(Utility.getOps(ins[i][1]).calculate((ins[i][2]),fighters[user].getStat(ins[i][3]),fighters[target].getStat(ins[i][4])),ins[i][5]);
    			  break;
    		  case 2:
    			 for(int j = 0;j<Remaining;j++)
    			 {
    				 if(fighters[Order[j]].getAll()!=fighters[user].getAll())
    					 fighters[Order[j]].affect(Utility.getOps(ins[i][1]).calculate((ins[i][2]),fighters[user].getStat(ins[i][3]),fighters[target].getStat(ins[i][4])),ins[i][5]);
    			 }
    			 break;
    		  case 3:
    			 for(int j = 0;j<Remaining;j++)
     			 {
     				 if(fighters[Order[j]].getAll()==fighters[user].getAll())
     					 fighters[Order[j]].affect(Utility.getOps(ins[i][1]).calculate((ins[i][2]),fighters[user].getStat(ins[i][3]),fighters[target].getStat(ins[i][4])),ins[i][5]);
     			 }
    			  
    		  }
    		  if(ins[i][5] == 4&&ins[i][0] == 0)
          		setWait = true;
      		}           
      	/*	for(int i=0;i<ins.length;i++)
      		{  
      			if(ins[i][0] == 1)
      			{
      				//this line invokes the collateral affects of a move on the user	
      				fighters[user].affect(Utility.getOps(ins[i][1]).calculate(ins[i][2],a[ins[i][5]],fighters[user].getStat(ins[i][3])),ins[i][5]);
      			}              
      	    } */
      }
      else
    	  System.out.println(fighters[user].getName()+"'s attack hit nothing.");
      if(setWait)
      {
    	  Replace(FindOrder(target));
      }
      System.out.println("press enter to continue");
      ans = s.nextLine();
      System.out.println("");
    }
    public int FindOrder(int memPos)
    {
    	int i = 0;		
    	while(i<Remaining)
    	{
    		if(Order[i]==memPos)
    		{
    			return i;
    		}
    		i++;
    	}
    	return -1;
    }
    public static int getRemaining()
    {
    	return Remaining;
    }
}
