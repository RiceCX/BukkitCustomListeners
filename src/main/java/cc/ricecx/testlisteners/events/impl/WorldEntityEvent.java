package cc.ricecx.testlisteners.events.impl;

import cc.ricecx.testlisteners.events.AbstractWorldEvent;
import org.bukkit.event.entity.EntityEvent;

public class WorldEntityEvent<T extends EntityEvent> extends AbstractWorldEvent<T> {


    public WorldEntityEvent(T event) {
        super(event);
    }
}
