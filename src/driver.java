import ADTMatrix.*;


public class driver {

    static MatrixOps mOps = new MatrixOps();
    public static void main(String[] args){
        // cek Matriks Constructor
        Matrix m1 = new Matrix(3,4);
        m1.setElmt(0,0,3); m1.setElmt(0, 1, 2); m1.setElmt(0, 2, 1);m1.setElmt(0, 3, 7);
        m1.setElmt(1,0,6); m1.setElmt(1, 1, 5); m1.setElmt(1, 2, 6);m1.setElmt(1, 3, 6);
        m1.setElmt(2,0,7); m1.setElmt(2, 1, 8); m1.setElmt(2, 2, 9);m1.setElmt(2, 3, 8);
        // m1.setElmt(3,0,7); m1.setElmt(3, 1, 3); m1.setElmt(3, 2, 2);m1.setElmt(3, 3, 6);
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
        //m1.printMatrix();

        // System.out.println("================");

        // for (int i = 0; i < m1.getRowLength(); i++){
        //     m1.setElmt(i,0, m1.getElmt(m1.getRowIdx(), m1.getColIdx()));
        // }

        //System.out.println(mOps.detObe(m1));
        
        mOps.cramer(m1);

        // Matrix m2;
        // m2 = mOps.delLastRow(m1);
        // m2.printMatrix();

        // // coba transpose element 
        // System.out.println("Matriks yang sudah ditranspose");
        // Matrix temp1 = m1.transpose();
        // temp1.printMatrix();
    }
}
