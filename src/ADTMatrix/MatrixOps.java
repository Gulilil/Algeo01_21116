package ADTMatrix;

public class MatrixOps {

    // ============================== OPERASI MATRIKS ============================== 

    //FUNCTION
    // Menyalin matriks pada suatu matriks lain
    public Matrix copyMatrix (Matrix m){
        Matrix mOut = new Matrix(m.getRowLength(), m.getColLength());
        for (int i = 0; i <= m.getRowIdx(); i++){
            for (int j = 0; j <= m.getColIdx(); j++){
                mOut.setElmt(i, j, m.getElmt(i, j));
            }
        }
        return mOut;
    }

    // FUNCTION
    // Mencari determinan dari sebuah matriks dengan Kofaktor
    public double detKof(Matrix m){
        double det = 0;
        if (!m.isSquare()){
            return m.MARK;
        }
        if (m.getRowLength() == 1)
        {
            return m.getElmt(0, 0);
        }
        else
        {
            for (int i = 0; i <= m.getColIdx(); i++)
            {
                Matrix mTemp = new Matrix(m.getRowLength() - 1, m.getColLength() - 1);
                for (int j = 1; j <= m.getRowIdx(); j++)
                {
                    for (int k = 0; k <= m.getColIdx(); k++)
                    {
                        double val = m.getElmt(j,k);
                        if (k < i)
                        {
                            mTemp.setElmt(j-1, k, val);

                        }
                        else if (k > i)
                        {
                            mTemp.setElmt(j-1, k-1, val);

                        }
                        
                    }
                    
                }
                
                det += Math.pow(-1, i) * m.getElmt(0, i) * detKof(mTemp);
            }
            return det;
        }
    }

    // FUNCTION
    // Mencari determinan dengan cara OBE dan Kofaktor
    public double detObe (Matrix m){
        double det;
        int count;
        int swapCount = 0;
        double pembuatNol;
        double newVal;

        Matrix mTemp = new Matrix(m.getRowLength() - 1, m.getColLength() - 1);

        if (!m.isSquare()){
            return m.MARK;
        }
        if (m.getRowLength() == 1)
        {
            return m.getElmt(0, 0);
        } else {
            // Rekurens
            // Cek dulu apakah element pertama bernilai 0
            if (m.getElmt(0, 0) == 0){
                count = 0;
                while (m.getElmt(0, 0) == 0 && count < m.getRowLength()){
                    count++;
                }
                if ( count == m.getRowLength()){
                    return 0;
                } else {
                    m.swapRow(0, count);
                    swapCount++;
                }
            }

            // Membuat 0 semua baris lain pada kolom pertama
            // Mengurangi semua elemen pada baris 1 dengan k.elemen dari pada baris pertama
            for (int i = 1; i < m.getRowLength(); i++){
                // Jika sudah 0 maka dibiarkan saja
                if (m.getElmt(i, 0) != 0){
                    pembuatNol = m.getElmt(i, 0)/ m.getElmt(0, 0);
                    for (int j = 0; j < m.getColLength(); j++){
                        newVal = m.getElmt(i, j) - pembuatNol* m.getElmt(0, j);
                        m.setElmt(i, j, newVal);
                    }
                }
            }
            
            // memasukkan submatriks menjadi matriks baru
            for (int i = 1; i < m.getRowLength(); i++){
                for (int j = 1; j < m.getColLength(); j++){
                    mTemp.setElmt(i-1,j-1, (m.getElmt(i, j)));
                }
            }

            // setiap adanya pertukaran baris, nilai determinan dikali -1
            det = Math.pow(-1, swapCount) * m.getElmt(0,0) * detObe(mTemp);
            return det;
            
        }

    }
    
    //FUNCTION
    // Mencari matriks kofaktor dari sebuah matriks 
    public Matrix kofaktor(Matrix mIn, int row, int col){
        // Menerima input berupa matriks serta baris dan kolom yang tidak ingin dimasukkan 

        // Inisialisasi matrix
        Matrix mTemp = new Matrix(mIn.getRowLength()-1, mIn.getColLength()-1);
        int a = 0; 
        int b = 0;
        for(int i =0;i < mIn.getRowLength();i++){
            for(int j =0; j < mIn.getColLength(); j++){
                if(i != row && j != col){
                    double temp = mIn.getElmt(i,j);
                    mTemp.setElmt(a,b,temp);
                    b+=1;
                    if(b == mIn.getColLength()-1){
                        b = 0; 
                        a++;
                    }
                }
            }
        }
        return mTemp;
    }

