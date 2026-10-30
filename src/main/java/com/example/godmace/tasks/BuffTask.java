package com.example.godmace.tasks;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.inventory.ItemStack;

public class BuffTask implements Runnable {
    @Override
    public void run() {
        for (Player p : Bukkit.getOnlinePlayers()) {
            boolean mace = false, str = false, luck = false, speed = false;
            for (ItemStack item : p.getInventory().getContents()) {
                if (item == null || !item.hasItemMeta()) continue;
                String name = item.getItemMeta().getDisplayName();
                if (name.contains("Dragon Mace")) mace = true;
                if (name.contains("Strength Rune")) str = true;
                if (name.contains("Luck Rune")) luck = true;
                if (name.contains("Speed Rune")) speed = true;
            }

            if (mace) {
                p.addPotionEffect(new PotionEffect(PotionEffectType.STRENGTH, 40, 0, true, false));
                p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 40, 0, true, false));
                p.addPotionEffect(new PotionEffect(PotionEffectType.RESISTANCE, 40, 0, true, false));
                p.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 40, 0, true, false));
            }
            if (str) {
                p.addPotionEffect(new PotionEffect(PotionEffectType.STRENGTH, 40, mace ? 2 : 1, true, false));
            }
            if (luck) {
                p.addPotionEffect(new PotionEffect(PotionEffectType.HERO_OF_THE_VILLAGE, 40, 2, true, false));
                p.addPotionEffect(new PotionEffect(PotionEffectType.LUCK, 40, 2, true, false));
            }
            if (speed) {
                p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 40, 2, true, false));
            }
        }
    }
}
