package me.Lucas.EvilSlaughters.staff.staffmode.commands;

import me.Lucas.EvilSlaughters.Main;
import me.Lucas.EvilSlaughters.managers.SubCommand;
import me.Lucas.EvilSlaughters.staff.staffmode.listener.StaffModeEvents;
import me.Lucas.EvilSlaughters.utils.Utils;
import org.bukkit.entity.Player;

import java.util.List;

public class HideStaffCommand extends SubCommand {

    private Main plugin;

    public HideStaffCommand(Main plugin) {
        this.plugin = plugin;
    }

    @Override
    public void onCommand(Player p, String[] args) {
        if (p.hasPermission("evilslaughters.staff.hide")) {
            if (!(StaffModeCommand.hasHiddenStaff.contains(p.getName()))) {
                p.sendMessage(Utils.prefix + Utils.chat("Je zult Staff die in vanish zitten niet langer zien!"));
                StaffModeCommand.hasHiddenStaff.add(p.getName());

                for (Player plr : StaffModeEvents.vanishedPlayers) {
                    p.hidePlayer(plugin, plr);
                }
            }
        }
    }

    @Override
    public String name() {
        return plugin.cmdMngr.hidestaff;
    }

    @Override
    public String info() {
        return null;
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
