package it.mirco;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class GestoreClient  implements Runnable
{
    private Socket clientsocket;
    private Spettacolo spettacolo;
        
    public GestoreClient(Socket clientsocket, Spettacolo spettacolo) {
        this.clientsocket = clientsocket;
        this.spettacolo = spettacolo;
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
        String[] parti = risposta.split("\\s+");
        String fattore1=parti[0];
        int quantita=0;
        switch (fattore1) 
        {
            case "DISPONIBILI":
                    if(parti[1]!=null)
                    return "ERRORE;Sintassi non valida";
                return "OK;Posti disponibili: "+ spettacolo.disponibili();
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
                if(spettacolo.prenota(quantita)!=-1)      
                    return "OK;Prenotazione effettuata;Disponibili: "+ spettacolo.disponibili();
                else
                    return "ERRORE;Posti insufficienti;Disponibili: " + spettacolo.disponibili();
                    
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
                if(spettacolo.annulla(quantita)!=-1)
                {
                    return "OK;Annullamento effettuato;Disponibili: "+ spettacolo.disponibili();
                }
                else
                    return "ERRORE;Annullamento non consentito;Disponibili: "+ spettacolo.disponibili();
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
