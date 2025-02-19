package com.example.oopprojectnew2;

import model.MainGame;
import model.Map;
import model.Player;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class OopProjectNew2Application {

    public static void main(String[] args) {
        MainGame.getInstance();
        Map.getInstance();
        System.out.println("1 | Player VS Player    2 | Player VS Bot    3 | Bot VS Bot");
        System.out.print("Please select mode : ");
        Scanner sc = new Scanner(System.in);
        int mode = sc.nextInt();

        System.out.println("--------------------------------------");
        switch (mode) {
            case 1:
                // สร้าง Player 1 และ Player 2
                System.out.print("Enter name for Player 1: ");
                String name1 = sc.next();
                Player player1 = new Player(name1, MainGame.init_budget , 1);

                System.out.print("Enter name for Player 2: ");
                String name2 = sc.next();
                Player player2 = new Player(name2, MainGame.init_budget , 2);

                Map.players[0] = player1;
                Map.players[1] = player2;
                MainGame.GameSetting(player1, player2);
                break;

            case 2:
                // สามารถเพิ่ม logic สำหรับ Player VS Bot ได้ที่นี่
                System.out.println("Player VS Bot ยังไม่ได้พัฒนา");
                break;

            case 3:
                // สามารถเพิ่ม logic สำหรับ Bot VS Bot ได้ที่นี่
                System.out.println("Bot VS Bot ยังไม่ได้พัฒนา");
                break;

            default:
                System.out.println("Invalid option.");
                break;
        }
        sc.close();
        System.out.println("--------------------------------------");
    }

}
