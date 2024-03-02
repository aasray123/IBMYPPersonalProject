startMenu hover = new startMenu();
class settingsMenu {
  boolean keyboardShow = true;
  boolean keyboardType = true; //True is ANSI (with the flat enter)
  boolean followMouse = false; //For difficulty bar to follow the mouse

  int rectX = 80, charMove;
  boolean moveDifBar = false;
  void init() {
    // KeyboardShow
    textSize(50);
    PImage ayoMa = loadImage("Images/settingsMenuBack.jpg");
    image(ayoMa, 0, 0);
    fill(212, 175, 55);
    text("Keyboard Image", 1, 50);
    if (keyboardShow == true) {
      fill(0, 255, 0);
    } else {
      fill(255, 0, 0);
    }
    rect(1, 61, 389, 100);

    //KeyboardType


    fill(212, 175, 55);
    if (keyboardType) {
      text("ANSI", 835, 50);
      PImage ANSI = loadImage("Images/Keyboards/ANSI/ANSI.png");
      image(ANSI, 450, 61);
    } else {
      text("ISO", 835, 50);
      PImage ISO = loadImage("Images/Keyboards/ISO/ISO.png");
      image(ISO, 450, 61);
    }


    //Difficulty
    text("Difficulty", 500, 495);

    float w = 1191-70, h = 50;//width and height of the difficulty bar respectively
    int x =80, y =536;//x and y coords of the gradient bar respectively
    color c1, c2;
    c2 = color(255, 0, 0);
    c1 = color(0, 255, 0);
    for (int i = x; i<=x+w; i++) {
      float inter = map(i, x, x+w, 0, 1);
      color c = lerpColor(c1, c2, inter);
      stroke(c);
      line(i, y, i, y+h);
    }
    fill(255, 255, 255);
    stroke(140);
    rect(rectX, 506, 20, 120);
    if (moveDifBar) {
      rectX = mouseX;
      if (rectX+20 >= x+(1191-70)) {
        rectX = x+(1191-70)-20;
      }
      if (rectX <= x) {
        rectX = x;
      }
    }
    if (rectX >= 80 && rectX <= 208) {
      charMove = 1;
    }
    if (rectX > 208 && rectX <= 496) {
      charMove = 2;
    }
    if (rectX > 496 && rectX <= 704) {
      charMove = 3;
    }
    if (rectX > 704 && rectX <= 912) {
      charMove = 4;
    }
    if (rectX > 912) {
      charMove = 5;
    }
  }
};
