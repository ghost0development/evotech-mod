package com.evotech;

import com.evotech.init.ModBlockEntities;
import com.evotech.init.ModBlocks;
import com.evotech.init.ModItems;
import com.evotech.init.ModCreativeTabs;
import com.evotech.init.ModMenuTypes;
import com.evotech.screen.RecipeBookScreen;
import net.minecraft.client.Minecraft;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;
import com.mojang.logging.LogUtils;

@Mod(EvoTech.MOD_ID)
public class EvoTech {
    public static final String MOD_ID = "evotech";
    public static final Logger LOGGER = LogUtils.getLogger();

    public EvoTech() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ModItems.register(modEventBus);
        ModBlocks.register(modEventBus);
        ModBlockEntities.register(modEventBus);
        ModMenuTypes.register(modEventBus);
        ModCreativeTabs.register(modEventBus);

        MinecraftForge.EVENT_BUS.register(this);
        modEventBus.addListener(this::clientSetup);
    }

    private void clientSetup(FMLClientSetupEvent event) {
        MinecraftForge.EVENT_BUS.addListener(this::onRightClickItem);
    }

    private void onRightClickItem(PlayerInteractEvent.RightClickItem event) {
        if (event.getItemStack().is(ModItems.ENGINEERING_GUIDE.get())) {
            if (event.getEntity().level().isClientSide()) {
                Minecraft.getInstance().setScreen(new RecipeBookScreen());
            }
            event.setCanceled(true);
        }
    }
}
