package cc.ricecx.testlisteners.events.impl;

import cc.ricecx.testlisteners.events.AbstractWorldEvent;
import org.bukkit.event.server.ServerEvent;

public class WorldServerEvent<T extends ServerEvent> extends AbstractWorldEvent<T> {


    public WorldServerEvent(T event) {
        super(event);
    }
}
