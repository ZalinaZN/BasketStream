import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    static String[] products = {"1.Pan", "2.Manzanas", "3.Helados"};
    static int[] prices = {50, 100, 80};
    private static File textFile;

    public static void main(String[] args) throws IOException {

        int productNumber = 0;
        int productCount = 0;

        File saveFile = new File("basket.txt");

        Basket basket = null;
        if (saveFile.exists()) {//проверяем наличие файла для восстановления данных о содержимом корзины
            basket = Basket.loadFromTxtFile(saveFile);
        } else {// если файл неайден, создаем новый
            basket = new Basket(products, prices);
        }
        Scanner scanner = new Scanner(System.in);
        System.out.println("Lista de posibles bienes para la compra:");


        while (true) {
            showPrice();
            System.out.println("Ingrese el número de artículo y la cantidad separados por un espacio. " +
                    "Para completar, ingrese `end`");
            String input = scanner.nextLine();
            if (input.equals("end")) {
              //  basket.printCart();
                break;
            }
            String[] parts = input.split(" ");
            productNumber = Integer.parseInt(parts[0]) - 1;
            productCount = Integer.parseInt(parts[1]);
            basket.addToCart(productNumber, productCount);
            basket.saveTxt(saveFile);
        }
        basket.printCart(textFile);

    }

    public static void showPrice() {
        for (int i = 0; i < products.length; i++) {
            System.out.println(products[i] + " " + prices[i] + " rub./cantidad.");
        }
    }
}