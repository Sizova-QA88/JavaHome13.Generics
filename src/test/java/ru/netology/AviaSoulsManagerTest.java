package ru.netology;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AviaSoulsManagerTest {
    AviaSoulsManager purchase = new AviaSoulsManager();

    Ticket tick1 = new Ticket("Москва", "Барселона", 50_000, 10, 16); // 6
    Ticket tick2 = new Ticket("Москва", "Калиниград", 10_000, 11, 14); // 3
    Ticket tick3 = new Ticket("Москва", "Нью-Йорк", 20_000, 10, 17); // 7
    Ticket tick4 = new Ticket("Калиниград", "Симферополь", 20_000, 14, 19); // 5
    Ticket tick5 = new Ticket("Милан", "Дубаи", 180_000, 10, 20); // 10
    Ticket tick6 = new Ticket("Москва", "Германия", 20_000, 12, 19); // 7
    Ticket tick7 = new Ticket("Москва", "Нью-Йорк", 35_000, 17, 23); // 6

    @BeforeEach
    public void setUp() {
        purchase.add(tick1);
        purchase.add(tick2);
        purchase.add(tick3);
        purchase.add(tick4);
        purchase.add(tick5);
        purchase.add(tick6);
        purchase.add(tick7);
    }


    @Test
    public void compareAllTickets() {

        Assertions.assertEquals(-1, tick1.compareTo(tick5));
        Assertions.assertEquals(1, tick7.compareTo(tick2));
        Assertions.assertEquals(0, tick3.compareTo(tick6));
    }

    @Test
    public void sortingByAscendingPrice() {

        Ticket[] actual = purchase.search("Москва", "Нью-Йорк");
        Ticket[] expected = {tick3, tick7};

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void compareByFlightTime() {
        TicketTimeComparator comparator = new TicketTimeComparator();

        Assertions.assertEquals(-1, comparator.compare(tick2, tick6));
        Assertions.assertEquals(1, comparator.compare(tick5, tick4));
        Assertions.assertEquals(0, comparator.compare(tick7, tick1));
    }

    @Test
    public void sortingByFlightTime() {
        TicketTimeComparator comparator = new TicketTimeComparator();

        Ticket[] expected = {tick7, tick3};
        Ticket[] actual = purchase.searchAndSortBy("Москва", "Нью-Йорк", comparator);

        Assertions.assertArrayEquals(expected, actual);
    }

}