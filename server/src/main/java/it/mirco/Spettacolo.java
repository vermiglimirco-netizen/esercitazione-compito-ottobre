package it.mirco;

public class Spettacolo
{    
    private int postirimanenti;
    private int postimax;

    public Spettacolo(int postimax)
    {
        this.postirimanenti = postimax;
        this.postimax = postimax;
    }
    
    public synchronized int annulla(int posti) 
    {
        if(posti<=postimax-postirimanenti)
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
