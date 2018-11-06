package UI;

import Assets.AssetStore;
import Engine.DoWhen;
import Engine.GameObject;
import ADTs.Vector2;
import GameData.GameSettings;
import Scripts.AlwaysLateral;
import RenderingSystem.Renderizable;
import RenderingSystem.SpriteData;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.concurrent.Callable;

public class UI
{


    private static UI instance;
    public static UI getInstance()
    {
        if(instance==null)
        {
            instance=new UI();
        }
        return instance;
    }








    private JComponent uiPanel;

    private JLabel pausePrompt;
    private JLabel losePrompt;

    private JComponent score;

    private ArrayList<SpriteData> levels;
    private SpriteData gameOver;
    private SpriteData win;


    private UI(){

        uiPanel = new JLabel(new ImageIcon("fondo"));

        // --------------levels--------------
        levels = new ArrayList<>();
        levels.add(new SpriteData("intro"));
        levels.add(new SpriteData("levintro1"));
        levels.add(new SpriteData("levintro2"));
        levels.add(new SpriteData("levintro3"));
        levels.add(new SpriteData("finalboss"));
        levels.add(new SpriteData("point"));
        levels.add(new SpriteData("point"));
        levels.add(new SpriteData("point"));
        levels.add(new SpriteData("point"));



        // ------------ pause prompt -------------
        pausePrompt = new JLabel(AssetStore.getIcon("wooky"));
        pausePrompt.setBounds(200,200,624,312);
        uiPanel.add(pausePrompt);
        pausePrompt.setVisible(false);


        // --------- game over --------------

        gameOver = new SpriteData("gameover");
        win = new SpriteData("youwin");


        // ------------- score ---------

        score = new Score();
        uiPanel.add(score);
    }



    public void removeUIComponent(UIComponent uiComponent) {
        uiComponent.foreach(uiPanel::remove);
    }

    public void addUIComponent(UIComponent s){
        s.foreach(uiPanel::add);
    }

    private Callable<Boolean> moviblePrompt(SpriteData prompt,int por)
    {
        GameObject goprompt = GameObject.getRoot().addChild();
        AlwaysLateral al = new AlwaysLateral(Vector2.UP(3));
        Renderizable rend = new Renderizable(prompt);
        goprompt.transform().setZcomponent(2);
        goprompt.addComponent(al);
        goprompt.setRenderer(rend);
        goprompt.transform().setPosition(Vector2.DOWN(600f*por));
        rend.show();
        Callable<Boolean> completed = ()-> goprompt.transform().position().y()>500f*por;
        new DoWhen(completed, goprompt::destroy);
        return completed;
    }


    //TODO: poner todos los niveles
    public Callable<Boolean> startLevel(int index)
    {
        return moviblePrompt(levels.get(index),1);
    }

    public Callable<Boolean> startLevelByString(String name, int x)
    {
        return moviblePrompt(new SpriteData(name),x);
    }



    public JComponent getUIPanel() {
        return uiPanel;
    }

    public void pause(boolean visible)
    {
        pausePrompt.setVisible(visible);
    }

    public Callable<Boolean> gameOver()
    {
        return moviblePrompt(gameOver,1);
    }





    public Callable<Boolean> win()
    {
        return moviblePrompt(win,1);
    }


}
