package bg.tu_varna.sit.f24621606.core;

import bg.tu_varna.sit.f24621606.core.commands.HelpCommand;

import java.util.HashMap;
import java.util.Map;

/**
 * Обработва командите от конзолата.
 */
public class CommandProcessor {

    private final Map<String, Command> commands;

    private boolean running;

    public CommandProcessor() {

        commands = new HashMap<>();

        running = true;

        registerCommands();
    }

    private void registerCommands() {

        commands.put("help", new HelpCommand());
    }

    public void processCommand(String input) {

        if (input == null || input.isBlank()) {
            return;
        }

        String[] parts = input.trim().split("\\s+");

        String commandName = parts[0].toLowerCase();

        Command command = commands.get(commandName);

        if (command == null) {
            System.out.println("Непозната команда.");
            return;
        }

        try {
            command.execute(parts);

        } catch (Exception e) {
            System.out.println("Грешка: " + e.getMessage());
        }
    }

    public boolean isRunning() {
        return running;
    }

    public void stop() {
        running = false;
    }
}