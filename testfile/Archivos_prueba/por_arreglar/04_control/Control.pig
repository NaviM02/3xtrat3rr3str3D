import control.Control.z

VARIABILES>
esto c : novus Control() : Control ;

MAIOR>
>> "Suma: " >> c.operar(1, 7, 3) ;
>> "Resta: " >> c.operar(2, 7, 3) ;
>> "Mult: " >> c.operar(3, 7, 3) ;
>> "Op invalida: " >> c.operar(9, 7, 3) ;
>> "Pares hasta 6: " >> c.contarPares(6) ;
>> "Pares hasta 10: " >> c.contarPares(10) ;
>> "Clasificacion 5: " >> c.clasificar(5) ;
>> "Clasificacion -2: " >> c.clasificar(-2) ;
>> "Clasificacion 0: " >> c.clasificar(0) ;
>> "Primero con cuadrado >= 50: " >> c.buscarPrimero(50) ;
si (c.esValido(150) == verum) {
    >> "150 valido" ;
} aliter {
    >> "150 no valido" ;
} finis;
>> "Prefijo/postfijo: " >> c.prefijoPostfijo() ;
FINIS;
