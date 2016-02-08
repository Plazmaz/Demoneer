package com.dylan.demoneer.entity.ai.pathfinding;

import com.badlogic.gdx.ai.pfa.Connection;
import com.badlogic.gdx.ai.pfa.DefaultConnection;
import com.badlogic.gdx.ai.pfa.indexed.IndexedGraph;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.utils.Array;
import com.dylan.demoneer.Demoneer;

import java.util.ArrayList;

/**
 * Created by Dylan on 2/7/2016.
 */
public class TiledGraph implements IndexedGraph<TileNode> {
    private final int width;
    private final int height;
    protected Array<TileNode> nodes = new Array<>();

    @SuppressWarnings("unchecked")
    public TiledGraph(int width, int height) {
        this.width = width;
        this.height = height;
        int index = 0;
        for (int y = 0; y < height; ++y) {
            for (int x = 0; x < width; ++x) {
                TileNode node = Demoneer.getInstance().getTileNodeAt(x, y, "obstructions");
                if(node == null) {
                    node = new TileNode(x, y, null, false);
                }
                node.setIndex(index++);
                nodes.add(node);
            }
        }
        for (int y = 0; y < height; ++y) {
            for (int x = 0; x < width; ++x) {
                TileNode node = getNodeAt(x, y);
                if(node == null) {
                    node = new TileNode(x, y, null, false);
                }
                TileNode target = Demoneer.getInstance().getTileNodeAt(x, y, "obstructions");
                TileNode up = Demoneer.getInstance().getTileNodeAt(x, y + 1, "obstructions");
                TileNode left = Demoneer.getInstance().getTileNodeAt(x - 1, y, "obstructions");
                TileNode right = Demoneer.getInstance().getTileNodeAt(x + 1, y, "obstructions");
                TileNode down = Demoneer.getInstance().getTileNodeAt(x, y - 1, "obstructions");
                if(target == null) {
                    if (y != 0 && down == null) {
                        down = new TileNode(x, y - 1, null, false);
                        node.addConnection(getNodeAt(x, y - 1));
                    }
                    if (x != 0 && left == null) {
                        left = new TileNode(x - 1, y, null, false);
                        node.addConnection(getNodeAt(x - 1, y));
                    }
                    if (y != height - 1 && up == null) {
                        up = new TileNode(x, y + 1, null, false);
                        node.addConnection(getNodeAt(x, y + 1));
                    }
                    if (x != width - 1 && right == null) {
                        right = new TileNode(x + 1, y, null, false);
                        node.addConnection(getNodeAt(x + 1, y));
                    }
                }
            }
        }
    }

    @Override
    public int getIndex(TileNode node) {
        return node.getIndex();
    }

    @Override
    public int getNodeCount() {
        return nodes.size;
    }

    public TileNode getNodeAt(int x, int y) {
        return nodes.get(y * width + x);
    }

    @Override
    public Array<Connection<TileNode>> getConnections(TileNode fromNode) {
        return fromNode.getConnections();
    }
}
