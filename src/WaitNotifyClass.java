public class WaitNotifyClass {
    private final Object mon = new Object();
    private volatile char currentLetter = 'A';
    public static void main(String[] args) {
        WaitNotifyClass w = new WaitNotifyClass();
        Thread t1 = new Thread(() -> {
            w.printA();
        });
        Thread t2 = new Thread(() -> {
            w.printB();
        });
        Thread t3 = new Thread(() -> {
            w.printC();
        });
        t1.start();
        t2.start();
        t3.start();
    }

    public void printA() {
        synchronized (mon) {
            try {
             //   Thread.sleep(1000);
                for (int i = 0; i < 5; i++) {
                    while (currentLetter != 'A' ) {
                        mon.wait();
                    }
                    System.out.print("A");
                    currentLetter = 'B';
//                    mon.notify();
                    mon.notifyAll();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    public void printB() {
        synchronized (mon) {
            try {
//                Thread.sleep(900);
                for (int i = 0; i < 5; i++) {
                    while (currentLetter != 'B') {
                    mon.wait();
                }
                System.out.print("B");
                currentLetter = 'C';
//                mon.notify();
                mon.notifyAll();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
    public void printC() {
        synchronized (mon) {
            try {
//                Thread.sleep(800);
                for (int i = 0; i < 5; i++) {
                    while (currentLetter != 'C') {
                    mon.wait();
                    }
                    System.out.print("C");
                    currentLetter = 'A';
//                    mon.notify();
                    mon.notifyAll();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}