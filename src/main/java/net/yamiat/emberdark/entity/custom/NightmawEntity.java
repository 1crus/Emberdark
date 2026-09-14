package net.yamiat.emberdark.entity.custom;


import leaf.cosmere.common.registry.AttributesRegistry;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.Spider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.living.LivingChangeTargetEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.yamiat.emberdark.entity.goals.CakobansLureGoal;
import org.jetbrains.annotations.Nullable;


public class NightmawEntity extends Monster {
    public NightmawEntity(EntityType<? extends Monster> pEntityType, Level pLevel) {
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
        this.goalSelector.addGoal(4, new NightmawEntity.NightmawAttackGoal(this));
        this.goalSelector.addGoal(6, new WaterAvoidingRandomStrollGoal(this, 0.8D));
        this.goalSelector.addGoal(7, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(7, new RandomLookAroundGoal(this));
        this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
        this.goalSelector.addGoal(5, new CakobansLureGoal(this, 1.0D, 32, 10));
        this.targetSelector.addGoal(2, new NightmawEntity.NightmawTargetGoal<>(this, Player.class));
        this.targetSelector.addGoal(3, new NightmawEntity.NightmawTargetGoal<>(this, IronGolem.class));
    }

    static class NightmawAttackGoal extends MeleeAttackGoal {
        public NightmawAttackGoal(NightmawEntity pNightmaw) {
            super(pNightmaw, 1.2D, true);
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

    static class NightmawTargetGoal<T extends LivingEntity> extends NearestAttackableTargetGoal<T> {
        public NightmawTargetGoal(NightmawEntity pNightmaw, Class<T> pEntityTypeToTarget) {
            super(pNightmaw, pEntityTypeToTarget, true);
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
                .add(Attributes.MAX_HEALTH, 40.0D)
                .add(Attributes.ATTACK_DAMAGE, 10.0f)
                .add(Attributes.MOVEMENT_SPEED, 0.6D)
                .add(Attributes.FOLLOW_RANGE, 32.0D)
                .add(Attributes.KNOCKBACK_RESISTANCE, 1.0D)
                .add(Attributes.ARMOR, 16.0f)
                .add(Attributes.JUMP_STRENGTH, 2.0D)
                .add(Attributes.ARMOR_TOUGHNESS, 8.0f);


    }

    @Override
    protected @Nullable SoundEvent getAmbientSound() {
        return SoundEvents.CHICKEN_AMBIENT;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource pDamageSource) {
        return SoundEvents.PARROT_HURT;
    }


    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.CHICKEN_DEATH;
    }


    @Mod.EventBusSubscriber(modid = "emberdark", bus = Mod.EventBusSubscriber.Bus.FORGE)
    public class MobIgnoreAttributeHandler {

        @SubscribeEvent
        public static void onTargetChange(LivingChangeTargetEvent event) {
            LivingEntity newTarget = event.getNewTarget();
            LivingEntity mob = event.getEntity();

            // Check if the mob is trying to target a player
            if (newTarget instanceof Player player && mob != null) {

                // Check if the player possesses your custom attribute
                double attrValue = player.getAttributeValue(AttributesRegistry.COGNITIVE_CONCEALMENT.get());

                if (attrValue > 0.0D) {
                    // Fetch the mob's maximum detection range (Vanilla default is usually 16-32 blocks)
                    double maxFollowRange = mob.getAttributeValue(Attributes.FOLLOW_RANGE);
                    double allowedDistance = maxFollowRange * 0.5D; // Cut it in half

                    // Measure the actual distance between the mob and the player
                    double distanceToPlayer = mob.distanceTo(player);

                    // If the player is further away than half the view distance, force the mob to ignore them
                    if (distanceToPlayer > allowedDistance) {
                        event.setCanceled(true); // Cancels the target acquisition entirely
                    }
                }
            }
        }
    }
}



