package BicubicInterpolation;

import ADTMatrix.*;

public class BicubicInterpolation{

    MatrixOps mo = new MatrixOps();
    Matrix augMatrix = new Matrix(16, 16);
    Matrix invAugMatrix = new Matrix(16, 16);

    // Konstruktor aug matrix dan invers
    public BicubicInterpolation(){
        Matrix koef = new Matrix(16, 16); //matrix variable dari tiap fungsi
        int i, j;
        int baris=0;
        int kolom=0;
        double x, y;

        for (x = -1; x < 3;x++){
            for (y = -1; y < 3; y++) {
                kolom = 0;
                for (i = 0; i < 4; i++) {
                    for (j = 0; j < 4; j++) {
                        koef.setElmt(baris, kolom, Math.pow(x, i) * Math.pow(y, j));
                        kolom++;
                    }
                }
                baris++;
            }
        }
        Matrix tempInv = mo.gaJoInverse(koef);
        invAugMatrix = tempInv;
    }

    //Rumus Model
    public double calcElmt(double x, double y, int i, int j){
        return Math.pow(x, i) * Math.pow(y, j);
    }

    //Mengambil augMatrix Bicubic
    public Matrix getAugMatrix(){
        return this.augMatrix;
    }

    //Mendapatkan inverse matriks
    public Matrix getInvMatrix(){
        return this.invAugMatrix;
    }

    //Mendapatkan matriks koefisien
    public Matrix getCoefMatrix(Matrix sol){
        return mo.multiplyMatrix(this.invAugMatrix, sol);
    }

    //Melakukan proses interpolasi suatu nilai
    public double interpolate(double x, double y, Matrix coef){
        int row = 0;
        double hasil = 0;
        int i, j;
        
        for (i = 0; i <= 3; i++) {
            for (j = 0; j <= 3; j++) {
                hasil += Math.pow(x, i) * Math.pow(y, j) * coef.getElmt(row, 0);
                row++;
            }
        }
        return hasil;
    }
}