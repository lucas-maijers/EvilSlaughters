package me.Lucas.EvilSlaughters.empirewand.spells;

import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.*;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import static java.lang.Math.cos;
import static java.lang.Math.sin;

public class LightningStorm {

    public static void fireCrimsonSpell(Plugin plugin, Player p) {
        World w = p.getWorld();
        Location loc = p.getLocation();

        ArmorStand as = (ArmorStand) p.getWorld().spawnEntity(p.getLocation(), EntityType.ARMOR_STAND);
        as.setVisible(false);
        as.setGravity(false);

        double posX = p.getLocation().getX();
        double posZ = p.getLocation().getZ();
        Location loc2 = p.getLocation();
        double y = p.getLocation().getY() + 30;

        new BukkitRunnable() {

            int amount = 96;

            double t = 0;
            double t2 = 0;
            double r = 20;

            @Override
            public void run() {

                t = t + Math.PI / amount;
                t2 = t2 - Math.PI / amount;

                double x = r * cos(t);
                double z = r * sin(t);

                double x2 = r * cos(t2);
                double z2 = r * sin(t2);

                loc.setX(posX + x);
                loc.setY(y);
                loc.setZ(posZ + z);

                loc2.setX(posX + x2);
                loc2.setY(y);
                loc2.setZ(posZ + z2);

                final Firework fw = w.spawn(loc, Firework.class);
                final Firework fw2 = w.spawn(loc2, Firework.class);
                fw.setSilent(true);
                fw2.setSilent(true);
                FireworkMeta fmeta = fw.getFireworkMeta();
                FireworkEffect fwEffect = FireworkEffect.builder().flicker(false).trail(false).with(FireworkEffect.Type.BALL).withColor(Color.fromRGB(99, 17, 16), Color.BLACK).build();

                FireworkMeta fmeta2 = fw.getFireworkMeta();
                FireworkEffect fwEffect2 = FireworkEffect.builder().flicker(false).trail(false).with(FireworkEffect.Type.BALL).withColor(Color.fromRGB(99, 17, 16), Color.BLACK).build();

                fmeta.addEffect(fwEffect);
                fmeta.setPower(0);
                fw.setFireworkMeta(fmeta);

                fmeta2.addEffect(fwEffect2);
                fmeta2.setPower(0);
                fw2.setFireworkMeta(fmeta2);

                fw.detonate();
                fw2.detonate();

                if (t > Math.PI * 1) {

                    Location asLoc = as.getLocation();
                    asLoc.setY(as.getLocation().getY() + 18);

                    final Firework endFW = w.spawn(asLoc, Firework.class);
                    endFW.setSilent(true);
                    FireworkMeta endFWmeta = endFW.getFireworkMeta();
                    FireworkEffect endFwEffect = FireworkEffect.builder().flicker(false).trail(false).with(FireworkEffect.Type.STAR).withColor(Color.fromRGB(99, 17, 16), Color.BLACK).build();

                    endFWmeta.addEffect(endFwEffect);
                    endFWmeta.setPower(0);
                    endFW.setFireworkMeta(endFWmeta);

                    endFW.detonate();

                    fw.detonate();
                    fw2.detonate();
                    for (Entity t : as.getNearbyEntities(r, 30, r)) {
                        if (t instanceof Player || t instanceof Monster || t instanceof Phantom) {
                            if (!(t == p)) {
                                t.getWorld().strikeLightning(t.getLocation());
                            }
                        }
                    }
                    as.remove();
                    this.cancel();
                }
            }
        }.runTaskTimer(plugin, 0, 1);
    }

