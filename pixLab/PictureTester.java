
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

  public static void testRotate(double angle) {
    Picture beach = new Picture("images/beach.jpg");
    beach.explore();
    Picture result = beach.rotate(angle);
    result.explore();
  }

  public static void testGreenScreen() {
    // choose any picture to start since it will *not* be used
    Picture pic = new Picture("images/beach.jpg");
    Picture gScreen = pic.greenScreen();
    gScreen.explore();
  } 

  public static void testEdgeDetectionBelow(int edgeDist) {
    Picture beach = new Picture("images/swan.jpg");
    beach = beach.edgeDetectionBelow(edgeDist);
    beach.explore();
  }

  public static void testWavy(int amp) {
    Picture motor = new Picture("images/redMotorcycle.jpg");
    motor.explore();
    motor = motor.wavy(amp);
    motor.explore(); 
  }

  public static void testLiquify(int maxHeight) {
    Picture motor = new Picture("images/redMotorcycle.jpg");
    motor.explore();
    motor = motor.liquify(maxHeight);
    motor.explore(); 
  }

  public static void testStairStep(int shiftCount, int steps) {
    Picture motor = new Picture("images/redMotorcycle.jpg");
    motor.explore();
    motor = motor.stairStep(shiftCount, steps);
    motor.explore();
  }

  public static void testSwap() {
    Picture motor = new Picture("images/redMotorcycle.jpg");
    motor.explore();
    motor = motor.swapLeftRight();
    motor.explore();
  }

  public static void testBlur(int size) {
    Picture beach = new Picture("images/beach.jpg");
    beach.explore();
    beach = beach.blur(size);
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
    beach = beach.enhance(size);
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
    swan.edgeDetection(15);
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
    //testEnhance(20);
    //testSwap();
    //testStairStep(1, 400);
    // testLiquify(100);
    // testWavy(100);
    testEdgeDetectionBelow(12);
    testGreenScreen();
    testRotate(Math.PI/6);
    testRotate(Math.PI/4);
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
