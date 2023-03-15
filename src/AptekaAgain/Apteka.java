package AptekaAgain;

import java.util.Hashtable;
import java.util.Locale;
import java.util.Objects;
import java.util.Scanner;

public class Apteka {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        scanner.nextLine();
        Hashtable<CureName, Cure> hashtable = new Hashtable();

        for (int i = 0; i < N; i++) {
            String line = scanner.nextLine();
            String [] parts = line.split("\\s+");

            String name = parts[0];
            int positive = Integer.parseInt(parts[1]);
            int price = Integer.parseInt(parts[2]);
            int numOfCures = Integer.parseInt(parts[3]);

            CureName cureName = new CureName(name);
            Cure cure = new Cure(name, positive, price, numOfCures);

            hashtable.put(cureName, cure);
        }

        while (true){
            String line = scanner.nextLine();
            if(line.equals("KRAJ")){
                break;
            }

            CureName cureName = new CureName(line);
            int numOfCuresToBuy = Integer.parseInt(scanner.nextLine());

            if(hashtable.containsKey(cureName)){
                Cure cure = hashtable.get(cureName);
                System.out.println(cure);
                cure.order(numOfCuresToBuy);
            } else System.out.println("Nema takov lek");

        }

    }
}

class CureName{
    public String shortName;

    public CureName(String shortName) {
        this.shortName = shortName.toLowerCase();
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
        //return (29*(29*(29*0+shortName.charAt(0)) + shortName.charAt(1)) % 102780 + shortName.charAt(2));
    }

    @Override
    public String toString() {
        return shortName;
    }
}

class Cure{
    public String name;
    public int positivity;
    public int price;
    public int numOfCures;

    public Cure(String name, int positivity, int price, int numOfCures) {
        this.name = name.toUpperCase();
        this.positivity = positivity;
        this.price = price;
        this.numOfCures = numOfCures;
    }

    public String getPos(){
        if(positivity == 1){
            return "NEG";
        } else return "POZ";
    }

    public String order(int n){
        if(n > numOfCures){
            return "Nema dovolno lekovi";
        } else{
            numOfCures -= n;
            return "Napravena naracka";
        }
    }

    @Override
    public String toString() {
        return name + '\n' + getPos() + '\n' + price + '\n' + numOfCures;
    }
}