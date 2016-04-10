package com.dgrissom.osbu.main;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public final class OSBUCommands implements Iterable<OSBUCommand> {
    private final Set<OSBUCommand> commands;

    OSBUCommands() {
        this.commands = new HashSet<>();
    }

    public void registerCommand(OSBUCommand command) {
        this.commands.add(command);
    }
    public boolean unregisterCommand(OSBUCommand command) {
        return this.commands.remove(command);
    }
    @Override
    public Iterator<OSBUCommand> iterator() {
        return this.commands.iterator();
    }
}
