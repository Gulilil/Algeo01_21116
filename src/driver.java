import ADTMatrix.Matrix;
import ADTMatrix.MatrixOps;

public class driver {

    static MatrixOps mOps = new MatrixOps();
    public static void main(String[] args){
        // cek Matriks Constructor
        Matrix m1 = new Matrix(3,3);
        m1.setToIdentity();
        System.out.println(m1.getRowIdx());
        System.out.println(m1.getColIdx());

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
        m1.printMatrix();

        System.out.println(mOps.det(m1));

        // // coba transpose element 
        // System.out.println("Matriks yang sudah ditranspose");
        // Matrix temp1 = m1.transpose();
        // temp1.printMatrix();
    }
}
