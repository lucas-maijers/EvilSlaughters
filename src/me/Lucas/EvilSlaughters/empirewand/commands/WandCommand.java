package me.Lucas.EvilSlaughters.empirewand.commands;

import me.Lucas.EvilSlaughters.Main;
import me.Lucas.EvilSlaughters.utils.Utils;
import me.Lucas.EvilSlaughters.managers.SubCommand;
import org.bukkit.entity.Player;

import java.util.List;

public class WandCommand extends SubCommand {

    private Main plugin;

    public WandCommand(Main plugin) {
        this.plugin = plugin;
    }

    @Override
    public void onCommand(Player p, String[] args) {
        if (p.hasPermission("evilslaughters.crimsonwand")) {
            p.getInventory().addItem(Utils.empireWand());
            p.sendMessage(Utils.prefix + Utils.chat("Je hebt een &c&lCrimson Wand &7gekregen!"));
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
        return null;
    }
}
