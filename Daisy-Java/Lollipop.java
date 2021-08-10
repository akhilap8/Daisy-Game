import java.awt.image.BufferedImage;
import java.awt.Graphics;


class Lollipop extends Item 
{
	static BufferedImage image = null;
	Model model;

	public Lollipop(int x, int y, Model m)
	{
		this.x = x;
		this.y = y;
		w = 48;
		h = 70;
		model=m;
		// (lazy load image from disk into memory)
		loadLollipopImage();
	}

	//json constructor (will unmarshal the lollipop)
	public Lollipop(Json ob, Model m)
	{
		x = (int)ob.getLong("x");
		y = (int)ob.getLong("y");
		w = 48;
		h = 70;
		model = m;
		// (lazy load image from disk into memory)
		loadLollipopImage();
	}

	void loadLollipopImage()
	{
		if(image==null)
			image = View.loadImage("lollipop.png");
	}

	public boolean didUserClickLollipop(int xpos, int ypos)
	{
		//48 is lollipop's w
		//70 is lollipop's h
		if ((xpos < x)||(xpos > (x+48))||(ypos < y)||(ypos > (y+70))) 
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



