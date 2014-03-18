package tcpclient;

import java.io.BufferedReader;
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
        Scanner sc = new Scanner(System.in);
        int     numberToGuessUser;
        String  answer = "";
        do {
            System.out.println("pick a number between 0 and 5");
            numberToGuessUser = sc.nextInt();
            outSocket.println(numberToGuessUser);
            answer = inSocket.readLine();
            System.out.println(answer);
        } while (answer.compareTo("=") != 0);
        
        System.out.println("Gagné");
        
        System.out.println("Sending Hello to server");
        outSocket.println("Hello");
        System.out.println("Waiting answer from server");
        if ("Hello".equals(inSocket.readLine())) {
            System.out.println("Server is nice :)");
        }
        System.out.println("End.");
    }
}

