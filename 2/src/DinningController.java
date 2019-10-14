import java.util.ArrayList;
import java.util.concurrent.Semaphore;

class DinningController implements Runnable{

    private final int n;
    private Semaphore[] fork;
    private ArrayList<Integer> queue = new ArrayList<Integer>();
    private Semaphore mutex = new Semaphore(1);
    private ArrayList<Philosopher> philosofers = new ArrayList<>();

    DinningController(int initN) {
        n = initN;
        fork = new Semaphore[initN];
        for (int i = 0; i < initN; i++) {
            fork[i] = new Semaphore(1);
        }
    }

    void wantEat(Integer id) throws InterruptedException {
        mutex.acquire();
        queue.add(id);
        mutex.release();
    }

    void giveForks(int id) throws InterruptedException {
        fork[id].acquire();
        fork[(id + 1) % n].acquire();
    }

    void returnForks(int id) {
        fork[id].release();
        fork[(id + 1) % n].release();
    }

    void seatOnTable(Philosopher philosopher){
        philosofers.add(philosopher);
    }

    @Override
    public void run() {
        while(true){
            try {
                mutex.acquire();
                int queueSize = queue.size();
                for(int i = 0; i < queueSize; i++){
                    synchronized (philosofers.get(queue.get(0))){
                        philosofers.get(queue.get(0)).eat();
                        philosofers.get(queue.get(0)).notify();
                    }
                    queue.remove(queue.get(0));
                }
                mutex.release();
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
