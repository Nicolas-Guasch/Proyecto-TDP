package ShowThings;

import Engine.Component;

import javax.swing.*;
import java.awt.*;

public class Windor extends Component
{
    private JFrame lewindor;
    private JPanel panel;
    private JLabel label;

    public Windor()
    {
        //inicializo ventana
        lewindor = new JFrame();
        lewindor.setVisible(false);
        lewindor.setSize(800,500);
        lewindor.setBackground(new Color(0,150,80));
        lewindor.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        lewindor.setLayout(null);
        //inicializo panel
        panel = new JPanel();
        panel.setBounds(40,40,100,300);
        panel.setBackground(new Color(200,223,110));
        panel.setLayout(new FlowLayout());
        lewindor.add(panel);
        //inicializo el label
        label = new JLabel("Counter ");
        panel.add(label);

    }

    public void Show()
    {
        lewindor.setVisible(true);
    }

    int i=2;
    public void Update()
    {
        label.setText(""+ (i));
        panel.setSize(i*4,i*i/90);
        panel.setBackground( new Color(i,200-i,Math.min(255,i*2)));
        if(subiendo)
        {
            i= (int)(i*1.05)+2;
            subiendo = i<180;
            if(!subiendo){
                i=180;
            }
        }
        else{
            i--;
            subiendo = i<40;
        }
    }

    boolean subiendo=true;
}
