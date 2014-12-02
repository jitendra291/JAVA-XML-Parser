//package test2;

import java.awt.GridLayout;
import java.util.*;

import javax.swing.*;

/**
 *  This is the main program
 * @author Jitendra & Saurabh Kumar Gangwar
 */
 class Main
{
   public static Hashtable<String,String> xp=new Hashtable<String,String>();
   public static Enumeration names;
   public static String root_name=""; 
   
  public static void main2(String s1,String s2){
  //put(retrieve from xmlparser file ) all the values in xp hashtabel 
      xp.put(s1,s2);
       
   }
    public static void main1()
    {
          	
        //build GUI
		int g=xp.size();
		int level[]=new int [g];//for leveling.
		int copy_level[]=new int [g+1];
		String child[]=new String[g];
       String parent[]=new String[g];
       int colum[]=new int[g];
       int colum_freq[]=new int[g];
        JFrame mainWindow = new JFrame();//main frame
        mainWindow.setSize(1000,800);
        mainWindow.setLayout(new GridLayout());
        JButton addButton = new JButton("Add");
		
        DisplayPanel graphPanel = new DisplayPanel();
        mainWindow.add(graphPanel);
        
        mainWindow.setVisible(true);
         graphPanel.setVisible(true);
		
        String str="";
		Node nd[]=new Node[xp.size()];
		
      
        int count=0;
		names=xp.keys();
		while(names.hasMoreElements())
		    {
		      str=(String)names.nextElement();
		       child[count]=str;
			   parent[count]=xp.get(str);
			   count++;
		     }
		
			 for(int i=0;i<xp.size();i++)
			   {
			     String s1=child[i];
			     System.out.println(child[i]+"\t"+parent[i]);
				
				}
			int arr[][]=new int[xp.size()][xp.size()];
		    for(int i=0;i<xp.size();i++)
			   {
			     nd[i]=new Node(graphPanel,child[i]);
				 nd[i].setVisible(true);
				 //graphPanel.add(nd[i],100,110);
	
				 }
				 for(int i=0;i<xp.size();i++){
				  for(int j=0;j<xp.size();j++)
				    {
					  if(child[j].equals(parent[i]))
					     {
						    //if((arr[i][j]==0 )||(arr[j][i]==0))
						     //{
							  nd[i].addConnection(nd[j]);
                              arr[i][j]=1;
							  arr[j][i]=1; 						   
						      break;
							 
							 //}
						   }
					
					   }
        }
		
		for(int k=0;k<xp.size();k++)
		   {
		     if(parent[k].equals("root"))
			     level[k]=1;	
				 colum[k]=2;
				 colum_freq[k]=3;
			 }
			 for(int k=0;k<xp.size();k++){
			 if(level[k]==0){
			     int p= check_level(parent[k],xp.size(),child,level,parent);
				 level[k]=p;
			 }
			 }
			for(int k=0;k<xp.size();k++){
			 System.out.println(child[k]+"\t"+level[k]);
			 }
		
		
		
	  for(int i=0;i<xp.size()+1;i++){
	       if(i>=xp.size())
		     copy_level[i]=89;
			 else
	           copy_level[i]=level[i];}
		   
	 int count2=1,n=1;
        Arrays.sort(copy_level);
		for(int i=1;i<xp.size();i++)
		   { 
		       if(copy_level[i]==copy_level[i+1]){
			     count2++;
				 
				 }
				 else
				 {
				 //System.out.println("my name njhgfjhgjhfjcv "+count2);
				 	for(int k=0;k<xp.size();k++)
					{
                      if(copy_level[i]==level[k]){
					    colum_freq[k]=count2;
                         colum[k]=n;
						n++;
					  
                        }
					}
					count2=1;
					n=1;
					}
			 }
			 
			 
		for(int k=0;k<xp.size();k++){
			 System.out.println(child[k]+"\t"+colum[k]+"\t"+level[k]);
			 }
       for(int i=0;i<xp.size();i++)
           {
		     graphPanel.add(nd[i],((700/colum_freq[i])*(colum[i])+10),(level[i]*150));
			 }	   
    }
public static int   check_level(String pr ,int x,String child[],int level[],String parent[]){
 int k=0;
    for(int i=0;i<x;i++){
         if(pr.equals(child[i]))
		   {
		      if(level[i]!=0)
			     k=level[i]+1;
				else
				 k= check_level(parent[i],x,child,level,parent)+1;
			  }
    } 
	return k;
}
}
