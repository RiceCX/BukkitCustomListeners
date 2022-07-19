package cc.ricecx.testlisteners.events.impl;

import cc.ricecx.testlisteners.events.AbstractWorldEvent;
import org.bukkit.event.inventory.InventoryEvent;

public class WorldInventoryEvent <T extends InventoryEvent> extends AbstractWorldEvent<T> {

    public WorldInventoryEvent(T event) {
        super(event);
    }
}
