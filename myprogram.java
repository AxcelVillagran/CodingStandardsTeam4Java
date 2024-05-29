import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Logger;

class Menu {
    Map<String, Double> items;
    Logger logger = Logger.getLogger(getClass().getName());

    Menu() {
        items = new HashMap<>();
        items.put("Burger", 10.0);
        items.put("Pizza", 15.0);
        items.put("Salad", 8.0);
        items.put("Pasta", 12.0);
    }

    void show() {
        logger.info("menu:");
        for (Map.Entry<String, Double> item : items.entrySet()) {
            logger.info(item.getKey() + ": $" + item.getValue());
        }
    }

    boolean aval(String var45) {
        //is here
        logger.info("here i am in aval method");
        return var45.equals("Burger") || var45.equals("Pizza") || var45.equals("Salad") || var45.equals("Pasta");
    }

    double getPrice(String var45) {
        return items.get(var45);
    }
}

class Order {
    Map<String, Integer> var45s;

    Order() {
        //this will create a new order
        var45s = new HashMap<>();
    }

    void add(String var45, int quantity) {
        //this will add the meal and quantity to the order
        var45s.put(var45, quantity);
    }

    Map<String, Integer> getvar45s() {
        return var45s;
    }

    int getvar2() {
        int total = 0;
        for (int quantity : var45s.values()) {
            total += quantity;
        }
        return total;
    }
}

class SumTheTotal {
    double baseCost = 5;

    double calc(Order order, Menu menu) {
        //my function to calculate the total cost
        double totalC = baseCost;
        int var2 = 0;

        for (Map.Entry<String, Integer> item : order.getvar45s().entrySet()) {
            totalC += menu.getPrice(item.getKey()) * item.getValue();
            var2 += item.getValue();
        }

        double discount = 0;
        if (var2 > 5) {
            discount = 0.1;
        } else if (var2 > 10) {
            discount = 0.2;
        }

        totalC = totalC - (totalC * discount);

        //TODO: Add more discounts based on total cost in requirements

        return totalC;
    }
}

public class myprogram {
    public static void main(String[] args) {
        Menu menu = new Menu();
        Order order = new Order();
        SumTheTotal calculator = new SumTheTotal();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            menu.show();

            System.out.print("Enter meal name to order or 'done' to finish: ");
            String var45 = scanner.nextLine();
            //System.out.println("here i am in main method");
            //this will allow the user to exit the loop
            if (var45.equals("done")) break;

            if (!menu.aval(var45)) {
                System.out.println("meal not available. Please re-select.");
                continue;
            }

            System.out.print("Enter quantity for " + var45 + ": ");
            int quantity = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            if (quantity <= 0) {
                System.out.println("Invalid quantity. Please re-enter.");
                continue;
            }

            order.add(var45, quantity);
        }

        double totalC_ = calculator.calc(order, menu);
        int var2 = order.getvar2();

        if (var2 > 100) {
            System.out.println("Order quantity exceeds maximum limit. Please re-enter.");
            return;
        }

        System.out.println("Your Ord:");
        for (Map.Entry<String, Integer> item : order.getvar45s().entrySet()) {
            System.out.println(item.getKey() + ": " + item.getValue());
        }

        System.out.println("Total Cost: $" + totalC_);
        System.out.print("Confirm order (yes/no): ");
        String confirm = scanner.nextLine();

        if (!confirm.equals("yes") || !confirm.equals("YES")) {
            System.out.println("Order canceled.");
            System.out.println(-1);
            return;
        }

        System.out.println("Order confirmed. Total cost is: $" + totalC_);
    }
}
