/*Start
 * Quit
 * Settings
 * Heading =  Let's Type a Game
 * 
 */
class startMenu {
  void init() {
    PImage img;
    img = loadImage("Images/startMenuBack.jpg");
    image(img, 0, 0);
    //------------------------------------------------------------
    //Heading Box
    if(hover(200,32,880,192)){
      fill(255, 255, 255);
    }
    else{
      fill(0, 0, 0);
    }
    rect(200, 32, 880, 192);
    textSize(100);
    fill(212, 175, 55);
    text("Let's Type a Game", 200, 172);

    //------------------------------------------------------------
    //Play Box
    if(hover(280,256,720,4*32)){
      fill(255, 255, 255);
    }
    else{
      fill(0, 0, 0);
    }
    rect(280, 256, 720, 4*32); 
    textSize(100);
    fill(212, 175, 55);
    text("Play", 550, 350);

    //------------------------------------------------------------
    //Setting Box
    if(hover(280,13*32,12*40,4*32)){
      fill(255, 255, 255);
    }
    else{
      fill(0, 0, 0);
    }
    rect(280, 13*32, 12*40, 4*32); 
    textSize(100);
    fill(212, 175, 55);
    text("Settings", 300, 516);

    //------------------------------------------------------------
    //Quit Box
    if(hover(20*40,13*32,5*40,4*32)){
      fill(255, 255, 255);
    }
    else{
      fill(0, 0, 0);
    }
    rect(20*40, 13*32, 5*40, 4*32); 
    textSize(100);
    fill(212, 175, 55);
    text("Quit", 800, 516);
  }
  boolean hover(float x, float y, float Width, float Height) {
    if(mouseX>=x && mouseX<=(x+Width)){
      if(mouseY>=y && mouseY<=(y+Height)){
        return true;
      }
      else {
        return false;
      }
    }
    else{
      return false;
    }
  }
};
