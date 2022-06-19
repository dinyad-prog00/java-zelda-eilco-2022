package com.dinyad.zelda;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

import java.io.File;

public class Joueur {
    protected  int vitesse ;
    protected  int posX;
    protected  int posY;
    protected  String direction;
    protected Image up1, up2, down1, down2, right1, right2, left1, left2;

    private boolean etat;
    private  int nb;
    private  final int nbDplcmt = 5;

    public Joueur() {
        vitesse = 10;
        posX = Panel.TILESIZE*7;
        posY = Panel.TILESIZE*7;
        direction = "bas";
        etat=false;
        nb=0;

    }

    public  void setDefaultValues(){
            String up1Url = new File("resources/boy_up_1.png").toURI().toString();
            String down1Url = new File("resources/boy_down_1.png").toURI().toString();
            String rigth1Url = new File("resources/boy_right_1.png").toURI().toString();
            String left1Url = new File("resources/boy_left_1.png").toURI().toString();

            String up2Url = new File("resources/boy_up_2.png").toURI().toString();
            String down2Url = new File("resources/boy_down_2.png").toURI().toString();
            String rigth2Url = new File("resources/boy_right_2.png").toURI().toString();
            String left2Url = new File("resources/boy_left_2.png").toURI().toString();

            up1 = new Image(up1Url);
            down1 = new Image(down1Url);
            right1 = new Image(rigth1Url);
            left1 = new Image(left1Url);

            up2 = new Image(up2Url);
            down2 = new Image(down2Url);
            right2 = new Image(rigth2Url);
            left2 = new Image(left2Url);

    }

    public  void update(){
        if(direction=="bas"){
            posY = posY+vitesse;
        }
        else if(direction=="haut"){
            posY = posY-vitesse;
        }
        else if(direction=="gauche"){
            posX = posX-vitesse;
        }
        else {
            posX = posX+vitesse;
        }

        //Gestion apparence joueur
        if(0<=nb && nb <= nbDplcmt-1)
            etat=false;
        else if(nbDplcmt<=nb && nb <= 2*nbDplcmt-1)
            etat=true;
        else if(nb==2*nbDplcmt)
            nb=0;
        nb++;

    }

    public void  repaint(GraphicsContext gc,TuileManager tuileManager){


        gc.setFill(Color.WHITE);
        gc.fillRect(0,0,Panel.SCREENWIDTH,Panel.SCREENHEIGHT);

        tuileManager.paint(gc);

    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public  void paint(GraphicsContext gc,TuileManager tuileManager){

        repaint(gc,tuileManager);
        if(direction=="bas"){
            if(etat)
            gc.drawImage(down1,posX,posY,Panel.TILESIZE,Panel.TILESIZE);
            else
                gc.drawImage(down2,posX,posY,Panel.TILESIZE,Panel.TILESIZE);
        }
        if(direction=="haut"){
            if(etat)
            gc.drawImage(up1,posX,posY,Panel.TILESIZE,Panel.TILESIZE);
            else
                gc.drawImage(up2,posX,posY,Panel.TILESIZE,Panel.TILESIZE);
        }
        if(direction=="droit"){
            if(etat)
            gc.drawImage(right1,posX,posY,Panel.TILESIZE,Panel.TILESIZE);
            else
                gc.drawImage(right2,posX,posY,Panel.TILESIZE,Panel.TILESIZE);
        }if(direction=="gauche"){
            if(etat)
            gc.drawImage(left1,posX,posY,Panel.TILESIZE,Panel.TILESIZE);
            else
                gc.drawImage(left2,posX,posY,Panel.TILESIZE,Panel.TILESIZE);
        }


    }

    public int getPosY() {
        return posY;
    }

    public int getPosX() {
        return posX;
    }

    public int getVitesse() {
        return vitesse;
    }

    public void setVitesse(int vitesse) {
        this.vitesse = vitesse;
    }

}
