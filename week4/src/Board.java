/**
 * Created by zhangsheng on 2016/11/30.
 */
import java.util.Stack;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class Board {
    private int[][] blocks;
    private int n;

    // construct a board from an n-by-n array of blocks
    public Board(int[][] blocks) {
        n = blocks.length;
        this.blocks = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                this.blocks[i][j] = blocks[i][j];
    }

    // board dimension n
    public int dimension() {
        return n;
    }

    // number of blocks out of place
    public int hamming() {
        int dist = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (blocks[i][j] == 0)
                    continue;
                if (blocks[i][j] != (n * i + j + 1)) {
                    dist++;
                }
            }
        }
        return dist;
    }

    // sum of Manhattan distances between blocks and goal
    public int manhattan() {
        int dist = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (blocks[i][j] == 0)
                    continue;
                int row = (blocks[i][j] - 1) / n;
                int col = (blocks[i][j] - 1) % n;
                dist += Math.abs(row - i) + Math.abs(col - j);
            }
        }
        return dist;
    }

    // is this board the goal board?
    public boolean isGoal() {
        return (hamming() == 0);
    }

    // a board that is obtained by exchanging any pair of blocks
    public Board twin() {
        Board board = new Board(blocks);

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n - 1; j++) {
                if (blocks[i][j] != 0 && blocks[i][j + 1] != 0) {
                    board.swap(i, j, i, j + 1);
                    return board;
                }
            }
        }

        return board;
    }

    private boolean swap(int i, int j, int it, int jt) {
        if (it < 0 || it >= n || jt < 0 || jt >= n) {
            return false;
        }
        int temp = blocks[i][j];
        blocks[i][j] = blocks[it][jt];
        blocks[it][jt] = temp;
        return true;
    }

    // does this board equal y?
    public boolean equals(Object y) {
        if (y == this) return true;
        if (y == null) return false;
        if (y.getClass() != this.getClass())
            return false;

        Board that = (Board) y;
        if (this.n != that.n) return false;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (this.blocks[i][j] != that.blocks[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    // all neighboring boards
    public Iterable<Board> neighbors() {
        int row = 0;
        int col = 0;
        boolean flag = false;

        for (int i = 0; i < n && !flag; i++) {
            for (int j = 0; j < n; j++) {
                if (blocks[i][j] == 0) {
                    row = i;
                    col = j;
                    flag = true;
                }
            }
        }

        Stack<Board> boards = new Stack<>();
        Board board = new Board(blocks);
        boolean isNeighbor = board.swap(row, col, row - 1, col);
        if (isNeighbor) {
            boards.push(board);
        }
        board = new Board(blocks);
        isNeighbor = board.swap(row, col, row, col - 1);
        if (isNeighbor) {
            boards.push(board);
        }
        board = new Board(blocks);
        isNeighbor = board.swap(row, col, row + 1, col);
        if (isNeighbor) {
            boards.push(board);
        }
        board = new Board(blocks);
        isNeighbor = board.swap(row, col, row, col + 1);
        if (isNeighbor) {
            boards.push(board);
        }

        return boards;

    }

    // string representation of this board (in the output format specified below)
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(n + "\n");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                s.append(String.format("%2d ", blocks[i][j]));
            }
            s.append("\n");
        }
        return s.toString();
    }

    public static void main(String[] args) {
        In in = new In(args[0]);
        int n = in.readInt();
        int[][] blocks = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                blocks[i][j] = in.readInt();
        Board initial = new Board(blocks);
        StdOut.println(initial.manhattan());

    }
}