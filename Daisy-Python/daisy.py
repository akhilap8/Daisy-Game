import pygame
import time
from abc import ABC, abstractmethod

from pygame.locals import*
from time import sleep


class Item(ABC):
	def __init__(self,x, y):
		self.x = x
		self.y = y

	@abstractmethod
	def update(self):
		pass
	@abstractmethod
	def draw(self):
		pass

class Donut(Item):
	def __init__(self, x, y, model):
		Item.__init__(self, x, y)
		self.model = model
		self.w = 96
		self.h = 70
		self.image = pygame.image.load("donut.png")

	def update(self):
		pass

	def draw(self, screen):
		screen.blit(self.image, (self.x - self.model.daisy.x + 200, self.y))

class Lollipop(Item):
	def __init__(self, x, y, model):
		Item.__init__(self, x, y)
		self.model = model
		self.w = 48
		self.h = 70
		self.image = pygame.image.load("lollipop.png")

	def update(self):
		pass

	def draw(self, screen):
		screen.blit(self.image, (self.x - self.model.daisy.x + 200, self.y))

class Candy(Item):
	def __init__(self, x, y, model):
		Item.__init__(self, x, y)
		self.model = model
		self.w = 79
		self.h = 70
		self.image = pygame.image.load("candy.png")

	def update(self):
		pass

	def draw(self, screen):
		screen.blit(self.image, (self.x - self.model.daisy.x + 200, self.y))

class Bench(Item):
	def __init__(self, x, y, model):
		Item.__init__(self, x, y)
		self.model = model
		self.w = 258
		self.h = 190
		self.image = pygame.image.load("bench.png")

	def update(self):
		pass

	def draw(self, screen):
		screen.blit(self.image, (self.x - self.model.daisy.x + 200, self.y))

class Flower(Item):
	def __init__(self, x, y, model):
		Item.__init__(self, x, y)
		self.model = model
		self.w = 184
		self.h = 360
		self.image = pygame.image.load("flower.png")

	def update(self):
		pass

	def draw(self, screen):
		screen.blit(self.image, (self.x - self.model.daisy.x + 200, self.y))

class Daisy(Item):
	def __init__(self, x, y):
		Item.__init__(self, x, y)
		self.w = 122
		self.h = 180
		self.daisyNum = 0
		self.vert_vel = 0.0
		self.frameCount = 0
		self.px = 0 
		self.py = 0 

		self.daisy_images = [] 
		self.pics = ["daisy1.png", "daisy2.png", "daisy3.png", "daisy4.png", "daisy5.png"]
		for i in range(5):
			self.daisy_images.append(pygame.image.load(self.pics[i]))
	
	def draw(self, screen):
		screen.blit(self.daisy_images[self.daisyNum], (200, self.y))

	# saves Daisy's previous position prior to colliding tube
	def savePrevCoord(self):
		self.px = self.x
		self.py = self.y

	def getOutOfItem(self, t):
		if ((self.x + self.w >= t.x) and (self.px + self.w < t.x)):	
			self.x = t.x - self.w-1
		elif ((self.x <= t.x + t.w) and (self.px > t.x + t.w)):	
			self.x = t.x + t.w+1
		elif ((self.y + self.h >= t.y) and (self.py + self.h < t.y)):	
			self.y = t.y - self.h-1
			self.frameCount = 0
			self.vert_vel = 0
		elif ((self.y <= t.y + t.h) and (self.py > t.y + t.h)):	
			self.y = t.y + t.h+1
		else:
			print("There is an error!")

	# make daisy jump
	def jump(self):
		if (self.frameCount == 0):
			self.vert_vel = -20
		if (self.frameCount < 5):
			self.vert_vel -= 5


	def update(self):
		self.frameCount += 1

		# add gravity
		self.vert_vel += 2.0
		self.y += self.vert_vel

		# have daisy land on ground
		if (self.y > 330):
			self.vert_vel = 0.0
			self.y = 330 # snap back to the ground
			self.frameCount=0
		
		# how high daisy can jump (can't go off the screen
		if (self.y < 0):
			self.y=0
		
	def update2(self):
		self.daisyNum += 1
		if (self.daisyNum > 4):
			self.daisyNum = 0

class Score(Item):
	def __init__(self, x, y, place, model):
		Item.__init__(self, x, y)
		self.place = place
		self.model = model
		self.scoreNum = 0
		self.canDraw = False
		self.score = ''

		self.num = [] 
		self.pics = ["0.png", "1.png", "2.png", "3.png", "4.png", "5.png", "6.png", "7.png", "8.png", "9.png" ]
		for i in range(10):
			self.num.append(pygame.image.load(self.pics[i]))
	
	def draw(self, screen):
		screen.blit(self.num[self.scoreNum], (self.x, self.y))

	def update(self):
		if (self.place == "ones"):
			self.canDraw = True
		if (self.place == "tens" and self.model.count > 9):
			self.canDraw = True
		self.score = str(self.model.count)
		numarr = list(self.score)

		if (self.place == 'ones'):
			onesNum = int(numarr[len(numarr)-1])
			self.scoreNum = onesNum

		elif (self.place == 'tens' and len(numarr) > 1):
			if (self.model.count > 9):
				tensNum = int(numarr[len(numarr)-2])
				self.scoreNum = tensNum

