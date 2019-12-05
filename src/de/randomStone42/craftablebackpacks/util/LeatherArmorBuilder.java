package de.randomStone42.craftablebackpacks.util;

import java.util.Arrays;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.LeatherArmorMeta;

public class LeatherArmorBuilder{
	
	ItemStack item;
	LeatherArmorMeta leatherArmorMeta;
	
	public LeatherArmorBuilder(Material material) {
		item = new ItemStack(material);
		leatherArmorMeta = (LeatherArmorMeta) item.getItemMeta();
	}
	
	public LeatherArmorBuilder setColor(int r, int g, int b) {
		leatherArmorMeta.setColor(Color.fromRGB(r,g,b));
		return this;		
	}
	
	public LeatherArmorBuilder setName(String name) {
		leatherArmorMeta.setDisplayName(name);
		return this;
	}
	
	public LeatherArmorBuilder setAmount (int amount) {
		item.setAmount(amount);
		return this;
	}
	
	public LeatherArmorBuilder setUnbreakable() {
		leatherArmorMeta.setUnbreakable(true);
		return this;
	}
	
	public LeatherArmorBuilder setLore(String... lore) {
		leatherArmorMeta.setLore(Arrays.asList(lore));
		return this;
	}
	
	public ItemStack build() {
		item.setItemMeta(leatherArmorMeta);
		return item;
	}

}
