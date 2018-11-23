package UI;

import ADTs.IVector2;
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

    private final IVector2 position;
    private final IVector2 cellSize;
    private final int maxCols;
    private ArrayList<Renderizable> components;
    private final IVector2 phaseShift;
    private Queue<Renderizable> auxQueue,addQueue;

    public Grill(IVector2 position, IVector2 cellSize, int maxCols)
    {
        this.maxCols = maxCols;
        this.cellSize = cellSize;
        this.position = position;
        phaseShift = position.sum(this.cellSize.prod(0.5f)); // el desface inicial mas ponerlo al centro de la celda
        components = new ArrayList<Renderizable>();
        auxQueue = new LinkedBlockingQueue<Renderizable>();
        addQueue = new LinkedBlockingQueue<Renderizable>();
    }

    private RowCol getRowCol(int index){
        int row = index / maxCols;
        int col = index % maxCols;
        return new RowCol(row,col);
    }

    private IVector2 getPoint(int index){
        RowCol rowCol = getRowCol(index);

        float x = phaseShift.x() + rowCol.getCol()*cellSize.x();
        float y = phaseShift.y() - rowCol.getRow()*cellSize.y();
        return new Vector2(x,y);
    }



    public void add(Renderizable rend){
        rend.show();
        addQueue.add(rend);
        repaint();
    }

    public void repaint(){
        int i=0;

        for(Renderizable renderizable : new LinkedBlockingQueue<Renderizable>(addQueue)){
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
