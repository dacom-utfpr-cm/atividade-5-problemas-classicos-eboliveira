import javax.management.monitor.Monitor;
import java.util.concurrent.Semaphore;

public class ReaderWriterPriority {
    private int numReaders = 0;
    private int numWriters = 0;
    private Semaphore mutex = new Semaphore(1);
    private Semaphore wlock = new Semaphore(1);
    final Object monitor = new Object();

    public void startRead() throws InterruptedException {
        mutex.acquire();
        if(numReaders == 0){
            while (numWriters > 0){
                synchronized (monitor){
                    monitor.wait();
                }
            }
            wlock.acquire();
        }
        numReaders+=1;
        mutex.release();
    }

    public void endRead() throws InterruptedException {
        mutex.acquire();
        numReaders-=1;
        if(numReaders == 0){
            wlock.release();
        }
        mutex.release();
    }

    public void startWrite() throws InterruptedException {
        numWriters+=1;
        wlock.acquire();
    }

    public void endWrite()
    {
        numWriters-=1;
        synchronized (monitor){
            monitor.notifyAll();
        }
        wlock.release();
    }

}
