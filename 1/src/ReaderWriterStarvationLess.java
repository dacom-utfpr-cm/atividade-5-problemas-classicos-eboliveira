import java.util.concurrent.Semaphore;

public class ReaderWriterStarvationLess {
    private int numReaders = 0;
    private Semaphore mutex = new Semaphore(1, true);
    private Semaphore wlock = new Semaphore(1, true);

    public void startRead() throws InterruptedException {
        mutex.acquire();
        if(numReaders == 0){
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
        wlock.acquire();
    }

    public void endWrite(){
        wlock.release();
    }

}
