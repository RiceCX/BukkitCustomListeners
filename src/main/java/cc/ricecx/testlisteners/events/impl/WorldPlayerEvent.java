package cc.ricecx.testlisteners.events.impl;

import cc.ricecx.testlisteners.events.AbstractWorldEvent;
import org.bukkit.event.player.PlayerEvent;

public class WorldPlayerEvent<T extends PlayerEvent> extends AbstractWorldEvent<T> {

    public WorldPlayerEvent(T event) {
        super(event);
    }
}
