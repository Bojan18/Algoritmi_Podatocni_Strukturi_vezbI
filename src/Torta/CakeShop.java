package Torta;

import java.util.Scanner;

class SLLNode {
    String name;
    int price;
    SLLNode succ;

    public SLLNode(String name, int price, SLLNode succ) {
        this.name = name;
        this.price = price;
        this.succ = succ;
    }

    @Override
    public String toString() {
        return name;
    }
}

class SLL {
    SLLNode first;

    public SLL() {
        this.first = null;
    }

    public void insertFirst(String name, int price) {
        SLLNode ins = new SLLNode(name, price, first);
        first = ins;
    }

    public void insertLast(String name, int price) {
        if (first != null) {
            SLLNode tmp = first;
            while (tmp.succ != null)
                tmp = tmp.succ;
            SLLNode ins = new SLLNode(name, price, null);
            tmp.succ = ins;
        } else {
            insertFirst(name, price);
        }
    }

    @Override
    public String toString() {
        StringBuilder ret = new StringBuilder();
        if (first != null) {
            SLLNode tmp = first;
            ret.append(tmp).append("\n");
            while (tmp.succ != null) {
                tmp = tmp.succ;
                ret.append(tmp).append("\n");
            }
        } else
            ret = new StringBuilder("NO ELEMENTS");
        return ret.toString();
    }
}

public class CakeShop {

    public static void removeCakes(SLL cakes) {
        //da se izbrise tortata od lista ako nejzinata cena e pogolema od prosecnata cena na site torti
        SLLNode cake = cakes.first;
        SLLNode followFirst = cakes.first;

        int sumOfCakes = 0;
        int numOfCakes = 0;
        double avgPrice = 0;

        while (cake != null){
            sumOfCakes += cake.price;
            numOfCakes++;
            cake = cake.succ;
        }

        avgPrice = (double) sumOfCakes / numOfCakes;
        boolean firstCake = true;
        boolean tmp = true;
        cake = cakes.first;

        while (cake != null){
            if(cake.price > avgPrice){
                if(firstCake && tmp){
                    cakes.first = cake.succ;
                }
                firstCake = false;
                followFirst.succ = cake.succ;
                cake = followFirst;
            }
            if(!tmp) {
                followFirst = cake;
                cake = cake.succ;
            }
            tmp = false;
        }

    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());
        SLL cakes = new SLL();

        for (int i = 0; i < n; i++) {
            String line = scanner.nextLine();
            String[] parts = line.split("\\s+");
            cakes.insertLast(parts[0], Integer.parseInt(parts[1]));
        }

        removeCakes(cakes);
        System.out.println(cakes.toString());
    }
}
