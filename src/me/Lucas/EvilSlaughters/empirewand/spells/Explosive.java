package me.Lucas.EvilSlaughters.empirewand.spells;

import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

public class Explosive {

    public static void fireCrimsonSpell(Plugin plugin, Player p) {
        World w = p.getWorld();

        double y = p.getLocation().getY();

        Location spawnFireball = p.getLocation();
        spawnFireball.setY(y + 0.8);

        final Fireball fireball = p.getWorld().spawn(spawnFireball, Fireball.class);
        fireball.setShooter(p);
        fireball.setVelocity(p.getEyeLocation().getDirection().multiply(3));
        fireball.setBounce(false);
        fireball.setIsIncendiary(false);
        fireball.setYield(0F);

        new BukkitRunnable() {
            @Override
            public void run() {
                if (!(fireball.isValid())) {
                    w.createExplosion(fireball.getLocation(), 10F, true, true, p);
                    this.cancel();
                }

                if (fireball.isValid()) {
                    final Firework fw = fireball.getWorld().spawn(fireball.getLocation(), Firework.class);
                    fw.setSilent(true);
                    FireworkMeta fmeta = fw.getFireworkMeta();
                    FireworkEffect fwEffect = FireworkEffect.builder().flicker(true).trail(false).with(FireworkEffect.Type.BALL).withColor(Color.fromRGB(99, 17, 16), Color.BLACK).build();

                    fmeta.addEffect(fwEffect);
                    fw.setFireworkMeta(fmeta);
                    fw.detonate();
                }
            }
        }.runTaskTimer(plugin, 3, 1);
    }
}
