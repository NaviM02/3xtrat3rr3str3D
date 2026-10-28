import lib.Segmentos.y

VARIABILES>
esto sg : Segmento {{1, 2}, {4, 6}, 7};

MAIOR>
imprimirSeg(sg);
>> "manhattan=" >> manhattan(sg) ;
mover(sg, 10);
imprimirSeg(sg);
>> "manhattan=" >> manhattan(sg) ;
moverPunto(sg.a, 100);
imprimirSeg(sg);
FINIS;
