package com.dinyad.zelda;

import javafx.animation.AnimationTimer;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ScrollPane;
import java.util.Date;

public class MonTimer extends AnimationTimer {

   private Panel pg;
   private Panel side;
   ScrollPane scrollPane;
    private Date dt;

    public MonTimer(Panel pg,ScrollPane scrollPane,Panel side) {
        this.pg=pg;
        this.side=side;
        this.scrollPane=scrollPane;
        dt= new Date();
    }
    private  int nombreClefs=0;
    private int nombreCoffres=5;
    private int score=0;
    long startTime;




    @Override
    public void start() {
        startTime = dt.toInstant().getEpochSecond();
        super.start();
    }

    @Override
    public void handle(long l) {
        GestClavier gestClavier = pg.getGestClavier();
        Joueur joueur = pg.getJoueur();
        GraphicsContext gc = pg.getGraphicsContext2D();
        TuileManager tuileManager = pg.getTuileManager();
        int d;
        int tmp;
        dt=new Date();

        if(tuileManager.isKey(joueur.getPosX(), joueur.getPosY())){
            nombreClefs++;
            joueur.paint(gc,tuileManager);
        }

        if(tuileManager.openCoffre(joueur.getPosX(), joueur.getPosY())){
            nombreCoffres--;
            score+=20;
            if(nombreCoffres==0){
                this.stop();
            }
            joueur.paint(gc,tuileManager);
        }

        if(tuileManager.openGate(joueur.getPosX(), joueur.getPosY(),joueur.direction,nombreClefs)){
            nombreClefs--;
            joueur.paint(gc,tuileManager);
        }

        if(gestClavier.isUpPressed() ){
            joueur.setDirection("haut");
            d = tuileManager.distanceBeforeCollision(joueur.getPosX(), joueur.getPosY(), joueur.direction);
            if(d>joueur.getVitesse()) {

                joueur.update();

            }

            else if(d>0){
                tmp = joueur.getVitesse();
                joueur.setVitesse(d);
                joueur.update();
                joueur.setVitesse(tmp);
            }

            joueur.paint(gc,tuileManager);

            //Gestion scrollement automatique
            if(joueur.getPosY()<=58*7){
                scrollPane.setVvalue(0);
            }
            else if(Panel.SCREENHEIGHT - joueur.getPosY()<= 58*7){
                scrollPane.setVvalue(1);
            }
            else{
                double h=(joueur.getPosY()-58*7.0)/(Panel.SCREENHEIGHT-48*17);
                scrollPane.setVvalue(h);

            }


        }
        else if(gestClavier.isDownPressed() ){
            joueur.setDirection("bas");

            d = tuileManager.distanceBeforeCollision(joueur.getPosX(), joueur.getPosY(), joueur.direction);

            if(d>joueur.getVitesse()){
                joueur.update();

            }

            else if(d>0){
                tmp = joueur.getVitesse();
                joueur.setVitesse(d);
                joueur.update();
                joueur.setVitesse(tmp);


            }


            joueur.paint(gc,tuileManager);

            if(joueur.getPosY()<58*7){
                scrollPane.setVvalue(0);
            }
            else if(Panel.SCREENHEIGHT - joueur.getPosY()< 58*7){
                scrollPane.setVvalue(1);
            }
            else{
                double h=(joueur.getPosY()-58*7.0)/(Panel.SCREENHEIGHT-48*17);
                scrollPane.setVvalue(h);

            }

        }
        else if(gestClavier.isLeftPressed() ){
            joueur.setDirection("gauche");
            d = tuileManager.distanceBeforeCollision(joueur.getPosX(), joueur.getPosY(), joueur.direction);
            if(d>joueur.getVitesse()){
                joueur.update();

            }

            else if(d>0){
                tmp = joueur.getVitesse();
                joueur.setVitesse(d);
                joueur.update();
                joueur.setVitesse(tmp);


            }

            joueur.paint(gc,tuileManager);
            if(joueur.getPosX()<58*7){
                scrollPane.setHvalue(0);
            }
            else if(Panel.SCREENWIDTH - joueur.getPosX()< 58*7){
                scrollPane.setHvalue(1);
            }
            else{
                double h=(joueur.getPosX()-58*7.0)/(Panel.SCREENWIDTH-48*17);
                scrollPane.setHvalue(h);

            }


        }
        else if(gestClavier.isRightPressed() ){
            joueur.setDirection("droit");
            d = tuileManager.distanceBeforeCollision(joueur.getPosX(), joueur.getPosY(), joueur.direction);
            if(d>joueur.getVitesse()){
                joueur.update();
            }

            else if(d>0){
                tmp = joueur.getVitesse();
                joueur.setVitesse(d);
                joueur.update();
                joueur.setVitesse(tmp);

            }

            joueur.paint(gc,tuileManager);
            if(joueur.getPosX()<58*7){
                scrollPane.setHvalue(0);
            }
            else if(Panel.SCREENWIDTH - joueur.getPosX()< 58*7){
                scrollPane.setHvalue(1);
            }
            else{
                double h=(joueur.getPosX()-58*7.0)/(Panel.SCREENWIDTH-48*17);
                scrollPane.setHvalue(h);

            }

        }

        else if(gestClavier.isVPressed()){
            joueur.setVitesse(joueur.getVitesse()+1);
        }
        else if(gestClavier.isBPressed()){
            if(joueur.getVitesse()>2){
                joueur.setVitesse(joueur.getVitesse()-1);
            }

        }
        //Mise à jour des affichages à droite
        side.setSideElements(nombreClefs,nombreCoffres,score,dt.toInstant().getEpochSecond()-startTime,nombreCoffres==0,joueur.getVitesse());

    }


}
