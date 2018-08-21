package Engine;

public class HiloDeReloj extends  Thread
{
    @Override
    public void run() {
        super.run();
        current = 0;
        while (true){
            try {
                Thread.sleep(1);
                current++;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public long current;

    @Override
    public synchronized void start() {
        super.start();
    }
}
