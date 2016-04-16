package com.dgrissom.osbu.main.utilities;

import com.dgrissom.osbu.main.events.InventoryClickSlotEvent;
import com.dgrissom.osbu.main.events.InventoryExitEvent;
import com.dgrissom.osbu.main.listeners.InventoryUtilityClickHandler;
import com.dgrissom.osbu.main.listeners.InventoryUtilityCloseHandler;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.*;

public class InventoryUtility extends OSBUUtility implements Iterable<ItemStack> {
    public static final int SLOTS_PER_ROW, MAX_ROWS;
    private static final Set<InventoryUtility> INVENTORIES;

    static {
        SLOTS_PER_ROW = 9;
        MAX_ROWS  = 6;
        INVENTORIES = new HashSet<>();
    }

    private final Inventory inventory;
    private UUID viewer;
    private boolean autoUnregister;
    private Set<InventoryUtilityClickHandler> clickHandlers;
    private Set<InventoryUtilityCloseHandler> closeHandlers;

    public InventoryUtility(int rows, String title) {
        this(Bukkit.createInventory(null, rows * SLOTS_PER_ROW, new StringUtility(title).format().toString()));
    }
    public InventoryUtility(Object object) {
        super(object);
        this.inventory = (Inventory) object;
        this.viewer = null;
        this.autoUnregister = true;
        this.clickHandlers = new HashSet<>();
        this.closeHandlers = new HashSet<>();
    }



    public UUID getViewerId() {
        return this.viewer;
    }
    // it is necessary to register this inventory so InventoryClickSlotEvents can be called
    public boolean isRegistered() {
        return this.viewer != null;
    }
    // should this inventory automatically be unregistered when a player closes it?
    // saves memory - keeps INVENTORIES set size low
    // if false, you MUST MANUALLY UNREGISTER THIS (unregister())
    public boolean autoUnegister() {
        return this.autoUnregister;
    }
    public InventoryUtility autoUnegister(boolean autoUnregister) {
        this.autoUnregister = autoUnregister;
        return this;
    }
    public InventoryUtility register(PlayerUtility viewer) {
        this.viewer = viewer.getUniqueId();
        INVENTORIES.add(this);
        return this;
    }
    public InventoryUtility unregister() {
        if (!isRegistered())
            return this;
        INVENTORIES.remove(this);
        this.viewer = null;
        return this;
    }
    public static InventoryUtility fromViewerId(UUID id) {
        for (InventoryUtility inv : INVENTORIES)
            if (inv.isRegistered() && inv.getViewerId().equals(id))
                return inv;
        return null;
    }



    /*
    InventoryUtility allows Bukkit's built-in listener and event system, and these methods, similar to Java's ActionListener used by Swing components
     */
    public Set<InventoryUtilityClickHandler> getClickHandlers() {
        return this.clickHandlers;
    }
    public InventoryUtility addClickHandler(InventoryUtilityClickHandler handler) {
        this.clickHandlers.add(handler);
        return this;
    }
    public InventoryUtility removeClickHandler(InventoryUtilityClickHandler handler) {
        this.clickHandlers.remove(handler);
        return this;
    }
    public InventoryUtility triggerClickHandlers(InventoryClickSlotEvent evt) {
        for (InventoryUtilityClickHandler handler : this.clickHandlers)
            handler.onClick(evt);
        return this;
    }
    
    public Set<InventoryUtilityCloseHandler> getCloseHandlers() {
        return this.closeHandlers;
    }
    public InventoryUtility addCloseHandler(InventoryUtilityCloseHandler handler) {
        this.closeHandlers.add(handler);
        return this;
    }
    public InventoryUtility removeCloseHandler(InventoryUtilityCloseHandler handler) {
        this.closeHandlers.remove(handler);
        return this;
    }
    public InventoryUtility triggerCloseHandlers(InventoryExitEvent evt) {
        for (InventoryUtilityCloseHandler handler : this.closeHandlers)
            handler.onClose(evt);
        return this;
    }



    public String getTitle() {
        return this.inventory.getTitle();
    }
    public int getSize() {
        return this.inventory.getSize();
    }
    public int getRows() {
        return this.inventory.getSize() / SLOTS_PER_ROW;
    }

    public void clear() {
        this.inventory.clear();
    }
    public ItemStack getItem(int slot) {
        return this.inventory.getItem(slot);
    }
    public InventoryUtility setItem(int slot, ItemStack item) {
        this.inventory.setItem(slot, item);
        return this;
    }
    public InventoryUtility remove(int slot) {
        setItem(slot, new ItemStack(Material.AIR));
        return this;
    }

    public InventoryUtility add(ItemStack... items) {
        this.inventory.addItem(items);
        return this;
    }
    public InventoryUtility add(Collection<ItemStack> items) {
        for (ItemStack item : items)
            this.inventory.addItem(item);
        return this;
    }
    public InventoryUtility remove(ItemStack... items) {
        for (ItemStack item : items) {
            ItemStack check = item.clone();
            int leftToRemove = item.getAmount();
            for (int slot = 0; slot < getSize(); slot++) {
                ItemStack i = getItem(slot);
                if (i == null)
                    continue;
                // check if they are same item. to do this, we must set their amounts to be the same temporarily
                check.setAmount(i.getAmount());
                if (i.equals(check)) {
                    if (i.getAmount() > leftToRemove) {
                        i.setAmount(i.getAmount() - leftToRemove);
                        leftToRemove = 0;
                    } else {
                        remove(slot);
                        leftToRemove -= i.getAmount();
                    }
                }
                if (leftToRemove == 0)
                    break;
            }
        }
        return this;
    }

    @Override
    public Inventory getObject() {
        return this.inventory;
    }
    @Override
    public Iterator<ItemStack> iterator() {
        return this.inventory.iterator();
    }
}
