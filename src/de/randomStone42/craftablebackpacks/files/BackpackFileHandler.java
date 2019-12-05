package de.randomStone42.craftablebackpacks.files;

import java.util.HashMap;
import java.util.Set;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import de.randomStone42.craftablebackpacks.main.Main;

public class BackpackFileHandler {

	public static void saveItemsInFile(Player player, HashMap<Integer, ItemStack> items, String backpackSize) {
		HashMap<Integer, ItemStack> itemsWithPositions = items;
		itemsWithPositions.entrySet().stream().forEach(e -> {
			Main.getPlugin().getCustomConfig().set(player.getName() + "." + backpackSize + "." + e.getKey(),
					e.getValue());
		});
		Main.getPlugin().saveCustomYml(Main.getPlugin().getCustomConfig(), Main.getPlugin().getBackpacks());
	}

	public static HashMap<Integer, ItemStack> loadItemsFromFile(Player player, String backpackSize) {
		HashMap<Integer, ItemStack> itemsWithPositions = new HashMap<>();
		try {
			Set<String> strings = (Set<String>) Main.getPlugin().getCustomConfig()
					.getConfigurationSection(player.getName() + "." + backpackSize).getKeys(false);
			for (String s : strings) {
				itemsWithPositions.put(Integer.parseInt(s), Main.getPlugin().getCustomConfig()
						.getItemStack(player.getName() + "." + backpackSize + "." + s));
			}
			return itemsWithPositions;
		} catch (NullPointerException e) {
			return null;
		}
	}

}
