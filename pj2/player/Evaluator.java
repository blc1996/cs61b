package player;

public class Evaluator {

    public SimpleBoard board;
    public int score;
    public Move move;
    public int color;
    //vertexLookUp存储棋盘上所有的某一颜色的chip
    public Vertex[10] vertexLookUp;
    //存储connections
    public DList[10] adjList；

    public Evaluator(SimpleBoard currentBoard, int color){
        board = currentBoard;
        this.color = color;
        score = 0;
    }

    /**
     *  判断游戏是否结束
     *  @return true when the game is finished, false when the game can be continued
     */
    public boolean isFinished(){
        //三种情况
        //第一种：已经没有legalmove可以走了
        //第二种：白方获胜
        //第三种：黑方获胜

        //第一种
        return true;
    }

    /**
     *  判断是否有一方胜出
     *  @return 0 when black wins, 1 when white wins, 2 when nobody wins, 使用adjacent list来储存每一个chip所有的connection，并且可以使用DFS来迭代地遍历所有的connection,用hash table来储存每一个chip的编号
     */
    public int winningSide(){
        for(int i = 0; i < 10; i++){
            vertexLookUp[i] = null;
        }
        //start judging from black side
        int tempColor = 0;
           //introduce a new class called vertex
        int countBlack = 0; //count how many black chips exist
        for(int i = 0; i < 8; i ++){
            for(int j = 0; j < 8; j++){
                if(board.elementAt(i, j) == color){
                    vertexLookUp[count] = new Vertex(i,j,countBlack);
                    countBlack++;
                    //需要构建adjlist
                }
            }
        }

        for(int i = 0; i < countBlack){
            if(vertexLookUp[i].y == 7){
                boolean winFlag = this.vertexSearch(i, 1, 0); //迭代查找
                if(winFlag == true)
                    return 0;
                //每一轮visit之后要做初始化
                for(int j = 0; j < countBlack; j++){
                    vertexLookUp[j].isVisited = false;
                }
            }
        }

    }

     /**
     *  查找是否有胜利的组合，DFS
     *  @param vertexIndex:当前查找的vertex的index;
     *  @param chipCount:当前搜索到第几层了；
     *  @param direction：0为刚开始第一层，+-1为上下，+-2为右上左下，+-3为左右，+-4为右下左上
     *  @return the score of the current evaluator, use hash table to store the score of each set of board to get better performence.
     */
    public boolean vertexSearch(Vertex vertexIndex, int chipCount, int direction){
        boolean winFlag = false;
        //isVisited初始化
        vertexLookUp[vertexIndex].isVisited = true;
        //胜利判断
        if(chipCount >= 6 && currentVertex.y = 0){
            return true;
        }
        //得到与当前vertex有connection的vertex的index
        DListNode currentNode = adjList[currentVertex.index].front();
        for(int i = 0; i < adjList[index].length(); i++){
            //如果访问到的vertex之前没有被访问过，则进行操作
            if(vertexLookUp[currentNode.item()].isVisited == false){
                int thisDirection = determineDirection(vertexLookUp[vertexIndex].x, vertexLookUp[vertexIndex].y, vertexLookUp[currentNode.item()].x, vertexLookUp[currentNode.item()].y);
                //不和之前的connection在同一条轴上
                if(Math.abs(direction) != Math.abs(thisDirection)){
                    winFlag = vertexSearch(currentNode.item(), chipCount++, thisDirection);
                    if(winFlag == true){
                        return true;
                    }
                    //如果回退，需要恢复那个vertex的isvisited标志位
                    vertexLookUp[currentNode.item()].isVisited = false;
                }
            }
            currentNode = currentNode.next();
        }
        return winFlag;
    }

    public void findConnection(Vertex chip, int index){

    }

    /**
     *  判断两个有connection的vertex之间连接的方向
     *  @return 0为刚开始第一层，+-1为上下，+-2为右上左下，+-3为左右，+-4为右下左上
     */
    public int determineDirection(int x1, int y1, int x2, int y2){
        int deltaX = x2 - x1;
        int deltaY = y2 - y1;
        if(deltaX > 0){
            if(deltaY > 0)
                return 4;
            else if(deltaY == 0)
                return 3;
            else
                return 2;
        }else if (deltaX == 0){
            return 1;
        }else{
            if(deltaY > 0)
                return 2;
            else if(deltaY == 0)
                return 3;
            else
                return 4;
        }
    }

    /**
     *  棋盘得分估计函数
     *  @return the score of the current evaluator, use hash table to store the score of each set of board to get better performence.
     */
    public int evaluates(){

    }

}
