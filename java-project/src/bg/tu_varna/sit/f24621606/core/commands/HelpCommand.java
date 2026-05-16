package bg.tu_varna.sit.f24621606.core.commands;

import bg.tu_varna.sit.f24621606.core.Command;

/**
 * Показва всички команди.
 */
public class HelpCommand implements Command {

    @Override
    public void execute(String[] args) {

        System.out.println("Налични команди:");
        System.out.println("help");
        System.out.println("exit");
        System.out.println("menu");
        System.out.println("tables");
        System.out.println("orders <status>");
        System.out.println("addtable <number> <seats>");
        System.out.println("additem <name> <category> <price> <quantity>");
        System.out.println("openorder <tableNumber>");
        System.out.println("addtoorder <orderId> <itemId> <quantity>");
        System.out.println("removefromorder <orderId> <itemId>");
        System.out.println("showorder <orderId>");
        System.out.println("closeorder <orderId>");
        System.out.println("cancelorder <orderId>");
        System.out.println("report <from> <to>");
        System.out.println("topitems <from> <to>");
        System.out.println("lowstock <threshold>");
        System.out.println("save");
        System.out.println("saveas <file>");
    }
}