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






    private final ShipStatus ShipStatus;

    private JComponent uiPanel;

    private JLabel pausePrompt;
    private JLabel losePrompt;

    private JLabel score;

    private ArrayList<SpriteData> levels;
    private SpriteData gameOver;
    private SpriteData win;


    private UI(){

        uiPanel = new JLabel(new ImageIcon("fondo"));

        // --------------levels--------------
        levels = new ArrayList<>();
        levels.add(new SpriteData("levintro1"));
        levels.add(new SpriteData("levintro2"));



        // ------------ pause prompt -------------
        pausePrompt = new JLabel(AssetStore.getIcon("wooky"));
        pausePrompt.setBounds(200,200,624,312);
        uiPanel.add(pausePrompt);
        pausePrompt.setVisible(false);


        // --------- game over --------------

        gameOver = new SpriteData("gameover");
        win = new SpriteData("youwin");


        // --------- LifeBar -------------
        ShipStatus = new ShipStatus(new Vector2(100,100));
        ShipStatus.foreach(uiPanel::add);


        // ------------- score ---------

        score = new Score();
        score.setBounds(650,10,300,50);
        uiPanel.add(score);

    }

    private Callable<Boolean> moviblePrompt(SpriteData prompt)
    {
        GameObject goprompt = GameObject.getRoot().addChild();
        AlwaysLateral al = new AlwaysLateral(Vector2.UP(3));
        Renderizable rend = new Renderizable(prompt);
        goprompt.getTransform().setZcomponent(2);
        goprompt.addComponent(al);
        goprompt.setRenderer(rend);
        goprompt.getTransform().setPosition(Vector2.DOWN(600));
        rend.show();
        Callable<Boolean> completed = ()-> goprompt.getTransform().position().y()>500;
        new DoWhen(completed, goprompt::Destroy);
        return completed;
    }


    //TODO: poner todos los niveles
    public Callable<Boolean> startLevel(int index)
    {
        return moviblePrompt(levels.get(index));
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
        return moviblePrompt(gameOver);
    }


    public void PlayerLife(int life)
    {
        ShipStatus.PlayerLife(life);
    }


    public Callable<Boolean> win()
    {
        return moviblePrompt(win);
    }
}
