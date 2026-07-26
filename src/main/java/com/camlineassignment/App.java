package com.camlineassignment;

import java.util.Scanner;

public class App 
{
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        LibraryRepository repo = new LibraryRepository();
        LibraryService library = new LibraryService(repo);

        boolean running = true;

        while (running) {
            System.out.println("\n=== Library Menu ===");
            System.out.println("1. List Books");
            System.out.println("2. List Members");
            System.out.println("3. Check Out Book");
            System.out.println("4. Return Book");
            System.out.println("5. View Member's Books");
            System.out.println("6. View Available Copies");
            System.out.println("0. Exit");

            System.out.print("Choice: ");
            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    for (Book book : library.getBooks()) {
                        System.out.println(book);
                    }
                    break;

                case 2:
                    for (Member member : library.getMembers()) {
                        System.out.println(member);
                    }
                    break;

                case 3:
                    System.out.print("Member ID: ");
                    String memberId = scanner.nextLine();

                    System.out.print("ISBN: ");
                    String isbn = scanner.nextLine();

                    System.out.println(library.checkout(memberId, isbn));
                    break;

                case 4:
                    System.out.print("Member ID: ");
                    memberId = scanner.nextLine();

                    System.out.print("ISBN: ");
                    isbn = scanner.nextLine();

                    System.out.println(library.returnBook(memberId, isbn));
                    break;

                case 5:
                    System.out.print("Member ID: ");
                    memberId = scanner.nextLine();

                    for (String bookId : library.checkedOutByMember(memberId)) {
                        System.out.println(bookId);
                    }
                    break;

                case 6:
                    System.out.print("ISBN: ");
                    isbn = scanner.nextLine();

                    System.out.println(library.copiesOfBookAvailable(isbn));
                    break;

                case 0:
                    running = false;
                    break;

                default:
                    System.out.println("Invalid option.");
            }
        }

        scanner.close();
    }
}
