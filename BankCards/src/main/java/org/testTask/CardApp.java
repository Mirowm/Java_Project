package org.testTask;
public class App 
{
    public static void main( String[] args )
    {
        System.out.println("------------------CreditCard--------------------");
        BankCard creditCard = new CreditCard("10000");
        System.out.println(creditCard.getAvailableMoney());

        System.out.println("--------------------------------------");

        creditCard.fillUpBalance("5000");
        System.out.println("После пополнения карты на 5 000:");
        System.out.println(creditCard.getAvailableMoney());

        System.out.println("--------------------------------------");

        creditCard.pay("5000");
        System.out.println("После оплаты на 5 000:");
        System.out.println(creditCard.getAvailableMoney());

        System.out.println("--------------------------------------");

        creditCard.pay("3000");
        System.out.println("После оплаты на 3 000:");
        System.out.println(creditCard.getAvailableMoney());

        System.out.println("--------------------------------------");

        creditCard.fillUpBalance("2000");
        System.out.println("После пополнения карты на 2 000:");
        System.out.println(creditCard.getAvailableMoney());

        System.out.println("--------------------------------------");

        creditCard.fillUpBalance("2000");
        System.out.println("После пополнения карты на 2 000:");
        System.out.println(creditCard.getAvailableMoney());

        System.out.println("------------------DebitCard--------------------");
        BankCard debitCard = new DebitCard();
        System.out.println(debitCard.getAvailableMoney());

        System.out.println("--------------------------------------");

        debitCard.fillUpBalance("10000");
        System.out.println("После пополнения карты на 10 000:");
        System.out.println(debitCard.getAvailableMoney());

        System.out.println("--------------------------------------");

        debitCard.pay("2000");
        System.out.println("После оплаты на 2 000:");
        System.out.println(debitCard.getAvailableMoney());

        System.out.println("--------------------------------------");

        debitCard.pay("9000");
        System.out.println("После  попытки оплаты на 9 000:");
        System.out.println(debitCard.getAvailableMoney());

        System.out.println("------------------UltraCreditCard--------------------");
        BankCard creditCard = new CreditCard("10000");
        System.out.println(creditCard.getAvailableMoney());

        System.out.println("--------------------------------------");

        creditCard.fillUpBalance("5000");
        System.out.println("После пополнения карты на 5 000:");
        System.out.println(creditCard.getAvailableMoney());

        System.out.println("--------------------------------------");

        creditCard.pay("5000");
        System.out.println("После оплаты на 5 000:");
        System.out.println(creditCard.getAvailableMoney());

        System.out.println("--------------------------------------");

        creditCard.pay("3000");
        System.out.println("После оплаты на 3 000:");
        System.out.println(creditCard.getAvailableMoney());

        System.out.println("--------------------------------------");

        creditCard.fillUpBalance("2000");
        System.out.println("После пополнения карты на 2 000:");
        System.out.println(creditCard.getAvailableMoney());

        System.out.println("--------------------------------------");

        creditCard.fillUpBalance("2000");
        System.out.println("После пополнения карты на 2 000:");
        System.out.println(creditCard.getAvailableMoney());
        System.out.println("------------------UltraDebitCard--------------------");
        BankCard debitCard = new DebitCard();
        System.out.println(debitCard.getAvailableMoney());

        System.out.println("--------------------------------------");

        debitCard.fillUpBalance("10000");
        System.out.println("После пополнения карты на 10 000:");
        System.out.println(debitCard.getAvailableMoney());

        System.out.println("--------------------------------------");

        debitCard.pay("2000");
        System.out.println("После оплаты на 2 000:");
        System.out.println(debitCard.getAvailableMoney());

        System.out.println("--------------------------------------");

        debitCard.pay("9000");
        System.out.println("После  попытки оплаты на 9 000:");
        System.out.println(debitCard.getAvailableMoney());

    }

}
