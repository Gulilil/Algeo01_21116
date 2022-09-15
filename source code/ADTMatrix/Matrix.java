package ADTMatrix;
import java.io.*;
import java.util.*;

public class Matrix{
    int rowLength = 0;
    int colLength = 0;
    double[][] matrix;

    Scanner scanObj = new Scanner(System.in);

    public Matrix(int rows, int cols){
        matrix = new double[rows][cols];
        this.rowLength = rows;
        this.colLength = cols;
    }

    public Matrix(double[][] matrix){
        rowLength = matrix.length;
        colLength = matrix[0].length;
        this.matrix = new double[rowLength][colLength];
        for(int rows = 0; rows < rowLength; rows++){
            for(int cols = 0; cols < colLength; cols++){
                this.matrix[rows][cols] = matrix[rows][cols];
            }
        }
    }

}
