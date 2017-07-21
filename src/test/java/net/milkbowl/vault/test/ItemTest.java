/*
 * This file is part of Vault.
 *
 * Copyright (C) 2011 Morgan Humes <morgan@lanaddict.com>
 * Copyright (C) 2017 Lukas Nehrke
 *
 * Vault is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Vault is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with Vault.  If not, see <http://www.gnu.org/licenses/>.
 */

package net.milkbowl.vault.test;

import static org.junit.Assert.assertEquals;

import java.util.EnumSet;
import java.util.Set;
import net.milkbowl.vault.item.ItemInfo;
import net.milkbowl.vault.item.Items;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.junit.Test;


@SuppressWarnings("deprecation")
public class ItemTest {

    // Static list of materials we shouldn't be testing for as they are now longer able to be held in inventory.
    private static final Set<Material> ignoreMats = EnumSet.noneOf(Material.class);
    {
        // 1.9
        ignoreMats.add(Material.GRASS_PATH);
        ignoreMats.add(Material.END_GATEWAY);
        ignoreMats.add(Material.FROSTED_ICE);
        ignoreMats.add(Material.STRUCTURE_BLOCK);
        //
        //ignoreMats.add(Material.LOCKED_CHEST); invalid material as of 1.9
        ignoreMats.add(Material.STATIONARY_WATER);
        ignoreMats.add(Material.STATIONARY_LAVA);
        ignoreMats.add(Material.PISTON_EXTENSION);
        ignoreMats.add(Material.PISTON_MOVING_PIECE);
        ignoreMats.add(Material.REDSTONE_WIRE);
        ignoreMats.add(Material.CROPS);
        ignoreMats.add(Material.BURNING_FURNACE);
        ignoreMats.add(Material.SIGN_POST);
        ignoreMats.add(Material.WOODEN_DOOR);
        ignoreMats.add(Material.WALL_SIGN);
        ignoreMats.add(Material.IRON_DOOR_BLOCK);
        ignoreMats.add(Material.GLOWING_REDSTONE_ORE);
        ignoreMats.add(Material.SUGAR_CANE_BLOCK);
        ignoreMats.add(Material.CAKE_BLOCK);
        ignoreMats.add(Material.DIODE_BLOCK_OFF);
        ignoreMats.add(Material.DIODE_BLOCK_ON);
        ignoreMats.add(Material.PUMPKIN_STEM);
        ignoreMats.add(Material.MELON_STEM);
        ignoreMats.add(Material.REDSTONE_LAMP_ON);
        ignoreMats.add(Material.SKULL);
        ignoreMats.add(Material.REDSTONE_COMPARATOR_OFF);
        ignoreMats.add(Material.REDSTONE_COMPARATOR_ON);
        // 1.8 technical blocks
        ignoreMats.add(Material.STANDING_BANNER);
        ignoreMats.add(Material.WALL_BANNER);
        ignoreMats.add(Material.SPRUCE_DOOR);
        ignoreMats.add(Material.BIRCH_DOOR);
        ignoreMats.add(Material.JUNGLE_DOOR);
        ignoreMats.add(Material.ACACIA_DOOR);
        ignoreMats.add(Material.DARK_OAK_DOOR);
        ignoreMats.add(Material.DAYLIGHT_DETECTOR_INVERTED);
        ignoreMats.add(Material.DOUBLE_STEP);
        ignoreMats.add(Material.WOOD_DOUBLE_STEP);
        ignoreMats.add(Material.DOUBLE_STONE_SLAB2);
    }

    @Test
    public void testItems() {
        boolean failed = false;
        for (ItemInfo item : Items.getItemList()) {
            ItemInfo queriedInfo = Items.itemByString(item.getName());
            try {
                assertEquals(item, queriedInfo);
            } catch (AssertionError e) {
                e.printStackTrace();
                failed = true;
            }
        }
        assertEquals(false, failed);
    }
    
    @Test
    public void testItemStacks() {
        boolean failed = false;
        for (ItemInfo item : Items.getItemList()) {
            ItemStack stack = item.toStack();
            try {
                assertEquals(item, Items.itemByStack(stack));
            } catch (AssertionError e) {
                e.printStackTrace();
                failed = true;
            }
        }
        assertEquals(false, failed);
    }
    
    @Test
    public void MissingMaterialtest() {
    	boolean missing = false;
        for (Material mat : Material.values()) {
            if (ignoreMats.contains(mat)) continue;
            if (Items.itemByType(mat) == null) {
            	missing = true;
            	System.out.println("Missing " + mat.toString() + " in item search list");
            }
        }
        assertEquals(missing, false);
    }
}