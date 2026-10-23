package dev.mininghelmet;

import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;

/**
 * Geometria extraida del MiningHelmetModel original (textura 64x64):
 *  - base 9x4x9, visor 7x1x2, vela 2x6x2 (+ capa exterior), y dos clips girados 45 grados.
 */
public class MiningHelmetModel extends HumanoidModel<LivingEntity> {
    public static final ModelLayerLocation LAYER = new ModelLayerLocation(
            ResourceLocation.fromNamespaceAndPath(MiningHelmetMod.ID, "mining_helmet"), "main");

    public MiningHelmetModel(ModelPart root) {
        super(root);
    }

    public static LayerDefinition createLayer() {
        MeshDefinition mesh = HumanoidModel.createMesh(CubeDeformation.NONE, 0.0F);
        PartDefinition root = mesh.getRoot();

        // El resto de partes se vacian: solo se dibuja el casco.
        PartDefinition head = root.addOrReplaceChild("head", CubeListBuilder.create(), PartPose.ZERO);
        root.addOrReplaceChild("hat", CubeListBuilder.create(), PartPose.ZERO);

        PartDefinition helmet = head.addOrReplaceChild("helmet", CubeListBuilder.create()
                        .texOffs(0, 0).addBox(-4.5F, -10.3F, -4.5F, 9.0F, 4.0F, 9.0F, new CubeDeformation(0.1F))
                        .texOffs(27, 0).addBox(-3.5F, -7.2F, -6.6F, 7.0F, 1.0F, 2.0F, new CubeDeformation(0.025F))
                        .texOffs(44, 0).addBox(-1.0F, -13.3F, -5.5F, 2.0F, 6.0F, 2.0F)
                        .texOffs(52, 0).addBox(-1.0F, -13.3F, -5.5F, 2.0F, 6.0F, 2.0F, new CubeDeformation(0.25F)),
                PartPose.offset(0.0F, 1.0F, 0.0F));

        helmet.addOrReplaceChild("clip_right", CubeListBuilder.create()
                        .texOffs(51, 1).addBox(-0.5F, -0.5F, 0.0F, 1.0F, 1.0F, 0.0F),
                PartPose.offsetAndRotation(0.1F, -13.8F, -4.5F, 0.0F, -0.7854F, 0.0F));

        helmet.addOrReplaceChild("clip_left", CubeListBuilder.create()
                        .texOffs(51, 1).addBox(-0.5F, -0.5F, 0.0F, 1.0F, 1.0F, 0.0F),
                PartPose.offsetAndRotation(0.1F, -13.8F, -4.5F, 0.0F, 0.7854F, 0.0F));

        return LayerDefinition.create(mesh, 64, 64);
    }
}
