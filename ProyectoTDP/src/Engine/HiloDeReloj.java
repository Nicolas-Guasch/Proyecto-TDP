package Engine;

//TODO: tratar de hacer un safe remove de esto
final class HiloDeReloj extends  Thread
{

    private volatile boolean runClock = true;
    public volatile long current;
    @Override
    public void run() {
        try
        {
            super.run();
            do
            {
                //current = System.currentTimeMillis();
                current = current+1;
                Thread.sleep(1);
            } while (runClock);
        }
        catch (Exception e)//dejarlo aca por si vuelvo a poner el thread.sleep
        {
            e.printStackTrace();
        }
    }

    @Override
    public synchronized void start()
    {
        runClock=true;
        super.start();

    }

    public synchronized void stopClock()
    {
        runClock = false;
    }

}
