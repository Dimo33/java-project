package bg.tu_varna.sit.f24621606.core.commands;

import bg.tu_varna.sit.f24621606.core.Command;

/**
 * Излиза от програмата.
 */
public class ExitCommand implements Command {

    private boolean running;

    public ExitCommand(boolean running) {
        this.running = running;
    }

    @Override
    public void execute(String[] args) {

        System.out.println("Изход от програмата.");
    }

    public boolean isRunning() {
        return running;
    }

    public void stop() {
        running = false;
    }
}