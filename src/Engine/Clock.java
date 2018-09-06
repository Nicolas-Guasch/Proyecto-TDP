package Engine;


import java.util.*;

public class Clock // NO! I don't want a singleton
{
    private static long initialTime = -1;
    private static Map<Object,Long> stamps;

    private static HiloDeReloj thread = new HiloDeReloj();


    public static void Start()
    {
        initialTime = 0;
        thread.start();
        stamps = new HashMap<>();
    }

    public static boolean HasStamp(Object reference)
    {
        check();
        return stamps != null && stamps.containsKey(reference);
    }

    public static float Time()//returns float seconds
    {
        check();
        return currentTimeMillis()/1000f;
    }

    public static void StampSomething(Object reference)
    {

        stamps.put(reference,System.currentTimeMillis());
    }

    public static void CleanStamps()
    {

        stamps.clear();
    }

    public static float TimeElapsed(Object reference)
    {

        if(!stamps.containsKey(reference))
            throw new RuntimeException("This Object has not reference");
        return (currentTimeMillis() - stamps.get(reference)) / 1000f;
    }

    public static long currentTimeNanos()
    {
        return System.nanoTime();
    }

    public static long currentTimeMillis()
    {
        return thread.current;
    }
    private static void check()
    {
        if(initialTime == -1)
            Start();
    }

}
