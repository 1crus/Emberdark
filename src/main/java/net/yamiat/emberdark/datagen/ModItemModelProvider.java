package net.yamiat.emberdark.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;
import net.yamiat.emberdark.Emberdark;
import net.yamiat.emberdark.item.ModItems;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, Emberdark.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        simpleItem(ModItems.INVESTEDPASTE);
        simpleItem(ModItems.INVESTEDWORM);
        simpleItem(ModItems.DEATHANTVENOM);


        withExistingParent(ModItems.SPAWNNIGHTMAW.getId().getPath(), mcLoc("item/template_spawn_egg"));

    }
    private ItemModelBuilder simpleItem(RegistryObject<Item> item) {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(Emberdark.MODID,"item/" + item.getId().getPath()));
    }
}
