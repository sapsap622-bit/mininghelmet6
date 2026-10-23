package dev.mininghelmet;

import java.util.EnumMap;
import java.util.List;

import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

@Mod(MiningHelmetMod.ID)
public class MiningHelmetMod {
    public static final String ID = "mininghelmet";

    public static final DeferredRegister<ArmorMaterial> MATERIALS = DeferredRegister.create(Registries.ARMOR_MATERIAL, ID);
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(ID);

    // Equivale al hierro (casco: 2 de defensa). La capa "mining_helmet" es solo un nombre; la textura real
    // la devuelve MiningHelmetItem#getArmorTexture.
    public static final Holder<ArmorMaterial> MINING_MATERIAL = MATERIALS.register("mining", () -> {
        EnumMap<ArmorItem.Type, Integer> defense = new EnumMap<>(ArmorItem.Type.class);
        defense.put(ArmorItem.Type.BOOTS, 0);
        defense.put(ArmorItem.Type.LEGGINGS, 0);
        defense.put(ArmorItem.Type.CHESTPLATE, 0);
        defense.put(ArmorItem.Type.HELMET, 2);
        defense.put(ArmorItem.Type.BODY, 0);
        return new ArmorMaterial(
                defense,
                9,
                SoundEvents.ARMOR_EQUIP_IRON,
                () -> Ingredient.EMPTY,
                List.of(new ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath(ID, "mining_helmet"))),
                0.0F,
                0.0F);
    });

    public static final DeferredItem<Item> MINING_HELMET = ITEMS.register("mining_helmet",
            () -> new MiningHelmetItem(new Item.Properties().durability(ArmorItem.Type.HELMET.getDurability(15))));

    public MiningHelmetMod(IEventBus modBus) {
        MATERIALS.register(modBus);
        ITEMS.register(modBus);
        modBus.addListener(MiningHelmetMod::addToTab);
    }

    private static void addToTab(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == CreativeModeTabs.COMBAT) {
            event.accept(MINING_HELMET.get());
        }
    }
}
