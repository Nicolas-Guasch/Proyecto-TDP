package UI;

import ADTs.Vector2;
import Assets.AssetStore;
import GameData.GameSettings;

import javax.swing.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;
import java.util.function.Consumer;

public class ShipStatus implements UIComponent
{
    private final float lifeMax = GameSettings.GetInstance().PlayerData.getHealth();
    private final JComponent lifeBar;
    private final JComponent decorate;
    private final int lifeBarHeight = 230;
    private final Vector2 point;
    private List<JComponent> all;




    public ShipStatus(Vector2 point)
    {
        this.point = point;
        int x = (int) point.x();
        int y = (int) point.y();

        JLabel jl =new JLabel(AssetStore.getIcon("life"));
        jl.setHorizontalAlignment(SwingConstants.LEFT);

        lifeBar = jl;
        lifeBar.setBounds(x,y,lifeBarHeight,10);

        decorate = new JLabel(AssetStore.getIcon("bigbar"));
        decorate.setBounds(x-88,y-170,345,350);




        all = new LinkedList<>();
        all.add(decorate);
        all.add(lifeBar);
    }

    public void PlayerLife(int life)
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
    public void foreach(Consumer<JComponent> visitor) {
        all.forEach(visitor);
    }


}
