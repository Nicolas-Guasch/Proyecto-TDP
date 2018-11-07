package Entities.Weapons;

import Observer.IBroadcaster;
import Engine.Components.IActivable;

public abstract class Arsenal implements IActivable {

    public abstract Arsenal clone();

    private boolean active = true;

    @Override
    public boolean isActive() {
        return active;
    }

    @Override
    public void setActive(boolean active) {
        this.active = active;
    }

    /**
     * Shoot the current Weapon Selected
     */
    public abstract void shoot();

    /**
     * Will select the next weapon in the collection
     */
    public abstract void switchCurrent();

    /**
     * Add a new weapon to the collection
     * @param weapon weapon to be added
     */
    public abstract void add(Weapon weapon);

    /**
     * consult if you have any weapon available
     * @return true if you have any weapon available
     */
    public abstract boolean isEmpty();

    /**
     * remove a new weapon from the collection
     * @param weapon weapon from be added
     */
    public abstract void remove(Weapon weapon);

    /**
     * get the current weapon selected
     * @return the current weapon selected
     */
    public abstract Weapon getCurrent();

    /**
     * destroys the arsenal
     */
    public abstract void destroy();

    /**
     * get a broadcaster to subscribe when there is important
     * changes in the arsenal
     * @return broadcaster
     */
    public abstract IBroadcaster<Boolean> observer();

    /**
     * returns an iterable of all usable weapons
     * @return weapons
     */
    public abstract Iterable<Weapon> weapons();

}
