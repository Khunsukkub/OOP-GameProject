package model;

class Interest {
    private static int base_interate_pct = 5;

    private static double interestCalurating(double playerBudget, int current_turn) {
        return (base_interate_pct * Math.log10(playerBudget) * Math.log(current_turn))/100;
    }

    static double pct(double playerBudget , int current_turn) {
        return playerBudget*interestCalurating(playerBudget,current_turn);
    }
}