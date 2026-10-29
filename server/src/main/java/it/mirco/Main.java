package it.mirco;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;

public class Main {
    public static void main(String[] args) {
        
        Spettacolo spettacolo = new Spettacolo();

        try (ServerSocket server = new ServerSocket(12345))
        {
            
            while (true) 
            {
                Socket socketClient = server.accept();

                Thread thread = new Thread(new MioTask());
                thread.start();
            }

        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        }





    }
}