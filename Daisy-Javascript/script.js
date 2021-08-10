var map = [{"items": [{"flowers": [{"x": 831,"y": 282}],"benches": [{"x": 1435,"y": 336}],"candies": [{"x": 444,"y": 398}, {"x": 613,"y": 404}, {"x": 1882,"y": 348}, {"x": 1992,"y": 396}, {"x": 2144,"y": 398}, {"x": 2284,"y": 337}], "lollipops": [{"x": 1208,"y": 399}, {"x": 1335,"y": 400}, {"x": 1480,"y": 237}, {"x": 1625,"y": 236}, {"x": 2008,"y": 152}, {"x": 2208,"y": 157}], "donuts": [{"x": 868,"y": 155}, {"x": 1246,"y": 131}, {"x": 1847,"y": 290}, {"x": 1943,"y": 362}, {"x": 2209,"y": 375}, {"x": 2315,"y": 282}, {"x": 2068,"y": 393}]}]}];

class Item
{
	constructor(x,y)
	{
		this.x = x;
		this.y = y;
	}
}

class Flower extends Item
{
	constructor(x,y,model)
	{
		super(x,y);
		this.model = model;
		this.w = 184;
		this.h = 360;
		this.canvas = document.getElementById("myCanvas");
		this.image = new Image();
		this.image.src = "flower.png";
	}
	update()
	{
	}

	draw(ctx)
	{
		ctx.drawImage(this.image, this.x - this.model.daisy.x + 200, this.y);
	}
}

class Bench extends Item
{
	constructor(x,y,model)
	{
		super(x,y);
		this.model = model;
		this.w = 258;
		this.h = 190;
		this.canvas = document.getElementById("myCanvas");
		this.image = new Image();
		this.image.src = "bench.png";
	}
	update()
	{
	}

	draw(ctx)
	{
		ctx.drawImage(this.image, this.x - this.model.daisy.x + 200, this.y);
	}
}

class Candy extends Item
{
	constructor(x,y,model)
	{
		super(x,y);
		this.model = model;
		this.w = 79;
		this.h = 70;
		this.canvas = document.getElementById("myCanvas");
		this.image = new Image();
		this.image.src = "candy.png";
	}
	update()
	{
	}

	draw(ctx)
	{
		ctx.drawImage(this.image, this.x - this.model.daisy.x + 200, this.y);
	}
}

class Lollipop extends Item
{
	constructor(x,y,model)
	{
		super(x,y);
		this.model = model;
		this.w = 48;
		this.h = 70;
		this.canvas = document.getElementById("myCanvas");
		this.image = new Image();
		this.image.src = "lollipop.png";
	}
	update()
	{
	}

	draw(ctx)
	{
		ctx.drawImage(this.image, this.x - this.model.daisy.x + 200, this.y);
	}
}

class Donut extends Item
{
	constructor(x,y,model)
	{
		super(x,y);
		this.model = model;
		this.w = 96;
		this.h = 70;
		this.canvas = document.getElementById("myCanvas");
		this.image = new Image();
		this.image.src = "donut.png";
	}
	update()
	{
	}

	draw(ctx)
	{
		ctx.drawImage(this.image, this.x - this.model.daisy.x + 200, this.y);
	}
}

class Daisy extends Item
{
	constructor(x,y)
	{
		super(x,y);
		this.w = 122;
		this.h = 180;
		this.daisyNum = 0;
		this.vert_vel = 0.0;
		this.px = 0;
		this.py = 0;
		this.frameCount = 0;

		this.canvas = document.getElementById("myCanvas");
		this.daisy_images = new Array();
		this.daisy_images[0] = new Image();
		this.daisy_images[0].src = "daisy1.png";
		this.daisy_images[1] = new Image();
		this.daisy_images[1].src = "daisy2.png";
		this.daisy_images[2] = new Image();
		this.daisy_images[2].src = "daisy3.png";
		this.daisy_images[3] = new Image();
		this.daisy_images[3].src = "daisy4.png";
		this.daisy_images[4] = new Image();
		this.daisy_images[4].src = "daisy5.png";			
	}

	draw(ctx)
	{
		ctx.drawImage(this.daisy_images[this.daisyNum], 200, this.y);
	}

