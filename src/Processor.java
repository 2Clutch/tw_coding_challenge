import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashSet;
import java.util.Set;

public class Processor {

    private static Set<String> exItems;

    static	{
        exItems = new HashSet<>();
        exItems.add("book");
        exItems.add("headache pills");
        exItems.add("packet of headache pills");
        exItems.add("box of imported chocolates");
        exItems.add("imported box of chocolates");
        exItems.add("box of chocolates");
        exItems.add("chocolate");
        exItems.add("chocolate bar");
        exItems.add("pills");
    }

    static public double near5Percent(double amount) {
        return new BigDecimal(Math.ceil(amount * 20)/20).setScale(2,RoundingMode.HALF_UP).doubleValue();
    }

    public static double roundPrice(double amount) {
        return new BigDecimal(amount).setScale(2, RoundingMode.HALF_UP).doubleValue();
    }

    public static boolean isExempt(String name) {
        return exItems.contains(name);
    }

    public static void getFromFile(String fileName) {
        ShoppingCart sc = new ShoppingCart();

        try {
            BufferedReader in = new BufferedReader(new FileReader(fileName));
            String str;

            while ((str = in.readLine()) != null) {

                if (ItemParser.matches(str) && !str.isEmpty()) {
                    sc.put(ItemParser.parser(str), ItemParser.count(str));
                }

                else if (!str.isEmpty()) {
                    System.out.println("unknown line format: " + str);
                }
            }

            in.close();
        }

        catch (IOException e) {
            System.out.println("error:" + e.getMessage());

            return;
        }

        sc.printOrderInput();
        sc.printOrderResults();
    }
}