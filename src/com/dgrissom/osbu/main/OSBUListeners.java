package com.dgrissom.osbu.main;

import com.dgrissom.osbu.main.listeners.OSBUCommandListener;
import com.dgrissom.osbu.main.listeners.OSBUInventoryClickListener;
import com.dgrissom.osbu.main.listeners.OSBUInventoryCloseListener;
import org.bukkit.event.Listener;

import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public final class OSBUListeners implements Iterable<Listener> {
    private final Set<Listener> listeners;

    OSBUListeners() {
        this.listeners = new HashSet<>();
        this.listeners.add(new OSBUCommandListener());
        this.listeners.add(new OSBUInventoryClickListener());
        this.listeners.add(new OSBUInventoryCloseListener());
        Collections.unmodifiableSet(this.listeners);
    }

    @Override
    public Iterator<Listener> iterator() {
        return this.listeners.iterator();
    }
}
