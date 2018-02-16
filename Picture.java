import java.awt.*;
import java.awt.font.*;
import java.awt.geom.*;
import java.awt.image.BufferedImage;
import java.text.*;
import java.util.*;
import java.util.List; // resolves problem with java.awt.List and java.util.List

/**
 * A class that represents a picture.  This class inherits from 
 * SimplePicture and allows the student to add functionality to
 * the Picture class.  
 * 
 * @author Barbara Ericson ericson@cc.gatech.edu
 * @author Cynthia Hom (added methods only)
 * @since February 8, 2018 (added methods)
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
    Picture flower1 = new Picture("flower1.jpg");
    Picture flower2 = new Picture("flower2.jpg");
    this.copy(flower1,0,0);
    this.copy(flower2,100,0);
    this.copy(flower1,200,0);
    Picture flowerNoBlue = new Picture(flower2);
    flowerNoBlue.zeroBlue();
    this.copy(flowerNoBlue,300,0);
    this.copy(flower1,400,0);
    this.copy(flower2,500,0);
    this.mirrorVertical();
    this.write("collage.jpg");
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
  

/******* Methods added by Cynthia Hom ***************/

  /** Keeps only the blue color for each pixel
   */
  public void keepOnlyBlue()
  {
	  Pixel[][] pixels = this.getPixels2D();
	  for (Pixel[] rowArray : pixels)
	  {
		  for (Pixel pixelObj : rowArray)
		  {
			pixelObj.setRed(0);
			pixelObj.setGreen(0);
		  }
	  }
  }
  
  /** Negates the pixels in the picture by setting their color
   * values to 255 minus their current color
   */
   public void negate()
   {
	  Pixel[][] pixels = this.getPixels2D();
	  for (Pixel[] rowArray : pixels)
	  {
		  for (Pixel pixelObj : rowArray)
		  {
			pixelObj.setRed(255- pixelObj.getRed());
			pixelObj.setGreen(255- pixelObj.getGreen());
			pixelObj.setBlue(255- pixelObj.getBlue());
		  }
	  }
   }
   
   /** Turns the picutre into black and white by making the pixels gray
    * Sets all values to the average of the red, green, and blue values
    */
   public void grayScale()
   {
	  Pixel[][] pixels = this.getPixels2D();
	  for (Pixel[] rowArray : pixels)
	  {
		  for (Pixel pixelObj : rowArray)
		  {
			  int gray = (pixelObj.getRed() + pixelObj.getBlue() + 
						pixelObj.getGreen())/3;
			  pixelObj.setRed(gray);
			  pixelObj.setGreen(gray);
			  pixelObj.setBlue(gray);
		  }
	  }
   }
   
   /** Makes the fish in the picture water.jpg stand out more
    * by making the surroundings more green
    */
   public void amplifyFish()
   {
      Pixel[][] pixels = this.getPixels2D();
      // all fish have red from 10 to 20, green from 155-170,
        //blue from 165 to 190
      Color avgFishColor = new Color(15, 162, 177);
      for (Pixel[] rowArray : pixels)
      {
        for (Pixel pixelObj : rowArray)
        {
          //how much the pixel's color differs from the average
            //fish color
          double colorDistance = pixelObj.colorDistance(avgFishColor);

          // decrement blue and increment green according to color
            //distance- the greater the distance, the greater the change
            // this way the surroundings become more green, while fish
                //stay the same
          pixelObj.setGreen(pixelObj.getGreen() + (int)(colorDistance / 1.5));
          pixelObj.setBlue(pixelObj.getBlue() - (int)(colorDistance / 1.5));
        }
      }
   }
   
    /** To pixelate by dividing area into size x size
	 *  @param size    Side length of square area to pixelate. 
	 */
	 public void pixelate(int size)
	 {
		  Pixel[][] pixels = this.getPixels2D();
		  
		  // total number of full rows and columns of square areas
        // (not counting ends)
		  int totalRows = pixels.length/size;
		  int totalCols = pixels[0].length/size;
		  
		  // the dimensions of the area to average- same as size
				// unless at edges of picture
		  int areaWidth = size;
		  int areaHeight = size;
		  
		  // upper left corner starting vars for each square area
		  int x = 0;
		  int y = 0;
		  
		  // loop through square areas until no pixels left
      // loop through rows (vertically)
		  for (int rowNum = 0; rowNum <= totalRows; rowNum++)
		  {
        //loop through columns (horizantally)
			  for (int colNum = 0; colNum <= totalCols; colNum++)
			  {	
				  // if on one of the square areas on the edge, make 
					// sure you don't go over the edge
				  if (rowNum == totalRows) 
					 areaHeight = pixels.length - y;
				  if (colNum == totalCols)
					 areaWidth = pixels[0].length - x;
					 
				   //  loop through all pixels in area and
					// add up total for each color
				  Color averageColor = findAverage(x, y, areaWidth, 
											areaHeight, pixels);

          //set each pixel's color to the average
          setSectionColor(x, y, areaWidth, areaHeight, pixels, averageColor);

					//update x
					x += areaWidth;
			   }

         //update y and x
         y += areaHeight;
         x = 0;

         //reset area width and height
         areaWidth = size;
         areaHeight = size;
		  }
	 }

  /** Sets the color for the given section of pixels to the 
   * color passed in (helper method for pixelate)
   * @param int startX, int startY  the coordinates of the upper
   *                  left corner of the section
   * @param width, height       width and height of section to
   *                    find average of
   * @param Pixel[][] pixels      the array of pixels
   * @param Color color   the color to which the pixels should
   *                        be set to
   */
  private void setSectionColor(int startX, int startY, int width, int height,
      Pixel [][] pixels, Color color)
  {
      //  loop through all pixels in area
    for(int x = startX; x < startX + width; x++)
    {
      for(int y = startY; y < startY + height; y++)
      {
        // set the color
        pixels[y][x].setColor(color);
      }
    }
  }
	
	/** Returns the average color for the given section of pixels
   * (helper method for pixelate)
	 * @param int startX, int startY 	the coordinates of the upper
	 * 									left corner of the section
	 * @param width, height				width and height of section to
	 * 										find average of
	 * @param Pixel[][] pixels			the array of pixels
	 * @return Color averageColor		the average color of all pixels
	 * 									in the area
	 */
	private Color findAverage(int startX, int startY, int width, int height,
			Pixel [][] pixels)
	{
		// totals for red, green, blue values
		int redTotal = 0;
		int greenTotal = 0;
		int blueTotal = 0;
		
		// total number of pixels
		int pixelCount = 0;
		
		//  loop through all pixels in area
		for(int x = startX; x < startX + width; x++)
		{
			for(int y = startY; y < startY + height; y++)
			{
				// increment totals
				redTotal += pixels[y][x].getRed();
				greenTotal += pixels[y][x].getGreen();
				blueTotal += pixels[y][x].getBlue();
				
				pixelCount++;
			}
		}
		
		//determine averages, return the average color
		int redAverage = (int)((float)redTotal/pixelCount);
		int greenAverage = (int)((float)greenTotal/pixelCount);
		int blueAverage = (int)((float)blueTotal/pixelCount);
		Color averageColor = new Color(redAverage, greenAverage,
			blueAverage);
		return averageColor;
	}
  
  /** Method that blurs the picture
  * @param size Blur size, greater is more blur 
  * @return Blurred picture
  */
  public Picture blur(int size)
  {
    Pixel[][] pixels = this.getPixels2D();
    // the new blurred picture
    Picture result = new Picture(pixels.length, pixels[0].length);
    Pixel[][] resultPixels = result.getPixels2D();

    // loop through all pixels in the array
    for (int y = 0; y < pixels.length; y++)
    {
      for(int x = 0; x < pixels[y].length; x++)
      {
          // find the average of the surrounding area
          Color average = findSurroundingAverage(x, y, size, pixels);

          //set average to this pixel
          resultPixels[y][x].setColor(average);
      }
    }

    return result;
  }
  
  /** Method that makes a watermark using the cynthia.jpg picture
   * 	Changes the picture by changing the pixels at the indicies 
   * 	where the cynthia.jpg picture has a black pixel
   */
  public void watermark ()
  {
	  Pixel[][] pixels = this.getPixels2D();
	  
	  // the picture to use for watermark
	  Picture watermarkPic = new Picture("images/cynthia.jpg");
	  Pixel[][] watermarkPixels = watermarkPic.getPixels2D();
	  
	  // the amount to increment color values for watermark
	  int INCREMENT = 50;

    // loop through all pixels in the original picture
    for (int y = 0; y < pixels.length; y++)
    {
      for(int x = 0; x < pixels[y].length; x++)
      {
		  // repeat watermark if neccessary
		  int newX = x % watermarkPixels[0].length;
		  int newY = y % watermarkPixels.length;

		  // if the pixel in the watermark picture is black,
			// increment all color values to make them lighter
          if( watermarkPixels[newY][newX].getColor().equals(Color.BLACK) )
          {
			  // calculate new values
			  int newRed = pixels[y][x].getRed() + INCREMENT;
			  int newGreen = pixels[y][x].getGreen() + INCREMENT;
			  int newBlue = pixels[y][x].getBlue() + INCREMENT; 
			  
			  // set new values
			  pixels[y][x].setRed(newRed);
			  pixels[y][x].setGreen(newGreen);
			  pixels[y][x].setBlue(newBlue);
		  }
      }
    }
  }

  /** Method that enhances a picture by getting average Color around 
  * a pixel then applies the following formula:
  *
  * pixelColor <- 2 * currentValue - averageValue
  *
  *  size is the area to sample for blur.

  *  @param size      Larger means more area to average around pixel
                         and longer compute time.
  *  @return          enhanced picture
  */
  public Picture enhance(int size)
  {
    Pixel[][] pixels = this.getPixels2D();
    Picture result = new Picture(pixels.length, pixels[0].length);
    Pixel[][] resultPixels = result.getPixels2D();

    // loop through all pixels in the array
    for (int y = 0; y < pixels.length; y++)
    {
      for(int x = 0; x < pixels[y].length; x++)
      {
          // find the average of the surrounding area
          Color average = findSurroundingAverage(x, y, size, pixels);
          
          // calc new values according to formula
          int red = 2 * pixels[y][x].getRed() - average.getRed();
          int green = 2 * pixels[y][x].getGreen() - average.getGreen();
          int blue = 2 * pixels[y][x].getBlue() - average.getBlue();

          // use set method so values are corrected if neccessary

          resultPixels[y][x].setRed( red );
          resultPixels[y][x].setGreen( green );
          resultPixels[y][x].setBlue( blue );
      }
    }
    // return the enhanced picture
    return result;
  }


  /** Finds the average color of the pixels surrounding the given
   * pixel given the size of the area around the pixel to average
   * (Helper method for blur and enhance)
   * @param int x                 the x value of the pixel
   * @param int y                 the y value of the pixel
   * @param int size              the size of the surrounding area to find
   *                              the averge of
   * @param Pixel [][] pixels     the pixel array
   * @return                      the average color of the surrouding 
   *                                    pixels
   */
  private Color findSurroundingAverage(int x, int y, int size,
                                             Pixel [][] pixels)
  {
         // values to help find the area to average
         int startX, startY, width, height = 0;

         //default values of starting x and y of the section
              // as well as width and height
          startX = x - size/2;
          startY = y - size/2;
          width = height = size;

          //change values if at the end- if no pixels on one side
            // or another of the pixel to blur
          // if x values go too far left
          if (startX < 0)
          {
             startX = 0;
             // x + size/2 is the ending pixel's index, this is to 
                // update width if the area does not include
                // the default amount of pixels to the left
             width = x + size/2;
          }
          // if x goes too far right
          else if (startX + width >= pixels[y].length)
          {
              //pixels[y].length - 1 is the last pixel's x value
              width = pixels[y].length - startX;
          }

          //if y goes too far up
          if (startY < 0)
          {
              startY = 0;
              height = y + size/2;
          }
          // y goes too far down
          else if (startY + height >= pixels.length)
          {
              height = pixels.length - startY;
          }

          //calculate average- this is average with whole section
              // including this pixel
          Color averageWithPixel = findAverage(startX, startY, width, height, pixels);

          // find the average red, blue, green values for only the 
              //surroundings by taking out this pixel's value
          int avgRed = (int)((double)(averageWithPixel.getRed() * width * height -
             (double)pixels[y][x].getRed())/(width * height - 1));
          int avgBlue = (int)((double)(averageWithPixel.getBlue() * width * height -
             (double)pixels[y][x].getBlue())/(width * height - 1));
          int avgGreen = (int)((double)(averageWithPixel.getGreen() * width * height -
             (double)pixels[y][x].getGreen())/(width * height - 1));

          return new Color(avgRed, avgGreen, avgBlue);
  }
  
  /** Swaps the left and right sides of the picture, so the left half
   * ends up on the right and the right half is wrapped around to the
   * left 
   * This is done according to the formula:
   * newColumn = (column + width / 2) % width
   * 
   * @return Picture 		the picture with the swapped halves
   */
  public Picture swapLeftRight()
  {
	    Pixel[][] pixels = this.getPixels2D();
		Picture result = new Picture(pixels.length, pixels[0].length);
		Pixel[][] resultPixels = result.getPixels2D();

		// width of the picture
		int width = pixels[0].length;
		
		//the new shifted column number (new x value)
		int newColumn = 0; 		
		
		// loop through all pixels in the array
		for (int y = 0; y < pixels.length; y++)
		{
		  for(int x = 0; x < pixels[y].length; x++)
		  {
			  newColumn = (x + width/2) % width;
			  
			  // use set method based on column formula
			  resultPixels[y][newColumn].setColor(pixels[y][x].getColor());
		  }
		}
		// return the enhanced picture
		return result;
  }
  
   /** Creates a stair step effect where the pixels are shifted to
    * 	the right according to their placement upon the page
    * 	Pixels are shifted more to the right the lower they are on
    * 	the picture
   *  @param shiftCount  The number of pixels to shift to the right 
   *  @param steps  The number of steps 
   *  @return  The picture with pixels shifted in stair steps 
   */ 
  public Picture stairStep(int shiftCount, int steps) 
  {
	    Pixel[][] pixels = this.getPixels2D();
		Picture result = new Picture(pixels.length, pixels[0].length);
		Pixel[][] resultPixels = result.getPixels2D();
		
		// the width of the picture
		int width = pixels[0].length;
		
		//the new shifted column number (new x value)
		int newColumn = 0; 		
		
		// the height of each step
		int stairHeight = pixels.length / steps;
		
		// the number of the step, with 0 as the first step from 
				// the top
		int stepNum = 0;
		
		// loop through all pixels in the array
		for (int y = 0; y < pixels.length; y++)
		{
			  stepNum = y / stairHeight;
			  for(int x = 0; x < width; x++)
			  {
				  // multiply by stepNum because the amount of shift
						// depends on which step it is
				  newColumn = (x + shiftCount * stepNum) % width;
				  
				  // use set method based on column formula
				  resultPixels[y][newColumn].setColor(pixels[y][x].getColor());
			  }
		}
		// return the enhanced picture
		return result;
  }
  
   /** Liquifies the picture by shifting the middle pixels the most, 
    * 	with less shift as one goes towards either the top or bottom
    * 	 of the picture
    * A gaussian curve is used to calculate the shift
    * 
   *  @param maxFactor  Larger means more pixel shift - between 0 and 100
   *  @return           Liquified picture 
   */ 
  public Picture liquify(int maxFactor) 
  {
		Pixel[][] pixels = this.getPixels2D();
		Picture result = new Picture(pixels.length, pixels[0].length);
		Pixel[][] resultPixels = result.getPixels2D();
		
		// the width and height of the picture
		int width = pixels[0].length;
		int height = pixels.length;
		
		//the new shifted column number (new x value)
		int newColumn = 0;
		
		// amount to shift to the right
		int rightShift = 0;
		
		// exponent in formula
		double exponent = 0;
		
		// compute the standard deviation of the bell- larger means a greater
			// range of y values is affected
		int STANDARD_DEVIATIONS_IN_A_PICTURE = 12;
		double bellWidth = (double)height/STANDARD_DEVIATIONS_IN_A_PICTURE;
		
		// loop through all pixels in the array
		for (int y = 0; y < pixels.length; y++)
		{
			  for(int x = 0; x < width; x++)
			  {
				  // use bell curve formula to calculate shift
				  exponent = Math.pow(y - height / 2.0, 2) 
										/(2.0 * Math.pow(bellWidth, 2)); 
				  rightShift = (int)(maxFactor * Math.exp(- exponent)); 
				  
				  // multiply by stepNum because the amount of shift
						// depends on which step it is
				  newColumn = (x + rightShift) % width;
				  
				  // use set method based on column formula
				  resultPixels[y][newColumn].setColor(pixels[y][x].getColor());
			  }
		}
		// return the enhanced picture
		return result;
  }
  
  
  /** Creates a wavy picture by using a sine function for the shift
  *  @param amplitude  The maximum shift of pixels 
  *  @return           Wavy picture 
  */ 
  public Picture wavy(int amplitude)
  {
		Pixel[][] pixels = this.getPixels2D();
		Picture result = new Picture(pixels.length, pixels[0].length);
		Pixel[][] resultPixels = result.getPixels2D();
		
		// the width and height of the picture
		int width = pixels[0].length;
		int height = pixels.length;
		
		//the new shifted column number (new x value)
		int newColumn = 0;
		
		// amount to shift to the right
		int rightShift = 0;
		
		// total number of waves (one wave is left and right)
		int totalWaves = 5;
		
		// frequency of the wave: amount of waves per pixel
		double frequency = 1/(pixels.length/(double)totalWaves);
		
		// loop through all pixels in the array
		for (int y = 0; y < pixels.length; y++)
		{
			  for(int x = 0; x < width; x++)
			  {
				  // use sine formula to calculate the shift
						// starts at zero shift at (0, 0) so no phase shift
				  rightShift = (int)(amplitude * Math.sin(2 * Math.PI * 
								frequency * y));

				  // multiply by stepNum because the amount of shift
						// depends on which step it is
				  newColumn = (x + rightShift) % width;
				  
				  // account for negative values
				  if (newColumn < 0)
					 newColumn = width + newColumn;
				  
				  // use set method based on column formula
				  resultPixels[y][newColumn].setColor(pixels[y][x].getColor());
			  }
		}
		// return the enhanced picture
		return result;
  }
  
  
  /** Main method for testing - each class in Java can have a main 
   * method 
   */
  public static void main(String[] args) 
  {
    Picture beach = new Picture("beach.jpg");
    beach.explore();
    beach.zeroBlue();
    beach.explore();
  }
  
} // this } is the end of class Picture, put all new methods before this