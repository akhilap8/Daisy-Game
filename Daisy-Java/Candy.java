import java.awt.image.BufferedImage;
import java.awt.Graphics;


class Candy extends Item 
{
	static BufferedImage image = null;
	Model model;

	public Candy(int x, int y, Model m)
	{
		this.x = x;
		this.y = y;
		w = 79;
		h = 70;
		model=m;
		// (lazy load image from disk into memory)
		loadCandyImage();
	}

	//json constructor (will unmarshal the candy)
	public Candy(Json ob, Model m)
	{
		x = (int)ob.getLong("x");
		y = (int)ob.getLong("y");
		w = 79;
		h = 70;
		model = m;
		// (lazy load image from disk into memory)
		loadCandyImage();
	}

	void loadCandyImage()
	{
		if(image==null)
			image = View.loadImage("candy.png");
	}

	public boolean didUserClickCandy(int xpos, int ypos)
	{
		//79 is candy's w
		//70 is candy's h
		if ((xpos < x)||(xpos > (x+79))||(ypos < y)||(ypos > (y+70))) 
			return false;
		else 
			return true;
	}

	Json marshal()
	{
		Json ob = Json.newObject();
		ob.add("x", x);
		ob.add("y", y);

		return ob;
	}

	void update()
	{

	}

	void draw(Graphics g)
	{
		g.drawImage(image, x - model.daisy.x + 200, y, null);
	}


}



