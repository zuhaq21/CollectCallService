
package com.mycompany.clientserver;

import java.net.*;
import java.io.*;
import java.util.Scanner;
public class MyClient {
    public static void main(String args[])throws Exception
    {
        Scanner sc = new Scanner(System.in);
        Socket s=new Socket("localhost",3333);  
        DataInputStream din=new DataInputStream(s.getInputStream());  
        DataOutputStream dout=new DataOutputStream(s.getOutputStream());  
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));  
        System.out.println("Press 1 to make a call");
        int choice = sc.nextInt();
        boolean connected = true;
        String str="",str2="";
        str="calling";
        dout.writeUTF(str);  
        dout.flush();
        do{
            
            if (choice == 1)
            {
                
                //str="calling";
                //dout.writeUTF(str);  
                //dout.flush();  
                str2=din.readUTF();  
                System.out.println("Server says: "+str2);
                //ye logic check krni ha
                if(str2.equals("call disconnected"))
                {
                    System.out.println("Call Hanged up");
                    break;
                }
                else if (str2.equals("call accept"))
                {
                    str = "permission requested";
                    dout.writeUTF(str);
                    dout.flush();
                }
                else if (str2.equals("request accepted"))
                {
                    str = "call connected";
                    System.out.println("Call Connected");
                    dout.writeUTF(str);
                    dout.flush();
                }
                else if (str2.equals("request denied"))
                {
                    System.out.println("request Denied\nCall Hanged up");
                    break;
                }
                else if (str2.equals("wrong input"))
                {
                    break;
                }
                else {
                    break;
                }
            }
            
        }
        while(connected=true);
  
        dout.close();  
        s.close();  
    }
}
