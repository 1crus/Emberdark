package net.yamiat.emberdark.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.yamiat.emberdark.Emberdark;
import net.yamiat.emberdark.block.ModBlocks;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagGenerator extends BlockTagsProvider {
    public ModBlockTagGenerator(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, Emberdark.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
        this.tag(BlockTags.NEEDS_DIAMOND_TOOL).add(ModBlocks.COGNITIVE_GRASS.get());

        this.tag(BlockTags.MINEABLE_WITH_SHOVEL).add(ModBlocks.COGNITIVE_GRASS.get(),
                ModBlocks.WORMYDIRTBLOCK.get(),
                ModBlocks.DEATHANTNEST.get());

        this.tag(BlockTags.MINEABLE_WITH_PICKAXE).add(ModBlocks.COGNITIVE_GRASS.get());

        this.tag(BlockTags.MINEABLE_WITH_AXE).add(ModBlocks.CAKOBANS_FINGER.get());

        this.tag(BlockTags.MINEABLE_WITH_HOE).add(ModBlocks.CUTAWAY_VINES.get(),
                ModBlocks.JELLYWIRE.get());

        this.tag(BlockTags.LOGS_THAT_BURN)
                .add(ModBlocks.STRIPPED_CAKOBANS_FINGERS_LOG.get())
                .add(ModBlocks.STRIPPED_CAKOBANS_FINGERS_WOOD.get())
                .add(ModBlocks.CAKOBANS_FINGERS_LOG.get())
                .add(ModBlocks.CAKOBANS_FINGERS_WOOD.get());

        this.tag(BlockTags.PLANKS)
                .add(ModBlocks.CAKOBANS_FINGERS_PLANKS.get());


    }
}
