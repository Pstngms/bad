import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;

public class test {
    @Test
    @DisplayName("Тестирование функции Calculations c рабочим днем с 9 до 18 и двумя перерывами")
    public void CalculationsTestWorkDayFrom9To18WithTwoPauses() {
        NewCalculation calculation = new NewCalculation();
        LocalTime[] startTimes = new LocalTime[] {
                LocalTime.of(12, 0),
                LocalTime.of(15, 0),
        };
        int[] durations = { 30, 45 };
        String[] array = calculation.getPeriods(startTimes, durations, 30, LocalTime.of(8, 0), LocalTime.of(18, 0));
        for(String s : array) {
            System.out.println(s);
        }
        String[] answer = {
                "08:00-08:30",
                "08:30-09:00",
                "09:00-09:30",
                "09:30-10:00",
                "10:00-10:30",
                "10:30-11:00",
                "11:00-11:30",
                "11:30-12:00",
                "12:30-13:00",
                "13:00-13:30",
                "13:30-14:00",
                "14:00-14:30",
                "14:30-15:00",
                "15:45-16:15",
                "16:15-16:45",
                "16:45-17:15",
                "17:15-17:45"
        };
        Assertions.assertArrayEquals(array, answer);
    }
}
