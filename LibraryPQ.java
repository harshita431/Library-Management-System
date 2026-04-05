import java.util.*;

// book class
class Book {
    int id;
    String name;
    String author;
    boolean available;

    Book(int id, String name, String author) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.available = true;
    }
}

// request class
class Request {
    int bookId;
    int priority;
    int order;

    Request(int bookId, int priority, int order) {
        this.bookId = bookId;
        this.priority = priority;
        this.order = order;
    }
}

public class LibraryPQ {
    static HashMap<Integer, Book> library = new HashMap<>();
    static int counter = 0;

    static PriorityQueue<Request> pq = new PriorityQueue<>(new Comparator<Request>() {
        public int compare(Request a, Request b) {
            if (a.priority != b.priority) {
                return a.priority - b.priority;
            } else {
                return a.order - b.order;
            }
        }
    });

    static Scanner sc = new Scanner(System.in);

    // default books
    static void addDefaultBooks() {
        library.put(1, new Book(1, "Java", "James Gosling"));
        library.put(2, new Book(2, "Java", "Herbert Schildt"));
        library.put(3, new Book(3, "Data Structures", "Mark Allen"));
        library.put(4, new Book(4, "Algorithms", "Mark Allen"));
        library.put(5, new Book(5, "Operating System", "Galvin"));
        library.put(6, new Book(6, "Computer Networks", "Tanenbaum"));
        library.put(7, new Book(7, "DBMS", "Korth"));
        library.put(8, new Book(8, "Software Engineering", "Pressman"));
        library.put(9, new Book(9, "Python", "Guido van Rossum"));
        library.put(10, new Book(10, "C Programming", "Dennis Ritchie"));
        library.put(11, new Book(11, "Machine Learning", "Andrew Ng"));
        library.put(12, new Book(12, "Artificial Intelligence", "Stuart Russell"));
        library.put(13, new Book(13, "Java", "XYZ Author"));
        library.put(14, new Book(14, "DSA", "ABC Author"));
        library.put(15, new Book(15, "DSA", "Another Author"));
    }

    // add book
    static void addBook() {
        System.out.print("Enter Book ID: ");
        int id = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter Book Name: ");
        String name = sc.nextLine();

        System.out.print("Enter Author Name: ");
        String author = sc.nextLine();

        Book b = new Book(id, name, author);
        library.put(id, b);
        System.out.println("Book Added Successfully");
    }

    // request book
    static void requestBook() {
        System.out.print("Enter Book ID: ");
        int id = sc.nextInt();

        if (!library.containsKey(id)) {
            System.out.println("Book not found");
            return;
        }

        System.out.print("Enter Priority (1=High, 2=Medium, 3=Low): ");
        int p = sc.nextInt();

        Request r = new Request(id, p, counter++);
        pq.add(r);

        System.out.println("Request Added");
    }

    // issue book
    static void issueBook() {
        if (pq.isEmpty()) {
            System.out.println("No requests available");
            return;
        }

        Request r = pq.poll();
        Book b = library.get(r.bookId);

        if (!b.available) {
            System.out.println("Book already issued");
            return;
        }

        b.available = false;
        System.out.println("Book Issued -> " + b.name + " by " + b.author);
    }

    // display books
    static void displayBooks() {
        for (Integer key : library.keySet()) {
            Book b = library.get(key);

            if (b.available) {
                System.out.println(b.id + " | " + b.name + " | " + b.author + " | Available");
            } else {
                System.out.println(b.id + " | " + b.name + " | " + b.author + " | Issued");
            }
        }
    }

    // main
    public static void main(String[] args) {
        addDefaultBooks();

        while (true) {
            System.out.println("\n1. Add Book");
            System.out.println("2. Request Book");
            System.out.println("3. Issue Book");
            System.out.println("4. Display Books");
            System.out.println("5. Exit");

            int ch = sc.nextInt();

            if (ch == 1) {
                addBook();
            } else if (ch == 2) {
                requestBook();
            } else if (ch == 3) {
                issueBook();
            } else if (ch == 4) {
                displayBooks();
            } else {
                System.out.println("Exiting...");
                break;
            }
        }
    }
}