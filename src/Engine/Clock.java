package Engine;


import java.util.*;

public class Clock // NO! I don't want a singleton
{
    private static long initialTime = 0;
    private static Map<Object,Long> stamps;

    private static HiloDeReloj thread = new HiloDeReloj();


    public static void Start()
    {
        initialTime = currentTimeMillis();
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
        check();
        stamps.put(reference,System.currentTimeMillis());
    }

    public static void CleanStamps()
    {
        check();
        stamps.clear();
    }

    public static float TimeElapsed(Object reference)
    {
        check();
        if(!stamps.containsKey(reference))
            throw new RuntimeException("This Object has not reference");
        return (stamps.get(reference) - currentTimeMillis()) / 1000f;
    }

    public static long currentTimeMillis()
    {
        check();
        return thread.current;
    }
    private static void check()
    {
        if(initialTime == 0)
            Start();
    }

}
