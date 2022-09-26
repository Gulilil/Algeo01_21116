package BicubicInterpolation;

import java.util.Scanner;
import ADTMatrix.*;

public class BicubicInterpolation{

    Scanner scanObj = new Scanner(System.in);
    MatrixOps mo = new MatrixOps();

    Matrix sol = new Matrix(16, 1);
    Matrix augMatrix = new Matrix(16, 16);
    
    public double m(double x, double y, int i, int j){
        return Math.pow(x, i)*Math.pow(y, j);
    }

    public Matrix getAugMatrix(){
        for(int y = -1; y <= 2; y++){
            for(int x = -1; x <= 2; x++){
                for(int j = 0; j < 16; j++){
                    for(int i = 0; i < 16; i++){
                        this.augMatrix.setElmt(i, j, m(x, y, , ));
                    }
                }
            }
        }
        return this.augMatrix;
    }

    public void isiSol(){
        for(int i = 0; i <= 16; i++){
            this.sol.setElmt(i, 0, scanObj.nextDouble());
        }
    }

    public Matrix hasilKoef(Matrix augMatrix, Matrix sol){
        return mo.multiplyMatrix(mo.inverse(augMatrix), sol);
    }

}