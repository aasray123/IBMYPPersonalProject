import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class personalProject extends PApplet {

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
public void setup() {
  
  background(0, 0, 0);
  frameRate(30);
}

public void draw() {
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

public void mouseClicked() {
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

class Credits{
  public void init(){
    textSize(110);
    background(0,0,0);
    fill(212,175,55);
    text("Made By: Aasray Sundar",1,350);

  }
};
//settingsMenu settingsmenut = new settingsMenu();
//class Platformer{
//  void init(){
//    //The Lower half of the screen will be keyboard if the setting is set to true
//    if(settingsmenut.keyboardShow == true){
//      withKeyboard();
//    }
//    else{
//     withoutKeyboard();
//    }
//  }
//  void withKeyboard(){
//    //720/2 = 360
//    //So height of keyboard and screen will be 360
    
//  }
  
//  void withoutKeyboard(){
    
//  }
//};
startMenu startmenuu = new startMenu();
class gameSelect {
  public void init() {
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
startMenu hover = new startMenu();
class settingsMenu {
  boolean keyboardShow = true;
  boolean keyboardType = true; //True is ANSI (with the flat enter)
  boolean followMouse = false; //For difficulty bar to follow the mouse

  int rectX = 80, charMove;
  boolean moveDifBar = false;
  public void init() {
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
    int c1, c2;
    c2 = color(255, 0, 0);
    c1 = color(0, 255, 0);
    for (int i = x; i<=x+w; i++) {
      float inter = map(i, x, x+w, 0, 1);
      int c = lerpColor(c1, c2, inter);
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
sideScroller sidescrollert = new sideScroller();
settingsMenu settingsmenur = new settingsMenu();
boolean charMovementDirection = true;
class sideScroller {
  PImage character;
  int charX = (width/40)*50, charY = (height/3), level = 0;
  int i, iii, tempScore = 0, score = 0, currentLetter, bypassInt = 0, tempMovementChar; //bypassInt used so that the for loop beneath doesnt happen on the first iteration
  int[] letters = new int[16];
  boolean runOnce = true;
  boolean goBack = false;
  boolean sideScroll = true, takeDamage = true;
  char[] alphabets = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '};
  int characterImageCycleStage = 1, timer = 0, wpmm;
  float health = 3.0f, wpm = 0;
  public void init() {

    withKeyboard(); //I did it like this for formatting purposes
  }

  public void loadCharacter() {
    letters[15] = floor(random(0, 38)); //push new number into letters array
    for (int co = 0; co<15; co++) {
      if (letters[co] >= 26 && letters[co+1] >= 26) {
        letters[co+1] = floor(random(0, 38));
      }
      if (letters[co] == 16 && !(letters[co+1] == 20)) {
        letters[co+1] = 20;
      }
    }
  }
  //----------------------------------------------------------------------------------------------------------------------------------
  public void keyboardLoader() {
    if (settingsmenur.keyboardType) {
      ANSIkeyboardLoader();
    } else {
      ISOkeyboardLoader();
    }
    fill(255, 0, 0);
    switch(alphabets[letters[0]]) {
    case 'a':
      //9 down 5 right
      rect(319, 515, 51, 50, 10);
      break;
    case 'b':
      rect(571, 571, 51, 50, 10);
      break;
    case 'c':
      rect(459, 571, 51, 50, 10);
      break;
    case 'd':
      rect(431, 515, 51, 50, 10);
      break;
    case 'e':
      rect(421, 459, 51, 50, 10);
      break;
    case 'f':
      rect(487, 515, 51, 50, 10);
      break;
    case 'g':
      rect(543, 515, 51, 50, 10);
      break;
    case 'h':
      rect(599, 515, 51, 50, 10);
      break;
    case 'i':
      rect(701, 459, 51, 50, 10);
      break;
    case 'j':
      rect(655, 515, 51, 50, 10);
      break;
    case 'k':
      rect(711, 515, 51, 50, 10);
      break;
    case 'l':
      rect(767, 515, 51, 50, 10); //28 right
      break;
    case 'm':
      rect(683, 571, 51, 50, 10);
      break;
    case 'n':
      rect(627, 571, 51, 50, 10);
      break;
    case 'o':
      rect(757, 459, 51, 50, 10);
      break;
    case 'p':
      rect(813, 459, 51, 50, 10);
      break;
    case 'q':
      rect(309, 459, 51, 50, 10);
      break;
    case 'r':
      rect(477, 459, 51, 50, 10);
      break;
    case 's':
      rect(375, 515, 51, 50, 10); //56 +
      break;
    case 't':
      rect(533, 459, 51, 50, 10);
      break;
    case 'u':
      rect(645, 459, 51, 50, 10);
      break;
    case 'v':
      rect(515, 571, 51, 50, 10);
      break;
    case 'w':
      rect(365, 459, 51, 50, 10);
      break;
    case 'x':
      rect(403, 571, 51, 50, 10);
      break;
    case 'y':
      rect(589, 459, 51, 50, 10);
      break;
    case 'z':
      rect(347, 571, 51, 50, 10);
      break;
    case ' ':
      rect(487, 627, 272, 49, 10);
      break;
    }
  }
  //----------------------------------------------------------------------------------------------------------------------------------
  public void ANSIkeyboardLoader() {
    PImage ANSI = loadImage("Images/Keyboards/ANSI/ANSI.png");
    image(ANSI, 225, 402.5f);
  }

  public void ISOkeyboardLoader() {
    PImage ISO = loadImage("Images/Keyboards/ISO/ISO.png");
    image(ISO, 225, 402.5f);
  }

  public void unloadCharacter() {
    for (int a = 0; a<15; a++) {
      letters[a] = letters[a+1]; //move number array forward
    }//remove last number
  }
  //----------------------------------------------------------------------------------------------------------------------------------
  public void withKeyboard() {
    background(255);
    PImage sky = loadImage("Images/Skies/sacreBleu.jpg");
    if (level > 0) {
      sky = loadImage("Images/Skies/sacreBleu.jpg");
    }
    if (level > 2) {
      sky = loadImage("Images/Skies/magenta.jpg");
    }
    if (level > 6) {
      sky = loadImage("Images/Skies/red.jpg");
    }
    image(sky, 0, 0);
    if (runOnce) {
      tempMovementChar = settingsmenu.charMove;
      for (int a = 0; a<16; a++) {
        letters[a] = floor(random(0, 38));
        for (int co = 0; co<15; co++) {
          if (letters[co] >= 26 && letters[co+1] >= 26) {
            letters[co+1] = floor(random(0, 38));
          }
          if (letters[co] == 16 && !(letters[co+1] == 20)) {
            letters[co+1] = 20;
          }
        }
      }
      runOnce = false;
      if (scene == 3) {
        level = 1;
      }
    }
    if (scene == 4) {
      settingsmenu.charMove = 0;
      level = 0;
    } else if (scene == 3) {
      settingsmenu.charMove = tempMovementChar;
      goBack = true;
    }

    charY = height/6;
    if (tempScore < score) {
      charX += 50;
    }
    tempScore = score;


    if (goBack) {
      charX -= settingsmenu.charMove + (level*2);
    }
    if (charX<= 0) {
      if (takeDamage) {
        switch(settingsmenu.charMove) {
        case 1:
          health -= 0.2f;
          break;
        case 2:
          health -= 0.4f;
          break;
        case 3:
          health -= 0.6f;
          break;
        case 4:
          health -= 0.8f;
          break;
        case 5:
          health -= 1.0f;
          break;
        }
        takeDamage = false;
      }
      charX = 0;
      goBack = false;
    } else if (charX >= width) {
      charX = 100;
      level += 1;
      goBack = false;
    }

    if (health <= 0) {
      scene = 7;
    }

    loadMovingGuy(characterImageCycleStage); //WHEN YOU RUN OUT OF VARIABLE NAMES

    PImage floor = loadImage("Images/floor.jpg");
    image(floor, 0, (height/6)*2);
    fill(255, 0, 0);
    image(character, charX, charY, width/40+40, height/3-40);
    if (settingsmenu.keyboardShow) {
      keyboardLoader();
    }

    timer = timer + 1;
    if (timer >= (20*7.5f)) {
      wpm = ((score/5)/((timer/7.5f)/60))*2;
      wpmm = floor(wpm);
    }
    textSize(50);
    fill(212, 175, 55);
    if (settingsmenu.keyboardShow) {
      text("WPM", 0, height/2+50);
      text(wpmm, 0, height/2+100);
    } else {
      textSize(200);
      text("WPM", width/2 - 200, height/2+150);
      text(wpmm, width/2 - 200, height/2+350);
    }
    //System.out.println(wpm + " " + score + " " + timer);
    for (i = 0; i<16; i++) {
      switch(letters[i]) {
      case 0:
        PImage a;
        a = loadImage("Images/Keys/A.png");
        image(a, i*80, 0);
        break;
      case 1:
        PImage b;
        b = loadImage("Images/Keys/B.png");
        image(b, i*80, 0);
        break;
      case 2:
        PImage c;
        c = loadImage("Images/Keys/C.png");
        image(c, i*80, 0);
        break;
      case 3:
        PImage d;
        d = loadImage("Images/Keys/D.png");
        image(d, i*80, 0);
        break;
      case 4:
        PImage e;
        e = loadImage("Images/Keys/E.png");
        image(e, i*80, 0);
        break;
      case 5:
        PImage f;
        f = loadImage("Images/Keys/F.png");
        image(f, i*80, 0);
        break;
      case 6:
        PImage g;
        g = loadImage("Images/Keys/G.png");
        image(g, i*80, 0);
        break;
      case 7:
        PImage h;
        h = loadImage("Images/Keys/H.png");
        image(h, i*80, 0);
        break;
      case 8:
        PImage ii;
        ii = loadImage("Images/Keys/I.png");
        image(ii, i*80, 0);
        break;
      case 9:
        PImage j;
        j = loadImage("Images/Keys/J.png");
        image(j, i*80, 0);
        break;
      case 10:
        PImage k;
        k = loadImage("Images/Keys/K.png");
        image(k, i*80, 0);
        break;
      case 11:
        PImage l;
        l = loadImage("Images/Keys/L.png");
        image(l, i*80, 0);
        break;
      case 12:
        PImage m;
        m = loadImage("Images/Keys/M.png");
        image(m, i*80, 0);
        break;
      case 13:
        PImage n;
        n = loadImage("Images/Keys/N.png");
        image(n, i*80, 0);
        break;
      case 14:
        PImage o;
        o = loadImage("Images/Keys/O.png");
        image(o, i*80, 0);
        break;
      case 15:
        PImage p;
        p = loadImage("Images/Keys/P.png");
        image(p, i*80, 0);
        break;
      case 16:
        PImage q;
        q = loadImage("Images/Keys/Q.png");
        image(q, i*80, 0);
        break;
      case 17:
        PImage r;
        r = loadImage("Images/Keys/R.png");
        image(r, i*80, 0);
        break;
      case 18:
        PImage s = loadImage("Images/Keys/S.png");
        image(s, i*80, 0);
        break;
      case 19:
        PImage t = loadImage("Images/Keys/T.png");
        image(t, i*80, 0);
        break;
      case 20:
        PImage u = loadImage("Images/Keys/U.png");
        image(u, i*80, 0);
        break;
      case 21:
        PImage v = loadImage("Images/Keys/V.png");
        image(v, i*80, 0);
        break;
      case 22:
        PImage w = loadImage("Images/Keys/W.png");
        image(w, i*80, 0);
        break;
      case 23:
        PImage x = loadImage("Images/Keys/X.png");
        image(x, i*80, 0);
        break;
      case 24:
        PImage y = loadImage("Images/Keys/Y.png");
        image(y, i*80, 0);
        break;
      case 25:
        PImage z = loadImage("Images/Keys/Z.png");
        image(z, i*80, 0);
        break;
      }
    }
  }

  public void loadMovingGuy(int stage) {
    switch(stage) {
    case 1:
      if (health>=2) {
        character = loadImage("Images/Characters/ppCharacter1-1.png");
      } 
      if (health>=1 && health <=2) {
        character = loadImage("Images/Characters/ppCharacter2-1.png");
      }  
      if (health > 0 && health <= 1) {
        character = loadImage("Images/Characters/ppCharacter3-1.png");
      }
      break;
    case 2:
      if (health>=2) {
        character = loadImage("Images/Characters/ppCharacter1-2.png");
      } 
      if (health>=1 && health <=2) {
        character = loadImage("Images/Characters/ppCharacter2-2.png");
      }  
      if (health > 0 && health <= 1) {
        character = loadImage("Images/Characters/ppCharacter3-2.png");
      }
      break;
    case 3:
      if (health>=2) {
        character = loadImage("Images/Characters/ppCharacter1-3.png");
      } 
      if (health>=1 && health <=2) {
        character = loadImage("Images/Characters/ppCharacter2-3.png");
      }  
      if (health > 0 && health <= 1) {
        character = loadImage("Images/Characters/ppCharacter3-3.png");
      }
      break;
    }
  }
};

//----------------------------------------------------------------------------------------------------------------------------------
public void keyPressed() {
  if (keyCode == DELETE || keyCode == BACKSPACE) {
    switch(scene) {
    case 1:
    case 2:
    case 3:
    case 4:
    case 5:
    case 6:
    case 7:
      scene = 1;
      break;
    }
  }
  //sidescroller
  if (scene == 3 || scene == 4) {
    sidescroller.goBack = true;
    if (key == PApplet.parseChar(sidescroller.alphabets[sidescroller.letters[0]])) {
      sidescroller.score += 1;
      sidescroller.unloadCharacter();
      sidescroller.loadCharacter();
      if (charMovementDirection) {
        sidescroller.characterImageCycleStage += 1;
      } else {
        sidescroller.characterImageCycleStage -= 1;
      }
      if (sidescroller.characterImageCycleStage == 4) {
        charMovementDirection = false;
        sidescroller.characterImageCycleStage = 2;
      }
      if(sidescroller.characterImageCycleStage == 0){
        charMovementDirection = true;
        sidescroller.characterImageCycleStage = 2;
      }
      sidescroller.takeDamage = true;
    } else {
      sidescroller.score -= 1;
    }
  }
}
/*Start
 * Quit
 * Settings
 * Heading =  Let's Type a Game
 * 
 */
class startMenu {
  public void init() {
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
  public boolean hover(float x, float y, float Width, float Height) {
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
  public void settings() {  size(1280, 720); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "personalProject" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
