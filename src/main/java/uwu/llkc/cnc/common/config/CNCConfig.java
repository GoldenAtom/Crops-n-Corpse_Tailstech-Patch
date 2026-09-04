package uwu.llkc.cnc.common.config;

import net.neoforged.neoforge.common.ModConfigSpec;

public final class CNCConfig {
    public record SpawnSettings(
            ModConfigSpec.BooleanValue enabled,
            ModConfigSpec.IntValue weight,
            ModConfigSpec.IntValue minimumGroupSize,
            ModConfigSpec.IntValue maximumGroupSize
    ) {
    }

    public record SeedPacketSettings(ModConfigSpec.IntValue sunCost, ModConfigSpec.IntValue cooldownTicks) {
    }

    public static final ModConfigSpec SPEC;

    public static final SpawnSettings PEASHOOTER_SPAWN;
    public static final SpawnSettings SNOW_PEA_SPAWN;
    public static final SpawnSettings REPEATER_SPAWN;
    public static final SpawnSettings WALL_NUT_SPAWN;
    public static final SpawnSettings SUNFLOWER_SPAWN;
    public static final SpawnSettings BROWNCOAT_SPAWN;
    public static final SpawnSettings IMP_SPAWN;
    public static final SpawnSettings CHERRY_BOMB_SPAWN;

    public static final SeedPacketSettings PEASHOOTER_PACKET;
    public static final SeedPacketSettings SNOW_PEA_PACKET;
    public static final SeedPacketSettings SUNFLOWER_PACKET;
    public static final SeedPacketSettings WALL_NUT_PACKET;
    public static final SeedPacketSettings POTATO_MINE_PACKET;
    public static final SeedPacketSettings REPEATER_PACKET;
    public static final SeedPacketSettings CHERRY_BOMB_PACKET;

    public static final ModConfigSpec.IntValue PEASHOOTER_PROJECTILE_DAMAGE;
    public static final ModConfigSpec.IntValue PEASHOOTER_ATTACK_INTERVAL_TICKS;
    public static final ModConfigSpec.DoubleValue PEASHOOTER_ATTACK_RANGE;
    public static final ModConfigSpec.IntValue SNOW_PEA_PROJECTILE_DAMAGE;
    public static final ModConfigSpec.IntValue SNOW_PEA_ATTACK_INTERVAL_TICKS;
    public static final ModConfigSpec.DoubleValue SNOW_PEA_ATTACK_RANGE;
    public static final ModConfigSpec.IntValue SNOW_PEA_CHILL_DURATION_TICKS;
    public static final ModConfigSpec.IntValue REPEATER_PROJECTILE_DAMAGE;
    public static final ModConfigSpec.IntValue REPEATER_ATTACK_INTERVAL_TICKS;
    public static final ModConfigSpec.DoubleValue REPEATER_ATTACK_RANGE;
    public static final ModConfigSpec.IntValue REPEATER_SHOT_GAP_TICKS;
    public static final ModConfigSpec.IntValue REPEATER_SHOT_COUNT;
    public static final ModConfigSpec.IntValue SUNFLOWER_MIN_PRODUCTION_TICKS;
    public static final ModConfigSpec.IntValue SUNFLOWER_MAX_PRODUCTION_TICKS;
    public static final ModConfigSpec.DoubleValue SUNFLOWER_OWNER_RANGE;
    public static final ModConfigSpec.DoubleValue WALL_NUT_ARMOR_HEALTH;
    public static final ModConfigSpec.IntValue POTATO_MINE_ARMING_TICKS;
    public static final ModConfigSpec.DoubleValue POTATO_MINE_DAMAGE;
    public static final ModConfigSpec.DoubleValue POTATO_MINE_EXPLOSION_RADIUS;
    public static final ModConfigSpec.DoubleValue CHERRY_BOMB_DAMAGE;
    public static final ModConfigSpec.DoubleValue CHERRY_BOMB_EXPLOSION_RADIUS;
    public static final ModConfigSpec.DoubleValue CHERRY_BOMB_TRIGGER_RANGE;

    public static final ModConfigSpec.DoubleValue CROP_SNOW_PEA_CHANCE;
    public static final ModConfigSpec.DoubleValue CROP_PEASHOOTER_CHANCE;
    public static final ModConfigSpec.DoubleValue CROP_REPEATER_CHANCE;
    public static final ModConfigSpec.DoubleValue SUNFLOWER_SEED_GROWTH_CHANCE;
    public static final ModConfigSpec.DoubleValue SUNFLOWER_PLANT_SPAWN_CHANCE;
    public static final ModConfigSpec.DoubleValue POTATO_MINE_CROP_SPAWN_CHANCE;
    public static final ModConfigSpec.DoubleValue WALL_NUT_LEAF_SPAWN_CHANCE;
    public static final ModConfigSpec.DoubleValue CHERRY_BOMB_LEAF_SPAWN_CHANCE;

    public static final ModConfigSpec.DoubleValue BROWNCOAT_CONE_CHANCE;
    public static final ModConfigSpec.DoubleValue BROWNCOAT_BUCKET_CHANCE;
    public static final ModConfigSpec.DoubleValue BROWNCOAT_FLAG_CHANCE;
    public static final ModConfigSpec.DoubleValue BROWNCOAT_EQUIPMENT_DROP_CHANCE;

