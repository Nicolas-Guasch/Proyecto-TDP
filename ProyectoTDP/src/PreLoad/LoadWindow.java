package PreLoad;

import Assets.AssetStore;
import Audio.SoundManager;
import Game.TheGame;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class LoadWindow
{
    private static LoadWindow instance;
    public static LoadWindow getInstance(){
        if(instance==null){
            instance = new LoadWindow();
        }
        return instance;
    }

    private JFrame wind;
    private String[] everynames =
            ("only coin;point;rewardfire;rewardforce;" +
            "rewardhealth;rewardshield;mouseAim;life;levintro3;levintro2;levintro1;" +
            "wooky;bigbar;bossbar;shipplayer;commontie1;commontie2;hybridtie;shield;" +
                    "whitetie;soloship;vadership_a;vadership_b;vadership_c;" +
                    "bforcefield;forcefield;fexplo;bfexplo;exploA;exploB;" +
                    "bg_space;bg_water;bg_sand;black;").split(";");


    private LoadWindow(){
        wind = new JFrame();
        wind.setSize(600,600);
        wind.setUndecorated(true);
        wind.setLocationRelativeTo(null);
        wind.setContentPane(new JLabel(AssetStore.getIcon("preload")));
        wind.setVisible(true);
        wind.repaint();
        wind.setIconImage(AssetStore.getImage("main_icon"));
        //300,40 barrita

        JLabel bar = new JLabel(AssetStore.getIcon("loadbar"));

        bar.setBounds(150,550,0,40);

        wind.add(bar);

        wind.addKeyListener(new KeyListener() {
            int escapes = 0;
            @Override
            public void keyTyped(KeyEvent e) {}
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode()==KeyEvent.VK_ESCAPE){
                    escapes++;
                }
                if(escapes>=4){
                    System.exit(0);
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });


        float sub_load = (300f/everynames.length);

        System.out.println("listo0");
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("listo1");

        SoundManager.Instance();

        for (int j = 0; j < everynames.length; j++) {
            String s = everynames[j];
            int i = 0;
            bar.setBounds(150, 550, (int)(sub_load * j), 5);
            bar.repaint();
            wind.repaint();
            try {
                Thread.sleep(5);
                AssetStore.getIcon(s);
            } catch (Exception ignored) {
            }
            try {
                AssetStore.getIcon(s + "_shadow");
            } catch (Exception ignored) {
            }
            while (AssetStore.isThereIcon(s + "_" + i)) {
                try {
                    AssetStore.getIcon(s + "_" + i);
                } catch (Exception ignored) {
                }
                i++;
            }
        }


        System.out.println("listo2");

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("listo3");

        wind.dispose();
        TheGame.startGame();
    }




}
