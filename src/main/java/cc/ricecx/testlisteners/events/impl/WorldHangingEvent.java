package cc.ricecx.testlisteners.events.impl;

import cc.ricecx.testlisteners.events.AbstractWorldEvent;
import org.bukkit.event.hanging.HangingEvent;

public class WorldHangingEvent<T extends HangingEvent> extends AbstractWorldEvent<T> {


    public WorldHangingEvent(T event) {
        super(event);
    }
}
