package ADTMatrix;
import java.util.*;
import java.io.*;

public class InputOutput {

    Scanner scanObj = new Scanner(System.in);
    
    // ========================== BACA TULIS ==========================

    // PROCEDURE
    // Membaca file text dengan mengembalikan matrix berdasarkan file text tersebut
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
                System.out.print("Masukkan nama file (.txt) lengkap dengan .txt : ");
                scanObj.nextLine(); // Read the leftover new line
                fileName = scanObj.nextLine();
                // Membaca current working path dan memanipulasi path
                currentPath = System.getProperty("user.dir");
                currentPath = currentPath.replaceAll("src","");
                // Menggabungkan currentPath dengan lokasi file
                filePath = currentPath+"\\test\\input\\"+ fileName;
                File file = new File(filePath);
                
                // Melakukan pencarian apakah file tersebut ada
                if (file.exists()){
                    try {
                        // Meminta user untuk memasukkan panjang baris dan kolom matriks
                        // System.out.print("Masukkan panjang baris dan panjang kolom matriks: ");
                        // row = scanObj.nextInt(); 
                        // col = scanObj.nextInt();
                        // m = new Matrix(row, col);

                        FileReader reader = new FileReader(file);
                        BufferedReader buffReader = new BufferedReader(reader);

                        // Membuat variabel pembaca dan variabel penghitung count (untuk menghitung panjang baris);
                        String line;
                        int rowCount = 0;
                        Matrix mTemp;
                        row = 0;
                        col = 0;

                        // Mencari tahu banyak row dan col dari matrix pada text
                        while ((line = buffReader.readLine()) != null){
                            if (row == 0){
                                col = countCol(line);
                            }
                            row++;
                        }
                        // Buat matrix sebesar row dan col
                        m = new Matrix(row, col);
                        reader.close();

                        // Membuka kembali file text tersebut
                        reader = new FileReader(file);
                        buffReader = new BufferedReader(reader); 

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

    // FUNCTION
    // Mengembalikan banyak kolom suatu matrix dengan membaca suatu line pada matrix text
    public Integer countCol(String line){
        // Menghitung banyaknya kolom dapat menggunakan dengan menghitung banyaknya spasi
        // Setiap elemen berakhir maka terdapat spasi. Maka 1 spasi merepresentasikan 1 elemen.
        int lineLength = line.length();
        int count = 0;
        int i;
        for (i = 0; i < lineLength; i++){
            if (Character.isWhitespace(line.charAt(i))){
                count++;
            }
        }
        // ditambahkan 1 kali lagi karena diakhir line tidak ada spasi (elemen terakhir belum terhitung).
        count++;

        return count;
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
    public void printMatrixToText(String fileName, Matrix m){

        // Membaca current working path dan memanipulasi path
        String currentPath = System.getProperty("user.dir");
        currentPath = currentPath.replaceAll("src","");
        // Menggabungkan currentPath dengan lokasi file
        String filePath = currentPath+"test\\output\\"+ fileName;

        File file = new File(filePath);

        if (file.exists()){
            file.delete();
        }

        try {
            file.createNewFile();
            FileWriter writer = new FileWriter(file);
            BufferedWriter buffWriter = new BufferedWriter(writer);

            for (int i = 0; i < m.getRowLength(); i++){
                for (int j = 0; j < m.getColLength(); j++){
                    if ( j == m.getColLength()-1) {
                        buffWriter.write(String.format("%.2f", m.getElmt(i, j)));
                    } else {
                        buffWriter.write(String.format("%.2f", m.getElmt(i, j)));
                        buffWriter.write(" ");
                    }
                }
                if( i <= m.getRowLength() - 1){
                    buffWriter.newLine();
                }
            }

            buffWriter.close();
            writer.close();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    // PROCEDURE
    // Melakukan print suatu string pada text dengan menambahkan line baru
    public void printStringToText(String fileName, String str){

        // Membaca current working path dan memanipulasi path
        String currentPath = System.getProperty("user.dir");
        currentPath = currentPath.replaceAll("src","");
        // Menggabungkan currentPath dengan lokasi file
        String filePath = currentPath+"test\\output\\"+ fileName;

        File file = new File(filePath);

        try {
            
            if(!file.exists()){
                file.createNewFile();
            }

            FileWriter writer = new FileWriter(file, true);
            BufferedWriter buffWriter = new BufferedWriter(writer);
            PrintWriter pw = new PrintWriter(buffWriter);

            pw.println(str);

            pw.close();
            buffWriter.close();
            writer.close();

        } catch (IOException e){
            e.printStackTrace();
        }
    }

    // FUNCTION 
    // Menanyakan kepada user apakah ingin di print pada terminal atau pada text
    public boolean askUserPrint(){
        boolean program = true;
        boolean text = false;
        int userInput;

        while (program) {
            System.out.println("=================================");
            System.out.println("======  MENU PRINT MATRIX  ======");
            System.out.println("=================================");
            System.out.println("Pilih cara untuk menulis Output:");
            System.out.println("1. Print pada terminal ");
            System.out.println("2. Print pada file .txt ");
            System.out.print("Masukkan angkanya saja (1-2): ");
            userInput = scanObj.nextInt();
            if (userInput == 1){
                text = false;
                program = false;
            } 
            else if (userInput == 2){
                text = true;
                program = false;
            }
            else {
                System.out.println("Input "+ userInput +" tidak valid. Silahkan masukan input yang valid.");
            }

        }
        return text;
    }

    // PROCEDURE 
    // Menghapus file tertentu pada tempat penyimpanan file output
    public void delFile(String fileName){
        // Membaca current working path dan memanipulasi path
        String currentPath = System.getProperty("user.dir");
        currentPath = currentPath.replaceAll("src","");
        // Menggabungkan currentPath dengan lokasi file
        String filePath = currentPath+"test\\output\\"+ fileName;

        File file = new File(filePath);

        if (file.exists()){
            file.delete();
        }
    }

}
