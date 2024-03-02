startMenu startmenuu = new startMenu();
class gameSelect {
  void init() {
    PImage background = loadImage("Images/gameSelectBackground.jpg");
    image(background, 0, 0);
    if (startmenu.hover(width/2+20, 32, 620, 656)) {
      fill(0, 0, 0, 190);
      rect(width/2, 12, 900, 696, 40);
    } else if (startmenu.hover(0, 32, 620, 656)) {
      fill(0, 0, 0, 190);
      rect(-30, 12, 670, 696, 40);
    }
    PImage platformer;
    platformer = loadImage("Images/platformerLogo.png");
    image(platformer, 0, 32);
    PImage sidescroller;
    sidescroller = loadImage("Images/sidescrollerLogo.png");
    image(sidescroller, width/2+20, 32);
  }
};
