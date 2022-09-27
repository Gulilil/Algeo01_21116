import java.util.Scanner;
import ADTMatrix.Matrix;
import ADTMatrix.InputOutput;
import ADTMatrix.MatrixOps;

public class Interpolasi {
    Scanner scanObj = new Scanner(System.in);
    static InputOutput io = new InputOutput();
    static MatrixOps op = new MatrixOps();

    public void bacaInterpolasi(){
        System.out.println("Masukkan nilai n yang diinginkan : ");
        int n = scanObj.nextInt();

        System.out.println("Masukkan nilai x yang ingin di-interpolasi : ");
        float xOutput = scanObj.nextFloat();
        Matrix mTemp = new Matrix(n, n+1);
        // Membuat matriks dari persamaan - persamaan berdasarkan input user
        for(int i =0 ; i<n; i++){

            System.out.println("Masukkan nilai titik ke- " + (i+1));
            // Meminta input dari user untuk nilai x dan y 
            float x = scanObj.nextFloat();
            float y = scanObj.nextFloat();

            // Meng-assign nilai x dipangkatkan dengan integer tertentu ke dalam matriks
            for(int j =0 ; j<n+1; j++){
                if(j != n){
                    mTemp.setElmt(i, j, Math.pow(x, j));
                }else{
                    mTemp.setElmt(i,j,y);
                }
            }
        }
        Matrix mOriginal = mTemp.getMOriginal(mTemp);
        Matrix mConst = mTemp.getMResult(mTemp);
        op.upperTriangleMatrix(mOriginal, mConst);
        op.lowerTriangleMatrix(mOriginal, mConst);
        System.out.println("=================================================");
        System.out.println("Persamaan interpolasi : ");
        System.out.print("f(x) = ");
        for(int i = 0 ; i < mConst.getRowLength();i++){
            if(i == 0){
                System.out.print(mConst.getElmt(i,0) + " + ");
            }else if ( i == mConst.getRowLength()-1){
                if(i != 1){
                    System.out.println(mConst.getElmt(i, 0) + "x^" +i);
                }else{
                    System.out.println(mConst.getElmt(i, 0) + "x");
                }
            }else if(i == 1 && i != mConst.getRowLength()-1){
                System.out.print(mConst.getElmt(i, 0) + "x ");
            }else{
                System.out.print(mConst.getElmt(i, 0) + "x^" + i + "+ ");
            }
        }
        
        float result =0;
        for(int i =0 ; i<mConst.getRowLength();i++){
            result += mConst.getElmt(i,0)*Math.pow(xOutput, i);
        }
        System.out.println( "Hasil interpolasi dari " + xOutput + " adalah " + result);
    }        
    
}
