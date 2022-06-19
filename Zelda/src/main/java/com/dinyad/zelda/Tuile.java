package com.dinyad.zelda;

import javafx.scene.image.Image;

public class Tuile {
    private Image image;

    public boolean isCollision() {
        return collision;
    }

    private boolean collision;

    public Tuile(Image image, boolean collision) {
        this.image = image;
        this.collision = collision;
    }

    public Image getImage(boolean isCollision) {
        return image;
    }
}
