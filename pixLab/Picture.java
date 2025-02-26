import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * A class that represents a picture.  This class inherits from 
 * SimplePicture and allows the student to add functionality to
 * the Picture class.  
 * 
 * @author Barbara Ericson ericson@cc.gatech.edu
 */
public class Picture extends SimplePicture 
{
  ///////////////////// constructors //////////////////////////////////
  
  /**
   * Constructor that takes no arguments 
   */
  public Picture ()
  {
    /* not needed but use it to show students the implicit call to super()
     * child constructors always call a parent constructor 
     */
    super();  
  }
  
  /**
   * Constructor that takes a file name and creates the picture 
   * @param fileName the name of the file to create the picture from
   */
  public Picture(String fileName)
  {
    // let the parent class handle this fileName
    super(fileName);
  }
  
  /**
   * Constructor that takes the width and height
   * @param height the height of the desired picture
   * @param width the width of the desired picture
   */
  public Picture(int height, int width)
  {
    // let the parent class handle this width and height
    super(width,height);
  }
  
  /**
   * Constructor that takes a picture and creates a 
   * copy of that picture
   * @param copyPicture the picture to copy
   */
  public Picture(Picture copyPicture)
  {
    // let the parent class do the copy
    super(copyPicture);
  }
  
  /**
   * Constructor that takes a buffered image
   * @param image the buffered image to use
   */
  public Picture(BufferedImage image)
  {
    super(image);
  }
  
  ////////////////////// methods ///////////////////////////////////////

  /**
   * Method to return a string with information about this picture.
   * @return a string with information about the picture such as fileName,
   * height and width.
   */
  public String toString()
  {
    String output = "Picture, filename " + getFileName() + 
      " height " + getHeight() 
      + " width " + getWidth();
    return output;
    
  }
  
  // START ASSIGNMENT ONES

  /**
   * Rotate image in radians, clean up "drop-out" pixels
   * @param angle angle of rotation in radians
   * @return Picture that is rotated
   */
  public Picture rotate(double angle) {
    Pixel[][] pixels = this.getPixels2D();

    // calculate the new width and height
    int newWidth = (int)Math.round(Math.abs(pixels[0].length * Math.cos(angle))+Math.abs(pixels.length * Math.sin(angle)));
    int newHeight = (int)Math.round(Math.abs(pixels[0].length * Math.sin(angle))+Math.abs(pixels.length * Math.cos(angle)));

    Picture result = new Picture(newHeight, newWidth);
    Pixel[][] resultPixels = result.getPixels2D();

    // centers of images
    int cx = pixels[0].length/2;
    int cy = pixels.length/2;
    int newCx = newWidth/2;
    int newCy = newHeight/2;
    
    for (int y0 = 0; y0<pixels.length; y0++) {
      for (int x0 = 0; x0<pixels[0].length; x0++) {

        // calculate the new x and y position of the original pixel
        int x1 = (int) Math.round((x0-cx) * Math.cos(angle)-(y0-cy) * Math.sin(angle))+newCx;
        int y1 = (int) Math.round((x0-cx) * Math.sin(angle)+(y0-cy) * Math.cos(angle))+newCy;

        // if in bounds, set it
        if (x1>=0 && x1<newWidth && y1>=0 && y1<newHeight) {
            resultPixels[y1][x1].setColor(pixels[y0][x0].getColor());
        }
      }
    }

    // fill in the empty pixels based on average of surrounding
    for (int i = 1; i<newHeight-1; i++) {
      for (int j = 1; j<newWidth-1; j++) {
        if (resultPixels[i][j].getColor().equals(Color.WHITE)) { 

            // average the red, green, blue values
            int new_r = (resultPixels[i+1][j].getRed() + resultPixels[i-1][j].getRed() +
            resultPixels[i][j+1].getRed() + resultPixels[i][j-1].getRed()) / 4;
            int new_g = (resultPixels[i+1][j].getGreen() + resultPixels[i-1][j].getGreen() +
            resultPixels[i][j+1].getGreen() + resultPixels[i][j-1].getGreen()) / 4;
            int new_b = (resultPixels[i+1][j].getBlue() + resultPixels[i-1][j].getBlue() +
            resultPixels[i][j+1].getBlue() + resultPixels[i][j-1].getBlue()) / 4;

            resultPixels[i][j].setColor(new Color(new_r, new_g, new_b));
        }
      }
    }

    return result;
  }



