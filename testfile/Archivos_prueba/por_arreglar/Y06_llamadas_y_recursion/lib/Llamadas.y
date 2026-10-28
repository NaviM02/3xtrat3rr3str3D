%funciones

definir suma3(entero a, entero b, entero c) -> entero:
    retornar a + b + c

definir cuadrado(entero n) -> entero:
    retornar n * n

// 10 parametros: fuerza a pasar argumentos por stack (RISC-V solo tiene a0-a7)
definir diez(entero a, entero b, entero c, entero d, entero e, entero f, entero g, entero h, entero i, entero j) -> entero:
    retornar a + b * 2 + c * 3 + d * 4 + e * 5 + f * 6 + g * 7 + h * 8 + i * 9 + j * 10

definir mezcla(entero a, flotante b, entero c, flotante d, entero e, flotante f, entero g, flotante h, entero i, flotante j) -> flotante:
    retornar a + b + c + d + e + f + g + h + i + j

// efecto lateral visible: sirve para verificar orden de evaluacion
definir por10(entero n) -> entero:
    imprimir("por10(" + n + ")")
    retornar n * 10

// los primitivos van POR VALOR
definir incrementar(entero n) -> entero:
    n = n + 1
    retornar n

definir factorial(entero n) -> entero:
    si(n < 2) entonces
        retornar 1
    retornar n * factorial(n - 1)

definir fibonacci(entero n) -> entero:
    si(n < 2) entonces
        retornar n
    retornar fibonacci(n - 1) + fibonacci(n - 2)

definir ackermann(entero m, entero n) -> entero:
    si(m == 0) entonces
        retornar n + 1
    si(n == 0) entonces
        retornar ackermann(m - 1, 1)
    retornar ackermann(m - 1, ackermann(m, n - 1))

// recursion profunda (1000 frames)
definir sumaHasta(entero n) -> entero:
    si(n == 0) entonces
        retornar 0
    retornar n + sumaHasta(n - 1)

// recursion mutua: esImparRec se usa antes de estar definida (DUDA: referencia hacia adelante)
definir esParRec(entero n) -> bool:
    si(n == 0) entonces
        retornar verdadero
    retornar esImparRec(n - 1)

definir esImparRec(entero n) -> bool:
    si(n == 0) entonces
        retornar falso
    retornar esParRec(n - 1)

// enteros pasados a parametros flotantes (conversion implicita)
definir promedio(entero a, entero b) -> flotante:
    retornar (a + b) / 2.0

definir mitad(flotante x) -> flotante:
    retornar x / 2.0
