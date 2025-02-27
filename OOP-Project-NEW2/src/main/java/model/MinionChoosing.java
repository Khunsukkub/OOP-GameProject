package model;

import java.util.Scanner;

public class MinionChoosing {
    public static int chooseMinionNumber() {
        System.out.println("--------------------------------------");
        System.out.print("How many minions? (1-5) :");
        Scanner sc = new Scanner(System.in);
        int minionNumber = sc.nextInt();

        return minionNumber;
    }
}
