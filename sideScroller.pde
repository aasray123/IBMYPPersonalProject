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
  float health = 3.0, wpm = 0;
  void init() {

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
    image(ANSI, 225, 402.5);
  }

  public void ISOkeyboardLoader() {
    PImage ISO = loadImage("Images/Keyboards/ISO/ISO.png");
    image(ISO, 225, 402.5);
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
          health -= 0.2;
          break;
        case 2:
          health -= 0.4;
          break;
        case 3:
          health -= 0.6;
          break;
        case 4:
          health -= 0.8;
          break;
        case 5:
          health -= 1.0;
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
    if (timer >= (20*7.5)) {
      wpm = ((score/5)/((timer/7.5)/60))*2;
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

  void loadMovingGuy(int stage) {
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
void keyPressed() {
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
    if (key == char(sidescroller.alphabets[sidescroller.letters[0]])) {
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
