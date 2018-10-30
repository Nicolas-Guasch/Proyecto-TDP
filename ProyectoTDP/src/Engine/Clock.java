package Engine;


import java.util.*;

public class Clock // NO! I don't want a singleton
{


    public static long currentTimeNanos()
    {
        return System.nanoTime();
    }



}
