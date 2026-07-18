package com.evotech.screen;

import com.evotech.EvoTech;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;

import java.util.ArrayList;
import java.util.List;

public class RecipeBookScreen extends Screen {
    private static final ResourceLocation TEXTURE = new ResourceLocation(EvoTech.MOD_ID, "textures/gui/recipe_book_gui.png");
    private static final int PAGE_WIDTH = 176;
    private static final int PAGE_HEIGHT = 166;

    private int currentPage = 0;
    private int leftPos, topPos;
    private final List<Page> pages = new ArrayList<>();

    public RecipeBookScreen() {
        super(Component.translatable("item.evotech.engineering_guide"));
    }

    @Override
    protected void init() {
        super.init();
        leftPos = (width - PAGE_WIDTH) / 2;
        topPos = (height - PAGE_HEIGHT) / 2;
        buildPages();
    }

    private void buildPages() {
        pages.clear();

        // Page 0: Ores & Mining
        pages.add(new Page("Ore Mining Levels", new String[]{
            "TIN ORE",
            "  Y: 0 to max height, Vein size: 8",
            "  Drops: Tin Ingot",
            "",
            "LEAD ORE",
            "  Y: 0 to 128, Vein size: 7",
            "  Drops: Lead Ingot",
            "",
            "SILVER ORE",
            "  Y: 0 to 256, Vein size: 8",
            "  Drops: Silver Ingot",
            "",
            "ALUMINUM ORE",
            "  Y: 0 to 128, Vein size: 7",
            "  Drops: Aluminum Ingot",
            "",
            "NICKEL ORE",
            "  Y: 0 to 96, Vein size: 6",
            "  Drops: Nickel Ingot",
            "",
            "ZINC ORE",
            "  Y: 0 to 160, Vein size: 9",
            "  Drops: Zinc Ingot",
        }));

        // Page 1: Alloy Smelter
        pages.add(new Page("Alloy Smelter Recipes", new String[]{
            "BRONZE = Tin + Copper",
            "  1 Tin Ingot + 1 Copper Ingot",
            "  -> 1 Bronze Ingot",
            "",
            "STEEL = Iron + Coal Coke",
            "  1 Iron Ingot + 1 Coal Coke",
            "  -> 1 Steel Ingot",
            "",
            "ELECTRUM = Gold + Silver",
            "  1 Gold Ingot + 1 Silver Ingot",
            "  -> 1 Electrum Ingot",
            "",
            "CONSTANTAN = Copper + Nickel",
            "  1 Copper Ingot + 1 Nickel Ingot",
            "  -> 1 Constantan Ingot",
        }));

        // Page 2: Macerator
        pages.add(new Page("Macerator Recipes (Ore Doubling)", new String[]{
            "PUT IN: Ore Block",
            "GET OUT: 2x Dust",
            "",
            "Tin Ore -> 2x Tin Dust",
            "Lead Ore -> 2x Lead Dust",
            "Silver Ore -> 2x Silver Dust",
            "Aluminum Ore -> 2x Aluminum Dust",
            "Nickel Ore -> 2x Nickel Dust",
            "Zinc Ore -> 2x Zinc Dust",
            "Iron Ore -> 2x Iron Dust",
            "Gold Ore -> 2x Gold Dust",
            "Coal Ore -> 2x Coal",
            "",
            "Works with Deepslate variants too!",
        }));

        // Page 3: Compressor
        pages.add(new Page("Compressor Recipes (4 -> 1 Plate)", new String[]{
            "PUT IN: 4x Ingot",
            "GET OUT: 1x Plate",
            "",
            "4x Copper Ingot -> Copper Plate",
            "4x Tin Ingot -> Tin Plate",
            "4x Bronze Ingot -> Bronze Plate",
            "4x Iron Ingot -> Iron Plate",
            "4x Steel Ingot -> Steel Plate",
            "4x Aluminum Ingot -> Aluminum Plate",
            "4x Nickel Ingot -> Nickel Plate",
        }));

        // Page 4: Generator
        pages.add(new Page("Generator Fuel", new String[]{
            "The generator burns fuel items:",
            "",
            "Coal -> 200 ticks (10s)",
            "Charcoal -> 200 ticks (10s)",
            "Coal Coke -> 400 ticks (20s)",
            "Biochar -> 300 ticks (15s)",
            "",
            "Tip: Coal Coke is the most",
            "efficient fuel source!",
        }));

        // Page 5: Crafting
        pages.add(new Page("Key Crafting Recipes", new String[]{
            "ALLOY SMELTER:",
            " I B I / I   I / C C C",
            " (Iron, Brick, Cobblestone)",
            "",
            "ELECTRIC FURNACE:",
            " I I I / I R I / C C C",
            " (Iron, Redstone, Cobblestone)",
            "",
            "MACERATOR:",
            " S I S / S S S / C C C",
            " (Steel, Iron, Cobblestone)",
            "",
            "COMPRESSOR:",
            " S S S / S I S /   C  ",
            " (Steel, Iron, Cobblestone)",
            "",
            "GENERATOR:",
            " I R I / I C I / I R I",
            " (Iron, Redstone, Cobblestone)",
            "",
            "GEARS: 4 Ingots around Stick",
            "PLATES: 2x2 Ingots",
            "RODS: 2 Ingots vertical",
        }));

        // Page 6: Materials
        pages.add(new Page("Processing Chain", new String[]{
            "STEP 1: Mine Ores",
            "  -> Raw ore blocks",
            "",
            "STEP 2: Macerate",
            "  -> 2x Dust per ore",
            "",
            "STEP 3: Smelt Dusts",
            "  -> Ingots (1:1 ratio)",
            "",
            "STEP 4: Alloy (optional)",
            "  -> Bronze, Steel, Electrum...",
            "",
            "STEP 5: Compress",
            "  -> Plates (4 ingots -> 1)",
            "",
            "TIP: Always macerate ores first",
            "for 2x yield before smelting!",
        }));
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        renderBackground(guiGraphics);
        guiGraphics.blit(TEXTURE, leftPos, topPos, 0, 0, PAGE_WIDTH, PAGE_HEIGHT);

        if (currentPage >= 0 && currentPage < pages.size()) {
            Page page = pages.get(currentPage);

            guiGraphics.drawCenteredString(this.font, page.title, leftPos + PAGE_WIDTH / 2, topPos + 8, 0x404040);

            int startY = topPos + 22;
            for (int i = 0; i < page.lines.length && i < 13; i++) {
                String line = page.lines[i];
                int color = line.isEmpty() ? 0x404040 : (line.startsWith(" ") ? 0x606060 : 0x202020);
                if (line.endsWith(":")) color = 0x1A6B1A;
                guiGraphics.drawString(this.font, line, leftPos + 10, startY + i * 10, color, false);
            }
        }

        String pageText = (currentPage + 1) + " / " + pages.size();
        guiGraphics.drawCenteredString(this.font, pageText, leftPos + PAGE_WIDTH / 2, topPos + PAGE_HEIGHT - 14, 0x808080);

        // Arrow buttons
        if (currentPage > 0) {
            guiGraphics.drawString(this.font, "<", leftPos + 4, topPos + PAGE_HEIGHT / 2 - 4, 0x202020);
        }
        if (currentPage < pages.size() - 1) {
            guiGraphics.drawString(this.font, ">", leftPos + PAGE_WIDTH - 12, topPos + PAGE_HEIGHT / 2 - 4, 0x202020);
        }

        super.render(guiGraphics, mouseX, mouseY, partialTick);
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        if (button == 0) {
            if (mouseX >= leftPos && mouseX <= leftPos + 20 && mouseY >= topPos + PAGE_HEIGHT / 2 - 10 && mouseY <= topPos + PAGE_HEIGHT / 2 + 10) {
                if (currentPage > 0) currentPage--;
                return true;
            }
            if (mouseX >= leftPos + PAGE_WIDTH - 20 && mouseX <= leftPos + PAGE_WIDTH && mouseY >= topPos + PAGE_HEIGHT / 2 - 10 && mouseY <= topPos + PAGE_HEIGHT / 2 + 10) {
                if (currentPage < pages.size() - 1) currentPage++;
                return true;
            }
        }
        return super.mouseClicked(mouseX, mouseY, button);
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }

    private static class Page {
        final String title;
        final String[] lines;

        Page(String title, String[] lines) {
            this.title = title;
            this.lines = lines;
        }
    }
}
