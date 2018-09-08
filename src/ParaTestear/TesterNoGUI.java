package ParaTestear;

import RenderingSystem.LayerTable;

public class TesterNoGUI
{


    public static void main(String[] args)
    {

        var p = new LayerTable<Float,Float>();

        p.putOrMove(0f,2f);
        p.putOrMove(3f,2f);


        System.out.println(p.Count());
    }
}
