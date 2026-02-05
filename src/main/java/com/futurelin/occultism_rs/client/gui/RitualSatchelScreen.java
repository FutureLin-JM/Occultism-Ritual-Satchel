package com.futurelin.occultism_rs.client.gui;

import com.futurelin.occultism_rs.OccultismRS;
import com.futurelin.occultism_rs.container.RitualSatchelContainer;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class RitualSatchelScreen extends AbstractContainerScreen<RitualSatchelContainer> {
    protected static final ResourceLocation BACKGROUND = new ResourceLocation(OccultismRS.MOD_ID, "textures/gui/inventory_ritual_satchel.png");

    public RitualSatchelScreen(RitualSatchelContainer container, Inventory inv, Component title) {
        super(container, inv, title);
        this.imageWidth = 176;
        this.imageHeight = 166;
    }

    @Override
    protected void init() {
        super.init();
        this.leftPos = (this.width - this.imageWidth) / 2;
        this.topPos = Math.max(0, (this.height - this.imageHeight) / 2);
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(guiGraphics);
        super.render(guiGraphics, mouseX, mouseY, partialTicks);
        this.renderTooltip(guiGraphics, mouseX, mouseY);
    }

    @Override
    protected void renderLabels(GuiGraphics guiGraphics, int pMouseX, int pMouseY) {
    }

    @Override
    protected void renderBg(GuiGraphics guiGraphics, float partialTicks, int mouseX, int mouseY) {
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        guiGraphics.blit(BACKGROUND, this.leftPos, this.topPos, 0, 0, this.imageWidth, this.imageHeight);
    }
}
