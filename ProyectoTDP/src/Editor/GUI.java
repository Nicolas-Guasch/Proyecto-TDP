package Editor;

import Assets.AssetStore;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

enum bulls
{
    Rectline,
    Hunter,
    DangerHunter,
    IntrinsecWay,
}
enum weaps{
    PhaseShifting,
    Front,
    Angular,
}


/*

la proligidad de esto es relativa, no va a ser parte de lo que entreguemos :P
mientras ande nos sirve, es para nosotros

 */
public class GUI
{

    private JPanel center;

    public static void main(String[] a){new GUI();} // poner el main en otro lado?

    private JFrame frame;
    private Container container;
    private weaps[] weapons;
    private bulls[] bullets;

    private JButton[] shipButtons;
    private Icon[] ships;

    private int currentShip =0;
    private JLabel currentShipLabel;

    private String[] ships_icons = {
            "CommonTie1.png",
            "CommonTie2.png",
            "HybridTie.png",
            "shipplayer.png",
            "vadership.png",
    };


    public GUI() {

        frame = new JFrame();

        initialize();
        labels();
        buttons();
        centerData();
        listeners();
        shipData();

        frame.setSize(100,100);
        frame.repaint();
        container.repaint();
        frame.setSize(900,700);
        //try{Thread.sleep(10);}catch (Exception e){}
        frame.setVisible(true);
    }

    private void centerData() {

        currentShipLabel = new JLabel();
        currentShipLabel.setBounds(25,25,100,100);
        center = new JPanel();
        center.setBounds(300,100,250,150);
        center.setBackground(new Color(8,12, 11));
        center.add(currentShipLabel);
        container.add(center);
        resetShipCenter();
    }

    private void resetShipCenter() {
        currentShipLabel.setIcon(shipButtons[currentShip].getIcon());
        center.repaint();
    }


    private JTextField name, speed;
    private JList weaponsset;
    private JComboBox behav;
    private void shipData()
    {
        JPanel dataPanel = new JPanel();
        dataPanel.setBounds(600,50,200,300);
        dataPanel.setLayout(null);
        dataPanel.setBackground(new Color(186, 157, 191));
        container.add(dataPanel);
        name = new JFormattedTextField();
        speed = new JFormattedTextField();
        var n = new JLabel("Name");
        var s = new JLabel("Speed");

        n.setBounds(10,10,100,30);
        name.setBounds(20,40,100,30);
        dataPanel.add(name);
        dataPanel.add(n);

        JSeparator js = new JSeparator();
        js.setOrientation(SwingConstants.HORIZONTAL);
        js.setBounds(20,90,100,30);
        dataPanel.add(js);

        s.setBounds(10,90,100,30);
        speed.setBounds(20,120,100,30);
        dataPanel.add(speed);
        dataPanel.add(s);


        behav = new JComboBox<>(new String[]{"",
                "Basic Top Line",
                "Hard Top Line",
                "Hybrid1",
                "Hybrid2",
                "Kamikazee",
        });
        var b = new JLabel("Ship Behaviour");
        b.setBounds(10,160,100,30);
        behav.setBounds(20,200,100,30);
        dataPanel.add(behav);
        dataPanel.add(b);

        var saveButton = new JButton("Save");
        saveButton.setBounds(20,240,100,40);
        saveButton.setBackground(new Color(0,0,0));
        saveButton.setFocusPainted(false);
        saveButton.setFocusable(false);
        saveButton.setForeground(new Color(255, 246, 162));
        dataPanel.add(saveButton);

    }

    private void labels()
    {
        var nship = new JLabel("Ship Creator");
        nship.setBounds(350,50,150,50);
        nship.setFont(new Font(nship.getName(), Font.PLAIN, 20));
        container.add(nship);

        var escto = new JLabel("Press Esc To Exit");
        escto.setBounds(750,5,100,50);
        escto.setFont(new Font(nship.getName(), Font.ITALIC, 12));
        container.add(escto);

    }

    private void buttons()
    {
        shipButtons = new JButton[ships_icons.length];
        JPanel buttonsPanel = new JPanel();


        JScrollPane scrollPane =  new JScrollPane(
            JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
            JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);


        scrollPane.setBounds(5,50,250,400);
        buttonsPanel.setBounds(0,0,250,900);
        container.add(scrollPane);
        scrollPane.add(buttonsPanel);

        for(int i=0 ; i< ships_icons.length; i++)
        {

            var name= ships_icons[i].substring(0,ships_icons[i].length()-4);

            shipButtons[i] = new JButton(AssetStore.getIcon(name));
            shipButtons[i].addActionListener(new ShipButtonListener(i));
            shipButtons[i].setBounds(30,i*140+40,150,130);
            buttonsPanel.add(shipButtons[i]);
            shipButtons[i].setBackground(new Color(0,0,0));
            shipButtons[i].setFocusPainted(false);
            shipButtons[i].setFocusable(false);

            shipButtons[i].repaint();

        }

        scrollPane.repaint();
        buttonsPanel.repaint();
        container.repaint();

    }

    private void listeners() {
        //listeners
        frame.addKeyListener(new KeyListener() {
            public void keyTyped(KeyEvent e) {}
            public void keyReleased(KeyEvent e) {}
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ESCAPE)
                {
                    System.exit(0);
                }
            }
        });


    }

    private void initialize(){
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(900,700);
        frame.setLocationRelativeTo(null);//lo pone al "centro"
        //frame.setUndecorated(true);//le saca la barra de arriba
        frame.setResizable(false);
        frame.setVisible(false);

        container = frame.getContentPane();
        container.setBackground(new Color(188, 191, 190));
        container.setLayout(null);
    }

    private class ShipButtonListener implements ActionListener
    {
        private final int index;

        public ShipButtonListener(int index)
        {
            this.index = index;
        }

        @Override
        public void actionPerformed(ActionEvent e)
        {
            for (JButton shipButton : shipButtons) {
                shipButton.setBackground(new Color(0,0,0));
            }
            shipButtons[index].setBackground(new Color(30,30,20));
            currentShip = index;
            resetShipCenter();
        }
    }
}
