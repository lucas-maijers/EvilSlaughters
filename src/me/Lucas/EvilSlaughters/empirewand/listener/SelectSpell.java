package me.Lucas.EvilSlaughters.empirewand.listener;

import me.Lucas.EvilSlaughters.Main;
import me.Lucas.EvilSlaughters.empirewand.spells.*;
import me.Lucas.EvilSlaughters.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.Particle;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SelectSpell implements Listener {

    private Main plugin;

    public Map<String, Integer> selectedSpell = new HashMap<>();

    private List<String> spells = new ArrayList<>();

    public SelectSpell(Main plugin) {
        this.plugin = plugin;

        spells.add("Comet");
        spells.add("Confuse");
        spells.add("Explosive");
        spells.add("Launch");
        spells.add("Leap");
        spells.add("Levitate");
        spells.add("Lightning Arrow");
        spells.add("Lightning Storm");
        spells.add("Spark");


        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onSpellSelect(PlayerInteractEvent e) {
        Player p = e.getPlayer();

        if (e.getAction() == Action.RIGHT_CLICK_AIR) {
            if (p.getInventory().getItemInMainHand().equals(Utils.crimsonWand())) {
                if (p.hasPermission("evilslaughters.crimsonwand.use")) {

                    if (!selectedSpell.containsKey(p.getName())) {
                        selectedSpell.put(p.getName(), 0);
                    }

                    if (p.isSneaking()) {
                        if (selectedSpell.get(p.getName()) > 1) {
                            selectedSpell.replace(p.getName(), selectedSpell.get(p.getName()) - 1);
                        } else if (selectedSpell.get(p.getName()) == 1) {
                            selectedSpell.replace(p.getName(), spells.size());
                        }
                    } else {
                        if (selectedSpell.get(p.getName()) < spells.size()) {
                            selectedSpell.replace(p.getName(), selectedSpell.get(p.getName()) + 1);
                        } else if (selectedSpell.get(p.getName()) == spells.size()) {
                            selectedSpell.replace(p.getName(), 1);
                        }
                    }

                    p.getWorld().spawnParticle(Particle.SPELL_WITCH, p.getLocation(), 20, 0.3, 0.5, 0.3, 0.1);
                    p.sendMessage(Utils.prefix + Utils.chat(String.format("Je hebt de spell &6%s &7geselecteerd!", spells.get(selectedSpell.get(p.getName()) - 1))));
                }
            }
        }
    }

    @EventHandler
    public void onCrimsonSpellCast(PlayerInteractEvent e) {
        Player p = e.getPlayer();

        if (e.getAction() == Action.LEFT_CLICK_AIR || e.getAction() == Action.LEFT_CLICK_BLOCK) {
            if (p.getInventory().getItemInMainHand().equals(Utils.crimsonWand())) {
                if (p.hasPermission("evilslaughters.crimsonwand.use")) {
                    for (Map.Entry<String, Integer> spell : selectedSpell.entrySet()) {
                        if (spell.getKey().equals(p.getName())) {
                            switch (spell.getValue()) {
                                case 1:
                                    Comet.fireSpell(plugin, p);
                                    break;
                                case 2:
                                    Confuse.fireCrimsonSpell(plugin, p);
                                    break;
                                case 3:
                                    Explosive.fireCrimsonSpell(plugin, p);
                                    break;
                                case 4:
                                    Launch.fireCrimsonSpell(plugin, p);
                                    break;
                                case 5:
                                    Leap.fireSpell(plugin, p);
                                    break;
                                case 6:
                                    Levitate.fireCrimsonSpell(plugin, p);
                                    break;
                                case 7:
                                    LightningArrow.fireCrimsonSpell(plugin, p);
                                    break;
                                case 8:
                                    LightningStorm.fireCrimsonSpell(plugin, p);
                                    break;
                                case 9:
                                    Spark.fireCrimsonSpell(plugin, p);
                                    break;
                            }
                        }
                    }
                }
            }
        }
    }

    @EventHandler
    public void onFallDamage(EntityDamageEvent e) {
        if (e.getEntity() instanceof Player) {
            Player p = (Player) e.getEntity();
            if (p.getInventory().getItemInMainHand().equals(Utils.crimsonWand())) {
                if (selectedSpell.get(p.getName()) == 4) {
                    if (e.getCause() == EntityDamageEvent.DamageCause.FALL) {
                        e.setCancelled(true);
                    }
                }
            }
        }
    }

    @EventHandler
    public void onArrowDamage(EntityDamageByEntityEvent e) {
        if (e.getCause() == EntityDamageEvent.DamageCause.PROJECTILE) {
            if (e.getDamager() instanceof Arrow) {
                Arrow arrow = (Arrow) e.getDamager();
                if (arrow.getCustomName() != null) {
                    if (arrow.getCustomName().equals("Lightning Arrow")) {
                        arrow.remove();
                    }
                }

            }
        }
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent e) {
        Player p = e.getPlayer();

        if (p.getInventory().getItemInMainHand().equals(Utils.crimsonWand())) {
            e.setCancelled(true);
        }
    }
}
