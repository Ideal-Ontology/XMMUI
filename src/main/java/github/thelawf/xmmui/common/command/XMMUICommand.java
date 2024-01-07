package github.thelawf.xmmui.common.command;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import github.thelawf.xmmui.XMMUI;
import github.thelawf.xmmui.client.UIHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import org.dom4j.DocumentException;

public class XMMUICommand implements Command<CommandSource> {
    public static final XMMUICommand XMMUI_COMMAND = new XMMUICommand();
    public static void register(CommandDispatcher<CommandSource> dispatcher) {
        dispatcher.register(Commands.literal(XMMUI.MOD_ID).executes(XMMUI_COMMAND));
    }

    @Override
    public int run(CommandContext context) throws CommandSyntaxException {
        try {
            UIHelper.displayXMMUI();
        } catch (DocumentException e) {
            throw new RuntimeException(e);
        }
        return 1;
    }
}
