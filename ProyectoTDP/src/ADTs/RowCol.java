package ADTs;

public class RowCol
{
    private int row,col;

    public RowCol(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public int getCol() {
        return col;
    }

    public int getRow() {
        return row;
    }

    /**
     *
     * @return new Vector(col,row);
     */
    public Vector2 toVector(){
        return new Vector2(col,row);
    }
}
