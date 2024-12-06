package com.nanokulon.primalstage.mixin;

import com.nanokulon.primalstage.init.ModItems;
import net.minecraft.block.BlockState;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ItemScatterer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Optional;

@Mixin(AxeItem.class)
public abstract class UseOnBlockMixin extends MiningToolItem {
    public UseOnBlockMixin(float attackDamage, float attackSpeed, ToolMaterial material, Settings settings) {
        super(attackDamage, attackSpeed, material, BlockTags.AXE_MINEABLE, settings);
    }

    @Inject(
            method = "useOnBlock(Lnet/minecraft/item/ItemUsageContext;)Lnet/minecraft/util/ActionResult;",
            at = @At("HEAD")
    )
    private void useItem(ItemUsageContext context, CallbackInfoReturnable<ActionResult> cir) {
        World world = context.getWorld();
        BlockPos blockPos = context.getBlockPos();
        BlockState blockState = world.getBlockState(blockPos);
        Optional<Item> optional = getBarkType(blockState);
        if(optional.isPresent()){
            ItemStack itemStack = new ItemStack(optional.get());
            ItemScatterer.spawn(world, (double)blockPos.getX(), (double)blockPos.getY(), (double)blockPos.getZ(), itemStack);
        }
    }

    @Unique
    private Optional<Item> getBarkType(BlockState state) {
        return Optional.ofNullable(ModItems.REGISTERED_BARKS.get(state.streamTags()
                .map(TagKey::id)
                .filter(identifier -> ModItems.REGISTERED_BARKS.containsKey(identifier))
                .findFirst().orElse(Registries.BLOCK.getId(state.getBlock()))
        ));
    }
}
