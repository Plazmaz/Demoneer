package com.dylan.demoneer.entity;

import com.badlogic.gdx.ai.pfa.PathFinder;
import com.badlogic.gdx.ai.pfa.indexed.IndexedAStarPathFinder;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.dylan.demoneer.Demoneer;
import com.dylan.demoneer.entity.ai.Behavior;
import com.dylan.demoneer.entity.ai.pathfinding.TileNode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dylan on 2/7/2016.
 */
public abstract class EntityCreature extends EntityLiving {
    protected List<Behavior> behaviors = new ArrayList<>();
/*    private static final SteeringAcceleration<Vector2> steeringOutput =
            new SteeringAcceleration<>(new Vector2());*/
    private IndexedAStarPathFinder<TileNode> pathfinder = new IndexedAStarPathFinder<>(Demoneer.getInstance().getPathfinderGraph(), false);
//    protected BlendedSteering<Vector2> blendedSteering;
//    protected CreatureSteerable steerable;
    public EntityCreature(TextureRegion texture, int spriteWidth, int spriteHeight) {
        super(texture, spriteWidth, spriteHeight);
//        steerable = new CreatureSteerable(this);
//        blendedSteering = new BlendedSteering<>(steerable);
    }

    /*public CreatureSteerable getSteerable() {
        return steerable;
    }*/

    @Override
    public void act(float delta) {
        super.act(delta);
        behaviors.forEach(Behavior::tick);
//        blendedSteering.calculateSteering(steeringOutput);
//        setVelX(steeringOutput.linear.x);
//        setVelY(steeringOutput.linear.y);
    }


    public PathFinder getPathfinder() {
        return pathfinder;
    }
    /*static class CreatureSteerable extends SteerableAdapter<Vector2> {
        private Entity target;

        public CreatureSteerable(Entity target) {
            this.target = target;
        }

        @Override
        public float vectorToAngle (Vector2 vector) {
            return (float)Math.atan2(-vector.x, vector.y);
        }

        // Actual implementation depends on your coordinate system.
        // Here we assume the y-axis is pointing upwards.
        @Override
        public Vector2 angleToVector (Vector2 outVector, float angle) {
            outVector.x = -(float)Math.sin(angle);
            outVector.y = (float)Math.cos(angle);
            return outVector;
        }

        @Override
        public float getMaxAngularAcceleration() {
            return 0.5f;
        }

        @Override
        public float getMaxLinearSpeed() {
            return target.getSpeed();
        }

        @Override
        public float getMaxLinearAcceleration() {
            return 0.5f;
        }

        @Override
        public Vector2 getPosition() {
            return new Vector2(target.getX(), target.getY());
        }

        @Override
        public float getOrientation() {
            return vectorToAngle(new Vector2(target.velX, target.velY));
        }

        @Override
        public float getBoundingRadius() {
            return target.getWidth();
        }

        @Override
        public Vector2 getLinearVelocity () {
            return new Vector2(target.velX, target.velY);
        }
    }*/
}
