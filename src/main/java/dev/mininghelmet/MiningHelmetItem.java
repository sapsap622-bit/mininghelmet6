package dev.mininghelmet;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;

public class MiningHelmetItem extends ArmorItem {
    private static final ResourceLocation TEXTURE =
            ResourceLocation.fromNamespaceAndPath(MiningHelmetMod.ID, "textures/armor/mining_helmet.png");

    public MiningHelmetItem(Properties properties) {
        super(MiningHelmetMod.MINING_MATERIAL, Type.HELMET, properties);
    }

    @Override
    public ResourceLocation getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot,
                                            ArmorMaterial.Layer layer, boolean innerModel) {
        return TEXTURE;
    }
}
