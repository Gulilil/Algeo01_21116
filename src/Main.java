import ADTMatrix.*;
import java.util.Scanner;

public class Main {

    static MatrixOps mOps = new MatrixOps();
    static InputOutput io = new InputOutput();
    public static void main(String[] args) {
        // program adalah variabel yang menunjukkan bahwa program masih berjalan 
        // program bernilai true apabila Program masih dijalankan. Di sisi lain, bernilai false bila Program telah selesai dijalankan
        boolean program = true;
        boolean subprogram1 = false;
        Scanner scanObj = new Scanner(System.in);
        
        int userNumber;         // untuk input pada main menu
        int userSubNumber;      // untuk input pada sub main menu
        while (program) {
            System.out.println("=================================");
            System.out.println("=====       MAIN MENU       =====");
            System.out.println("=================================");
            System.out.println("1. Sistem Persamaan Linier");
            System.out.println("2. Determinan");
            System.out.println("3. Matriks Balikan");
            System.out.println("4. Interpolasi Polinom");
            System.out.println("5. Interpolasi Bicubic");
            System.out.println("6. Regresi Linier Berganda");
            System.out.println("7. Keluar");
            System.out.print("Masukkan angkanya saja (1-7): ");
            
            // Membaca input dari user
            userNumber = scanObj.nextInt();

            // Meminta ulang input dari user bila input yang diberikan tidak valid. Diberikan pesan apabila input tidak valid
            if ((userNumber < 1) || (userNumber > 7)){
                System.out.println("Input "+ userNumber +" tidak valid. Silahkan masukan input yang valid.");
            }
            // Bila input yang diberikan bersifat valid
            else {

                // User memilih fitur pertama
                if ( userNumber == 1){
                    // variabel subprogram bernilai true ( dijalankan )
                    subprogram1 = true;
                    while (subprogram1) {
                        System.out.println("=================================");
                        System.out.println("======       MENU  1       ======");
                        System.out.println("=================================");
                        System.out.println("1. Metode Eliminasi Gauss");
                        System.out.println("2. Metode Eliminasi Gauss-Jordan");
                        System.out.println("3. Metode Matriks Balikan");
                        System.out.println("4. Kaidah Cramer");
                        System.out.println("5. Kembali ke Main Menu");

                        System.out.print("Masukkan angkanya saja (1-5): ");

                        // Membaca input dari user
                        userSubNumber = scanObj.nextInt();

                        // Jika inputnya tidak valid, maka akan diberikan pesan bahwa input tidak valid
                        if ((userSubNumber < 1) || (userSubNumber > 5)){
                            System.out.println("Input "+ userSubNumber +" tidak valid. Silahkan masukan input yang valid.");
                        } else {

                            // User memilih fitur pertama
                            if ( userSubNumber == 1){
                                Matrix m;
                                m = io.readMatrix();

                                mOps.splGaussJordan(m, false);
                                
                                subprogram1 = false;
                                
                            }

                            
                            // User memilih fitur kedua
                            if ( userSubNumber == 2){
                                Matrix m;
                                m = io.readMatrix();

                                mOps.splGaussJordan(m, true);
                                
                                subprogram1 = false;
                                
                            }

                            // User memilih fitur ketiga
                            if ( userSubNumber == 3){
                                Matrix m;
                                m = io.readMatrix();

                                mOps.splInverse(m);
                                
                                subprogram1 = false;
                                
                            }

                            // User memilih fitur keempat
                            if ( userSubNumber == 4){
                                Matrix m;
                                m = io.readMatrix();

                                mOps.splCramer(m);
                                
                                subprogram1 = false;
                                
                            }

                            // User memilih fitur kelima
                            if (userSubNumber == 5) {
                                subprogram1 = false;
                            }
                        }
                    }
                }

                // User memilih fitur kedua
                // if ( userNumber == 2){

                // }

                // User memilih fitur ketiga
                // if ( userNumber == 3){

                // }

                // User memilih fitur keempat
                // if ( userNumber == 4){

                // }

                // User memilih fitur kelima
                // if ( userNumber == 5){

                // }

                // User memilih fitur keenam
                // if ( userNumber == 6){

                // }


                // User memilih fitur ketujuh
                if ( userNumber == 7){
                    program = false;
                }
            }
        }
        // Menutup scanner 
        scanObj.close();
    }
    
}