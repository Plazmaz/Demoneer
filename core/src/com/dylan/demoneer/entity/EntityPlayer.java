package com.dylan.demoneer.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by Dylan on 2/6/2016.
 */
public class EntityPlayer extends EntityLiving {
    private Camera camera;

    public EntityPlayer(Camera camera) {
        super(new TextureRegion(new Texture(Gdx.files.internal("player.png"))), 64, 64);

        this.camera = camera;
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        boolean animate = false;
        if(!Gdx.input.isTouched()) {
            if (Gdx.input.isKeyPressed(Input.Keys.W)) {
                setFrameY(0);
                velY += speed;
//            camera.position.y += speed;
                animate = true;
            }
            if (Gdx.input.isKeyPressed(Input.Keys.A)) {
                setFrameY(1);
                velX -= speed;
//            camera.position.x -= speed;
                animate = true;
            }
            if (Gdx.input.isKeyPressed(Input.Keys.S)) {
                setFrameY(2);
                velY -= speed;
//            camera.position.y -= speed;
                animate = true;
            }
            if (Gdx.input.isKeyPressed(Input.Keys.D)) {
                setFrameY(3);
//            camera.position.x += speed;
                velX += speed;
                animate = true;
            }
        } else {

        }
//        setX(camera.position.x);
//        setY(camera.position.y);
        camera.position.x = getX();
        camera.position.y = getY();
        camera.update();
        if(animate) {
            if(getFrameX() < 8) {
                setFrameX(getFrameX() + 1);
            } else {
                setFrameX(0);
            }
        }
    }

    @Override
    public double getMaxHealth() {
        return 10.0;
    }

    @Override
    public void playDeathAnimation() {

    }
}
