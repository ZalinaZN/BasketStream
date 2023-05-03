import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    static String[] products = {"1.Хлеб", "2.Яблоки", "3.Мороженка"};
    static int[] prices = {50, 100, 80};
    private static File textFile;

    public static void main(String[] args) throws IOException {

        int productNumber = 0;
        int productCount = 0;

        File saveFile = new File("basket.bin");

        Basket basket = null;
        if (saveFile.exists()) {//проверяем наличие файла для восстановления данных о содержимом корзины
            basket = Basket.loadFromFileBin(saveFile);
        } else {// если файл неайден, создаем новый
            basket = new Basket(products, prices);
        }
        Scanner scanner = new Scanner(System.in);
        System.out.println("Список продуктов для покупки:");


            showPrice();
        while (true) {
            System.out.println("ВВедите номер продукта и количество через пробел. " +
                    "Для завершения введите `end`");
            String input = scanner.nextLine();
            if (input.equals("end")) {
              //  basket.printCart();
                break;
            }
            String[] parts = input.split(" ");
            productNumber = Integer.parseInt(parts[0]) - 1;
            productCount = Integer.parseInt(parts[1]);
            basket.addToCart(productNumber, productCount);
            basket.saveBin(saveFile);
        }
        basket.printCart(textFile);

    }

    public static void showPrice() {
        for (int i = 0; i < products.length; i++) {
            System.out.println(products[i] + " " + prices[i] + " руб./шт.");
        }
    }
}