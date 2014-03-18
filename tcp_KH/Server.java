package tcpserver;

import static com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type.Int;
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
            int numberToGuess   = r.nextInt(6);
            System.out.println(numberToGuess);
            
            try {
                String answer = "";    
                do {
                    answer = in_socket.readLine();
                    System.out.println("Envoi client: " + answer);
                        if (numberToGuess > Integer.parseInt(answer)) {
                            out_socket.println("+");
                        } else if (numberToGuess < Integer.parseInt(answer)) {
                            out_socket.println("-");
                        } else {
                            out_socket.println("=");
                        }
                } while (numberToGuess != Integer.parseInt(answer)); 
// le jeu se deroule avant les bonjours serveurs clients 

                System.out.println("Expecting Hello from client...");
                //sleep(5000);
                if ("Hello".equals(in_socket.readLine())) {
                    System.out.println("Client is nice :) Let's be polite...");
                    out_socket.println("Hello");
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
