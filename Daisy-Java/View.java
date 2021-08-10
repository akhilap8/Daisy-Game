import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.io.File;
import javax.swing.JButton;
import java.awt.Color;


class View extends JPanel
{
	// Member Variables
	BufferedImage bkgd_image; 
	Model model; 

	View(Controller c, Model m)
	{
		model = m; 
		c.setView(this); 
		try
		{
			this.bkgd_image =
				ImageIO.read(new File("bkgd.png"));
		} catch(Exception e) {
			e.printStackTrace(System.err);
			System.exit(1);
		}
	}

	//loadImage function
	public static BufferedImage loadImage(String filename)
	{
		BufferedImage im = null;
		try
		{
			im = ImageIO.read(new File(filename));
		} catch(Exception e) {
			e.printStackTrace(System.err);
			System.exit(1);
		}
		return im;
	}

	// draw all images to screen
	public void paintComponent(Graphics g)
	{
		
		g.drawImage(this.bkgd_image, 0, 0, null);

		for(int i = 0; i < model.items.size(); i++)
		{
			Item t = model.items.get(i);
			t.draw(g);
		}
	
	}
}

