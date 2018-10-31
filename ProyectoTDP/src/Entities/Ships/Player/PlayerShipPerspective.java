package Entities.Ships.Player;

import Assets.AssetStore;
import Engine.Component;
import InputManager.AbstractContinueInput;
import InputManager.ContinueKeyInput;
import RenderingSystem.Renderizable;

import javax.swing.*;

public class PlayerShipPerspective extends Component
{
    private Icon left,right,middle;
    private final Renderizable renderizable;
    private AbstractContinueInput a,d;
    public PlayerShipPerspective(Renderizable renderizable){
        this.renderizable = renderizable;
        middle = renderizable.Sprite().getIcon();
        left = AssetStore.getIcon("shipplayer_left");
        right = AssetStore.getIcon("shipplayer_right");
        a = new ContinueKeyInput("aA");
        d = new ContinueKeyInput("dD");
    }

    @Override
    public void update() {
        if (!a.happens() && !d.happens()) {
            renderizable.Sprite().setIcon(middle);
            return;
        }
        if (a.happens() && !d.happens()) {
            renderizable.Sprite().setIcon(left);
            return;
        }
        if (d.happens() && !a.happens()) {
            renderizable.Sprite().setIcon(right);
        }
    }
}
