package com.dgrissom.osbu.main;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class OSBU extends JavaPlugin {
    private static OSBU instance;
    private OSBUCommands commands;
    private OSBUListeners listeners;

    @Override
    public void onEnable() {
        instance = this;
        getConfig().options().copyDefaults(true);
        saveDefaultConfig();
        registerListeners();
        registerCommands();
    }

    private void registerListeners() {
        this.listeners = new OSBUListeners();
        for (Listener listener : this.listeners)
            Bukkit.getPluginManager().registerEvents(listener, this);
    }
    private void registerCommands() {
        this.commands = new OSBUCommands();
    }

    public static OSBU getInstance() {
        return instance;
    }

    public OSBUListeners getListeners() {
        return this.listeners;
    }
    public OSBUCommands getCommands() {
        return this.commands;
    }
    public OSBUCommand commandFromName(String label) {
        for (OSBUCommand cmd : this.commands)
            if (cmd.getLabel().equalsIgnoreCase(label))
                return cmd;
        return null;
    }
}
