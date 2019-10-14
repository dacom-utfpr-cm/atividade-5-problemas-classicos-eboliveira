import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        DinningController dinningController = new DinningController(5);
        ArrayList<Philosopher> philosophers = new ArrayList<>();

        for(int i=0; i<5; i++){
            Philosopher philosopher = new Philosopher(i, dinningController);
            philosophers.add(philosopher);
            dinningController.seatOnTable(philosopher);
        }
        for(Philosopher philosopher : philosophers){
            new Thread(philosopher).start();
        }
        dinningController.run();
    }
}
