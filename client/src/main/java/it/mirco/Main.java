package it.mirco;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;



public class Main {
    public static void main(final String[] args) {
        
        try 
        (
            Socket socket = new Socket("localhost", 12345);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            Scanner tastiera = new Scanner(System.in);
        )
        {

            String scelta="default"; 
            while (!scelta.equals("0")) 
                {
                    
                    System.out.println("1. Consulta i posti disponibili");
                    System.out.println("2. Prenota posti");
                    System.out.println("3. annulla posti");
                    System.out.println("0. Esci");
                    
                scelta = tastiera.nextLine();
                int azione=Integer.parseInt(scelta);
                
                switch(azione)
                {
                    case 1:
                        out.println("DISPONIBILI");
                    break;
                    case 2:
                        String aggiungiposti="0";
                        aggiungiposti = tastiera.nextLine();
                        try 
                        {
                            int quantita = Integer.parseInt(aggiungiposti);
                        } 
                        catch (NumberFormatException e) 
                        {
                            System.out.println("ERRORE;Sintassi non valida");
                        }
                        out.println("PRENOTA " + aggiungiposti);
                    break;
                    case 3:
                        String rimuoviposti="0";
                        rimuoviposti = tastiera.nextLine();
                        out.println("ANNULLA " + rimuoviposti);
                    break;
                    case 0:
                        out.println("ESCI");
                    break;
                    default:
                        System.out.println("ERRORE;Sintassi non valida");
                    break;
                }                 
    
                String risposta = in.readLine();  
                System.out.println(risposta); 

            }    
            System.out.println("OK;Connessione in chiusura");
        } 
        catch (final IOException e) 
        {
            e.printStackTrace();
        }
        


    }
}