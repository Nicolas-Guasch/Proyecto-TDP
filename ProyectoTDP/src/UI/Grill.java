package UI;

import ADTs.RowCol;
import ADTs.Vector2;
import RenderingSystem.Renderizable;

import javax.swing.*;
import java.util.*;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class Grill implements UIComponent
{

    private final Vector2 position;
    private final Vector2 cellSize;
    private final int maxCols;
    private ArrayList<Renderizable> components;
    private final Vector2 phaseShift;
    private Queue<Renderizable> auxQueue,addQueue;

    public Grill(Vector2 position, Vector2 cellSize, int maxCols)
    {
        this.maxCols = maxCols;
        this.cellSize = cellSize;
        this.position = position;
        phaseShift = position.sum(this.cellSize.prod(0.5f)); // el desface inicial mas ponerlo al centro de la celda
        components = new ArrayList<>();
        auxQueue = new LinkedBlockingQueue<>();
        addQueue = new LinkedBlockingQueue<>();
    }

    private RowCol getRowCol(int index){
        var row = index / maxCols;
        var col = index % maxCols;
        return new RowCol(row,col);
    }

    private Vector2 getPoint(int index){
        var rowCol = getRowCol(index);

        var x = phaseShift.x() + rowCol.getCol()*cellSize.x();
        var y = phaseShift.y() - rowCol.getRow()*cellSize.y();
        return new Vector2(x,y);
    }



    public void add(Renderizable rend){
        rend.show();
        addQueue.add(rend);
        repaint();
    }

    public void repaint(){
        int i=0;

        for(Renderizable renderizable : new LinkedBlockingQueue<>(addQueue)){
            if(renderizable.transform()!=null && renderizable.gameObject()!=null) {
                components.add(renderizable);
                addQueue.remove(renderizable);
            }
        }

        for(Renderizable rend : components){
            if(!rend.isVisible() || !rend.isActive()){
                auxQueue.add(rend);
            }
        }
        for (Renderizable renderizable : auxQueue) {
            components.remove(renderizable);
        }
        for (Renderizable renderizable : auxQueue) {
            if(renderizable.transform()!=null && renderizable.gameObject()!=null) {
                components.add(renderizable);
            }
        }
        for (Renderizable rend : components) {
            rend.transform().setPosition(getPoint(i));
            //TODO: animar movimiento usando Hyperspace
            i++;
        }
        auxQueue.clear();
    }


    @Override
    public Iterable<JComponent> getComponents() {
        return components.stream().map(Renderizable::Sprite).collect(Collectors.toList());
    }

    @Override
    public void foreach(Consumer<JComponent> operation) {
        getComponents().forEach(operation);
    }

}