  public Picture greenScreen() {
    // Get background picture
    Picture bkgnd = new Picture("greenScreenImages/IndoorHouseLibraryBackground.jpg");
    Pixel[][] bkgndPixels = bkgnd.getPixels2D();
    // Get cat picture
    Picture cat = new Picture("greenScreenImages/kitten1GreenScreen.jpg");
    cat = cat.scale(0.75, 0.75);
    Pixel[][] catPixels = cat.getPixels2D(); 
    // Get mouse picture
    Picture mouse = new Picture("greenScreenImages/mouse1GreenScreen.jpg");
    mouse = mouse.scale(0.75, 0.75);
    Pixel[][] mousePixels = mouse.getPixels2D();

    // for each cat pixel, if it is not green, set the background pixel to it
    for (int i = 0; i<catPixels.length; i++) {
      for (int j = 0; j<catPixels[0].length; j++) {
        if ((catPixels[i][j].getGreen() < 195) ||  (catPixels[i][j].getBlue() > 200)) {
          bkgndPixels[i + 400][j + 300].setColor(catPixels[i][j].getColor());
        }
      }
    }

    // for each mouse pixel, if it is not green, set the background pixel to it.
    for (int i = 0; i<mousePixels.length; i++) {
      for (int j = 0; j<mousePixels[0].length; j++) {
        if ((mousePixels[i][j].getRed() > 100)) {
          bkgndPixels[i + 300][j + 500].setColor(mousePixels[i][j].getColor());
        }
      }
    }
    
    return bkgnd;
  }

  public Picture swapLeftRight() {
    Pixel[][] pixels = this.getPixels2D();
    Picture result = new Picture(this);
    Pixel[][] resultPixels = result.getPixels2D();
    int width = this.getWidth();
    int height = this.getHeight();
    int halfWidth = width / 2;

    for (int row = 0; row < height; row++) {
        for (int col = 0; col < width; col++) {
            int newCol = (col + halfWidth) % width; 
            resultPixels[row][newCol].setColor(pixels[row][col].getColor()); 
        }
    }

    return result;
  }

  public Picture stairStep(int shiftCount, int steps) {
    Pixel[][] pixels = this.getPixels2D();
    Picture result = new Picture(this);
    Pixel[][] resultPixels = result.getPixels2D();
    int width = this.getWidth();
    int height = this.getHeight();
    int stepHeight = height / steps;

    for (int row = 0; row < height; row++) {
        int stepNumber = row / stepHeight;
        int shift = (stepNumber * shiftCount) % width;

        for (int col = 0; col < width; col++) {
            int newCol = (col + shift) % width; 
            resultPixels[row][newCol].setColor(pixels[row][col].getColor()); 
        }
    }

    return result;
  }

  public Picture wavy(int amplitude) {
    Pixel[][] pixels = this.getPixels2D();
    Picture result = new Picture(this);
    Pixel[][] resultPixels = result.getPixels2D();
    int width = this.getWidth();
    int height = this.getHeight();

    double frequency = 0.01;
    double phase = 0; 

    for (int row = 0; row < height; row++) {
        int shift = (int) (amplitude * Math.sin(2*Math.PI*frequency * row + phase));
        
        for (int col = 0; col < width; col++) {
            int newCol = col + shift;

            newCol = ((newCol % width) + width) % width;
            
            resultPixels[row][newCol].setColor(pixels[row][col].getColor());
        }
    }

    return result;
  }

  public Picture liquify(int maxHeight) {
    Pixel[][] pixels = this.getPixels2D();
    Picture result = new Picture(this);
    Pixel[][] resultPixels = result.getPixels2D();

    for (int row = 0; row<this.getHeight(); row++) {
        double exponent = Math.pow(row - this.getHeight() / 2, 2) / (2.0 * Math.pow(this.getHeight() / 4.0, 2));
        int rightShift = (int) (maxHeight * Math.exp(-exponent));

        for (int col = 0; col<this.getWidth(); col++) {
            int newCol = (col + rightShift) % this.getWidth(); 

            resultPixels[row][newCol].setColor(pixels[row][col].getColor()); 
        }
    }

    return result;
  }

  /** Method to show large changes in color 
  * @param edgeDist the distance for finding edges
  */
  public Picture edgeDetectionBelow(int threshold)
  {
    Pixel[][] pixels = this.getPixels2D();
    Picture result = new Picture(pixels.length, pixels[0].length);
    Pixel[][] resultPixels = result.getPixels2D(); 

    for (int i = 0; i<pixels.length - 1; i++) {
      for (int j = 0 ;j<pixels[0].length; j++) {
        Pixel down = pixels[i + 1][j];

        // if the pixel is far enough from the pixel below, set it to white
        if (pixels[i][j].colorDistance(down.getColor()) < threshold) {
          resultPixels[i][j].setColor(Color.WHITE);
        }
        else {
          resultPixels[i][j].setColor(Color.BLACK);
        }
      }
    }
    return result;
  }

