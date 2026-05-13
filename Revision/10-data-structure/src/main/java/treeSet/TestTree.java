package treeSet;

import java.util.Set;
import java.util.TreeSet;

public class TestTree {
    static void main() {
        go();
    }

    public static void go(){
        Book b1 = new Book("How Cats Work");
        Book b2 = new Book("Remix your Body");
        Book b3 = new Book("Finding Emo");

        Set<Book> books = new TreeSet<>((a, b) -> a.getTitle().compareTo(b.getTitle()));
        books.add(b1);
        books.add(b2);
        books.add(b3);
        System.out.println(books);
    }
}
