package Scripts;
import ADTs.IVector2;
import Engine.Components.Transform;
import Engine.GameObject;
import Engine.IGameObject;

import java.util.ArrayList;
import java.util.List;

public class HyperSpace
{

    private static IGameObject obj;

    public static Jumper Jump(Transform toMove, IVector2 destiny, int frames, int delayFrames){
        checkObj();
        assert frames>0;
        List<IVector2> path = GetPath(toMove.position(),destiny,frames);
        return obj.addChild().addComponent(new Jumper(path,toMove,delayFrames));
    }

    private static void checkObj() {
        if(obj==null){
            System.out.println("hyperSpace::checkobj");
            obj = GameObject.getRoot().addChild();
        }
    }

    private static List<IVector2> GetPath(IVector2 src, IVector2 dest, int frames){
        IVector2 minPath = dest.sub(src).prod(1f/frames);
        List<IVector2> v = new ArrayList<IVector2>(frames+1);
        IVector2 lastPos = src;
        for(int i=0 ; i<frames-1 ; i++){
            lastPos = lastPos.sum(minPath);
            v.add(lastPos);
        }
        v.add(dest);
        return v;
    }


}

