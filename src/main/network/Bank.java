package network;

import Model.Account.Account;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Bank {
    public static void main(String[] args) {
        new BankImpl().run();
    }
    static class BankImpl {
        private ArrayList<BankAccount> accounts = new ArrayList<>();
        private BankAccount currentUser;

        public ArrayList<BankAccount> getAccounts() {
            return accounts;
        }

        public void setAccounts(ArrayList<BankAccount> accounts) {
            this.accounts = accounts;
        }

        public BankAccount getCurrentUser() {
            return currentUser;
        }

        public void setCurrentUser(BankAccount currentUser) {
            this.currentUser = currentUser;
        }



        private void readDB(){
            File db=new File("src/DB.txt");
            try {
                Scanner scanner=new Scanner(db);
                while (scanner.hasNextLine()){
                    String userLine=scanner.nextLine();
                    String[] userEntries=userLine.split("\\s+");
                    accounts.add(new BankAccount(userEntries[0],userEntries[1],userEntries[2],userEntries[3]));
                }
                scanner.close();
            } catch (FileNotFoundException e) {
                System.err.println("db not find!");
            }
        }
        private String getUserInfo(){
            StringBuilder userInfo=new StringBuilder();
            for (BankAccount account:accounts) {
                userInfo.append(account.getFirstName()).append("\t")
                        .append(account.getLastName()).append("\t")
                        .append(account.getUserName()).append("\t")
                        .append(account.getPassword()).append("\n");

            }
            return userInfo.toString();
        }
        private void updateDataBase(){
            String toBeWritten=getUserInfo();
            try {
                FileOutputStream fileOutputStream=new FileOutputStream("src/DB.txt");
                fileOutputStream.write(toBeWritten.getBytes());
                fileOutputStream.close();
            } catch (FileNotFoundException e) {
                System.err.println("db not find!");
            } catch (IOException e) {
                System.err.println("error updating data base!");
            }
        }
        private void addAccount(){
            accounts.add(currentUser);
            updateDataBase();
        }
        private void deleteAccount(){
            accounts.remove(currentUser);
            updateDataBase();
        }
        public void run() {
            System.out.println("IP : ");
            System.out.println("Port : ");
            readDB();
            System.out.println(getUserInfo());
        }
    }

}
