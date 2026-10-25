package com.navi.backend.c3d.riscv;

import com.navi.backend.c3d.Quad;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/** Función C3D junto a los datos de frame que necesita el backend RV32. */
final class RiscVFunction {
    final String name;
    final int frameCells;
    final List<Quad> body;
    final Map<String, Integer> tempSlots = new LinkedHashMap<>();
    int paramCount;
    int tempCount;
    int frameBytes;

    RiscVFunction(String name, int frameCells, List<Quad> body) {
        this.name = name;
        this.frameCells = frameCells;
        this.body = body;
    }
}
