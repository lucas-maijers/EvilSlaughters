package me.Lucas.EvilSlaughters.managers;

import me.Lucas.EvilSlaughters.Main;
import me.Lucas.EvilSlaughters.empirewand.commands.FlyCloudCommand;
import me.Lucas.EvilSlaughters.empirewand.commands.WandCommand;
import me.Lucas.EvilSlaughters.staff.staffchat.commands.StaffChatCommand;
import me.Lucas.EvilSlaughters.utils.Utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CommandManager implements TabExecutor {

    private Main plugin;

    public String main = "evilslaughters";
    public String wand = "crimsonwand";
    public String flycloud = "flycloud";
    public String staffchat = "staffchat";

    public static ArrayList<String> commandList = new ArrayList<>();
    private ArrayList<SubCommand> commands = new ArrayList<>();

    public CommandManager(Main plugin) {
        this.plugin = plugin;
    }

    public void setup() {
        plugin.getCommand(main).setExecutor(this);

        this.commands.add(new WandCommand(plugin));
        this.commands.add(new FlyCloudCommand(plugin));
        this.commands.add(new StaffChatCommand(plugin));

        commandList.add(wand);
        commandList.add(flycloud);
        commandList.add(staffchat);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Dit gaat alleen via een speler");
            return true;
        }

        Player p = (Player) sender;

        if (cmd.getName().equalsIgnoreCase(main)) {
            if (args.length == 0) {
                p.sendMessage(Utils.chat("je moet wel iets erachter invullen he"));
                return true;
            }

            SubCommand target = this.get(args[0]);

            if (target == null) {
                p.sendMessage(Utils.chat("je moet wel iets erachter invullen he"));
                return true;
            }

            ArrayList<String> arrayList = new ArrayList<>(Arrays.asList(args));
            arrayList.remove(0);
            try {
                target.onCommand((Player) sender, args);
            } catch (Exception e) {
                p.sendMessage("Omg dikke error gevonden");

                e.printStackTrace();
            }
        }

        return true;
    }

    private SubCommand get(String name) {

        for (SubCommand scmd : this.commands) {
            if (scmd.name().equalsIgnoreCase(name)) {
                return scmd;
            }

            String[] aliases;
            int length = (aliases = scmd.aliases()).length;

            if (Arrays.stream(aliases, 0, length).anyMatch(name::equalsIgnoreCase)) {
                return scmd;
            }
        }
        return null;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {

        if (args.length == 1) {
            List<String> completionList = new ArrayList<>();

            if (!args[0].equals("")) {
                for (String s : commandList) {
                    if (s.startsWith(args[0].toLowerCase())) {
                        completionList.add(s);
                    }
                }
                return completionList;
            }

            return new ArrayList<>(commandList);
        } else if (args.length > 1) {
            for (SubCommand command : commands) {
                if (args[0].equalsIgnoreCase(command.name())) {
                    return command.getArguments((Player) sender, args);
                }
            }
        }
        return null;
    }
}
