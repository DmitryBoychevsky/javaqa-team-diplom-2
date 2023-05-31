package ru.netology.javaqadiplom;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SavingAccountTest {

    // Тесты конструктора

    @Test
    public void shouldCreateAccountIfInitialIsEqualMin() {
        SavingAccount account = new SavingAccount(1_000, 1_000, 6_000, 10);

        int expected = 1_000;
        int actual = account.getBalance();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldCreateAccountIfInitialIsEqualMax() {
        SavingAccount account = new SavingAccount(5_000, 1_000, 5_000, 10);

        int expected = 5_000;
        int actual = account.getBalance();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldNotCreateAccountWithNegativeMinBalance() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
           new SavingAccount(500, -5_000, 100_000, 10);
        });
    }

    @Test
    public void shouldNotCreateAccountWithNegativeRate() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
           new SavingAccount(500, 0, 100_000, -5);
        });
    }

    @Test
    public void shouldNotCreateAccountIfMinMoreThanMax() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
           new SavingAccount(1_000, 2_000, 1_500, 15);
        });
    }

    @Test
    public void shouldNotCreateAccountIfInitialMoreThanMax() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
           new SavingAccount(10_000, 2_000, 5_000, 10);
        });
    }

    @Test
    public void shouldNotCreateAccountIfInitialLessThanMin() {
      Assertions.assertThrows(IllegalArgumentException.class, () -> {
          new SavingAccount(1_000, 2_000, 5_000, 10);
      });
    };

    // Тесты метода pay

    @Test
    public void shouldBeTrueIfPayWithAllDateValid() {
        SavingAccount account = new SavingAccount(3_000, 1_000, 10_000, 10);

        Assertions.assertTrue(account.pay(1_000));
    }

    @Test
    public void shouldSubtractValidAmountIfAllDateValid() {
        SavingAccount account = new SavingAccount(3_000, 1_000, 10_000, 10);
        account.pay(1_000);

        int expected = 2_000;
        int actual = account.getBalance();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldBeFalseIfAmountLessThanZeroWhenPay() {
        SavingAccount account = new SavingAccount(3_000, 1_000, 10_000, 10);

        Assertions.assertFalse(account.pay(-100));
    }

    @Test
    public void shouldBeFalseIfAmountIsEqualZeroWhenPay() {
        SavingAccount account = new SavingAccount(3_000, 1_000, 10_000, 10);

        Assertions.assertFalse(account.pay(-100));
    }

    @Test
    public void shouldNotBalanceChangesIfAmountLessThanZeroWhenPay() {
        SavingAccount account = new SavingAccount(3_000, 1_000, 10_000, 10);
        account.pay(-50);

        int expected = 3_000;
        int actual = account.getBalance();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldNotBalanceChangesIfAmountIsEqualZeroWhenPay() {
        SavingAccount account = new SavingAccount(3_000, 1_000, 10_000, 10);
        account.pay(0);

        int expected = 3_000;
        int actual = account.getBalance();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldBeFalseIfTryingPayBalanceWillBeLessThanMin() {
        SavingAccount account = new SavingAccount(3_000, 1_000, 10_000, 10);

        Assertions.assertFalse(account.pay(2_500));
    }

    @Test
    public void shouldNotBalanceChangesIfTryingPayBalanceWillBeLessThanMin() {
        SavingAccount account = new SavingAccount(3_000, 1_000, 10_000, 10);
        account.pay(2_500);

        int expected = 3_000;
        int actual = account.getBalance();
        Assertions.assertEquals(expected, actual);
    }

    // Тесты метода add

    @Test
    public void shouldBeTrueIfAddWithAllDateValid() {
        SavingAccount account = new SavingAccount(3_000, 1_000, 10_000, 10);

        Assertions.assertTrue(account.add(1_000));
    }

    @Test
    public void shouldAddValidAmountIfAllDateValid() {
        SavingAccount account = new SavingAccount(3_000, 1_000, 10_000, 10);
        account.add(1_000);

        int expected = 4_000;
        int actual = account.getBalance();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldBeFalseIfAmountLessThanZeroWhenAdd() {
        SavingAccount account = new SavingAccount(3_000, 1_000, 10_000, 10);

        Assertions.assertFalse(account.add(-100));
    }

    @Test
    public void shouldBeFalseIfAmountIsEqualZeroWhenAdd() {
        SavingAccount account = new SavingAccount(3_000, 1_000, 10_000, 10);

        Assertions.assertFalse(account.add(0));
    }

    @Test
    public void shouldNotBalanceChangesIfAmountLessThanZeroWhenAdd() {
        SavingAccount account = new SavingAccount(3_000, 1_000, 10_000, 10);
        account.add(-50);

        int expected = 3_000;
        int actual = account.getBalance();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldNotBalanceChangesIfAmountIsEqualZeroWhenAdd() {
        SavingAccount account = new SavingAccount(3_000, 1_000, 10_000, 10);
        account.add(0);

        int expected = 3_000;
        int actual = account.getBalance();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldBeFalseIfBalanceMoreThanMaxAfterAdd() {
        SavingAccount account = new SavingAccount(3_000, 1_000, 10_000, 10);

        Assertions.assertFalse(account.add(8_000));
    }

    @Test
    public void shouldNotBalanceChangeIfBalanceWillBeMoreThanMaxAfterAdd() {
        SavingAccount account = new SavingAccount(3_000, 1_000, 10_000, 10);
        account.add(8_000);

        int expected = 3_000;
        int actual = account.getBalance();
        Assertions.assertEquals(expected, actual);
    }

    // тесты для yearChange

    @Test
    public void shouldReturnYearChangeIfAllDateValid() {
        SavingAccount account = new SavingAccount(200, 0, 20_000, 15);

        int expected = 30;
        int actual = account.yearChange();
    }

}
