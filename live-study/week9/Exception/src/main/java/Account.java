public class Account {
    int balance;

    public void withdraw(int money) throws BalanceInsufficientException {
        if(money > balance){
            throw new BalanceInsufficientException("잔액이 부족합니다.");
        }
    }
}
