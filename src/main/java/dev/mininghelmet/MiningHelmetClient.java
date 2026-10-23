package dev.mininghelmet;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.extensions.common.IClientItemExtensions;
import net.neoforged.neoforge.client.extensions.common.RegisterClientExtensionsEvent;

@EventBusSubscriber(modid = MiningHelmetMod.ID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class MiningHelmetClient {

    @SubscribeEvent
    public static void registerLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(MiningHelmetModel.LAYER, MiningHelmetModel::createLayer);
    }

    @SubscribeEvent
    public static void registerExtensions(RegisterClientExtensionsEvent event) {
        event.registerItem(new IClientItemExtensions() {
            @Override
            public HumanoidModel<?> getHumanoidArmorModel(LivingEntity entity, ItemStack stack,
                                                          EquipmentSlot slot, HumanoidModel<?> original) {
                // Se hornea cada vez para no quedar con un modelo viejo tras recargar recursos.
                return new MiningHelmetModel(Minecraft.getInstance().getEntityModels().bakeLayer(MiningHelmetModel.LAYER));
            }
        }, MiningHelmetMod.MINING_HELMET.get());
    }
}
