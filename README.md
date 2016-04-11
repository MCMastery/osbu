# OSBU
Change the way you program Bukkit plugins. OSBU (Open-Source Bukkit Utilities) is a new way of writing plugins which is faster and easier. No need to register your commands in your `plugin.yml`. Create subcommands easily.

#### Note: Please know how to program in Bukkit before using this. Don't ask questions like "wheredo i put teh publics`

## Utilities
OSBU has many utility classes that consist of helpful methods. They provide helpful methods for various objects.

List of utility classes:

- `StringUtility`
- `PlayerUtility`
- `ArrayUtility`
- `PluginUtility`
- `ScoreboardUtility`

Example plugin:
`OSBUExample.java:`

	package some.example.package;

    import com.dgrissom.osbu.main.OSBU;
    import com.dgrissom.osbu.main.utilities.PluginUtility;
    import org.bukkit.plugin.java.JavaPlugin;

    public class OSBUExample extends JavaPlugin {
        private static PluginUtility instance; /* allows us to reference this plugin later, as a PluginUtility */

        @Override
        public void onEnable() {
            instance = new PluginUtility(this) { /* this code will register our plugin with OSBU (you must have this!) */
                @Override
                public boolean enablePDFs() {
                    return false; /* do not enable Player Data Files to be generated (not actually doing anything at this version of OSBU) */
                }
            }.register(); // registers our plugin with OSBU, with PDFs disabled now
            OSBU.getInstance().getCommands().registerCommand(new ExampleCommand()); /* registers a command (see below) */
        }

        public static PluginUtility getInstance() { /* a method that lets us access the instance variable */
            return instance;
        }
    }

`ExampleCommand.java:`

	package some.example.package;

    import com.dgrissom.osbu.main.OSBUCommand;
    import com.dgrissom.osbu.main.utilities.PlayerUtility;

    public class ExampleCommand extends OSBUCommand {
        public ExampleCommand() {
            super(OSBUExample.getInstance(), "osbuexample", 0); /* creates a new command which can be used in-game with "/osbuexample". 0 means this command has 0 args. */
            addSubCommand(new OSBUCommand(OSBUExample.getInstance(), "sub", 0) {
                { /* a generic object initializer block */
                    addSubCommand(new OSBUCommand(OSBUExample.getInstance(), "sub1", 0) {
                        @Override
                        public void execute(PlayerUtility sender, String[] args) {
                            sender.sendFormattedMessage("This is an OSBU example subsubcommand #1!");
                        }
                    });
                    addSubCommand(new OSBUCommand(OSBUExample.getInstance(), "sub2", 0) {
                        @Override
                        public void execute(PlayerUtility sender, String[] args) {
                            sender.sendFormattedMessage("This is an OSBU example subsubcommand #2!");
                        }
                    });
                }
                @Override
                public void execute(PlayerUtility sender, String[] args) {
                    sender.sendFormattedMessage("This is an OSBU example subcommand!");
                }
            });
        }
        @Override
        public void execute(PlayerUtility sender, String[] args) { /* called when this command is run in-game */
            sender.sendFormattedMessage("This is an OSBU example command!");
        }
    }


Okay, this last class has a lot of repetitive code, so I'll explain it here.
- `/osbuexample` will tell the player "This is an OSBU example command!"
- `/osbuexample sub` will tell the player "This is an OSBU example subcommand!"
- `/osbuexample sub sub1` will tell the player "This is an OSBU example subsubcommand #1!"
- `/osbuexample sub sub2` will tell the player "This is an OSBU example subsubcommand #2!"

`OSBUCommand#addSubCommand(OSBUCommand)` adds a sub command to another command. You can add a sub command to a sub command to a sub command, if you wanted. Wait - what exactly *is* a subcommand?

 - `/somecommand something` You can make `something` be a subcommand. If you don't, it will be an argument for the command `somecommand`.
 - `/tell MCMastery I See you.` This is a Bukkit command. `/tell`. `MCMastery, I, See, you.` are the arguments.
 - `/time set 5465` This is another Bukkit command. `set` is the subcommand. `5465` is the argument.



### Scoreboard example
With a `PlayerUtility` object called `player`, this will show them their health on a scoreboard with a carousel display name.

    UpdatedText text = UpdatedText.generateCarousel("This is a very long scoreboard name, isn't it?", ScoreboardUtility.MAX_DISPLAY_NAME, 5, true);
    ScoreboardUtility scoreboard = new ScoreboardUtility();
    scoreboard.addObjective("player_health", ScoreboardUtility.Criteria.HEALTH, text, DisplaySlot.SIDEBAR).addRow(player.getName(), player.getHealthInt(), DisplaySlot.SIDEBAR);
    player.setScoreboard(scoreboard);
    
Output:
https://gyazo.com/c0538093f384390aac8195ca6081a293
(It does wrap around - this image doesn't show the whole output)
The `true` at the end of `UpdatedText.generateCarousel` tells it to generate spaces at the end of our message, so it has "padding" when the message wraps around.

This simple code will hide a scoreboard from a `PlayerUtility` object called `player`:

    player.removeScoreboard();

This is an awful tutorial... if you want to make a tutorial, please do so! PM me (MCMastery) on dev.bukkit.org a link to a Youtube video. I will be uploading more examples later.
