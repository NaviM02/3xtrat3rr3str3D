package com.navi.backend.c3d.riscv;

/** Convención y cálculos de offsets para los frames RV32. */
final class RiscVFrameLayout {

    static final int WORD_BYTES = 4;
    static final int HEADER_BYTES = 8; // ra + s0 anterior
    private static final int FIRST_CELL_DISTANCE = 12;

    private RiscVFrameLayout() {
    }

    static int dataOffset(int cell) {
        return -(FIRST_CELL_DISTANCE + cell * WORD_BYTES);
    }

    static int localOffset(int frameCells, int cell) {
        // Locales y parámetros crecen hacia direcciones mayores, sin invadir ra/s0.
        return dataOffset(frameCells - 1 - cell);
    }

    static int frameBytes(int frameCells, int tempCount) {
        return align16(HEADER_BYTES + (frameCells + tempCount) * WORD_BYTES);
    }

    private static int align16(int bytes) {
        return (bytes + 15) & ~15;
    }
}
