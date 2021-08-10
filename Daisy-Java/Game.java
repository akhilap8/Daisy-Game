import javax.swing.JFrame;
import java.awt.Toolkit;

public class Game extends JFrame
{
	// Member Variables
	Model model;
	Controller controller;
	View view;

	public Game()
	{
		model = new Model();
		controller = new Controller(model); 
		view = new View(controller, model); 

		this.setTitle("Daisy");
		this.setSize(1140, 600);
		this.setFocusable(true);
		this.getContentPane().add(view);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		view.addMouseListener(controller); 
		this.addKeyListener(controller);
		this.setVisible(true);

		try
		{
			Json j = Json.load("map.json");
			model.unmarshal(j);
			System.out.println("loaded map.json");
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.out.println("Error loading map");
			System.exit(0);
		}
	}

	
	public void run()
	{
		while(true)
		{
			controller.update();
			model.update();
			view.repaint(); // Indirectly calls View.paintComponent
			Toolkit.getDefaultToolkit().sync(); // Updates screen

			try
			{
				Thread.sleep(40);
			} catch(Exception e) {
				e.printStackTrace();
				System.exit(1);
			}
		}
	}

	public static void main(String[] args)
	{
		Game g = new Game();
		g.run();
	}
}






