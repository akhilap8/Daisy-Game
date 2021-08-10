import java.awt.image.BufferedImage;
import java.awt.Graphics;

class Daisy extends Item
{
	static BufferedImage[] daisy_images = null; //lazy loading
	int daisyNum;
	double vert_vel; // add gravity
	int numFrames;
	int px, py;
	int frameCount;

	public Daisy(int x, int y)
	{
		this.x = x;
		this.y = y;
		w = 122;
		h = 180;
		daisyNum = 0;
		vert_vel = 0.0;
		frameCount = 0;

		// lazy loading daisy images into memory
		if(daisy_images == null){
			daisy_images = new BufferedImage[5];
			daisy_images[0] = View.loadImage("daisy1.png");
			daisy_images[1] = View.loadImage("daisy2.png");
			daisy_images[2] = View.loadImage("daisy3.png");
			daisy_images[3] = View.loadImage("daisy4.png");
			daisy_images[4] = View.loadImage("daisy5.png");
		}
	}

	void draw(Graphics g)
	{
		g.drawImage(daisy_images[daisyNum], 200, y, null);
	}

	void savePrevCoord()
	{
		px = x;
		py = y;
	}

	void getOutOfItem(Item t)
	{
		if((x + w >= t.x) && (px + w < t.x))	
			x = t.x - w-1;
		else if((x <= t.x + t.w) && (px > t.x + t.w))	
			x = t.x + t.w+1;
		else if((y + h >= t.y) && (py + h < t.y)){	
			y = t.y - h-1;
			frameCount = 0;
			vert_vel = 0;
		}
		else if((y <= t.y + t.h) && (py > t.y + t.h))		
			y = t.y + t.h+1;
		else
			System.out.println("There is an error!");
	}

	// make daisy jump
	void jump()
	{
		if(frameCount == 0)
			vert_vel = -20;
		if(frameCount < 5) {
			vert_vel -= 5; 
		}

	}

	void update()
	{
		frameCount++;
		// add gravity
		vert_vel += 2.0;
		y += vert_vel;

		// have daisy land on ground
		if(y > 330)
		{
			vert_vel = 0.0;
			y = 330; // snap back to the ground
			frameCount=0;
		}
		// add cap to how high daisy can jump
		if(y < 0)
		{
			y=0;
		}
	}

	void update2()
	{
		daisyNum++;
		if (daisyNum > 4)
			daisyNum = 0;
	}

}





