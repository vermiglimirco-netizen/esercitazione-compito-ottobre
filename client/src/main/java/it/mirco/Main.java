package it.mirco;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;

public class Main {
    public static void main(String[] args) {
        
        try (Socket socket = new Socket("localhost", 12345))
        {
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            
            System.out.println("1. Consulta i posti disponibili");
            System.out.println("2. Prenota posti");
            System.out.println("3. annulla posti");
            System.out.println("0. Esci");
            
            Scanner tastiera = new Scanner(System.in);
            String scelta = tastiera.nextLine();

            GestoreClient gestoreclient = new GestoreClient (scelta);
            
            


            out.println(scelta);


            
            String risposta = in.readLine();




        } catch (IOException e) 
        {
            e.printStackTrace();
        }
        


    }
}