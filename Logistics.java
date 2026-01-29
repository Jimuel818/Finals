
import java.util.Scanner;

public class Logistics {
    public static void main(String[] args) {

       /* String[] deliv_status = new String[30];*/
       
        String[] customer = new String[30];
        String[] cus_add = new String[30];
        String[] cus_phone = new String[30];

        String[] shipment = new String[30];
        double[] payment = new double[30];
        double[] totalPrice = new double[30];

        int[] monthsIns = new int[30];
        double[] monthlyPay = new double[30];
        double inter_rate = 0.02;

        String[] inv_num = new String[30];
        String[] inv_date = new String[30];
        String[] dueDate = new String[30];

        int inv_count = 0;

        String[] ship_status = new String[30];
        int[] ship_cus_ind = new int[30];      
        int[] ship_phone_ind = new int[30];  

        double[] ship_fee = new double[30];
        double def_ship_fee = 150;

        int cus_count = 0;
        int ship_count = 0;

        String[] phones = {
                "Samsung Galaxy S25 Ultra",
                "Apple iPhone 17 Pro",
                "OnePlus 15",
                "OPPO Find X9 2025",
                "Xiaomi 15",
                "vivo V50 5G",
                "POCO F7 2025",
                "OPPO A6 Pro 5G",
                "vivo Y04s",
                "TECNO SPARK Go 1s"
        };
        double[] phonePrice = {
                84990, 79990, 45999, 69999, 45999,
                27999, 17499, 13999, 6499, 2999
        };
        int[] productSales = new int[10];
        double com_rev = 0;

        int choice = 0;
        Scanner input = new Scanner(System.in);

        while (true) {
            System.out.println("===AINCRAD LOGISTICS COMPANY===");
            System.out.println("1. Shipment & Order Management");
            System.out.println("2. Customer Management");
            System.out.println("3. Accounts Receivable");
            System.out.println("4. Company Status");
            System.out.println("5. Exit");
            System.out.print("Enter your Choice(1-5): ");
            choice = input.nextInt();
            input.nextLine();

            if (choice == 1) {
                int choice2 = 0;
                while (true) {
                    System.out.println("===SHIPMENT & ORDER MANAGEMENT===");
                    System.out.println("1. Order Product");
                    System.out.println("2. Update Shipment");
                    System.out.println("3. View All Shipments");
                    System.out.println("4. Back");
                    System.out.print("Enter your choice(1-6): ");
                    choice2 = input.nextInt();
                    input.nextLine();

                    if (choice2 == 1) {
                        if (cus_count == 0) {
                            System.out.println("No customers available. Add customer first.");
                        } else {
                            System.out.println("Available Customer:");
                            for (int i = 0; i < cus_count; i++) {
                                System.out.println((i + 1) + ". " + customer[i]);
                            }
                            System.out.print("Enter Customer: ");
                            int cus_ind = input.nextInt() - 1;
                            input.nextLine();

                            if (cus_ind < 0 || cus_ind >= cus_count) {
                                System.out.println("Invalid customer.");
                            } else {
                                System.out.println("===Phone list===");
                                for (int i = 0; i < phones.length; i++) {
                                    System.out.println((i + 1) + ". " + phones[i] + " - " + phonePrice[i]);
                                }
                                System.out.print("Enter product you want to order: ");
                                int phone_ind = input.nextInt() - 1;
                                input.nextLine();

                                if (phone_ind < 0 || phone_ind >= phones.length) {
                                    System.out.println("Invalid phone selection.");
                                } else {
                                    shipment[ship_count] = customer[cus_ind] + " ordered " + phones[phone_ind];
                                    ship_status[ship_count] = "PENDING";            
                                    ship_cus_ind[ship_count] = cus_ind;       
                                    ship_phone_ind[ship_count] = phone_ind;      
                                    double price = phonePrice[phone_ind];
                                    
                                    System.out.println("Default shipping fee: " + def_ship_fee);
                                    price += def_ship_fee;
                                    ship_fee[cus_ind] = def_ship_fee;

                                    System.out.println("Select Payment Method:");
                                    System.out.println("1. Cash on Delivery");
                                    System.out.println("2. E-Wallet");
                                    System.out.println("3. Credit / Debit");
                                    System.out.print("Enter choice (1-3): ");
                                    int payChoice = input.nextInt();
                                    input.nextLine();

                                    boolean ins_avail = (payChoice == 2 || payChoice == 3);

                                    if (payChoice == 1) {
                                        payment[cus_ind] = price;
                                        monthsIns[cus_ind] = 1;
                                        monthlyPay[cus_ind] = price;

                                        System.out.println("Payment Method: Cash on Delivery");
                                        System.out.println("Total Amount (including shipping): " + price);

                                    } else if (ins_avail) {
                                        System.out.println("Select Installment Months:");
                                        System.out.println("1. 3 months");
                                        System.out.println("2. 6 months");
                                        System.out.println("3. 9 months");
                                        System.out.println("4. 12 months");
                                        System.out.print("Enter choice (1-4): ");
                                        int insChoice = input.nextInt();
                                        input.nextLine();

                                        if (insChoice == 1) monthsIns[cus_ind] = 3;
                                        else if (insChoice == 2) monthsIns[cus_ind] = 6;
                                        else if (insChoice == 3) monthsIns[cus_ind] = 9;
                                        else if (insChoice == 4) monthsIns[cus_ind] = 12;
                                        else {
                                            System.out.println("Invalid installment choice.");
                                            return;
                                        }
                                        double interest = price * inter_rate * monthsIns[cus_ind];
                                        double total_int = price + interest;

                                        payment[cus_ind] = total_int;
                                        monthlyPay[cus_ind] = total_int / monthsIns[cus_ind];

                                        System.out.println("Installment Plan Selected");
                                        System.out.println("Months: " + monthsIns[cus_ind]);
                                        System.out.println("Interest Added: " + interest);
                                        System.out.println("Total Amount (including shipping): " + total_int);
                                        System.out.println("Monthly Payment Breakdown:");
                                        for (int i= 1; i <= monthsIns[cus_ind]; i++) {
                                            System.out.println("Month " + i + ": " + monthlyPay[cus_ind]);
                                        }

                                    } else {
                                        System.out.println("Invalid payment method.");
                                    }
                                    totalPrice[cus_ind] += payment[cus_ind];
                                    com_rev += payment[cus_ind];
                                    productSales[phone_ind]++;
                                    ship_count++;
                                    System.out.println("Order completed successfully. Status: PENDING");
                                }
                            }
                        }
                    } else if (choice2 == 2) {
                        if (ship_count == 0) {
                            System.out.println("No shipments available.");
                        } else {
                            System.out.println("===SHIPMENT LIST===");
                            for (int i = 0; i < ship_count; i++) {
                                System.out.println((i + 1) + ". " + shipment[i] + " | Status: " + ship_status[i]);
                            }
                            System.out.print("Select shipment to update: ");
                            int ship_ind = input.nextInt() - 1;
                            input.nextLine();

                            if (ship_ind < 0 || ship_ind >= ship_count) {
                                System.out.println("Invalid shipment selection.");
                            } else {
                                System.out.println("Current Status: " + ship_status[ship_ind]);
                                System.out.println("1. Confirm Shipment");
                                System.out.println("2. Cancel Shipment");
                                System.out.print("Enter choice: ");
                                int statusChoice = input.nextInt();
                                input.nextLine();

                                int cus_ind = ship_cus_ind[ship_ind];
                                int phone_ind = ship_phone_ind[ship_ind];

                                if (statusChoice == 1) {
                                    ship_status[ship_ind] = "CONFIRMED";
                                    System.out.println("Shipment confirmed successfully.");
                                } else if (statusChoice == 2) {
                                    ship_status[ship_ind] = "CANCELLED";

                                    totalPrice[cus_ind] -= payment[cus_ind];
                                    com_rev -= payment[cus_ind];
                                    productSales[phone_ind]--;
                                    payment[cus_ind] = 0;
                                    monthsIns[cus_ind] = 0;
                                    monthlyPay[cus_ind] = 0;
                                    ship_fee[cus_ind] = 0;

                                    System.out.println("Shipment cancelled successfully.");
                                } else {
                                    System.out.println("Invalid choice");
                                }
                            }    
                        }
                    } else if (choice2 == 3) {
                        if (ship_count == 0) {
                            System.out.println("No shipments recorded.");
                        } else {
                            System.out.println("===ALL SHIPMENTS===");
                            for (int i = 0; i < ship_count; i++) {
                                System.out.println((i + 1) + ". " + shipment[i]);
                            }
                        }
                    } else if (choice2 == 4) {
                        break;
                    } else
                        System.out.println("Invalid Choice Try again");

                    System.out.print("Do you want to back to sub-menu again? (y/n): ");
                    if (!input.nextLine().equalsIgnoreCase("y"))
                        break;
                }

            } else if (choice == 2) {
                while (true) {
                    System.out.println("====CUSTOMER MANAGEMENT====");
                    System.out.println("1. Add Customer");
                    System.out.println("2. Edit Customer");
                    System.out.println("3. View Customer List");
                    System.out.println("4. View Customer Balance");
                    System.out.println("5. Back");
                    System.out.print("Enter your Choice (1-5): ");
                    int choice2 = input.nextInt();
                    input.nextLine();

                    if (choice2 == 1) {
                        if (choice2 == 1) {
                                 System.out.print("Enter Customer Name: ");
                                 String name = input.nextLine();
                                 System.out.print("Enter Customer Address: ");
                                 String address = input.nextLine();
                                 System.out.print("Enter Customer Phone Number: ");
                                 String phone = input.nextLine();

                                customer[cus_count] = name;
                                cus_add[cus_count] = address;
                                cus_phone[cus_count] = phone;
                                payment[cus_count] = 0;
                                totalPrice[cus_count] = 0;
                                monthsIns[cus_count] = 0;
                                monthlyPay[cus_count] = 0;

                                cus_count++;

                                System.out.println("Customer added successfully!");
                        }
                    } else if (choice2 == 2) {
                            if (cus_count == 0) {
                                System.out.println("No customers available to edit.");

                            } else {
                                System.out.println("Select Customer to Edit:");
                                for (int i = 0; i < cus_count; i++) {
                                 System.out.println((i + 1) + ". " + customer[i]);
                                }
                                System.out.print("Enter choice to edit: ");
                                int cus_ind = input.nextInt() - 1;
                                input.nextLine();
                               
                                if (cus_ind < 0 || cus_ind >= cus_count) {
                                    System.out.println("Invalid customer selection.");          
                                } else {
                                    System.out.print("Enter new name (current: " + customer[cus_ind] + "): ");
                                    String newName = input.nextLine();
                                    System.out.print("Enter new address (current: " + cus_add[cus_ind] + "): ");
                                    String newAddress = input.nextLine();
                                    System.out.print("Enter new phone number (current: " + cus_phone[cus_ind] + "): ");
                                    String newPhone = input.nextLine();
                                    customer[cus_ind] = newName;
                                    cus_add[cus_ind] = newAddress;
                                    cus_phone[cus_ind] = newPhone;

                                    System.out.println("Customer information updated successfully!");
                                }
                            }
                    } else if (choice2 == 3) {
                        if (cus_count == 0) {
                            System.out.println("No customers available. Add customer first");
                        } else {
                            System.out.println("===CUSTOMER LIST===");
                            for (int i = 0; i < cus_count; i++) {
                                System.out.println((i + 1) + ". Name: " + customer[i]
                                        + ", Address: " + cus_add[i]
                                            + ", Phone: " + cus_phone[i]);
                            }
                        }

                    } else if (choice2 == 4) {
                            if (cus_count == 0) {
                                System.out.println("No customers available. Add customer first");
                            } else {
                                System.out.println("=== Customer Balances ===");
                                for (int i = 0; i < cus_count; i++) {
                                    System.out.println((i + 1) + ". " + customer[i]
                                            + " - Total Purchased: " + totalPrice[i]
                                            + ", Remaining Payment: " + payment[i]);
                                }
                            }
                    } else if (choice2 == 5)
                        break;
                    else
                        System.out.println("Invalid Choice Try again");

                    System.out.print("Do you want to back to sub-menu again? (y/n): ");
                    if (!input.nextLine().equalsIgnoreCase("y"))
                        break;
                }

            } else if (choice == 3) {
                while (true) {
                    System.out.println("==ACCOUNTS RECEIVABLE==");
                    System.out.println("1. Create Invoice");
                    System.out.println("2. View Invoice List");
                    System.out.println("3. Record Payment");
                    System.out.println("4. Back");
                    System.out.print("Enter your Choice(1-5): ");
                    int choice2 = input.nextInt();
                    input.nextLine();

                    if (choice2 == 1) {
                        if (cus_count == 0) {
                            System.out.println("No customers available.");
                        } else {
                            System.out.println("Select Customer:");
                            for (int i = 0; i < cus_count; i++) {
                                System.out.println((i + 1) + ". " + customer[i]);
                            System.out.print("Enter Customer Number: ");
                            }
                            int cus_ind = input.nextInt() - 1;
                            input.nextLine();

                            if (cus_ind < 0 || cus_ind >= cus_count) {
                                System.out.println("Invalid customer.");
                            } else {
                                inv_num[inv_count] = String.format("%03d", inv_count + 1);
                                System.out.print("Enter Invoice Date (YYYY-MM-DD): ");
                                inv_date[inv_count] = input.nextLine();

                                System.out.print("Enter Due Date (YYYY-MM-DD): ");
                                dueDate[inv_count] = input.nextLine();
                                System.out.println("===== INVOICE CREATED =====");
                                System.out.println("Invoice No: " + inv_num[inv_count]);
                                System.out.println("Invoice Date: " + inv_date[inv_count]);
                                System.out.println("Due Date: " + dueDate[inv_count]);

                                System.out.println("Customer: " + customer[cus_ind]);
                                System.out.println("Address: " + cus_add[cus_ind]);
                                System.out.println("Phone: " + cus_phone[cus_ind]);

                                System.out.println("Total Purchased: " + totalPrice[cus_ind]);
                                System.out.println("Amount Due: " + payment[cus_ind]);

                                if (monthsIns[cus_ind] > 1) {
                                    System.out.println("Installment: " + monthsIns[cus_ind] + " months");
                                    System.out.println("Monthly Payment: " + monthlyPay[cus_ind]);
                                } else {
                                    System.out.println("Payment Type: Full Payment");
                                }

                                if (payment[cus_ind] > 0) {
                                     System.out.println("Invoice Status: UNPAID");
                                } else {
                                    System.out.println("Invoice Status: PAID");
                                }
                                inv_count++;
                            }
                        }

                    } else if (choice2 == 2) {
                            System.out.println("===INVOICE LIST===");
                            if (inv_count == 0) {
                                System.out.println("No invoices created yet.");
                            } else {
                                for (int i = 0; i < inv_count; i++) {
                                    System.out.println( (i + 1) + ". Invoice No: " + inv_num[i] +
                                    " | Due Date: " + dueDate[i] + " | Amount Due: " + payment[i] );
                                }
                            }
                    } else if (choice2 == 3) {
                            if (cus_count == 0) {
                                System.out.println("No customers available.");
                            } else {
                                System.out.println("Select Customer:");
                            for (int i = 0; i < cus_count; i++) {
                                 System.out.println((i + 1) + ". " + customer[i]);
                            System.out.print("Enter Customer Number: ");
                            }
                            int cus_ind = input.nextInt() - 1;
                            input.nextLine();

                            if (cus_ind < 0 || cus_ind >= cus_count) {
                                System.out.println("Invalid selection.");
                            } else {
                                System.out.print("Enter payment amount: ");
                                double pay = input.nextDouble();
                                input.nextLine();

                                payment[cus_ind] -= pay;
                                if (payment[cus_ind] < 0) payment[cus_ind] = 0;

                                System.out.println("Payment recorded.");
                                System.out.println("Remaining Balance: " + payment[cus_ind]);
                            }
                        }
                    } else if (choice2 == 4)
                        break;
                    else
                        System.out.println("Invalid Choice Try again");

                    System.out.print("Do you want to back to sub-menu again? (y/n): ");
                    if (!input.nextLine().equalsIgnoreCase("y"))
                        break;
                }
            } else if (choice == 4) {
                while (true) {
                    System.out.println("===COMPANY STATUS===");
                    System.out.println("1. Daily Shipments");
                    System.out.println("2. Monthly Revenue");
                    System.out.println("3. Customer Ledger");
                    System.out.println("4. Unpaid Invoices");
                    System.out.println("5. Back");
                    System.out.print("Enter your Choice(1-5): ");
                    int choice2 = input.nextInt();
                    input.nextLine();

                    if (choice2 == 1) {
                        System.out.println("Total Shipments Today: " + ship_count);
                    } else if (choice2 == 2) {
                        System.out.println("Company Revenue: " + com_rev);
                    } else if (choice2 == 3) {
                        System.out.println("===CUSTOMER LEDGER===");
                        for (int i = 0; i < cus_count; i++) {
                            System.out.println(customer[i] + " - Total order: " + (totalPrice[i] - ship_fee[i])
                            + ", Shipping fee: " + ship_fee[i] + ", Total with Shipping: " + totalPrice[i]);
                        }
                    } else if (choice2 == 4) {
                            System.out.println("===UNPAID INVOICES===");
                            for (int i = 0; i < cus_count; i++) {
                                if (payment[i] > 0) {
                                    System.out.println(customer[i] + " - Remaining Balance: " + payment[i]);
                                }
                            }
                    } else if (choice2 == 5)
                        break;
                    else
                        System.out.println("Invalid Choice Try again");

                    System.out.print("Do you want to back to sub-menu again? (y/n): ");
                    if (!input.nextLine().equalsIgnoreCase("y"))
                        break;
                }
            } else if (choice == 5) {
                break;
            }
            System.out.print("Do you want to back to main menu again? (y/n): ");
            if (!input.nextLine().equalsIgnoreCase("y"))
                break;
        }
        input.close();
    }
}
