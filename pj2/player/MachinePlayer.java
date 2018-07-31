/* MachinePlayer.java */

package player;

/**
 *  An implementation of an automatic Network player.  Keeps track of moves
 *  made by both players.  Can select a move for itself.
 */
public class MachinePlayer extends Player {

    public int color; //0 is black, 1 is white
    public int searchDepth; //search depth
    public SimpleBoard currentBoard;
    public int whiteCount;
    public int blackCount;

  // Creates a machine player with the given color.  Color is either 0 (black)
  // or 1 (white).  (White has the first move.)
  public MachinePlayer(int color) {
      this.color = color;
      searchDepth = 3;
      currentBoard = new SimpleBoard();
      whiteCount = 0;
      blackCount = 0;

  }

  // Creates a machine player with the given color and search depth.  Color is
  // either 0 (black) or 1 (white).  (White has the first move.)
  public MachinePlayer(int color, int searchDepth) {
      this.color = color;
      this.searchDepth = searchDepth
      currentBoard = new SimpleBoard();
      whiteCount = 0;
      blackCount = 0;
  }

    //a function chooses best move
    private Evaluator choosing(int alpha, int beta, int depth, int color){
        Evaluator myMove = new Evaluator(SimpleBoard currentBoard, color);
        Evaluator reply;
        //当我方棋子少于10个时

        if(myMove.isFinished() == true || depth == 1){
            myMove.score = myMove.evaluates();
            return myMove;
        }
        if(color == this.color){
            myMove.score = alpha;
        }else{
            myMove.score = beta;
        }
        if(currentBoard.numOfChip(color) < 10){
            for(int i = 0; i < 8; i++){
                for(int j = 0; j < 8; j++){
                    Move temp = new Move(i, j);

                    if(currentBoard.isLegalMove(temp, color) == true){
                        currentBoard.update(temp, color);
                        reply = choosing(alpha, beta, depth - 1, Math.abs(color - 1));
                        currentBoard.removeElement(i, j);

                        if(color == this.color && reply.score > myMove.score){
                            myMove.move = new Move(i, j);
                            myMove.score = reply.score;
                            alpha = reply.score;
                        }else if(color != this.color && reply.score < myMove.score){
                            myMove.move = new Move(i, j);
                            myMove.score = reply.score;
                            beta = reply.score;
                        }

                        if(alpha >= beta){
                            return myMove;
                        }
                    }
                }
            }
            return myMove;
        }
        else if(currentBoard.numOfChip(color) >= 10){
            for(i = 0; i < 8; i++){
                for(j = 0; j < 8; j++){
                    if(elementAt(i, j) == color){
                        for(int k = 0; k < 8; k++){
                            for(int l = 0; l < 8; l++){
                                //go on alpha-beta pruning
                                Move temp = new Move(k, l, i, j);
                                if(currentBoard.isLegalMove(temp, color) == true){
                                    currentBoard.update(temp, color);
                                    reply = choosing(alpha, beta, depth - 1, Math.abs(color - 1));
                                    currentBoard.removeElement(k, l);
                                    currentBoard.update(new Move(i, j), color);
                                    if(color == this.color && reply.score > myMove.score){
                                        myMove.move = new Move(i, j);
                                        myMove.score = reply.score;
                                        alpha = reply.score;
                                    }else if(color != this.color && reply.score < myMove.score){
                                        myMove.move = new Move(i, j);
                                        myMove.score = reply.score;
                                        beta = reply.score;
                                    }
                                    if(alpha >= beta){
                                        return myMove;
                                    }
                                }
                            }
                        }
                    }
                }
            }
            return myMove;
        }

    }



  // Returns a new move by "this" player.  Internally records the move (updates
  // the internal game board) as a move by "this" player.
  public Move chooseMove(int alpha, int beta, int depth) {
      Evaluator myBest = choosing(alpha, beta, depth, color);
      currentBoard.update(myBest.Move, color);
      return myBest.move;
  }

  // If the Move m is legal, records the move as a move by the opponent
  // (updates the internal game board) and returns true.  If the move is
  // illegal, returns false without modifying the internal state of "this"
  // player.  This method allows your opponents to inform you of their moves.
  public boolean opponentMove(Move m) {
    boolean flag = currentBoard.update(m, Math.abs(color - 1));
    return flag;
  }

  // If the Move m is legal, records the move as a move by "this" player
  // (updates the internal game board) and returns true.  If the move is
  // illegal, returns false without modifying the internal state of "this"
  // player.  This method is used to help set up "Network problems" for your
  // player to solve.
  public boolean forceMove(Move m) {
    boolean flag = currentBoard.update(m, color);
    return flag;
  }

}
