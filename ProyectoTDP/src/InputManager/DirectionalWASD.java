package InputManager;

import ADTs.Vector2;

public class DirectionalWASD extends AbstractDirectionalInput
{
    private AbstractContinueInput W,A,S,D;
    private boolean Xblocked,Yblocked;
    public DirectionalWASD()
    {
        W = new ContinueKeyInput("wW");
        A = new ContinueKeyInput("aA");
        S = new ContinueKeyInput("Ss");
        D = new ContinueKeyInput("dD");
        Xblocked = false;
        Yblocked = false;
    }

    public void lockX(){Xblocked=true;}
    public void lockY(){Yblocked = true;}
    public void unLockX(){Xblocked = false;}
    public void unLockY(){Yblocked = false;}

    @Override
    public void Destroy() {
        W.Destroy();
        D.Destroy();
        S.Destroy();
        A.Destroy();
    }

    @Override
    public Vector2 Direction()
    {
        float x = 0, y = 0;
        x += D.happens() &&!Xblocked ? 1:0;
        x -= A.happens() &&!Xblocked ? 1:0;
        y += W.happens() &&!Yblocked ? 1:0;
        y -= S.happens() &&!Yblocked ? 1:0;
        return new Vector2(x,y).norma();
    }
}
