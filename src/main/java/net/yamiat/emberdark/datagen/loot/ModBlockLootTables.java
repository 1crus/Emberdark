package net.yamiat.emberdark.datagen.loot;

import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.minecraftforge.registries.RegistryObject;
import net.yamiat.emberdark.block.ModBlocks;
import net.yamiat.emberdark.item.ModItems;

import java.util.Set;

public class ModBlockLootTables extends BlockLootSubProvider {
    public ModBlockLootTables() {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags());
    }

    @Override
    protected void generate() {
        this.dropSelf(ModBlocks.COGNITIVE_GRASS.get());


        this.dropSelf(ModBlocks.CAKOBANS_FINGERS_PLANKS.get());
        this.dropSelf(ModBlocks.CAKOBANS_FINGERS_WOOD.get());
        this.dropSelf(ModBlocks.CAKOBANS_FINGERS_LOG.get());
        this.dropSelf(ModBlocks.STRIPPED_CAKOBANS_FINGERS_WOOD.get());
        this.dropSelf(ModBlocks.STRIPPED_CAKOBANS_FINGERS_LOG.get());
        this.dropSelf(ModBlocks.CAKOBANS_FINGER.get());
        this.dropSelf(ModBlocks.CUTAWAY_VINES.get());
        this.dropSelf(ModBlocks.DEATHANTNEST.get());


        this.add(ModBlocks.CAKOBANS_FINGERS_LEAVES.get(),
                block -> createLeavesDrops(block, ModBlocks.CAKOBANS_FINGERS_LEAVES.get(), NORMAL_LEAVES_SAPLING_CHANCES));



        this.add(ModBlocks.WORMYDIRTBLOCK.get(),
                block -> createCopperLikeOreDrops(ModBlocks.WORMYDIRTBLOCK.get(), ModItems.INVESTEDWORM.get()));

    }

    protected LootTable.Builder createCopperLikeOreDrops(Block pBlock, Item item) {
        return createSilkTouchDispatchTable(pBlock,
                this.applyExplosionDecay(pBlock,
                        LootItem.lootTableItem(item)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(2.0F, 5.0F)))
                                .apply(ApplyBonusCount.addOreBonusCount(Enchantments.BLOCK_FORTUNE))));
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return ModBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get)::iterator;
    }
}