package src;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Scanner;

public class Theatre {
    public static int row_number;//variable for get row number as input
    public static int seat_number;//variable for get seat number as input
    public static String[] row1 = new String[]{"O", "O", "O", "O", "O", "O", "O", "O", "O", "O", "O", "O"};//array for store seats in row 1.Use to print the stage
    public static String[] row2 = new String[]{"O", "O", "O", "O", "O", "O", "O", "O", "O", "O", "O", "O", "O", "O", "O", "O"};//array for store seats in row 2.Use to print the stage
    public static String[] row3 = new String[]{"O", "O", "O", "O", "O", "O", "O", "O", "O", "O", "O", "O", "O", "O", "O", "O", "O", "O", "O", "O"};//array for store seats in row 3.Use to print the stage
    public static int[] available_seats_row1 = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};//array for store available seats in row 1.Use to print available seats
    public static int[] available_seats_row2 = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16};//array for store available seats in row 2.Use to print available seats
    public static int[] available_seats_row3 = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20};//array for store available seats in row 3.Use to print available seats
    public static int[] buy_ticket_row1 = new int[12];//array to store the bought tickets in row 1
    public static int[] buy_ticket_row2 = new int[16];//array to store the bought tickets in row 2
    public static int[] buy_ticket_row3 = new int[20];//array to store the bought tickets in row 3
    public static int count_row1 = 0;//variable for save how many tickets bought in row 1
    public static int count_row2 = 0;//variable for save how many tickets bought in row 2
    public static int count_row3 = 0;//variable for save how many tickets bought in row 3
    public static int quit_code = 1;//variable use to break the while loop in main method
    public static int cancel_code = 0;//variable use identify if it is a cancel ticket
    public static int cancel_code_check_availability_in_cancel = 0;//variable use to break the loop in check availability in cancel
    public static int cancel_code_check_availability = 0;//variable use to break the loop in check availability
    public static ArrayList<String[]> tickets = new ArrayList();//save to ticket information

    public Theatre() {
    }

    public static void main(String[] args) {
        System.out.println("Welcome to the New Theatre");

        // start a loop that will continue until the user chooses to quit
        do {
            // Print the menu and prompt the user to enter an option
            PrintStream var10000 = System.out;
            String var10001 = "-".repeat(50);
            var10000.print(var10001 + "\nPlease select an option: \n1) Buy a ticket\n2) Print seating area\n3) Cancel ticket\n4) List available seats\n5) Save to file\n6) Load from file\n7) Print ticket information and total price\n8) Sort tickets by price\n\t0) Quit\n" + "-".repeat(50) + "\nEnter option: ");
            Scanner input = new Scanner(System.in);

            // try to read the user's input as an integer
            try {
                int getInput = input.nextInt();
                // use a switch statement to determine which function to call based on the user's input
                switch (getInput) {
                    case 0:
                        // if the user chose to quit, print a message and set quit_code to 0 to exit the loop
                        System.out.println("Thank You!");
                        quit_code = 0;
                        break;
                    case 1:
                        // if the user chose to buy a ticket, call the buy_ticket function
                        buy_ticket();
                        break;
                    case 2:
                        // if the user chose to print the seating area, call the print_seating_area function
                        print_seating_area();
                        break;
                    case 3:
                        // if the user chose to cancel a ticket, call the cancel_ticket function
                        cancel_ticket();
                        break;
                    case 4:
                        //if the user chose to List available seats, call the List available seats function
                        System.out.print("Seats available in row 1 : ");
                        show_available(available_seats_row1);
                        System.out.print("Seats available in row 2 : ");
                        show_available(available_seats_row2);
                        System.out.print("Seats available in row 3 : ");
                        show_available(available_seats_row3);
                        break;
                    case 5:
                        //if the user chose to save the file, call the save function
                        save();
                        break;
                    case 6:
                        //if the user chose to load the file, call the load function
                        load();
                        break;
                    case 7:
                        //if the user chose to Print ticket information and total price, call the print ticket and price function
                        print_tickets_and_price();
                        break;
                    case 8:
                        //if the user chose to Sort tickets by price, call the sort_tickets function
                        ArrayList<String[]> duplicate = new ArrayList(tickets);
                        sort_tickets(duplicate);
                        break;
                    default:
                        System.out.println("Wrong Input!. Please try again. Enter a number between \"0\" and \"8\".");
                }
            } catch (Exception var4) {
                System.out.println("Wrong Input!. Please try again. Enter a number between \"0\" and \"8\".");
            }
        } while(quit_code != 0);

    }

    public static void buy_ticket() {
        get_row_and_check();

        while(true) {
            while(true) {
                while(true) {
                    try {
                        Scanner input = new Scanner(System.in);
                        System.out.print("Input a seat number : ");
                        seat_number = input.nextInt();
                        Scanner scanner;
                        String name;
                        String surname;
                        String email;
                        double price;
                        Person person;
                        Ticket ticket;
                        String[] detail_list;
                        switch (row_number) {
                            case 1:
                                if (seat_number < 1 || seat_number > 12) {
                                    System.out.println("Wrong seat number. In row 1 seat number must be a value between 1 and 12.\nPlease check and re-enter!");
                                    continue;
                                }

                                check_availability(available_seats_row1, seat_number);
                                if (cancel_code_check_availability == 1) {
                                    cancel_code_check_availability = 0;
                                    System.out.println("Loop break");
                                } else {
                                    scanner = new Scanner(System.in);
                                    while(true) {
                                        System.out.print("Enter name : ");
                                        name = scanner.nextLine();
                                        if ( isValidName(name) == false){
                                            System.out.println("Name should be contain letters only.Please check and re-enter");
                                        }else{
                                            break;
                                        }
                                    }

                                    while(true) {
                                        System.out.print("Enter Surname : ");
                                        surname = scanner.nextLine();
                                        if ( isValidName(surname) == false){
                                            System.out.println("SurName should be contain letters only.Please check and re-enter");
                                        }else{
                                            break;
                                        }
                                    }

                                    while(true) {
                                        System.out.print("Enter email : ");
                                        email = scanner.nextLine();
                                        if ( isValidEmail(email) == false){
                                            System.out.println("Wrong Email address.Please check and re-enter");
                                        }else{
                                            break;
                                        }
                                    }

                                    while (true) {
                                        try {
                                            System.out.print("Enter price : ");
                                            price = scanner.nextDouble();
                                            break;
                                        }catch (Exception e){
                                            scanner.nextLine(); // clear the input buffer
                                            System.out.println("Please input integer or decimal value.");
                                        }
                                    }
                                    person = new Person(name, surname, email);
                                    ticket = new Ticket(row_number, seat_number, price, person);
                                    detail_list = new String[]{Integer.toString(ticket.getRow()), Integer.toString(ticket.getSeat()), Double.toString(ticket.getPrice()), person.getName(), person.getSurname(), person.getEmail()};
                                    tickets.add(detail_list);
                                    System.out.println("Ticket purchased successfully.");
                                    marking_seats(available_seats_row1, seat_number);
                                    row1[seat_number - 1] = "X";
                                    print_stage();
                                    print_real_stage(row1, row2, row3);
                                    ++count_row1;
                                    get_input_to_array(buy_ticket_row1, seat_number, count_row1);
                                }
                                break;
                            case 2:
                                if (seat_number < 1 || seat_number > 16) {
                                    System.out.println("Wrong seat number. In row 2 seat number must be a value between 1 and 16.\nPlease check and reenter!");
                                    continue;
                                }

                                check_availability(available_seats_row2, seat_number);
                                if (cancel_code_check_availability == 1) {
                                    cancel_code_check_availability = 0;
                                    System.out.println("Loop break");
                                }else {
                                    scanner = new Scanner(System.in);
                                    while(true) {
                                        System.out.print("Enter name : ");
                                        name = scanner.nextLine();
                                        if ( isValidName(name) == false){
                                            System.out.println("Name should be contain letters only.Please check and re-enter");
                                        }else{
                                            break;
                                        }
                                    }

                                    while(true) {
                                        System.out.print("Enter Surname : ");
                                        surname = scanner.nextLine();
                                        if ( isValidName(surname) == false){
                                            System.out.println("SurName should be contain letters only.Please check and re-enter");
                                        }else{
                                            break;
                                        }
                                    }

                                    while(true) {
                                        System.out.print("Enter email : ");
                                        email = scanner.nextLine();
                                        if ( isValidEmail(email) == false){
                                            System.out.println("Wrong Email address.Please check and re-enter");
                                        }else{
                                            break;
                                        }
                                    }

                                    while (true) {
                                        try {
                                            System.out.print("Enter price : ");
                                            price = scanner.nextDouble();
                                            break;
                                        }catch (Exception e){
                                            scanner.nextLine(); // clear the input buffer
                                            System.out.println("Please input integer or decimal value.");
                                        }
                                    }

                                    person = new Person(name, surname, email);
                                    ticket = new Ticket(row_number, seat_number, price, person);
                                    detail_list = new String[]{Integer.toString(ticket.getRow()), Integer.toString(ticket.getSeat()), Double.toString(ticket.getPrice()), person.getName(), person.getSurname(), person.getEmail()};
                                    tickets.add(detail_list);
                                    marking_seats(available_seats_row2, seat_number);
                                    row2[seat_number - 1] = "X";
                                    print_stage();
                                    print_real_stage(row1, row2, row3);
                                    ++count_row2;
                                    get_input_to_array(buy_ticket_row2, seat_number, count_row2);
                                }
                                break;
                            case 3:
                                if (seat_number < 1 || seat_number > 20) {
                                    System.out.println("Wrong seat number. In row 3 seat number must be a value between 1 and 20.\nPlease check and reenter!");
                                    continue;
                                }

                                check_availability(available_seats_row3, seat_number);
                                if (cancel_code_check_availability == 1) {
                                    cancel_code_check_availability = 0;
                                    System.out.println("Loop break");
                                }else {
                                    scanner = new Scanner(System.in);
                                    while(true) {
                                        System.out.print("Enter name : ");
                                        name = scanner.nextLine();
                                        if ( isValidName(name) == false){
                                            System.out.println("Name should be contain letters only.Please check and re-enter");
                                        }else{
                                            break;
                                        }
                                    }

                                    while(true) {
                                        System.out.print("Enter Surname : ");
                                        surname = scanner.nextLine();
                                        if ( isValidName(surname) == false){
                                            System.out.println("SurName should be contain letters only.Please check and re-enter");
                                        }else{
                                            break;
                                        }
                                    }

                                    while(true) {
                                        System.out.print("Enter email : ");
                                        email = scanner.nextLine();
                                        if ( isValidEmail(email) == false){
                                            System.out.println("Wrong Email address.Please check and re-enter");
                                        }else{
                                            break;
                                        }
                                    }

                                    while (true) {
                                        try {
                                            System.out.print("Enter price : ");
                                            price = scanner.nextDouble();
                                            break;
                                        }catch (Exception e){
                                            scanner.nextLine(); // clear the input buffer
                                            System.out.println("Please input integer or decimal value.");
                                        }
                                    }

                                    person = new Person(name, surname, email);
                                    ticket = new Ticket(row_number, seat_number, price, person);
                                    detail_list = new String[]{Integer.toString(ticket.getRow()), Integer.toString(ticket.getSeat()), Double.toString(ticket.getPrice()), person.getName(), person.getSurname(), person.getEmail()};
                                    tickets.add(detail_list);
                                    marking_seats(available_seats_row3, seat_number);
                                    row3[seat_number - 1] = "X";
                                    print_stage();
                                    print_real_stage(row1, row2, row3);
                                    ++count_row3;
                                    get_input_to_array(buy_ticket_row3, seat_number, count_row3);
                                }
                        }
                        return;
                    } catch (Exception var10) {
                        System.out.println("Wrong input. Please enter an integer and try again.");
                    }
                }
            }
        }
    }

    public static void print_seating_area() {
        for_loop(row1);
        for_loop(row2);
        for_loop(row3);
        Iterator var0 = tickets.iterator();

        while (var0.hasNext()) {
            String[] tickts = (String[]) var0.next();
            String ticktsStr = String.join(", ", tickts);
            System.out.println(ticktsStr);
        }

    }

    public static void for_loop(String[] array_name) {
        for(int i = 0; i < array_name.length; ++i) {
            System.out.print(array_name[i]);
        }

        System.out.println();
    }

    public static void print_real_stage(String[] array_name1, String[] array_name2, String[] array_name3) {
        PrintStream var10000 = System.out;
        String var10001 = " ".repeat(4);
        var10000.println("\n" + var10001 + "*".repeat(11) + "\n    *  STAGE  *\n" + " ".repeat(4) + "*".repeat(11));
        System.out.print(" ".repeat(4));
        for_loop_with_space(row1);
        System.out.print(" ".repeat(2));
        for_loop_with_space(row2);
        for_loop_with_space(row3);
    }

    public static void for_loop_with_space(String[] array_name) {
        int y = array_name.length / 2;
        int z = array_name.length / 2;

        int j;
        for(j = 0; j < y; ++j) {
            System.out.print(array_name[j]);
        }

        System.out.print(" ");

        for(j = z; j < array_name.length; ++j) {
            System.out.print(array_name[j]);
        }

        System.out.println();
    }

    public static void print_stage() {
        for_loop(row1);
        for_loop(row2);
        for_loop(row3);
    }

    public static void check_availability(int[] array, int seat_number) {
        if (seat_number != array[seat_number - 1]) {
            System.out.println("SORRY! This seat is already occupied! \nPlease enter another seat number");
            cancel_code_check_availability = 1;
        }

    }

    public static void check_availability_in_cancel(int[] array, int seat_number) {
        int x = array[seat_number - 1];
        if (seat_number == x) {
            System.out.println("This seat is available one. Please check and reenter.");
            cancel_code_check_availability_in_cancel = 1;
        } else {
            array[seat_number - 1] = seat_number;
            System.out.println("Seat number : " + seat_number + " is available now!");
        }

    }

    public static void marking_seats(int[] array, int seat_number) {
        array[seat_number - 1] = 0;
    }

    public static void cancel_ticket() {
        get_row_and_check();

        while(true) {
            while(true) {
                while(true) {
                    try {
                        Scanner input = new Scanner(System.in);
                        System.out.print("Input a seat number : ");
                        seat_number = input.nextInt();
                        switch (row_number) {
                            case 1:
                                if (seat_number < 1 || seat_number > 12) {
                                    System.out.println("Wrong seat number.Please check and reenter!");
                                    continue;
                                }

                                check_availability_in_cancel(available_seats_row1, seat_number);
                                if (cancel_code_check_availability_in_cancel != 1) {
                                    row1[seat_number - 1] = "O";
                                    print_stage();
                                    print_real_stage(row1, row2, row3);
                                    remove_values_from_array(buy_ticket_row1, seat_number);
                                    cancel_code = 1;
                                    --count_row1;
                                }
                                break;
                            case 2:
                                if (seat_number < 1 || seat_number > 16) {
                                    System.out.println("Wrong seat number.Please check and reenter!");
                                    continue;
                                }

                                check_availability_in_cancel(available_seats_row2, seat_number);
                                row2[seat_number - 1] = "O";
                                print_stage();
                                print_real_stage(row1, row2, row3);
                                remove_values_from_array(buy_ticket_row2, seat_number);
                                cancel_code = 1;
                                --count_row2;
                                break;
                            case 3:
                                if (seat_number < 1 || seat_number > 20) {
                                    System.out.println("Wrong seat number.Please check and reenter!");
                                    continue;
                                }

                                check_availability_in_cancel(available_seats_row3, seat_number);
                                row3[seat_number - 1] = "O";
                                print_stage();
                                print_real_stage(row1, row2, row3);
                                remove_values_from_array(buy_ticket_row3, seat_number);
                                cancel_code = 1;
                                --count_row3;
                        }
                        for(String[] ticket: tickets){
                            if(Integer.parseInt(ticket[0])== row_number && Integer.parseInt(ticket[1])==seat_number){
                                tickets.remove(ticket);
                            }
                        }
                        return;
                    } catch (Exception var1) {
                        System.out.println("Wrong input. Please enter an integer and try again.");
                    }
                }
            }
        }
    }

    public static void get_row_and_check() {
        while(true) {
            try {
                while(true) {
                    Scanner input = new Scanner(System.in);
                    System.out.print("Input a row number : ");
                    row_number = input.nextInt();
                    if (row_number >= 1 && row_number <= 3) {
                        return;
                    }

                    System.out.println("Wrong row number!. Row number must be a value between 1 and 3.\nPlease check and reenter!");
                }
            } catch (Exception var1) {
                System.out.println("Wrong input. Please enter an integer and try again.");
            }
        }
    }

    public static void show_available(int[] array_name) {
        int x = array_name.length - 1;

        for(int i = 0; i < array_name.length; ++i) {
            int y = array_name[i];
            if (y != 0) {
                if (i != x) {
                    System.out.print(array_name[i] + ", ");
                } else {
                    System.out.print(array_name[i] + ".");
                }
            }
        }

        System.out.println();
    }

    public static void print_tickets_and_price() {
        Iterator var0 = tickets.iterator();

        while(var0.hasNext()) {
            String[] tickts = (String[])var0.next();
            System.out.println(Arrays.toString(tickts));
        }

    }

    public static void get_input_to_array(int[] array, int seat_number, int count) {
        int i = count - 1;
        array[i] = seat_number;
    }

    public static void remove_values_from_array(int[] array, int seat_number) {
        for(int i = 0; i < array.length; ++i) {
            int x = array[i];
            if (seat_number == x) {
                array[i] = 0;
                break;
            }
        }

    }

    public static void save() {
        try {
            FileWriter file = new FileWriter("Theatre_info.txt");
            file.write("Available_seats_row1 : \n");

            int var10001;
            int i;
            for(i = 0; i < available_seats_row1.length; ++i) {
                var10001 = available_seats_row1[i];
                file.write("" + var10001 + " ");
            }

            file.write("\nBuy_ticket_row1 : \n");

            for(i = 0; i < buy_ticket_row1.length; ++i) {
                var10001 = buy_ticket_row1[i];
                file.write("" + var10001 + " ");
            }

            file.write("\nCount_row1 : \n" + count_row1);
            file.write("\nrow1 : \n");

            String var3;
            for(i = 0; i < row1.length; ++i) {
                var3 = row1[i];
                file.write(var3 + " ");
            }

            file.write("\n");
            file.write("\nAvailable_seats_row2 : \n");

            for(i = 0; i < available_seats_row2.length; ++i) {
                var10001 = available_seats_row2[i];
                file.write("" + var10001 + " ");
            }

            file.write("\nBuy_ticket_row2 : \n");

            for(i = 0; i < buy_ticket_row2.length; ++i) {
                var10001 = buy_ticket_row2[i];
                file.write("" + var10001 + " ");
            }

            file.write("\nCount_row2 : \n" + count_row2);
            file.write("\nrow2 : \n");

            for(i = 0; i < row2.length; ++i) {
                var3 = row2[i];
                file.write(var3 + " ");
            }

            file.write("\n");
            file.write("\nAvailable_seats_row3 : \n");

            for(i = 0; i < available_seats_row3.length; ++i) {
                var10001 = available_seats_row3[i];
                file.write("" + var10001 + " ");
            }

            file.write("\nBuy_ticket_row3 : \n");

            for(i = 0; i < buy_ticket_row3.length; ++i) {
                var10001 = buy_ticket_row3[i];
                file.write("" + var10001 + " ");
            }

            file.write("\nCount_row3 : \n" + count_row3);
            file.write("\nrow3 : \n");

            for(i = 0; i < row3.length; ++i) {
                var3 = row3[i];
                file.write(var3 + " ");
            }

            file.write("\n");
            file.close();

            System.out.println("File saved successfully!");
        } catch (Exception var2) {
            System.out.println("File didn't created. " + var2);
        }

    }

    public static void load() {
        try {
            File file = new File("Theatre_info.txt");
            Scanner file_reader = new Scanner(file);

            for(int linenum = 1; file_reader.hasNextLine(); ++linenum) {
                String line = file_reader.nextLine();
                String[] line_arr;
                int i;
                if (linenum == 2) {
                    line_arr = line.split(" ");

                    for(i = 0; i < available_seats_row1.length; ++i) {
                        available_seats_row1[i] = Integer.parseInt(line_arr[i]);
                    }
                } else if (linenum == 4) {
                    line_arr = line.split(" ");

                    for(i = 0; i < buy_ticket_row1.length; ++i) {
                        buy_ticket_row1[i] = Integer.parseInt(line_arr[i]);
                    }
                } else {
                    int row3;
                    if (linenum == 6) {
                        row3 = Integer.parseInt(line.trim());
                        count_row1 = row3;
                    } else if (linenum == 8) {
                        line_arr = line.split(" ");

                        for(i = 0; i < Theatre.row1.length; ++i) {
                            Theatre.row1[i] = line_arr[i];
                        }
                    } else if (linenum == 11) {
                        line_arr = line.split(" ");

                        for(i = 0; i < available_seats_row2.length; ++i) {
                            available_seats_row2[i] = Integer.parseInt(line_arr[i]);
                        }
                    } else if (linenum == 13) {
                        line_arr = line.split(" ");

                        for(i = 0; i < buy_ticket_row2.length; ++i) {
                            buy_ticket_row2[i] = Integer.parseInt(line_arr[i]);
                        }
                    } else if (linenum == 15) {
                        row3 = Integer.parseInt(line);
                        count_row2 = row3;
                    } else if (linenum == 17) {
                        line_arr = line.split(" ");

                        for(i = 0; i < Theatre.row2.length; ++i) {
                            Theatre.row2[i] = line_arr[i];
                        }
                    } else if (linenum == 20) {
                        line_arr = line.split(" ");

                        for(i = 0; i < available_seats_row3.length; ++i) {
                            available_seats_row3[i] = Integer.parseInt(line_arr[i]);
                        }
                    } else if (linenum == 22) {
                        line_arr = line.split(" ");

                        for(i = 0; i < buy_ticket_row3.length; ++i) {
                            buy_ticket_row3[i] = Integer.parseInt(line_arr[i]);
                        }
                    } else if (linenum == 24) {
                        row3 = Integer.parseInt(line);
                        count_row3 = row3;
                    } else if (linenum == 26) {
                        line_arr = line.split(" ");

                        for(i = 0; i < Theatre.row3.length; ++i) {
                            Theatre.row3[i] = line_arr[i];
                        }
                    }
                }
            }

            file_reader.close();
            System.out.println("File loaded successfully!");
        } catch (Exception var6) {
            System.out.println("File could not be load. " + var6);
        }

    }

    public static void sort_tickets(ArrayList<String[]> array) {
        int last = array.size() - 2;

        for(boolean switched = true; switched; --last) {
            switched = false;

            for(int i = 0; i <= last; ++i) {
                if (Double.parseDouble(((String[])array.get(i))[2]) > Double.parseDouble(((String[])array.get(i + 1))[2])) {
                    String[] temp = (String[])array.get(i);
                    array.set(i, (String[])array.get(i + 1));
                    array.set(i + 1, temp);
                    switched = true;
                }
            }
        }

        Iterator var6 = array.iterator();

        System.out.println("Ticket information sorted by price value from lowest to highest.");
        while (var6.hasNext()) {
            String[] tickts = (String[]) var6.next();
            String ticktsStr = String.join(", ", tickts);
            System.out.println(ticktsStr);
        }

    }
    public static boolean isValidEmail(String email) {
        String regex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";  // Regular expression pattern for email validation
        return email.matches(regex);
    }

    public static boolean isValidName(String name) {
        String regex = "^[a-zA-Z ]+$";
        return name.matches(regex);
    }
}
