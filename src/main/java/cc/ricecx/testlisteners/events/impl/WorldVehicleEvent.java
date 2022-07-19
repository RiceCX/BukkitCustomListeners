package cc.ricecx.testlisteners.events.impl;

import cc.ricecx.testlisteners.events.AbstractWorldEvent;
import org.bukkit.event.vehicle.VehicleEvent;

public class WorldVehicleEvent<T extends VehicleEvent> extends AbstractWorldEvent<T> {


    public WorldVehicleEvent(T event) {
        super(event);
    }
}
