// Autor: Eduardo Barbosa de Oliveira       13/10/2019

// Descrição: Implemente uma solução com monitor para o problema do Produtor-Consumidor usando um buffer circular.

public class Main {
    public static void main(String[] args) throws InterruptedException {
        // Test ReaderWriter without starvation
        ReaderWriterStarvationLess readerWriterStarvationLess = new ReaderWriterStarvationLess();

        new Thread(() -> {
            try {
                readerWriterStarvationLess.startWrite();
                System.out.println("Escrevendo");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            try {
                readerWriterStarvationLess.startRead();
                System.out.println("Lendo");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(() -> {
            try {
                readerWriterStarvationLess.startWrite();
                System.out.println("Escrevendo");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(() -> {
            try {
                readerWriterStarvationLess.startRead();
                System.out.println("Lendo");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        Thread.sleep(1000);
        readerWriterStarvationLess.endWrite();
        Thread.sleep(1000);
        readerWriterStarvationLess.endWrite();
        Thread.sleep(1000);
        readerWriterStarvationLess.endRead();
        readerWriterStarvationLess.endRead();

        // Test ReaderWriter writer priority
        ReaderWriterPriority readerWriterPriority = new ReaderWriterPriority();

        new Thread(() -> {
            try {
                readerWriterPriority.startWrite();
                System.out.println("Escrevendo");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            try {
                readerWriterPriority.startRead();
                System.out.println("Lendo");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            try {
                readerWriterPriority.startRead();
                System.out.println("Lendo");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            try {
                readerWriterPriority.startRead();
                System.out.println("Lendo");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            try {
                readerWriterPriority.startWrite();
                System.out.println("Escrevendo");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            try {
                readerWriterPriority.startWrite();
                System.out.println("Escrevendo");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        Thread.sleep(1000);
        readerWriterPriority.endWrite();
        Thread.sleep(1000);
        readerWriterPriority.endWrite();
        Thread.sleep(1000);
        readerWriterPriority.endWrite();
        Thread.sleep(1000);
        readerWriterPriority.endRead();
        readerWriterPriority.endRead();
        readerWriterPriority.endRead();

    }
}
