import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

class Controller implements ActionListener, MouseListener, KeyListener
{ 
	// Member Variables
	View view; 
	Model model;

	boolean keyLeft;
	boolean keyRight;
	boolean keyUp;
	boolean keyDown;
	boolean spacebar;

	boolean addFlowersEditor = false;
	boolean addBenchesEditor = false;
	boolean addCandiesEditor = false;
	boolean addLollipopsEditor = false;
	boolean addDonutsEditor = false;

	Controller(Model m)
	{
		model = m;
	}

	public void actionPerformed(ActionEvent e)
	{	
	}

	void setView(View v) 
	{
		view = v;
	}

	public void mousePressed(MouseEvent e)
	{
		if(addFlowersEditor)
			model.flowerClick(e.getX()+model.daisy.x-200, e.getY());
		else if(addBenchesEditor)
			model.benchClick(e.getX()+model.daisy.x-200, e.getY());
		else if(addCandiesEditor)
			model.candyClick(e.getX()+model.daisy.x-200, e.getY());
		else if(addLollipopsEditor)
			model.lollipopClick(e.getX()+model.daisy.x-200, e.getY());
		else if(addDonutsEditor)
			model.donutClick(e.getX()+model.daisy.x-200, e.getY());
	}

	public void mouseReleased(MouseEvent e) {    }
	public void mouseEntered(MouseEvent e) {    }
	public void mouseExited(MouseEvent e) {    }
	public void mouseClicked(MouseEvent e) {    }

	public void keyPressed(KeyEvent e)
	{
		switch(e.getKeyCode())
		{
			case KeyEvent.VK_RIGHT: keyRight = true; break;
			case KeyEvent.VK_LEFT: keyLeft = true; break;
			case KeyEvent.VK_UP: keyUp = true; break;
			case KeyEvent.VK_DOWN: keyDown = true; break;
			case KeyEvent.VK_SPACE: spacebar = true; break;
		}

		char letter = e.getKeyChar();
		if (letter=='s') {
			model.marshal().save("map.json");
			System.out.println("saved map.json");
		}
		
		if (letter=='l') {
			Json j = Json.load("map.json");
			model.unmarshal(j);
			System.out.println("loaded map.json");
		}
		if (letter=='q'){
			System.exit(0);
		}

		// f - toggle add flower
		if (letter=='f'){
			addFlowersEditor = !addFlowersEditor;
		}

		// b - toggle add bench
		if (letter=='b'){
			addBenchesEditor = !addBenchesEditor;
		}

		// c - toggle add candy
		if (letter=='c'){
			addCandiesEditor = !addCandiesEditor;
		}

		// l - toggle add lollipop
		if (letter=='p'){
			addLollipopsEditor = !addLollipopsEditor;
		}

		// d - toggle add donut
		if (letter=='d'){
			addDonutsEditor = !addDonutsEditor;
		}
	}

	public void keyReleased(KeyEvent e)
	{
		switch(e.getKeyCode())
		{
			case KeyEvent.VK_RIGHT: keyRight = false; break;
			case KeyEvent.VK_LEFT: keyLeft = false; break;
			case KeyEvent.VK_UP: keyUp = false; break;
			case KeyEvent.VK_DOWN: keyDown = false; break;
			case KeyEvent.VK_SPACE: spacebar = false; break;
		}
	}

	public void keyTyped(KeyEvent e)
	{
	}

	void update()
	{
		model.savePrevCoord();
		//scrolling
		if(keyRight) {
			model.daisy.x+=10;
			model.daisy.update2();
		}
		if(keyLeft) {
			model.daisy.x-=10;
			model.daisy.update2();
		}
		// if(keyDown) 
		if(keyUp || spacebar) 
			model.daisy.jump();
	}
}

