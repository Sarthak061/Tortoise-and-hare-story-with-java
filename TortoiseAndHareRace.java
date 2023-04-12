import java.util.Random;

public class TortoiseAndHareRace {
    private static boolean raceFinished = false;

    public static void main(String[] args) throws InterruptedException {
        Thread tortoise = new Thread(new Tortoise(), "Tortoise");
        Thread hare = new Thread(new Hare(), "Hare");

        tortoise.start();
        hare.start();

        while (!raceFinished) {
            Thread.sleep(1000);
        }
    }

    static class Tortoise implements Runnable {
        private int distance = 0;

        @Override
        public void run() {
            while (!raceFinished) {
                distance += 1;
                System.out.println(Thread.currentThread().getName() + " has covered " + distance + " meters.");

                if (distance == 50) {
                    System.out.println("Tortoise has won the race!");
                    raceFinished = true;
                }

                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    static class Hare implements Runnable {
        private int distance = 0;
        private boolean sleeping = false;
        private final Random random = new Random();

        @Override
        public void run() {
            while (!raceFinished) {
                if (!sleeping) {
                    distance += 2;
                    System.out.println(Thread.currentThread().getName() + " has covered " + distance + " meters.");

                    if (distance >= 50) {
                        System.out.println("Hare has reached the mid-point of the race and decided to take a nap.");
                        sleeping = true;
                    }
                } else {
                    System.out.println(Thread.currentThread().getName() + " is sleeping...");
                    try {
                        Thread.sleep(random.nextInt(5000) + 5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + " has woken up!");
                    distance += 2;
                    System.out.println(Thread.currentThread().getName() + " has covered " + distance + " meters.");

                    if (distance >= 50) {
                        System.out.println("Hare has caught up to the tortoise but it's too late, the race is over.");
                        raceFinished = true;
                    }

                    sleeping = false;
                }

                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
