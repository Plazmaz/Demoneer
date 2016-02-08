package com.dylan.demoneer.entity;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.dylan.demoneer.Demoneer;

/**
 * Created by Dylan on 2/6/2016.
 */
public abstract class Entity extends Actor {
    protected TextureRegion textureRegion;
    protected int spriteWidth;
    protected int spriteHeight;
    private int frameX, frameY;
    protected float speed = 2f;
    protected float velX, velY;
    private Rectangle boundingBox;
    public Entity(TextureRegion texture, int spriteWidth, int spriteHeight) {
        textureRegion = texture;
        this.spriteWidth = spriteWidth;
        this.spriteHeight = spriteHeight;
        boundingBox = new Rectangle(0, 0, spriteWidth, spriteHeight);
        setFrame(0, 0);
    }

    public void setFrame(int x, int y) {
        textureRegion = new TextureRegion(textureRegion.getTexture(), x * spriteWidth, y * spriteHeight, spriteWidth, spriteHeight);
    }

    public int getGridX() {
        return (int) ((getX() + spriteWidth / 2) / (Demoneer.TILE_SIZE * Demoneer.MAP_SCALE));
    }

    public int getGridY() {
        return (int) ((getY() + spriteHeight / 2) / (Demoneer.TILE_SIZE * Demoneer.MAP_SCALE));
    }

    @Override
    public void act(float delta) {
        boundingBox.setX(getX());
        boundingBox.setY(getY());
        TiledMapTileLayer obstructions = (TiledMapTileLayer) Demoneer.MAP.getLayers().get("obstructions");
        float nextX = getX() + velX;
        float nextY = getY() + velY;
        boundingBox.setX(nextX);
        boundingBox.setY(nextY);
//        boolean invalid = false;
//        double playerCellX = Math.floor((nextX + spriteWidth / 2) / (obstructions.getTileWidth() * Demoneer.MAP_SCALE));
//        double playerCellY = Math.floor((nextY + spriteHeight / 2) / (obstructions.getTileHeight() * Demoneer.MAP_SCALE));
     /*   for (double x = -1; x <= 1; x++) {
            for (double y = -1; y <= 1; y++) {
                double cellX =  Math.floor((nextX + x +  spriteWidth / 2) / (obstructions.getTileHeight() * Demoneer.MAP_SCALE));
                double cellY =  Math.floor((nextY + y +  spriteHeight / 2) / (obstructions.getTileHeight() * Demoneer.MAP_SCALE));
                TiledMapTileLayer.Cell cell = obstructions.getCell((int) cellX, (int) cellY);
                if(cell != null) {
                    invalid = true;
                    break;
                }
            }
            if(invalid) {
                break;
            }
        }
        if(invalid) {*/
            boolean xValid = true, yValid = true;
            for (double x = -spriteWidth / 2; x <= spriteWidth / 2; x += 2) {
                for(double y = -spriteHeight / 2; y <= spriteHeight / 2; y += 2) {
                    int cellX = (int) ((nextX + x + (spriteWidth / 2)) / (obstructions.getTileWidth() * Demoneer.MAP_SCALE));
                    int cellY = (int) ((getY() + y + (spriteHeight / 2)) / (obstructions.getTileHeight() * Demoneer.MAP_SCALE));
                    if (obstructions.getCell(cellX, cellY) != null) {
                        xValid = false;
                    }
                }
            }
            for (double y = -spriteHeight / 2; y <= spriteHeight / 2; y += 2) {
                for (double x = -spriteWidth / 2; x <= spriteWidth / 2; x += 2) {
                    int cellX = (int) ((getX() + x + (spriteWidth / 2)) / (obstructions.getTileWidth() * Demoneer.MAP_SCALE));
                    int cellY = (int) ((nextY + y + (spriteHeight / 2)) / (obstructions.getTileHeight() * Demoneer.MAP_SCALE));
                    if (obstructions.getCell(cellX, cellY) != null) {
                        yValid = false;
                    }
                }
            }
            if(xValid) {
                setX(nextX);
            }
            if(yValid) {
                setY(nextY);
            }
        /*} else {
            setX(nextX);
            setY(nextY);
        }*/
        /*TiledMapTileLayer.Cell cell = obstructions.getCell((int)((nextX + spriteWidth / 2) / (obstructions.getTileWidth() * Demoneer.MAP_SCALE)),
                (int) ((nextY + spriteHeight / 2) / (obstructions.getTileHeight() * Demoneer.MAP_SCALE)));
        if(cell == null) {
            setX((float) nextX);
            setY((float) nextY);
        }*/
        velX = 0;
        velY = 0;
    }

    private float drag(float value, float amount) {
        if(value >= amount) {
            value -= amount;
        } else if(value <= -amount) {
            value += amount;
        } else {
            value = 0;
        }
        return value;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        Color color = getColor();
        batch.setColor(color.r, color.g, color.b, color.a * parentAlpha);
        setFrame(getFrameX(), getFrameY());
        batch.draw(textureRegion, getX(), getY(), getOriginX(), getOriginY(), spriteWidth, spriteHeight, getScaleX(), getScaleY(), getRotation());
    }

    public int getFrameX() {
        return frameX;
    }

    public void setFrameX(int frameX) {
        this.frameX = frameX;
    }

    public int getFrameY() {
        return frameY;
    }

    public void setFrameY(int frameY) {
        this.frameY = frameY;
    }

    public int getImageWidth() {
        return textureRegion.getTexture().getWidth();
    }
    public int getImageHeight() {
        return textureRegion.getTexture().getHeight();
    }

    public void setVelX(float velX) {
        this.velX = velX;
    }

    public void setVelY(float velY) {
        this.velY = velY;
    }

    public float getSpeed() {
        return speed;
    }
}
