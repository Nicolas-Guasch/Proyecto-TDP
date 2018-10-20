package UI;



import javax.swing.*;
import java.util.function.Consumer;
import java.util.function.Function;

public interface UIComponent
{
    Iterable<JComponent> getComponents();

    void foreach(Consumer<JComponent> operation);
}
