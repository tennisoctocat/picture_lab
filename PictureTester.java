/**
 * This class contains class (static) methods
 * that will help you test the Picture class 
 * methods.  Uncomment the methods and the code
 * in the main to test.
 * 
 * @author Barbara Ericson 
 * @author Cynthia Hom (added methods only)
 * @since February 8, 2018 (added methods)
 */
public class PictureTester
{
  /** Method to test zeroBlue */
  public static void testZeroBlue()
  {
    Picture beach = new Picture("beach.jpg");
    beach.explore();
    beach.zeroBlue();
    beach.explore();
  }
  
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
  
  /** Method to test keepOnlyBlue */
  public static void testKeepOnlyBlue()
  {
	  Picture beach = new Picture("beach.jpg");
	  beach.explore();
	  beach.keepOnlyBlue();
	  beach.explore();
  }
  
  /** Method to test negate */
  public static void testNegate()
  {
	  Picture beach = new Picture("beach.jpg");
	  beach.explore();
	  beach.negate();
	  beach.explore();
  }
  
  /** Method to test grayScale */
  public static void testGrayscale()
  {
	  Picture pic = new Picture("images/water.jpg");
	  pic.explore();
	  pic.grayScale();
	  pic.explore();
  }

  /** Method to test amplifyFish */
  public static void testAmplifyFish()
  {
	  Picture pic = new Picture("images/water.jpg");
	  pic.explore();
	  pic.amplifyFish();
	  pic.explore();
  }
  
  /** Method to test pixelate */
  public static void testPixelate()
  {
  	  int PIXELATE_WIDTH = 10;
	  Picture pic = new Picture("images/swan.jpg");
	  pic.explore();
	  pic.pixelate(PIXELATE_WIDTH);
	  pic.explore();
  }

  /** Method to test blur */
  public static void testBlur()
  {
  	int BLUR_WIDTH = 10;
  	Picture pic = new Picture("images/swan.jpg");
	  pic.explore();
	  Picture blurredPic = pic.blur(BLUR_WIDTH);
	  blurredPic.explore();
  }

  /** Method to test enhance */
  public static void testEnhance()
  {
    int ENHANCE_WIDTH = 10;
    Picture pic = new Picture("images/water.jpg");
    pic.explore();
    Picture enhancedPic = pic.enhance(ENHANCE_WIDTH);
    enhancedPic.explore();
  }
  
  /** Method to test swapLeftRight */
  public static void testSwapLeftRight()
  {
    Picture pic = new Picture("images/water.jpg");
    pic.explore();
    Picture swappedPic = pic.swapLeftRight();
    swappedPic.explore();
  }

  /** Method to test stairStep */
  public static void testStairStep()
  {
    Picture pic = new Picture("images/redMotorcycle.jpg");
    pic.explore();
    Picture newPic = pic.stairStep(10, 10);
    newPic.explore();
  }
  
  /** Method to test liquify */
  public static void testLiquify()
  {
    Picture pic = new Picture("images/redMotorcycle.jpg");
    pic.explore();
    Picture newPic = pic.liquify(200);
    newPic.explore();
  }
  
  /** Method to test wavy */
  public static void testWavy()
  {
    Picture pic = new Picture("images/arch.jpg");
    pic.explore();
    Picture newPic = pic.wavy(20);
    newPic.explore();
  }
  
  /** Method to test watermark */
  public static void testWatermark()
  {
	  Picture pic = new Picture("images/tennisHamster.jpg");
	  pic.explore();
	  pic.watermark();
	  pic.explore();
  }
  
  /** Main method for testing.  Every class can have a main
    * method in Java */
  public static void main(String[] args)
  {
    // uncomment a call here to run a test
    // and comment out the ones you don't want
    // to run
    testWatermark();
    //testWavy();
    //testLiquify();
    //testStairStep();
    //testSwapLeftRight();
    //testEnhance();
    //testBlur();
    //testPixelate();
  	//testAmplifyFish();
    //testZeroBlue();
    //testKeepOnlyBlue();
    //testKeepOnlyRed();
    //testKeepOnlyGreen();
    //testNegate();
    //testGrayscale();
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