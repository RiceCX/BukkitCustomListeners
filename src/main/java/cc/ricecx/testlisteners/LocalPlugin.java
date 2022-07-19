package cc.ricecx.testlisteners;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;

public class LocalPlugin extends JavaPlugin {


    private final CustomEventBus eventBus = new CustomEventBus();

    @Override
    public void onEnable() {
        EventManager eventManager = new EventManager(this, getClassLoader(), eventBus);
        try {
            eventManager.registerAllEvents();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        registerListeners(new TestListener());
    }


    public void registerListeners(Listener... listeners) {
        for (Listener listener : listeners) {
            eventBus.register(listener);
            Bukkit.getPluginManager().registerEvents(listener, this);
        }
    }


}