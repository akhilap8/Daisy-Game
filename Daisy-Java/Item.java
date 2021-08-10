import java.awt.image.BufferedImage;
import java.awt.Graphics;

abstract class Item
{
	int x, y;
	int w, h;
	// static BufferedImage image = null;

	//abstract methods that must be implemented in Daisy, Flower, Bench, Candy, Lollipop, Donut
	abstract void update();
	abstract void draw(Graphics g);

}