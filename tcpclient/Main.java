package tcpclient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws Exception {
        Socket socket = new Socket("localhost", 4444);
        //Socket socket = new Socket("127.0.0.1", 4444);
        //to get the ip address
        System.out.println((java.net.InetAddress.getLocalHost()).toString());

        //true: it will flush the output buffer
        PrintWriter outSocket = new PrintWriter(socket.getOutputStream(), true);
        BufferedReader inSocket = new BufferedReader(new InputStreamReader(socket.getInputStream()));
       // Thread.sleep(1000);

        System.out.println("Sending Hello to server");
        outSocket.println("Hello");
        System.out.println("Waiting answer from server");
        if ("Hello".equals(inSocket.readLine())) {
            System.out.println("Server is nice :)");
        }
        int b=3;
        while(b==3){
        Scanner sc=new Scanner(System.in);
          System.out.println("pick a number between 0 eand 5");
          int proposition=sc.nextInt();
          outSocket.println(proposition);
          String result=inSocket.readLine();
          
        if("GG".equals(result)){
            System.out.println("WIN");
            b=5;
            }  
        if("+".equals(result)){
            System.out.println("serveur said +");
        }
        if("-".equals(result)){
            System.out.println("serveur said -");
        }
        }
        System.out.println("End.");
    }
}

