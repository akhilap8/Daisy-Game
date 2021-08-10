import java.awt.image.BufferedImage;
import java.awt.Graphics;


class Bench extends Item 
{
	static BufferedImage image = null;
	Model model;

	public Bench(int x, int y, Model m)
	{
		this.x = x;
		this.y = y;
		w = 258;
		h = 190;
		model=m;
		// (lazy load image from disk into memory)
		loadBenchImage();
	}

	//json constructor (will unmarshal the bench)
	public Bench(Json ob, Model m)
	{
		x = (int)ob.getLong("x");
		y = (int)ob.getLong("y");
		w = 258;
		h = 190;
		model = m;
		// (lazy load image from disk into memory)
		loadBenchImage();
	}

	void loadBenchImage()
	{
		if(image==null)
			image = View.loadImage("bench.png");
	}

	public boolean didUserClickBench(int xpos, int ypos)
	{
		//258 is bench's w
		//190 is bench's h
		if ((xpos < x)||(xpos > (x+258))||(ypos < y)||(ypos > (y+190))) 
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



