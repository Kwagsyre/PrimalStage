package com.nanokulon.primalstage.utils;

import com.nanokulon.primalstage.PrimalStage;
import com.nanokulon.primalstage.init.ModItems;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class RegistryUtils {
    public static void registerBark(String path, Item bark, Block log) {
        var identifier = new Identifier(PrimalStage.MOD_ID, path);
        ModItems.REGISTERED_BARKS.put(Registries.BLOCK.getId(log), bark);
        Registry.register(Registries.ITEM, identifier, bark);
    }
}
