package Game;

import Assets.AssetStore;

import javax.swing.*;
import java.awt.*;

public class ParaTestearCosas
{

    public static void main(String[] args) throws Exception
    {
        var frame = new JFrame("Testing");
        frame.setVisible(true);
        frame.setSize(550,550);
        frame.setVisible(true);
        var cont = frame.getContentPane();
        cont.setBackground(new Color(0,0,0));
        cont.setLayout(null);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        var label = new JLabel(AssetStore.getIcon("life"));
        label.setBounds(40,40,300,300);
        cont.add(label);
        label.setHorizontalAlignment(0);
        label.setVerticalAlignment(0);
        for(int i=0 ; i<60 ; i++)
        {
            label.setBounds(40,40,i,10);
            frame.repaint();
            Thread.sleep(100);
        }

    }

}