    //FUNCTION
    // Membentuk matriks adjoin dari sebuah matriks
    public Matrix adj(Matrix mInput){
        Matrix mTemp = new Matrix(mInput.getRowLength(), mInput.getColLength());
        for(int i =0 ; i < mInput.getRowLength(); i++){
            for(int j=0; j <mInput.getColLength(); j++){
                double temp = Math.pow(-1, i+j) * detKof(kofaktor(mInput,i,j));
                mTemp.setElmt(i,j,temp);
            }
        }
        return mTemp.transpose();
    }

    // FUNCTION
    // Mencari nilai matriks inverse dari sebuah matriks
    public Matrix inverse(Matrix m){
        double determinan = detKof(m);
        Matrix adj = adj(m);
        adj.multiplyByConst(adj, (1/determinan));
        return adj;
    }
    
    // FUNCTION
    // Membuat matriks baru dengan membuang baris terakhir dalam matriks
    public Matrix delLastRow (Matrix m){
        Matrix mTemp = new Matrix(m.getRowLength() - 1, m.getColLength());
        for (int i =0 ; i < m.getRowLength()-1; i++){
            for (int j = 0; j < mTemp.getColLength(); j++ ){
                mTemp.setElmt(i, j, m.getElmt(i, j));
            }
        }
        return mTemp;
    }

    // FUNCTION
    // Mengembalikan matriks baru yang merupakan perkalian dua matriks
    public Matrix addMatrix (Matrix m1, Matrix m2){
        // Hanya dilakukan bila panjang dan lebar matriksnya sama
        Matrix mResult = new Matrix(m1.getRowLength(), m2.getColLength());
        // Jika ukuran matriks tidak sama maka mengembalikan matriks kosong (berisikan NaN)
        if ((m1.getRowLength() == m2.getRowLength()) && (m1.getColLength() == m2.getColLength())){
            for (int i = 0; i < m1.getRowLength(); i++){
                for (int j = 0; j < m2.getColLength(); j++){
                    mResult.setElmt(i, j, (m1.getElmt(i, j) + m2.getElmt(i, j)));
                }
            }
        }
        return mResult;
    }

    // FUNCTION
    // Mengembalikan matriks baru yang merupakan perkalian dua matriks
    public Matrix substractMatrix (Matrix m1, Matrix m2){
        // Hanya dilakukan bila panjang dan lebar matriksnya sama
        Matrix mResult = new Matrix(m1.getRowLength(), m2.getColLength());
        // Jika ukuran matriks tidak sama maka mengembalikan matriks kosong (berisikan NaN)
        if ((m1.getRowLength() == m2.getRowLength()) && (m1.getColLength() == m2.getColLength())){
            for (int i = 0; i < m1.getRowLength(); i++){
                for (int j = 0; j < m2.getColLength(); j++){
                    mResult.setElmt(i, j, (m1.getElmt(i, j) - m2.getElmt(i, j)));
                }
            }
        }
        return mResult;
    }

    // FUNCTION
    // Mengembalikan matriks baru yang merupakan perkalian dua matriks
    public Matrix multiplyMatrix (Matrix m1, Matrix m2){
        Matrix mResult = new Matrix(m1.getRowLength(), m2.getColLength());
        double sum;

        for (int i = 0; i < m1.getRowLength(); i++){
            for (int j = 0; j < m2.getColLength(); j++){
                sum = 0;
                for (int k = 0; k < m1.getColLength(); k++){
                    sum = sum + (m1.getElmt(i,k) * m2.getElmt(k ,j));
                }
                mResult.setElmt(i, j, sum);
            }
        }
        return mResult;
    }

