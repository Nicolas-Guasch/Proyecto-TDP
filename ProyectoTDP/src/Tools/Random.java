package Tools;

public class Random
{


    /**
     *
     * @return random float value between 0 and 1
     */
    public static float value()
    {
        float f = (System.currentTimeMillis()*3>>2<<6)/(float)System.nanoTime();
        f = f>1?(float)Math.cos(f)*2-1:f;
        return f;
    }
    /**
     *
     * @param min include
     * @param max exclude
     * @return
     */
    public static int value(int min,int max)
    {
        if(max<min){
            int aux = min;
            min = max;
            max = aux;
        }
        int r = (max-min);
        if(Math.abs(r)<0.01f){
            r = 1;
        }
        return (int)((Math.abs((System.currentTimeMillis()*max>>7<<min)*System.nanoTime()))%r)+min;
    }

}
