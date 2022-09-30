package BicubicInterpolation;

import ADTMatrix.*;

public class BicubicInterpolation{

    MatrixOps mo = new MatrixOps();
    Matrix augMatrix = new Matrix(16, 16);
    Matrix invAugMatrix = new Matrix(16, 16);

    // Konstruktor aug matrix
    public BicubicInterpolation(){
        for(int y = -1; y <= 2; y++){
            for(int x = -1; x <= 2; x++){
                for(int j = 0; j <= 3; j++){
                    for(int i = 0; i <= 3; i++){
                        double val = calcElmt(x, y, i, j);
                        augMatrix.setElmt((4*(y+1)+(x+1)), (4*j + i), val);
                    }
                }
            }
        }
        Matrix tempInv = mo.gaJoInverse(augMatrix);
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
        double result = 0;
        for(int j = 0; j <= 3; j++){
            for(int i = 0; i <= 3; i++){
                result += coef.getElmt((4*j + i), 0) * calcElmt(x, y, i, j);
            }
        }
        return result;
    }
}