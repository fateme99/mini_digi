package View.Requests;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum ConsoleCommandOff {
    OFFS("(?i)offs"),
    SHOW_PRODUCT("(?i)show\\s+product\\s+(\\w+)"),
    HELP("(?i)help"),
    BACK("(?i)back");




    private Pattern commandPathern;
    public Pattern getCommandPathern(){
        return commandPathern;
    }
    public Matcher getStringMatcher(String input){
        return this.commandPathern.matcher(input);

    }
    ConsoleCommandOff(String commandPathernString){
        this.commandPathern=Pattern.compile(commandPathernString);
    }
}
