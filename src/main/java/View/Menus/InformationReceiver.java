package View.Menus;

import Model.Account.Buyer;

import java.util.ArrayList;
import java.util.regex.Pattern;

public class InformationReceiver extends Menu {
    ArrayList<String> information;

    public InformationReceiver(Menu headMenu) {
        information = new ArrayList<>();
        this.headMenu = headMenu;
    }

    public void run(Buyer buyer){
        information.add(getUserName());
        information.add(getUserAddress());
        information.add(getUserLastName());
        information.add(getUserEmail());
        information.add(getUserPhoneNumber());
        new AddDiscountCodeMenu(this).run(buyer);
    }

    private String getUserAddress(){
        System.out.println("please enter you address and postal code :");
        return  Menu.getInputFromUser().trim();
    }

    public String getUserName(){
        String name;
        System.out.println("please enter your name");
        name = Menu.getInputFromUser().trim();
        if ( name.matches(".*\\d.*") ){
            System.out.println("what the... whose name has digits in it....? any ways....");
        }
        return name;
    }

    public String getUserLastName(){
        String lastName;
        System.out.println("please enter your last name");
        lastName = Menu.getInputFromUser().trim();
        if ( lastName.matches(".*\\d.*") ){
            System.out.println("what the... whose last name has digits in it....? any ways....");
        }
        return lastName;
    }

    public String getUserEmail(){
        String email;
        int flag = 0;
        do {
            if( flag == 1 ){
                System.out.println("this email is not valid.... who are you trying too fool huh??");
            }
            flag = 1;
            System.out.println("please enter your email");
            email = Menu.getInputFromUser().trim();
        }while( !emailIsValid(email) );
        return email;
    }

    public String getUserPhoneNumber(){
        String phoneNumber;
        int flag = 0;
        do {
            if( flag == 1 ){
                System.out.println("in which galaxy, phone numbers have characters you said?");
            }
            flag = 1;
            System.out.println("please enter your phone number");
            phoneNumber = Menu.getInputFromUser().trim();
        }while( !phoneNumber.matches("^\\d+$") );
        return phoneNumber;
    }

    public static boolean emailIsValid(String email)
    {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);
        if (email == null)
            return false;
        return pat.matcher(email).matches();
    }
}
