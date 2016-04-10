package com.dgrissom.osbu.main;

import com.dgrissom.osbu.main.utilities.PluginUtility;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public final class OSBUPlugins implements Iterable<PluginUtility> {
    private final Set<PluginUtility> plugins;

    OSBUPlugins() {
        this.plugins = new HashSet<>();
    }

    public void registerPlugin(PluginUtility plugin) {
        this.plugins.add(plugin);
    }
    public boolean unregisterPlugin(PluginUtility plugin) {
        return this.plugins.remove(plugin);
    }
    @Override
    public Iterator<PluginUtility> iterator() {
        return this.plugins.iterator();
    }
}
