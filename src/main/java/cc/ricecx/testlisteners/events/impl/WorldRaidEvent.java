package cc.ricecx.testlisteners.events.impl;

import cc.ricecx.testlisteners.events.AbstractWorldEvent;
import org.bukkit.event.raid.RaidEvent;

public class WorldRaidEvent<T extends RaidEvent> extends AbstractWorldEvent<T> {


    public WorldRaidEvent(T event) {
        super(event);
    }
}
