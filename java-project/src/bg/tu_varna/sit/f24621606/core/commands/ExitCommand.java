package bg.tu_varna.sit.f24621606.core.commands;

import bg.tu_varna.sit.f24621606.core.Command;
import bg.tu_varna.sit.f24621606.core.CommandProcessor;

/**
 * Излиза от програмата.
 */
public class ExitCommand implements Command {

    private final CommandProcessor processor;

    public ExitCommand(CommandProcessor processor) {
        this.processor = processor;
    }

    @Override
    public void execute(String[] args) {

        processor.stop();

        System.out.println("Изход от програмата.");
    }
}