package me.Lucas.EvilSlaughters.staff.staffmode.listener;

import me.Lucas.EvilSlaughters.Main;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoin implements Listener {

    private Main plugin;

    public PlayerJoin(Main plugin) {
        this.plugin = plugin;

        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();

        if (!(p.hasPermission("evilslaughters.staffmode"))) {
            for (Player plr : StaffModeEvents.vanishedPlayers) {
                if (p.canSee(plr)) {
                    p.hidePlayer(plugin, plr);
                }
            }

            for (Player all : Bukkit.getOnlinePlayers()) {
                if (!StaffModeEvents.vanishedPlayers.contains(all)) {
                    if (!(p.canSee(all))) {
                        p.showPlayer(plugin, all);
                    }
                }
            }
        }
    }
}
