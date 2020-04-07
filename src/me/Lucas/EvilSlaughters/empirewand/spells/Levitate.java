package me.Lucas.EvilSlaughters.empirewand.spells;

import me.Lucas.EvilSlaughters.utils.Utils;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.HashSet;
import java.util.Set;

public class Levitate {

    private static Set<String> playerLevitating = new HashSet<>();

    public static void fireSpell(Plugin plugin, Player p) {
        World w = p.getWorld();

        if (!playerLevitating.contains(p.getName())) {
            playerLevitating.add(p.getName());

            new BukkitRunnable() {

                Player target = getTarget(p);
                boolean hasStartedLevitation = false;

                @Override
                public void run() {

                    if (target == null) {
                        playerLevitating.remove(p.getName());
                        this.cancel();
                        return;
                    }

                    if (!hasStartedLevitation) {
                        p.sendMessage(Utils.prefix + Utils.chat("Je hebt de Levitation gestart!"));
                        hasStartedLevitation = true;
                    }

                    target.setAllowFlight(true);
                    target.setFlying(true);

                    Vector targetLocation = target.getLocation().toVector();
                    Vector wantedLocation = p.getEyeLocation().toVector().add(p.getLocation().getDirection().multiply(10));

                    target.setFallDistance(0);
                    Vector velocity = wantedLocation.subtract(targetLocation).multiply(0.4);
                    target.setVelocity(velocity);

                    // Particles

                    w.spawnParticle(Particle.SMOKE_LARGE, target.getLocation(), 10, 0, 0, 0, 0.5);
                    w.spawnParticle(Particle.ENCHANTMENT_TABLE, target.getLocation(), 10, 0.5, 0.5, 0.5, 0.01);

                    if (!playerLevitating.contains(p.getName())) {
                        p.sendMessage(Utils.prefix + Utils.chat("Je hebt de Levitation gestopt!"));
                        target.setFallDistance(0);
                        if (!target.getGameMode().equals(GameMode.CREATIVE)) {
                            target.setAllowFlight(false);
                            target.setFlying(false);
                        }
                        this.cancel();
                    }
                }
            }.runTaskTimer(plugin, 0, 0);
        } else {
            playerLevitating.remove(p.getName());
        }
    }

    private static Player getTarget(Player p) {
        for (Entity e : p.getNearbyEntities(10, 10, 10)) {
            if (e instanceof Player) {
                if (getLookingAt(p, (Player) e)) {
                    return (Player) e;
                }
            }
        }
        return null;
    }

    private static boolean getLookingAt(Player p1, Player p2) {
        Location eye = p1.getEyeLocation();
        Vector toEntity = p2.getEyeLocation().toVector().subtract(eye.toVector());
        double dot = toEntity.normalize().dot(eye.getDirection());

        return dot > 0.99D;
    }

}