	savePrevCoord()
	{
		this.px = this.x;
		this.py = this.y;
	}

	getOutOfItem(t)
	{
		if((this.x + this.w >= t.x) && (this.px + this.w < t.x))	
			this.x = t.x - this.w-1;
		else if((this.x <= t.x + t.w) && (this.px > t.x + t.w))
			this.x = t.x + t.w+1;
		else if((this.y + this.h >= t.y) && (this.py + this.h < t.y)){	
			this.y = t.y - this.h-1;
			this.frameCount = 0;
			this.vert_vel = 0;
		}
		else if((this.y <= t.y + t.h) && (this.py > t.y + t.h))
			this.y = t.y + t.h+1;
		else
			console.log("There is an error!");
	}

	jump()
	{
		if(this.frameCount === 0)
			this.vert_vel = -20;
		if(this.frameCount < 5) {
			this.vert_vel -= 5; 
		}
	}

	update()
	{
		this.frameCount++;

		// add gravity
		this.vert_vel += 2.0;
		this.y += this.vert_vel;

		// have daisy land on ground
		if(this.y > 330)
		{
			this.vert_vel = 0.0;
			this.y = 330; // snap back to the ground
			this.frameCount=0;
		}
		// add cap to how high daisy can jump
		if(this.y < 0)
		{
			this.y=0;
		}
	}

	update2()
	{
		this.daisyNum++;
		if (this.daisyNum > 4)
			this.daisyNum = 0;
	}
}

class Score extends Item 
{
	constructor(x,y,place,model)
	{
		super(x,y);
		this.place = place;
		this.model = model;
		this.scoreNum = 0;
		this.canDraw = false;
		this.score = "";

		this.canvas = document.getElementById("myCanvas");
		this.num = new Array();
		this.num[0] = new Image();
		this.num[0].src = "0.png";
		this.num[1] = new Image();
		this.num[1].src = "1.png";
		this.num[2] = new Image();
		this.num[2].src = "2.png";
		this.num[3] = new Image();
		this.num[3].src = "3.png";
		this.num[4] = new Image();
		this.num[4].src = "4.png";
		this.num[5] = new Image();
		this.num[5].src = "5.png";
		this.num[6] = new Image();
		this.num[6].src = "6.png";
		this.num[7] = new Image();
		this.num[7].src = "7.png";
		this.num[8] = new Image();
		this.num[8].src = "8.png";
		this.num[9] = new Image();
		this.num[9].src = "9.png";
	}

	draw(ctx)
	{
		ctx.drawImage(this.num[this.scoreNum], this.x, this.y);
	}

	update()
	{
		if(this.place === "ones")
			this.canDraw = true;
		if(this.place === "tens" && this.model.count > 9)
			this.canDraw = true;
		this.score = (this.model.count).toString();
		const numarr = this.score.split("");
		if(this.place === "ones")
		{
			var onesNum = parseInt(numarr[numarr.length-1]);
			this.scoreNum = onesNum;
		}
		else if(this.place === "tens" && numarr.length > 1)
		{
			var tensNum = parseInt(numarr[numarr.length-2]);
			this.scoreNum = tensNum;
		}
	}

}

class Model
{
	constructor()
	{
		this.items = new Array();
		this.daisy = new Daisy(200, 330);
		this.items.push(this.daisy);
		this.onesPlace = new Score(80,10,"ones",this);
		this.items.push(this.onesPlace);
		this.tensPlace = new Score(10,10,"tens",this);
		this.items.push(this.tensPlace);
		this.count = 0;

		// Flowers
		for(let i = 0; i < map[0].items[0].flowers.length; i++)
		{
			this.items.push(new Flower(map[0].items[0].flowers[i].x, map[0].items[0].flowers[i].y, this))
		}

		// Benches
		for(let i = 0; i < map[0].items[0].benches.length; i++)
		{
			this.items.push(new Bench(map[0].items[0].benches[i].x, map[0].items[0].benches[i].y, this))
		}

		// Candies
		for(let i = 0; i < map[0].items[0].candies.length; i++)
		{
			this.items.push(new Candy(map[0].items[0].candies[i].x, map[0].items[0].candies[i].y, this))
		}

		// Lollipops
		for(let i = 0; i < map[0].items[0].lollipops.length; i++)
		{
			this.items.push(new Lollipop(map[0].items[0].lollipops[i].x, map[0].items[0].lollipops[i].y, this))
		}

		// Donuts
		for(let i = 0; i < map[0].items[0].donuts.length; i++)
		{
			this.items.push(new Donut(map[0].items[0].donuts[i].x, map[0].items[0].donuts[i].y, this))
		}
	}

