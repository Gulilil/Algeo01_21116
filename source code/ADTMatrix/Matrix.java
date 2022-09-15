package ADTMatrix;

public class Matrix{
    int rows = 0;
    int cols = 0;
    double[][] matrix;

    public Matrix(int rows, int cols){
        matrix = new double[rows][cols];
        this.rows = rows;
        this.cols = cols;
    }

}
