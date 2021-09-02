package View.Menus;

import Controller.Controller;
import Model.Account.Buyer;

public class AddDiscountCodeMenu extends Menu {
    public AddDiscountCodeMenu(Menu headMenu) {
        this.headMenu = headMenu;
    }

    public void run(Buyer buyer){
        String input;
        do{
            System.out.println("enter off ticket id, or if you dont want, or dont have, enter end\nWARNING NOTE : please keep in mind that if you use an off ticket and are not able to complete the purchase, your off ticket would not redeem.");
            input = Menu.getInputFromUser().trim();
            if(!input.toLowerCase().equals("end")){
                try {
                    new PaymentMenu(this).run(catchOffTicket(input , buyer) , buyer);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }while(!input.toLowerCase().equals("end"));
    }

    private double catchOffTicket(String offTicketId , Buyer buyer){
        double totalValue;
        try {
            totalValue = Controller.useOffTicket(offTicketId , buyer);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            totalValue = buyer.getCartValue();
        }
        return totalValue;
    }
}
