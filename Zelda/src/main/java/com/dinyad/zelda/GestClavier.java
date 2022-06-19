package com.dinyad.zelda;

import       javafx.event.EventHandler;
import       javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import      javafx.scene.input.KeyEvent;


public class GestClavier {
    private Scene scene;
    private boolean upPressed, downPressed, rightPressed, leftPressed,vPressed,bPressed;
    public GestClavier(Scene scene) {
        this.scene = scene;
        this.gestionClavier();;
    }

    public void gestionClavier() {
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                System.out.println(keyEvent.getCode());
                if (keyEvent.getCode() == KeyCode.UP) {
                    System.out.println("up");
                    upPressed = true;
                }
                if (keyEvent.getCode() == KeyCode.DOWN)
                    downPressed = true;
                if (keyEvent.getCode() == KeyCode.LEFT)
                    leftPressed = true;
                if (keyEvent.getCode() == KeyCode.RIGHT)
                    rightPressed = true;
                if (keyEvent.getCode() == KeyCode.V)
                    vPressed = true;
                if (keyEvent.getCode() == KeyCode.B)
                    bPressed = true;
            }
        });

        scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                KeyCode code = keyEvent.getCode();
                if (keyEvent.getCode() == KeyCode.UP)
                    upPressed = false;
                if (keyEvent.getCode() == KeyCode.DOWN)
                    downPressed = false;
                if (keyEvent.getCode() == KeyCode.LEFT)
                    leftPressed = false;
                if (keyEvent.getCode() == KeyCode.RIGHT)
                    rightPressed = false;
                if (keyEvent.getCode() == KeyCode.V)
                    vPressed = false;
                if (keyEvent.getCode() == KeyCode.B)
                    bPressed = false;
            }
        });
    }
    public boolean isUpPressed() {
        return upPressed;
    }
    public boolean isDownPressed() {
        return downPressed;
    }
    public boolean isRightPressed() {
        return rightPressed;
    }
    public boolean isLeftPressed() {
        return leftPressed;
    }
    public boolean isVPressed() {
        return vPressed;
    }
    public boolean isBPressed() {
        return bPressed;
    }

}
