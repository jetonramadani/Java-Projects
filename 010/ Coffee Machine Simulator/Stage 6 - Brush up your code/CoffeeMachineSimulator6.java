import java.util.Scanner;

enum CoffeeType6 {
    ESPRESSO(1, 250, 0, 16, 4),
    LATTE(2, 350, 75, 20, 7),
    CAPPUCCINO(3, 200, 100, 12, 6);

    private final int ID;
    private final int WATER_REQUIRED;
    private final int MILK_REQUIRED;
    private final int COFFEE_BEANS_REQUIRED;
    private final int COST;

    CoffeeType6(int ID, int WATER_REQUIRED, int MILK_REQUIRED, int COFFEE_BEANS_REQUIRED, int COST) {
        this.ID = ID;
        this.WATER_REQUIRED = WATER_REQUIRED;
        this.MILK_REQUIRED = MILK_REQUIRED;
        this.COFFEE_BEANS_REQUIRED = COFFEE_BEANS_REQUIRED;
        this.COST = COST;
    }

    public static CoffeeType6 getCoffeeType6(int id) {
        for (CoffeeType6 coffee : CoffeeType6.values()) {
            if (coffee.ID == id) {
                return coffee;
            }
        }
        throw new RuntimeException();
    }

    public int getWATER_REQUIRED() {
        return WATER_REQUIRED;
    }

    public int getMILK_REQUIRED() {
        return MILK_REQUIRED;
    }

    public int getCOFFEE_BEANS_REQUIRED() {
        return COFFEE_BEANS_REQUIRED;
    }

    public int getCOST() {
        return COST;
    }
}

class CoffeeMachineFunc6 {
    private int water;
    private int milk;
    private int coffeeBeans;
    private int cups;
    private int money;
    private final int CLEAN_THRESHOLD = 10;
    private int coffeeMade;

    public CoffeeMachineFunc6(int watter, int milk, int coffeeBeans, int cups, int money) {
        this.water = watter;
        this.milk = milk;
        this.coffeeBeans = coffeeBeans;
        this.cups = cups;
        this.money = money;
        this.coffeeMade = 0;
    }

    public void add(int watter, int milk, int coffeeBeans, int cups) {
        this.water += watter;
        this.milk += milk;
        this.coffeeBeans += coffeeBeans;
        this.cups += cups;
    }

    public void stats() {
        System.out.printf("The coffee machine has:\n" +
                        "%d ml of water\n" +
                        "%d ml of milk\n" +
                        "%d g of coffee beans\n" +
                        "%d disposable cups\n" +
                        "$%d of money",
                water, milk, coffeeBeans, cups, money);
    }

    public void buy(CoffeeType6 CoffeeType6) {
        water -= CoffeeType6.getWATER_REQUIRED();
        milk -= CoffeeType6.getMILK_REQUIRED();
        coffeeBeans -= CoffeeType6.getCOFFEE_BEANS_REQUIRED();
        money += CoffeeType6.getCOST();
        --cups;
        ++this.coffeeMade;
    }

    public boolean needsCleaning() {
        if (this.coffeeMade == this.CLEAN_THRESHOLD) {
            System.out.println("I need cleaning!");
            return true;
        }

        return false;
    }

    public void checkAvailable(CoffeeType6 CoffeeType6) {
        if (CoffeeType6.getWATER_REQUIRED() > water) {
            System.out.println("Sorry, not enough water!");
        } else if (CoffeeType6.getMILK_REQUIRED() > milk) {
            System.out.println("Sorry, not enough milk!");
        } else if (CoffeeType6.getCOFFEE_BEANS_REQUIRED() > coffeeBeans) {
            System.out.println("Sorry, not enough coffee beans!");
        } else if (cups == 0) {
            System.out.println("Sorry, not enough cups!");
        } else {
            System.out.println("I have enough resources, making you a coffee!");
            buy(CoffeeType6);
        }
    }

    public void take() {
        System.out.printf("I gave you $%d\n", money);
        this.money = 0;
    }

    public void clean() {
        this.coffeeMade = 0;
        System.out.println("I have been cleaned!");
    }

}

public class CoffeeMachineSimulator6 {
    private static final Scanner sc = new Scanner(System.in);

    private static void fill(CoffeeMachineFunc6 coffeeMaker) {
        System.out.println("Write how many ml of water you want to add: ");
        int water = sc.nextInt();
        System.out.println("Write how many ml of milk you want to add: ");
        int milk = sc.nextInt();
        System.out.println("Write how many grams of coffee beans you want to add: ");
        int coffeeBeans = sc.nextInt();
        System.out.println("Write how many disposable cups of coffee you want to add: ");
        int cups = sc.nextInt();
        coffeeMaker.add(water, milk, coffeeBeans, cups);
    }

    public static void main(String[] args) {
        CoffeeMachineFunc6 coffeeMaker = new CoffeeMachineFunc6(400, 540, 120, 9, 550);
        boolean goOn = true;
        while (goOn) {
            System.out.println("Write action (buy, fill, take, clean, remaining, exit): ");
            String action = sc.next();
            switch (action) {
                case "buy":
                    if (!coffeeMaker.needsCleaning()) {
                        System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu: ");
                        String input = sc.next();
                        if (!input.equals("back")) {
                            coffeeMaker.checkAvailable(CoffeeType6.getCoffeeType6(Integer.parseInt(input)));
                        }
                    }
                    break;
                case "fill":
                    fill(coffeeMaker);
                    break;
                case "take":
                    coffeeMaker.take();
                    break;
                case "remaining":
                    coffeeMaker.stats();
                    break;
                case "clean":
                    // new cleaning logic
                    coffeeMaker.clean();
                    break;
                case "exit":
                    goOn = false;
                    break;
                default:
                    break;
            }
        }
    }
}