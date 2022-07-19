package cc.ricecx.testlisteners;

import cc.ricecx.testlisteners.annotation.WorldEventHandler;
import cc.ricecx.testlisteners.events.impl.WorldEntityEvent;
import cc.ricecx.testlisteners.events.impl.WorldPlayerEvent;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.*;
import org.bukkit.event.player.PlayerJoinEvent;

public class TestListener implements Listener {

    @WorldEventHandler
    public void onPlayerJoin(WorldPlayerEvent<PlayerJoinEvent> evt) {
        System.out.println("Player joined world " + evt.getBukkitEvent().getPlayer().getWorld().getName());
    }

    @WorldEventHandler
    public void onEntityLove(WorldEntityEvent<EntityEnterLoveModeEvent> evt) {
        evt.getBukkitEvent().getHumanEntity().sendMessage("love ");
    }

    @WorldEventHandler
    public void gnghj(WorldEntityEvent<EntityBreakDoorEvent> evt) {
        evt.getBukkitEvent().getEntity().sendMessage("doorf beak");
    }

    @WorldEventHandler
    public void yu(WorldEntityEvent<EntityAirChangeEvent> evt) {
        evt.getBukkitEvent().getEntity().sendMessage("air change");
    }

    @WorldEventHandler
    public void jh(WorldEntityEvent<EntityChangeBlockEvent> evt) {
        evt.getBukkitEvent().getEntity().sendMessage("block change");
    }

    @WorldEventHandler
    public void hkjlh(WorldEntityEvent<EntityCombustEvent> evt) {
        evt.getBukkitEvent().getEntity().sendMessage("cuombust");
    }

}
