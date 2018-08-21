package Engine;

import java.lang.*;


import java.util.*;

public class Clock // NO! I don't want a singletone
{
    private static long initialTime = 0;
    private static Map<Object,Long> stamps;

    private static HiloDeReloj Reloj = new HiloDeReloj();


    public static void Start()
    {

        initialTime = System.currentTimeMillis();

        Reloj.start();

        stamps = new HashMap<>();
    }

    public static boolean HasStamp(Object reference)
    {
        if(initialTime == 0)
            Start();
        return stamps != null && stamps.containsKey(reference);
    }

    public static float Time()//returns float seconds
    {
        return System.currentTimeMillis()/1000f;
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
        return (stamps.get(reference) - System.currentTimeMillis()) / 1000f;
    }

    public static long currentTimeMillis()
    {

        return Reloj.current;
    }

}
