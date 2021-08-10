import java.util.ArrayList;
import java.util.Iterator;

class Model 
{
	ArrayList<Item> items;
	Daisy daisy;
	Score onesPlace;
	Score tensPlace;
	int count = 0;

	Model()
	{
		items = new ArrayList<Item>();

		// add daisy to list of items
		daisy = new Daisy(200,330);
		items.add(daisy);
		// add score labels
		addScoreLabels();
	}

	public void addScoreLabels()
	{
		onesPlace = new Score(80,10,"ones",this);
		items.add(onesPlace);
		tensPlace = new Score(10,10,"tens",this);
		items.add(tensPlace);
	}

	// add flower where mouse clicks, press f key to activate
	public void flowerClick(int x, int y)
	{ 
		Flower f;
		for(int i = 0; i < items.size(); i++)
		{
			if(items.get(i) instanceof Flower)
			{
				f = (Flower)items.get(i);
				if (f.didUserClickFlower(x,y)) {
					items.remove(f);
					return;
				}
			}
		}
		f = new Flower(x,y,this);
		items.add(f);
	}

	// add bench where mouse clicks, press b key to activate
	public void benchClick(int x, int y)
	{ 
		Bench b;
		for(int i = 0; i < items.size(); i++)
		{
			if(items.get(i) instanceof Bench)
			{
				b = (Bench)items.get(i);
				if (b.didUserClickBench(x,y)) {
					items.remove(b);
					return;
				}
			}
		}
		b = new Bench(x,y,this);
		items.add(b);
	}

	// add candy where mouse clicks, press c key to activate
	public void candyClick(int x, int y)
	{
		Candy c;
		for(int i = 0; i < items.size(); i++)
		{
			if(items.get(i) instanceof Candy)
			{
				c = (Candy)items.get(i);
				if (c.didUserClickCandy(x,y)) {
					items.remove(c);
					return;
				}
			}
		}
		c = new Candy(x, y, this);
		items.add(c);
	}

	// add lollipop where mouse clicks, press p key to activate
	public void lollipopClick(int x, int y)
	{
		Lollipop l;
		for(int i = 0; i < items.size(); i++)
		{
			if(items.get(i) instanceof Lollipop)
			{
				l = (Lollipop)items.get(i);
				if (l.didUserClickLollipop(x,y)) {
					items.remove(l);
					return;
				}
			}
		}
		l = new Lollipop(x, y, this);
		items.add(l);
	}

	// add donut where mouse clicks, press d key to activate
	public void donutClick(int x, int y)
	{
		Donut d;
		for(int i = 0; i < items.size(); i++)
		{
			if(items.get(i) instanceof Donut)
			{
				d = (Donut)items.get(i);
				if (d.didUserClickDonut(x,y)) {
					items.remove(d);
					return;
				}
			}
		}
		d = new Donut(x, y, this);
		items.add(d);
	}

	Json marshal()
    {
		Json ob = Json.newObject();
		Json itemsOb = Json.newObject();
		Json tempList = Json.newList();
		ob.add("items", itemsOb);
		itemsOb.add("flowers", tempList);
		for (int i = 0; i < items.size(); i++)
		{
			if(items.get(i) instanceof Flower)
			{
				Flower f = (Flower)items.get(i);
				tempList.add(f.marshal());
			}
		}
		tempList = Json.newList();
		itemsOb.add("benches", tempList);
		for (int i = 0; i < items.size(); i++)
		{
			if(items.get(i) instanceof Bench)
			{
				Bench b = (Bench)items.get(i);
				tempList.add(b.marshal());
			}
		}
		tempList = Json.newList();
		itemsOb.add("candies", tempList);
		for (int i = 0; i < items.size(); i++)
		{
			if(items.get(i) instanceof Candy)
			{
				Candy c = (Candy)items.get(i);
				tempList.add(c.marshal());
			}
		}
		tempList = Json.newList();
		itemsOb.add("lollipops", tempList);
		for (int i = 0; i < items.size(); i++)
		{
			if(items.get(i) instanceof Lollipop)
			{
				Lollipop l = (Lollipop)items.get(i);
				tempList.add(l.marshal());
			}
		}
		tempList = Json.newList();
		itemsOb.add("donuts", tempList);
		for (int i = 0; i < items.size(); i++)
		{
			if(items.get(i) instanceof Donut)
			{
				Donut d = (Donut)items.get(i);
				tempList.add(d.marshal());
			}
		}
		return ob;
	}

	void unmarshal(Json ob)
	{
		items = new ArrayList<Item>();
		items.add(daisy);
		items.add(onesPlace);
		items.add(tensPlace);
		Json jsonList = ob.get("items");
		Json flowerList = jsonList.get("flowers");
		Json benchList = jsonList.get("benches");
		Json candyList = jsonList.get("candies");
		Json lollipopList = jsonList.get("lollipops");
		Json donutList = jsonList.get("donuts");
		for(int i = 0; i < flowerList.size(); i++)
		{
			items.add(new Flower(flowerList.get(i), this));
		}
		for(int i = 0; i < benchList.size(); i++)
		{
			items.add(new Bench(benchList.get(i), this));
		}
		for(int i = 0; i < candyList.size(); i++)
		{
			items.add(new Candy(candyList.get(i), this));
		}
		for(int i = 0; i < lollipopList.size(); i++)
		{
			items.add(new Lollipop(lollipopList.get(i), this));
		}
		for(int i = 0; i < donutList.size(); i++)
		{
			items.add(new Donut(donutList.get(i), this));
		}
	}

	static boolean isThereCollision(Item a, Item b)
	{
		if(a.x + a.w < b.x)	// a's right colliding with b's left
			return false;
		if(a.x > b.x + b.w)	// a's left colliding with b's right
			return false;
		if(a.y + a.h < b.y)	// a's bottom colliding with b's top
			return false;	
		if(a.y > b.y + b.h)	// a's top colliding with b's bottom
			return false;
		return true;
	}

	void savePrevCoord()
	{
		daisy.savePrevCoord();
	}

	public void update()
	{
		for(int i = 0; i < items.size(); i++) 
		{
			Item t = items.get(i);  // polymorphism
			t.update();	
			
			// daisy and flower collision
			if(items.get(i) instanceof Flower)
			{
				Flower f = (Flower)items.get(i); 
				if(isThereCollision(daisy, f))
				{
					daisy.getOutOfItem(f);
				}
			}

			// daisy and bench collision
			if(items.get(i) instanceof Bench)
			{
				Bench b = (Bench)items.get(i); 
				if(isThereCollision(daisy, b))
				{
					daisy.getOutOfItem(b);
				}
			}

			// daisy and candy collision 
			if(items.get(i) instanceof Candy)
			{
				Candy c = (Candy)items.get(i); 
				if(isThereCollision(daisy, c))
				{
					items.remove(c);
					count += 1;
				}
			}


			// daisy and lollipop collision 
			else if(items.get(i) instanceof Lollipop)
			{
				Lollipop l = (Lollipop)items.get(i); 
				if(isThereCollision(daisy, l))
				{
					items.remove(l);
					count += 1;
				}
			}

			// daisy and donut collision 
			else if(items.get(i) instanceof Donut)
			{
				Donut d = (Donut)items.get(i); 
				if(isThereCollision(daisy, d))
				{
					items.remove(d);
					count += 1;
				}
			}


		}

	}

}