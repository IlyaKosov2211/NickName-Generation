import java.util.Random;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Random random = new Random();
        String[] texts = new String[100_000];
        for (int i = 0; i < texts.length; i++) {
            texts[i] = Utils.generateText("abc", 3 + random.nextInt(3));
        }

        Thread palindrome = new Thread(() -> {
            for (String text : texts) {
                if (Utils.isPalindrome(text) && !Utils.isSameChar(text)) {
                    Utils.incrementCounter(text.length());
                }
            }
        });
        palindrome.start();

        Thread sameChar = new Thread(() -> {
            for (String text : texts) {
                if (Utils.isSameChar(text)) {
                    Utils.incrementCounter(text.length());
                }
            }
        });
        sameChar.start();

        Thread ascendingOrder = new Thread(() -> {
            for (String text : texts) {
                if (!Utils.isPalindrome(text) && Utils.isAscendingOrder(text)) {
                    Utils.incrementCounter(text.length());
                }
            }
        });
        ascendingOrder.start();

        sameChar.join();
        ascendingOrder.join();
        palindrome.join();

        System.out.println("Красивых слов с длиной 3: " + Utils.counter1);
        System.out.println("Красивых слов с длиной 4: " + Utils.counter2);
        System.out.println("Красивых слов с длиной 5: " + Utils.counter3);
    }
}