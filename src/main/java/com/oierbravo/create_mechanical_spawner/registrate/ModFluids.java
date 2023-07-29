package com.oierbravo.create_mechanical_spawner.registrate;

import com.oierbravo.create_mechanical_spawner.CreateMechanicalSpawner;
import com.oierbravo.create_mechanical_spawner.content.fluids.SpawnFluid;
import com.simibubi.create.AllFluids;
import com.simibubi.create.content.fluids.VirtualFluid;
import com.simibubi.create.foundation.data.CreateRegistrate;
import com.simibubi.create.foundation.utility.Color;
import com.tterrag.registrate.builders.FluidBuilder;
import com.tterrag.registrate.util.entry.FluidEntry;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.BlockAndTintGetter;
import net.minecraft.world.level.material.FluidState;
import net.minecraftforge.fluids.FluidStack;
import org.joml.Vector3f;

public class ModFluids {
    public static String PREFIX = "spawn_fluid";
    private static float FOG_DISTANCE = 0.5f;
    public static final CreateRegistrate REGISTRATE = CreateMechanicalSpawner.registrate();

    public static final FluidEntry<SpawnFluid> RANDOM = createSpawnFluid( "random", 0xb400ff);

    /* Hostile Mobs */
    public static final FluidEntry<SpawnFluid> BLAZE = createSpawnFluid("blaze", 0xff6c00);
    public static final FluidEntry<SpawnFluid> CREEPER = createSpawnFluid("creeper", 0x11c900);
    public static final FluidEntry<SpawnFluid> ENDERMAN = createSpawnFluid("enderman", 0x00ba88);
    public static final FluidEntry<SpawnFluid> MAGMA_CUBE = createSpawnFluid("magma_cube", 0x7d0000);
    public static final FluidEntry<SpawnFluid> SKELETON = createSpawnFluid("skeleton", 0x555555);
    public static final FluidEntry<SpawnFluid> SLIME = createSpawnFluid("slime", 0x16ff00);
    public static final FluidEntry<SpawnFluid> SPIDER = createSpawnFluid("spider", 0x220000);
    public static final FluidEntry<SpawnFluid> ZOMBIE = createSpawnFluid("zombie", 0x0a7300);

    /* Friendly Mobs */
    private static FluidEntry<SpawnFluid> createSpawnFluid(String target, int color){
        ResourceLocation flow = new ResourceLocation(CreateMechanicalSpawner.MODID,"fluid/spawn_fluid_" + target + "_flow");
        ResourceLocation still = new ResourceLocation(CreateMechanicalSpawner.MODID,"fluid/spawn_fluid_" + target + "_still");
        //return REGISTRATE.virtualFluid(PREFIX + "_" + target,still,flow)
        return REGISTRATE.virtualFluid(PREFIX + "_" + target, SpawnFluid.SpawnFluidType::new,SpawnFluid::new)
                .lang("Spawn fluid " + target)
                .tag(ModTags.ModFluidTags.SPANW_LIQUID.tag)
                .bucket()
                .build()
                .register();
    }

    public static void register() {
    }

    private static class ModFluidAttributes extends AllFluids.TintedFluidType {
        private Vector3f fogColor;
        private Float fogDistance;
        private int color;

        public ModFluidAttributes(Properties p, ResourceLocation s, ResourceLocation f) {
            super(p, s, f);
        }

        public static FluidBuilder.FluidTypeFactory create(int color) {
            return (p, s, f) -> {
                ModFluidAttributes fluidType = new ModFluidAttributes(p, s, f);
                fluidType.fogColor = new Color(color, false).asVectorF();
                fluidType.fogDistance = FOG_DISTANCE;
                fluidType.color = color;
                return fluidType;
            };
        }

        @Override
        protected int getTintColor(FluidStack stack) {
            return color;
        }

        @Override
        public int getTintColor(FluidState state, BlockAndTintGetter world, BlockPos pos) {
            return color;
        }
        @Override
        protected Vector3f getCustomFogColor() {
            return fogColor;
        }

        @Override
        protected float getFogDistanceModifier() {
            return fogDistance;
        }
    }
}
