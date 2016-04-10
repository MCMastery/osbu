package com.dgrissom.osbu.main.listeners;

import com.dgrissom.osbu.main.OSBU;
import com.dgrissom.osbu.main.OSBUCommand;
import com.dgrissom.osbu.main.utilities.ArrayUtility;
import com.dgrissom.osbu.main.utilities.PlayerUtility;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class OSBUCommandListener implements Listener {
    @EventHandler
    public void onPlayerSendCommand(PlayerCommandPreprocessEvent evt) {
        if (evt.isCancelled())
            return;

        PlayerUtility player = new PlayerUtility(evt.getPlayer());
        // substring to remove "/"
        String[] split = evt.getMessage().substring(1).split(" ");
        String label = split[0];
        String[] args = new ArrayUtility(split).subArray(1).toStringArray();
        OSBUCommand cmd = OSBU.getInstance().commandFromName(label);
        // this is another plugin's command or a Bukkit command
        if (cmd != null) {
            executeCommand(player, cmd, args);
            // so it doesn't display "unknown command"
            evt.setCancelled(true);
        }
    }

    /**
     * Checks to see if an arg is a subcommand - if it is, run this method on that subcommand. Otherwise, just run the command.
     * (Recursive)
     */
    private void executeCommand(PlayerUtility sender, OSBUCommand cmd, String[] args) {
        // check if it is a subcommand
        OSBUCommand subCommand = null;
        if (args.length > 0)
            for (OSBUCommand subcmd : cmd.getSubCommands())
                if (subcmd.getLabel().equals(args[0]))
                    subCommand = subcmd;
        if (subCommand == null)
            cmd.execute(sender, args);
        else {
            String[] subCommandArgs = new ArrayUtility(args).subArray(1).toStringArray();
            executeCommand(sender, subCommand, subCommandArgs);
        }
    }
}
