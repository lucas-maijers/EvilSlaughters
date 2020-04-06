package me.Lucas.EvilSlaughters.staff.staffmode.listener;

import me.Lucas.EvilSlaughters.Main;
import me.Lucas.EvilSlaughters.staff.staffmode.inventory.OnlineStaffGUI;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class InventoryClick implements Listener {

    private Main plugin;

    public InventoryClick(Main plugin) {
        this.plugin = plugin;

        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onClick(InventoryClickEvent e) {
        String title = e.getView().getTitle();

        if (title.equals(OnlineStaffGUI.invname)) {
            e.setCancelled(true);
        }
    }
}
