package com.dgrissom.osbu.main;

import com.dgrissom.osbu.main.utilities.PlayerUtility;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.libs.jline.internal.Nullable;

import java.util.HashSet;
import java.util.Set;

public abstract class OSBUCommand {
    private final String label, permission, usage, description;
    private Set<OSBUCommand> subCommands;

    // no description means the
    public OSBUCommand(String label, @Nullable String permission, String usage, @Nullable String description) {
        this.label = label;
        this.permission = permission;
        this.subCommands = new HashSet<>();
        this.usage = usage;
        this.description = description;
    }

    public String getLabel() {
        return this.label;
    }
    public String getPermission() {
        return this.permission;
    }
    public String getUsage() {
        return this.usage;
    }
    public boolean hasPermission() {
        return this.permission != null;
    }
    public boolean hasPermission(CommandSender sender) {
        return !hasPermission() || sender.hasPermission(this.permission);
    }
    public String getDescription() {
        return this.description;
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
    // override if you want
    public void execute(CommandSender sender, String[] args) {
        sender.sendMessage(ChatColor.RED + "Only players can use that command!");
    }

    public OSBUCommand register() {
        OSBU.getInstance().getCommands().registerCommand(this);
        return this;
    }
    public boolean unregister() {
        return OSBU.getInstance().getCommands().unregisterCommand(this);
    }

    /*
    header - {page} = current page
    header - {pages} = # of pages
     */
    public Book generateHelp(CommandSender sender, ChatColor cmdColor, ChatColor descColor, String header) {
        Book book = new Book();
        for (OSBUCommand subCommand : this.subCommands)
            if (subCommand.hasPermission(sender))
                sender.sendMessage(cmdColor + subCommand.getUsage() + descColor + " - " + subCommand.getDescription());
        // 10 lines - includes Headers
        book.generatePages(9);
        for (int pg = 0; pg < book.getPages(); pg++)
            book.insert(pg, 0, header.replace("{page}", String.valueOf(pg + 1)).replace("{pages}", String.valueOf(book.getPages())));
        return book;
    }
}
