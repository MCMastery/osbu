package com.dgrissom.osbu.main.utilities;

import com.dgrissom.osbu.main.OSBU;
import org.bukkit.plugin.java.JavaPlugin;

public abstract class PluginUtility extends OSBUUtility {
    private final JavaPlugin plugin;

    public PluginUtility(Object object) {
        super(object);
        this.plugin = (JavaPlugin) object;
    }

    public abstract boolean enablePDFs();

    public PluginUtility register() {
        OSBU.getInstance().getPlugins().registerPlugin(this);
        return this;
    }
    public boolean unregister() {
        return OSBU.getInstance().getPlugins().unregisterPlugin(this);
    }

    @Override
    public JavaPlugin getObject() {
        return this.plugin;
    }
}
