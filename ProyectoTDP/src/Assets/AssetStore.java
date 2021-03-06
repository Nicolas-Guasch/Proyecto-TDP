package Assets;


import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class AssetStore
{
    //public static URL BlueBullet = AssetStore.class.getResource("fondo.meta");

    private static Map<String,Icon> map;

    private static String [] subfolders = {"","Video/","UI/", "Stuff/", "Ships/", "Bullets/", "Animators/","Miniatures/"};

    public static boolean isThereIcon(String name){
        return map.containsKey(name.toLowerCase()) || getURL(name+".png")!=null || getURL(name+".gif")!=null;
    }

    public static Icon getIcon(String name) {
        checknullmap();
        if(!map.containsKey(name.toLowerCase()))
        {
            URL img = getURL(name+".png");
            if(img==null)img=getURL(name+".gif");
            assert img!=null : name+" not found";
            map.put(name.toLowerCase(),new ImageIcon(img));
        }
        return map.get(name.toLowerCase());
    }

    private static URL getURL(String file){
        URL res=null;
        for(String f:subfolders) {
            res = AssetStore.class.getResource("/Assets/" + f + file);
            if (res != null) {
                return res;
            }
        }
        return null;
    }

    private static void checknullmap() {
        map = map==null ?new HashMap<String, Icon>() : map;
    }

    public static Image getImage(String filename) {
        return((ImageIcon)getIcon(filename)).getImage();
    }
}
