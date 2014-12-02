import java.io.*;
import java.util.*;

public class errorfinding{
public static boolean errorfinding()throws Exception
    {
	  FileReader fr=new FileReader("E:/ptools/simple.xml");
	  BufferedReader br= new BufferedReader(fr);
      Stack<String> node=new Stack<String>();

	  Stack<Integer> error_stck=new Stack<Integer>();//store the error line  
	  int a=0,linecount=0;
	  boolean bool=false;
	  String s,s1="",s2="";
      Loop: while((s=br.readLine())!=null)//read the file line by line
                { 
			      linecount++;  //line counter
			      char arr[]=new char[s.length()];//store the line into char array
			      arr=s.toCharArray();
				  Loop1 :for(int i=0;i<arr.length;i++)
				            { 
							  //this condition is for starting tag
					          if(arr[i]=='<' && arr[i+1]!='/' && arr[i+1]!='?' && arr[i+1]!='!')
					            {  
						          for(int j=i+1;j<arr.length-1;j++)
							         {
						               if(arr[j]=='>')
						                 {  								   
     								       i=j;
								           a=j+1;
									   	   break;
							               } 
                                       s2=s2+Character.toString(arr[j]);//s2 is used for tag name							   
							            }
										// note for admin ,config and system tag
								  if(s2.equals("admin") || s2.equals("config") || s2.equals("system"))
								     { 
									    System.out.println("Note :"+"  the admin, system and config are already defined by core modules so there is no need to define a title for these.");
								        bool=true;
									    break Loop;
								       }
								  if(node.size()==0 || !node.peek().equals(s2))//error condition
						              node.push(s2);
									  else
									   {
									     System.out.println(" There is a opening or closing tag error in the line " +linecount);//print the error
									     bool=true;
									     break Loop;
										}
							         s2="";							 
						          }
					              else if(arr[i]=='<' && arr[i+1]=='/')//for closing tag
						              {	
						                 for(int j=i+1;j<arr.length-1;j++)
							                {
						                      if(arr[j+1]=='>')
						                         {  
									               i=j; 
												   break;
								                   } 
                                               s1=s1+Character.toString(arr[j+1]);//							   
							                  }
						                 if(s1.equals(node.peek()))
								            {
										       node.pop();
											   
											  }
                                              else
                                               {
									             System.out.println(" There is a tag type error in the line " +linecount);//for tag name error
									             bool=true;
									             break Loop;
									             }	 
							              s1="";
							             }
                                   		
							 }
				}
		return bool;
     }
			 
}
	

