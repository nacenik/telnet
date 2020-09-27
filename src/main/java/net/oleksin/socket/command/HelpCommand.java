package net.oleksin.socket.command;

import net.oleksin.Context;

public class HelpCommand implements Command {
  
  @Override
  public void execute(Context context) {
    context.printLn("|||\tHELP\t|||\n"
            + "cat [filename] | [absolute filepath]\t - \tread file\n"
            + "cd [] | [..] | [absolute path] | [path]\t -\t change current directory\n"
            + "echo [string]\t - \treturn string\n"
            + "exit\t - \tclose connection\n"
            + "ls\t - \toutput all files in current directory\n"
            + "stat\t - \toutput information about connection\n");
  }
}
