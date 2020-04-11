package me.Lucas.EvilSlaughters.staff.staffmode.listener;

import me.Lucas.EvilSlaughters.Main;
import me.Lucas.EvilSlaughters.staff.staffmode.commands.StaffModeCommand;
import me.Lucas.EvilSlaughters.staff.staffmode.inventory.OnlineStaffGUI;
import me.Lucas.EvilSlaughters.staff.staffmode.inventory.StaffMode;
import me.Lucas.EvilSlaughters.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.block.Barrel;
import org.bukkit.block.Chest;
import org.bukkit.block.EnderChest;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.*;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.Inventory;

import java.util.HashSet;
import java.util.Set;

public class StaffModeEvents implements Listener {

    private Main plugin;
    public static Player target;

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
                p.setCanPickupItems(true);
                p.getInventory().setItem(8, Utils.vanish());
                return;
            }
        }

        if (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {
            if (p.getInventory().getItemInMainHand().equals(Utils.vanish())) {
                for (Player all : Bukkit.getOnlinePlayers()) {
                    if (!(all.hasPermission("evilslaughters.staff.staffmode"))) {
                        all.hidePlayer(plugin, p);
                    } else if (StaffModeCommand.hasHiddenStaff.contains(all.getName())) {
                        all.hidePlayer(plugin, p);
                    }
                    vanishedPlayers.add(p);
                    p.setCanPickupItems(false);
                    p.getInventory().setItem(8, Utils.unvanish());
                }
            }
        }

        if (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {
            if (p.getInventory().getItemInMainHand().equals(Utils.onlineStaff(p))) {
                p.openInventory(OnlineStaffGUI.onlineStaffUI(p));
            }
        }
    }

    @EventHandler
    public void onDamageEvent(EntityDamageEvent e) {
        if (e.getEntity() instanceof Player) {
            Player p = (Player) e.getEntity();
            if (StaffModeCommand.inStaffMode.contains(p.getName())) {
                e.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onFoodLost(FoodLevelChangeEvent e) {
        Player p = (Player) e.getEntity();

        if (StaffModeCommand.inStaffMode.contains(p.getName())) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent e) {
        Player p = e.getEntity();

        if (StaffModeCommand.inStaffMode.contains(p.getName())) {
            e.getDrops().clear();
        }
    }

    @EventHandler
    public void onRespawn(PlayerRespawnEvent e) {
        Player p = e.getPlayer();

        if (StaffModeCommand.inStaffMode.contains(p.getName())) {
            p.getInventory().setContents(StaffMode.staffModeInv(p).getContents());
        }
    }

    @EventHandler
    public void onItemDrop(PlayerDropItemEvent e) {
        Player p = e.getPlayer();

        if (StaffModeCommand.inStaffMode.contains(p.getName())) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void modifyInventory(InventoryClickEvent e) {

        if (e.getInventory().getHolder() instanceof Player) {
            Player p = (Player) e.getInventory().getHolder();
            if (StaffModeCommand.inStaffMode.contains(p.getName())) {
                if (!(p.getGameMode().equals(GameMode.CREATIVE))) {
                    e.setCancelled(true);
                }
            }
        }
    }

    @EventHandler
    public void onEntityTarget(EntityTargetLivingEntityEvent e) {
        if (e.getTarget() instanceof Player) {
            Player p = (Player) e.getTarget();
            if (StaffModeCommand.inStaffMode.contains(p.getName())) {
                e.setCancelled(true);
            }
        }
    }
}
