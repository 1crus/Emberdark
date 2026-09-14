package net.yamiat.emberdark.entity.custom;

import leaf.cosmere.common.registry.AttributesRegistry;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.living.LivingChangeTargetEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.yamiat.emberdark.entity.goals.CakobansLureGoal;
import net.yamiat.emberdark.entity.goals.DeathantNestGoal;
import org.jetbrains.annotations.Nullable;

public class DeathAntEntity extends Animal {
    public DeathAntEntity(EntityType<? extends Animal> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    @Override
    public void tick() {
        super.tick();

        if (this.level().isClientSide) {
            setupAnimationStates();

        }
    }

    private void setupAnimationStates() {
        if (this.idleAnimationTimeout <= 0) {
            this.idleAnimationTimeout = this.random.nextInt(40) + 80;
            this.idleAnimationState.start(this.tickCount);
        } else {
            --this.idleAnimationTimeout;

        }


    }

    @Override
    protected void updateWalkAnimation(float pPartialTick) {
        float f;
        if (this.getPose() == Pose.STANDING) {
            f = Math.min(pPartialTick * 6f, 1f);
        } else {
            f = 0f;
        }

        this.walkAnimation.update(f, 0.2f);
    }

    public final AnimationState idleAnimationState = new AnimationState();
    private int idleAnimationTimeout = 0;


    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(1, new FloatGoal(this));
        this.goalSelector.addGoal(3, new LeapAtTargetGoal(this, 0.4F));
        this.goalSelector.addGoal(4, new DeathAntEntity.DeathAntAttackGoal(this));
        this.goalSelector.addGoal(6, new WaterAvoidingRandomStrollGoal(this, 0.8D));
        this.goalSelector.addGoal(7, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(7, new RandomLookAroundGoal(this));
        this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
        this.goalSelector.addGoal(5, new CakobansLureGoal(this, 1.0D, 32, 10));
        this.goalSelector.addGoal(6, new DeathantNestGoal(this, 1.0D, 32, 10));
        this.targetSelector.addGoal(2, new DeathAntEntity.DeathAntTargetGoal<>(this, Player.class));
        this.targetSelector.addGoal(3, new DeathAntEntity.DeathAntTargetGoal<>(this, IronGolem.class));
    }

    @Override
    public @Nullable AgeableMob getBreedOffspring(ServerLevel pLevel, AgeableMob pOtherParent) {
        return null;
    }

    static class DeathAntAttackGoal extends MeleeAttackGoal {
        public DeathAntAttackGoal(DeathAntEntity pDeathAnt) {
            super(pDeathAnt, 1.2D, true);
        }

        /**
         * Returns whether execution should begin. You can also read and cache any state necessary for execution in this
         * method as well.
         */
        public boolean canUse() {
            return super.canUse() && !this.mob.isVehicle();
        }

        /**
         * Returns whether an in-progress EntityAIBase should continue executing
         */
        public boolean canContinueToUse() {
            float f = this.mob.getLightLevelDependentMagicValue();
            if (f >= 0.5F && this.mob.getRandom().nextInt(100) == 0) {
                return super.canContinueToUse();
            } else {
                return super.canContinueToUse();
            }
        }

        protected double getAttackReachSqr(LivingEntity pAttackTarget) {
            return (double) (4.0F + pAttackTarget.getBbWidth());
        }
    }

    static class DeathAntTargetGoal<T extends LivingEntity> extends NearestAttackableTargetGoal<T> {
        public DeathAntTargetGoal(DeathAntEntity pDeathAnt, Class<T> pEntityTypeToTarget) {
            super(pDeathAnt, pEntityTypeToTarget, true);
        }

        /**
         * Returns whether execution should begin. You can also read and cache any state necessary for execution in this
         * method as well.
         */
        public boolean canUse() {
            float f = this.mob.getLightLevelDependentMagicValue();
            return f >= 0.5F ? super.canUse() : super.canUse();
        }
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, 5.0D)
                .add(Attributes.ATTACK_DAMAGE, 2.0f)
                .add(Attributes.MOVEMENT_SPEED, 0.3D)
                .add(Attributes.FOLLOW_RANGE, 32.0D)
                .add(Attributes.KNOCKBACK_RESISTANCE, 1.0D)
                .add(Attributes.ARMOR, 2.0f)
                .add(Attributes.JUMP_STRENGTH, 1.0D);


    }

    @Override
    public boolean doHurtTarget(Entity target) {
        // Let the default attack logic run first (deals damage)
        boolean hasHurt = super.doHurtTarget(target);

        if (hasHurt && target instanceof LivingEntity livingTarget) {
            // Apply Poison effect: 100 ticks (5 seconds), amplifier 0 (Poison I)
            livingTarget.addEffect(new MobEffectInstance(MobEffects.POISON, 100, 2), this);
        }

        return hasHurt;
    }


    @Override
    protected @Nullable SoundEvent getAmbientSound() {
        return SoundEvents.SILVERFISH_AMBIENT;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource pDamageSource) {
        return SoundEvents.SHULKER_HURT_CLOSED;
    }


    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.SILVERFISH_DEATH;
    }


    @Mod.EventBusSubscriber(modid = "emberdark", bus = Mod.EventBusSubscriber.Bus.FORGE)
    public class MobIgnoreAttributeHandler {

        @SubscribeEvent
        public static void onTargetChange(LivingChangeTargetEvent event) {
            LivingEntity newTarget = event.getNewTarget();

            if (newTarget instanceof Player player) {
                // Check if the player has your custom attribute instance and a specific value
                double attrValue = player.getAttributeValue(AttributesRegistry.COGNITIVE_CONCEALMENT.get());

                if (attrValue > 0.0D) { // Condition to ignore
                    event.setCanceled(true); // Prevents the target from being set
                    // Alternatively: event.getEntity().setTarget(null);
                }
            }
        }
    }
}



