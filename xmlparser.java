import java.io.*;
import java.util.*;

public class xmlparser{
public static void main(String[] args) throws Exception 
   { 
     //Object for error finding 
	 //here we call the method of error-finding class
	 Main dtd=new Main();
				  
     errorfinding err_check=new errorfinding();
     if(err_check.errorfinding())
         System.out.println("Please correct this error.");	
         else
		  {			   
		  
            FileReader fr=new FileReader("E:/ptools/simple.xml");
		    BufferedReader br= new BufferedReader(fr);
            Stack<String> node=new Stack<String>();
		    PrintWriter writer = new PrintWriter("E:/ptools/file.text", "UTF-8");
			//PrintWriter writer1 = new PrintWriter("E:/ptools/newfile.text", "UTF-8");
			// Hash table for the parse tree description
		    Hashtable<String,String> xparse=new Hashtable<String,String>();
			
		    int count=-1,a=0;//node counter

		    String s,s1="",s2="",st="",root="";
		    Enumeration names;//for hash table node retrieval 
			
            while((s=br.readLine())!=null)//reading  file line by line
               { 
			     char arr[]=new char[s.length()];
			     arr=s.toCharArray();
				 
				 for(int i=0;i<arr.length;i++)
				   { 
				   //condition checking for starting tag,comment and description
				   
					 if(arr[i]=='<' && arr[i+1]!='/' && arr[i+1]!='?' && arr[i+1]!='!')
					   { 
						//for finding tag name  (s2 string)
						 for(int j=i+1;j<arr.length-1;j++)
						   {
						     if(arr[j]=='>')
						       {  
							     i=j;
								 a=j+1;
				                 break;
							    } 
                              s2=s2+Character.toString(arr[j]);							   
							 }
							 //for finding data string(st string)
					     for(int k=a;k<arr.length;k++)
						   {
							  if(arr[k]=='<')
						        {								   
							      break;
							      } 
							  st=st+Character.toString(arr[k]);  
						      }
							  //putting the tag name in hash table
						if(node.size()==0)
						   {
						      xparse.put(s2,"root");
							  dtd.main2(s2,"root");
						      }
							else
							  {
								xparse.put(s2,node.peek());
								dtd.main2(s2,node.peek());
								}
								//pushing tag name in node stack
					    node.push(s2);
						//writing the xml file  description in the text file
					    if(node.size()==1)
                          {
							 root=node.peek();
						     writer.println("This is the description of "+root+"\n");
						  }								
					    if(node.peek().equals(s2) && st.trim()=="")
						  {
							  if(node.size()!=1)
							    {
							      writer.println(s2+" : "+(count+1));
							      
								}
							   count++;
							  }		
						//	printing tag name on consol					  
                        System.out.println("\t\t\t"+s2);
						s2="";
						}
						
					    else if(arr[i]=='<' && arr[i+1]=='/')//condition for ending tag name  
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
									//data writing in text file 
								 if(node.peek().equals(s1) && st.trim()!="")
								    {
							          writer.print(s1+" : ");
						              writer.println(st.trim());
							         }
							     if(node.peek().equals(s1) && st.trim()=="")//line space b/w two main tag
							         {
							          writer.println();
							          }
									  
								  node.pop(); 
								  s1="";
                                  st="";								
							      }
			         }
                 }
				 // print description of the parse tree
				  
			String str;
			names=xparse.keys();
			System.out.println("****************This is the description of parse tree.****************\n\n");
			while(names.hasMoreElements())
			    {
			      str=(String)names.nextElement();
				  if(xparse.get(str).equals("root"))
				    System.out.println("\t"+str+" is the root of xml tree");
			         else
				      System.out.println("\t"+str+" is the child of "+xparse.get(str));
			     }
			   //closing file
			   dtd.main1();
			writer.close();   		
         }
	}
}