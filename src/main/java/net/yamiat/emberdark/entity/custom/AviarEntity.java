package net.yamiat.emberdark.entity.custom;

import leaf.cosmere.common.registry.AttributesRegistry;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.animal.Parrot;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.living.LivingChangeTargetEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.yamiat.emberdark.entity.goals.CakobansLureGoal;
import net.yamiat.emberdark.entity.goals.DeathantNestGoal;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

public class AviarEntity extends Parrot {
    public AviarEntity(EntityType<? extends Parrot> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }



    // A unique UUID ensuring this specific buff doesn't duplicate or conflict
    private static final UUID OWNER_BUFF_UUID = UUID.fromString("12345678-abcd-1234-abcd-123456789abc");

    // Define the modifier (Example: +4.0 Max Health / 2 Hearts)
    private static final AttributeModifier OWNER_BUFF = new AttributeModifier(
            OWNER_BUFF_UUID,
            "Aviar buff",
            10.0D,
            AttributeModifier.Operation.ADDITION
    );


    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 15.0D).add(Attributes.FLYING_SPEED, (double)0.8F).add(Attributes.MOVEMENT_SPEED, (double)0.5F);
    }


    private void applyBuffToOwner(LivingEntity owner) {
        // Example uses MAX_HEALTH, change to Attributes.MOVEMENT_SPEED, ATTACK_DAMAGE, etc.
        AttributeInstance attributeInstance = owner.getAttribute(AttributesRegistry.COGNITIVE_CONCEALMENT.get());

        if (attributeInstance != null) {
            // Check if the owner already has this specific modifier before adding it
            if (!attributeInstance.hasModifier(OWNER_BUFF)) {
                attributeInstance.addTransientModifier(OWNER_BUFF);
                // Note: addTransientModifier is used so the buff clears naturally if the server restarts
                // or if the player drops connection, preventing perm-stuck attributes.
            }
        }
    }

    private void removeBuffFromOwner(LivingEntity owner) {
        AttributeInstance attributeInstance = owner.getAttribute(AttributesRegistry.COGNITIVE_CONCEALMENT.get());
        if (attributeInstance != null && attributeInstance.hasModifier(OWNER_BUFF)) {
            attributeInstance.removeModifier(OWNER_BUFF);
        }
    }

    @Override
    public void tick() {
        super.tick();

        if (!this.level().isClientSide() && this.tickCount % 20 == 0) {
            // Change LivingEntity to Player here
            if (this.isTame() && this.getOwner() instanceof Player owner) {

                if (this.distanceToSqr(owner) <= 900.0D && this.isAlive()) {
                    applyBuffToOwner(owner);
                } else {
                    removeBuffFromOwner(owner);
                }
            }
        }
    }

    @Override
    public void die(DamageSource damageSource) {
        if (!this.level().isClientSide() && this.isTame() && this.getOwner() instanceof Player owner) {
            removeBuffFromOwner(owner);
        }
        super.die(damageSource);
    }
}