    // PROCEDURE
    // Mengembalikan matriks segitiga bawah dari suatu matriks menggunakan OBE
    public void lowerTriangleMatrix (Matrix m, Matrix mConst){
        // Prosedur hanya dijalankan apabila matriks berbentuk persegi
        if (m.isSquare()){
            int row = 0;
            int col = 0;
            int i;
            int j;
            int k;
            double pembuatNol;
            double newVal;
            double pembuatSatu;
            boolean program = true;
            int count;
            
    
            // Variabel program menunjukkan bahwa pembuatan segitiga masih dilakukan
            while (program == true){

                // Bila program telah mencapai elemen terakhir (pojok kanan bawah) maka program dihentikan
                if (row == m.getRowIdx() && col == m.getColIdx()){
                    // Jika hasil akhir bukan 1, maka perlu dijadikan 1
                    if (m.getElmt(row, col) != 1){
                        pembuatSatu = m.getElmt(row, col);
                        m.setElmt(row, col, 1);
                        for (k = 0; k < mConst.getColLength(); k++){
                            mConst.setElmt(row, k,(mConst.getElmt(row,k)/ pembuatSatu));
                        }

                    }
                    program = false;
                } else { 
                    // Program masih dilakukan karena row dan col belum mencapai index terakhir
                    // Cek dulu apakah element awal bernilai 0
                    if (m.getElmt(row, col) == 0){
                        count = 0;
                        while (m.getElmt(0, 0) == 0 && count < m.getRowLength()){
                            count++;
                        }
                        // Satu kolom bernilai 0 semua
                        if ( count == m.getRowIdx()){
                            // Jika 0 semua pada kolom tersebut, maka baris kolom tersebut dilewatkan saja
                            row++;
                            col++;
                        } else {
                            // ada satu elemen yang tidak bernilai 0 pada kolom tersebut
                            m.swapRow(row, col+count);
                        }
                    }

                    // Jika pada kolom baris awal bukan 1, maka dijadikan 1 terlebih dahulu
                    if (m.getElmt(row, col) != 1){
                        // semua elemen pada baris itu dibagi dengan elemen pada kolom awal
                        pembuatSatu = m.getElmt(row, col);
                        for (k = col; k < m.getColLength(); k++){
                            m.setElmt(row, k, (m.getElmt(row, k)/ pembuatSatu));
                        }
                        // Matriks konstanta juga dibagi
                        for (k = 0; k < mConst.getColLength(); k++){
                            mConst.setElmt(row, k,(mConst.getElmt(row,k)/ pembuatSatu));
                        }
                        
                    }
                    // Melakukan loop mulai dari baris pertama setelah baris 'awal'
                    for (i = row+1; i < m.getRowLength(); i++){
                        // Membuat 0 semua baris lain pada kolom awal
                        // Jika sudah 0, baris tersebut tidak perlu dilakukan apapun
                        if (m.getElmt(row, col) != 0){
                            pembuatNol = m.getElmt(i, col)/ m.getElmt(row, col);
                            for (j = col; j < m.getColLength(); j++){
                                newVal = m.getElmt(i,j) - pembuatNol * m.getElmt(row, j);
                                m.setElmt(i, j, newVal);
                            }
                            // Matriks const juga dikurangi
                            for (j = 0; j < mConst.getColLength(); j++){
                                newVal = mConst.getElmt(i, j) - pembuatNol * mConst.getElmt(row, j);
                                mConst.setElmt(i, j, newVal);
                            }

                        }
                    }
                    row++;
                    col++;
                }

            }
        }
    }

    // PROCEDURE
    // Mengembalikan matriks segitiga atas dari suatu matriks menggunakan OBE 
    // Prosedur ini adalah LANJUTAN dari lowerTriangleMatrix (JANGAN DIPAKAI TERPISAH!!)
    public void upperTriangleMatrix(Matrix m, Matrix mConst){
        int i;
        int j;
        int row = 1;
        int col = 1;
        double pembuatNol;
        double newVal;
        boolean program = true;

        while (program) {
            // Bila program telah mencapai elemen terakhir (pojok kanan bawah) maka program dihentikan
            if (row == m.getRowLength() && col == m.getColLength()){
                program = false;
            } else {
                for (i = row-1; i >= 0 ; i--){
                    // Membuat 0 semua baris lain pada kolom awal
                    // Jika sudah 0, baris tersebut tidak perlu dilakukan apapun
                    if (m.getElmt(row, col) != 0){
                        pembuatNol = m.getElmt(i, col);
                        for (j = col; j < m.getColLength(); j++){
                            newVal = m.getElmt(i,j) - pembuatNol * m.getElmt(row, j);
                            m.setElmt(i,j,newVal);
                        }
                        // Matriks const juga dikurangi
                        for (j =0 ; j < mConst.getColLength(); j++){
                            newVal = mConst.getElmt(i, j) - pembuatNol * mConst.getElmt(row, j);
                            mConst.setElmt(i,j, newVal);
                        }
                    }
                }
                row++;
                col++;
            }   
        }
    }


    // ============================== PENYELESAIAN SPL ============================== 

