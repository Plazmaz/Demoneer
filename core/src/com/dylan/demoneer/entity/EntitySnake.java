package com.dylan.demoneer.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ai.steer.behaviors.RaycastObstacleAvoidance;
import com.badlogic.gdx.ai.steer.behaviors.Seek;
import com.badlogic.gdx.ai.steer.utils.rays.CentralRayWithWhiskersConfiguration;
import com.badlogic.gdx.ai.steer.utils.rays.SingleRayConfiguration;
import com.badlogic.gdx.ai.utils.Collision;
import com.badlogic.gdx.ai.utils.Ray;
import com.badlogic.gdx.ai.utils.RaycastCollisionDetector;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.dylan.demoneer.Demoneer;
import com.dylan.demoneer.entity.ai.behaviors.BehaviorSeekPlayer;

/**
 * Created by Dylan on 2/7/2016.
 */
public class EntitySnake extends EntityCreature {
    private int ticksLived = 0;
    public EntitySnake() {
        super(new TextureRegion(new Texture(Gdx.files.internal("snake.png"))), 48, 48);
        speed = 2.5f;
        behaviors.add(new BehaviorSeekPlayer(this));
//        Seek<Vector2> seek = new Seek<>(steerable);
//        RaycastObstacleAvoidance<Vector2> avoidance = new RaycastObstacleAvoidance<>(steerable, new SingleRayConfiguration<>(steerable, 3f));
//        avoidance.setRaycastCollisionDetector(new RaycastCollisionDetector<Vector2>() {
//            @Override
//            public boolean collides(Ray<Vector2> ray) {
//                return Demoneer.getInstance().isObstructed(ray.end.x, ray.end.y);
//            }
//
//            @Override
//            public boolean findCollision(Collision<Vector2> outputCollision, Ray<Vector2> inputRay) {
//                outputCollision.point = inputRay.end;
//                outputCollision.normal = inputRay.end;
//                return Demoneer.getInstance().isObstructed(inputRay.end.x, inputRay.end.y);
//            }
//        });
//        blendedSteering.add(seek, .5f);
//        blendedSteering.add(avoidance, 1f);
//        seek.setEnabled(true);
//        EntityPlayer player = Demoneer.getInstance().getPlayer();
//        seek.setTarget(new CreatureSteerable(player));
    }
    @Override
    public void act(float delta) {
        ticksLived++;
        if (velY < 0) {
            setFrameY(0);
        } else if (velY > 0) {
            setFrameY(3);
        } else if (velX > 0) {
            setFrameY(2);
        } else if (velX < 0) {
            setFrameY(1);
        }
        if(ticksLived % 5 == 0) {
            super.act(delta);
            if (getFrameX() < 2) {
                setFrameX(getFrameX() + 1);
            } else {
                setFrameX(0);
            }
        }
    }

    @Override
    public double getMaxHealth() {
        return 5;
    }

    @Override
    public void playDeathAnimation() {

    }
}
