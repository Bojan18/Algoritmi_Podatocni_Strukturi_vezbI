package DvocifreniBroeviLista;

import java.io.IOException;
import java.util.Scanner;

class DLLNode<E> {
    protected E element;
    protected DLLNode<E> pred, succ;

    public DLLNode(E elem, DLLNode<E> pred, DLLNode<E> succ) {
        this.element = elem;
        this.pred = pred;
        this.succ = succ;
    }

    @Override
    public String toString() {
        return element.toString();
    }
}

class DLL<E> {
    private DLLNode<E> first, last;

    public DLL() {
        // Construct an empty SLL
        this.first = null;
        this.last = null;
    }

    public void deleteList() {
        first = null;
        last = null;
    }

    public int length() {
        int ret;
        if (first != null) {
            DLLNode<E> tmp = first;
            ret = 1;
            while (tmp.succ != null) {
                tmp = tmp.succ;
                ret++;
            }
            return ret;
        } else
            return 0;

    }

    public DLLNode<E> find(E o) {
        if (first != null) {
            DLLNode<E> tmp = first;
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

    public void insertFirst(E o) {
        DLLNode<E> ins = new DLLNode<E>(o, null, first);
        if (first == null)
            last = ins;
        else
            first.pred = ins;
        first = ins;
    }

    public void insertLast(E o) {
        if (first == null)
            insertFirst(o);
        else {
            DLLNode<E> ins = new DLLNode<E>(o, last, null);
            last.succ = ins;
            last = ins;
        }
    }

    public void insertAfter(E o, DLLNode<E> after) {
        if(after==last){
            insertLast(o);
            return;
        }
        DLLNode<E> ins = new DLLNode<E>(o, after, after.succ);
        after.succ.pred = ins;
        after.succ = ins;
    }

    public void insertBefore(E o, DLLNode<E> before) {
        if(before == first){
            insertFirst(o);
            return;
        }
        DLLNode<E> ins = new DLLNode<E>(o, before.pred, before);
        before.pred.succ = ins;
        before.pred = ins;
    }

    public E deleteFirst() {
        if (first != null) {
            DLLNode<E> tmp = first;
            first = first.succ;
            if (first != null) first.pred = null;
            if (first == null)
                last = null;
            return tmp.element;
        } else
            return null;
    }

    public E deleteLast() {
        if (first != null) {
            if (first.succ == null)
                return deleteFirst();
            else {
                DLLNode<E> tmp = last;
                last = last.pred;
                last.succ = null;
                return tmp.element;
            }
        }
        // else throw Exception
        return null;
    }

    public E delete(DLLNode<E> node) {
        if(node==first){
            deleteFirst();
            return node.element;
        }
        if(node==last){
            deleteLast();
            return node.element;
        }
        node.pred.succ = node.succ;
        node.succ.pred = node.pred;
        return node.element;

    }

    @Override
    public String toString() {
        String ret = new String();
        if (first != null) {
            DLLNode<E> tmp = first;
            ret += tmp + "<->";
            while (tmp.succ != null) {
                tmp = tmp.succ;
                ret += tmp + "<->";
            }
        } else
            ret = "Prazna lista!!!";
        return ret;
    }

    public String toStringR() {
        String ret = new String();
        if (last != null) {
            DLLNode<E> tmp = last;
            ret += tmp + "<->";
            while (tmp.pred != null) {
                tmp = tmp.pred;
                ret += tmp + "<->";
            }
        } else
            ret = "Prazna lista!!!";
        return ret;
    }

    public DLLNode<E> getFirst() {
        return first;
    }

    public DLLNode<E> getLast() {

        return last;
    }

    public void izvadiDupliIPrebroj(){

    }
}


public class DvocifreniBroeviLista {
    // Дадена е  двострана поврзана листа чии што јазли содржат по една цифра.
    // Во дадената листа треба да се пронајдат сите скриени двоцифрени броеви поголеми од 50
    // (двоцифрен број се формира од два последователни јазли), да се избришат од листата и на
    // нивно место да фигурира јазол со добиениот двоцифрен број. Листата се процесира еднаш од лево
    // кон десно и кога ќе се најде ваков пар од јазли веднаш се заменува со јазол што го содржи двоцифрениот број креиран од парот на јазли.
    //Пример: Од листата 4 5 4 6 6 7 се добива листата 4 54 66 7
    public static DLL funkcija(DLL list){
        DLLNode<Integer> first = list.getFirst();
        DLLNode<Integer> afterFirst = first;
        DLLNode<Integer> newNode = first;
        int zbir = 0;

        while (first != null){
            afterFirst = first.succ;
            if(afterFirst != null){
                zbir = (first.element * 10) + first.succ.element;
                if(zbir > 50){
                    //4 5 4 6 6 7
                    //first = 5
                    //afterFirst = 4
                    newNode = new DLLNode<Integer>(zbir, first, afterFirst.succ);
                    list.insertBefore(zbir, first);
                    list.delete(first);
                    list.delete(afterFirst);
                    first = newNode;
                }
            }

            first = first.succ;
        }

        return list;
    }
    public static void main(String[] args) throws IOException {
        Scanner in= new Scanner(System.in);
        int n= in.nextInt();
        DLL lista= new DLL();
        for (int i=0;i<n;i++)
        {
            lista.insertLast(in.nextInt());
        }
        System.out.println(funkcija(lista));
        // write your code here
    }
}