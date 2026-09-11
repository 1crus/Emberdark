package net.yamiat.emberdark.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.yamiat.emberdark.Emberdark;
import net.yamiat.emberdark.block.ModBlocks;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModItemTagGenerator extends ItemTagsProvider {
    public ModItemTagGenerator(PackOutput p_275343_, CompletableFuture<HolderLookup.Provider> p_275729_, CompletableFuture<TagLookup<Block>> p_275322_, @Nullable ExistingFileHelper existingFileHelper) {
        super(p_275343_, p_275729_, p_275322_, Emberdark.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {

        this.tag(ItemTags.LOGS_THAT_BURN)
                .add(ModBlocks.STRIPPED_CAKOBANS_FINGERS_LOG.get().asItem())
                .add(ModBlocks.STRIPPED_CAKOBANS_FINGERS_WOOD.get().asItem())
                .add(ModBlocks.CAKOBANS_FINGERS_LOG.get().asItem())
                .add(ModBlocks.CAKOBANS_FINGERS_WOOD.get().asItem());
        this.tag(ItemTags.PLANKS)
                .add(ModBlocks.CAKOBANS_FINGERS_PLANKS.get().asItem());

    }
}
