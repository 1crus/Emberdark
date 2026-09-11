package net.yamiat.emberdark.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.yamiat.emberdark.Emberdark;
import net.yamiat.emberdark.block.ModBlocks;

public class ModBlockStateProvider extends BlockStateProvider {
    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, Emberdark.MODID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {


        logBlock(((RotatedPillarBlock) ModBlocks.CAKOBANS_FINGERS_LOG.get()));
        axisBlock(((RotatedPillarBlock) ModBlocks.CAKOBANS_FINGERS_WOOD.get()), blockTexture(ModBlocks.CAKOBANS_FINGERS_LOG.get()), blockTexture(ModBlocks.CAKOBANS_FINGERS_LOG.get()));
        axisBlock(((RotatedPillarBlock) ModBlocks.STRIPPED_CAKOBANS_FINGERS_LOG.get()), blockTexture(ModBlocks.STRIPPED_CAKOBANS_FINGERS_LOG.get()),
                new ResourceLocation(Emberdark.MODID, "block/stripped_cakobans_fingers_log_top"));
        axisBlock(((RotatedPillarBlock) ModBlocks.STRIPPED_CAKOBANS_FINGERS_WOOD.get()), blockTexture(ModBlocks.STRIPPED_CAKOBANS_FINGERS_LOG.get()), blockTexture(ModBlocks.STRIPPED_CAKOBANS_FINGERS_LOG.get()));


        blockItem(ModBlocks.CAKOBANS_FINGERS_LOG);
        blockItem(ModBlocks.CAKOBANS_FINGERS_WOOD);
        blockItem(ModBlocks.STRIPPED_CAKOBANS_FINGERS_LOG);
        blockItem(ModBlocks.STRIPPED_CAKOBANS_FINGERS_WOOD);
        blockWithItem(ModBlocks.CAKOBANS_FINGERS_PLANKS);
        leavesBlock(ModBlocks.CAKOBANS_FINGERS_LEAVES);



    }


    private void leavesBlock(RegistryObject<Block> blockRegistryObject) {
        simpleBlockWithItem(blockRegistryObject.get(),
                models().singleTexture(ForgeRegistries.BLOCKS.getKey(blockRegistryObject.get()).getPath(), new ResourceLocation("minecraft:block/leaves"),
                        "all", blockTexture(blockRegistryObject.get())).renderType("cutout"));
    }

    private void blockItem(RegistryObject<Block> blockRegistryObject) {
        simpleBlockItem(blockRegistryObject.get(), new ModelFile.UncheckedModelFile(Emberdark.MODID +
                ":block/" + ForgeRegistries.BLOCKS.getKey(blockRegistryObject.get()).getPath()));
    }

    private void blockWithItem(RegistryObject<Block> blockRegistryObject) {
        simpleBlockWithItem(blockRegistryObject.get(), cubeAll(blockRegistryObject.get()));
    }
}
