package it.mirco;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;

public class Main {
    public static void main(String[] args) {
        
        try (ServerSocket server = new ServerSocket(12345))
        {
            
            while (true) 
            {
                Socket socketClient = server.accept();
                
            }

        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        }





    }
}