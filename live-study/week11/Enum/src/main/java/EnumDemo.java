enum Job {
    DOCTOR(5000){
        @Override
        double getMonthlyWages(int day) {
            return getDailyWages() * day * 2.5;
        }
    },
    TEACHER(1500){
        @Override
        double getMonthlyWages(int day) {
            return getDailyWages() * day * 2;
        }
    },
    NURSE(1000){
        @Override
        double getMonthlyWages(int day) {
            return getDailyWages() * day * 1.5;
        }
    };

    private final int dailyWages; //일급

    Job(int dailyWages){
        this.dailyWages = dailyWages;
    }

    public int getDailyWages() {
        return dailyWages;
    }

    abstract double getMonthlyWages(int day);


}

public class EnumDemo {
    public static void main(String[] args) {
        System.out.println("----일급----");
        System.out.println("DOCTOR : " + Job.DOCTOR.getDailyWages());
        System.out.println("TEACHER : " + Job.TEACHER.getDailyWages());
        System.out.println("NURSE : " + Job.NURSE.getDailyWages());

        System.out.println("----월급----");
        System.out.println("DOCTOR : " + Job.DOCTOR.getMonthlyWages(30));
        System.out.println("TEACHER : " + Job.TEACHER.getMonthlyWages(30));
        System.out.println("NURSE : " + Job.NURSE.getMonthlyWages(30));

        Job[] arr= Job.values();

        Job job = Job.valueOf("DOCTOR");
        if(job == Job.DOCTOR){
            System.out.println("동일합니다.");
        }
    }
}
