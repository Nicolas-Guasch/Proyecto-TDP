package Scripts;

import ADTs.Vector2;
import Engine.Components.Transform;
import Engine.GameObject;

import java.util.ArrayList;
import java.util.List;

public class HyperSpace
{

    private static GameObject obj;

    public static Jumper Jump(Transform toMove, Vector2 destiny, int frames, int delayFrames){
        checkObj();
        assert frames>0;
        var path = GetPath(toMove.position(),destiny,frames);
        return obj.addChild().addComponent(new Jumper(path,toMove,delayFrames));
    }

    private static void checkObj() {
        if(obj==null){
            System.out.println("hyperSpace::checkobj");
            obj = GameObject.getRoot().addChild();
        }
    }

    private static List<Vector2> GetPath(Vector2 src, Vector2 dest, int frames){
        Vector2 minPath = dest.sub(src).prod(1f/frames);
        List<Vector2> v = new ArrayList<>(frames+1);
        Vector2 lastPos = src;
        for(int i=0 ; i<frames-1 ; i++){
            lastPos = lastPos.sum(minPath);
            v.add(lastPos);
        }
        v.add(dest);
        return v;
    }


}

