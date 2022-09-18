package ADTMatrix;

public class MatrixOps {

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


    public void cramer(Matrix mIn){
        double det = detKof(mIn);
        if (det == 0){
            System.out.println("Matriks tidak memiliki solusi.");
        }
        else{
            for (int i = 0; i <= mIn.getColIdx(); i++){
                Matrix mTemp = copyMatrix(mIn);
                mTemp.transpose();
                mTemp.swapRow(i, mIn.getRowIdx());
                delLastRow(mTemp);
                mTemp.transpose();
                System.out.println("X" + (i+1) + " = " + detKof(mTemp) / det);
            }
        }
    }

}
