package com.dgrissom.osbu.main.utilities;

import com.dgrissom.osbu.main.Serializer;
import org.bukkit.Location;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.ItemStack;

import java.io.File;
import java.io.IOException;

public class YamlUtility extends OSBUUtility {
    private final YamlConfiguration file;

    public YamlUtility(Object object) {
        super(object);
        this.file = (YamlConfiguration) object;
    }

    /*
    Currently only supports "normal" inventories (no brewing stand, anvil, etc.)!
     */
    public YamlUtility saveInventory(InventoryUtility inventory, String path) {
        this.file.set(path + ".title", new StringUtility(inventory.getTitle()).unformat().toString());
        this.file.set(path + ".size", inventory.getSize());
        for (int slot = 0; slot < inventory.getSize(); slot++) {
            ItemStack item = inventory.getItem(slot);
            if (item != null)
                this.file.set(path + "." + slot, item);
        }
        return this;
    }
    public InventoryUtility getInventory(String path) {
        int size = this.file.getInt(path + ".size");
        String title = new StringUtility(this.file.getString(path + ".title")).format().toString();
        InventoryUtility inventory = new InventoryUtility(size / InventoryUtility.SLOTS_PER_ROW, title);
        for (int slot = 0; slot < inventory.getSize(); slot++) {
            ItemStack item = this.file.getItemStack(path + "." + slot);
            if (item != null)
                inventory.setItem(slot, item);
        }
        return inventory;
    }
    public YamlUtility saveLocation(Location location, String path) {
        this.file.set(path, Serializer.serialize(location));
        return this;
    }
    public Location getLocation(String path) {
        return Serializer.deserializeLocation(this.file.getString(path));
    }

    public YamlUtility set(String path, Object object) {
        this.file.set(path,object);
        return this;
    }

    public YamlUtility save(File file) throws IOException {
        this.file.save(file);
        return this;
    }

    @Override
    public YamlConfiguration getObject() {
        return this.file;
    }
}
