package cc.ricecx.testlisteners.events.impl;

import cc.ricecx.testlisteners.events.AbstractWorldEvent;
import org.bukkit.event.weather.WeatherEvent;

public class WorldWeatherEvent<T extends WeatherEvent> extends AbstractWorldEvent<T> {


    public WorldWeatherEvent(T event) {
        super(event);
    }
}
