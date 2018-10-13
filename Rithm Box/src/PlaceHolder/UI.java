package PlaceHolder;

import SoundSystem.Sound;
import SoundSystem.TinySound;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicInteger;

public class UI
{

    private static UI instance;
    private final JButton play;
    private final BeatSend<Object> beatsender;

    public static UI getInstance() {
        if(instance == null)
        {
            instance = new UI();
        }
        return instance;
    }


    JFrame frame;
    Container container;

    private Button[] kicks;
    private Button[] hihats;
    private Button[] claps;
    private Button[] bass;
    private Collection<Button[]> channels;


    private Thread sender;

    private UI()
    {
        TinySound.init();

        frame = new JFrame("Rithm Box");
        frame.setVisible(false);
        //frame.setUndecorated(true);
        frame.setSize(800,500);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        container = frame.getContentPane();
        container.setLayout(null);







        kicks = new Button[8];
        hihats = new Button[8];
        bass = new Button[8];
        claps = new Button[8];


        JLabel labelkick = new JLabel("kicks");
        JLabel labelhh = new JLabel("hh");
        JLabel labelclaps = new JLabel("claps");
        JLabel labelbass = new JLabel("bass");
        int g = 0;
        labelkick.setBounds(10,80+g,45,45);
        g+=60;
        labelhh.setBounds(10,80+g,45,45);
        g+=60;
        labelclaps.setBounds(10,80+g,45,45);
        g+=60;
        labelbass.setBounds(10,80+g,45,45);

        labelbass.setForeground(new Color(155, 151, 191));
        labelkick.setForeground(new Color(155, 151, 191));
        labelclaps.setForeground(new Color(155, 151, 191));
        labelhh.setForeground(new Color(155, 151, 191));

        container.add(labelkick);
        container.add(labelhh);
        container.add(labelclaps);
        container.add(labelbass);


        JSlider volKick = new JSlider();
        JSlider volHiHat = new JSlider();
        JSlider volClap = new JSlider();
        JSlider volBass = new JSlider();


        g = 0;
        volKick.setBounds(530,80+g,145,45);
        g+=60;
        volHiHat.setBounds(530,80+g,145,45);
        g+=60;
        volClap.setBounds(530,80+g,145,45);
        g+=60;
        volBass.setBounds(530,80+g,145,45);

        container.add(volKick);
        container.add(volHiHat);
        container.add(volClap);
        container.add(volBass);


        volKick.setBackground(new Color(17, 43, 41));
        volClap.setBackground(new Color(17, 43, 41));
        volBass.setBackground(new Color(17, 43, 41));
        volHiHat.setBackground(new Color(17, 43, 41));


        HashMap<JSlider, Button[]> slidervoltobutton = new HashMap<>();

        slidervoltobutton.put(volKick,kicks);
        slidervoltobutton.put(volHiHat,hihats);
        slidervoltobutton.put(volClap,claps);
        slidervoltobutton.put(volBass,bass);



        var soudkicks = TinySound.loadSound(Sounds.kicks);
        var soudhhs = TinySound.loadSound(Sounds.hihats);
        var soudclaps = TinySound.loadSound(Sounds.claps);
        var soudbass = TinySound.loadSound(Sounds.bassline1);

        for (int ind = 0; ind < 8; ind++) {

            kicks[ind] = new Button(new Color(32, 186, 116),soudkicks);
            kicks[ind].getComponent().setBounds(54*ind+80,80,35,45);
            kicks[ind].setSlider(volKick);
            container.add(kicks[ind].getComponent());


            hihats[ind] = new Button(new Color(102, 140, 48),soudhhs);
            hihats[ind].getComponent().setBounds(54*ind+80,140,35,45);
            hihats[ind].setSlider(volHiHat);
            container.add(hihats[ind].getComponent());

            claps[ind] = new Button(new Color(238, 144, 112),soudclaps);
            claps[ind].getComponent().setBounds(54*ind+80,200,35,45);
            claps[ind].setSlider(volClap);
            container.add(claps[ind].getComponent());


            bass[ind] = new Button(new Color(248, 67, 107),soudbass);
            bass[ind].getComponent().setBounds(54*ind+80,260,35,45);
            bass[ind].setSlider(volBass);
            container.add(bass[ind].getComponent());
        }

        container.setBackground(new Color(20, 24, 46));

        channels = new LinkedList<>();
        channels.add(hihats);
        channels.add(kicks);
        channels.add(claps);
        channels.add(bass);




        beatsender = new BeatSend<>(120,new Object());
        beatsender.suscribe(this::onsignal);
        sender = new Thread(beatsender);



        JTextField bpm = new JTextField("120");
        AtomicInteger lastbpm = new AtomicInteger(120);
        bpm.addActionListener((e)->{
            String text = bpm.getText();

            try{
                int nextbpm = Integer.parseInt(text);
                beatsender.setBpm(nextbpm);
                lastbpm.set(nextbpm);
            }catch (Exception ec){
                bpm.setText(""+lastbpm);
            }
        });

        bpm.setBounds(150,10,70,30);
        container.add(bpm);




        play = new JButton("PLAY");
        play.setBackground(new Color(191, 113, 91));
        play.setForeground(new Color(34, 0, 46));
        play.addActionListener(e->playbutton());
        play.setBounds(400,400,90,50);
        container.add(play);
        sender.start();


        ///// piano------

        Sound[] piano = new Sound[18];
        KeyListener[] listeners = new KeyListener[18];
        int index=0;

        for(char c : "zxcvbnasdfghqwerty".toCharArray()){
            piano[index] = TinySound.loadSound(UI.class.getResource(c+".wav"));
            listeners[index] = new MyKeyListener(c,piano[index]);
            play.addKeyListener(listeners[index]);
            index++;
        }
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);


    }


    private int i=0;


    private void onsignal(Object o)
    {
        i++;
        if(i==8)
        {
            i=0;
        }

        for(var line : channels)
        {
            for(int x=0 ; x<8 ; x++)
            {
                line[x].onLightChanges(false);
            }
            line[i].onLightChanges(true);
        }
    }

    public void show() {
        frame.setVisible(true);
    }

    boolean playing = false;
    void playbutton()
    {
        if(!playing){
            beatsender.playpause();
            play.setText("Pause");
        }
        else{
            beatsender.playpause();
            play.setText("Play");
        }
        playing = !playing;
    }


    private class MyKeyListener implements KeyListener {
        private final Sound sound;
        private final char c;

        public MyKeyListener(char c, Sound sound) {
            this.c = c;
            this.sound = sound;
        }

        @Override
        public void keyTyped(KeyEvent e) {

        }

        @Override
        public void keyPressed(KeyEvent e) {
            if(aceptable(e.getKeyChar(),c))
                sound.play();
        }

        private boolean aceptable(char get, char ch) {
            return get == ch || get == Character.toUpperCase(ch);
        }



        @Override
        public void keyReleased(KeyEvent e) {

        }
    }
}
