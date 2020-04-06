package me.Lucas.EvilSlaughters.staff.staffmode.listener;

import me.Lucas.EvilSlaughters.Main;
import me.Lucas.EvilSlaughters.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.HashSet;
import java.util.Set;

public class StaffModeEvents implements Listener {

    private Main plugin;

    public static Set<Player> vanishedPlayers = new HashSet<>();

    public StaffModeEvents(Main plugin) {
        this.plugin = plugin;

        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onClick(PlayerInteractEvent e) {
        Player p = e.getPlayer();

        if (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {
            if (p.getInventory().getItemInMainHand().equals(Utils.unvanish())) {
                for (Player all : Bukkit.getOnlinePlayers()) {
                    all.showPlayer(plugin, p);
                }
                vanishedPlayers.remove(p);
                p.getInventory().setItem(8, Utils.vanish());
                return;
            }
        }

        if (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {
            if (p.getInventory().getItemInMainHand().equals(Utils.vanish())) {
                for (Player all : Bukkit.getOnlinePlayers()) {
                    if (!(all.hasPermission("evilslaughters.staff.staffmode"))) {
                        all.hidePlayer(plugin, p);

                    }
                    vanishedPlayers.add(p);
                    p.getInventory().setItem(8, Utils.unvanish());
                }
            }
        }
    }
}
