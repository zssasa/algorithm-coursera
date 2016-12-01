/**
 * Created by zhangsheng on 2016/11/30.
 */
import edu.princeton.cs.algs4.MinPQ;
import java.util.Collections;
import java.util.ArrayList;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class Solver {
    private boolean isSolvable;
    private int move;
    private SearchNode last;

    private class SearchNode implements Comparable<SearchNode> {
        private Board board;
        private int moves;
        private SearchNode preNode;
        private int priority;

        public SearchNode(Board board, int moves, SearchNode preNode) {
            this.board = board;
            this.moves = moves;
            this.preNode = preNode;
            this.priority = board.manhattan() + moves;
        }

        public int compareTo(SearchNode that) {
            if (this.priority < that.priority) return -1;
            if (this.priority > that.priority) return 1;
            return 0;
        }
    }

    public Solver(Board initial) {
        MinPQ<SearchNode> p = new MinPQ<>();
        MinPQ<SearchNode> q = new MinPQ<>();

        SearchNode start = new SearchNode(initial, 0, null);
        SearchNode startTwin = new SearchNode(initial.twin(), 0, null);
        p.insert(start);
        q.insert(startTwin);

        while (true) {
            SearchNode node = p.delMin();
            SearchNode nodeTwin = q.delMin();

            if (node.board.isGoal()) {
                last = node;
                isSolvable = true;
                move = node.moves;
                break;
            }
            if (nodeTwin.board.isGoal()) {
                isSolvable = false;
                move = -1;
                break;
            }

            enqueueNode(node, p);
            enqueueNode(nodeTwin, q);
        }
    }

    private void enqueueNode(SearchNode node, MinPQ<SearchNode> pq) {
        for (Board nextBoard : node.board.neighbors()) {
            if ((node.preNode == null) || (!nextBoard.equals(node.preNode.board))) {
                pq.insert(new SearchNode(nextBoard, node.moves + 1, node));
            }
        }
    }

    // is the initial board solvable?
    public boolean isSolvable() {
        return isSolvable;
    }

    // min number of moves to solve initial board; -1 if unsolvable
    public int moves() {
        return move;
    }

    // sequence of boards in a shortest solution; null if unsolvable
    public Iterable<Board> solution() {
        if (!isSolvable())
            return null;

        ArrayList<Board> boards = new ArrayList<>();
        SearchNode s = last;
        while (s != null) {
            boards.add(s.board);
            s = s.preNode;
        }
        Collections.reverse(boards);
        return boards;
    }

    // solve a slider puzzle (given below)
    public static void main(String[] args) {
        // create initial board from file
        In in = new In(args[0]);
        int n = in.readInt();
        int[][] blocks = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                blocks[i][j] = in.readInt();
        Board initial = new Board(blocks);

        // solve the puzzle
        Solver solver = new Solver(initial);

        // print solution to standard output
//        StdOut.println(solver.last.board);
//        StdOut.println(solver.last.preNode.board);
//        StdOut.println(solver.solution());
        if (!solver.isSolvable())
            StdOut.println("No solution possible");
        else {
            StdOut.println("Minimum number of moves = " + solver.moves());
            for (Board board : solver.solution())
                StdOut.println(board);
        }

    }
}