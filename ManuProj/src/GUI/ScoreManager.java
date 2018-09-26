package GUI;

import GameObjects.Player;

public class ScoreManager
{
    private Etiqueta Score;
    private Etiqueta VidaJugador;

    private static ScoreManager instance = null;

    public static ScoreManager getInstance(){
        if (instance==null)
            instance = new ScoreManager();
        return instance;

    }

    private ScoreManager(){
        Score = new Etiqueta();
        VidaJugador = new Etiqueta(Player.getInstance().getHealth());
        System.out.println(Score.getJLabel().getText());
        System.out.println(VidaJugador.getJLabel().getText());
    }

    public Etiqueta getScore(){
        return Score;
    }

    public Etiqueta getVida(){
        return VidaJugador;
    }

}