    static {
        ModConfigSpec.Builder builder = new ModConfigSpec.Builder();

        builder.comment(
                "Natural spawn settings.",
                "Weight is relative to other mobs in the same spawn category.",
                "Set enabled to false or weight to 0 to disable a natural spawn.",
                "Changes require a game/server restart because biome spawn lists are built during startup."
        ).push("spawns");

        PEASHOOTER_SPAWN = defineSpawn(builder, "peashooter", 50, 1, 2);
        SNOW_PEA_SPAWN = defineSpawn(builder, "snow_pea", 50, 1, 2);
        REPEATER_SPAWN = defineSpawn(builder, "repeater", 25, 1, 2);
        WALL_NUT_SPAWN = defineSpawn(builder, "wall_nut", 20, 1, 1);
        SUNFLOWER_SPAWN = defineSpawn(builder, "sunflower", 50, 2, 3);
        BROWNCOAT_SPAWN = defineSpawn(builder, "browncoat", 30, 3, 7);
        IMP_SPAWN = defineSpawn(builder, "imp", 20, 5, 7);
        CHERRY_BOMB_SPAWN = defineSpawn(builder, "cherry_bomb", 10, 1, 1);

        builder.pop();

        builder.comment("Seed packet prices and cooldowns. Twenty ticks equal one second.").push("seed_packets");
        PEASHOOTER_PACKET = definePacket(builder, "peashooter", 16, 40);
        SNOW_PEA_PACKET = definePacket(builder, "snow_pea", 24, 40);
        SUNFLOWER_PACKET = definePacket(builder, "sunflower", 0, 200);
        WALL_NUT_PACKET = definePacket(builder, "wall_nut", 8, 320);
        POTATO_MINE_PACKET = definePacket(builder, "potato_mine", 4, 320);
        REPEATER_PACKET = definePacket(builder, "repeater", 32, 60);
        CHERRY_BOMB_PACKET = definePacket(builder, "cherry_bomb", 24, 900);
        builder.pop();

        builder.comment("Plant combat and production values. Animation-only timings remain internal.").push("plants");
        PEASHOOTER_PROJECTILE_DAMAGE = integer(builder, "peashooter_projectile_damage", 3, 0, 10_000);
        PEASHOOTER_ATTACK_INTERVAL_TICKS = integer(builder, "peashooter_attack_interval_ticks", 40, 1, 72_000);
        PEASHOOTER_ATTACK_RANGE = decimal(builder, "peashooter_attack_range", 30.0, 0.1, 256.0);
        SNOW_PEA_PROJECTILE_DAMAGE = integer(builder, "snow_pea_projectile_damage", 3, 0, 10_000);
        SNOW_PEA_ATTACK_INTERVAL_TICKS = integer(builder, "snow_pea_attack_interval_ticks", 40, 1, 72_000);
        SNOW_PEA_ATTACK_RANGE = decimal(builder, "snow_pea_attack_range", 30.0, 0.1, 256.0);
        SNOW_PEA_CHILL_DURATION_TICKS = integer(builder, "snow_pea_chill_duration_ticks", 80, 0, 72_000);
        REPEATER_PROJECTILE_DAMAGE = integer(builder, "repeater_projectile_damage", 3, 0, 10_000);
        REPEATER_ATTACK_INTERVAL_TICKS = integer(builder, "repeater_attack_interval_ticks", 40, 1, 72_000);
        REPEATER_ATTACK_RANGE = decimal(builder, "repeater_attack_range", 30.0, 0.1, 256.0);
        REPEATER_SHOT_GAP_TICKS = integer(builder, "repeater_shot_gap_ticks", 5, 1, 72_000);
        REPEATER_SHOT_COUNT = integer(builder, "repeater_shot_count", 2, 1, 128);
        SUNFLOWER_MIN_PRODUCTION_TICKS = integer(builder, "sunflower_min_production_ticks", 1120, 1, 1_728_000);
        SUNFLOWER_MAX_PRODUCTION_TICKS = integer(builder, "sunflower_max_production_ticks", 1200, 1, 1_728_000);
        SUNFLOWER_OWNER_RANGE = decimal(builder, "sunflower_owner_range", 8.0, 0.0, 256.0);
        WALL_NUT_ARMOR_HEALTH = decimal(builder, "wall_nut_armor_health", 300.0, 0.0, 1_000_000.0);
        POTATO_MINE_ARMING_TICKS = integer(builder, "potato_mine_arming_ticks", 600, 0, 1_728_000);
        POTATO_MINE_DAMAGE = decimal(builder, "potato_mine_damage", 40.0, 0.0, 1_000_000.0);
        POTATO_MINE_EXPLOSION_RADIUS = decimal(builder, "potato_mine_explosion_radius", 1.5, 0.0, 128.0);
        CHERRY_BOMB_DAMAGE = decimal(builder, "cherry_bomb_damage", 65.0, 0.0, 1_000_000.0);
        CHERRY_BOMB_EXPLOSION_RADIUS = decimal(builder, "cherry_bomb_explosion_radius", 2.0, 0.0, 128.0);
        CHERRY_BOMB_TRIGGER_RANGE = decimal(builder, "cherry_bomb_trigger_range", 3.5, 0.0, 256.0);
        builder.pop();

        builder.comment("Chances for obtaining plants from crops and leaves. Values range from 0.0 to 1.0.").push("world_acquisition");
        CROP_SNOW_PEA_CHANCE = chance(builder, "cold_peashooter_crop_snow_pea_chance", 0.05);
        CROP_PEASHOOTER_CHANCE = chance(builder, "peashooter_crop_peashooter_chance", 0.065);
        CROP_REPEATER_CHANCE = chance(builder, "peashooter_crop_repeater_chance", 0.02);
        SUNFLOWER_SEED_GROWTH_CHANCE = chance(builder, "sunflower_seed_growth_chance", 0.02);
        SUNFLOWER_PLANT_SPAWN_CHANCE = chance(builder, "sunflower_plant_spawn_chance", 0.04);
        POTATO_MINE_CROP_SPAWN_CHANCE = chance(builder, "potato_mine_crop_spawn_chance", 0.015);
        WALL_NUT_LEAF_SPAWN_CHANCE = chance(builder, "wall_nut_leaf_spawn_chance", 0.005);
        CHERRY_BOMB_LEAF_SPAWN_CHANCE = chance(builder, "cherry_bomb_leaf_spawn_chance", 0.01);
        builder.pop();

        builder.comment("Browncoat equipment probabilities. Each later roll only occurs if earlier equipment was not selected.").push("browncoat_equipment");
        BROWNCOAT_CONE_CHANCE = chance(builder, "traffic_cone_chance", 0.30);
        BROWNCOAT_BUCKET_CHANCE = chance(builder, "bucket_chance", 0.15);
        BROWNCOAT_FLAG_CHANCE = chance(builder, "flag_chance", 0.05);
        BROWNCOAT_EQUIPMENT_DROP_CHANCE = chance(builder, "equipment_drop_chance", 0.085);
        builder.pop();
        SPEC = builder.build();
    }

