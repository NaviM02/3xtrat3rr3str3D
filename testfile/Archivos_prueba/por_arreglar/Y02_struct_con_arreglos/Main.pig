##
    Y02: structs que contienen arreglos + arreglos por referencia
    - dos structs independientes (cada uno con su propio arreglo)
    - lectura/escritura de elementos desde .y y desde .pig
##
import lib.Inventario.y

VARIABILES>
series its[5] : numerus {0, 0, 0, 0, 0};
series prs[5] : decimalis {0.0, 0.0, 0.0, 0.0, 0.0};
series its2[5] : numerus {0, 0, 0, 0, 0};
series prs2[5] : decimalis {0.0, 0.0, 0.0, 0.0, 0.0};
series seq[5] : numerus {0, 0, 0, 0, 0};
esto lote : Lote {0, its, prs};
esto otro : Lote {0, its2, prs2};

MAIOR>
agregar(lote, 10, 1.5);
agregar(lote, 20, 2.25);
agregar(lote, 30, 0.25);
mostrar(lote);
>> "Total: " >> totalPrecios(lote) ;
duplicarItems(lote);
invertirItems(lote);
>> lote.items[0] >> "," >> lote.items[1] >> "," >> lote.items[2] ;
agregar(lote, 40, 1.0);
agregar(lote, 50, 1.0);
agregar(lote, 60, 1.0);
>> "cantidad: " >> lote.cantidad ;
>> "Total: " >> totalPrecios(lote) ;

agregar(otro, 7, 0.5);
>> otro.cantidad >> " " >> lote.cantidad ;
>> otro.items[0] >> " vs " >> lote.items[0] ;

// DUDA: asignar a un elemento de un campo-arreglo desde .pig
lote.items[4] = 99;
>> "items[4]=" >> lote.items[4] ;
>> "sumaItemsDirecto=" >> sumaItemsDirecto(lote) ;
>> "sumaItems(arreglo-campo)=" >> sumaItems(lote) ;

llenarSecuencia(seq, 5, 100);
>> "seq[4]=" >> seq[4] ;
>> "suma seq=" >> sumarEnteros(seq, 5) ;
FINIS;
