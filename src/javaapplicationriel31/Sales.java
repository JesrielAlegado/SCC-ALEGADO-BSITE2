package javaapplicationriel31;

import java.util.Scanner;

public class Sales {
    public void getSales(){
        Scanner input = new Scanner (System.in);
        
        System.out.print("Enter customer name: ");
        String customer = input.nextLine();
        System.out.print("product name: ");
        String product = input.nextLine();
        System.out.print("quantity: ");
        int quantity = input.nextInt();
        System.out.print("price: ");
        int price = input.nextInt();
        System.out.print("cash: ");
        int cash = input.nextInt();
        
        
        System.out.println("\n------------------");
        System.out.println("RECEIPT");
        System.out.println("---------------------");
        
        System.out.println("Name:"+customer);
        System.out.println("Product:"+product);
        System.out.println("Quantity:"+quantity);
        System.out.println("Total due:"+(quantity*price));
        System.out.println("Cash:"+(cash));
        System.out.println("Change:"+(cash-(quantity*price)));
    }
}
