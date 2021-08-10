#!/bin/bash
set -u -e
javac Game.java View.java Controller.java Model.java Json.java Daisy.java Flower.java Bench.java Candy.java Lollipop.java Donut.java Item.java Score.java
java Game
