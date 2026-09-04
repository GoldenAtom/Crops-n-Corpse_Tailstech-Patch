package uwu.llkc.cnc.common.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.common.Tags;
import uwu.llkc.cnc.common.init.EntityTypeRegistry;
import uwu.llkc.cnc.common.init.ItemRegistry;
import uwu.llkc.cnc.common.config.CNCConfig;

import java.util.Optional;

public class PeashooterCropBlock extends PlantCropBlock{
    public PeashooterCropBlock(Properties properties) {
        super(properties);
    }

    @Override
    Optional<EntityType<?>> getEntityType(BlockState state, ServerLevel level, BlockPos pos) {
        if (level.getBiome(pos).is(Tags.Biomes.IS_COLD) && level.getRandom().nextFloat() < CNCConfig.CROP_SNOW_PEA_CHANCE.get()) {
            return Optional.of(EntityTypeRegistry.SNOW_PEA.get());
        }
        if (level.getRandom().nextFloat() < CNCConfig.CROP_PEASHOOTER_CHANCE.get()) {
            return Optional.of(EntityTypeRegistry.PEASHOOTER.get());
        }
        if (level.getRandom().nextFloat() < CNCConfig.CROP_REPEATER_CHANCE.get()) {
            return Optional.of(EntityTypeRegistry.REPEATER.get());
        }
        return Optional.empty();
    }

    @Override
    protected ItemLike getBaseSeedId() {
        return ItemRegistry.RAW_PEA.get();
    }
}
