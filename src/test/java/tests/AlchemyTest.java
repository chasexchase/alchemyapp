package tests;

import org.junit.jupiter.api.Test;

public class AlchemyTest extends BaseTest {

    @Test
    void testHintsIncreaseAfterAd() throws InterruptedException {
        app.playGame();

        int initialHints = app.mainScreenHints().getMainScreenHintsCount();

        if (initialHints >= 4) {
            if (initialHints == 4) {
                System.out.println("Подсказок уже 4, тест завершён");
            } else {
                System.out.println("Подсказок больше 4 (" + initialHints + "), тест завершён");
            }
            return;
        }

        if (initialHints == 1 || initialHints == 3) {
            System.out.println("Сейчас " + initialHints +
                    " подсказок. Просмотр рекламы даст +2, будет нечетное число");
            return;
        }

        int adsToWatch = 0;
        if (initialHints == 0) {
            adsToWatch = 2;
            System.out.println("Подсказок 0 — нужно посмотреть 2 рекламы");
        } else if (initialHints == 2) {
            adsToWatch = 1;
            System.out.println("Подсказок 2 — нужно посмотреть 1 рекламу");
        }

        app.addHintOnMainScreen();

        for (int i = 1; i <= adsToWatch; i++) {
            System.out.println("Просмотр рекламы №" + i);

            app.watchAdAndWait();

            int hintsAfterAd = app.adWindowHints().getHintsSumAfterAd();
            System.out.println("Подсказок после рекламы: " + hintsAfterAd);
        }

        System.out.println("Тест завершён");
    }
}