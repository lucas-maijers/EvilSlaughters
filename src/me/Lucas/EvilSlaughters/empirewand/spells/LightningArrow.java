package me.Lucas.EvilSlaughters.empirewand.spells;

import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

public class LightningArrow {

    public static void fireCrimsonSpell(Plugin plugin, Player p) {
        World w = p.getWorld();

        double y = p.getLocation().getY();

        Location arrowSpawn = p.getLocation();
        arrowSpawn.setY(y + 0.8);

        final Arrow arrow = p.getWorld().spawn(arrowSpawn, Arrow.class);
        arrow.setShooter(p);
        arrow.setVelocity(p.getEyeLocation().getDirection().multiply(4));
        arrow.setDamage(0);
        arrow.setSilent(true);
        arrow.setBounce(true);
        arrow.setInvulnerable(false);
        arrow.setGravity(false);
        arrow.setCustomName("Lightning Arrow");

        new BukkitRunnable() {
            @Override
            public void run() {
                if (!(arrow.isValid())) {
                    arrow.remove();
                    this.cancel();
                }

                if (!(arrow.isOnGround())) {
                    final Firework fw = arrow.getWorld().spawn(arrow.getLocation(), Firework.class);
                    fw.setSilent(true);
                    FireworkMeta fmeta = fw.getFireworkMeta();
                    FireworkEffect fwEffect = FireworkEffect.builder().flicker(true).trail(false).with(FireworkEffect.Type.BALL).withColor(Color.fromRGB(99, 17, 16), Color.BLACK).build();

                    fmeta.addEffect(fwEffect);
                    fw.setFireworkMeta(fmeta);
                    fw.detonate();
                } else {
                    w.strikeLightning(arrow.getLocation());
                    w.strikeLightning(arrow.getLocation());
                    w.strikeLightning(arrow.getLocation());
                    w.createExplosion(arrow.getLocation(), (float) 1.5, true, false);
                    arrow.remove();
                    this.cancel();
                }

                if (arrow.isDead()) {
                    arrow.remove();
                    this.cancel();
                }

                if (arrow.getTicksLived() == 20 * 30) {
                    System.out.println("Er is een lightning arrow die langer dan 30 seconden leeft gedetecteerd, start verwijderen!");
                    arrow.remove();
                    this.cancel();
                }
            }
        }.runTaskTimer(plugin, 3, 1);
    }

    public static void fireWarpedSpell(Plugin plugin, Player p) {
        World w = p.getWorld();

        double y = p.getLocation().getY();

        Location arrowSpawn = p.getLocation();
        arrowSpawn.setY(y + 0.8);

        final Arrow arrow = p.getWorld().spawn(arrowSpawn, Arrow.class);
        arrow.setShooter(p);
        arrow.setVelocity(p.getEyeLocation().getDirection().multiply(4));
        arrow.setDamage(0);
        arrow.setSilent(true);
        arrow.setBounce(true);
        arrow.setInvulnerable(false);
        arrow.setGravity(false);
        arrow.setCustomName("Lightning Arrow");

        new BukkitRunnable() {
            @Override
            public void run() {
                if (!(arrow.isValid())) {
                    arrow.remove();
                    this.cancel();
                }

                if (!(arrow.isOnGround())) {
                    final Firework fw = arrow.getWorld().spawn(arrow.getLocation(), Firework.class);
                    fw.setSilent(true);
                    FireworkMeta fmeta = fw.getFireworkMeta();
                    FireworkEffect fwEffect = FireworkEffect.builder().flicker(true).trail(false).with(FireworkEffect.Type.BALL).withColor(Color.TEAL, Color.BLACK).build();

                    fmeta.addEffect(fwEffect);
                    fw.setFireworkMeta(fmeta);
                    fw.detonate();
                } else {
                    w.strikeLightning(arrow.getLocation());
                    w.strikeLightning(arrow.getLocation());
                    w.strikeLightning(arrow.getLocation());
                    w.createExplosion(arrow.getLocation(), (float) 1.5, true, false);
                    arrow.remove();
                    this.cancel();
                }

                if (arrow.isDead()) {
                    arrow.remove();
                    this.cancel();
                }

                if (arrow.getTicksLived() == 20 * 30) {
                    System.out.println("Er is een lightning arrow die langer dan 30 seconden leeft gedetecteerd, start verwijderen!");
                    arrow.remove();
                    this.cancel();
                }
            }
        }.runTaskTimer(plugin, 3, 1);
    }

    public static void fireEnderSpell(Plugin plugin, Player p) {
        World w = p.getWorld();

        double y = p.getLocation().getY();

        Location arrowSpawn = p.getLocation();
        arrowSpawn.setY(y + 0.8);

        final Arrow arrow = p.getWorld().spawn(arrowSpawn, Arrow.class);
        arrow.setShooter(p);
        arrow.setVelocity(p.getEyeLocation().getDirection().multiply(4));
        arrow.setDamage(0);
        arrow.setSilent(true);
        arrow.setBounce(true);
        arrow.setInvulnerable(false);
        arrow.setGravity(false);
        arrow.setCustomName("Lightning Arrow");

        new BukkitRunnable() {
            @Override
            public void run() {
                if (!(arrow.isValid())) {
                    arrow.remove();
                    this.cancel();
                }

                if (!(arrow.isOnGround())) {
                    final Firework fw = arrow.getWorld().spawn(arrow.getLocation(), Firework.class);
                    fw.setSilent(true);
                    FireworkMeta fmeta = fw.getFireworkMeta();
                    FireworkEffect fwEffect = FireworkEffect.builder().flicker(true).trail(false).with(FireworkEffect.Type.BALL).withColor(Color.PURPLE, Color.BLACK).build();

                    fmeta.addEffect(fwEffect);
                    fw.setFireworkMeta(fmeta);
                    fw.detonate();
                } else {
                    w.strikeLightning(arrow.getLocation());
                    w.strikeLightning(arrow.getLocation());
                    w.strikeLightning(arrow.getLocation());
                    w.createExplosion(arrow.getLocation(), (float) 1.5, true, false);
                    arrow.remove();
                    this.cancel();
                }

                if (arrow.isDead()) {
                    arrow.remove();
                    this.cancel();
                }

                if (arrow.getTicksLived() == 20 * 30) {
                    System.out.println("Er is een lightning arrow die langer dan 30 seconden leeft gedetecteerd, start verwijderen!");
                    arrow.remove();
                    this.cancel();
                }
            }
        }.runTaskTimer(plugin, 3, 1);
    }

}
