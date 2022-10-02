package ImageResizing;
import ADTMatrix.*;
import BicubicInterpolation.BicubicInterpolation;

import java.io.*; 
import java.util.*;
import java.awt.image.BufferedImage; 
import javax.imageio.ImageIO; 

public class ImageResizing {

    Scanner scanObj = new Scanner(System.in);
    static InputOutput io = new InputOutput();
    static MatrixOps mOps = new MatrixOps();
    static BicubicInterpolation bi = new BicubicInterpolation();

    // Program Utama dari Image Resizing
    public void ImageProcess()
        //throws IOException 
    { 

        String fileName;
        String outputName;
        String imagePath;
        int i, j;
        int k, l;
        Matrix mColorTemp;
        Matrix mColor;

        // For storing image in RAM 
        BufferedImage image = null; 
        BufferedImage padImage = null; // padImage adalah image yang sudah diberikan padding
        BufferedImage newImage = null;

        
        // READ IMAGE 
        // Insert Image Name
        System.out.println("Masukkan Nama File Gambar (berserta formatnya): ");
        fileName = scanObj.nextLine();

        System.out.println("Reading Image...");
        imagePath = getImagePathInput(fileName);
        File imageFile = new File( imagePath); 

        // image file path create an object of 
        // BufferedImage type and pass as parameter the 
        // width,  height and image int 
        // type. TYPE_INT_ARGB means that we are 
        // representing the Alpha , Red, Green and Blue 
        // component of the image pixel using 8 bit 
        // integer value. 

        try{
            // Reading input file 
            image = ImageIO.read(imageFile); 

            // Width and Height of the image 
            int width = image.getWidth(); 
            int height = image.getHeight();

        System.out.println("Reading Image - DONE");


        // GrayScaling Image
        System.out.println("Converting To Grayscale...");
        for (i = 0; i < width; i++){
            for (j = 0; j < height; j++){
                mColorTemp = new Matrix (1, 4);
                int pixel = image.getRGB(i, j);
                int a = getAlpha(pixel);
                int r = getRed(pixel);
                int g = getGreen(pixel);
                int b = getBlue(pixel);

                mColorTemp.setElmt(0, 0, a);
                mColorTemp.setElmt(0, 1, r);
                mColorTemp.setElmt(0, 2, g);
                mColorTemp.setElmt(0, 3, b);

                mColor = grayscale(mColorTemp);
                pixel = ((int)(mColor.getElmt(0,0)) << 24) | ((int) (mColor.getElmt(0, 1))<< 16) | ((int)(mColor.getElmt(0,2))<<8) | (int) (mColor.getElmt(0, 3));
                //System.out.println(pixel);
                image.setRGB(i, j, pixel);
            }
        }

        System.out.println("Converting To Grayscale - DONE."); 
        
        // IMAGE WITH PADDING
        System.out.println("Making Image with Padding...");
        padImage = new BufferedImage (width+4, height+4, BufferedImage.TYPE_INT_RGB);
        for (i = 0; i < width; i++){
            for (j = 0; j <  height; j++){
                padImage.setRGB(i+2, j+2, image.getRGB(i,j));
            }
        }
        System.out.println("Making Image with Padding - DONE");



        // RESIZING IMAGE
        System.out.println("Resizing Image...");
        newImage = new BufferedImage (2*width, 2*height, BufferedImage.TYPE_INT_RGB);

        // Membuat frame gambarnya 
        for(i = 0; i < width; i++){
            for (j = 0; j < height; j++){
                newImage.setRGB((2*i), (2*j), image.getRGB(i,j));     

                // membuat matriks 4x4
                Matrix m4x4 = new Matrix (4,4);
                int mRow = 0;
                // start loop mulai dari index -1 sampai index +3 (untuk membuat 4x4)
                for (k = i-1; k < i+3 ; k++){
                    int mCol = 0;
                    for (l = j-1; l < j+3; l++){
                        // System.out.println(k + " "+ l);
                        // ambil salah satu dari value RGB pada padImage
                        int val = getRed(padImage.getRGB(k+2, l+2));
                        m4x4.setElmt(mRow, mCol, val);
                        mCol++;
                    }
                    mRow++;
                }

                // Mengubah matriks 4x4 menjadi 16x1
                Matrix m16x1 = mOps.transform4x4To16x1(m4x4);

                int pixel = image.getRGB(i, j);
                int alpha = getAlpha(pixel);

                int pxl = getRed(image.getRGB(i, j));
                double right, bottom, rightBottom;

                if (pxl <= 51){
                    right = bi.interpolate(0.2, 0, m16x1);
                    bottom = bi.interpolate(0, 0.2, m16x1);
                    rightBottom = bi.interpolate(0.2, 0.2, m16x1);
                } else if (pxl <= 102){
                    right = bi.interpolate(0.4, 0, m16x1);
                    bottom = bi.interpolate(0, 0.4, m16x1);
                    rightBottom = bi.interpolate(0.4, 0.4, m16x1);
                } else if (pxl <= 153) {
                    right = bi.interpolate(0.6, 0, m16x1);
                    bottom = bi.interpolate(0, 0.6, m16x1);
                    rightBottom = bi.interpolate(0.6, 0.6, m16x1);
                } else if (pxl <= 204) {
                    right = bi.interpolate(0.8, 0, m16x1);
                    bottom = bi.interpolate(0, 0.8, m16x1);
                    rightBottom = bi.interpolate(0.8, 0.8, m16x1);
                } else {
                    right = bi.interpolate(1, 0, m16x1);
                    bottom = bi.interpolate(0, 1, m16x1);
                    rightBottom = bi.interpolate(1, 1, m16x1);
                }


                //Mengisi pixel yang kosong
                // set Right
                int rightVal = ((int) alpha << 24) | ((int) right << 16) | ((int) right << 8) | ((int) right);
                newImage.setRGB(2*i+1, 2*j, rightVal);
                int bottomVal = ((int) alpha << 24) | ((int) bottom << 16) | ((int) bottom << 8) | ((int) bottom);
                newImage.setRGB(2*i, 2*j+1, bottomVal);
                int rightBottomVal = ((int) alpha << 24) | ((int) rightBottom << 16) | ((int) rightBottom << 8) | ((int) rightBottom);
                newImage.setRGB(2*i+1, 2*j+1, rightBottomVal);
            }
        }


        System.out.println("Resizing Image - DONE");

        } 
        catch (IOException e) { 
            System.out.println("Error: " + e); 
        } 

        // WRITE IMAGE 
        // Output file path 
        System.out.println("Masukkan Nama File Output (berserta formatnya): ");
        outputName = scanObj.nextLine();
        System.out.println("Writing Image...");
        writeImage(outputName, newImage);
        System.out.println("Writing Image - DONE");
        
    }
    
