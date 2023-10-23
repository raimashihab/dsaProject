import java.util.Scanner;

public class Polynomial {
    private Term head;
    private int degree;
    private int terms;

    public class Term {
        int coefficient;
        int exponent;
        Term next;

        Term(int coefficient, int exponent) {
            this.coefficient = coefficient;
            this.exponent = exponent;
            this.next = null;
        }
    }

    public Polynomial() {
        head = null;
        degree = 0;
        terms = 0;
    }

    public void addTerm(int coefficient, int exponent) {
        if (coefficient == 0) {
            return;
        }

        Term newTerm = new Term(coefficient, exponent);
        if (head == null) {
            head = newTerm;
        } else {
            Term current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newTerm;
        }

        if (exponent > degree) {
            degree = exponent;
        }

        terms++;
    }

    public Polynomial add(Polynomial other) {
        Polynomial result = new Polynomial();
        for (int i = 0; i <= degree || i <= other.degree; i++) {
            int coef1 = getCoefficient(i);
            int coef2 = other.getCoefficient(i);
            result.addTerm(coef1 + coef2, i);
        }
        return result;
    }

    public Polynomial subtract(Polynomial other) {
        Polynomial result = new Polynomial();
        for (int i = 0; i <= degree || i <= other.degree; i++) {
            int coef1 = getCoefficient(i);
            int coef2 = other.getCoefficient(i);
            result.addTerm(coef1 - coef2, i);
        }
        return result;
    }

    public Polynomial multiply(Polynomial other) {
        Polynomial result = new Polynomial();
        Term current1 = head;
        while (current1 != null) {
            Term current2 = other.head;
            while (current2 != null) {
                int coef = current1.coefficient * current2.coefficient;
                int exp = current1.exponent + current2.exponent;
                result.addTerm(coef, exp);
                current2 = current2.next;
            }
            current1 = current1.next;
        }
        return result;
    }

    public int getCoefficient(int exponent) {
        Term current = head;
        while (current != null) {
            if (current.exponent == exponent) {
                return current.coefficient;
            }
            current = current.next;
        }
        return 0;
    }

    public void display() {
        Term current = head;
        while (current != null) {
            System.out.print(current.coefficient + "x^" + current.exponent);
            current = current.next;
            if (current != null) {
                System.out.print(" + ");
            }
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter Polynomial 1 (Format: coefficient1 exponent1, coefficient2 exponent2, ...):");
        String input1 = scanner.nextLine();
        Polynomial p1 = createPolynomialFromInput(input1);

        System.out.println("Enter Polynomial 2 (Format: coefficient1 exponent1, coefficient2 exponent2, ...):");
        String input2 = scanner.nextLine();
        Polynomial p2 = createPolynomialFromInput(input2);

        Polynomial sum = p1.add(p2);
        Polynomial difference = p1.subtract(p2);
        Polynomial product = p1.multiply(p2);

        System.out.println("Polynomial p1: ");
        p1.display();

        System.out.println("Polynomial p2: ");
        p2.display();

        System.out.println("Sum of p1 and p2: ");
        sum.display();

        System.out.println("Difference of p1 and p2: ");
        difference.display();

        System.out.println("Product of p1 and p2: ");
        product.display();
    }

    public static Polynomial createPolynomialFromInput(String input) {
        Polynomial polynomial = new Polynomial();
        String[] terms = input.split(", ");
        for (String term : terms) {
            String[] parts = term.split(" ");
            if (parts.length == 2) {
                int coefficient = Integer.parseInt(parts[0]);
                int exponent = Integer.parseInt(parts[1]);
                polynomial.addTerm(coefficient, exponent);
            }
        }
        return polynomial;
    }
}
