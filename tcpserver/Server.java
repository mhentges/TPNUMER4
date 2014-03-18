package tcpserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

public class Server {

    public static void main(String[] args) throws IOException {
        
        
        new Server().begin(4444);
    }

    
    ServerSocket serverSocket;

    public void begin(int port) throws IOException {
        serverSocket = new ServerSocket(port);
        while (true) {
            System.out.println("Waiting for clients to connect on port " + port + "...");
            new ProtocolThread(serverSocket.accept()).start();
            //Thread.start() calls Thread.run()
        }
    }

    class ProtocolThread extends Thread {

        Socket socket;
        PrintWriter out_socket;
        BufferedReader in_socket;

        public ProtocolThread(Socket socket) {
            System.out.println("Accepting connection from " + socket.getInetAddress() + "...");
            this.socket = socket;
            try {
                out_socket = new PrintWriter(socket.getOutputStream(), true);
                in_socket = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void run() {
            Random r = new Random();
            int a=r.nextInt(6);
            String b =""+a+"";
            
            try {
                System.out.println("Expecting Hello from client...");
                //sleep(5000);
                if ("Hello".equals(in_socket.readLine())) {
                    System.out.println("Client is nice :) Let's be polite...");
                    out_socket.println("Hello");
                }
                /*
                * Utiliser une boucle do {] while(); avec la condition à vérifier
                * au lieu de passer par un while avec une condition fixé initialement
                */
               int e=3;
                while(e==3){
                String c=in_socket.readLine();
                System.out.println(c);
                if(b.equals(c))    {
                    System.out.println("he won");
                    out_socket.println("GG"); 
                    e=4;
                }
                
                else{
                    String result="";
                    int d = Integer.parseInt(c);
                    if (d>a){result="-";}
                    else if (d<a){result="+";}
                 System.out.println(result);
                 out_socket.println(result);
                }
                }
             
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    System.out.println("Closing connection.");
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
