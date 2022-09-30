import ADTMatrix.*;
import BicubicInterpolation.BicubicInterpolation;
import Interpolation.Interpolasi;
import MultipleLinearRegession.MultipleLinearRegression;

import java.util.Scanner;

public class Main {

    static MatrixOps mOps = new MatrixOps();
    static InputOutput io = new InputOutput();
    static BicubicInterpolation bi = new BicubicInterpolation();
    static Interpolasi interpolasi = new Interpolasi();
    static MultipleLinearRegression regresiLinear = new MultipleLinearRegression();
    public static void main(String[] args) {
        // program adalah variabel yang menunjukkan bahwa program masih berjalan 
        // program bernilai true apabila Program masih dijalankan. Di sisi lain, bernilai false bila Program telah selesai dijalankan
        boolean program = true;
        boolean subprogram1 = false;
        boolean subprogram2 = false;
        boolean subprogram3 = false;
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
                                System.out.println("================== PENYELESAIAN SPL METODE GAUSS ==================");
                                Matrix m;
                                m = io.readMatrix();

                                mOps.splGaussJordan(m, false);
                                
                                subprogram1 = false;
                                
                            }

                            
                            // User memilih fitur kedua
                            if ( userSubNumber == 2){
                                // System.out.println("================== PENYELESAIAN SPL METODE GAUSS JORDAN ==================");
                                Matrix m;
                                m = io.readMatrix();

                                mOps.splGaussJordan(m, true);
                                
                                subprogram1 = false;
                                
                            }

                            // User memilih fitur ketiga
                            if ( userSubNumber == 3){
                                System.out.println("================== PENYELESAIAN SPL METODE INVERSE ==================");
                                Matrix m;
                                m = io.readMatrix();

                                mOps.splInverse(m);
                                
                                subprogram1 = false;
                                
                            }

