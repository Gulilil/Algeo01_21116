package ADTMatrix;

public class MatrixOps {
    
    
    // Menyalin matriks pada suatu matriks lain
    public Matrix copyMatrix (Matrix m){
        Matrix mOut = new Matrix(m.getRowIdx(), m.getColIdx());
        for (int i = 0; i <= m.getRowIdx(); i++){
            for (int j = 0; j <= m.getColIdx(); j++){
                mOut.setElmt(i, j, m.getElmt(i, j));
            }
        }
        return mOut;
    }

    // Mencari determinan dari sebuah matriks
    public double det(Matrix m){
        double det = m.MARK;
        if (!m.isSquare()){
            return m.MARK;
        }
        if (m.getRowIdx() == 1)
        {
            return m.getElmt(0, 0);
        }
        else
        {
            for (int i = 0; i <= m.getRowIdx(); i++)
            {
                Matrix mTemp = new Matrix(m.getRowIdx() - 1, m.getColIdx() - 1);
                for (int j = 1; j <= m.getRowIdx(); j++)
                {
                    for (int k = 0; k <= m.getColIdx(); k++)
                    {
                        if (k < i)
                        {
                            mTemp.setElmt(j-1, k, m.getElmt(j, k));
                        }
                        else if (k > i)
                        {
                            mTemp.setElmt(j-1, k-1, m.getElmt(j, k));
                        }
                    }
                }
                det += Math.pow(-1, i) * m.getElmt(0, i) * det(mTemp);
            }
            return det;
        }
    }
}
