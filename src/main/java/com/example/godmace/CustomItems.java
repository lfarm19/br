package com.example.godmace;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.Bukkit;
import java.util.Arrays;

public class CustomItems {
    public static ItemStack DRAGON_MACE, DASH_RUNE, STRENGTH_RUNE, LIFESTEAL_RUNE, LUCK_RUNE, SPEED_RUNE;

    public static void init(GodMacePlugin plugin) {
        DRAGON_MACE = new ItemStack(Material.MACE);
        ItemMeta maceMeta = DRAGON_MACE.getItemMeta();
        maceMeta.setDisplayName("§5Dragon Mace");
        maceMeta.setCustomModelData(1001);
        maceMeta.setLore(Arrays.asList("§7Legendary Weapon", "§c+ Infinite Strength I", "§b+ Infinite Speed I", "§9+ Infinite Resistance I", "§6+ Infinite Fire Resistance"));
        DRAGON_MACE.setItemMeta(maceMeta);
        NamespacedKey maceKey = new NamespacedKey(plugin, "dragon_mace");
        ShapedRecipe maceRecipe = new ShapedRecipe(maceKey, DRAGON_MACE);
        maceRecipe.shape("HBH", "BMB", "HBH");
        maceRecipe.setIngredient('H', Material.DRAGON_HEAD); maceRecipe.setIngredient('B', Material.DRAGON_BREATH); maceRecipe.setIngredient('M', Material.MACE);
        Bukkit.addRecipe(maceRecipe);

        DASH_RUNE = createRune("§eDash Rune", 2001, "§7Ability Rune", "§eRight-Click to Dash upwards!", "§aGrants Immunity to Fall Damage.");
        NamespacedKey dashKey = new NamespacedKey(plugin, "dash_rune");
        ShapedRecipe dashRecipe = new ShapedRecipe(dashKey, DASH_RUNE);
        dashRecipe.shape("SRW", "RBR", "WRS");
        dashRecipe.setIngredient('S', Material.SUGAR); dashRecipe.setIngredient('R', Material.RABBIT_FOOT); dashRecipe.setIngredient('W', Material.WIND_CHARGE); dashRecipe.setIngredient('B', Material.BOOK);
        Bukkit.addRecipe(dashRecipe);

        STRENGTH_RUNE = createRune("§4Strength Rune", 2002, "§7Passive Rune", "§c+ Infinite Strength II", "§c(Upgrades to Strength III with Dragon Mace)");
        NamespacedKey strKey = new NamespacedKey(plugin, "strength_rune");
        ShapedRecipe strRecipe = new ShapedRecipe(strKey, STRENGTH_RUNE);
        strRecipe.shape("PWP", "SNS", "PWP");
        strRecipe.setIngredient('P', Material.PLAYER_HEAD); strRecipe.setIngredient('W', Material.WITHER_SKELETON_SKULL); strRecipe.setIngredient('S', Material.POTION); strRecipe.setIngredient('N', Material.NETHERITE_SWORD);
        Bukkit.addRecipe(strRecipe);

        LIFESTEAL_RUNE = createRune("§cLifesteal Rune", 2003, "§7Combat Rune", "§c+ 1 Max Heart per Kill", "§4- Steals max health from victims!");
        NamespacedKey lifeKey = new NamespacedKey(plugin, "lifesteal_rune");
        ShapedRecipe lifeRecipe = new ShapedRecipe(lifeKey, LIFESTEAL_RUNE);
        lifeRecipe.shape("TET", "EPE", "TET");
        lifeRecipe.setIngredient('T', Material.TOTEM_OF_UNDYING); lifeRecipe.setIngredient('E', Material.ENCHANTED_GOLDEN_APPLE); lifeRecipe.setIngredient('P', Material.POTION); 
        Bukkit.addRecipe(lifeRecipe);

        LUCK_RUNE = createRune("§aLuck Rune", 2004, "§7Passive Rune", "§a+ Infinite Luck III", "§2+ Hero of the Village III");
        NamespacedKey luckKey = new NamespacedKey(plugin, "luck_rune");
        ShapedRecipe luckRecipe = new ShapedRecipe(luckKey, LUCK_RUNE);
        luckRecipe.shape("EGE", "DND", "EGE");
        luckRecipe.setIngredient('E', Material.EMERALD); luckRecipe.setIngredient('G', Material.GOLD_INGOT); luckRecipe.setIngredient('D', Material.DIAMOND_BLOCK); luckRecipe.setIngredient('N', Material.NETHERITE_INGOT);
        Bukkit.addRecipe(luckRecipe);

        SPEED_RUNE = createRune("§bSpeed Rune", 2005, "§7Passive Rune", "§b+ Infinite Speed III");
        NamespacedKey speedKey = new NamespacedKey(plugin, "speed_rune");
        ShapedRecipe speedRecipe = new ShapedRecipe(speedKey, SPEED_RUNE);
        speedRecipe.shape("ASA", "SRS", "ASA");
        speedRecipe.setIngredient('A', Material.AMETHYST_SHARD); speedRecipe.setIngredient('S', Material.SUGAR); speedRecipe.setIngredient('R', Material.RABBIT_FOOT);
        Bukkit.addRecipe(speedRecipe);
    }

    private static ItemStack createRune(String name, int customModelData, String... lore) {
        ItemStack item = new ItemStack(Material.PAPER);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(name);
        meta.setCustomModelData(customModelData);
        meta.setLore(Arrays.asList(lore));
        item.setItemMeta(meta);
        return item;
    }
}
