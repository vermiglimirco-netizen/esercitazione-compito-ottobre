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
    int postimax = 30;
    int postioccupati = 0;
    int postirimanenti=postimax-postioccupati;
    
    public synchronized int annulla(int posti) 
    {}
    public synchronized int prenota(int posti) 
    {}
    public synchronized int disponibili() 
    {}
}