class Model():
	def __init__(self):
		self.items = []
		self.daisy = Daisy(200,330)
		self.items.append(self.daisy)
		self.onesPlace = Score(80,10,'ones',self)
		self.items.append(self.onesPlace)
		self.tensPlace = Score(10,10,'tens',self)
		self.items.append(self.tensPlace)
		self.count = 0

		# Flowers
		self.flowers = [(831,282)]
		for i in range(len(self.flowers)):
			self.items.append(Flower(self.flowers[i][0], self.flowers[i][1], self))

		# Benches
		self.benches = [(1435,336)]
		for i in range(len(self.benches)):
			self.items.append(Bench(self.benches[i][0], self.benches[i][1], self))

		# Candies
		self.candies = [(444,398), (613,404), (1882,348), (1992,396), (2144,398), (2284,337)]
		for i in range(len(self.candies)):
			self.items.append(Candy(self.candies[i][0], self.candies[i][1], self))

		# Lollipops
		self.lollipops = [(1208,399), (1335,400), (1480,237), (1625,236), (2008,152), (2208,157)]
		for i in range(len(self.lollipops)):
			self.items.append(Lollipop(self.lollipops[i][0], self.lollipops[i][1], self))

		# Donuts
		self.donuts = [(868,155), (1246,131), (1847,290), (1943,362), (2209,375), (2315,282), (2068,393)]
		for i in range(len(self.donuts)):
			self.items.append(Donut(self.donuts[i][0], self.donuts[i][1], self))

	def isThereCollision(self, a, b):
		if (a.x + a.w < b.x):	# a's right colliding with b's left
			return False
		if (a.x > b.x + b.w):	# a's left colliding with b's right
			return False
		if (a.y + a.h < b.y):	# a's bottom colliding with b's top
			return False	
		if (a.y > b.y + b.h):	# a's top colliding with b's bottom
			return False
		return True

	def savePrevCoord(self):
		self.daisy.savePrevCoord()

	def update(self):
		for i in self.items:
			i.update()

			# daisy and flower collision
			if (isinstance(i, Flower)):
				if (self.isThereCollision(self.daisy, i)):
					self.daisy.getOutOfItem(i)

			# daisy and Bench collision
			if (isinstance(i, Bench)):
				if (self.isThereCollision(self.daisy, i)):
					self.daisy.getOutOfItem(i)	

			# daisy and Candy collision
			if (isinstance(i, Candy)):
				if (self.isThereCollision(self.daisy, i)):
					self.items.remove(i)
					self.count += 1

			# daisy and Lollipop collision
			elif (isinstance(i, Lollipop)):
				if (self.isThereCollision(self.daisy, i)):
					self.items.remove(i)
					self.count += 1

			# daisy and Donut collision
			if (isinstance(i, Donut)):
				if (self.isThereCollision(self.daisy, i)):
					self.items.remove(i)
					self.count += 1

class View():
	def __init__(self, model):
		screen_size = (1140,600)
		self.screen = pygame.display.set_mode(screen_size, 32)
		self.model = model
		self.image = pygame.image.load("bkgd.png")

	def update(self):
		# self.screen.fill([128, 255, 255])
		# pygame.draw.rect(self.screen, (160, 110, 0), pygame.Rect(0,396,1000,500))

		self.screen.blit(self.image, (0, 0))

		for i in range(len(self.model.items)):
			s = self.model.items[i]
			s.draw(self.screen)
		pygame.display.flip()

class Controller():
	def __init__(self, model):
		self.model = model
		self.keep_going = True

	def update(self):
		self.model.savePrevCoord()

		for event in pygame.event.get():
			if event.type == QUIT:
				self.keep_going = False
			elif event.type == KEYDOWN:
				if event.key == K_ESCAPE:
					self.keep_going = False
		keys = pygame.key.get_pressed()
		
		if keys[K_RIGHT]:
			self.model.daisy.x+=10
			self.model.daisy.update2()
		if keys[K_LEFT]:
			self.model.daisy.x-=10
			self.model.daisy.update2()
		if keys[K_UP] or keys[K_SPACE]:
			self.model.daisy.jump()
		
print("arrow keys: move, Esc: quit")
pygame.init()
m = Model()
v = View(m)
c = Controller(m)
while c.keep_going:
	c.update()
	m.update()
	v.update()
	sleep(0.04)
print("Bye!")

