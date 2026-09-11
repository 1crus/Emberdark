package net.yamiat.emberdark.event;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.item.FallingBlockEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.yamiat.emberdark.Emberdark;
import net.yamiat.emberdark.block.ModBlocks;

@Mod.EventBusSubscriber(modid = Emberdark.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class BlockDropEvent {

    // Adjust this to change how high up the block can be detected
    private static final int MAX_DETECTION_HEIGHT = 5;

    @SubscribeEvent
    public static void onPlayerTick(LivingEvent.LivingTickEvent event) {
        // Run only on the logical server and only for players
        if (event.getEntity().level().isClientSide() || !(event.getEntity() instanceof Player player)) {
            return;
        }

        // Only check when the player is actually moving or ticking
        Level level = player.level();
        BlockPos playerPos = player.blockPosition();

        // Scan upwards from the player's head
        for (int i = 2; i <= MAX_DETECTION_HEIGHT; i++) {
            BlockPos checkPos = playerPos.above(i);
            BlockState state = level.getBlockState(checkPos);

            // Replace 'Blocks.ANVIL' with your custom block if needed
            if (state.is(ModBlocks.CUTAWAY_VINES.get())) {
                triggerBlockDrop(level, checkPos, state);
                break; // Stop scanning higher if we found one
            }
        }
    }

    private static void triggerBlockDrop(Level level, BlockPos pos, BlockState state) {
        // Convert the block to air so it vanishes from its original spot
        level.removeBlock(pos, false);

        // Spawn a falling block entity at the center of the block coordinates
        FallingBlockEntity fallingBlock = FallingBlockEntity.fall(
                level,
                pos,
                state
        );

        // Force it to hurt entities when it lands
        fallingBlock.setHurtsEntities(2.0F, 40); // 2.0F damage per block fallen, max 40 damage

        level.addFreshEntity(fallingBlock);
    }
}