package com.dylan.demoneer.entity.ai.pathfinding;

import com.badlogic.gdx.ai.pfa.Connection;
import com.badlogic.gdx.ai.pfa.DefaultConnection;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.utils.Array;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dylan on 2/7/2016.
 */
public class TileNode<N extends TileNode<N>>{
    private final TiledMapTile tile;
    private int x, y;
    private int id;
    private int index;
    Array<Connection<N>> connections = new Array<>();
    private boolean obstruction;

    public TileNode(int x, int y, TiledMapTile tile, boolean obstruction) {
        this.x = x;
        this.y = y;
        this.tile = tile;
        this.obstruction = obstruction;
        this.id = tile != null ? tile.getId() : 0;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void addConnection(N to) {
        connections.add((Connection<N>) new DefaultConnection<>(this, to));
    }

    public Array<Connection<N>> getConnections() {
        return connections;
    }

    public int getId() {
        return id;
    }

    public TiledMapTile getTile() {
        return tile;
    }

    public boolean isObstruction() {
        return obstruction;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