                            // User memilih fitur keempat
                            if ( userSubNumber == 4){
                                System.out.println("================== PENYELESAIAN SPL METODE CRAMER ==================");
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
                // User ingin mencari determinan
                if ( userNumber == 2){
                    // variabel subprogram bernilai true ( dijalankan )
                    subprogram2 = true;
                    while (subprogram2) {
                        System.out.println("=================================");
                        System.out.println("======       MENU  2       ======");
                        System.out.println("=================================");
                        System.out.println("1. Determinan metode Kofaktor");
                        System.out.println("2. Determinan metode Operasi Baris Elementer");
                        System.out.println("3. Kembali ke Main Menu");

                        System.out.print("Masukkan angkanya saja (1-3): ");

                        // Membaca input dari user
                        userSubNumber = scanObj.nextInt();

                        // Jika inputnya tidak valid, maka akan diberikan pesan bahwa input tidak valid
                        if ((userSubNumber < 1) || (userSubNumber > 3)){
                            System.out.println("Input "+ userSubNumber +" tidak valid. Silahkan masukan input yang valid.");
                        } else {

                            // User memilih fitur pertama
                            if ( userSubNumber == 1){
                                Matrix m;
                                double determinan;
                                boolean printOnText;
                                String fileName;
                                String resultString;
                                
                                m = io.readMatrix();

                                determinan = mOps.detKof(m);

                                printOnText = io.askUserPrint();
                                if (printOnText){
                                    System.out.print("Masukkan nama file (.txt) lengkap dengan .txt : ");
                                    scanObj.nextLine(); // Read the leftover new line
                                    fileName = scanObj.nextLine();
                                    io.delFile(fileName);
                                    resultString = "Determinan : "+ Double.toString(determinan);
                                    
                                    io.printStringToText(fileName, "=============== HASIL DETERMINAN ===============");
                                    io.printStringToText(fileName, resultString);
                                } else {
                                    System.out.println("=============== HASIL DETERMINAN ===============");
                                    System.out.println("Determinan : "+determinan);
                                }

                                subprogram2 = false;
                                
                            }

                            
                            // User memilih fitur kedua
                            if ( userSubNumber == 2){
                                Matrix m;
                                double determinan;
                                boolean printOnText;
                                String fileName;
                                String resultString;
                                
                                m = io.readMatrix();

                                determinan = mOps.detObe(m);

                                printOnText = io.askUserPrint();
                                if (printOnText){
                                    System.out.print("Masukkan nama file (.txt) lengkap dengan .txt : ");
                                    scanObj.nextLine(); // Read the leftover new line
                                    fileName = scanObj.nextLine();
                                    io.delFile(fileName);
                                    resultString = "Determinan : "+ Double.toString(determinan);
                                    
                                    io.printStringToText(fileName, "=============== HASIL DETERMINAN ===============");
                                    io.printStringToText(fileName, resultString);
                                } else {
                                    System.out.println("=============== HASIL DETERMINAN ===============");
                                    System.out.println("Determinan : "+determinan);
                                }
                                
                                subprogram2 = false;
                                
                            }

                            // User memilih fitur ketiga
                            if (userSubNumber == 3){
                                subprogram2 = false;
                            }
                        }
                    }
                    
                }

                // User memilih fitur ketiga
                // User ingin mencari inverse matrix
                if ( userNumber == 3){
                    // variabel subprogram bernilai true ( dijalankan )
                    subprogram3 = true;
                    while (subprogram3) {
                        System.out.println("=================================");
                        System.out.println("======       MENU  3       ======");
                        System.out.println("=================================");
                        System.out.println("1. Matrix Inverse metode Kofaktor dan Adjoint");
                        System.out.println("2. Matrix Inverse metode Eliminasi Gauss Jordan");
                        System.out.println("3. Kembali ke Main Menu");

                        System.out.print("Masukkan angkanya saja (1-3): ");

                        // Membaca input dari user
                        userSubNumber = scanObj.nextInt();

                        // Jika inputnya tidak valid, maka akan diberikan pesan bahwa input tidak valid
                        if ((userSubNumber < 1) || (userSubNumber > 3)){
                            System.out.println("Input "+ userSubNumber +" tidak valid. Silahkan masukan input yang valid.");
                        } else {

                            // User memilih fitur pertama
                            if ( userSubNumber == 1){
                                Matrix m;
                                Matrix mInverse;
                                boolean printOnText;
                                String fileName;
                                
                                m = io.readMatrix();

                                mInverse = mOps.inverse(m);

                                printOnText = io.askUserPrint();
                                if (printOnText){
                                    System.out.print("Masukkan nama file (.txt) lengkap dengan .txt : ");
                                    scanObj.nextLine(); // Read the leftover new line
                                    fileName = scanObj.nextLine();
                                    io.delFile(fileName);
                                    
                                    io.printMatrixToText(fileName, mInverse);
                                    io.printStringToText(fileName, "=============== HASIL INVERSE ===============");
                                } else {
                                    System.out.println("=============== HASIL INVERSE ===============");
                                    io.printMatrix(mInverse);
                                }


                                subprogram3 = false;
                                
                            }

                            
                            // User memilih fitur kedua
                            if ( userSubNumber == 2){
                                Matrix m;
                                boolean printOnText;
                                String fileName;
                                
                                m = io.readMatrix();
                                Matrix mInverse = new Matrix(m.getRowLength(), m.getColLength());
                                mInverse.setToIdentity();

                                mOps.upperTriangleMatrix(m, mInverse);
                                mOps.lowerTriangleMatrix(m, mInverse);

                                printOnText = io.askUserPrint();
                                if (printOnText){
                                    System.out.print("Masukkan nama file (.txt) lengkap dengan .txt : ");
                                    scanObj.nextLine(); // Read the leftover new line
                                    fileName = scanObj.nextLine();
                                    io.delFile(fileName);
                                    
                                    io.printMatrixToText(fileName, mInverse);
                                    io.printStringToText(fileName, "=============== HASIL INVERSE ===============");
                                } else {
                                    System.out.println("=============== HASIL INVERSE ===============");
                                    io.printMatrix(mInverse);
                                }
                                
                                subprogram3 = false;
                                
                            }

                            // User memilih fitur ketiga
                            if (userSubNumber == 3){
                                subprogram3 = false;
                            }
                        }
                    }

                }

                // User memilih fitur keempat
                // User ingin menggunakan fungsi interpolasi
                // if ( userNumber == 4){
                    if(userNumber == 4){
                        Matrix mIn; 
                        double x;
                        String text = "";
                        mIn = io.readMatrix();
                        double lastElement = mIn.getElmt(mIn.getRowLength()-1, 1);
                        if(Double.isNaN(lastElement)){
                            System.out.println("Ada nan");
                            Matrix mTemp = mOps.delLastRow(mIn);
                            x = mIn.getElmt(mIn.getRowLength()-1, 0); 
                            text = interpolasi.bacaInterpolasi(mTemp, x); 
                        }else{
                            System.out.println("Masukkan nilai x yang ingin di-interpolasi : ");
                            x = scanObj.nextDouble();
                            text = interpolasi.bacaInterpolasi(mIn,x);
                        }                        
                        boolean printOnText = io.askUserPrint();
                        if(printOnText){
                            System.out.print("Masukkan nama file (.txt) lengkap dengan .txt : ");
                            scanObj.nextLine(); // Read the leftover new line
                            String fileName = scanObj.nextLine();
                            io.delFile(fileName);       
                            io.printStringToText(fileName, "=============== HASIL INVERSE ===============");
                            io.printStringToText(fileName, text);
                        }else{
                            System.out.println("====== Fungsi Interpolasi ================");
                            System.out.println(text);
                        }
                    }
                // }

                    if(userNumber == 5){
                        Matrix input = new Matrix(5, 5);
                        input = io.readMatrix();

                        Matrix koefFungsi = new Matrix(4, 4);
                        koefFungsi = mOps.readBicubicMatrix(input);
                        koefFungsi = mOps.transform4x4To16x1(koefFungsi);
                        koefFungsi = bi.getCoefMatrix(koefFungsi);

                        Matrix nilaiFungsi = mOps.readBicubicMatrix(input);
                        double x = nilaiFungsi.getElmt(0, 0);
                        double y = nilaiFungsi.getElmt(0, 1);
                        
                        double hasilInterpolasi = bi.interpolate(x, y, koefFungsi);

                        //Bagian Print
                        boolean printOnText = io.askUserPrint();
                        if (printOnText){
                            System.out.println("Masukkan nama file (.txt) lengkap dengan .txt: ");
                            scanObj.nextLine();
                            String fileName = scanObj.nextLine();
                            io.delFile(fileName);
                            String resultString = "f(" + Double.toString(x) + "," + Double.toString(y) + ") = " + Double.toString(hasilInterpolasi);
                            
                            io.printStringToText(fileName, "=============== HASIL INTERPOLASI ===============");
                            io.printStringToText(fileName, resultString);
                        } else {
                            System.out.println("=============== HASIL INTERPOLASI ===============");
                            System.out.println("f(" + Double.toString(x) + "," + Double.toString(y) + ") = " + Double.toString(hasilInterpolasi));
                        }
                    }

                // User memilih fitur kelima
                // User ingin menggunakan fungsi interpolasi bicubic
                

                // User memilih fitur keenam
                // User ingin menggunakan fungsi regresi linear berganda
                if ( userNumber == 6){
                    Matrix m;
                    String displayAnswer = "";
                    m = io.readMatrix();
                    if(io.userInput == 2){
                        regresiLinear.userInput = io.userInput;
                        regresiLinear.mX = new Matrix(m.getColLength()-1,1);
                        for(int i =0; i < m.getColLength()-1;i++){
                            regresiLinear.mX.setElmt(i, 0, m.getElmt(m.getRowLength()-1, i));
                        }
                        Matrix mIn = mOps.delLastRow(m);
                        displayAnswer = regresiLinear.regresiLinear(mIn);
                    }else{
                        displayAnswer = regresiLinear.regresiLinear(m);
                    }
                    boolean printOnText = io.askUserPrint();
                    if(printOnText){
                        System.out.print("Masukkan nama file (.txt) lengkap dengan .txt : ");
                        scanObj.nextLine(); // Read the leftover new line
                        String fileName = scanObj.nextLine();
                        io.delFile(fileName);       
                        io.printStringToText(fileName, "=============== HASIL REGRESI ===============");
                        io.printStringToText(fileName, displayAnswer);
                    }else{
                        System.out.println("====== Hasil Regresi ================");
                        System.out.println(displayAnswer);
                    }
                }


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