    // PROCEDURE
    // Menyelesaikan permasalahan SPL menggunakan metode Gauss
    public void splGaussJordan (Matrix mIn, boolean jordan){
        // Jika jordan true, maka yang dihasilkan adalah metode Gauss-Jordan
        // Jika jordan false, maka yang dihasilkan adalah metode Gauss

        // Inisiasi matriks original
        Matrix mOriginal = mIn.getMOriginal(mIn);
        // Inisasi matriks konstanta (bagian kolom paling kanan)
        Matrix mConstant = mIn.getMResult(mIn);
        System.out.println("Bentuk Awal Matriks: ");
        mOriginal.printMatrix();
        System.out.println("\n");

        System.out.println("Bentuk Matriks Konstanta: ");
        mConstant.printMatrix();
        System.out.println("\n");

        if (jordan){
            System.out.println("================== PENYELESAIAN SPL METODE GAUSS JORDAN ==================");
            // User menginginkan metode Gauss Jordan
            lowerTriangleMatrix(mOriginal, mConstant);
            upperTriangleMatrix(mOriginal, mConstant);
            System.out.println("Bentuk Akhir Matriks Segitiga: ");
            mOriginal.printMatrix();
            System.out.println("\n");

            System.out.println("Bentuk Akhir Matriks Konstanta: ");
            mConstant.printMatrix();
            System.out.println("\n");

            for (int i = 0; i < mConstant.getRowLength(); i++){
                System.out.println("X"+(i+1)+" = "+ mConstant.getElmt(i,0));
            }
        } else {
            // User menginginkan metode Gauss
            System.out.println("================== PENYELESAIAN SPL METODE GAUSS ==================");
            lowerTriangleMatrix(mOriginal, mConstant);
            System.out.println("Bentuk Akhir Matriks Segitiga: ");
            mOriginal.printMatrix();
            System.out.println("\n");

            System.out.println("Bentuk Akhir Matriks Konstanta: ");
            mConstant.printMatrix();
            System.out.println("\n");

            upperTriangleMatrix(mOriginal, mConstant);
            for (int i = 0; i < mConstant.getRowLength(); i++){
                System.out.println("X"+(i+1)+" = "+ mConstant.getElmt(i,0));
            }
        }
    }

    // PROCEDURE
    // Menyelesaikan permasalahan SPL menggunakan metode Inverse
    public void splInverse(Matrix mIn){

        // Inisiasi matriks original
        Matrix mOriginal = mIn.getMOriginal(mIn);
       if(detKof(mOriginal) == 0){
            System.out.println("SPL tidak memiliki solusi");
        }
        
        // Inisasi matriks konstanta (bagian kolom paling kanan)
        Matrix mConstant = mIn.getMResult(mIn);
        

        System.out.println("Bentuk Awal Matriks: ");
        mOriginal.printMatrix();
        System.out.println("\n");
        
        System.out.println("Bentuk Matriks Konstanta: ");
        mConstant.printMatrix();
        System.out.println("\n");

        System.out.println("================== PENYELESAIAN SPL METODE INVERSE ==================");
        Matrix mInverse = inverse(mOriginal);
        System.out.println("Bentuk Matriks Inverse: ");
        mInverse.printMatrix();
        System.out.println("\n");

        Matrix mResult;
        mResult = multiplyMatrix(mInverse, mConstant);
        System.out.println("Bentuk Matriks Akhir: ");
        mResult.printMatrix();
        System.out.println("\n");

        for (int i =0; i < mResult.getRowLength(); i++){
            System.out.println("X"+ (i+1) +" = " + mResult.getElmt(i, 0));
        }
    }

    // PROCEDURE
    // Menyelesaikan permasalahan SPL menggunakan metode Cramer
    public void splCramer(Matrix mIn){

        // Inisiasi matriks original
        Matrix mOriginal = mIn.getMOriginal(mIn);

        // Inisasi matriks konstanta (bagian kolom paling kanan)
        Matrix mConstant = mIn.getMResult(mIn);

        System.out.println("Bentuk Awal Matriks: ");
        mOriginal.printMatrix();
        System.out.println("\n");
        
        System.out.println("Bentuk Matriks Konstanta: ");
        mConstant.printMatrix();
        System.out.println("\n");

        System.out.println("================== PENYELESAIAN SPL METODE CRAMER ==================");

        double det = detKof(mOriginal);
        if (det == 0){
            System.out.println("Matriks tidak memiliki solusi.");
        }
        else{
            for (int i = 0; i <= mOriginal.getColIdx(); i++){
                Matrix mTemp = copyMatrix(mIn);
                Matrix mNew = new Matrix(mTemp.getColLength(), mTemp.getRowLength());
                Matrix mTranspose = mTemp.transpose();
                mTranspose.swapRow(i, mTranspose.getRowIdx());
                mNew = delLastRow(mTranspose);
                mNew = mNew.transpose();

                System.out.println("Bentuk Matriks: ");
                mNew.printMatrix();
                System.out.println("===============");
                mOriginal.printMatrix();
                System.out.println("Solusi : ");
                System.out.println("X" + (i+1) + " = " + detKof(mNew) / det);
                System.out.println("\n");
            }
        }
    }

}