    //  =================================================== BATAS MAIN PROGRAM =========================================================

    // FUNCTION
    // Mengembalikan nilai alpha pada warna pixel
    public int getAlpha (int pixel){
        return (pixel >> 24) & 0xff;
    }

    // FUNCTION
    // Mengembalikan nilai red pada warna pixel
    public int getRed (int pixel){
        return (pixel >> 16) & 0xff;
    }

    // FUNCTION
    // Mengembalikan nilai green pada warna pixel
    public int getGreen (int pixel){
        return (pixel >> 8) & 0xff;
    }

    // FUNCTION
    // Mengembalikan nilai blue pada warna pixel
    public int getBlue (int pixel){
        return pixel & 0xff;
    }

    // FUNCTION 
    // Mengembalikan path input dari sebuah image
    public String getImagePathInput (String fileName){
        String filePath;
        String currentPath;

        // Membaca current working path dan memanipulasi path
        currentPath = System.getProperty("user.dir");
        if (currentPath.contains("src")){
            // jika currentPath user.dir berhenti hingga directory src
            currentPath = currentPath.replaceAll("src","");
            // Menggabungkan currentPath dengan lokasi file
            filePath = currentPath+"test\\input\\"+ fileName;
        } else {
            // jika currentPath user.dir tidak sampai directory src
            // Menggabungkan currentPath dengan lokasi file
            filePath = currentPath+"\\test\\input\\"+ fileName;
        }

        return filePath;
    }

    // FUNCTION 
    // Mengembalikan path output dari sebuah image
    public String getImagePathOutput (String fileName){
        String filePath;
        String currentPath;

        // Membaca current working path dan memanipulasi path
        currentPath = System.getProperty("user.dir");
        if (currentPath.contains("src")){
            // jika currentPath user.dir berhenti hingga directory src
            currentPath = currentPath.replaceAll("src","");
            // Menggabungkan currentPath dengan lokasi file
            filePath = currentPath+"test\\output\\"+ fileName;
        } else {
            // jika currentPath user.dir tidak sampai directory src
            // Menggabungkan currentPath dengan lokasi file
            filePath = currentPath+"\\test\\output\\"+ fileName;
        }

        return filePath;
    }

    // FUNCTION
    // Mengembalikan 
    public Matrix grayscale (Matrix m){
        Matrix mNew = new Matrix (1, 4);
        mNew.setElmt(0,0, m.getElmt(0,0));
        double avg = 0;
        double sum = 0;
        int i;
        for (i = 1; i < 4; i++){
            sum = sum + m.getElmt(0, i);
        }
        // Mencari nilai tengah untuk membuat grayscale
        avg = sum / 3;

        for (i = 1; i < 4; i++){
            mNew.setElmt(0,i, avg);
        }

        return mNew;
    }

    // PROCEDURE
    // Melakukan write image
    public void writeImage (String outputName, BufferedImage image){
        try { 
            String outputPath = getImagePathOutput(outputName);
            File outputFile = new File( outputPath); 

            // Writing to file taking type and path as 
            ImageIO.write(image, "jpg", outputFile); 
    

            System.out.println("Writing complete."); 
        } catch (IOException e) { 
            System.out.println("Error: " + e); 
        } 
    }


    
}
