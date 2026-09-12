package net.yamiat.emberdark.item;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import net.yamiat.emberdark.block.ModBlocks;

public class ModCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, "emberdark");

    public static final RegistryObject<CreativeModeTab> EMBERDARK_TAB = CREATIVE_MODE_TABS.register("emberdark_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.INVESTEDWORM.get()))
                    .title(Component.translatable("creativetab.emberdark_tab"))
                    .displayItems((pParameters, pOutput) -> {
                        pOutput.accept(ModItems.INVESTEDWORM.get());
                        pOutput.accept(ModItems.INVESTEDPASTE.get());
                        pOutput.accept(ModItems.SPAWNNIGHTMAW.get());
                        pOutput.accept(ModBlocks.WORMYDIRTBLOCK.get());
                        pOutput.accept(ModBlocks.COGNITIVE_GRASS.get());
                        pOutput.accept(ModBlocks.CAKOBANS_FINGERS_LOG.get());
                        pOutput.accept(ModBlocks.CAKOBANS_FINGERS_WOOD.get());
                        pOutput.accept(ModBlocks.STRIPPED_CAKOBANS_FINGERS_LOG.get());
                        pOutput.accept(ModBlocks.STRIPPED_CAKOBANS_FINGERS_WOOD.get());
                        pOutput.accept(ModBlocks.CAKOBANS_FINGERS_PLANKS.get());
                        pOutput.accept(ModBlocks.CAKOBANS_FINGERS_LEAVES.get());
                        pOutput.accept(ModBlocks.CUTAWAY_VINES.get());
                        pOutput.accept(ModBlocks.DEATHANTNEST.get());
                        pOutput.accept(ModItems.DEATHANTVENOM.get());
                        pOutput.accept(ModBlocks.STINGINGTERRORVINESNUB.get());
                        pOutput.accept(ModBlocks.MEMISTVINESNUB.get());
                        pOutput.accept(ModBlocks.SWAMPVINESNUB.get());
                        pOutput.accept(ModBlocks.JELLYWIRE.get());





                    })
                    .build());


    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
