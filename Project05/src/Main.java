import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

import static java.lang.Integer.parseInt;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Scanner scanner = new Scanner(System.in);

        System.out.println("     Alarm Clock");
        System.out.println("**********************");
        System.out.print("Please set your alarm clock date (DD/MM/YYYY): ");
        String DMY = scanner.nextLine();
        System.out.println(DMY);
        String day = DMY.substring(0, 2);
        String month = DMY.substring(3, 5);
        int year = parseInt(DMY.substring(6, 10));

        System.out.print("Please set your alarm clock time (24 hour time): ");
        String time = scanner.nextLine();
        String hour = time.substring(0, 2);
        String minute = time.substring(3, 5);
        String second = "00";
        LocalDateTime dateTime = LocalDateTime.parse(year + "-" + month + "-" + day + "T" + hour + ":" + minute + ":" + second);
        LocalDateTime datetime = LocalDateTime.now();

        if (dateTime.isBefore(datetime) || dateTime.isEqual(datetime)) {
            System.out.println("The alarm clock must be set to the future.");
        }
        System.out.println("Alarm set");
        while (true) {
            if (parseInt(day) == LocalDate.now().getDayOfMonth()) {
                Timer timer = new Timer();
                TimerTask task = new TimerTask() {
                    int counter = ((parseInt(hour) * 60 + parseInt(minute)) - (LocalDateTime.now().getHour() * 60 + LocalDateTime.now().getMinute())) + 1;

                    @Override
                    public void run() {
                        counter--;
                        if (counter == 0) {
                            System.out.println("ALARM END");
                            timer.cancel();
                            scanner.close();
                            System.exit(0);
                        }
                    }
                };
                timer.scheduleAtFixedRate(task, 0, 60000);
                break;
            }
        }
    }
}
