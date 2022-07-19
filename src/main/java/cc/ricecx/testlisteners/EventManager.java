package cc.ricecx.testlisteners;

import cc.ricecx.testlisteners.events.AbstractWorldEvent;
import cc.ricecx.testlisteners.events.impl.*;
import com.google.common.collect.ImmutableSet;
import com.google.common.reflect.ClassPath;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.event.Event;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityEvent;
import org.bukkit.event.hanging.HangingEvent;
import org.bukkit.event.inventory.InventoryEvent;
import org.bukkit.event.player.PlayerEvent;
import org.bukkit.event.raid.RaidEvent;
import org.bukkit.event.server.ServerEvent;
import org.bukkit.event.vehicle.VehicleEvent;
import org.bukkit.event.weather.WeatherEvent;
import org.bukkit.event.world.WorldEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;
import java.lang.reflect.Modifier;

public class EventManager {


    private final JavaPlugin plugin;
    private final ClassLoader classLoader;
    private final CustomEventBus eventBus;

    public EventManager(JavaPlugin plugin, ClassLoader classLoader, CustomEventBus eventBus) {
        this.plugin = plugin;
        this.classLoader = classLoader;

        this.eventBus = eventBus;
    }

    @SuppressWarnings("unchecked")
    public void registerAllEvents() throws IOException, ClassNotFoundException {

        final String packageBukkitEvents = "org.bukkit.event";

        for (ClassNames value : ClassNames.values()) {

            String packageClass = value.name().toLowerCase();
            ImmutableSet<ClassPath.ClassInfo> classes = ClassPath.from(classLoader).getTopLevelClasses(packageBukkitEvents + "." + packageClass);

            for (ClassPath.ClassInfo aClass : classes) {
                Class<? extends Event> clazz = (Class<? extends Event>) Class.forName(aClass.getName());
                if (clazz.isAnonymousClass() || clazz.isEnum() || Modifier.isAbstract(clazz.getModifiers())) continue;
                Bukkit.getPluginManager().registerEvent(clazz, new EmptyListener(), EventPriority.HIGHEST, this::handleEvent, plugin);
            }
        }
    }

    static class EmptyListener implements Listener {
    }

    private void handleEvent(Listener listener, Event evt) {
        ClassNames evtType = ClassNames.find(evt.getClass());
        if (evtType == null) return;
        AbstractWorldEvent<?> event = evtType.createEvent(evt);
        eventBus.callEvent(event);
    }

    enum ClassNames {
        ENTITY(EntityEvent.class),
        PLAYER(PlayerEvent.class),
        RAID(RaidEvent.class),
        HANGING(HangingEvent.class),
        INVENTORY(InventoryEvent.class),
        SERVER(ServerEvent.class),
        VEHICLE(VehicleEvent.class),
        WEATHER(WeatherEvent.class),
        WORLD(AbstractWorldEvent.class);

        @Getter
        private final Class<? extends Event> clazz;

        ClassNames(Class<? extends Event> evt) {
            this.clazz = evt;
        }

        public AbstractWorldEvent<?> createEvent(Event event) {
            return switch (this) {
                case ENTITY -> new WorldEntityEvent<>((EntityEvent) event);
                case PLAYER -> new WorldPlayerEvent<>((PlayerEvent) event);
                case RAID -> new WorldRaidEvent<>((RaidEvent) event);
                case HANGING -> new WorldHangingEvent<>((HangingEvent) event);
                case INVENTORY -> new WorldInventoryEvent<>((InventoryEvent) event);
                case SERVER -> new WorldServerEvent<>((ServerEvent) event);
                case VEHICLE -> new WorldVehicleEvent<>((VehicleEvent) event);
                case WEATHER -> new WorldWeatherEvent<>((WeatherEvent) event);
                case WORLD -> new WorldGenericEvent<>((WorldEvent) event);
            };
        }

        public static ClassNames find(Class<? extends Event> clazz) {
            for (ClassNames classNames : values()) {
                if (classNames.getClazz().isAssignableFrom(clazz)) {
                    return classNames;
                }
            }
            return null;
        }
    }

}
