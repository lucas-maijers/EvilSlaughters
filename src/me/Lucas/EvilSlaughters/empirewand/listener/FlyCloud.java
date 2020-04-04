package me.Lucas.EvilSlaughters.empirewand.listener;

import org.bukkit.Color;
import org.bukkit.Particle;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashSet;
import java.util.Set;

public class FlyCloud {
    public static Set<String> crimsonCloud = new HashSet<>();

    public static void crimsonFlyCloud(Player p, Plugin plugin) {
        World w = p.getWorld();

        new BukkitRunnable() {

            @Override
            public void run() {
                Particle.DustOptions black = new Particle.DustOptions(Color.BLACK, 1);
                Particle.DustOptions red = new Particle.DustOptions(rgb(99, 17, 16), 1);

                if (p.isFlying()) {
                    w.spawnParticle(Particle.SPELL_MOB, p.getLocation(), 0, 0.3, 0.3, 0.3, 0.01);
                    w.spawnParticle(Particle.SPELL_MOB, p.getLocation(), 0, 0.3, 0.3, 0.3, 0.01);
                    w.spawnParticle(Particle.SPELL_MOB, p.getLocation(), 0, 0.3, 0.3, 0.3, 0.01);
                    w.spawnParticle(Particle.REDSTONE, p.getLocation(), 5, 0.3, 0.2, 0.3, 0.1, black);
                    w.spawnParticle(Particle.REDSTONE, p.getLocation(), 5, 0.3, 0.2, 0.3, 0.1, red);
                }
                if (!crimsonCloud.contains(p.getName())) {
                    this.cancel();
                }
            }
        }.runTaskTimer(plugin, 0L, 1);
    }


    private static Color rgb(int red, int green, int blue) {
        return Color.fromRGB(red, green, blue);
    }
}
