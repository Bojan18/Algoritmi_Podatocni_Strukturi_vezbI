package Apteka;

import java.util.Hashtable;
import java.util.Locale;
import java.util.Objects;
import java.util.Scanner;

public class Apteka {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        scanner.nextLine();

        Hashtable<CureName, Cure> table = new Hashtable<>();

        for (int i = 0; i < n; i++) {
            String text = scanner.nextLine();
            String [] parts = text.split("\\s+");

            String name = parts[0];
            int positive = Integer.parseInt(parts[1]);
            int price = Integer.parseInt(parts[2]);
            int numOfCures = Integer.parseInt(parts[3]);

            Cure cure = new Cure(name, positive, price, numOfCures);
            CureName cureName = new CureName(name);

            table.put(cureName, cure);
        }

        while (true){
            String line = scanner.nextLine();
            if(line.equals("KRAJ"))
                break;
            CureName cureName = new CureName(line);

            int requestedNumberOfCures = Integer.parseInt(scanner.nextLine());

            if(table.containsKey(cureName)) {
                Cure cure = table.get(cureName);
                System.out.println(cure.toString());

                if(cure.makeOrder(requestedNumberOfCures)){
                    System.out.println("Napravena naracka!");
                } else System.out.println("Nema dovolno lekovi");

            } else System.out.println("Nema takov lek");
        }

    }

}

class CureName {
    String shortName;

    public CureName(String shortName) {
        this.shortName = shortName.toLowerCase();
    }

    public int shortNameMap() {
        return (29 * (29 * (29 * 0 + shortName.charAt(0)) + shortName.charAt(1) % 102780 + shortName.charAt(2)));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CureName cureName = (CureName) o;
        return Objects.equals(shortName, cureName.shortName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(shortName);
    }

}

class Cure {
    String name;
    int positive;
    int price;
    int numOfCures;

    public Cure(String name, int positive, int price, int numOfCures) {
        this.name = name.toUpperCase();
        this.positive = positive;
        this.price = price;
        this.numOfCures = numOfCures;
    }

    public String getPos(){
        if(positive == 1)
            return "POZ";
        else return "NEG";
    }

    //Доколку нарачката на клиентот е поголема од залихата се печати Nema dovolno lekovi инаку Napravena naracka.
    public boolean makeOrder(int n){
        if(numOfCures >= n){
            numOfCures -= n;
            return true;
        }
        else return false;
    }

    @Override
    public String toString() {
        return name + '\n' + getPos() + '\n' + price + '\n' + numOfCures;
    }
}