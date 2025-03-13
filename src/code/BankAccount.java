/**
 * Represents a bank account with basic operations such as
 * deposits, withdrawals, and transfers between accounts.
 *
 * @author Alan riveros
 * @version 1.0
 */

public class BankAccount
{
    private static final int VALID_ACCOUN_NUM_LEN;
    private static final int MIN_BALANCE;

    static {
        VALID_ACCOUN_NUM_LEN = 5;
        MIN_BALANCE          = 0;
    }

    private final String accountNumber;
    private int balanceInUsd;


    /**
     * Constructs a new BankAccount.
     *
     * @param accountNumber The bank account number (must be exactly VALID_ACCOUN_NUM_LEN digits).
     * @param balanceInUsd The initial account balanceInUsd (cannot be negative).
     * @throws IllegalArgumentException if the account number is invalid or the balanceInUsd is negative.
     */
    public BankAccount(final String accountNumber,
                       final int balanceInUsd){

        accountNumberValidator(accountNumber);
        balanceValidator(balanceInUsd);
        this.accountNumber = accountNumber;
        this.balanceInUsd = balanceInUsd;

    }

    /**
     * Method used to retrieve the account number.
     *
     * @return the account number.
     */
    public String getAccountNumber()
    {
        return accountNumber;
    }


    /**
     *  Method used to retrieve the balance in USD currency of the account.
     *
     * @return The USD balance
     */
    public int getBalanceUsd()
    {
        return balanceInUsd;
    }


    /**
     * Deposits an amount into the account.
     *
     * @param amount The amount to deposit (must be greater than MIN_BALANCE).
     * @throws IllegalArgumentException if the deposit amount is less than or equal to 0.
     */
    public void deposit(final int amount)
    {
        if(amount <= MIN_BALANCE) {
            throw new IllegalArgumentException("Deposit amount must be positive");
        }
        this.balanceInUsd += amount;
    }

    /**
     * Withdraws an amount from the account.
     *
     * @param amountToWithdraw The amount to withdraw (must be less than or equal to the available balance).
     * @throws IllegalArgumentException if the withdrawal amount exceeds the balance or is zero/negative.
     */
    public void withdraw(final int amountToWithdraw)
    {
        if(amountToWithdraw > this.balanceInUsd){
            throw new IllegalArgumentException("Insufficient funds");
        }
        else {
            this.balanceInUsd -= amountToWithdraw;
        }

    }
    /**
     * Transfers money from this account to another bank account.
     *
     * @param accountToReceive  The recipient bank account.
     * @param accountNumber     The recipient's account number (used for verification).
     * @param moneyToTransfer   The amount to transfer (must be greater than MIN_BALANCE and within available balance).
     * @throws IllegalArgumentException if:
     *         - The transfer amount is greater than the available balance.
     *         - The recipient account is null or has an empty account number.
     *         - The transfer amount is zero or negative.
     */
    public void transferToBank(final BankAccount accountToReceive,
                               final String accountNumber,
                               final int moneyToTransfer)
    {
        if (moneyToTransfer > this.balanceInUsd){
            throw new IllegalArgumentException("Insufficient funds");
        }
        else if(accountToReceive == null || accountNumber.isBlank())
        {
            throw new IllegalArgumentException("account is empty");
        }
        else if(moneyToTransfer <= MIN_BALANCE)
        {
            throw new IllegalArgumentException("Amount must be greater than zero");
        }
        else
        {
            this.withdraw(moneyToTransfer);
            accountToReceive.deposit(moneyToTransfer);
        }
    }

    /**
     * Validates that the initial balance is not negative.
     *
     * @param balanceToValidate The initial balance to validate.
     * @throws IllegalArgumentException if the balance is negative.
     */
    private void balanceValidator(final int balanceToValidate){

        if(balanceToValidate < MIN_BALANCE)
        {
            throw new IllegalArgumentException("Initial balance cannot be negative");
        }
    }

    /**
     * Validates the bank account number.
     * Ensures that the account number is not blank and has exactly VALID_ACCOUN_NUM_LEN digits.
     *
     * @param accountNumberToValidate The account number to validate.
     * @throws IllegalArgumentException if:
     *         - The account number is blank.
     *         - The account number does not have exactly VALID_ACCOUN_NUM_LEN digits.
     */
    private void accountNumberValidator(final String accountNumberToValidate)
    {
        if(accountNumberToValidate.isBlank()){
            throw new IllegalArgumentException("Account number cannot be blank");
        }
        if(accountNumberToValidate.length() != VALID_ACCOUN_NUM_LEN)
        {
            throw new IllegalArgumentException("Account number must be exactly 5 digits");
        }

    }
}
