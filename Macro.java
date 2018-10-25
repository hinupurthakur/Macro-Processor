import java.util.*;
import java.io.*;
class Macro
{
	static String mnt[][]=new String[5][3]; 
 	static String ala[][]=new String[10][2]; 
 	static String mdt[][]=new String[20][1]; 
	static int mntc=0;
	static int mdtc=0;
	static int alac=0;
	public static void main(String args[])
	{
	  pass1();
          pass2();	
        }
        static void pass1()
        {
          int index,i,j;
          String mdts,alas,ss,s,mnts,prev="";
          try
          {
        	BufferedReader in = new BufferedReader(new FileReader("input.txt"));
 	        File op = new File("pass1_output1.txt");
 	        if (!op.exists())
 	           op.createNewFile();
 	        BufferedWriter output = new BufferedWriter(new FileWriter("pass1_output1.txt"));
	        File  mntob= new File("mnt.txt");
 	        if (!mntob.exists())
 	           mntob.createNewFile();
 	        BufferedWriter mntwrite= new BufferedWriter(new FileWriter("mnt.txt"));
	        File mdtob = new File("mdt.txt");
 	        if (!mdtob.exists())
 	           mdtob.createNewFile();
 	        BufferedWriter mdtwrite = new BufferedWriter(new FileWriter("mdt.txt"));
	        File alaob = new File("ala.txt");
 	        if (!alaob.exists())
 	           alaob.createNewFile();
 	        BufferedWriter alawrite = new BufferedWriter(new FileWriter("ala.txt"));
	        while((s=in.readLine())!=null)
	        {
		     if(s.equals("MACRO"))
		     {	
			prev=s;
			for(;!(s=in.readLine()).equals("MEND");mdtc++,prev=s)
			{
			  if(prev.equals("MACRO"))
			  {
			    String str[]=s.split(" ");
			    mnt[mntc][0]=(mntc+1)+" "; 
			    mnt[mntc][1]=str[0]+" ";
 			    mnt[mntc++][2]=(++mdtc)+" ";
			    String str1[]=str[1].split(",");
			    for(i=0;i<str1.length;i++)
			    {
			       ala[alac][0]=alac+" ";
			       ala[alac++][1]=str1[i];
			
			    }
			  }
			  else
			  {
			    index=s.indexOf("&");
			    ss=s.substring(index);
			    for(i=0;i<alac;i++)
 			      if(ala[i][1].equals(ss))
 			        s=s.replaceAll(ss,"#"+ala[i][0]);
 			  }
 			  mdt[mdtc-1][0]=s;
 			}
			mdt[mdtc-1][0]=s;//MEND
 		     }
 		     else
 	             {
 			output.write(s);
 			output.newLine();
 		     }
 		}
			output.close();
			for( j=0;j<mntc;j++)
			{
			mnts=mnt[j][0]+mnt[j][1]+mnt[j][2];
			mntwrite.write(mnts);
			mntwrite.newLine();
			}
			mntwrite.close();
			for(j=0;j<mdtc;j++)
			{
			mdts=mdt[j][0];
			mdtwrite.write(mdts);
			mdtwrite.newLine();
			}
			mdtwrite.close();
			for(j=0;j<alac;j++)
			{
			alas=ala[j][0]+ala[j][1];
			alawrite.write(alas);
			alawrite.newLine();
			}
			alawrite.close();
			}
			catch(Exception ex)
			{
			System.out.println(ex);
			}
			
}

static void pass2()
{
try
{
int alap=0,index,mdtp,i,j,flag=0;
String s,temp,alas; 
BufferedReader in = new BufferedReader(new FileReader("pass1_output1.txt"));
 	File op = new File("pass2_output2.txt");
 	if (!op.exists())
 	op.createNewFile();
 	BufferedWriter output = new BufferedWriter(new FileWriter("pass2_output2.txt"));
	File alaob = new File("ala2.txt");
 	if (!alaob.exists())
 	alaob.createNewFile();
 	BufferedWriter alawrite = new BufferedWriter(new FileWriter("ala2.txt"));
	for(i=0;i<mntc;i++)
	{
	for(j=0;j<3;j++)
	mnt[i][j]=mnt[i][j].trim();
	}
	for(;(s=in.readLine())!=null;flag=0)
	{
		String st[]=s.split(" ");
		
                for(i=0;i<mntc;i++)
		{
		 if(st[0].equals(mnt[i][1]))
		{	
			mdtp=Integer.parseInt(mnt[i][2]);
			String arg[]=st[1].split(",");
			for(i=0;i<arg.length;i++)
				ala[alap++][1]=arg[i];
			for(i=mdtp;!(mdt[i][0].equals("MEND"));i++)
			{
				index=mdt[i][0].indexOf("#");
 				temp=mdt[i][0].substring(0,index);
 				temp+=ala[Integer.parseInt(""+mdt[i][0].charAt(index+1))][1]; //char to string to integer
				output.write(temp);
 				output.newLine();
			}
		        flag=1;
		}
            }
		if(flag==0) //not a macro
 		{
 			output.write(s);
 			output.newLine();
 		}
		
 	}
	output.close();
	for(j=0;j<alac;j++)
			{
			alas=ala[j][0]+ala[j][1];
			alawrite.write(alas);
			alawrite.newLine();
			}
			alawrite.close();
	}
catch(Exception e)
{System.out.println(e);}
}

}
			