package Assets;


import javax.swing.*;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class AssetStore
{
    //public static URL BlueBullet = AssetStore.class.getResource("fondo.meta");


    private static Map<String,Icon> map;

    public static Icon getIcon(String name) {
        checknullmap();
        if(!map.containsKey(name.toLowerCase()))
        {
            URL img = getURL(name+".png");
            if(img==null)img=getURL(name+".gif");
            if(img==null)System.out.println(name+" not found");
            map.put(name.toLowerCase(),new ImageIcon(img));
            // luego cambiar por
            //levantar el .meta y ver el icon posta
        }
        return map.get(name.toLowerCase());
    }

    private static URL getURL(String file){
        String [] subfolders = {"","Video/","UI/","Stuff/","Ships/","Bullets/"};
        URL res=null;
        for(String f:subfolders){
            res = AssetStore.class.getResource("./"+f+file);
            if(res!=null)return res;
        }
        return null;
    }

    private static void checknullmap() {
        map = map==null ?new HashMap<>() : map;
    }
}
