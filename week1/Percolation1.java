/**
 * Created by zhangsheng on 2016/11/7.
 */

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation1 {
    private int size;
    private boolean[][] status;
    private int top = 0;
    private int bottom;
    private WeightedQuickUnionUF quf;
    private WeightedQuickUnionUF backwash;

    private void validatePosition(int row, int col){
        if (row <= 0 || col <= 0 || row > size || col > size)
            throw new IndexOutOfBoundsException();
    }

    private int xy2id(int row, int col){
        validatePosition(row, col);
        return (row - 1)*size + col;
    }

    public Percolation1(int n){
        if (n <= 0){
            throw new IllegalArgumentException();
        }
        size = n;
        status = new boolean[size][size];
        bottom = size * size + 1;
        quf = new WeightedQuickUnionUF(size * size + 2);
        backwash = new WeightedQuickUnionUF(size * size + 1);
    }

    public void open(int row, int col){
        validatePosition(row, col);

        status[row-1][col-1] = true;

        if (row == 1){
            quf.union(xy2id(row, col), top);
            backwash.union(xy2id(row, col), top);
        }
        if (row > 1 && isOpen(row-1, col)){
            quf.union(xy2id(row, col), xy2id(row-1, col));
            backwash.union(xy2id(row, col), xy2id(row-1, col));
        }
        if (row < size && isOpen(row+1, col)){
            quf.union(xy2id(row, col), xy2id(row+1, col));
            backwash.union(xy2id(row, col), xy2id(row+1, col));
        }
        if(row == size){
            quf.union(xy2id(row, col), bottom);
        }
        if (col > 1 && isOpen(row, col-1)){
            quf.union(xy2id(row, col), xy2id(row, col-1));
            backwash.union(xy2id(row, col), xy2id(row, col-1));
        }
        if (col < size && isOpen(row, col+1)){
            quf.union(xy2id(row, col), xy2id(row, col+1));
            backwash.union(xy2id(row, col), xy2id(row, col+1));
        }

    }
    public boolean isOpen(int row, int col){
        validatePosition(row, col);
        return status[row-1][col-1];
    }
    public boolean isFull(int row, int col){
        validatePosition(row, col);
        return backwash.connected(xy2id(row, col), top);
    }
    public boolean percolates(){
        return quf.connected(top, bottom);
    }

}
