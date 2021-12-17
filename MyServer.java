/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.clientserver;

/**
 *
 * @author Zorays
 */
import java.net.*;  
import java.io.*;  
import java.util.Scanner;
class MyServer{  
    public static void main(String args[])throws Exception{  
        Scanner sc = new Scanner(System.in);
        ServerSocket ss=new ServerSocket(3333);  
        Socket s=ss.accept();  
        DataInputStream din=new DataInputStream(s.getInputStream());  
        DataOutputStream dout=new DataOutputStream(s.getOutputStream());  
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));  
        boolean connected = true;
        String str="",str2="";  
        do{ 
            str=din.readUTF();
            if(str.equals("calling"))
            {
                System.out.println("1 to accept call ");
                System.out.println("2 to hang the call ");
                int callChoice = sc.nextInt();
                if (callChoice == 1)
                {
                    str2="call accept";
                    dout.writeUTF(str2);  
                    dout.flush();
                }
                if (callChoice == 2)
                {
                    str2="call disconnected";
                    dout.writeUTF(str2);  
                    dout.flush();
                    connected = false;
                    break;
                }
                
            }
            else if (str.equals("permission requested"))
            {
                System.out.println("1 to accept request ");
                System.out.println("2 to deny the request and hang the call ");
                int callChoice = sc.nextInt();
                if (callChoice == 1)
                {
                    str2="request accepted";
                    System.out.println("Request Accepted");
                    dout.writeUTF(str2);  
                    dout.flush();
                }
                else if (callChoice == 2)
                {
                    str2="request denied";
                    dout.writeUTF(str2);  
                    dout.flush();
                    connected = false;
                    break;
                }
                else{
                    System.out.println("Wrong Input");
                    str2="wrong input";
                    dout.writeUTF(str2);  
                    dout.flush();
                    connected = false;
                    break;
                }
            }
            else if (str.equals("call connected"))
            {
                System.out.println("1 to disconnect the call");
                int callChoice = sc.nextInt();
                if (callChoice == 1)
                {
                    System.out.println("Call disconnected");
                    str2="Call disconnected";
                    dout.writeUTF(str2);  
                    dout.flush();
                    connected = false;
                    break;
                }
                else
                {
                    System.out.println("Wrong Input");
                    connected = false;
                    break;
                }
                
            }
            else{
                break;
            }
            
              
              
        }
        while(connected=true);
        din.close();  
        s.close();  
        ss.close();  
    }
}  
