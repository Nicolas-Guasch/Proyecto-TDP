package ADTs;

public class RowCol implements IRowCol
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

}
