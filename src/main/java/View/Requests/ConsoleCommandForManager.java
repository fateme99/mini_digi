package View.Requests;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum ConsoleCommandForManager {
    BACK("(?i)back"),
    VIEW_PERSONAL_INFO("(?i)view\\s+personal\\s+info"),
    EDIT_FIELD("(?i)edit\\s+(\\w+)"),
    MANAGE_USERS("(?i)manage\\s+users"),
    VIEW_USERNAME("(?i)view\\s+(\\w+)"),
    DELETE_USER("(?i)delete\\s+(\\w+)"),
    CREATE_MANAGER_PROFILE("(?i)create\\s+manager\\s+profile"),
    MANAGE_ALL_PRODUCT("(?i)manage\\s+all\\s+products"),
    REMOVE_PRODUCT("(?i)remove\\s+(\\w+)"),
    CREATE_DISCOUNT_CODE("(?i)create\\s+discount\\s+code"),
    VIEW_DISCOUNT_CODES("(?i)view\\s+discount\\s+codes"),
    VIEW_DISCOUNT_CODE("(?i)view\\s+discount\\s+code\\s+(\\w+)"),
    EDIT_DISCOUNT_CODE("(?i)edit\\s+discount\\s+code\\s+(\\w+)"),
    REMOVE_DISCOUNT_CODE("(?i)remove\\s+discount\\s+code\\s+(\\w+)"),
    MANAGE_REQUESTS("(?i)manage\\s+requests"),
    DETAIL_REQUEST("(?i)details\\s+(\\w+)"),
    ACCEPT_REQUEST("(?i)accept\\s+(\\w+)"),
    DECLINE_REQUEST("(?i)decline\\s+(\\w+)"),
    MANAGE_CATEGORIES("(?i)manage\\s+categories"),
    EDIT_CATEGORY("(?i)edit\\s+(\\w+)"),
    ADD_CATEGORY("(?i)add\\s+(\\w+)"),
    HELP("(?i)help"),
    REMOVE_CATEGORY("(?i)remove\\s+(\\w+)");




    private Pattern commandPathern;
    public Pattern getCommandPathern(){
        return commandPathern;
    }
    public Matcher getStringMatcher(String input){
        return this.commandPathern.matcher(input);

    }
    ConsoleCommandForManager(String commandPathernString){
        this.commandPathern=Pattern.compile(commandPathernString);
    }
}
