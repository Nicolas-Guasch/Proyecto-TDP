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
            URL img = AssetStore.class.getResource("./"+name+".png");
            if(img==null)img=AssetStore.class.getResource("./"+name+".gif");
            if(img==null)System.out.println(name+" not found");
            map.put(name.toLowerCase(),new ImageIcon(img));
            // luego cambiar por
            //levantar el .meta y ver el icon posta
        }
        return map.get(name.toLowerCase());
    }

    private static void checknullmap() {
        map = map==null ?new HashMap<>() : map;
    }
}
