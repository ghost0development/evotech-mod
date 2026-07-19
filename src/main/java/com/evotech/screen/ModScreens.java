package com.evotech.screen;

import com.evotech.EvoTech;
import com.evotech.init.ModMenuTypes;
import com.evotech.menu.*;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = EvoTech.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ModScreens {
    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            MenuScreens.register(ModMenuTypes.ALLOY_SMELTER.get(), AlloySmelterScreen::new);
            MenuScreens.register(ModMenuTypes.ELECTRIC_FURNACE.get(), ElectricFurnaceScreen::new);
            MenuScreens.register(ModMenuTypes.MACERATOR.get(), MaceratorScreen::new);
            MenuScreens.register(ModMenuTypes.GENERATOR.get(), GeneratorScreen::new);
            MenuScreens.register(ModMenuTypes.COMPRESSOR.get(), CompressorScreen::new);
            MenuScreens.register(ModMenuTypes.STEAM_ENGINE.get(), SteamEngineScreen::new);
            MenuScreens.register(ModMenuTypes.WIRE_MILL.get(), WireMillScreen::new);
            MenuScreens.register(ModMenuTypes.ASSEMBLING_MACHINE.get(), AssemblingMachineScreen::new);
            MenuScreens.register(ModMenuTypes.MATTER_SCANNER.get(), MatterScannerScreen::new);
        });
    }
}
