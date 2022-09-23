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
        float xOutput = scanObj.nextFloat();
        Matrix mTemp = new Matrix(n, n+1);
        // Membuat matriks dari persamaan - persamaan berdasarkan input user
        for(int i =0 ; i<n; i++){

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

        Matrix mResult = op.splGaussJordan(mTemp, true);
        float result =0;
        for(int i =0 ; i<mResult.getRowLength();i++){
            result += mResult.getElmt(i,0)*Math.pow(xOutput, i);
        }
        System.out.println(result);
        // io.printMatrix(mTemp);        
    }
}
