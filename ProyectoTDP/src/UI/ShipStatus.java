package UI;

import Assets.AssetStore;
import GameData.GameSettings;

import javax.swing.*;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;

public class ShipStatus implements UIComponent
{
    private final float lifeMax = GameSettings.GetInstance().PlayerData.getHealth();
    private final JComponent lifeBar;
    private final JComponent decorate;
    private final int lifeBarHeight = 180;
    private List<JComponent> all;

    public ShipStatus(){
        JLabel jl =new JLabel(AssetStore.getIcon("life"));
        jl.setHorizontalAlignment(SwingConstants.LEFT);

        lifeBar = jl;
        lifeBar.setBounds(85,62,lifeBarHeight,30);

        decorate = new JLabel(AssetStore.getIcon("bigbar"));
        decorate.setBounds(10,-50,250,255);




        all = new LinkedList<>();
        all.add(lifeBar);
        all.add(decorate);
    }

    public void PlayerLife(int life)
    {
        lifeBar.setBounds(85,62, (int) ((life/lifeMax)*lifeBarHeight),30);
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
