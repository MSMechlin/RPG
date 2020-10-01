class Guard extends Move
{
  Guard(String n,int[][] in, int c,String s)
  {
    super(n,in,c,s);    
  }
  public void initiate(int s,int c)
  {
    Capacity = c*s;
    
  }
}