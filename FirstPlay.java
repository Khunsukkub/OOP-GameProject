public class FirstPlay {

    int first_play; //ใส่ระบบสุ่มให้หน่อยงับ

    public FirstPlay(Player player1 , Player player2){ //สุ่มหาผู้เล่นคนแรกและมอบรหัสทีม

        if(first_play == 0){
            player1.team = 1;
            player2.team = 2;
        } else {
            player1.team = 2;
            player2.team = 1;
        } // เลข 1 คือ ผู้เล่นทีมที่ 1 และ เป็นคนเล่นอันดับที่ 1

    }
}
