// import java.util.Scanner;

import java.util.Scanner;

import ADTMatrix.InputOutput;
import ADTMatrix.Matrix;
import ADTMatrix.MatrixOps;

public class MultipleLinearRegression {
    // Scanner scanObj = new Scanner(System.in);
    static InputOutput io = new InputOutput();
    static MatrixOps op = new MatrixOps();
    Scanner scanObj = new Scanner(System.in); 

    public void regresiLinear(Matrix mIn){
        int n, m;

        // Memisahkan augmented matrix
        Matrix mOriginal = mIn.getMOriginal(mIn);

        // Memasukkan jumlah peubah x dalam regresi linear berganda
        n = mOriginal.getColLength();
        // System.out.println("Masukkan banyaknya peubah X : ");
        // int n = scanObj.nextInt();

        // Masukkan jumlah elemen x
        m = mOriginal.getRowLength();
        // System.out.println("Masukkan banyaknya elemen dalam setiap peubah ");
        // int m = scanObj.nextInt();

        // Declare matrix sementara untuk menampung data - data yang diberikan
        Matrix mData = mIn;
        // Matrix mData =  new Matrix(m, n+1);

        Matrix mSum = new Matrix(1,n+1);

        // Ini kan cuma masukin
        // for(int i =0 ; i<mData.getRowLength();i++){
        //     for(int j =0 ; j<mData.getColLength();j++){
        //         if(j != mData.getColLength()-1){
        //             System.out.print("Masukkan nilai elemen X" + (j+1) + (i+1) + " : ");
        //             temp = scanObj.nextFloat();
        //             mData.setElmt(i, j, temp);
        //         }else{
        //             System.out.println("Masukkan nilai elemen Y : ");
        //             temp = scanObj.nextFloat();
        //             mData.setElmt(i, j, temp);
        //         }
        //     }
        // }

        // Menyimpan nilai total dari data - data yang ada 
        for(int i =0; i<=n;i++){
            mSum.setElmt(0, i, mData.getColSum(mData, i));
        }

        // Menyimpan hasil perkalian dari matriks 1 dengan matriks lain 
        Matrix sumMultiply = new Matrix(n, n);
        for(int i =0 ; i < n;i++){
            for(int j =0 ; j<n; j++){
                float result = 0 ; 
                for (int k =0 ;k<m;k++){
                    result += mData.getElmt(k, i)*mData.getElmt(k, j);
                }
                sumMultiply.setElmt(i, j,result);
            }
        }

        Matrix sumMultiplyXY = new Matrix(1, n);
        for (int i = 0 ; i < n; i++){
            float temp2 = 0;
            for(int k =0 ; k< m; k++){
                temp2 += mData.getElmt(k, i) * mData.getElmt(k, n);
            }
            sumMultiplyXY.setElmt(0, i, temp2);
        }

        Matrix mReg = new Matrix(n+1, n+2);
        for (int i =0 ; i<n+1; i++){
            for(int j =0 ; j<=n+1; j++){
                if(i == 0 && j ==0 ){
                    mReg.setElmt(i, j, m);
                }else if(i ==0 && j != 0){
                    mReg.setElmt(i,j,(mSum.getElmt(0, j-1)));
                }else if(i != 0 && j ==0){
                    mReg.setElmt(i,j,(mSum.getElmt(0, i-1)));
                }else if(i != 0 && j==n+1){
                    mReg.setElmt(i,j,(sumMultiplyXY.getElmt(0, i-1)));
                }else{
                    mReg.setElmt(i, j, (sumMultiply.getElmt(i-1, j-1)));
                }
            }
        }

        // System.out.println("Matriks hasil akhir ");
        // System.out.println("===================================================");
        // io.printMatrix(mReg);

        // Memisahkan augmented matriks mReg
        //MatrixOps.upperTriangleMatrix();
        Matrix mTemp = mReg.getMOriginal(mReg);
        Matrix mResult = mReg.getMResult(mReg);
        op.upperTriangleMatrix(mTemp, mResult);
        op.lowerTriangleMatrix(mTemp, mResult);
        // Matrix mResult2 = op.splInverse(mReg);
        for(int i =0 ; i < mResult.getRowLength();i++){
            if(i ==0){
                System.out.print(mResult.getElmt(i,0) + (mResult.getElmt(i+1, 0)<0?" " : " + "));
            }else if(i == 1 && i != mResult.getRowLength()-1){
                System.out.print(mResult.getElmt(i, 0) + "x1" + (mResult.getElmt(i+1, 0)< 0? " " : "+"));
            }else if(i == mResult.getRowLength()-1){
                if(i == 1){
                    System.out.println(mResult.getElmt(i, 0));
                }else{
                    System.out.println(mResult.getElmt(i, 0) + "x"+Integer.toString(i));
                }
            }else{
                System.out.print(mResult.getElmt(i, 0) + "x" + Integer.toString(i) + (mResult.getElmt(i+1, 0)<0 ? " ":" + "));
            }
        }
        boolean check = true; 
        double result = mResult.getElmt(0, 0);
        while(check){
            System.out.println("Masukkan nilai X peubah yang ingin dicek");
            for(int i =0; i< n;i++){
                System.out.println("Masukkan nilai X peubah ke- " + Integer.toString(i));
                double temp = scanObj.nextDouble();
                result += mResult.getElmt(i+1, 0) * temp;
            }
            System.out.println(result);
            System.out.println("Apakah masih ingin mengecek nilai X peubah lain dengan f(x) yang sama? (1 : iya, 2 : tidak)");
            int option = scanObj.nextInt();
            if(option ==2){
                check = false;
            }
        }
        
    }
}
