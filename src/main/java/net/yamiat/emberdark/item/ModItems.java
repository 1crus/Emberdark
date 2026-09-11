package net.yamiat.emberdark.item;

import net.minecraft.world.item.Item;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.yamiat.emberdark.entity.ModEntities;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, "emberdark");

    public static  final RegistryObject<Item> INVESTEDWORM = ITEMS.register("invested_worm",
            () -> new Item(new Item.Properties()));
    public static  final RegistryObject<Item> INVESTEDPASTE = ITEMS.register("invested_paste",
            () -> new Item(new Item.Properties().food(ModFoods.INVESTEDPASTE)));
    public static  final RegistryObject<Item> DEATHANTVENOM = ITEMS.register("deathant_venom",
            () -> new Item(new Item.Properties()));
    public static  final RegistryObject<Item> SPAWNNIGHTMAW = ITEMS.register("nightmaw_spawner",
            () -> new ForgeSpawnEggItem(ModEntities.NIGHTMAW, 0x7e9680, 0x7e9680, new Item.Properties()));



    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }

}
