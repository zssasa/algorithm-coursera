/**
 * Created by zhangsheng on 2016/11/7.
 */

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private int size;
    private boolean[][] status;
    private boolean[] connectTop;
    private boolean[] connectBottom;
    private WeightedQuickUnionUF quf;
    private boolean percolateFlag;

    private void validatePosition(int row, int col){
        if (row <= 0 || col <= 0 || row > size || col > size)
            throw new IndexOutOfBoundsException();
    }

    private int xy2id(int row, int col){
        validatePosition(row, col);
        return (row - 1)*size + col-1;
    }

    public Percolation(int n){
        if (n <= 0){
            throw new IllegalArgumentException();
        }
        size = n;
        status = new boolean[size][size];
        connectTop = new boolean[size * size];
        connectBottom = new boolean[size * size];
        quf = new WeightedQuickUnionUF(size * size);
        percolateFlag = false;
    }

    public void open(int row, int col){
        validatePosition(row, col);

        status[row-1][col-1] = true;
        int index = xy2id(row, col);
        boolean top = false;
        boolean bottom = false;

        if (row == 1){
            top = true;
        }
        if (row > 1 && isOpen(row-1, col)){
            if (connectTop[quf.find(index)] || connectTop[quf.find(index-size)]){
                top = true;
            }
            if (connectBottom[quf.find(index)] || connectBottom[quf.find(index-size)]){
                bottom = true;
            }
            quf.union(index, index-size);
        }

        if (row < size && isOpen(row+1, col)){
            if (connectTop[quf.find(index)] || connectTop[quf.find(index+size)]){
                top = true;
            }
            if (connectBottom[quf.find(index)] || connectBottom[quf.find(index+size)]){
                bottom = true;
            }
            quf.union(index, index+size);
        }
        if(row == size){
            bottom = true;
        }

        if (col > 1 && isOpen(row, col-1)){
            if (connectTop[quf.find(index)] || connectTop[quf.find(index-1)]){
                top = true;
            }
            if (connectBottom[quf.find(index)] || connectBottom[quf.find(index-1)]){
                bottom = true;
            }
            quf.union(index, index-1);
        }
        if (col < size && isOpen(row, col+1)){
            if (connectTop[quf.find(index)] || connectTop[quf.find(index+1)]){
                top = true;
            }
            if (connectBottom[quf.find(index)] || connectBottom[quf.find(index+1)]){
                bottom = true;
            }
            quf.union(index, index+1);
        }

        connectTop[quf.find(index)] = top;
        connectBottom[quf.find(index)] = bottom;

        if (connectTop[quf.find(index)] && connectBottom[quf.find(index)]){
            percolateFlag = true;
        }

    }
    public boolean isOpen(int row, int col){
        validatePosition(row, col);
        return status[row-1][col-1];
    }
    public boolean isFull(int row, int col){
        validatePosition(row, col);
        return connectTop[quf.find(xy2id(row, col))];
    }
    public boolean percolates(){
        return percolateFlag;
    }

    public static void main(String[] args){
        Percolation pr = new Percolation(6);
        pr.open(1,6);
        System.out.println(pr.isFull(1,6));
        pr.open(2,6);
        System.out.println(pr.isFull(1,6));
    }
}