    private static SeedPacketSettings definePacket(ModConfigSpec.Builder builder, String name, int sunCost, int cooldownTicks) {
        builder.push(name);
        SeedPacketSettings settings = new SeedPacketSettings(
                integer(builder, "sun_cost", sunCost, 0, 1_000_000),
                integer(builder, "cooldown_ticks", cooldownTicks, 0, 1_728_000)
        );
        builder.pop();
        return settings;
    }

    private static ModConfigSpec.IntValue integer(ModConfigSpec.Builder builder, String name, int value, int minimum, int maximum) {
        return builder.defineInRange(name, value, minimum, maximum);
    }

    private static ModConfigSpec.DoubleValue decimal(ModConfigSpec.Builder builder, String name, double value, double minimum, double maximum) {
        return builder.defineInRange(name, value, minimum, maximum);
    }

    private static ModConfigSpec.DoubleValue chance(ModConfigSpec.Builder builder, String name, double value) {
        return builder.defineInRange(name, value, 0.0, 1.0);
    }

    private CNCConfig() {
    }

    private static SpawnSettings defineSpawn(
            ModConfigSpec.Builder builder,
            String name,
            int defaultWeight,
            int defaultMinimumGroupSize,
            int defaultMaximumGroupSize
    ) {
        builder.push(name);
        ModConfigSpec.BooleanValue enabled = builder
                .comment("Whether this entity is added to natural biome spawn lists.")
                .define("enabled", true);
        ModConfigSpec.IntValue weight = builder
                .comment("Relative natural spawn weight. Zero disables the spawn entry.")
                .defineInRange("weight", defaultWeight, 0, 10_000);
        ModConfigSpec.IntValue minimumGroupSize = builder
                .comment("Minimum number spawned in one group.")
                .defineInRange("minimum_group_size", defaultMinimumGroupSize, 1, 128);
        ModConfigSpec.IntValue maximumGroupSize = builder
                .comment("Maximum number spawned in one group. Values below the minimum are raised to the minimum at runtime.")
                .defineInRange("maximum_group_size", defaultMaximumGroupSize, 1, 128);
        builder.pop();
        return new SpawnSettings(enabled, weight, minimumGroupSize, maximumGroupSize);
    }

    public static SpawnSettings spawnSettings(String key) {
        return switch (key) {
            case "peashooter" -> PEASHOOTER_SPAWN;
            case "snow_pea" -> SNOW_PEA_SPAWN;
            case "repeater" -> REPEATER_SPAWN;
            case "wall_nut" -> WALL_NUT_SPAWN;
            case "sunflower" -> SUNFLOWER_SPAWN;
            case "browncoat" -> BROWNCOAT_SPAWN;
            case "imp" -> IMP_SPAWN;
            case "cherry_bomb" -> CHERRY_BOMB_SPAWN;
            default -> throw new IllegalArgumentException("Unknown Crops 'n' Corpses spawn config key: " + key);
        };
    }
}
