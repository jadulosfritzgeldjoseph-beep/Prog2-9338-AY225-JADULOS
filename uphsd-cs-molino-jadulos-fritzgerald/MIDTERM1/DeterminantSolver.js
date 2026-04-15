/**
 * =====================================================
 * Student Name    : JADULOS, FRITZGERALD JOSEPH S.
 * Course          : BSCSIT 1203 Programming 2
 * Assignment      : Programming Assignment 1 — 3x3 Matrix Determinant Solver
 * School          : University of Perpetual Help System DALTA, Molino Campus
 * Date            : April 15, 2026
 * GitHub Repo     : https://github.com/[your-username]/uphsd-cs-jadulos-fritzgeraldjoseph
 *
 * Description:
 * This program computes the determinant of a 3x3 matrix
 * using cofactor expansion along the first row. It shows
 * step-by-step solutions including minors and cofactors.
 * =====================================================
 */

// ── SECTION 1: Matrix Declaration ───────────────────
const matrix = [
    [3, 2, 4],
    [1, 5, 2],
    [6, 3, 1]
];

// ── SECTION 2: 2x2 Determinant Helper ───────────────
const computeMinor = (a, b, c, d) => (a * d) - (b * c);

// ── SECTION 3: Matrix Printer ───────────────────────
function printMatrix(m) {
    console.log("┌               ┐");
    m.forEach(row => {
        // Pads numbers to maintain alignment like printf %2d
        const r = row.map(num => num.toString().padStart(2, ' ')).join('  ');
        console.log(`│  ${r}  │`);
    });
    console.log("└               ┘");
}

// ── SECTION 4: Determinant Solver ───────────────────
function solveDeterminant(m) {
    const divider = "=".repeat(52);

    console.log(divider);
    console.log("  3x3 MATRIX DETERMINANT SOLVER");
    console.log("  Student: JADULOS, FRITZGERALD JOSEPH S.");
    console.log("  Assigned Matrix:");
    console.log(divider);
    printMatrix(m);
    console.log(divider);

    // Step 1
    const minor11 = computeMinor(m[1][1], m[1][2], m[2][1], m[2][2]);
    console.log(`  Step 1 — Minor M₁₁: (${m[1][1]}×${m[2][2]})-(${m[1][2]}×${m[2][1]}) = ${minor11}`);

    // Step 2
    const minor12 = computeMinor(m[1][0], m[1][2], m[2][0], m[2][2]);
    console.log(`  Step 2 — Minor M₁₂: (${m[1][0]}×${m[2][2]})-(${m[1][2]}×${m[2][0]}) = ${minor12}`);

    // Step 3
    const minor13 = computeMinor(m[1][0], m[1][1], m[2][0], m[2][1]);
    console.log(`  Step 3 — Minor M₁₃: (${m[1][0]}×${m[2][1]})-(${m[1][1]}×${m[2][0]}) = ${minor13}`);

    const c11 =  m[0][0] * minor11;
    const c12 = -m[0][1] * minor12;
    const c13 =  m[0][2] * minor13;

    console.log("");
    console.log(`  Cofactor C₁₁ = ${c11}`);
    console.log(`  Cofactor C₁₂ = ${c12}`);
    console.log(`  Cofactor C₁₃ = ${c13}`);

    const det = c11 + c12 + c13;

    console.log(`\n  det(M) = ${c11} + (${c12}) + ${c13}`);
    console.log(divider);
    console.log(`  ✓  DETERMINANT = ${det}`);

    if (det === 0) {
        console.log("  ⚠ The matrix is SINGULAR — it has no inverse.");
    }
    console.log(divider);
}

// ── EXECUTION ───────────────────────────────────────
solveDeterminant(matrix);