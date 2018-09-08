package ParaTestear;


import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class VerComoUsarCanvas
{
    public static void main(String[] args)
    {
        //inicializo ventana
        JFrame wind;
        wind = new JFrame();
        wind.setSize(300,300);
        wind.setVisible(true);
        //wind.setBackground(Color.BLACK);
        wind.setContentPane(new JLabel(new ImageIcon(Paths.Background)));
        wind.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        wind.setLayout(null);
        Container container;
        container = wind.getContentPane();
        container.setBackground(new Color(0,0,0,0));
        container.setBounds(0,0,0,0);
        container.setSize(250,250);

        //---------- poner cosas -----------





    }

    private static void Action(){

    }
}
