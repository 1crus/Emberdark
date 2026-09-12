package net.yamiat.emberdark.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.PushReaction;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.yamiat.emberdark.Emberdark;
import net.yamiat.emberdark.block.custom.*;
import net.yamiat.emberdark.item.ModItems;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, Emberdark.MODID);


    public static final RegistryObject<Block> COGNITIVE_GRASS = registerBlock("cognitive_grass",
            () -> new CognitiveGrassBlock(BlockBehaviour.Properties.copy(Blocks.OBSIDIAN).randomTicks()));
    public static final RegistryObject<Block> WORMYDIRTBLOCK = registerBlock("wormy_dirt",
            () -> new WormyDirtBlock(BlockBehaviour.Properties.copy(Blocks.DIRT).randomTicks()));
    public static final RegistryObject<Block> DEATHANTNEST = registerBlock("deathant_nest",
            () -> new DeathAntNest(BlockBehaviour.Properties.copy(Blocks.MOSS_BLOCK).noOcclusion()));

    public static final RegistryObject<Block> CAKOBANS_FINGERS_LOG = registerBlock("cakobans_fingers_log",
            () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LOG).strength(3.0F)));
    public static final RegistryObject<Block> CAKOBANS_FINGERS_WOOD = registerBlock("cakobans_fingers_wood",
            () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.OAK_WOOD).strength(3.0F)));
    public static final RegistryObject<Block> STRIPPED_CAKOBANS_FINGERS_LOG = registerBlock("stripped_cakobans_fingers_log",
            () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.STRIPPED_OAK_LOG).strength(3.0F)));
    public static final RegistryObject<Block> STRIPPED_CAKOBANS_FINGERS_WOOD = registerBlock("stripped_cakobans_fingers_wood",
            () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.STRIPPED_OAK_WOOD).strength(3.0F)));

    public static final RegistryObject<Block> CAKOBANS_FINGERS_PLANKS = registerBlock("cakobans_fingers_planks",
            () -> new Plank(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS).strength(1.0F)));
    public static final RegistryObject<Block> CAKOBANS_FINGERS_LEAVES = registerBlock("cakobans_fingers_leaves",
            () -> new LeavesBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LEAVES)){
                @Override
                public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return true;
                }

                @Override
                public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 60;
                }

                @Override
                public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 30;
                }
            });
    public static final RegistryObject<Block> CAKOBANS_FINGER = registerBlock("cakobans_finger",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.OAK_LEAVES)

                    .noCollission()){
                @Override
                public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return true;
                }

                @Override
                public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 60;
                }

                @Override
                public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 30;
                }
            });

    public static final RegistryObject<Block> CUTAWAY_VINES = registerBlock("cutaway_vines",
            () -> new CutAwayVines(BlockBehaviour.Properties.copy(Blocks.OAK_LEAVES)
                    .noCollission()
            ){
                @Override
                public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return true;
                }

                @Override
                public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 60;
                }

                @Override
                public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 30;
                }


            });
    // 1. The Head/Tip Block
    public static final RegistryObject<Block> MEMISTVINESNUB = registerBlock("memist_vines_nub",
            () -> new MemistVinesNub(Block.Properties.copy(Blocks.CAVE_VINES)
                    .noCollission()
                    .randomTicks()
                    .sound(SoundType.CAVE_VINES)
                    .pushReaction(PushReaction.DESTROY)));

    // 2. The Body/Segment Block
    public static final RegistryObject<Block> MEMISTVINES = registerBlock("memist_vines",
            () -> new MemistVines(Block.Properties.copy(Blocks.CAVE_VINES_PLANT)
                    .noCollission()
                    .sound(SoundType.CAVE_VINES)
                    .pushReaction(PushReaction.DESTROY)));
    // 1. The Head/Tip Block
    public static final RegistryObject<Block> STINGINGTERRORVINESNUB = registerBlock("stinging_terror_vines_nub",
            () -> new StingingTerrorVinesNub(Block.Properties.copy(Blocks.CAVE_VINES)
                    .noCollission()
                    .randomTicks()
                    .sound(SoundType.CAVE_VINES)
                    .pushReaction(PushReaction.DESTROY)));

    // 2. The Body/Segment Block
    public static final RegistryObject<Block> STINGINGTERRORVINES = registerBlock("stinging_terror_vines",
            () -> new StingingTerrorVines(Block.Properties.copy(Blocks.CAVE_VINES_PLANT)
                    .noCollission()
                    .sound(SoundType.CAVE_VINES)
                    .pushReaction(PushReaction.DESTROY)));
    // 1. The Head/Tip Block
    public static final RegistryObject<Block> SWAMPVINESNUB = registerBlock("swampvines_nub",
            () -> new SwampvinesNub(Block.Properties.copy(Blocks.CAVE_VINES)
                    .noCollission()
                    .randomTicks()
                    .sound(SoundType.CAVE_VINES)
                    .pushReaction(PushReaction.DESTROY)));

    // 2. The Body/Segment Block
    public static final RegistryObject<Block> SWAMPVINES = registerBlock("swampvines",
            () -> new Swampvines(Block.Properties.copy(Blocks.CAVE_VINES_PLANT)
                    .noCollission()
                    .sound(SoundType.CAVE_VINES)
                    .pushReaction(PushReaction.DESTROY)));
    public static final RegistryObject<Block> JELLYWIRE = registerBlock("jellywire",
            () -> new JellyWire(Block.Properties.copy(Blocks.VINE)
                    .noCollission()
                    .randomTicks()
                    .sound(SoundType.VINE)
                    .pushReaction(PushReaction.DESTROY)));








    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }


    private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block) {
        return ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
