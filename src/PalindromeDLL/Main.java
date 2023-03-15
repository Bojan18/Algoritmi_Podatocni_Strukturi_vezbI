package PalindromeDLL;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class DLLNode<E> {
    E element;
    DLLNode<E> pred, succ;

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
    DLLNode<E> first, last;

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


    @Override
    public String toString() {
        String ret = new String();
        if (first != null) {
            DLLNode<E> tmp = first;
            ret += tmp + " ";
            while (tmp.succ != null) {
                tmp = tmp.succ;
                ret += tmp + " ";
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

}

public class Main {

    public static int Palindrom(DLL<Integer> list){
        DLLNode<Integer> first = list.getFirst();
        DLLNode<Integer> last = list.getLast();
        boolean isPalindrome = true;

        while (first != null && last != null){
            if (!first.element.equals(last.element)) {
                isPalindrome = false;
                break;
            }
            first = first.succ;
            last = last.pred;
        }

        if(isPalindrome)
            return 1;
        else return -1;
    }

    public static void main(String[] args) throws IOException{
        // write your code here
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        DLL<Integer> list = new DLL<>();
        int N = Integer.parseInt(br.readLine());
        System.out.println(N);

        String line = br.readLine();
        String [] parts = line.split("\\s+");

        for (int i = 0; i < N; i++) {
            list.insertLast(Integer.valueOf(parts[i]));
        }

        System.out.println("Original list : " + list);
        System.out.println("Is palindrome? -> " + Palindrom(list));
    }
}