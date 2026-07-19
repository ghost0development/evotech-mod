package com.evotech.screen;

import com.evotech.EvoTech;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import com.evotech.menu.WireMillMenu;

public class WireMillScreen extends AbstractContainerScreen<WireMillMenu> {
    private static final ResourceLocation TEXTURE = new ResourceLocation(EvoTech.MOD_ID, "textures/gui/wire_mill_gui.png");

    public WireMillScreen(WireMillMenu menu, Inventory inv, Component title) { super(menu, inv, title); }

    @Override protected void init() { super.init(); this.inventoryLabelY = this.imageHeight - 94; }

    @Override
    protected void renderBg(GuiGraphics gg, float pt, int mx, int my) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        int x = (width - imageWidth) / 2, y = (height - imageHeight) / 2;
        gg.blit(TEXTURE, x, y, 0, 0, imageWidth, imageHeight);
        if (menu.isCrafting()) gg.blit(TEXTURE, x + 79, y + 34, 176, 0, menu.getScaledProgress() + 1, 16);
    }

    @Override
    public void render(GuiGraphics gg, int mx, int my, float pt) {
        renderBackground(gg);
        super.render(gg, mx, my, pt);
        renderTooltip(gg, mx, my);
    }
}
