import org.fusesource.jansi.AnsiConsole;

import java.util.Scanner;

import static org.fusesource.jansi.Ansi.*;
import static org.fusesource.jansi.Ansi.Color.*;

/**
 * Description:
 *
 * @author: KangWuBin
 * @Date: 2019/12/7
 * @Time: 22:28
 */
public class Main {
    private static final String[] fo = {
            "          _ooOoo",
            "         o8888888o",
            "         88\" . \"88",
            "         (| -_- |)",
            "          O\\ = /O",
            "        ____/`---'\\",
            "       .  ' \\\\| |// `.",
            "       / \\\\||| : |||// \\",
            "      / _||||| -:- |||||- \\",
            "       | | \\\\\\ - /// | |",
            "      | \\_| ''\\---/'' | |",
            "       \\ .-\\__ `-` ___/-. /",
            "     ___`. .' /--.--\\ `. . __",
            "    .\"\" '< `.___\\_<|>_/___.' >'\"\".",
            "   | | : `- \\`.;`\\ _ /`;.`/ - ` : | |",
            "    \\ \\ `-. \\_ __\\ /__ _/ .-` / /",
            "======`-.____`-.___\\_____/___.-`____.-'======",
            "          `=---='"
    };

    private static void printFO(Color color) {
        System.out.println(ansi().eraseScreen());
        for (String line : fo) {
            System.out.println(ansi().fg(color).a(line).reset());
        }
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
    }

    public static void main(String[] args) {
        AnsiConsole.systemInstall();
        printFO(RED);
        printFO(BLUE);
        printFO(YELLOW);
        printFO(GREEN);
        printFO(CYAN);
        printFO(WHITE);
        AnsiConsole.systemUninstall();
    }
}
