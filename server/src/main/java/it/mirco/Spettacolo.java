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

public class Spettacolo implements Runnable
{

    private Socket clientsocket;
        
    public Spettacolo(Socket clientsocket)
    {
        this.clientsocket = clientsocket;
    }

    @Override
    public void run() 
    {       
        try 
        (
            Socket socket = clientsocket;
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        ) 
        {
            String risposta = in.readLine();
            while (!risposta.equals("EXIT")) 
            {
                String stringa=modificarisorsa(risposta);
                System.out.println(stringa);
                risposta = in.readLine();
            }           
        } 
        catch (IOException e) 
        {
            System.err.println(e.getMessage());
        }           
    }    
    
    public synchronized String modificarisorsa(String risposta) 
    {
        int postimax = 30;
        int postioccupati = 0;
        int postirimanenti=postimax-postioccupati;
        String[] parti = risposta.split("\\s+");
        String fattore1=parti[0];
        int quantita=0;
        switch (fattore1) 
        {
            case "DISPONIBILI":
                    if(parti[1]!=null)
                    return "ERRORE;Sintassi non valida";
                return "OK;Posti disponibili: "+ postirimanenti;
            case "PRENOTA":
                try 
                {
                    if(parti[1]==null)
                        return "ERRORE;Sintassi non valida";
                    if(parti[2]!=null)
                        return "ERRORE;Sintassi non valida";   
                    quantita = Integer.parseInt(parti[1]);
                    if(quantita<0)
                        return "ERRORE;La quantità deve essere positiva";
                } 
                catch (NumberFormatException e) 
                {
                    return "ERRORE;Quantità non valida";
                }
                if(quantita<=postirimanenti)
                {
                    postirimanenti-=quantita;
                    return "OK;Prenotazione effettuata;Disponibili: "+ postirimanenti;
                }
                else
                    return "ERRORE;Posti insufficienti;Disponibili: "+ postirimanenti;
            case "ANNULLA":                
                try 
                {
                    if(parti[1]==null)
                        return "ERRORE;Sintassi non valida";
                    if(parti[2]!=null)
                        return "ERRORE;Sintassi non valida";                    
                    quantita = Integer.parseInt(parti[1]);
                    if(quantita<0)
                        return "ERRORE;La quantità deve essere positiva";
                } 
                catch (NumberFormatException e) 
                {
                    return "ERRORE;Quantità non valida";
                }
                if(quantita<=30-postirimanenti)
                {
                    postirimanenti+=quantita;
                    return "OK;Annullamento effettuato;Disponibili: "+ postirimanenti;
                }
                else
                    return "ERRORE;Annullamento non consentito;Disponibili: "+ postirimanenti;
            case "ESCI":
                if(parti[1]!=null)
                    return "ERRORE;Sintassi non valida";
                return "OK;Connessione in chiusura";
                case "":
                return "ERRORE;Sintassi non valida";
            default:
                return "ERRORE;Comando sconosciuto";
        }                
    }
}
