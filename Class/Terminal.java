package Class;

import java.util.Scanner;

public interface Terminal {
    void clearTerminal();
    void sleeping();
    int getValidIntegerInput(Scanner scan);
    String exitWithoutsteps(Scanner scan);
}