package GUI;

import GameObjects.Player;
import javax.swing.*;
public class Etiqueta {

    private JLabel label;
    private int contador;

    public Etiqueta(){
        contador = 0;
    label = new JLabel();
        label.setText("Score: "+contador);
        label.setBounds(40,40,100,50);
}

    public Etiqueta(int n){

        contador = n;
        label = new JLabel();
        label.setText("Vida: "+contador);
        label.setBounds(40,80,100,50);
    }

    public void modificar(int n){
        contador+=n;
        label.setText("Score: "+contador);
        System.out.println("Score: "+contador);
    }


    public JLabel getJLabel(){

        return label;
    }
}
