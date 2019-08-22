package com.lazynessmind.farmingtools.init;

import com.lazynessmind.farmingtools.init.item.ItemAdvancedBoneMeal;
import com.lazynessmind.farmingtools.init.item.ItemTypeUpgrade;
import net.minecraft.item.Item;

import java.util.ArrayList;
import java.util.List;

public class FarmingToolsItems {

    public static final List<Item> ITEMS = new ArrayList<>();

    public static final Item ADVANCED_BONE_MEAL = new ItemAdvancedBoneMeal("advanced_bone_meal");
    public static final Item TYPE_UPGRADE = new ItemTypeUpgrade("type_upgrade");
}