  /** Method to set the blue to 0 */
  public void zeroBlue()
  {
    Pixel[][] pixels = this.getPixels2D();
    for (Pixel[] rowArray : pixels)
    {
      for (Pixel pixelObj : rowArray)
      {
        pixelObj.setBlue(0);
      }
    }
  }

  /** To pixelate by dividing area into size x size.
   * @param size Side length of square area to pixelate.
   */
  public void pixelate(int size) {
    Pixel[][] pixels = this.getPixels2D();
    
    for (int i = 0; i<pixels.length / size; i++) {
      for (int j = 0; j<pixels[0].length / size; j++) {
        int r = 0, g = 0, b = 0;
        int num = 0;

        for (int x = i*size; x<Math.min((i + 1) * size, pixels.length); x++) {
          for (int y = j*size; y<Math.min((j + 1) * size, pixels[0].length); y++) {
            r += pixels[x][y].getRed();
            g += pixels[x][y].getGreen();
            b += pixels[x][y].getBlue();
            num++;
          }
        }

        r /= num;
        g /= num;
        b /= num;

        for (int x = i*size; x<Math.min((i + 1) * size, pixels.length); x++) {
          for (int y = j*size; y<Math.min((j + 1) * size, pixels[0].length); y++) {
            pixels[x][y].setRed(r);
            pixels[x][y].setGreen(g);
            pixels[x][y].setBlue(b);
          }
        }
      }
    }
  }

  /** Method that blurs the picture
   * @param size Blur size, greater is more blur
   * @return Blurred picture
   */
  public Picture blur(int size) {
    Pixel[][] pixels = this.getPixels2D();
    Picture result = new Picture(pixels.length, pixels[0].length);
    Pixel[][] resultPixels = result.getPixels2D();

    for (int i = 0; i<pixels.length; i++) {
      for (int j = 0; j<pixels[0].length ;j++) {
        int r = 0, g = 0, b = 0;
        int num = 0;
        
        for (int x = Math.max(0, i - size/2); x<Math.min(pixels.length, i + size/2 + 1); x++) {
          for (int y = Math.max(0, j - size/2); y<Math.min(pixels[0].length, j + size/2 + 1); y++) {
            r += pixels[x][y].getRed();
            g += pixels[x][y].getGreen();
            b += pixels[x][y].getBlue();
            num++;
          }
        }

        r /= num;
        g /= num;
        b /= num;

        resultPixels[i][j].setRed(r);
        resultPixels[i][j].setGreen(g);
        resultPixels[i][j].setBlue(b);
      }
    }

    return result;
  }

   /** Method that enhances a picture by getting average Color around
   * a pixel then applies the following formula:
   *
   * pixelColor <- 2 * currentValue - averageValue
   *
   * size is the area to sample for blur.
   *
   * @param size Larger means more area to average around pixel
   * and longer compute time.
   * @return enhanced picture
   */
  public Picture enhance(int size) {
    Pixel[][] pixels = this.getPixels2D();
    Picture result = new Picture(pixels.length, pixels[0].length);
    Pixel[][] resultPixels = result.getPixels2D();

    for (int i = 0; i<pixels.length; i++) {
      for (int j = 0; j<pixels[0].length ;j++) {
        int r = 0, g = 0, b = 0;
        int num = 0;
        
        for (int x = Math.max(0, i - size/2); x<Math.min(pixels.length, i + size/2 + 1); x++) {
          for (int y = Math.max(0, j - size/2); y<Math.min(pixels[0].length, j + size/2 + 1); y++) {
            r += pixels[x][y].getRed();
            g += pixels[x][y].getGreen();
            b += pixels[x][y].getBlue();
            num++;
          }
        }

        r /= num;
        g /= num;
        b /= num;

        resultPixels[i][j].setRed(Math.max(0, pixels[i][j].getRed() * 2 - r));
        resultPixels[i][j].setGreen(Math.max(0, pixels[i][j].getGreen() * 2 - g));
        resultPixels[i][j].setBlue(Math.max(0, pixels[i][j].getBlue() * 2 - b));
      }
    }

    return result;
  }

  /** Method to only keep blue, setting red and green to 0 */
  public void keepOnlyBlue() {
    Pixel[][] pixels = this.getPixels2D();
    for (Pixel[] rowArray : pixels) {
      for (Pixel pixelObj : rowArray) {
        pixelObj.setRed(0);
        pixelObj.setGreen(0);
      }
    }
  }

  public void negate() {
    Pixel[][] pixels = this.getPixels2D();
    for (Pixel[] rowArray : pixels) {
      for (Pixel pixelObj : rowArray) {
        pixelObj.setRed(255 - pixelObj.getRed());
        pixelObj.setGreen(255 - pixelObj.getGreen());
        pixelObj.setBlue(255 - pixelObj.getBlue());
      }
    }
  }

