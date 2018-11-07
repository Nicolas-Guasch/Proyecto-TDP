package UI;

import ADTs.Vector2;
import ADTs.Vector3;
import Engine.GameObject;
import Entities.Weapons.Arsenal;
import Entities.Weapons.Weapon;
import GameData.GameSettings;
import RenderingSystem.Renderizable;
import RenderingSystem.SpriteData;
import Tools.Tools;

import javax.swing.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.function.Consumer;

public class ArsenalUI implements UIComponent {

    private final Arsenal arsenal;
    private HashMap<Weapon, Renderizable> map = new HashMap<>();
    private Grill grill ;


    private GameObject selector;

    public ArsenalUI(Arsenal arsenal){
        this.arsenal = arsenal;
        var sizeWindow = GameSettings.GetInstance().sizeWindow;

        grill = new Grill(
                new Vector2(
                        -sizeWindow.width/(2.3f),
                        -sizeWindow.height/(3f)),

                new Vector2(40,40),
                5);

        arsenal.observer().suscribe(this::listenArsenal);
        selector = GameObject.getRoot().addChild();
        Renderizable rend = new Renderizable(new SpriteData("selector"));
        rend.show();
        selector.setRenderer(rend);
        selector.transform().setPosition(new Vector2(10000,10000));
    }

    private void listenArsenal(boolean b){
        if(b){
            refresh();
        }
    }

    private void refresh(){
        if(selector.transform()==null)return;
        grill.repaint();
        addingCheck();
        checkSelected();
        checkRemoval();
        grill.repaint();
    }

    private void checkRemoval() {
        for (Weapon weapon : new HashSet<>(map.keySet())) {
            if(!Tools.contains(arsenal.weapons(),weapon)){
                Renderizable rend = map.get(weapon);
                rend.hide();
                rend.setActive(false);
                grill.repaint();
                rend.gameObject().destroy();
                map.remove(weapon);
            }
        }
    }

    private void checkSelected() {
        Weapon weapon = arsenal.getCurrent();
        var rend = map.get(weapon);
        Vector2 planepos = rend.transform().position();
        Vector3 v = planepos.v3(10);
        selector.transform().setPosition(v);
    }

    private void addingCheck() {
        for (Weapon weapon : arsenal.weapons()) {
            if (map.containsKey(weapon)) continue; //reducir anidamiento

            SpriteData sd = new SpriteData(weapon.spriteName());
            Renderizable rend = new Renderizable(sd);
            rend.show();
            map.put(weapon, rend);
            grill.add(rend);
            selector.addChild().setRenderer(rend);
        }
    }

    @Override
    public Iterable<JComponent> getComponents() {
        return grill.getComponents();
    }

    @Override
    public void foreach(Consumer<JComponent> operation) {
        grill.foreach(operation);
    }

    public void dispose() {
        selector.destroy();
    }
}
