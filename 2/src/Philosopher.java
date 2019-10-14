class Philosopher implements Runnable {
    private int id;
    private final DinningController controller;

    Philosopher(int initId, DinningController controller) {
        id = initId;
        this.controller = controller;
    }

    void eat() throws InterruptedException {
        System.out.println("Phil " + id + " giving forks");
        controller.giveForks(id);
        System.out.println("Phil " + id + " eating");
        Thread.sleep(1000);
        System.out.println("Phil " + id + " finished eat");
        controller.returnForks(id);
        System.out.println("Phil " + id + " returning forks\n");
    }

    @Override
    public void run() {
        while (true) {
            try {
                System.out.println("Phil " + id + " thinking");
                Thread.sleep(500);
                System.out.println("Phil " + id + " hungry");
                controller.wantEat(id);
                synchronized (this){
                    wait();
                }
            } catch (InterruptedException e) {
                return;
            }
        }
    }
}
