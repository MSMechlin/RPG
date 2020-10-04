
import java.util.ArrayList;
import java.util.Scanner;

public class Ally extends Fighter{
  
	    private static Scanner scan = new Scanner(System.in);       
	    public Ally(String n,int[] s,ArrayList<Integer> m,ArrayList<Integer>in)
	    {
	        super(n,s,m,in);           
	    }    
	    public void choose(String[] p,String a)
	    {
	      int menu = 0;	
	      int ans;
	      int newMove = 0;
	      int target = 0;
	      boolean submit = false;
	      
	      boolean moveChosen = false;
	      boolean menuChosen = false;
	      boolean back = false;
	      while(!submit)
	      {	  
	    	  back = false;
	    	  menuChosen = false;
	    	  while(!menuChosen)
	    	  {
	    		  back = false;
	    		  System.out.println(a);
	      			System.out.println("What will " + Name + " do?");
	                System.out.println("HP: " + Stats[0]);
	                System.out.println("1. Attack  2. Item");
	                menu = scan.nextInt();  
	                switch(menu)
	                {
	                	case 1:
	                		moveChosen = false;
	                		MovePool.setPrint(Moveset,Stats[4]);
	                		while(!moveChosen&&!back)	
	                		{	
	                			ans = scan.nextInt();
	                			if(ans < 0||ans>Moveset.size())
	                			{
	                				System.out.println("Invalid choice");
	                			}
	                			else if(ans == Moveset.size())
	                			{
	                				back = true;
	                			}
	                			else
	                			{
	                				newMove = Moveset.get(ans);
	                				moveChosen = true;
	                			} 
	                		}	
	                		menuChosen = true;
	                		break;
	                	case 2:
	                		moveChosen = false;
	                		MovePool.setPrint(Inventory,Stats[4]);
	                		while(!moveChosen&&!back)	
	                		{	
	                			ans = scan.nextInt();
	                			if(ans < 0||ans>=Inventory.size())
	                			{
	                				System.out.println("Invalid choice.");
	                			}
	                			else if(ans == Inventory.size())
	                			{
	                				back = true;
	                			}
	                			else
	                			{
	                				newMove = Inventory.get(ans);
	                				moveChosen = true;
	                			} 
	                		}
	                		menuChosen = true;
	                		break;
	                	default:
	                	{
	                		System.out.println("Invalid choice.");
	                	}
	                	
	                }
	    	  } 
	    	  if(!back&&(MovePool.getMove(newMove).getIn(0,0)!=0))
	    	  {
	    		  target = 0; 
	    		  back = true;
	    		  submit = true;
	    	  }	  
	    	  if(!back)
	    	  {	  
	    		  Utility.arrPrint(p);
	    		  System.out.println(Party.getRemaining()+". <-back");
	    		  while(!submit&&!back)
	    		  {         
	    			  ans = scan.nextInt();
	    			  if(ans == Party.getRemaining())
	    			  {
	    				  back = true;
	    			  }
	    			  else
	    			  {  
	    			      target = Party.setTarget(ans); 
	    			      submit = (target != -1); 
	    			  }    
	    		  }
	    	  }
	      	}  
	        NextAttack = MovePool.getMove(newMove);
	        Target = target;
	        if(NextAttack instanceof Item)
	        {
	            Inventory.remove(Inventory.indexOf(newMove));
	        }
	      }
	        
	}


