// Abad, Eilene Joy P.
// BSIT NS 1-1 
// Sales Transact/ion Management System 

import java.util.Scanner;

public class SalesApp {
    static String[] names = {"Notebook", "Pen", "Pencil", "Eraser", "Ruler", "Sharpener", "Highlighter", "Markers", "Crayons",
                             "Glue stick", "Scissors", "Stapler", "Folder", "Binder", "Paper ream", "Compass set", "Calculator", "Backpack"};
    static double[] prices = {25, 10, 7, 5, 15, 6, 25, 40, 45, 20, 35, 90, 10, 60, 250, 80, 350, 800};
    static int[] iT = new int[500], iP = new int[500], iQ = new int[500], tQ = new int[100];
    static double[] tT = new double[100];
    static int ic = 0, tc = 0;
    static Scanner in = new Scanner(System.in);

    static int read() {
        try { return Integer.parseInt(in.nextLine().trim()); } catch (Exception e) { return -1; }
    }

    public static void main(String[] args) {
        while (true) {
            System.out.print("\n=== School Supplies Sales ===\n1. New transaction\n2. View transactions\n"
                           + "3. View transaction details\n4. Delete transaction\n5. Exit\nChoose: ");
            int c = read();
            if (c == 1) add();
            else if (c == 2) list();
            else if (c == 3) details();
            else if (c == 4) delete();
            else if (c == 5) break;
            else System.out.println("Invalid choice.");
        }
        System.out.println("Goodbye!");
    }

    static void add() {
        if (tc == 100) { System.out.println("Transaction list is full."); return; }
        System.out.println("\nProducts:");
        for (int i = 0; i < names.length; i++)
            System.out.printf("%d. %s (PHP %.2f)%n", i + 1, names[i], prices[i]);
        int items = 0;
        double total = 0;
        while (true) {
            System.out.print("\nProduct number (0 to finish): ");
            int p = read();
            if (p == 0) break;
            if (p < 1 || p > names.length) { System.out.println("Invalid product."); continue; }
            System.out.print("Quantity: ");
            int q = read();
            if (q <= 0) { System.out.println("Invalid quantity."); continue; }
            if (ic == 500) { System.out.println("Item list is full."); break; }
            iT[ic] = tc; iP[ic] = p - 1; iQ[ic++] = q;
            items += q;
            total += q * prices[p - 1];
            System.out.printf("Added. Running total: PHP %.2f%n", total);
        }
        if (items == 0) { System.out.println("No items. Transaction cancelled."); return; }
        tQ[tc] = items;
        tT[tc++] = total;
        System.out.printf("Transaction #%d recorded. Total: PHP %.2f%n", tc, total);
    }

    static void list() {
        if (tc == 0) { System.out.println("\nNo transactions yet."); return; }
        System.out.printf("%n%-4s %6s %12s%n", "#", "Items", "Total");
        double all = 0;
        for (int i = 0; i < tc; i++) {
            all += tT[i];
            System.out.printf("%-4d %6d %12.2f%n", i + 1, tQ[i], tT[i]);
        }
        System.out.printf("Total sales: PHP %.2f%n", all);
    }

    static void details() {
        if (tc == 0) { System.out.println("\nNo transactions yet."); return; }
        System.out.print("Transaction number: ");
        int n = read();
        if (n < 1 || n > tc) { System.out.println("Invalid number."); return; }
        System.out.printf("%nTransaction #%d%n%-12s %5s %10s %11s%n", n, "Product", "Qty", "Price", "Amount");
        for (int i = 0; i < ic; i++)
            if (iT[i] == n - 1)
                System.out.printf("%-12s %5d %10.2f %11.2f%n", names[iP[i]], iQ[i], prices[iP[i]], iQ[i] * prices[iP[i]]);
        System.out.printf("Total: PHP %.2f%n", tT[n - 1]);
    }

    static void delete() {
        list();
        if (tc == 0) return;
        System.out.print("Transaction number to delete: ");
        int n = read();
        if (n < 1 || n > tc) { System.out.println("Invalid number."); return; }
        int t = n - 1, j = 0;
        for (int i = 0; i < ic; i++)
            if (iT[i] != t) {
                iT[j] = iT[i] > t ? iT[i] - 1 : iT[i];
                iP[j] = iP[i];
                iQ[j++] = iQ[i];
            }
        ic = j;
        for (int i = t; i < tc - 1; i++) { tQ[i] = tQ[i + 1]; tT[i] = tT[i + 1]; }
        tc--;
        System.out.println("Transaction deleted.");
    }
 }
