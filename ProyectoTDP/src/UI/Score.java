package UI;

import GameData.CurrentMatchData;
import RenderingSystem.SpriteRenderer;


import java.awt.*;

class Score extends SpriteRenderer
{
    private String text = " ";
    private Integer score = 0;


    Score()
    {
        setForeground(new Color(200,200,200));
        setFont(Font.decode("Star Jedi Hollow-40"));
        CurrentMatchData.getMatchData().OnScoreChanges.Suscribe(this::readChange);
    }

    private void readChange(Integer integer) {
        this.score = integer;
        repaint();
    }


    public void repaint()
    {
        setText(text+ score);
        super.repaint();
    }




}
