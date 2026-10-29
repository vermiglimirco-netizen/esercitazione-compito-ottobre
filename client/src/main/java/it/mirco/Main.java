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
            int azione=Integer.parseInt(scelta);

            //GestoreClient gestoreclient = new GestoreClient (scelta);
            
            switch(azione)
            {
                case 1:
                    out.println("DISPONIBILI");
                break;
                case 2:
                    String aggiungiposti="0";
                    aggiungiposti = tastiera.nextLine();
                    out.println("PRENOTA " + aggiungiposti);
                break;
                case 3:
                    String rimuoviposti="0";
                    rimuoviposti = tastiera.nextLine();
                    out.println("ANNULLA " + rimuoviposti);
                break;
                case 0:
                    out.println("EXIT");
                break;
                default
                break;
            }


            out.println(scelta);


            
            String risposta = in.readLine();




        } catch (IOException e) 
        {
            e.printStackTrace();
        }
        


    }
}