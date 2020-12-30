package com.hoffnungland.echoServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        if (args.length != 1) {
            System.err.println("Usage: java EchoServer <port number>");
            System.exit(1);
        }
        
        int portNumber = Integer.parseInt(args[0]);
        
        System.out.println("Listening to port " + portNumber);
        try (
            ServerSocket serverSocket = new ServerSocket(portNumber);
            Socket clientSocket = serverSocket.accept();
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);                   
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        ) {
        	System.out.println("Connection accepted");
        	System.out.println("Local connection: " + clientSocket.getLocalAddress().getHostName() + "(" + clientSocket.getLocalAddress().getHostAddress() + "):" + clientSocket.getLocalPort());
        	System.out.println("Remote connection: " + clientSocket.getRemoteSocketAddress());
            System.out.println("Ready to receive");
        	String inputLine;
            while ((inputLine = in.readLine()) != null) {
            	System.out.println("received: " + inputLine);
                out.println(inputLine);
                System.out.println("sent back: " + inputLine);
            }
        } catch (IOException e) {
            System.out.println("Exception caught when trying to listen on port "
                + portNumber + " or listening for a connection");
            System.out.println(e.getMessage());
        }
    }
}
