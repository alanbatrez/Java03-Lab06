import java.util.HashMap;
import java.util.Map;
/**
 * Represents a bank that manages multiple bank accounts.
 * Allows adding accounts, retrieving accounts, and calculating total balance.
 *
 * @author Alan Riveros
 * @version 1.0
 */
public class Bank
{
    private static final int TOTAL_INITIALIZER;

    static {
        TOTAL_INITIALIZER = 0;
    }
    private Map<String, BankAccount> accounts;


    /**
     * Constructs a new Bank instance with an empty list of accounts.
     */
    public Bank()
    {
        accounts  = new HashMap<>();
    }


    /**
     * Adds a new account to the bank.
     *
     * @param accountToAdd The bank account to add.
     */
    public void addAccount(final BankAccount accountToAdd)
    {
        accounts.put(accountToAdd.getAccountNumber(), accountToAdd);
    }


    /**
     * Retrieves a bank account based on its account number.
     *
     * @param accountNumber The account number to look up.
     * @return The corresponding BankAccount object.
     * @throws IllegalArgumentException if the account is not found.
     */
    public BankAccount retrieveAccount(final String accountNumber)
    {
        if (!accounts.containsKey(accountNumber)) {
            throw new IllegalArgumentException("Account not found");
        }
        return accounts.get(accountNumber);
    }

    /**
     * Calculates the total balance of all accounts in the bank.
     *
     * @return The total balance of all accounts in USD.
     */
    public double totalBalanceUsd()
    {
        double total = TOTAL_INITIALIZER;
        for(final BankAccount account : accounts.values()) {
            total += account.getBalanceUsd();
        }
        return total;
    }
}
