package com.dylan.demoneer.entity.ai.behaviors;

import com.badlogic.gdx.ai.pfa.DefaultConnection;
import com.badlogic.gdx.ai.pfa.DefaultGraphPath;
import com.badlogic.gdx.ai.pfa.GraphPath;
import com.badlogic.gdx.ai.pfa.Heuristic;
import com.badlogic.gdx.ai.pfa.indexed.IndexedAStarPathFinder;
import com.dylan.demoneer.Demoneer;
import com.dylan.demoneer.entity.EntityCreature;
import com.dylan.demoneer.entity.EntityPlayer;
import com.dylan.demoneer.entity.ai.Behavior;
import com.dylan.demoneer.entity.ai.pathfinding.TileNode;
import com.dylan.demoneer.entity.ai.pathfinding.TiledGraph;

import java.util.Iterator;

/**
 * Created by Dylan on 2/7/2016.
 */
public class BehaviorSeekPlayer extends Behavior {
    DefaultGraphPath path = new DefaultGraphPath();
    public BehaviorSeekPlayer(EntityCreature creature) {
        super(creature);
    }

    @Override
    public void tick() {
        EntityPlayer player = Demoneer.getInstance().getPlayer();
        TiledGraph graph = Demoneer.getInstance().getPathfinderGraph();
        TileNode startNode = graph.getNodeAt(creature.getGridX(), creature.getGridY());
        TileNode endNode = graph.getNodeAt(player.getGridX(), player.getGridY());
        creature.getPathfinder().searchNodePath(startNode, endNode, (Heuristic<TileNode>) (node, en) -> Math.abs(en.getX() - node.getY()) + Math.abs(en.getY() - node.getY()), path);
        Iterator iter = path.iterator();
        if(iter.hasNext()) {
            iter.next();
        }
        if(iter.hasNext()) {
            TileNode next = (TileNode) iter.next();
            double xDiff = (next.getX() * Demoneer.TILE_SIZE * Demoneer.MAP_SCALE) - creature.getX();
            double yDiff = (next.getY() * Demoneer.TILE_SIZE * Demoneer.MAP_SCALE) - creature.getY();
            if(xDiff < 0) {
                creature.setVelX((float) Math.max(xDiff, -creature.getSpeed()));
            } else if(xDiff > 0) {
                creature.setVelX((float) Math.min(xDiff, creature.getSpeed()));
            }
            if(yDiff < 0) {
                creature.setVelY((float) Math.max(yDiff, -creature.getSpeed()));
            } else if(yDiff > 0){
                creature.setVelY((float) Math.min(yDiff, creature.getSpeed()));
            }
        }
    }

    @Override
    public void end() {

    }
}
