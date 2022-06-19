package com.dinyad.zelda;

import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.io.File;

public class Panel extends Canvas {
    public final static int TILESIZE = 48;
    public   static int MAXSCREENCOL =30;
    public   static int MAXSCREENROW = 30;
    public final static int SCREENWIDTH = TILESIZE* MAXSCREENCOL;
    public final static int SCREENHEIGHT = TILESIZE* MAXSCREENROW;

    private GraphicsContext gc ;
    private Scene scene;
    private  GestClavier gestClavier;

    private Joueur joueur;

    private TuileManager tuileManager;
    private MonTimer timer;

    public  Panel(int w,int h){
        super(w,h);
        gc = this.getGraphicsContext2D();


    }

    public void setDefaultValues(Scene scene, ScrollPane scrollPane,Panel side){
        double height = this.getHeight();
        double width = this.getWidth();

        for(int i = 0; i < width; i += 48)
            gc.strokeLine(0, i, width, i);
        for(int i = 0; i < width; i += 48)
            gc.strokeLine(i, 0, i, height);
        this.scene = scene;
        gestClavier = new GestClavier(scene);

        tuileManager = new TuileManager(this);

        joueur = new Joueur();
        timer = new MonTimer(this,scrollPane,side);

    }

    public void start(String carte){

        tuileManager.chargerCarte(carte);
        tuileManager.getImage();
        tuileManager.paint(gc);
        joueur.setDefaultValues();
        joueur.paint(gc,tuileManager);
        timer.start();
    }



    public GestClavier getGestClavier() {
        return gestClavier;
    }

    public Joueur getJoueur() {
        return joueur;
    }

    public TuileManager getTuileManager() {
        return tuileManager;
    }

    public MonTimer getTimer() {
        return timer;
    }

    public static void setMAXSCREENCOL(int MAXSCREENCOL) {
        Panel.MAXSCREENCOL = MAXSCREENCOL;
    }

    public static void setMAXSCREENROW(int MAXSCREENROW) {
        Panel.MAXSCREENROW = MAXSCREENROW;
    }


    //Methodes pour cléer les elements à doite de la scene (sans creer un autre classe)
    public void setSideElements(int nbClef,int nbCoffre,int score,long duree,boolean gagne,int vitesse){
        gc.setFill(Color.WHITE);
        gc.fillRect(0,0,100,600);

        String img1Url = new File("resources/clef.png").toURI().toString();
        String img2Url = new File("resources/coffre.png").toURI().toString();
        Image img1 = new Image(img1Url);
        Image img2 = new Image(img2Url);

        gc.setFill(Color.ORANGE);
        gc.fillText("Vous avez",20,70);
        gc.setFill(Color.BLACK);
        gc.fillText(String.valueOf(nbClef),20,104);
        gc.drawImage(img1,50,80);

        gc.setFill(Color.ORANGE);
        gc.fillText("A trouver",20,150);
        gc.setFill(Color.BLACK);
        gc.fillText(String.valueOf(nbCoffre),20,180);
        gc.drawImage(img2,50,160);

        gc.setFill(Color.ORANGE);
        gc.fillText("Score",20,230);
        gc.setFill(Color.BLACK);
        gc.fillText(String.valueOf(score),20,250);

        gc.setFill(Color.ORANGE);
        gc.fillText("Durée",20,300);
        gc.setFill(Color.BLACK);
        gc.fillText(duree+" s",20,320);

        gc.setFill(Color.ORANGE);
        gc.fillText("Vitesse : ",20,390);
        gc.setFill(Color.BLACK);
        gc.fillText(String.valueOf(vitesse),20,410);

        gc.fillText("+/- vitesse",20,430);
        gc.fillText("Touche V/B",20,450);

        if(gagne){
            gc.setFill(Color.DARKGREEN);
            gc.fillText("BRAVOO !!!",10,500);
        }
    }

}
