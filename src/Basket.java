import java.io.*;
import java.util.Arrays;

public class Basket {
    int currentPrice;
    private String[] product;
    private int[] price;
    private int[] amountPr;

    public Basket() {
    }
    public Basket(String[]products, int[] prices){
    this.product = products;
    this.price = prices;
    this.amountPr = new int[product.length];
    }
    public void addToCart(int productNum, int amount){
    amountPr[productNum] += amount;
    }// метод добавления продуктов в корзину
    public void printCart(File textFile) throws IOException { //метод вывода корзины в консоль
        int summProduct = 0;
        System.out.println("Lista de la compra:");
        for (int i = 0; i < product.length; i++) {
            if (amountPr[i] > 0) {
                currentPrice = amountPr[i] * price[i];
                summProduct += currentPrice;
                System.out.printf("%s %d rub./cantidad. %d cantidad. %d rub.\n", product[i], price[i], amountPr[i], currentPrice);
            }
        }
            System.out.printf("Total: %d rub.", summProduct);
    }
        public void saveTxt(File textFile) throws IOException {
            try (PrintWriter out = new PrintWriter(textFile)) {
                for (String product : product) {
                    out.print(product + " ");
                }
                out.println();
                for (int pr : price) {
                    out.print(pr + " ");
                }
                out.println();
                for (int a : amountPr) {
                    out.print(a + " ");
                }
                out.println();
            }
        }
        public static Basket loadFromTxtFile(File textFile){//throws IOException{
            Basket basket = new Basket();
            try (BufferedReader bufferedReader = new BufferedReader(new FileReader(textFile))) {
                String productStr = bufferedReader.readLine();
                String priceStr = bufferedReader.readLine();
                String amountStr = bufferedReader.readLine();

                basket.product = productStr.split(" ");
                basket.price = Arrays.stream(priceStr.split(" "))
                        .map(Integer::parseInt)
                        .mapToInt(Integer::intValue)
                        .toArray();
                basket.amountPr = Arrays.stream(amountStr.split(" "))
                        .map(Integer::parseInt)
                        .mapToInt(Integer::intValue)
                        .toArray();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return basket;
        }

}
