package View.Requests;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum ConsoleCommandForProducts {
    BACK("(?i)back"),
    PRODUCTS("(?i)products"),
    VIEW_CATEGORIES("(?i)view\\scategories"),
    FILTERING("(?i)filtering"),
    SHOW_AVAILABLE_FILTER("(?i)show\\s+available\\s+filters"),
    FILTER_AVAILABLE_FILTER("(?i)filter\\s+(\\w+)"),
    CURRENT_FILTER("(?i)current\\s+filter"),
    DISABLE_FILTER("(?i)disable\\s+filter\\s+(\\w+)"),
    SORTING("(?i)sorting"),
    SHOW_AVAILABLE_SORTS("(?i)show\\s+available\\s+sorts"),
    SORT_AVAILABLE_SORT("(?i)sort\\s+(\\w+)"),
    CURRENT_SORT("(?i)current\\s+sort"),
    DISABLE_SORT("(?i)disable\\s+sort"),
    SHOW_PRODUCTS("(?i)show\\s+products"),
    HELP("(?i)help"),
    SHOW_PRODUCT("(?i)show\\s+product\\s+(\\w+)"),
    FILTER("(?i)(\\w+)\\s*=\\s*(\\w+)")


    ;


    private Pattern commandPathern;
    public Pattern getCommandPathern(){
        return commandPathern;
    }
    public Matcher getStringMatcher(String input){
        return this.commandPathern.matcher(input);

    }
    ConsoleCommandForProducts(String commandPathernString){
        this.commandPathern=Pattern.compile(commandPathernString);
    }
}