    public static void fireWarpedSpell(Plugin plugin, Player p) {
        World w = p.getWorld();
        Location loc = p.getLocation();

        ArmorStand as = (ArmorStand) p.getWorld().spawnEntity(p.getLocation(), EntityType.ARMOR_STAND);
        as.setVisible(false);
        as.setGravity(false);

        double posX = p.getLocation().getX();
        double posZ = p.getLocation().getZ();
        Location loc2 = p.getLocation();
        double y = p.getLocation().getY() + 30;

        new BukkitRunnable() {

            int amount = 96;

            double t = 0;
            double t2 = 0;
            double r = 20;

            @Override
            public void run() {

                t = t + Math.PI / amount;
                t2 = t2 - Math.PI / amount;

                double x = r * cos(t);
                double z = r * sin(t);

                double x2 = r * cos(t2);
                double z2 = r * sin(t2);

                loc.setX(posX + x);
                loc.setY(y);
                loc.setZ(posZ + z);

                loc2.setX(posX + x2);
                loc2.setY(y);
                loc2.setZ(posZ + z2);

                final Firework fw = w.spawn(loc, Firework.class);
                final Firework fw2 = w.spawn(loc2, Firework.class);
                fw.setSilent(true);
                fw2.setSilent(true);
                FireworkMeta fmeta = fw.getFireworkMeta();
                FireworkEffect fwEffect = FireworkEffect.builder().flicker(false).trail(false).with(FireworkEffect.Type.BALL).withColor(Color.TEAL, Color.BLACK).build();

                FireworkMeta fmeta2 = fw.getFireworkMeta();
                FireworkEffect fwEffect2 = FireworkEffect.builder().flicker(false).trail(false).with(FireworkEffect.Type.BALL).withColor(Color.TEAL, Color.BLACK).build();

                fmeta.addEffect(fwEffect);
                fmeta.setPower(0);
                fw.setFireworkMeta(fmeta);

                fmeta2.addEffect(fwEffect2);
                fmeta2.setPower(0);
                fw2.setFireworkMeta(fmeta2);

                fw.detonate();
                fw2.detonate();

                if (t > Math.PI * 1) {

                    Location asLoc = as.getLocation();
                    asLoc.setY(as.getLocation().getY() + 18);

                    final Firework endFW = w.spawn(asLoc, Firework.class);
                    endFW.setSilent(true);
                    FireworkMeta endFWmeta = endFW.getFireworkMeta();
                    FireworkEffect endFwEffect = FireworkEffect.builder().flicker(false).trail(false).with(FireworkEffect.Type.STAR).withColor(Color.TEAL, Color.BLACK).build();

                    endFWmeta.addEffect(endFwEffect);
                    endFWmeta.setPower(0);
                    endFW.setFireworkMeta(endFWmeta);

                    endFW.detonate();

                    fw.detonate();
                    fw2.detonate();
                    for (Entity t : as.getNearbyEntities(r, 30, r)) {
                        if (t instanceof Player || t instanceof Monster || t instanceof Phantom) {
                            if (!(t == p)) {
                                t.getWorld().strikeLightning(t.getLocation());
                            }
                        }
                    }
                    as.remove();
                    this.cancel();
                }
            }
        }.runTaskTimer(plugin, 0, 1);
    }

    public static void fireEnderSpell(Plugin plugin, Player p) {
        World w = p.getWorld();
        Location loc = p.getLocation();

        ArmorStand as = (ArmorStand) p.getWorld().spawnEntity(p.getLocation(), EntityType.ARMOR_STAND);
        as.setVisible(false);
        as.setGravity(false);

        double posX = p.getLocation().getX();
        double posZ = p.getLocation().getZ();
        Location loc2 = p.getLocation();
        double y = p.getLocation().getY() + 30;

        new BukkitRunnable() {

            int amount = 96;

            double t = 0;
            double t2 = 0;
            double r = 20;

            @Override
            public void run() {

                t = t + Math.PI / amount;
                t2 = t2 - Math.PI / amount;

                double x = r * cos(t);
                double z = r * sin(t);

                double x2 = r * cos(t2);
                double z2 = r * sin(t2);

                loc.setX(posX + x);
                loc.setY(y);
                loc.setZ(posZ + z);

                loc2.setX(posX + x2);
                loc2.setY(y);
                loc2.setZ(posZ + z2);

                final Firework fw = w.spawn(loc, Firework.class);
                final Firework fw2 = w.spawn(loc2, Firework.class);
                fw.setSilent(true);
                fw2.setSilent(true);
                FireworkMeta fmeta = fw.getFireworkMeta();
                FireworkEffect fwEffect = FireworkEffect.builder().flicker(false).trail(false).with(FireworkEffect.Type.BALL).withColor(Color.PURPLE, Color.BLACK).build();

                FireworkMeta fmeta2 = fw.getFireworkMeta();
                FireworkEffect fwEffect2 = FireworkEffect.builder().flicker(false).trail(false).with(FireworkEffect.Type.BALL).withColor(Color.PURPLE, Color.BLACK).build();

                fmeta.addEffect(fwEffect);
                fmeta.setPower(0);
                fw.setFireworkMeta(fmeta);

                fmeta2.addEffect(fwEffect2);
                fmeta2.setPower(0);
                fw2.setFireworkMeta(fmeta2);

                fw.detonate();
                fw2.detonate();

                if (t > Math.PI * 1) {

                    Location asLoc = as.getLocation();
                    asLoc.setY(as.getLocation().getY() + 18);

                    final Firework endFW = w.spawn(asLoc, Firework.class);
                    endFW.setSilent(true);
                    FireworkMeta endFWmeta = endFW.getFireworkMeta();
                    FireworkEffect endFwEffect = FireworkEffect.builder().flicker(false).trail(false).with(FireworkEffect.Type.STAR).withColor(Color.PURPLE, Color.BLACK).build();

                    endFWmeta.addEffect(endFwEffect);
                    endFWmeta.setPower(0);
                    endFW.setFireworkMeta(endFWmeta);

                    endFW.detonate();

                    fw.detonate();
                    fw2.detonate();
                    for (Entity t : as.getNearbyEntities(r, 30, r)) {
                        if (t instanceof Player || t instanceof Monster || t instanceof Phantom) {
                            if (!(t == p)) {
                                t.getWorld().strikeLightning(t.getLocation());
                            }
                        }
                    }
                    as.remove();
                    this.cancel();
                }
            }
        }.runTaskTimer(plugin, 0, 1);
    }
}
