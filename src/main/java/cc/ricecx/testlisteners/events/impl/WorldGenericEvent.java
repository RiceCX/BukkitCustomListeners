package cc.ricecx.testlisteners.events.impl;

import cc.ricecx.testlisteners.events.AbstractWorldEvent;
import org.bukkit.event.world.WorldEvent;

public class WorldGenericEvent<T extends WorldEvent> extends AbstractWorldEvent<T> {
    public WorldGenericEvent(T event) {
        super(event);
    }
}
