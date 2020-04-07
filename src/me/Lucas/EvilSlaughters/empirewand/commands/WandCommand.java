package me.Lucas.EvilSlaughters.empirewand.commands;

import me.Lucas.EvilSlaughters.Main;
import me.Lucas.EvilSlaughters.managers.SubCommand;
import me.Lucas.EvilSlaughters.utils.Utils;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class WandCommand extends SubCommand {

    private Main plugin;

    private List<String> wandTypes = new ArrayList<>();

    public WandCommand(Main plugin) {
        this.plugin = plugin;

        wandTypes.add("crimson");
        wandTypes.add("warped");
        wandTypes.add("ender");
    }

    @Override
    public void onCommand(Player p, String[] args) {
        if (p.hasPermission("evilslaughters.wand")) {
            if (args.length == 1) {
                p.sendMessage(Utils.prefix + Utils.chat("Je moet een wandsoort invullen!"));
                p.sendMessage(Utils.chat("&7Je kan kiezen uit: Crimson, Warped en Ender"));
                return;
            }

            if (args[1].equalsIgnoreCase("crimson")) {
                p.getInventory().addItem(Utils.crimsonWand());
                p.sendMessage(Utils.prefix + Utils.chat("Je hebt een &c&lCrimson Wand &7gekregen!"));
            }

            if (args[1].equalsIgnoreCase("warped")) {
                p.getInventory().addItem(Utils.warpedWand());
                p.sendMessage(Utils.prefix + Utils.chat("Je hebt een &3&lWarped Wand &7gekregen!"));
            }

            if (args[1].equalsIgnoreCase("ender")) {
                p.getInventory().addItem(Utils.enderWand());
                p.sendMessage(Utils.prefix + Utils.chat("Je hebt een &5&lEnder Wand &7gekregen!"));
            }
        } else {
            p.sendMessage(Utils.noPermission);
        }
    }

    @Override
    public String name() {
        return plugin.cmdMngr.wand;
    }

    @Override
    public String info() {
        return "";
    }

    @Override
    public String[] aliases() {
        return new String[0];
    }

    @Override
    public List<String> getArguments(Player p, String[] args) {
        if (args.length == 2) {
            List<String> completionList = new ArrayList<>();
            if (!args[1].equals("")) {
                for (String s : wandTypes) {
                    if (s.startsWith(args[1].toLowerCase())) {
                        completionList.add(s);
                    }
                }
                return completionList;
            }
            return new ArrayList<>(wandTypes);
        }
        return null;
    }
}