  public void greyscale() {
    Pixel[][] pixels = this.getPixels2D();
    for (Pixel[] rowArray : pixels) {
      for (Pixel pixelObj : rowArray) {
        int avg = (pixelObj.getRed() + pixelObj.getGreen() + pixelObj.getBlue()) / 3;
        pixelObj.setRed(avg);
        pixelObj.setGreen(avg);
        pixelObj.setBlue(avg);
      }
    }
  }
  
  // END ASSIGNMENT ONES

  /** Method that mirrors the picture around a 
    * vertical mirror in the center of the picture
    * from left to right */
  public void mirrorVertical()
  {
    Pixel[][] pixels = this.getPixels2D();
    Pixel leftPixel = null;
    Pixel rightPixel = null;
    int width = pixels[0].length;
    for (int row = 0; row < pixels.length; row++)
    {
      for (int col = 0; col < width / 2; col++)
      {
        leftPixel = pixels[row][col];
        rightPixel = pixels[row][width - 1 - col];
        rightPixel.setColor(leftPixel.getColor());
      }
    } 
  }
  
  /** Mirror just part of a picture of a temple */
  public void mirrorTemple()
  {
    int mirrorPoint = 276;
    Pixel leftPixel = null;
    Pixel rightPixel = null;
    int count = 0;
    Pixel[][] pixels = this.getPixels2D();
    
    // loop through the rows
    for (int row = 27; row < 97; row++)
    {
      // loop from 13 to just before the mirror point
      for (int col = 13; col < mirrorPoint; col++)
      {
        
        leftPixel = pixels[row][col];      
        rightPixel = pixels[row]                       
                         [mirrorPoint - col + mirrorPoint];
        rightPixel.setColor(leftPixel.getColor());
      }
    }
  }
  
  /** copy from the passed fromPic to the
    * specified startRow and startCol in the
    * current picture
    * @param fromPic the picture to copy from
    * @param startRow the start row to copy to
    * @param startCol the start col to copy to
    */
  public void copy(Picture fromPic, 
                 int startRow, int startCol)
  {
    Pixel fromPixel = null;
    Pixel toPixel = null;
    Pixel[][] toPixels = this.getPixels2D();
    Pixel[][] fromPixels = fromPic.getPixels2D();
    for (int fromRow = 0, toRow = startRow; 
         fromRow < fromPixels.length &&
         toRow < toPixels.length; 
         fromRow++, toRow++)
    {
      for (int fromCol = 0, toCol = startCol; 
           fromCol < fromPixels[0].length &&
           toCol < toPixels[0].length;  
           fromCol++, toCol++)
      {
        fromPixel = fromPixels[fromRow][fromCol];
        toPixel = toPixels[toRow][toCol];
        toPixel.setColor(fromPixel.getColor());
      }
    }   
  }

  /** Method to create a collage of several pictures */
  public void createCollage()
  {
    Picture flower1 = new Picture("images/flower1.jpg");
    Picture flower2 = new Picture("images/flower2.jpg");
    this.copy(flower1,0,0);
    this.copy(flower2,100,0);
    this.copy(flower1,200,0);
    Picture flowerNoBlue = new Picture(flower2);
    flowerNoBlue.zeroBlue();
    this.copy(flowerNoBlue,300,0);
    this.copy(flower1,400,0);
    this.copy(flower2,500,0);
    this.mirrorVertical();
    this.write("images/collage.jpg");
  }
  
  
  /** Method to show large changes in color 
    * @param edgeDist the distance for finding edges
    */
  public void edgeDetection(int edgeDist)
  {
    Pixel leftPixel = null;
    Pixel rightPixel = null;
    Pixel[][] pixels = this.getPixels2D();
    Color rightColor = null;
    for (int row = 0; row < pixels.length; row++)
    {
      for (int col = 0; 
           col < pixels[0].length-1; col++)
      {
        leftPixel = pixels[row][col];
        rightPixel = pixels[row][col+1];
        rightColor = rightPixel.getColor();
        if (leftPixel.colorDistance(rightColor) > 
            edgeDist)
          leftPixel.setColor(Color.BLACK);
        else
          leftPixel.setColor(Color.WHITE);
      }
    }
  }
  
  
  /* Main method for testing - each class in Java can have a main 
   * method 
   */
  public static void main(String[] args) 
  {
    Picture beach = new Picture("images/beach.jpg");
    beach.explore();
    beach.zeroBlue();
    beach.explore();
  }
  
} // this } is the end of class Picture, put all new methods before this
