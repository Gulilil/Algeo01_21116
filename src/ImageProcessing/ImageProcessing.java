package ImageProcessing;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class ImageProcessing {

	BufferedImage imgInput = new BufferedImage(width*2, height*2,);

	public ImageProcessing(){
	}

	public void bacaImage(){
		BufferedImage image = null;
        try {
			File input_file = new File(
				"C:/Users/nicho/Desktop/Tubes/testing.png");
			image = ImageIO.read(input_file);
			int width = image.getWidth();
			int height = image.getHeight();
			image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
			System.out.println("Reading complete.");
		}
		catch (IOException e) {
			System.out.println("Error: " + e);
		}
	}


	public void tulisImage(){
		// try {
            // Output file path
            File output_file = new File(
                "C:/Users/nicho/Desktop/Tubes/testing2.png");
  
            // Writing to file taking type and path as
            // ImageIO.write(image, "png", output_file);
  
            System.out.println("Writing complete.");
        // }
        // catch (IOException e) {
        //     System.out.println("Error: " + e);
        // }
	}

}