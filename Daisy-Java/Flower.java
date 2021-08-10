import java.awt.image.BufferedImage;
import java.awt.Graphics;


class Flower extends Item 
{
	static BufferedImage image = null;
	Model model;

	public Flower(int x, int y, Model m)
	{
		this.x = x;
		this.y = y;
		w = 184;
		h = 360;
		model=m;
		// (lazy load image from disk into memory)
		loadFlowerImage();
	}

	//json constructor (will unmarshal the flower)
	public Flower(Json ob, Model m)
	{
		x = (int)ob.getLong("x");
		y = (int)ob.getLong("y");
		w = 184;
		h = 360;
		model = m;
		// (lazy load image from disk into memory)
		loadFlowerImage();
	}

	void loadFlowerImage()
	{
		if(image==null)
			image = View.loadImage("flower.png");
	}

	public boolean didUserClickFlower(int xpos, int ypos)
	{
		//184 is flower's w
		//360 is flower's h
		if ((xpos < x)||(xpos > (x+184))||(ypos < y)||(ypos > (y+360))) 
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



