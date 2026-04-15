/**
 * =====================================================
 * Student Name    : JADULOS, FRITZGERALD JOSEPH S.
 * Course          : BSCSIT 1203 Programming 2
 * Assignment      : Programming Assignment 1 — 3x3 Matrix Determinant Solver
 * School          : University of Perpetual Help System DALTA, Molino Campus
 * Date            : [PUT DATE HERE]
 * GitHub Repo     : https://github.com/[your-username]/uphsd-cs-jadulos-fritzgeraldjoseph
 *
 * Description:
 *   This program computes the determinant of a 3x3 matrix
 *   using cofactor expansion along the first row. It shows
 *   step-by-step solutions including minors and cofactors.
 * =====================================================
 */

public class DeterminantSolver {

    // ── SECTION 1: Matrix Declaration ───────────────────
    static int[][] matrix = {
        {3, 2, 4},
        {1, 5, 2},
        {6, 3, 1}
    };

    // ── SECTION 2: 2x2 Determinant Helper ───────────────
    static int computeMinor(int a, int b, int c, int d) {
        return (a * d) - (b * c);
    }

    // ── SECTION 3: Matrix Printer ───────────────────────
    static void printMatrix(int[][] m) {
        System.out.println("┌               ┐");
        for (int[] row : m) {
            System.out.printf("│  %2d  %2d  %2d  │%n", row[0], row[1], row[2]);
        }
        System.out.println("└               ┘");
    }

    // ── SECTION 4: Determinant Solver ───────────────────
    static void solveDeterminant(int[][] m) {

        System.out.println("=".repeat(52));
        System.out.println("  3x3 MATRIX DETERMINANT SOLVER");
        System.out.println("  Student: JADULOS, FRITZGERALD JOSEPH S.");
        System.out.println("  Assigned Matrix:");
        System.out.println("=".repeat(52));
        printMatrix(m);
        System.out.println("=".repeat(52));

        // Step 1
        int minor11 = computeMinor(m[1][1], m[1][2], m[2][1], m[2][2]);
        System.out.printf("  Step 1 — Minor M₁₁: (%d×%d)-(%d×%d) = %d%n",
                m[1][1], m[2][2], m[1][2], m[2][1], minor11);

        // Step 2
        int minor12 = computeMinor(m[1][0], m[1][2], m[2][0], m[2][2]);
        System.out.printf("  Step 2 — Minor M₁₂: (%d×%d)-(%d×%d) = %d%n",
                m[1][0], m[2][2], m[1][2], m[2][0], minor12);

        // Step 3
        int minor13 = computeMinor(m[1][0], m[1][1], m[2][0], m[2][1]);
        System.out.printf("  Step 3 — Minor M₁₃: (%d×%d)-(%d×%d) = %d%n",
                m[1][0], m[2][1], m[1][1], m[2][0], minor13);

        int c11 =  m[0][0] * minor11;
        int c12 = -m[0][1] * minor12;
        int c13 =  m[0][2] * minor13;

        System.out.println();
        System.out.printf("  Cofactor C₁₁ = %d%n", c11);
        System.out.printf("  Cofactor C₁₂ = %d%n", c12);
        System.out.printf("  Cofactor C₁₃ = %d%n", c13);

        int det = c11 + c12 + c13;

        System.out.printf("%n  det(M) = %d + (%d) + %d%n", c11, c12, c13);
        System.out.println("=".repeat(52));
        System.out.printf("  ✓  DETERMINANT = %d%n", det);

        if (det == 0) {
            System.out.println("  ⚠ The matrix is SINGULAR — it has no inverse.");
        }
        System.out.println("=".repeat(52));
    }

    // ── MAIN ────────────────────────────────────────────
    public static void main(String[] args) {
        solveDeterminant(matrix);
    }
}
