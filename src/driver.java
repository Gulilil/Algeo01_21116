import ADTMatrix.*;

public class driver {

    static MatrixOps mOps = new MatrixOps();
    static InputOutput io = new InputOutput();
    public static void main(String[] args){
        // cek Matriks Constructor
        Matrix m1 = new Matrix(3,4);
        m1.setElmt(0,0,1); m1.setElmt(0, 1, 2); m1.setElmt(0, 2, 3); m1.setElmt(0, 3,1);
        m1.setElmt(1,0,2); m1.setElmt(1, 1, 8); m1.setElmt(1, 2, 7); m1.setElmt(1, 3, 7);
        m1.setElmt(2,0,1); m1.setElmt(2, 1, 5); m1.setElmt(2, 2, 6); m1.setElmt(2, 3, 8);
        //m1.setElmt(3,0,7); m1.setElmt(3, 1, 3); m1.setElmt(3, 2, 2); m1.setElmt(3, 3, 6);

        Matrix m2 = new Matrix(2,2);
        m2.setElmt(0, 0, 2);m2.setElmt(0, 1, 3);
        m2.setElmt(1, 0, 4);m2.setElmt(1,1, 10);

        Matrix m3 = new Matrix(3,3);
        m3.setElmt(0,0,3); m3.setElmt(0, 1, 4); m3.setElmt(0, 2, 5);
        m3.setElmt(1,0,5); m3.setElmt(1, 1, 8); m3.setElmt(1, 2, 4);
        m3.setElmt(2,0,6); m3.setElmt(2, 1, 6); m3.setElmt(2, 2, 9);

        Matrix m4;
        // Matrix m4 = new Matrix (3, 1);
        // m4.setElmt(0,0,5);
        // m4.setElmt(1,0,7);
        // m4.setElmt(2,0,2);

        // Matrix m4 = new Matrix(3, 3);
        // m4.setToIdentity();

        // Matrix m5;

        // m5 = mOps.inverse(m3);
        // m5.printMatrix();
        // // Test triangle matriks
        // // m3.printMatrix();
        // // m4.printMatrix();
        // System.out.println("===================");
        // mOps.lowerTriangleMatrix(m3, m4);
        // // m3.printMatrix();
        // // m4.printMatrix();
        // mOps.upperTriangleMatrix(m3, m4);
        // m3.printMatrix();
        // m4.printMatrix();

        
        m4 = io.readMatrix();
        io.printMatrix(m4);
        

        

        // Test perkalian, penambahan, dan pengurangan
        // m1.printMatrix();
        // System.out.println("==========================");
        // m3.printMatrix();

        // Matrix mNew;
        // mNew = mOps.substractMatrix(m1, m3);
        // System.out.println("==========================");
        // mNew.printMatrix();
        


        // System.out.println(m1.getRowIdx());
        // System.out.println(m1.getColIdx());

        // // Cek display matriks
        // System.out.println("Nilai awal matriks");
        // m1.printMatrix();

        // // Cek row dan col length 
        // System.out.println(m1.getColLength());
        // System.out.println(m1.getRowLength());

        // // Coba set row dan col  
        // // m1 awalnya memiliki 2 row dan 3 col, diubah jadi 4 row dan 2 col
        // System.out.println("Nilai matriks setelah diubah kolom dan barisnya");
        // m1.setRowLength(4);
        // m1.setColLength(3);
        // m1.printMatrix();
        
        // //coba set element
        // System.out.println("Nilai matriks setelah diset elemennya : ");
        // m1.setElmt(1,0,10);
        // m1.setElmt(1,1,10);
        // m1.setElmt(1,2,10);
        //m1.printMatrix();

        // System.out.println("================");

        // for (int i = 0; i < m1.getRowLength(); i++){
        //     m1.setElmt(i,0, m1.getElmt(m1.getRowIdx(), m1.getColIdx()));
        // }

        //System.out.println(mOps.detObe(m1));
        
        // mOps.cramer(m1);

        // Matrix m2;
        // m2 = mOps.delLastRow(m1);
        // m2.printMatrix();

        // // coba transpose element 
        // System.out.println("Matriks yang sudah ditranspose");
        // Matrix temp1 = m1.transpose();
        // temp1.printMatrix();
        
        // Cek determinan
        // m1.printMatrix();
        // System.out.println(mOps.detKof(m1));
        // System.out.println(mOps.detObe(m1));
        // Matrix mtemp =mOps.inverse(m1);
        // mtemp.printMatrix();
        // System.out.print(mOps.inverse(m1));

        // CEK SOAL SPL
        // CEK GAUSS
        // mOps.splGaussJordan(m1, true);
        // mOps.splGaussJordan(m1, false);
        // CEK CRAMER
        // mOps.splCramer(m1);
        // CEK INVERSE
        // mOps.splInverse(m1);
    }
}
