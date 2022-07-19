package cc.ricecx.testlisteners.events;

import lombok.Getter;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

@Getter
public abstract class AbstractWorldEvent<T extends Event> extends Event {

    private final T bukkitEvent;
    private static final HandlerList handlerList = new HandlerList();


    public AbstractWorldEvent(T event) {
        this.bukkitEvent = event;
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return handlerList;
    }

    @NotNull
    public static HandlerList getHandlerList() {
        return handlerList;
    }

}
