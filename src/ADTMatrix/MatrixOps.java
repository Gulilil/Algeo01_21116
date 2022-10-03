package ADTMatrix;
import java.util.*;


public class MatrixOps {

    static InputOutput io = new InputOutput();
    Scanner scanObj = new Scanner(System.in);
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
                double temp = Math.pow(-1, i+j) * detObe(kofaktor(mInput,i,j));
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

    // FUNCTION
    // Melakukan pengecekan apakah solusi yang dihasilkan adalah tunggal dan unik
    public boolean checkUniqueSolution(Matrix m){
        int i;

        //cek dulu apakah row yang kosong pada matriks m
        // jika ada row yang bernilai 0 semua, maka solusi pasti bukanlah unik
        for (i = 0; i < m.getRowLength(); i++ ){
            if (m.isZeroRow(i)){
                return false;
            }
        }
        // jika tidak ada row kosong, lalu cek apakah matriks bersifat persegi atau bukan 
        if (!m.isSquare()){
            return false;
        }
        // bila tidak ada row kosong dan matriks berbentuk persegi, maka solusi yang dimiliki adalah tunggal dan unik
        return true;
    }

    // PROCEDURE
    // Mengembalikan matriks segitiga atas dari suatu matriks menggunakan OBE
    public void upperTriangleMatrix (Matrix m, Matrix mConst){
        // Prosedur hanya dijalankan apabila matriks berbentuk persegi
        int row = 0;
        int col = 0;
        int i;
        int j;
        int k;
        double pembuatNol;
        double newVal;
        double pembuatSatu;
        boolean program = true;
        boolean allZeroBelow = false;
        int count;


        // Variabel program menunjukkan bahwa pembuatan segitiga masih dilakukan
        while (program == true){

            // Mengecek jika terdapat baris yang bernilai 0 semua
            // hanya dicek apabila program tidak berada di baris paling bawah
            if (row < m.getRowIdx() && m.isZeroRow(row+1)){
                allZeroBelow = true;
                // jika baris dibawahnya adalah 0 semua, cek terlebih dahulu apakah setiap baris dibawahnya bernilai 0 semua
                for (i = row +1 ; i < m.getRowLength(); i++){
                    if (!m.isZeroRow(i)){
                        // jika ada satu saja yang tidak bernilai 0 semua, maka allZeroBelow bernilai false
                        allZeroBelow = false;
                    }
                }
            }
            if (allZeroBelow){
                // jika benar setiap baris dibawahnya adalah baris yang mengandung 0 semua
                // maka program akan diberhentikan
                program = false;
            }

            // Bila program telah mencapai batas baris terakhir dalam matriks 
            else if (row == m.getRowIdx() || (m.isZeroRow(row+1))){
                if (col == m.getColIdx()){
                    // program berada pada elemen pojok kanan bawah (baris dan kolom terakhir)
                    if (m.getElmt(row, col) != 1 && m.getElmt(row,col) != 0){
                        // Jika hasil akhir bukan 1, maka perlu dijadikan 1
                        pembuatSatu = m.getElmt(row, col);
                        m.setElmt(row, col, 1);
                        for (k = 0; k < mConst.getColLength(); k++){
                            mConst.setElmt(row, k,(mConst.getElmt(row,k)/ pembuatSatu));
                        }
    
                    }
                    program = false;
                } else {
                    // program berada pada baris terakhir tapi bukan kolom terakhir
                    // membagi baris tersebut dengan pembuatSatu sehingga mendapat leading 1
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
                    // karena setiap kolom telah di ubah, maka program telah selesai dijalankan
                    program = false;

                }
            }
            // Bila program mencapai kolom terakhir tapi tidak mencapai baris terakhir
            else if (col == m.getColIdx()){

                // Membaut 0 baris ke row dan kolom ke 0
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
                // karena sisa dari setiap baris telah diubah, maka program telah selesai dijalankan
                program = false;


            } else { 
                // Program masih dilakukan karena row dan col belum mencapai index terakhir
                // Cek dulu apakah element awal bernilai 0
                while (m.getElmt(row, col) == 0 ){
                    // keluar while apabila semua baris dibawahnya bernilai 0, hingga baris dan kolom terakhir
                    count = 0;
                    // cek elemen dibawahnya apakah bernilai 0 juga
                    // di cek elemen dari suatu row hingga row kedua dari bawah
                    while (m.getElmt(row + count, col) == 0 && ((row + count) < m.getRowIdx()) ){
                        count++;
                    }
                    // Satu kolom bernilai 0 semua
                    if ( (row+count) == m.getRowIdx()){
                        if (m.getElmt(row+count, col) != 0){
                            // count mencapai row terakhir dan bernilai bukan 0
                            m.swapRow(row, row + count);
                        } else {
                            // count mencapai row terakhir dan bernilai 0
                            col++;
                        }
                    } else {
                        // ada satu elemen yang tidak bernilai 0 pada kolom tersebut
                        m.swapRow(row, row+count);
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
                
                // Langkah pencegahan agar tidak keluar dari index matriks
                row++;
                col++;

                
            }
            //io.printMatrix(m);
            //io.printMatrix(mConst);
        }
    }

    // PROCEDURE
    // Mengembalikan matriks segitiga bawah dari suatu matriks menggunakan OBE 
    // Prosedur ini adalah LANJUTAN dari lowerTriangleMatrix (JANGAN DIPAKAI TERPISAH!!)
    public void lowerTriangleMatrix(Matrix m, Matrix mConst){
        int i;
        int j;
        int row = 1;
        int col = 1;
        double pembuatNol;
        double newVal;
        boolean program = true;

        while (program) {
            // Bila program telah menlampaui index terakhir (artinya baris paling bawah dan kolom paling kanan pun telah diubah), maka program dihentikan

            if (row == m.getRowLength() || col == m.getColLength() || m.isZeroRow(row)){
                program = false;
            } else {
                // Jika element pada baris ke row dan kolom ke col bukanlah 1 (leading 1)
                while (m.getElmt(row, col) != 1 && col < m.getColIdx()){
                    // kolom dimajukan 1 tingkat
                    // baris dibawah elemen tersebut tidak perlu di cek karena sudah di 0 kan oleh fungsi lowerTriangleMatrix
                    col++;
                }

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

    // PROCDEURE
    // Melakukan print khusus untuk matriks solusi
    public void displaySolution(Matrix mSolution){
        for (int i =0; i<mSolution.getRowLength();i++){
            System.out.println("X"+ (i+1) +" = " + mSolution.getElmt(i, 0));
        }
    }


    // ============================== PENYELESAIAN SPL ============================== 
    // PROCEDURE
    // Menyelesaikan permasalahan SPL menggunakan metode Gauss
    
    public Matrix splGaussJordan (Matrix mIn, boolean jordan){
        int i;
        int j;
        boolean solution = true;
        boolean printOnText;
        String fileName = "";
        String resultString;
        // Jika jordan true, maka yang dihasilkan adalah metode Gauss-Jordan
        // Jika jordan false, maka yang dihasilkan adalah metode Gauss

        // Inisiasi matriks original
        Matrix mOriginal = mIn.getMOriginal(mIn);
        // Inisasi matriks konstanta (bagian kolom paling kanan)
        Matrix mConstant = mIn.getMResult(mIn);
        // Inisiasi matriks result sebesar mConstant
        Matrix mResult = new Matrix (mConstant.getRowLength(), mConstant.getColLength());


        if (jordan){
            // User menginginkan metode Gauss Jordan
            upperTriangleMatrix(mOriginal, mConstant);
            lowerTriangleMatrix(mOriginal, mConstant);

            printOnText = io.askUserPrint();
            // hanya ditanyakan namafile apabila pengguna ingin melakukan output pada file
            if (printOnText){
                System.out.print("Masukkan nama file (.txt) lengkap dengan .txt : ");
                fileName = scanObj.nextLine();
                io.delFile(fileName);
                io.printStringToText(fileName, "================== PENYELESAIAN SPL METODE GAUSS JORDAN ==================" );
            }else{
                System.out.println("================== PENYELESAIAN SPL METODE GAUSS JORDAN ==================");
            }

            if (checkUniqueSolution(mOriginal)){
                // Jika solusi  
                if (printOnText){
                    for (i = 0; i < mConstant.getRowLength(); i++){
                        resultString = "X"+ Integer.toString(i+1)+" = "+ Double.toString(mConstant.getElmt(i,0));
                        io.printStringToText(fileName, resultString);
                    }
                } else {
                    displaySolution(mConstant);
                }
                return mConstant;

            } else {
                // cek apakah matrix tersebut ada solusinya
                for (i=0; i < mOriginal.getRowLength(); i++){
                    if (mOriginal.isZeroRow(i)) {
                        // bila terdapat row yang bernilai 0 semua pada mOriginal
                        // cek terlebih dahulu apakah elemen pada mConstant juga bernilai 0
                        if (mConstant.getElmt(i, 0) == 0){
                            solution = true;
                        } else {
                            // semua kolom pada mOriginal bernilai 0 tapi pada mConstant bernilai tidak 0
                            solution = false;
                        }
                    }
                }
                if (solution == false){
                    // Tidak ada solusi 
                    if (printOnText){
                        io.printStringToText(fileName, "Tidak ada solusi yang dapat dihasilkan!");
                    }else {
                        System.out.println("Tidak ada solusi yang dapat dihasilkan!");
                    }
                    
                    return mResult;
                } else {
                    int rowIdx = 0;
                    // Ada solusi tapi solusi parametrik
                    for (i = 0; i < mOriginal.getColLength(); i++){
                        resultString = "";

                        // melakukan print dalam bentuk solusi parametrik
                        if (rowIdx > mOriginal.getRowIdx() ){
                            resultString = "X"+Integer.toString(i+1)+" = a"+Integer.toString(i+1);
                        }
                        else if(mOriginal.isZeroRow(rowIdx)){
                            resultString = "X"+Integer.toString(i+1)+" = a"+Integer.toString(i+1);
                        }
                        else if (mOriginal.isZeroCol(i)){
                            resultString = "X"+Integer.toString(i+1)+" = a"+Integer.toString(i+1);
                        }
                        else if (!mOriginal.isZeroCol(i)){
                            // hanya dituliskan untuk matriks yang barisnya tidak bernilai 0 semua
                            resultString += "X"+Integer.toString(i+1)+" =";
                            
                            resultString += " "+Double.toString(mConstant.getElmt(rowIdx, 0));

                            for (j = i; j < mOriginal.getColLength(); j++){
                                if ((mOriginal.getElmt(rowIdx, j)) != 0 && (j != i)){
                                    // hanya dituliskan untuk elemen yang tidak bernilai 0
                                    // jika i == j tidak ditulis
                                    if (mOriginal.getElmt(rowIdx, j) < 0){
                                        // elemen pada baris rowIdx, kolom j kurang dari 0
                                        resultString += " + "+Double.toString(-1*mOriginal.getElmt(rowIdx, j))+"a"+Integer.toString(j+1);
                                    } else {
                                        // elemen pada baris rowIdx, kolom j lebih besar dari 0
                                        resultString += " - "+Double.toString(mOriginal.getElmt(rowIdx, j))+"a"+Integer.toString(j+1);
                                    }

                                }
                            }
                            //System.out.print("\n");
                            rowIdx++;
                        }
                        if (printOnText){
                            io.printStringToText(fileName, resultString);
                        } else {
                            System.out.println(resultString);
                        }
                    }
                    return mResult;
                }
            }


        } else {
            // User menginginkan metode Gauss
            upperTriangleMatrix(mOriginal, mConstant);

            printOnText = io.askUserPrint();
            // hanya ditanyakan namafile apabila pengguna ingin melakukan output pada file
            if (printOnText){
                System.out.print("Masukkan nama file (.txt) lengkap dengan .txt : ");
                fileName = scanObj.nextLine();
                io.delFile(fileName);
                io.printStringToText(fileName, "================== PENYELESAIAN SPL METODE GAUSS ==================" );
            }else{
                System.out.println("================== PENYELESAIAN SPL METODE GAUSS ==================");
            }


            if (checkUniqueSolution(mOriginal)){
                // Jika ada solusi
                lowerTriangleMatrix(mOriginal, mConstant);

                if (printOnText){
                    for (i = 0; i < mConstant.getRowLength(); i++){
                        resultString = "X"+ Integer.toString(i+1)+" = "+ Double.toString(mConstant.getElmt(i,0));
                        io.printStringToText(fileName, resultString);
                    }
                } else {
                    displaySolution(mConstant);
                }

                return mConstant;
            } else {
                // cek apakah matrix tersebut ada solusinya
                for (i=0; i < mOriginal.getRowLength(); i++){
                    if (mOriginal.isZeroRow(i)) {
                        // bila terdapat row yang bernilai 0 semua pada mOriginal
                        // cek terlebih dahulu apakah elemen pada mConstant juga bernilai 0
                        if (mConstant.getElmt(i, 0) == 0){
                            solution = true;
                        } else {
                            // semua kolom pada mOriginal bernilai 0 tapi pada mConstant bernilai tidak 0
                            solution = false;
                        }
                    }
                }
                if (solution == false){
                    // Tidak ada solusi 
                    if (printOnText){
                        io.printStringToText(fileName, "Tidak ada solusi yang dapat dihasilkan!");
                    }else {
                        System.out.println("Tidak ada solusi yang dapat dihasilkan!");
                    }
                    
                    return mResult;
                } else {
                    int rowIdx = 0;
                    // Ada solusi tapi solusi parametrik
                    for (i = 0; i < mOriginal.getColLength(); i++){
                        resultString = "";

                        // melakukan print dalam bentuk solusi parametrik
                        if (rowIdx > mOriginal.getRowIdx() ){
                            resultString = "X"+Integer.toString(i+1)+" = a"+Integer.toString(i+1);
                        }
                        else if(mOriginal.isZeroRow(rowIdx)){
                            resultString = "X"+Integer.toString(i+1)+" = a"+Integer.toString(i+1);
                        }
                        else if (mOriginal.isZeroCol(i)){
                            resultString = "X"+Integer.toString(i+1)+" = a"+Integer.toString(i+1);
                        }
                        else if (!mOriginal.isZeroCol(i)){
                            // hanya dituliskan untuk matriks yang barisnya tidak bernilai 0 semua
                            resultString += "X"+Integer.toString(i+1)+" =";
                            
                            resultString += " "+Double.toString(mConstant.getElmt(rowIdx, 0));

                            for (j = i; j < mOriginal.getColLength(); j++){
                                if ((mOriginal.getElmt(rowIdx, j)) != 0 && (j != i)){
                                    // hanya dituliskan untuk elemen yang tidak bernilai 0
                                    // jika i == j tidak ditulis
                                    if (mOriginal.getElmt(rowIdx, j) < 0){
                                        // elemen pada baris rowIdx, kolom j kurang dari 0
                                        resultString += " + "+Double.toString(-1*mOriginal.getElmt(rowIdx, j))+"a"+Integer.toString(j+1);
                                    } else {
                                        // elemen pada baris rowIdx, kolom j lebih besar dari 0
                                        resultString += " - "+Double.toString(mOriginal.getElmt(rowIdx, j))+"a"+Integer.toString(j+1);
                                    }

                                }
                            }
                            //System.out.print("\n");
                            rowIdx++;
                        }
                        if (printOnText){
                            io.printStringToText(fileName, resultString);
                        } else {
                            System.out.println(resultString);
                        }
                    }
                    return mResult;
                }
            }

        }

    }
    

    // PROCEDURE
    // Menyelesaikan permasalahan SPL menggunakan metode Inverse
    public Matrix splInverse(Matrix mIn){
        String fileName;

        // Inisiasi matriks original
        Matrix mOriginal = mIn.getMOriginal(mIn);
        
        // Inisasi matriks konstanta (bagian kolom paling kanan)
        Matrix mConstant = mIn.getMResult(mIn);
        Matrix mInverse = inverse(mOriginal);

        Matrix mResult;

        boolean printOnText = io.askUserPrint();
        if (printOnText){
            if(detKof(mOriginal) == 0){
                System.out.print("Masukkan nama file (.txt) lengkap dengan .txt : ");
                fileName = scanObj.nextLine();
                io.delFile(fileName);
                io.printStringToText(fileName, "================== PENYELESAIAN SPL METODE BALIKAN ==================" );
                io.printStringToText(fileName, "SPL tidak memiliki solusi.");
            } else {
                mResult = multiplyMatrix(mInverse, mConstant);
                System.out.print("Masukkan nama file (.txt) lengkap dengan .txt : ");
                fileName = scanObj.nextLine();
                io.delFile(fileName);
                io.printStringToText(fileName, "================== PENYELESAIAN SPL METODE BALIKAN ==================" );
                io.printMatrixToText(fileName, mResult);
            }
        } else {
            if (detKof(mOriginal) == 0){
                System.out.println("SPL tidak memiliki solusi");
            } else {
                System.out.println( "================== PENYELESAIAN SPL METODE BALIKAN ==================");
                // io.printMatrix(mInverse);
                // io.printMatrix(mConstant);
                mResult = multiplyMatrix(mInverse, mConstant);
                displaySolution(mResult);
            }
        }
        mResult = multiplyMatrix(mInverse, mConstant);
        return mResult;
    }

    // PROCEDURE
    // Menyelesaikan permasalahan SPL menggunakan metode Cramer
    public Matrix splCramer(Matrix mIn){

        // Inisiasi matriks original
        Matrix mOriginal = mIn.getMOriginal(mIn);

        // Inisasi matriks konstanta (bagian kolom paling kanan)
        Matrix mConstant = mIn.getMResult(mIn);

        // Insiasi matriks hasil akhir
        Matrix mResult = new Matrix(mConstant.getRowLength(), 1);

        String fileName;

        double det = detObe(mOriginal);

        boolean checkNotCramerable = ((det == 0) || !(mOriginal.isSquare())); 

        boolean printOnText = io.askUserPrint();
        if (printOnText){
            if (checkNotCramerable){
                System.out.print("Masukkan nama file (.txt) lengkap dengan .txt : ");
                fileName = scanObj.nextLine();
                io.delFile(fileName);
                io.printStringToText(fileName, "================== PENYELESAIAN SPL METODE CRAMER ==================" );
                io.printStringToText(fileName, "SPL tidak memiliki solusi.");
                return mResult;
            } else {
                System.out.print("Masukkan nama file (.txt) lengkap dengan .txt : ");
                fileName = scanObj.nextLine();
                io.delFile(fileName);
                io.printStringToText(fileName, "================== PENYELESAIAN SPL METODE CRAMER ==================" );
                io.printMatrixToText(fileName, mResult);
            }
        } else {
            if (checkNotCramerable){
                System.out.println("================== PENYELESAIAN SPL METODE CRAMER ==================");
                System.out.println("SPL tidak memiliki solusi.");
                return mResult;
            }
            else{
                for (int i = 0; i <= mOriginal.getColIdx(); i++){
                    Matrix mTemp = copyMatrix(mIn);
                    Matrix mNew = new Matrix(mTemp.getColLength(), mTemp.getRowLength());
                    Matrix mTranspose = mTemp.transpose();
                    mTranspose.swapRow(i, mTranspose.getRowIdx());
                    mNew = delLastRow(mTranspose);
                    mNew = mNew.transpose();
    
                    // Memasukkan nilai pembagian determinan mNew dengan det mOriginal pada mResult
                    mResult.setElmt(i, 0, detObe(mNew)/ det);
                }
            }
            displaySolution(mResult);
        }
        return mResult;
    }

    public Matrix gaJoInverse(Matrix m){
        Matrix mI = new Matrix(m.getColIdx()+1, m.getColIdx()+1);
        mI.setToIdentity();
        upperTriangleMatrix(m, mI);
        lowerTriangleMatrix(m, mI);
        return mI;
    }

    public Matrix transform4x4To16x1(Matrix m){
        Matrix mResult = new Matrix(16, 1);
        int i, j, k;
        k = 0;
        for (i = 0; i < m.getRowLength(); i++){
            for (j = 0; j < m.getColLength(); j++){
                mResult.setElmt(k, 0, m.getElmt(i, j));
                k++;
            }
        }
        return mResult;
    }

    public Matrix readBicubicMatrix(Matrix m){
        Matrix mOut = new Matrix(4, 4);
        for (int i = 0; i < 4; i++){
            for (int j = 0; j < 4; j++){
                mOut.setElmt(i, j, m.getElmt(i, j));
            }
        }
        return mOut;
    }

    public Matrix readBicubicFunctionValue(Matrix m){
        Matrix mOut = new Matrix(1, 2);
        mOut.setElmt(0, 0, m.getElmt(4, 0));
        mOut.setElmt(0, 1, m.getElmt(4, 1));
        return mOut;
    }

}
