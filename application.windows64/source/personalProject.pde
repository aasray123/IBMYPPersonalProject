startMenu startmenu = new startMenu();
sideScroller sidescroller = new sideScroller();
settingsMenu settingsmenu = new settingsMenu();
gameSelect gameselect = new gameSelect();
Credits credits = new Credits();
int scene = 1;
//RGB of metallic gold = (212,175,55)
/* 1 = startMenu
 * 2 = settings
 * 3 = side scroller
 * 4 = platformer
 * 5 = Game Select
 * 6 = Credits
 * 7 = Game Over
 */
void setup() {
  size(1280, 720);
  background(0, 0, 0);
  frameRate(30);
}

void draw() {
  clear();
  switch(scene) {

  case 1:
    //Start Menu
    startmenu.init();
    break;

  case 2:
    //Settings
    settingsmenu.init();
    break;

  case 3:
    //Side Scroller
    sidescroller.sideScroll = true;
    sidescroller.init();
    break;

  case 4:
    //Platformer
    sidescroller.sideScroll = false;
    sidescroller.init();
    break;

  case 5:
    //Game Mode Select
    gameselect.init();
    break;

  case 6:
    //Credits
    credits.init();
    break;

  case 7:
    //Game over
    PImage gameOver = loadImage("Images/gameOver.jpg");
    image(gameOver, 0, 0, 1280, 720);
  }
}

void mouseClicked() {
  // Heading/Credits
  if (scene == 1) {
    if (startmenu.hover(200, 32, 880, 192)) {
      scene = 6;
    }                                 
    //Play
    else if (startmenu.hover(280, 256, 720, 4*32)) {
      scene = 5;
    }
    //Quit
    else if (startmenu.hover(20*40, 13*32, 5*40, 4*32)) {
      exit();
    }
    //Settings
    else if (startmenu.hover(280, 13*32, 12*40, 4*32)) {
      scene = 2;
    }
  } else if (scene == 6) {
    if (startmenu.hover(1, 1, 200, 200)) {
      scene = 1;
    }
  }

  //Game Selectr
  else if (scene == 5) {
    if (startmenu.hover(width/2+20, 32, width/2-20, height-64)) { //width/2+20, 32, width/2-20, height-64
      sidescroller.charX = 10;
      sidescroller.goBack = true;
      sidescroller.level = 1;
      sidescroller.timer = 0; 
      sidescroller.score = 0;
      sidescroller.health = 3;
      scene = 3;
      sidescroller.runOnce = true;
    }
    if (startmenu.hover(0, 32, width/2-20, height-64)) { //Make this Platformer
      scene = 4;
      sidescroller.runOnce = true;
    }// Settings
  } else if (scene == 2) {
    if (startmenu.hover(1, 61, 389, 100)) {
      settingsmenu.keyboardShow = !settingsmenu.keyboardShow;
    }
    if (startmenu.hover(450, 61, 830, 275)) {
      settingsmenu.keyboardType = !settingsmenu.keyboardType;
    }
    if (settingsmenu.moveDifBar) {
      settingsmenu.moveDifBar = !settingsmenu.moveDifBar;
    } else if (startmenu.hover(settingsmenu.rectX, 506, 20, 120)) {
      settingsmenu.moveDifBar = !settingsmenu.moveDifBar;
    }
  }
}
