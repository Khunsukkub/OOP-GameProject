import java.util.Random;

public class FirstPlay {
    int first_play; // ตัวแปรสำหรับเก็บค่าสุ่ม
    public FirstPlay(Player player1, Player player2) {
        // สุ่มค่า 0 หรือ 1
        Random random = new Random();
        first_play = random.nextInt(2); // สุ่มค่า 0 หรือ 1
        // กำหนดทีมให้ผู้เล่นตามค่าที่สุ่มได้
        if (first_play == 0) {
            player1.team = 1;
            player2.team = 2;
        } else {
            player1.team = 2;
            player2.team = 1;
        }
        // เลข 1 คือ ผู้เล่นทีมที่ 1 และ เป็นคนเล่นอันดับที่ 1
    }
}