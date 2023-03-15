package Tarot;

import java.util.Scanner;

class Card {

    private int id;
    private int rank;

    public Card(int id, int rank) {
        this.id = id;
        this.rank = rank;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    @Override
    public String toString() {
        return String.valueOf(id);
    }
}

class SLLNode<E> {
    protected E element;
    protected SLLNode<E> succ;

    public SLLNode(E elem, SLLNode<E> succ) {
        this.element = elem;
        this.succ = succ;
    }

    @Override
    public String toString() {
        return element.toString();
    }

    public int lenght() {
        // TODO Auto-generated method stub
        return 0;
    }
}

class SLL<E> {
    private SLLNode<E> first;

    public SLL() {
        this.first = null;
    }

    public void deleteList() {
        first = null;
    }

    public int length() {
        int ret;
        if (first != null) {
            SLLNode<E> tmp = first;
            ret = 1;
            while (tmp.succ != null) {
                tmp = tmp.succ;
                ret++;
            }
            return ret;
        } else
            return 0;

    }

    @Override
    public String toString() {
        String ret = new String();
        if (first != null) {
            SLLNode<E> tmp = first;
            ret += tmp;
            while (tmp.succ != null) {
                tmp = tmp.succ;
                ret += " " + tmp;
            }
        } else
            ret = "Prazna lista!!!";
        return ret;
    }

    public void insertFirst(E o) {
        SLLNode<E> ins = new SLLNode<E>(o, first);
        first = ins;
    }

    public void insertAfter(E o, SLLNode<E> node) {
        if (node != null) {
            SLLNode<E> ins = new SLLNode<E>(o, node.succ);
            node.succ = ins;
        } else {
            System.out.println("Dadenot jazol e null");
        }
    }

    public void insertBefore(E o, SLLNode<E> before) {
        if (first != null) {
            SLLNode<E> tmp = first;
            if (first == before) {
                this.insertFirst(o);
                return;
            }
            while (tmp.succ != before)
                tmp = tmp.succ;
            if (tmp.succ == before) {
                SLLNode<E> ins = new SLLNode<E>(o, before);
                tmp.succ = ins;
            } else {
                System.out.println("Elementot ne postoi vo listata");
            }
        } else {
            System.out.println("Listata e prazna");
        }
    }

    public void insertLast(E o) {
        if (first != null) {
            SLLNode<E> tmp = first;
            while (tmp.succ != null)
                tmp = tmp.succ;
            SLLNode<E> ins = new SLLNode<E>(o, null);
            tmp.succ = ins;
        } else {
            insertFirst(o);
        }
    }

    public E deleteFirst() {
        if (first != null) {
            SLLNode<E> tmp = first;
            first = first.succ;
            return tmp.element;
        } else {
            System.out.println("Listata e prazna");
            return null;
        }
    }

    public E delete(SLLNode<E> node) {
        if (first != null) {
            SLLNode<E> tmp = first;
            if (first == node) {
                return this.deleteFirst();
            }
            while (tmp.succ != node && tmp.succ.succ != null)
                tmp = tmp.succ;
            if (tmp.succ == node) {
                tmp.succ = tmp.succ.succ;
                return node.element;
            } else {
                System.out.println("Elementot ne postoi vo listata");
                return null;
            }
        } else {
            System.out.println("Listata e prazna");
            return null;
        }
    }

    public SLLNode<E> getFirst() {
        return first;
    }

    public SLLNode<E> find(E o) {
        if (first != null) {
            SLLNode<E> tmp = first;
            while (tmp.element != o && tmp.succ != null)
                tmp = tmp.succ;
            if (tmp.element == o) {
                return tmp;
            } else {
                System.out.println("Elementot ne postoi vo listata");
            }
        } else {
            System.out.println("Listata e prazna");
        }
        return first;
    }
}

public class Tarot {

/*Input:
33 51
18 52
40 50
6 24
4 18
88 13
45 34
98 3
87 16
32 19
28 22
82 5

    Before Output:
    33 18 40 6 4 88

    posle 1.
    18 40 6 4 88
    45 98 87 32 28 82 33
    posle 2.
    18 40  6  4 88 45
    98 87 32 28 82 33
    posle 3.
    18 40 6 4 45
    98 87 32 88 28 82 33

    Output:
    18 40 6 4 45
    98 87 32 88 28 82 33
    */

    public static void tarotCards(SLL<Card> firstPart, SLL<Card> secondPart) {
        SLLNode<Card> firstPartFirst = firstPart.getFirst();
        SLLNode<Card> secondPartSecond = secondPart.getFirst();

        //1. od prvio del ja zema prvata karta i ja stava kako posledna karta vo votriot del
        secondPart.insertLast(firstPartFirst.element);
        firstPart.deleteFirst();

        //2. od vtoriot del ja zema prvata karta i ja stava na posledno mesto vo prviot del
        firstPart.insertLast(secondPartSecond.element);
        secondPart.deleteFirst();

        //3. ja zema pretposlednata karta na prviot del i ja stava vo sredinata na vtorio
        firstPartFirst = firstPart.getFirst();
        secondPartSecond = secondPart.getFirst();

        //18 40  6  4 88 45
        while (firstPartFirst.succ.succ != null){
            firstPartFirst = firstPartFirst.succ;
        }

        //98 87 32 tuka sakame da vmetneme 28 82 33
        for (int i = 0; i < 2; i++) {
            secondPartSecond = secondPartSecond.succ;
        }

        secondPart.insertAfter(firstPartFirst.element, secondPartSecond);

        System.out.println(firstPartFirst);
    }


    public static void main(String[] args) {
        @SuppressWarnings("resource")
        Scanner scanner = new Scanner(System.in);
        SLL<Card> firstPart = new SLL<Card>();
        SLL<Card> secondPart = new SLL<Card>();

        for(int i=0; i<6; i++) {
            String line = scanner.nextLine();
            String[] parts = line.split(" ");
            firstPart.insertLast(new Card(Integer.parseInt(parts[0]), Integer.parseInt(parts[1])));
        }
        for(int i=0; i<6; i++) {
            String line = scanner.nextLine();
            String[] parts = line.split(" ");
            secondPart.insertLast(new Card(Integer.parseInt(parts[0]), Integer.parseInt(parts[1])));
        }

        tarotCards(firstPart, secondPart);
        System.out.println(firstPart.toString());
        System.out.println(secondPart.toString());
    }
}