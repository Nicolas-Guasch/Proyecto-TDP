package PlaceHolder;

import SoundSystem.Sound;
import SoundSystem.TinySound;

import javax.swing.*;
import java.awt.*;
import java.util.function.Function;

public class Button
{
    private JButton button;
    private Color press;
    private Color press_light;
    private Color unpress;
    private Color unpress_light;

    private boolean pressed;
    private JSlider slider;

    public Button(Color color, Sound sound)
    {
        unpress = operateColor(this::grisar,color);
        press = color;
        button = new JButton();
        press_light = operateColor(this::lightThat,color);
        unpress_light = operateColor(this::grisar,press_light);
        button.addActionListener(x-> onPressed());
        pressed = false;
        onLightChanges(false);

        this.sound = sound;

    }

    private void onPressed()
    {
        pressed = !pressed;
        onLightChanges(false);
    }

    Sound sound ;


    public void onLightChanges(boolean lighted)
    {
        if(pressed)
        {
            if(lighted) {
                setCurrentColor(press_light);
                sound .play(getVol());
            }
            else
                setCurrentColor(press);
        }
        else
        {
            if(lighted)
                setCurrentColor(unpress_light);
            else
                setCurrentColor(unpress);
        }
    }

    private float getVol() {
        return (slider.getValue()-slider.getMinimum()*1f)/(slider.getMaximum() - slider.getMinimum());
    }

    private void setCurrentColor(Color color)
    {
        button.setBackground(color);
    }


    public Component getComponent()
    {
        return button;
    }















    private int lightThat(int v)
    {
        if (v < 220)
        {
            if (v < 90)
            {
                return v*2;
            }
            else
            {
                return 220;
            }
        }
        else
        {
            return v;
        }
    }


    private int grisar(int r)
    {
        r =(int)(r>127 ? r*0.3f: r*1.9f);
        return r;
    }

    private Color operateColor(Function<Integer,Integer> mono_shader, Color color)
    {
        int r = color.getRed(), g = color.getGreen(), b = color.getBlue();
        return new Color(mono_shader.apply(r),mono_shader.apply(g),mono_shader.apply(b));
    }

    public void setSlider(JSlider sl) {
        this.slider = sl;
    }
}
