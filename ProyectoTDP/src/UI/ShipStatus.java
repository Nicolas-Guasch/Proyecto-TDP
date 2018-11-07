package UI;

import ADTs.Vector2;
import Assets.AssetStore;
import Observer.IBroadcaster;

import javax.swing.*;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;

public class ShipStatus implements UIComponent
{
    private float lifeMax;
    private final JComponent lifeBar;
    private final JComponent decorate;
    private final int lifeBarHeight = 230;
    private final Vector2 point;
    private List<JComponent> all;




    public ShipStatus(Vector2 point, IBroadcaster<Float> shipHealth, String iconName, float lifemax)
    {
        this.lifeMax =lifemax;
        this.point = point;
        int x = (int) point.x();
        int y = (int) point.y();

        JLabel jl =new JLabel(AssetStore.getIcon("life"));
        jl.setHorizontalAlignment(SwingConstants.LEFT);

        lifeBar = jl;
        lifeBar.setBounds(x,y,lifeBarHeight,10);

        decorate = new JLabel(AssetStore.getIcon(iconName));
        decorate.setBounds(x-88,y-170,345,350);

        all = new LinkedList<>();
        all.add(decorate);
        all.add(lifeBar);

        shipHealth.suscribe(this::HealthShip);
    }

    public void HealthShip(float life)
    {
        int x = (int) point.x();
        int y = (int) point.y();
        lifeBar.setBounds(x,y, (int) ((life/lifeMax)*lifeBarHeight),10);
    }

    @Override
    public Iterable<JComponent> getComponents()
    {
        return all;
    }

    @Override
    public void foreach(Consumer<JComponent> operation) {
        all.forEach(operation);
    }


}
