package com.dgrissom.osbu.main;

import com.dgrissom.osbu.main.utilities.PlayerUtility;
import com.dgrissom.osbu.main.utilities.PluginUtility;

import java.util.HashSet;
import java.util.Set;

public abstract class OSBUCommand {
    private final PluginUtility plugin;
    private final String label;
    private final int args;
    private Set<OSBUCommand> subCommands;

    public OSBUCommand(PluginUtility plugin, String label, int args) {
        this.plugin = plugin;
        this.label = label;
        this.args = args;
        this.subCommands = new HashSet<>();
    }

    public PluginUtility getPlugin() {
        return this.plugin;
    }
    public String getLabel() {
        return this.label;
    }
    public int getArgs() {
        return this.args;
    }
    public Set<OSBUCommand> getSubCommands() {
        return this.subCommands;
    }
    public void addSubCommand(OSBUCommand cmd) {
        this.subCommands.add(cmd);
    }
    public boolean removeSubCommand(OSBUCommand cmd) {
        return this.subCommands.remove(cmd);
    }

    public abstract void execute(PlayerUtility sender, String[] args);

    public OSBUCommand register() {
        OSBU.getInstance().getCommands().registerCommand(this);
        return this;
    }
    public boolean unregister() {
        return OSBU.getInstance().getCommands().unregisterCommand(this);
    }
}
