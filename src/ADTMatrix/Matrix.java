package ADTMatrix;
import java.util.*;

public class Matrix{
    final double MARK = Double.NaN;
    int rowLength = 0;
    int colLength = 0;
    int rowIdx = 0;
    int colIdx = 0;
    double[][] matrix;
    Scanner scanObj = new Scanner(System.in);

    // ========================== KONSTRUKTOR ==========================
    //Konstruktor matriks kosong (isinya mark), dengan ukuran rowLength x colLength
    public Matrix(int rows, int cols){
        matrix = new double[rows][cols];
        this.rowLength = rows;
        this.colLength = cols;
        for(int i = 0; i < rows; i++){
            for(int j = 0; j <cols ; j++){
                setElmt(i, j, MARK);
            }
        }
    }

    //Konstruktor matriks yang isinya diisi dari keyboard ato file
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

    // ========================== SELEKTOR ==========================

    // FUNCTION
    // Memanggil panjang baris
    public int getRowLength(){
        return this.rowLength;
    }

    // FUNCTION
    // Memanggil panjang kolom
    public int getColLength(){
        return this.colLength;
    }

    // FUNCTION
    public int getRowIdx(){
        return this.rowLength - 1;
    }

    // FUNCTION
    public int getColIdx(){
        return this.colLength - 1;
    }

    // PROCEDURE
    // Melakukan set panjang baris
    public void setRowLength(int length){
        this.rowLength = length;
        this.matrix = new double [this.rowLength][this.colLength];
    }

    // PROCEDURE
    // Melakukan set kolom baris
    public void setColLength(int length){
        this.colLength = length;
        this.matrix = new double[this.rowLength][this.colLength];
    }

    // PROCEDURE
    // Melakukan set untuk elemen pada suatu baris
    public void setRowValue(int row){
        int col = getColLength();
        double val;
        for (int i = 0; i < col; i++){
            System.out.print("Element Matriks Baris ke-"+row+" Kolom ke-"+i+" : ");
            val = scanObj.nextDouble();
            setElmt(row, i, val);
        }
    }

    // PROCEDURE
    // Melakukan set untuk elemen pada suatu kolom
    public void setColValue(int col){
        int row = getRowLength();
        double val;
        for (int i = 0; i < row; i++){
            System.out.print("Element Matriks Baris ke-"+i+" Kolom ke-"+col+" : ");
            val = scanObj.nextDouble();
            setElmt(i, col, val);
        }

    }

    // FUNCTION
    // Memanggil element pada suatu baris dan kolom pada matriks
    public double getElmt(int row, int col){
        return this.matrix[row][col];
    }

    // PROCEDURE
    // Melakukan assignment terhadap suatu elemen pada matriks pada baris dan kolom tertentu
    public void setElmt(int row, int col, double val){
        matrix[row][col] = val;
    }

    // ========================== PREDIKAT ==========================

    // FUNCTION
    // Mengecek apakah suatu matriks itu persegi
    public boolean isSquare(){
        return rowLength == colLength;
    }
    
    //FUNCTION
    // Mengecek apakah semua elemen pada suatu baris itu nol
    public boolean isZeroRow(int row){
        for(int cols = 0; cols < colLength; cols++){
            if (matrix[row][cols] != 0){
                return false;
            }
        }
        return true;
    }

    //FUNCTION
    // Mengecek apakah semua elemen pada suatu kolom itu nol
    public boolean isZeroCol(int col){
        for(int rows = 0; rows < rowLength; rows++){
            if (matrix[rows][col] != 0){
                return false;
            }
        }
        return true;
    }

    //FUNCTION
    //Melakukan pengecekann apakah sebuah matriks itu identitas atau bukan
    public boolean isIdentity(){
        for(int rows = 0; rows < rowLength; rows++){
            for (int cols = 0; cols < colLength; cols++)
                if (rows == cols){
                    if (matrix[rows][cols] != 1){
                        return false;
                    }
                } else {
                    if (matrix[rows][cols] != 0){
                        return false;
                    }
                }
        }
        return true;
    }


    // ==========================  PRIMITIF LAIN ==========================

    // PROCEDURE
    // Melakukan perkalian skalar pada baris
    public void scalarMultiplyRow(int scalar){
        int row = scanObj.nextInt();
        for(int cols = 0; cols < colLength; cols++){
            matrix[row][cols] *= scalar;
        }
    }

    // PROCEDURE
    // Melakukan perkalian skalar pada kolom
    public void scalarMultiplyCol(int scalar){
        int col = scanObj.nextInt();
        for(int rows = 0; rows < rowLength; rows++){
            matrix[rows][col] *= scalar;
        }
    }

    // PROCEDURE
    // Melakukan perkalian skalar pada semua elemen pada matriks
    public void multiplyByConst(Matrix m, double p){
        for(int i = 0; i<m.getRowLength();i++){
            for(int j =0; j<m.getColLength();j++){
                m.setElmt(i, j, m.getElmt(i, j)*p);
            }
        }
    }

    // FUNCTION
    // Mencari nilai matriks equation, dari matriks augmented
    public Matrix getMOriginal(Matrix m){
        Matrix mResult = new Matrix(m.getRowLength(),m.getColLength()-1);
        for(int i =0; i< m.getRowLength();i++){
            for(int j =0; j< m.getColLength()-1;j++){
                double temp = m.getElmt(i,j);
                mResult.setElmt(i,j,temp);
            }
        }
        return mResult;
    }

    // FUNCTION
    // Mencari nilai matriks konstanta, dari matriks augmented
    public Matrix getMResult(Matrix m){
        Matrix mResult = new Matrix(m.getRowLength(),1);
        for(int i = 0; i<m.getRowLength();i++){
            double temp = m.getElmt(i, m.getColIdx());
            mResult.setElmt(i, 0, temp);
        }
        return mResult;
    }

    // FUNCTION
    // Melakukan transpose matriks
    public Matrix transpose(){
        Matrix mOut;
        int rowsT = this.rowLength;
        int colsT = this.colLength;
        mOut = new Matrix(colsT,rowsT);
        for(int i = 0; i < colsT; i++){
            for(int j = 0;j <rowsT; j++){
                mOut.setElmt(i, j, this.getElmt(j, i));
            }
        }
        return mOut;
    }

    // PROCEDURE
    // Mengecek apakah suatu matriks itu identitas
    public void setToIdentity(){
        for(int rows = 0; rows < rowLength; rows++){
            for(int cols = 0; cols < colLength; cols++){
                if (rows == cols) {
                    setElmt(rows, cols, 1);
                } else {
                    setElmt(rows, cols, 0);
                }
            }
        }
    }

    // PROCEDURE
    // Membuat suatu matriks menjadi matriks kosong
    public void setToEmpty(){
        for(int i = 0; i< getRowLength(); i++){
            for (int j = 0; j < getColLength(); j++){
                setElmt(i, j, MARK);
            }
        }
    }

    // PROCEDURE
    // Melakukan tukar baris pada matriks
    public void swapRow(int row1, int row2){
        double[] temp = matrix[row1];
        matrix[row1] = matrix[row2];
        matrix[row2] = temp;
    }

    // PROCEDURE
    // Melakukan tukar baris pada matriks
    public void swapCol(int col1, int col2){
        this.transpose();
        swapRow(col1, col2);
        this.transpose();
    }

    public double getColSum(Matrix m,int j){
        double sum = 0;
        for(int i = 0; i< m.getRowLength();i++){
            sum += getElmt(i,j);
        }
        return sum;
    }
}
