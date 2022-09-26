package ADTMatrix;
import java.util.*;
import java.io.*;

public class InputOutput {

    Scanner scanObj = new Scanner(System.in);
    
    // ========================== BACA TULIS ==========================

    // PROCEDURE
    public Matrix readMatrix() {
        boolean program = true;
        int userInput;
        String fileName;
        String filePath;
        String currentPath;
        int row; int col;
        int i; int j;
        Matrix m = new Matrix(1, 1);
        while (program){
            System.out.println("=================================");
            System.out.println("======  MENU  READ MATRIX  ======");
            System.out.println("=================================");
            System.out.println("Pilih cara untuk membaca Input:");
            System.out.println("1. Baca dari terminal ");
            System.out.println("2. Baca dari file .txt  (Pastikan file telah berada pada folder yang benar.)");
            System.out.print("Masukkan angkanya saja (1-2): ");
            userInput = scanObj.nextInt();
            // Jika user menginginkan read dari terminal
            if (userInput == 1){
                System.out.print("Masukkan panjang baris dan panjang kolom matriks: ");
                row = scanObj.nextInt(); 
                col = scanObj.nextInt();
                m = new Matrix(row, col);

                // Baca matriks
                for (i = 0; i < row; i++){
                    for (j = 0; j < col ; j++){
                        userInput = scanObj.nextInt();
                        m.setElmt(i, j, userInput);
                    }
                }

                program = false;
            }
            // Jika user menginginkan read dari txt file
            else if(userInput == 2){

                // Meminta masukan nama file
                System.out.print("Masukkan nama file (.txt): ");
                scanObj.nextLine(); // Read the leftover new line
                fileName = scanObj.nextLine();
                // Membaca current working path
                currentPath = System.getProperty("user.dir");
                // Menggabungkan currentPath dengan lokasi file
                filePath = currentPath+"\\testCase\\input\\"+ fileName;
                File file = new File(filePath);
                
                // Melakukan pencarian apakah file tersebut ada
                if (file.exists()){
                    try {
                        // Meminta user untuk memasukkan panjang baris dan kolom matriks
                        System.out.print("Masukkan panjang baris dan panjang kolom matriks: ");
                        row = scanObj.nextInt(); 
                        col = scanObj.nextInt();
                        m = new Matrix(row, col);

                        FileReader reader = new FileReader(file);
                        BufferedReader buffReader = new BufferedReader(reader);

                        // Membuat variabel pembaca dan variabel penghitung count (untuk menghitung panjang baris);
                        String line;
                        int rowCount = 0;
                        Matrix mTemp;

                        // Membaca isi pada text
                        while(( line = buffReader.readLine()) != null){
                            mTemp = lineToRow(line, col);
                            // Memasukkan nilai hasil dari mTemp ke dalam m
                            for (j = 0; j < col; j++){
                                m.setElmt(rowCount, j, mTemp.getElmt(0, j));
                            }
                            rowCount++;
                        }

                        reader.close();
                        program = false;

                    } catch (IOException e){
                        e.printStackTrace();
                    }

                } else {
                    // File tidak ditemukan
                    System.out.println("File '"+ fileName+ "' tidak ada!");
                }

            }
            else {
                System.out.println("Input "+ userInput +" tidak valid. Silahkan masukan input yang valid.");
            }

        }
        return m;
        
    }

    // FUNCTION
    // Membaca line dari teks lalu mengubahnya menjadi matriks
    public Matrix lineToRow(String line, int col){
        // Matrix yang dibuat disini sebenarnya adalah array
        // digunakan matrix 1 x nKolom 
        Matrix m = new Matrix(1, col);
        int lineLength = line.length();
        int count = 0;
        int i;
        String val = "";
        double dVal;
        for (i = 0; i < lineLength; i++){
            if (Character.isWhitespace(line.charAt((i)))){
                // Jika ditemukan spasi, maka nilai stop dibaca
                // Lalu dimasukkan ke dalam matriks
                // Nilai val di reset lagi
                dVal = Double.parseDouble(val);
                m.setElmt(0, count, dVal);
                count++;
                val = "";
            } else {
                val = val + line.charAt(i);
            }
        }
        // bagian terakhir belum dimasukkan
        dVal = Double.parseDouble(val);
        m.setElmt(0, count, dVal);

        return m;
        
    }


    // PROCEDURE
    // Melakukan print matriks
    public void printMatrix(Matrix m){
        for (int i = 0; i < m.getRowLength(); i++){
            System.out.print("[");
            for (int j = 0; j < m.getColLength(); j++){
                if ( j == m.getColLength()-1) {
                    System.out.printf("%.2f", m.getElmt(i, j));
                } else {
                    System.out.printf("%.2f", m.getElmt(i, j));
                    System.out.print(",");
                }
            }
            System.out.println("]");
        }
    }

    // PROCEDURE
    // Melakukan print matriks pada text
    public void printMatrixText(Matrix m){

    }



}
