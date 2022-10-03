package Interpolation;
import java.util.Scanner;
import ADTMatrix.Matrix;
import ADTMatrix.InputOutput;
import ADTMatrix.MatrixOps;

public class Interpolasi {
    Scanner scanObj = new Scanner(System.in);
    static InputOutput io = new InputOutput();
    static MatrixOps op = new MatrixOps();
    
    public String bacaInterpolasi(Matrix mIn, double xOutput){
        // System.out.println("Masukkan nilai n yang diinginkan : ");
        // int n = scanObj.nextInt();
        int n = mIn.getRowLength();
        // Matrix mPersamaan = new Matrix(n, 2);
        // for(int i = 0 ; i<n;i++){
        //     System.out.println("Masukkan nilai titik ke -"+ (i+1));
        //     double x = scanObj.nextDouble();
        //     double y = scanObj.nextDouble();
        //     for(int j =0; j<2;j++){
        //         if(j != 1){
        //             mPersamaan.setElmt(i, j, x);
        //         }else{
        //             mPersamaan.setElmt(i, j, y);
        //         }
        //     }
        // }

        Matrix mTemp = new Matrix(n, n+1);
        // Membuat matriks dari persamaan - persamaan berdasarkan input user
        for(int i =0 ; i<n; i++){
            // Meng-assign nilai x dipangkatkan dengan integer tertentu ke dalam matriks
            for(int j =0 ; j<n+1; j++){
                if(j != n){
                    mTemp.setElmt(i, j, Math.pow(mIn.getElmt(i, 0), j));
                }else{
                    mTemp.setElmt(i,j,mIn.getElmt(i, 1));
                }
            }
        }
        String temp="";
        Matrix mOriginal = mTemp.getMOriginal(mTemp);
        Matrix mConst = mTemp.getMResult(mTemp);
        op.upperTriangleMatrix(mOriginal, mConst);
        op.lowerTriangleMatrix(mOriginal, mConst);
        //Bagian output
        // System.out.println("=================================================");
        // System.out.println("Persamaan interpolasi : ");
        
        for(int i = 0 ; i < mConst.getRowLength();i++){
            if(i == 0){
                temp += Double.toString(mConst.getElmt(i, 0)) + (mConst.getElmt(i+1,0)<0 ? " " : " + ");
            }else if ( i == mConst.getRowLength()-1){
                if(i != 1){
                    temp +=  Double.toString(mConst.getElmt(i, 0)) + "x^" +Integer.toString(i);
                }else{
                    temp += Double.toString(mConst.getElmt(i, 0)) + "x";
                }
            }else if(i == 1 && i != mConst.getRowLength()-1){
                temp += Double.toString(mConst.getElmt(i, 0)) + "x " + (mConst.getElmt(i+1, 0) < 0 ? " " : " + ");
            }else{
                temp += Double.toString(mConst.getElmt(i, 0)) + "x^" + Integer.toString(i) + (mConst.getElmt(i+1, 0) < 0 ? " " : " + ");
            }
        }        
        double result =0;
        for(int i =0 ; i<mConst.getRowLength();i++){
            result += mConst.getElmt(i,0)*Math.pow(xOutput, i);
        }
        String str = "Hasil interpolasi dari " + Double.toString(xOutput) + " adalah " + Double.toString(result) + "\n" + "f(x) = " + temp;
        return str;
    }        
    
}
