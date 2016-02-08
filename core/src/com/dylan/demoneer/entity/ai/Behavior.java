package com.dylan.demoneer.entity.ai;

import com.dylan.demoneer.entity.EntityCreature;

/**
 * Created by Dylan on 2/7/2016.
 */
public abstract class Behavior {
    protected EntityCreature creature;
    public Behavior(EntityCreature creature) {
        this.creature = creature;
    }
    public abstract void tick();
    public abstract void end();
}
