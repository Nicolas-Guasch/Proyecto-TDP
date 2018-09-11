package ParaTestear;


import RenderingSystem.LayerTable;
import Stuff.Paths;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.TreeMap;

public class VerComoUsarCanvas
{
    private static JLabel lab1,lab2;

    public static void main(String[] args)
    {
        //inicializo ventana
        JFrame wind;
        wind = new JFrame();
        wind.setSize(300,300);
        wind.setVisible(true);
        wind.setBackground(Color.BLACK);
        //wind.setContentPane(new JLabel(new ImageIcon(Paths.Background)));
        wind.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        wind.setLayout(null);
        Container container;
        container = wind.getContentPane();
        container.setBackground(new Color(0,0,0,0));
        container.setBounds(0,0,0,0);
        container.setSize(250,250);
        container.setLayout(null);

        //---------- poner cosas -----------

        lab1 = new JLabel();
        //lab1.setBackground(Color.ORANGE);
        lab1.setIcon(new ImageIcon(Paths.Alcon));
        lab1.setBounds(20,20,90,90);

        lab2 = new JLabel();
        //lab2.setBackground(Color.RED);
        lab2.setIcon(new ImageIcon(Paths.NaveTester));
        lab2.setBounds(20,20,90,90);

        container.add(lab1);
        container.add(lab2);

        container.setComponentZOrder(lab1,1);
        container.setComponentZOrder(lab2,1);




        wind.addKeyListener(new KeyListener() {

            public void keyTyped(KeyEvent e) {
                if(e.getKeyChar() == 'a')
                {
                    Action1();
                }
                if(e.getKeyChar() == 'd')
                {
                    Action2();
                }
                wind.repaint();
            }

            public void keyPressed(KeyEvent e) {} public void keyReleased(KeyEvent e) {}
        });

    }

    private static void Action1()
    {
        lab1.setBounds(lab1.getX()+2,lab1.getY(),lab1.getWidth(),lab1.getHeight());
    }
    private static void Action2()
    {
        lab2.setBounds(lab2.getX()+2,lab2.getY(),lab2.getWidth(),lab2.getHeight());
    }
}
