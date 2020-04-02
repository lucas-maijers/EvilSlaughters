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

public class Comet {

    public static void fireSpell(Plugin plugin, Player p) {
        World w = p.getWorld();

        double y = p.getLocation().getY();

        Location spawnFireBall = p.getLocation();
        spawnFireBall.setY(y + 0.8);

        final Fireball fireball = p.getWorld().spawn(spawnFireBall, Fireball.class);
        fireball.setShooter(p);
        fireball.setVelocity(p.getEyeLocation().getDirection().multiply(3));
        fireball.setBounce(false);
        fireball.setIsIncendiary(true);
        fireball.setYield(0F);

        new BukkitRunnable() {

            @Override
            public void run() {
                if (!(fireball.isValid())) {
                    w.createExplosion(fireball.getLocation(), (float) 2, true, false, p);
                    this.cancel();
                }

                if (fireball.isValid()) {
                    final Firework fw = fireball.getWorld().spawn(fireball.getLocation(), Firework.class);
                    fw.setSilent(true);
                    FireworkMeta fmeta = fw.getFireworkMeta();
                    FireworkEffect fwEffect = FireworkEffect.builder().flicker(true).trail(false).with(FireworkEffect.Type.BALL).withColor(Color.ORANGE).build();

                    fmeta.addEffect(fwEffect);
                    fw.setFireworkMeta(fmeta);
                    fw.detonate();
                }

                if (fireball.getTicksLived() == 20 * 30) {
                    System.out.println("Er is een comet die langer dan 30 seconden leeft gedetecteerd, start verwijderen!");
                    fireball.remove();
                    this.cancel();
                }
            }
        }.runTaskTimer(plugin, 3, 1);
    }
}
