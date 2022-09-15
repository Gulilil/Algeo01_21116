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

    // Melakukan perkalian skalar pada baris
    public void scalarMultiplyRow(int scalar){
        int row = scanObj.nextInt();
        for(int cols = 0; cols < colLength; cols++){
            matrix[row][cols] *= scalar;
        }
    }

    // Melakukan perkalian skalar pada kolom
    public void scalarMultiplyCol(int scalar){
        int col = scanObj.nextInt();
        for(int rows = 0; rows < rowLength; rows++){
            matrix[rows][col] *= scalar;
        }
    }

    // Mengecek apakah suatu matriks itu persegi
    public boolean isSquare(){
        return rowLength == colLength;
    }
    
    // Melakukan transpose matriks
    public void transposeMatriks(){
        if (isSquare()){
            for(int rows = 0; rows < rowLength; rows++){
                for(int cols = rows; cols < colLength; cols++){
                    double temp = matrix[rows][cols];
                    matrix[rows][cols] = matrix[cols][rows];
                    matrix[cols][rows] = temp;
                }
            }
        }
    }

    // Mencari determinan dari sebuah matriks
    public double calculateDeterminant(){
        double det = 0;
        if (isSquare()){
            if (rowLength == 2){
                det = matrix[0][0] * matrix[1][1] - matrix[0][1] * matrix[1][0];
            } else {
                for(int cols = 0; cols < colLength; cols++){
                    // det += Math.pow(-1, cols) * matrix[0][cols] * subMatrix(0, cols).determinant();
                }
            }
        }
        return det;
    }

    // Mengecek apakah suatu baris itu nol
    public boolean isZeroRow(int row){
        boolean isZero = true;
        for(int cols = 0; cols < colLength; cols++){
            if (matrix[row][cols] != 0){
                isZero = false;
                break;
            }
        }
        return isZero;
    }

    // Mengecek apa suatu kolom itu nol
    public boolean isZeroCol(int col){
        boolean isZero = true;
        for(int rows = 0; rows < rowLength; rows++){
            if (matrix[rows][col] != 0){
                isZero = false;
                break;
            }
        }
        return isZero;
    }

    // Mengecek apakah suatu matriks itu identitas
    public void makeIdentity(Matrix m){
        for(int rows = 0; rows < rowLength; rows++){
            for(int cols = 0; cols < colLength; cols++){
                (rows == cols) ? setElmt(m, rows, cols, 1) : setElmt(m, rows, cols, 0);
            }
        }
    }
}
