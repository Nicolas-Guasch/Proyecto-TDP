package Controllers;

import GameObjects.Vector2;

public abstract class Behaviour {

    protected Vector2 direc;
    protected boolean fire;

    public abstract Vector2 getDir();


    public abstract boolean fire();
}
