import ADTMatrix.Matrix;

public class driver {
    public static void main(String[] args){
        // cek Matriks Constructor
        Matrix m1 = new Matrix(2,3);

        // Cek display matriks
        m1.printMatrix();

        // Cek row dan col length 
        System.out.println(m1.getColLength());
        System.out.println(m1.getRowLength());

        // Coba set row dan col  
        // m1 awalnya memiliki 2 row dan 3 col, diubah jadi 4 row dan 2 col
        m1.setRowLength(4);
        m1.setColLength(2);
        m1.printMatrix();
    }
}
