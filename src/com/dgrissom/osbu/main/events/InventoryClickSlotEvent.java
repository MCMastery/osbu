package com.dgrissom.osbu.main.events;

import com.dgrissom.osbu.main.utilities.InventoryUtility;
import com.dgrissom.osbu.main.utilities.PlayerUtility;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.inventory.ItemStack;

public class InventoryClickSlotEvent extends Event implements Cancellable {
    private static final HandlerList handlers = new HandlerList();
    private final PlayerUtility player;
    private final InventoryUtility inventory;
    private final int slot;
    private boolean isCancelled;

    public InventoryClickSlotEvent(PlayerUtility player, InventoryUtility inventory, int slot) {
        this.player = player;
        this.inventory = inventory;
        this.slot = slot;
    }

    public PlayerUtility getPlayer() {
        return this.player;
    }
    public InventoryUtility getInventory() {
        return this.inventory;
    }
    public int getClickedSlot() {
        return this.slot;
    }
    public ItemStack getClickedItem() {
        return this.inventory.getItem(this.slot);
    }
    /*
    The player may not have clicked inside the InventoryUtility - what if they clicked their own inventory (which can be underneath InventoryUtility)
     */
    public boolean clickedSlotInInventory() {
        try {
            this.inventory.getItem(this.slot);
            return true;
        } catch (ArrayIndexOutOfBoundsException e) {
            return false;
        }
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }
    public static HandlerList getHandlerList() {
        return handlers;
    }

    @Override
    public boolean isCancelled() {
        return isCancelled;
    }
    @Override
    public void setCancelled(boolean isCancelled) {
        this.isCancelled = isCancelled;
    }
}
