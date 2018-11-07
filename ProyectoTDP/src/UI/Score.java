package UI;

import GameData.CurrentMatchData;
import GameData.GameSettings;


import javax.swing.*;
import java.awt.*;

class Score extends JLabel
{
    private String text = " ";
    private Integer score = 0;


    Score()
    {
        setForeground(new Color(200,200,200));
        setFont(Font.decode("Star Jedi Hollow-40"));
        CurrentMatchData.getMatchData().OnScoreChanges.suscribe(this::readChange);
        var x1 = GameSettings.GetInstance().sizeWindow.width/2;
        var y1 = GameSettings.GetInstance().sizeWindow.height/2;
        setBounds(x1,50,300,100);
    }

    private void readChange(Integer integer) {
        this.score = integer;
        repaint();
    }


    public void repaint()
    {
        setVisible(true);
        setText(text+ score);
        super.repaint();
    }




}
