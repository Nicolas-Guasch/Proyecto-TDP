package DebugStuff;

import Entities.Entity;

public class Counters
{
    private static int dummyvisitor = 0;

    public static void CheckeoInnecesario(Entity ent, Entity con)
    {
        System.out.println("Checqueo innecesario en: "+ent.getClass()+" chequeando con "+con.getClass());
        System.out.println("total : " + (++dummyvisitor));
        try{throw new RuntimeException();}catch (Exception e){e.printStackTrace();}
    }
}
