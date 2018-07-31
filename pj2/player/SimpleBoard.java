/* SimpleBoard.java */

/**
 *  Simple class that implements an 8x8 game board with three possible values
 *  for each cell:  0, 1 or 2.
 *
 *  DO NOT CHANGE ANY PROTOTYPES IN THIS FILE.
 **/

public class SimpleBoard {
    private final static int DIMENSION = 8;
    private int[][] grid;

    /**
     *  Invariants:
     *  (1) grid.length == DIMENSION.
     *  (2) for all 0 <= i < DIMENSION, grid[i].length == DIMENSION.
     *  (3) for all 0 <= i, j < DIMENSION, grid[i][j] >= 0 and grid[i][j] <= 2.
     **/

    /**
     *  Construct a new board in which all cells are zero.
     */
    //0是黑子，1是白子，2是空
    public SimpleBoard() {
        grid = new int[DIMENSION][DIMENSION];
        int i, j;
        for(i = 0; i < DIMENSION; i++){
            for(j = 0; j < DIMENSION; j++){
                grid[i][j] = 2;
            }
        }
    }

    /**
     *  Set the cell (x, y) in the board to the given value mod 3.
     *  @param value to which the element should be set (normally 0, 1, or 2).
     *  @param x is the x-index.
     *  @param y is the y-index.
     *  @exception ArrayIndexOutOfBoundsException is thrown if an invalid index
     *  is given.
     **/

    public void setElementAt(int x, int y, int value) {
        grid[x][y] = value % 3;
        if (grid[x][y] < 0) {
            grid[x][y] = grid[x][y] + 3;
        }
    }


    public void removeElement(int x, int y){
        grid[x][y] = 2;
    }

    /**
     *  Get the valued stored in cell (x, y).
     *  @param x is the x-index.
     *  @param y is the y-index.
     *  @return the stored value (between 0 and 2).
     *  @exception ArrayIndexOutOfBoundsException is thrown if an invalid index
     *  is given.
     */

    public int elementAt(int x, int y) {
        return grid[x][y];
    }

    /**
     *  Returns true if "this" SimpleBoard and "board" have identical values in
     *    every cell.
     *  @param board is the second SimpleBoard.
     *  @return true if the boards are equal, false otherwise.
     */

    public boolean equals(Object board) {
        // Replace the following line with your solution.  Be sure to return false
        //   (rather than throwing a ClassCastException) if "board" is not
        //   a SimpleBoard.
        for(int i = 0; i < DIMENSION; i++){
            for (int j = 0; j < DIMENSION; j++){
                if(grid[i][j] != ((SimpleBoard)board).elementAt(i,j))
                    return false;
            }
        }
        return true;
    }

    /**
     *  Returns a hash code for this SimpleBoard.
     *  @return a number between Integer.MIN_VALUE and Integer.MAX_VALUE.
     */

    public int hashCode() {
        // Replace the following line with your solution.
        int temp = 0;
        for(int i = 0; i < DIMENSION; i++){
            for (int j = 0; j < DIMENSION; j++){
                temp = (temp * 3 + grid[i][j]) % 16908799;
            }
        }
        return String.valueOf(temp).hashCode();
    }

    /**
     *  得到当前棋盘上特定颜色棋子的数量
     *  @return
     */
    public int numOfChip(int color){
        int count = 0;
        for(int i = 0; i < DIMENSION; i++){
            for(int j = 0; j < DIMENSION; j++){
                if(elementA(i, j) == color)
                    count++;
            }
        }
        return count;
    }


    /**
     *  更新棋盘
     *  @return
     */
    //更新棋盘，如果合法move返回ture，非法move返回false,color=1白色
    public boolean update(Move thisMove, int color){
        if(isLegalMove(thisMove)){
            if(thisMove.moveKind == ADD){
                grid[thisMove.x1][thisMove.y1] = color;
                return true;
            }else if(thisMove.moveKind == STEP){
                grid[thisMove.x1][thisMove.y1] = color;
                grid[thisMove.x2][thisMove.y2] = 2;
                return true;
            }
        }else
            return false;
    }


    /**
     *  Returns a boolean indicate whether it is a legal move
     *  @param
     */
    //判断是否是一个合法的move
    public boolean isLegalMove(Move nextMove, int color){
        if(grid[nextMove.x2][nextMove.y2] != color && nextMove.moveKind == STEP)
                return false;
        boolean flag = LegalMove(nextMove, color);
        if(nextMove.moveKind == STEP || flag == false){
            setElementAt(nextMove.x2, nextMove.y2, color); //step恢复
        }
        return flag;
    }


    /**
     *  Returns a boolean indicate whether it is a legal move
     *  @param
     */
    //判断是否是一个合法的move
    private boolean LegalMove(Move nextMove, int color){
        //如果是step move，先清除old step
        if(nextMove.moveKind == STEP){
            removeElement(nextMove.x2, nextMove.y2);
            if(nextMove.x1 == nextMove.x2 && nextMove.y1 == nextMove.y2)
                return false;
        }

        //判断是否已经被占据
        if(grid[nextMove.x1][nextMove.y1] != 2)
            return false;

        //判断是否在四个角落
        if((nextMove.x1 == 0 && nextMove.y1 == 0) || (nextMove.x1 == 0 && nextMove.y1 == 7)
           || (nextMove.x1 == 7 && nextMove.y1 == 0) || (nextMove.x1 == 7 && nextMove.y1 == 7)){
            return false;
        }

        int count = 0;
        //如果是白棋
        if(color == 1){
            //判断是否在上下两行
            if(nextMove.y1 == 0 || nextMove.y1 == 7){
                return false;
            }
            //判断九宫格内有没有一样的棋子
            if(nextMove.x1 == 0){
                for(i = 0; i < 2; i++){
                    for(j = 0; j < 3; j++){
                        if(grid[i][nextMove.y1 - 1 + j] == 1)
                            count++;
                    }
                }
                if(count >= 2)
                    return false;
            }else if(nextMove.x1 == 7){
                for(i = 0; i < 2; i++){
                    for(j = 0; j < 3; j++){
                        if(grid[i+6][nextMove.y1 - 1 + j] == 1)
                            count++;
                    }
                }
                if(count >= 2)
                    return false;
                }
            }else{
                for(i = 0; i < 3; i++){
                    for(j = 0; j < 3; j++){
                        if(grid[nextMove.x1 - 1 + i][nextMove.y1 - 1 + j] == 1)
                            count++;
                    }
                }
                if(count >= 2)
                    return false;
            }
            return true;
        }
        //如果是黑棋
        if(color == 0){
            //判断是否在左右两行
            if(nextMove.x1 == 0 || nextMove.x1 == 7){
                return false;
            }
            //判断九宫格内有没有一样的棋子
            if(nextMove.y1 == 0){
                for(i = 0; i < 3; i++){
                    for(j = 0; j < 2; j++){
                        if(grid[nextMove.x1 -1 +i][j] == 0)
                            count++;
                    }
                }
                if(count >= 2)
                    return false;
            }else if(nextMove.y1 == 7){
                for(i = 0; i < 3; i++){
                    for(j = 0; j < 2; j++){
                        if(grid[nextMove.x1 -1 +i][j+6] == 0)
                            count++;
                    }
                }
                if(count >= 2)
                    return false;
                }
            }else{
                for(i = 0; i < 3; i++){
                    for(j = 0; j < 3; j++){
                        if(grid[nextMove.x1 -1 +i][nextMove.y1 - 1 + j] == 0)
                            count++;
                    }
                }
                if(count >= 2)
                    return false;
            }
            return true;
        }
    return false;
    }

}

