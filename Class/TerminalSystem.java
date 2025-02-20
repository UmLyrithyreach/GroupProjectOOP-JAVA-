package Class;

import java.io.IOException;
import java.util.concurrent.ThreadLocalRandom;

interface Terminal {
    void clearTerminal();
    void sleeping();
}

public class TerminalSystem implements Terminal {
    // For clearing terminal
    public void clearTerminal() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                new ProcessBuilder("clear").inheritIO().start().waitFor();
            }
        } catch (IOException | InterruptedException ex) {
            ex.printStackTrace();
        }
    }

    // For generating random loading
    public void sleeping() {
        int sleepTime = ThreadLocalRandom.current().nextInt(2000, 3001);
        try {
            Thread.sleep(sleepTime);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Loaded");
        }
    }
}
