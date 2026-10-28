// ============================================================
// TEST OPCIONAL: structs dentro de structs.
// Si tu lenguaje NO lo soporta, borra esta carpeta.
// DUDAS: campo de tipo struct ("Punto a"), inicializador anidado {{..},{..},..},
//        y pasar un struct interno (sg.a) como argumento.
// ============================================================
%estructuras
estructura Punto:
    entero x
    entero y

estructura Segmento:
    Punto a
    Punto b
    entero id

%funciones

definir mover({} Segmento s, entero dx):
    s.a.x = s.a.x + dx
    s.b.x = s.b.x + dx

definir moverPunto({} Punto p, entero dx):
    p.x = p.x + dx

// asume b "mayor o igual" que a en ambas coordenadas
definir manhattan({} Segmento s) -> entero:
    retornar (s.b.x - s.a.x) + (s.b.y - s.a.y)

definir imprimirSeg({} Segmento s):
    imprimir("Segmento " + s.id + ": (" + s.a.x + "," + s.a.y + ")-(" + s.b.x + "," + s.b.y + ")")
