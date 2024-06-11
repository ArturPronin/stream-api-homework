import java.util.*;
import java.util.function.Predicate;

/**
 * 1. Найти все транзакции за 2011 год и отсортировать их по сумме (от меньшей к большей).
 * 2. Вывести список неповторяющихся городов, в которых работают трейдеры.
 * 3. Найти всех трейдеров из Кембриджа и отсортировать их по именам.
 * 4. Вернуть строку со всеми именами трейдеров, отсортированными в алфавитном порядке.
 * 5. Выяснить, существует ли хоть один трейдер из Милана.
 * 6. Вывести суммы всех транзакций трейдеров из Кембриджа.
 * 7. Какова максимальная сумма среди всех транзакций?
 * 8. Найти транзакцию с минимальной суммой.
 */


public class PuttingIntoPractice {

    public static void main(String... args) {
        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario", "Milan");
        Trader alan = new Trader("Alan", "Cambridge");
        Trader brian = new Trader("Brian", "Cambridge");

        List<Transaction> transactions = Arrays.asList(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950)
        );

        /**
         * 1. Найти все транзакции за 2011 год и отсортировать их по сумме (от меньшей к большей).
         */
        List<Transaction> firstTask = transactions.stream()
                .filter(new Predicate<Transaction>() {
                    @Override
                    public boolean test(Transaction transaction) {
                        return transaction.getYear() == 2011;
                    }
                })
                //.filter(transaction -> transaction.getYear() == 2011)
                .sorted(Comparator.comparing(Transaction::getValue))
                .toList();
        System.out.println("1. " + firstTask);

        /**
         * 2. Вывести список неповторяющихся городов, в которых работают трейдеры.
         */
        Set<String> uniqueCityTraders = new HashSet<>();
        List<String> secondTask = transactions.stream()
                .map(transaction -> transaction.getTrader().getCity())
                .filter(uniqueCityTraders::add)
                .toList();
        System.out.println("2. " + secondTask);
        //trader -> trader.getCity().equals("Cambridge")

        /**
         * 3. Найти всех трейдеров из Кембриджа и отсортировать их по именам.
         */
        Set<String> uniqueTraders = new HashSet<>();
        List<Trader> thirdTask = transactions.stream()
                .map(Transaction::getTrader)
                .filter(trader -> uniqueTraders.add(trader.getName()))
                .filter(trader -> trader.getCity().contains("Cambridge"))
                .sorted(Comparator.comparing(Trader::getName))
                .toList();
        System.out.println("3. " + thirdTask);

        /**
         * 4. Вернуть строку со всеми именами трейдеров, отсортированными в алфавитном порядке.
         */
        Set<String> uniqueTradersNames = new HashSet<>();
        String fourthTask = transactions.stream()
                .map(Transaction::getTrader)
                .filter(trader -> uniqueTradersNames.add(trader.getName()))
                .sorted(Comparator.comparing(Trader::getName))
                .map(trader -> trader.getName() + " ")
                .reduce("", String::concat);
        System.out.println("4. " + fourthTask);

        /**
         * 5. Выяснить, существует ли хоть один трейдер из Милана.
         */
        boolean fifthTask = transactions.stream()
                .map(Transaction::getTrader)
                .anyMatch(trader -> trader.getCity().contains("Milan"));

        if (fifthTask) {
            System.out.println("5. существует " + true);
        } else System.out.println("5. не существует " + false);

        /**
         * 6. Вывести суммы всех транзакций трейдеров из Кембриджа.
         */
        int sixthTask = transactions.stream()
                .filter(transaction -> transaction.getTrader().getCity().contains("Cambridge"))
                .mapToInt(Transaction::getValue)
                .sum();
        System.out.println("6. " + sixthTask);

        /**
         * 7. Какова максимальная сумма среди всех транзакций?
         */
        OptionalInt seventhTask = transactions.stream()
                .mapToInt(Transaction::getValue)
                .max();
        System.out.println("7. " + seventhTask.getAsInt());

        /**
         * 8. Найти транзакцию с минимальной суммой.
         */
        OptionalInt eighthTask = transactions.stream()
                .mapToInt(Transaction::getValue)
                .min();
        System.out.println("8. " + eighthTask.getAsInt());
    }
}