package net.yamiat.emberdark.event;
import net.minecraft.core.BlockPos;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.FallingBlockEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.yamiat.emberdark.Emberdark;
import net.yamiat.emberdark.block.ModBlocks;

@Mod.EventBusSubscriber(modid = Emberdark.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class MemistPoisonEvent {

    // Define how many blocks upward you want to check
    private static final int CHECK_RANGE = 5;

    @SubscribeEvent
    public static void onLivingTick(LivingEvent.LivingTickEvent event) {
        LivingEntity entity = event.getEntity();
        Level level = entity.level();

        // Run only on the server side and every 20 ticks (1 second) to optimize performance
        if (!level.isClientSide() && entity.tickCount % 20 == 0) {
            BlockPos entityPos = entity.blockPosition();

            // Check the blocks directly above the entity
            for (int i = 1; i <= CHECK_RANGE; i++) {
                BlockPos checkPos = entityPos.above(i);
                BlockState state = level.getBlockState(checkPos);

                // Replace 'YourBlockRegistry.YOUR_POISON_BLOCK.get()' with your actual block object
                if (state.is(ModBlocks.MEMISTVINESNUB.get())) {

                    // Apply poison for 4 seconds (80 ticks) at amplifier 0 (Poison I)
                    entity.addEffect(new MobEffectInstance(MobEffects.DARKNESS, 100, 2));
                    entity.addEffect(new MobEffectInstance(MobEffects.POISON, 80, 1));
                    entity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 80, 4));
                    entity.addEffect(new MobEffectInstance(MobEffects.DIG_SLOWDOWN, 80, 4));

                    break; // Stop checking further up once a block is found
                }
            }
        }
    }
}