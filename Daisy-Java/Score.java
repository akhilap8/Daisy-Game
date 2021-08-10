import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.awt.Graphics;

class Score extends Item
{
	static BufferedImage[] num = null;
	Model model;
	int scoreNum;
	String place;
	String score;
	boolean canDraw;

	public Score(int x, int y, String place, Model m)
	{
		this.x = x;
		this.y = y;
		this.place = place;
		this.model = m;
		scoreNum = 0;
		canDraw = false;

		if(num == null)
		{
			num = new BufferedImage[10];
			num[0] = View.loadImage("0.png");
			num[1] = View.loadImage("1.png");
			num[2] = View.loadImage("2.png");
			num[3] = View.loadImage("3.png");
			num[4] = View.loadImage("4.png");
			num[5] = View.loadImage("5.png");
			num[6] = View.loadImage("6.png");
			num[7] = View.loadImage("7.png");
			num[8] = View.loadImage("8.png");
			num[9] = View.loadImage("9.png");
		}
	}


	void draw(Graphics g)
	{
		if(canDraw) 
		{
			g.drawImage(num[scoreNum], x, y, null);
		}
		
	}

	void update()
	{
		// check to see how many digits the score is
		if(place.equals("ones"))
		{
			canDraw = true;
		}
		if(place.equals("tens") && model.count > 9)
		{
			canDraw = true;
		}
		// get the score and put individual digit characters in array
		score = Integer.toString(model.count);
		String [] numarr = score.split("");
	
		// check digit and assign that value to get the correct image for number
		if(place.equals("ones"))
		{
			int onesNum = Integer.parseInt(numarr[numarr.length-1]);
			scoreNum = onesNum;

		}
		else if (place.equals("tens") && numarr.length > 1)
		{
			if(model.count > 9)
			{
				int tensNum = Integer.parseInt(numarr[numarr.length-2]);
				scoreNum = tensNum;
			}
		}

	}
}



