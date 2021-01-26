public class Bank {
    public static void main(String[] args) {
        Account account = new Account();
        User1 user1 = new User1(account);
        User2 user2 = new User2(account);

        user1.start();
        user2.start();
    }
}

class Account {
    private int balance = 10000;

    public int getBalance() {
        return balance;
    }

    public void withdraw(int money){
        synchronized (this){
            balance -= money;
            try { Thread.sleep(2000); } catch (InterruptedException e) {} //통장 예금을 확인하는데 2초가 걸린다고 가정
            System.out.println(Thread.currentThread().getName() + " : " + getBalance());
        }
    }
}

class User1 extends Thread{
    Account account;

    public User1(Account account) {
        this.account = account;
    }

    @Override
    public void run() {
        account.withdraw(3000);
    }
}
class User2 extends Thread{
    Account account;

    public User2(Account account) {
        this.account = account;
    }

    @Override
    public void run() {
        account.withdraw(5000);
    }
}

