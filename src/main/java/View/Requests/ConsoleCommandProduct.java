package View.Requests;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum ConsoleCommandProduct {
    DIGEST("(?i)digest"),
    ATTRIBUTES("(?i)attributes"),
    COMPARE_PRODUCT("(?i)compare\\s+(\\w+)"),
    COMMENTS("(?i)comments"),
    BACK("(?i)back"),
    HELP("(?i)help"),
    ADD_TO_CARD("(?i)add\\s+to\\s+card"),
    SELECT_SELLER("(?i)select\\s+seller\\s+(\\w+)"),
    ADD_COMMENTS("(?i)add\\s+comment");




    private Pattern commandPathern;
    public Pattern getCommandPathern(){
        return commandPathern;
    }
    public Matcher getStringMatcher(String input){
        return this.commandPathern.matcher(input);

    }
    ConsoleCommandProduct(String commandPathernString){
        this.commandPathern=Pattern.compile(commandPathernString);
    }
}
