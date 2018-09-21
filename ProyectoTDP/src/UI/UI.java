package UI;

import Engine.DoWhen;
import Engine.GameObject;
import Engine.Vector2;
import GameData.GameSettings;
import Scripts.AlwaysLateral;
import RenderingSystem.Renderizable;
import RenderingSystem.SpriteData;
import Stuff.Paths;

import javax.swing.*;
import java.util.ArrayList;
import java.util.concurrent.Callable;

public class UI
{
    // ----------------------------

    private static UI instance;
    private final JComponent lifeBar;
    private final int lifeBarHeight = 180;
    private final float lifeMax = GameSettings.GetInstance().PlayerData.getHealth();


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
    private JLabel loosePrompt;

    private ArrayList<SpriteData> levels;
    private SpriteData gameOver;


    private UI(){
        uiPanel = new JLabel(new ImageIcon(Paths.Background));

        // --------------levels--------------
        levels = new ArrayList<>();
        levels.add(new SpriteData(Paths.Level1));
        levels.add(new SpriteData(Paths.Level2));


        // ------------ pause prompt -------------
        pausePrompt = new JLabel(new ImageIcon(Paths.Pause));
        pausePrompt.setBounds(200,200,624,312);
        uiPanel.add(pausePrompt);
        pausePrompt.setVisible(false);

        // --------- game over --------------

        gameOver = new SpriteData(Paths.GameOver);


        // --------- LifeBar -------------




        var jl =new JLabel(new ImageIcon(Paths.LIFE));
        jl.setHorizontalAlignment(SwingConstants.LEFT);

        lifeBar = jl;
        lifeBar.setBounds(20,20,lifeBarHeight,30);
        uiPanel.add(lifeBar);



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
        rend.Show();
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
        lifeBar.setBounds(20,20, (int) ((life/lifeMax)*lifeBarHeight),30);
    }




}
