package github.thelawf.xmmui;

import github.thelawf.xmmui.common.command.XMMUICommand;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.stream.Collectors;

// The value here should match an entry in the META-INF/mods.toml file
@Mod("xmmui")
public class XMMUI {

    public static final String MOD_ID = "xmmui";
    private static final Logger LOGGER = LogManager.getLogger();

    public XMMUI() {
        MinecraftForge.EVENT_BUS.register(this);
    }


    @Mod.EventBusSubscriber(modid = XMMUI.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
    public static class CommonSetup {
        @SubscribeEvent
        public static void registerCommand(RegisterCommandsEvent event) {
            XMMUICommand.register(event.getDispatcher());
        }
    }

    public static ResourceLocation withRL(String modid, String path){
        return new ResourceLocation(modid, path);
    }
    public static String withAffix(String prefix, String suffix) {
        return prefix + "." + MOD_ID + "." + suffix;
    }
    public static TranslationTextComponent withTranslation(String prefix, String suffix) {
        return new TranslationTextComponent(withAffix(prefix, suffix));
    }
}
