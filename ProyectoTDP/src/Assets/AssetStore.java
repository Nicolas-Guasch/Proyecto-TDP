package Assets;

import Stuff.Paths;

import javax.swing.*;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class AssetStore
{
    public static URL BlueBullet = Paths.class.getResource("fondo.meta");


    private static Map<String,Icon> map;

    public static Icon getIcon(String name) {
        checknullmap();
        if(!map.containsKey(name.toLowerCase()))
        {
            map.put(name.toLowerCase(),new ImageIcon(AssetStore.class.getResource(name+".png")));
            // luego cambiar por
            //levantar el .meta y ver el icon posta
        }
        return map.get(name.toLowerCase());
    }

    private static void checknullmap() {
        map = map==null ?new HashMap<>() : map;
    }
}
