package de.randomStone42.craftablebackpacks.util;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.ShapedRecipe;

import de.randomStone42.craftablebackpacks.main.Main;

public class RecipeLoader {

	@SuppressWarnings("deprecation")
	public void registerRecipes() {
		FileConfiguration config = Main.getPlugin().getConfig();
		String[] lore = { "§6Hardened Leather", "§6which can be used to craft", "§6stronger Backpacks!" };
		ItemStack hardenedLeather = new ItemBuilder(Material.LEATHER).setName("§6§lhardened Leather").setLore(lore)
				.build();
		ShapedRecipe hardenedLeatherRecipe = new ShapedRecipe(new NamespacedKey(Main.getPlugin(), "hardenedLeather"),
				hardenedLeather);
		hardenedLeatherRecipe.shape(" L ", "LIL", " L ");
		hardenedLeatherRecipe.setIngredient('L', Material.LEATHER);
		hardenedLeatherRecipe.setIngredient('I', Material.IRON_INGOT);

		lore[0] = "§6Super Strong Leather";
		lore[1] = "§6which can be used to craft";
		lore[2] = "§6the biggest Backpack!";
		ItemStack superStrongLeather = new ItemBuilder(Material.LEATHER).setName("§6§lsuper strong Leather")
				.setLore(lore).build();
		ShapedRecipe superStrongLeatherRecipe = new ShapedRecipe(
				new NamespacedKey(Main.getPlugin(), "superStrongLeather"), superStrongLeather);
		superStrongLeatherRecipe.shape(" H ", "HDH", " H ");
		superStrongLeatherRecipe.setIngredient('H', new RecipeChoice.ExactChoice(hardenedLeather));
		superStrongLeatherRecipe.setIngredient('D', Material.DIAMOND);

		ItemStack smallBackpack = new LeatherArmorBuilder(Material.LEATHER_CHESTPLATE).setName("§6§lSmall Backpack")
				.setLore("§6A Small Backpack which can hold up to §6"+Main.getPlugin().getConfig().getString("SmallBackpack.Size") +" §6Items").setColor(config.getInt("SmallBackpack.Color.R"), config.getInt("SmallBackpack.Color.G"), config.getInt("SmallBackpack.Color.B")).setUnbreakable()
				.build();
		ShapedRecipe smallBackpackRecipe = new ShapedRecipe(new NamespacedKey(Main.getPlugin(), "SmallBackpack"),
				smallBackpack);
		smallBackpackRecipe.shape("LLL", "LCL", "LLL");
		smallBackpackRecipe.setIngredient('L', Material.LEATHER);
		smallBackpackRecipe.setIngredient('C', Material.CHEST);

		ItemStack mediumBackpack = new LeatherArmorBuilder(Material.LEATHER_CHESTPLATE).setName("§6§lMedium Backpack")
				.setLore("§6A Medium Backpack which can hold up to §6"+Main.getPlugin().getConfig().getString("MediumBackpack.Size") +" §6Items").setColor(config.getInt("MediumBackpack.Color.R"), config.getInt("MediumBackpack.Color.G"), config.getInt("MediumBackpack.Color.B")).setUnbreakable()
				.build();
		ShapedRecipe mediumBackpackRecipe = new ShapedRecipe(new NamespacedKey(Main.getPlugin(), "MediumBackpack"),
				mediumBackpack);
		mediumBackpackRecipe.shape("HHH", "HSH", "HHH");
		mediumBackpackRecipe.setIngredient('H', new RecipeChoice.ExactChoice(hardenedLeather));
		mediumBackpackRecipe.setIngredient('S', new RecipeChoice.ExactChoice(smallBackpack));

		ItemStack bigBackpack = new LeatherArmorBuilder(Material.LEATHER_CHESTPLATE).setName("§6§lBig Backpack")
				.setLore("§6A Big Backpack which can hold up to §6"+Main.getPlugin().getConfig().getString("BickBackpack.Size") +" §6Items").setColor(config.getInt("BigBackpack.Color.R"), config.getInt("BigBackpack.Color.G"), config.getInt("BigBackpack.Color.B")).setUnbreakable()
				.build();
		ShapedRecipe bigBackpackRecipe = new ShapedRecipe(new NamespacedKey(Main.getPlugin(), "BigBackpack"),
				bigBackpack);
		bigBackpackRecipe.shape("SSS", "SMS", "SSS");
		bigBackpackRecipe.setIngredient('S', new RecipeChoice.ExactChoice(superStrongLeather));
		bigBackpackRecipe.setIngredient('M', new RecipeChoice.ExactChoice(mediumBackpack));

		Bukkit.addRecipe(smallBackpackRecipe);
		Bukkit.addRecipe(mediumBackpackRecipe);
		Bukkit.addRecipe(bigBackpackRecipe);
		Bukkit.addRecipe(hardenedLeatherRecipe);
		Bukkit.addRecipe(superStrongLeatherRecipe);
	}
}
