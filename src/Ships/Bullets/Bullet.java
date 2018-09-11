package Ships.Bullets;

import BufferSystem.IClonable;
import Engine.Component;
import Engine.GameObject;
import RenderingSystem.Renderizable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;

public class Bullet implements IClonable<Bullet>
{
    private GameObject referenced;
    private Collection<BulletBehaviour> components;
    private AbstractBulletLauncher launcher;



    public Bullet(GameObject parent, Collection<BulletBehaviour> components)
    {
        referenced = parent.addChild(components);
        this.components = components;
    }

    public Bullet(GameObject parent)
    {
        referenced = parent.addChild();
        this.components = new LinkedList<>();
    }

    @Override
    public Bullet clone()
    {
        Collection<Component> comps = new ArrayList<>(components.size());
        components.forEach(c -> {
            BulletBehaviour b = (BulletBehaviour)c.clone();
            comps.add(b);
        });
        Bullet bull = new Bullet(referenced.getParent(),components);
        GameObject g = bull.getReferenced();
        g.addRenderer(referenced.getRenderer().clone());
        g.addCollider(referenced.getCollider().clone());
        return bull;
    }


    public Iterable<BulletBehaviour> getComponents()
    {
        return components;
    }

    public void addComponent(BulletBehaviour component)
    {
        components.add(component);
        referenced.addComponent(component);
    }
    public void removeComponent(BulletBehaviour component)
    {
        components.remove(component);
        referenced.addComponent(component);
    }


    public GameObject getReferenced() {
        return referenced;
    }

    public void setReferenced(GameObject referenced) {
        this.referenced = referenced;
    }


    public void setBackToLauncher(ReportDeath btl)
    {
        btl.OnDeath().Suscribe(this::BackToLauncher);
    }

    private void BackToLauncher(boolean b)
    {
        //launcher.Recycle(this);
    }

    public AbstractBulletLauncher getLauncher() {
        return launcher;
    }

    public void setLauncher(AbstractBulletLauncher launcher) {
        this.launcher = launcher;
    }
}
