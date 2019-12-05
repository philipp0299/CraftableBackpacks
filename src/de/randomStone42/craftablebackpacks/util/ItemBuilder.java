package de.randomStone42.craftablebackpacks.util;

import java.util.Arrays;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ItemBuilder {

	private ItemStack item;
	private ItemMeta itemMeta;
	
	public ItemBuilder(Material material, int amount) {
		item = new ItemStack(material, amount);
		itemMeta = item.getItemMeta();
	}
	
	public ItemBuilder(Material material) {
		this(material, 1);
	}
	
	public ItemBuilder setName(String name) {
		itemMeta.setDisplayName(name);
		return this;
	}
	
	public ItemBuilder setAmount (int amount) {
		item.setAmount(amount);
		return this;
	}
	
	public ItemBuilder setUnbreakable() {
		itemMeta.setUnbreakable(true);
		return this;
	}
	
	public ItemBuilder setLore(String... lore) {
		itemMeta.setLore(Arrays.asList(lore));
		return this;
	}
	
	public ItemStack build() {
		item.setItemMeta(itemMeta);
		return item;
	}
	
}
