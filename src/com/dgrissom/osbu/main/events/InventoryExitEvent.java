package com.dgrissom.osbu.main.events;

import com.dgrissom.osbu.main.utilities.InventoryUtility;
import com.dgrissom.osbu.main.utilities.PlayerUtility;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class InventoryExitEvent extends Event {
    private static final HandlerList handlers = new HandlerList();
    private final PlayerUtility player;
    private final InventoryUtility inventory;

    public InventoryExitEvent(PlayerUtility player, InventoryUtility inventory) {
        this.player = player;
        this.inventory = inventory;
    }

    public PlayerUtility getPlayer() {
        return this.player;
    }
    public InventoryUtility getInventory() {
        return this.inventory;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }
    public static HandlerList getHandlerList() {
        return handlers;
    }
}
