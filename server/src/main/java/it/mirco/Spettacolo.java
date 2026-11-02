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

public class Spettacolo
{    
    private int postirimanenti;

    public Spettacolo(int postirimanenti)
    {
        this.postirimanenti = postirimanenti;
    }
    
    public synchronized int annulla(int posti) 
    {
        if(posti<=postirimanenti)
        {
            postirimanenti += posti;
            return postirimanenti;
        }
        else
            return -1;
    }
    public synchronized int prenota(int posti) 
    {
        if(posti<=postirimanenti)
        {
            postirimanenti -= posti;
            return postirimanenti;
        }
        else 
            return -1;
    }
    public synchronized int disponibili() 
    {
        return postirimanenti;
    }
}
