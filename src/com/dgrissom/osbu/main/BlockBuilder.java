package com.dgrissom.osbu.main;

import org.bukkit.Location;
import org.bukkit.Material;

public class BlockBuilder {
    public BlockBuilder buildSolidCuboid(Location location, int width, int height, int length, Material material) {
        for (int tx = 0; tx < width; tx++)
            for (int ty = 0; ty < height; ty++)
                for (int tz = 0; tz < length; tz++)
                    location.add(tx, ty, tz).getBlock().setType(material);
        return this;
    }
}
