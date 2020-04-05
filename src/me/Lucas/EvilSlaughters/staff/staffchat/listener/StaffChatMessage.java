package me.Lucas.EvilSlaughters.staff.staffchat.listener;

import me.Lucas.EvilSlaughters.Main;
import me.Lucas.EvilSlaughters.staff.staffchat.commands.StaffChatCommand;
import me.Lucas.EvilSlaughters.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class StaffChatMessage implements Listener {

    private Main plugin;


    public StaffChatMessage(Main plugin) {
        this.plugin = plugin;

        Bukkit.getPluginManager().registerEvents(this, plugin);
    }


    @EventHandler
    public void onStaffChatMessage(AsyncPlayerChatEvent e) {
        Player p = e.getPlayer();

        String message = e.getMessage();

        if (p.hasPermission("evilslaughters.staff.chat")) {
            if (message.startsWith("#") || StaffChatCommand.staffChatToggle.contains(p.getName())) {
                if (!(StaffChatCommand.staffChatHidden.contains(p.getName()))) {
                    e.setCancelled(true);
                    message = message.replaceFirst("#", "");
                    for (Player plr : Bukkit.getOnlinePlayers()) {
                        if (plr.hasPermission("evilslaughters.staff.chat")) {
                            if (!StaffChatCommand.staffChatHidden.contains(plr.getName())) {
                                plr.sendMessage(Utils.staffChat + Utils.chat(String.format("&3%s &8| &7%s", p.getName(), message)));
                            }
                        }
                    }
                }
            }
        }
    }

}
