/**
 * This class contains class (static) methods
 * that will help you test the Picture class 
 * methods.  Uncomment the methods and the code
 * in the main to test.
 * 
 * @author Barbara Ericson 
 */
public class PictureTester
{

  // ASSIGNMENT ONES

  public static void testEdgeDetectionBelow(int edgeDist) {
    Picture beach = new Picture("images/swan.jpg");
    beach.edgeDetectionBelow(edgeDist);
    beach.explore();
  }

  public static void testBlur(int size) {
    Picture beach = new Picture("images/beach.jpg");
    beach.explore();
    beach.blur(size);
    beach.explore();
  }

  public static void textPixelate(int size)
  {
    Picture beach = new Picture("images/beach.jpg");
    beach.explore();
    beach.pixelate(size);
    beach.explore();
  }

  public static void testEnhance(int size) {
    Picture beach = new Picture("images/water.jpg");
    beach.explore();
    beach.enhance(size);
    beach.explore();
  }

  /** Method to test zeroBlue */
  public static void testZeroBlue()
  {
    Picture beach = new Picture("images/beach.jpg");
    beach.explore();
    beach.zeroBlue();
    beach.explore();
  }

  /** Method to test keepOnlyBlue */
  public static void testKeepOnlyBlue() {
    Picture beach = new Picture("images/beach.jpg");
    beach.explore();
    beach.keepOnlyBlue();
    beach.explore();
  }

  public static void testNegate() {
    Picture beach = new Picture("images/beach.jpg");
    beach.explore();
    beach.negate();
    beach.explore();
  }

  public static void testGreyscale() {
    Picture beach = new Picture("images/beach.jpg");
    beach.explore();
    beach.greyscale();
    beach.explore();
  }

  // END ASSIGNMENT ONES

  /** Method to test mirrorVertical */
  public static void testMirrorVertical()
  {
    Picture caterpillar = new Picture("images/caterpillar.jpg");
    caterpillar.explore();
    caterpillar.mirrorVertical();
    caterpillar.explore();
  }
  
  /** Method to test mirrorTemple */
  public static void testMirrorTemple()
  {
    Picture temple = new Picture("images/temple.jpg");
    temple.explore();
    temple.mirrorTemple();
    temple.explore();
  }
  
  /** Method to test the collage method */
  public static void testCollage()
  {
    Picture canvas = new Picture("images/640x480.jpg");
    canvas.createCollage();
    canvas.explore();
  }
  
  /** Method to test edgeDetection */
  public static void testEdgeDetection()
  {
    Picture swan = new Picture("images/swan.jpg");
    swan.edgeDetection(10);
    swan.explore();
  }
  
  /** Main method for testing.  Every class can have a main
    * method in Java */
  public static void main(String[] args)
  {
    // uncomment a call here to run a test
    // and comment out the ones you don't want
    // to run

    // ASSIGNMENT ONES
    //testZeroBlue();
    //testKeepOnlyBlue();
    //testGrayscale();
    //testBlur(10);
    //textPixelate(10);
    testEnhance(20);
    //testEdgeDetectionBelow(15);

    //testKeepOnlyRed();
    //testKeepOnlyGreen();
    //testNegate();
    //testFixUnderwater();
    //testMirrorVertical();
    //testMirrorTemple();
    //testMirrorArms();
    //testMirrorGull();
    //testMirrorDiagonal();
    //testCollage();
    //testCopy();
    //testEdgeDetection();
    //testEdgeDetection2();
    //testChromakey();
    //testEncodeAndDecode();
    //testGetCountRedOverValue(250);
    //testSetRedToHalfValueInTopHalf();
    //testClearBlueOverValue(200);
    //testGetAverageForColumn(0);
  }
}