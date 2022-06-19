package com.dinyad.zelda;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TuileManager {
    private Panel pg;
    private List<Tuile> tuiles;
    private int[][] carteTuile;

    public TuileManager(Panel pg) {

        this.pg = pg;
        tuiles = new ArrayList<Tuile>();

    }

    public void getImage(){
        String imgUrl;
        boolean collision=false;
        tuiles.clear();
        for (int i = 0; i < Panel.MAXSCREENROW; i++) {
            for (int j = 0; j <Panel.MAXSCREENCOL ; j++) {
               if(carteTuile[i][j]==TypeTuile.GRASS){
                    imgUrl = new File("resources/grass.png").toURI().toString();

               }
                else if(carteTuile[i][j]==TypeTuile.PORTE){
                    imgUrl = new File("resources/porte.png").toURI().toString();  //get img
                   collision=true;
                }
               else if(carteTuile[i][j]==TypeTuile.EAU){
                    imgUrl = new File("resources/eau.png").toURI().toString(); //get
                   collision=true;
               }
               else if(carteTuile[i][j]==TypeTuile.EARTH){
                   imgUrl = new File("resources/earth.png").toURI().toString();
               }
               else if(carteTuile[i][j]==TypeTuile.FORET){
                    imgUrl = new File("resources/foret.png").toURI().toString();
                   collision=true;
               }
               else if(carteTuile[i][j]==TypeTuile.SABLE){
                    imgUrl = new File("resources/sable.png").toURI().toString();
               }
               else if(carteTuile[i][j]==TypeTuile.CHATEAU){
                   imgUrl = new File("resources/chateau.png").toURI().toString();
               }
               else if(carteTuile[i][j]==TypeTuile.CLEF){
                   imgUrl = new File("resources/clef.png").toURI().toString();
               }


               else {
                    imgUrl = new File("resources/coffre.png").toURI().toString();
               }

                Image img = new Image(imgUrl);
                tuiles.add(new Tuile(img,collision));
                collision=false;

            }
        }

    }

    public void chargerCarte(String cartPath){
        Path chemin= Paths.get(cartPath);

        Scanner scanner=null;
        try{
            scanner = new Scanner(chemin);

        }catch(IOException e)
        {
            e.printStackTrace();
        }
        Panel.setMAXSCREENROW(scanner.nextInt());
        Panel.setMAXSCREENCOL(scanner.nextInt());
        carteTuile = new int[Panel.MAXSCREENROW][Panel.MAXSCREENCOL];
        pg.setHeight(Panel.SCREENHEIGHT);
        pg.setWidth(Panel.SCREENWIDTH);
        int i=0,j=0;
        while(scanner.hasNext())
        {
           carteTuile[i][j]=scanner.nextInt();
           i++;

           if(i==Panel.MAXSCREENCOL){
               i=0;
               j++;
           }

        }

    }

    public void paint(GraphicsContext gc){
        //int k=0;

        for (int i = 0; i < Panel.MAXSCREENROW; i++) {

            for (int j = 0; j < Panel.MAXSCREENCOL; j++) {


                gc.drawImage(tuiles.get(toArrayIndex(i,j)).getImage(false),i*Panel.TILESIZE,j*Panel.TILESIZE,Panel.TILESIZE,Panel.TILESIZE);
                //k++;
            }
        }
    }

    public int toArrayIndex(int i,int j){
        return  i*Panel.MAXSCREENROW + j;
    }

    //Distance qui separe le joueur d'un obstacle avant
    public  int distanceBeforeCollision(int posX,int posY,String direction){
        int i=posX/Panel.TILESIZE,j=posY/Panel.TILESIZE,k=-1;

        if(direction=="bas"){
            for (int l = j+1; l <Panel.MAXSCREENROW ; l++) {
                if(tuiles.get(toArrayIndex(i,l)).isCollision()){
                    k=l;
                    break;
                }
            }

            if(k!=-1)
                return k*Panel.TILESIZE-posY-Panel.TILESIZE;
            else
                return Panel.SCREENHEIGHT-posY-Panel.TILESIZE;


        }
        else if(direction=="haut"){
            for (int l = j-1; l >=0 ; l--) {
                if(tuiles.get(toArrayIndex(i,l)).isCollision()){
                    k=l;
                    break;
                }
            }

            if(k!=-1)
                return -k*Panel.TILESIZE+posY-Panel.TILESIZE;
            else
                return posY;

        }
        else if(direction=="gauche"){
            for (int l = i-1; l >=0 ; l--) {
                if(tuiles.get(toArrayIndex(l,j)).isCollision()){
                    k=l;
                    break;
                }
            }

            if(k!=-1)
                return -k*Panel.TILESIZE+posX-Panel.TILESIZE;
            else
                return posX;
        }
        else{
            for (int l = i+1; l <Panel.MAXSCREENCOL ; l++) {
                if(tuiles.get(toArrayIndex(l,j)).isCollision()){
                    k=l;
                    break;
                }
            }

            if(k!=-1)
                return k*Panel.TILESIZE-posX-Panel.TILESIZE;
            else
                return Panel.SCREENHEIGHT-posX-Panel.TILESIZE;

        }


    }

    public boolean isKey(int posX,int posY){
        int i=posX/Panel.TILESIZE,j=posY/Panel.TILESIZE,k=-1;
        if(carteTuile[i][j]==TypeTuile.CLEF){
            carteTuile[i][j]=TypeTuile.SABLE;
            getImage();
            return true;
        }

        return false;
    }
    public boolean openGate(int posX,int posY,String direction,int nbClefs){
        int i=posX/Panel.TILESIZE,j=posY/Panel.TILESIZE,k=-1,l=-1;

        if(direction=="bas" && j!=Panel.MAXSCREENROW) {
            k=i;l=j+1;

        }
        else if(direction=="haut" && j!=0){
            k=i;l=j-1;

        }
        else if(direction=="gauche" && i!=0){
            k=i-1;l=j;
        }
        else if(direction=="droit" && i!=Panel.MAXSCREENCOL){

            k=i+1;l=j;
        }
        if(k!=-1 && carteTuile[k][l]==TypeTuile.PORTE){
            if(nbClefs>0){
                carteTuile[k][l]=TypeTuile.SABLE;
                getImage();
                return true;
            }


        }

        return false;
    }

    public boolean openCoffre(int posX,int posY){
        int i=posX/Panel.TILESIZE,j=posY/Panel.TILESIZE,k=-1;
        if(carteTuile[i][j]==TypeTuile.COFFRE){
            carteTuile[i][j]=TypeTuile.SABLE;
            getImage();
            return true;
        }
        return false;
    }


}
