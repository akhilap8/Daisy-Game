import java.awt.image.BufferedImage;
import java.awt.Graphics;


class Donut extends Item 
{
	static BufferedImage image = null;
	Model model;

	public Donut(int x, int y, Model m)
	{
		this.x = x;
		this.y = y;
		w = 96;
		h = 70;
		model=m;
		// (lazy load image from disk into memory)
		loadDonutImage();
	}

	//json constructor (will unmarshal the donut)
	public Donut(Json ob, Model m)
	{
		x = (int)ob.getLong("x");
		y = (int)ob.getLong("y");
		w = 96;
		h = 70;
		model = m;
		// (lazy load image from disk into memory)
		loadDonutImage();
	}

	void loadDonutImage()
	{
		if(image==null)
			image = View.loadImage("donut.png");
	}

	public boolean didUserClickDonut(int xpos, int ypos)
	{
		//96 is donut's w
		//70 is donut's h
		if ((xpos < x)||(xpos > (x+96))||(ypos < y)||(ypos > (y+70))) 
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