	isThereCollision(a, b)
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

	savePrevCoord()
	{
		this.daisy.savePrevCoord();
	}

	update()
	{
		for(let i = 0; i < this.items.length; i++)
		{
			this.items[i].update();

			// daisy and flower collision
			if(this.items[i] instanceof Flower)
			{
				if(this.isThereCollision(this.daisy, this.items[i]))
				{
					this.daisy.getOutOfItem(this.sprites[i]);
				}
			}

			// daisy and bench collision
			if(this.items[i] instanceof Bench)
			{
				if(this.isThereCollision(this.daisy, this.items[i]))
				{
					this.daisy.getOutOfItem(this.sprites[i]);
				}
			}

			// daisy and candy collision
			if(this.items[i] instanceof Candy)
			{
				if(this.isThereCollision(this.daisy, this.items[i]))
				{
					this.items.splice(i, 1);
					this.count += 1;
				}
			}

			// daisy and lollipop collision
			else if(this.items[i] instanceof Lollipop)
			{
				if(this.isThereCollision(this.daisy, this.items[i]))
				{
					this.items.splice(i, 1);
					this.count += 1;
				}
			}

			// daisy and donut collision
			else if(this.items[i] instanceof Donut)
			{
				if(this.isThereCollision(this.daisy, this.items[i]))
				{
					this.items.splice(i, 1);
					this.count += 1;
				}
			}
		}
	}
}

class View
{
	constructor(controller, model){
		this.model = model;
		this.canvas = document.getElementById("myCanvas");
		this.image = new Image();
		this.image.src = "bkgd.png";
	}

	update()
	{
		var ctx = this.canvas.getContext("2d");
		ctx.drawImage(this.image, 0, 0);
		for(let i = 0; i < this.model.items.length; i++)
		{
			let t = this.model.items[i];
			t.draw(ctx)
		}
	}
}

class Controller
{
	constructor(model)
	{
		this.model = model;
		this.key_right = false;
		this.key_left = false;
		this.key_up = false;
		this.key_down = false;
		this.spacebar = false;
		let self = this;
		document.addEventListener('keydown', function(event) { self.keyDown(event); }, false);
		document.addEventListener('keyup', function(event) { self.keyUp(event); }, false);
	}

	keyDown(event)
	{
		if(event.keyCode == 39) this.key_right = true;
		else if(event.keyCode == 37) this.key_left = true;
		else if(event.keyCode == 38) this.key_up = true;
		else if(event.keyCode == 40) this.key_down = true;
		else if(event.keyCode == 32) this.spacebar = true;
		else if(event.keyCode == 17) this.ctrl = true;
	}

	keyUp(event)
	{
		if(event.keyCode == 39) this.key_right = false;
		else if(event.keyCode == 37) this.key_left = false;
		else if(event.keyCode == 38) this.key_up = false;
		else if(event.keyCode == 40) this.key_down = false;
		else if(event.keyCode == 32) this.spacebar = false;
	}

	update()
	{
		this.model.savePrevCoord();
		if(this.key_right) {
			this.model.daisy.x+=10;
			this.model.daisy.update2();
		}
		if(this.key_left) {
			this.model.daisy.x-=10;
			this.model.daisy.update2();
		}
		if(this.key_up || this.spacebar){
			this.model.daisy.jump();
		}
	}
}

class Game
{
	constructor()
	{
		this.model = new Model();
		this.controller = new Controller(this.model);
		this.view = new View(this.controller, this.model);
		
	}

	onTimer()
	{
		this.controller.update();
		this.model.update();
		this.view.update();
	}

}

let game = new Game();
let timer = setInterval(function() { game.onTimer(); }, 40);
















