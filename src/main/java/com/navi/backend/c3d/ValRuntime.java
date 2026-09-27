package com.navi.backend.c3d;

import java.util.List;

// runtime C para el modo Val: la memoria guarda valores etiquetados (Val)
// se emiten solo las ayudas que las cuartetas realmente usan
final class ValRuntime {

    private ValRuntime() {
    }

    // arma el preludio segun las operaciones que aparezcan en las cuartetas
    static String build(List<Quad> quads) {
        boolean useAdd = false, useOp = false, useCmp = false, usePrint = false, useRead = false;

        for (Quad q : quads) {
            switch (q.getOp()) {
                case "+" -> useAdd = true;
                case "-", "*", "/", "%", "neg" -> useOp = true;
                case "if" -> useCmp = true;
                case "print" -> usePrint = true;
                case "read" -> useRead = true;
                default -> { }
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append("/* Generado desde las cuartetas C3D. Compilar: gcc -o programa programa.c */\n");
        sb.append("#include <stdio.h>\n");
        if (useAdd || useCmp || useRead) sb.append("#include <string.h>\n#include <stdlib.h>\n");
        sb.append('\n').append(VAL_TYPE);
        if (useAdd || useCmp) sb.append(AS_STR);
        if (useAdd) sb.append(ADD);
        if (useOp) sb.append(OP);
        if (useCmp) sb.append(CMP);
        if (usePrint) sb.append(PRINT);
        if (useRead) sb.append(READ);
        return sb.toString();
    }

    private static final String VAL_TYPE = """
            /* Cada celda de la memoria es un Val: el valor + su tipo (tag).
               tag: 0 = entero, 1 = decimal, 2 = caracter, 3 = cadena, 4 = null. */
            typedef struct { int t; union { long long i; double d; char c; char *s; } u; } Val;
            static inline Val mk_int(long long x)   { Val r; r.t = 0; r.u.i = x; return r; }  /* crea Val entero   */
            static inline Val mk_dbl(double x)      { Val r; r.t = 1; r.u.d = x; return r; }  /* crea Val decimal  */
            static inline Val mk_chr(char x)        { Val r; r.t = 2; r.u.c = x; return r; }  /* crea Val caracter */
            static inline Val mk_str(const char *x) { Val r; r.t = 3; r.u.s = (char *)x; return r; } /* crea Val cadena */
            static inline Val mk_null(void)         { Val r; r.t = 4; r.u.i = 0; return r; }  /* crea Val null     */
            static inline long long as_index(Val v) { return v.u.i; }                         /* lee el entero (se usa como direccion) */
            static inline double as_double(Val v)   { return v.t == 1 ? v.u.d : (double)v.u.i; } /* lee el valor como decimal */

            static Val stack[262144];                               /* memoria de pila   */
            static Val heap[262144];                                /* memoria de monton */
            static long long BP = 0, GP = 0, HP = 0, SP = 0;        /* punteros          */
            static Val RET;                                         /* valor de retorno  */

            """;

    private static final String AS_STR = """
            /* Pasa un Val a texto (para concatenar, comparar o imprimir).
               snprintf escribe el numero formateado dentro del buffer b. */
            static const char *as_str(Val v) {
                static char b[64];                                  /* buffer para numeros */
                switch (v.t) {
                    case 3: return v.u.s;                           /* ya es cadena */
                    case 2: snprintf(b, sizeof b, "%c", v.u.c); return b;    /* caracter */
                    case 1: snprintf(b, sizeof b, "%g", v.u.d); return b;    /* decimal  */
                    case 4: return "null";
                    default: snprintf(b, sizeof b, "%lld", v.u.i); return b; /* entero */
                }
            }

            """;

    private static final String ADD = """
            /* Suma: si alguno es cadena, concatena; si no, suma numerica. */
            static Val add(Val a, Val b) {
                if (a.t == 3 || b.t == 3) {
                    const char *x = as_str(a);
                    const char *y = as_str(b);
                    size_t nx = strlen(x), ny = strlen(y);      /* strlen = largo del texto */
                    char *r = (char *) malloc(nx + ny + 1);     /* malloc = aparta memoria (+1 por el '\\0') */
                    memcpy(r, x, nx);                           /* copia x */
                    memcpy(r + nx, y, ny + 1);                  /* copia y con su '\\0' */
                    return mk_str(r);
                }
                if (a.t == 1 || b.t == 1) return mk_dbl(as_double(a) + as_double(b));
                return mk_int(a.u.i + b.u.i);
            }

            """;

    private static final String OP = """
            /* Aritmetica: '-' resta, '*' producto, '/' division, '%' modulo. */
            static Val op(char k, Val a, Val b) {
                if (a.t == 1 || b.t == 1) {
                    double x = as_double(a), y = as_double(b);
                    switch (k) {
                        case '-': return mk_dbl(x - y);
                        case '*': return mk_dbl(x * y);
                        case '/': return mk_dbl(x / y);
                        default:  return mk_dbl((double)((long long)x % (long long)y));
                    }
                }
                switch (k) {
                    case '-': return mk_int(a.u.i - b.u.i);
                    case '*': return mk_int(a.u.i * b.u.i);
                    case '/': return mk_int(a.u.i / b.u.i);
                    default:  return mk_int(a.u.i % b.u.i);
                }
            }

            """;

    private static final String CMP = """
            /* Compara: -1 si a<b, 0 si son iguales, 1 si a>b. strcmp compara textos. */
            static int cmp(Val a, Val b) {
                if (a.t == 3 || b.t == 3) {
                    int r = strcmp(as_str(a), as_str(b));
                    return r < 0 ? -1 : (r > 0 ? 1 : 0);
                }
                if (a.t == 4 || b.t == 4) return (a.t == 4 && b.t == 4) ? 0 : (a.t == 4 ? -1 : 1);
                double x = as_double(a), y = as_double(b);
                return x < y ? -1 : (x > y ? 1 : 0);
            }

            """;

    private static final String PRINT = """
            /* Imprime un Val segun su tipo.
               fputs = escribe texto, putchar = escribe un caracter, printf = con formato. */
            static void prn(Val v) {
                switch (v.t) {
                    case 3: fputs(v.u.s, stdout); break;
                    case 2: putchar(v.u.c); break;
                    case 1: printf("%g", v.u.d); break;
                    case 4: fputs("null", stdout); break;
                    default: printf("%lld", v.u.i); break;
                }
            }

            """;

    private static final String READ = """
            /* Lee una linea y decide si es entero, decimal o cadena. */
            static Val rd(void) {
                static char b[4096];
                fflush(stdout);                                 /* vuelca la salida pendiente */
                if (!fgets(b, sizeof b, stdin)) b[0] = '\\0';    /* fgets = lee una linea */
                size_t n = strlen(b);                           /* largo de lo leido */
                while (n > 0 && (b[n-1] == '\\n' || b[n-1] == '\\r')) b[--n] = '\\0'; /* quita el salto de linea */
                char *e;
                long long i = strtoll(b, &e, 10);               /* strtoll = intenta entero */
                if (e != b && *e == '\\0') return mk_int(i);
                double d = strtod(b, &e);                       /* strtod = intenta decimal */
                if (e != b && *e == '\\0') return mk_dbl(d);
                char *copy = (char *) malloc(n + 1);            /* malloc = aparta memoria (+1 por el '\\0') */
                memcpy(copy, b, n + 1);                         /* copia: b se reusa en cada lectura */
                return mk_str(copy);                                 /* si no, es cadena */
            }

            """;
}
