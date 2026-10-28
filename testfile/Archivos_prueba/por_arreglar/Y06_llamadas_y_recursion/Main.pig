##
    Y06: llamadas (muchos parametros, anidadas, orden de evaluacion),
    paso por valor de primitivos, recursion simple/doble/mutua/profunda
##
import lib.Llamadas.y

VARIABILES>
esto n : numerus 100;
esto v : numerus 5;
esto t : numerus 0;

MAIOR>
>> "diez=" >> diez(1, 2, 3, 4, 5, 6, 7, 8, 9, 10) ;
>> "mezcla=" >> mezcla(1, 0.5, 2, 1.5, 3, 2.5, 4, 3.5, 5, 4.5) ;

t = por10(1) + por10(2) * por10(3);
>> "t=" >> t ;
t = suma3(por10(4), por10(5), por10(6));
>> "t=" >> t ;

>> "anidado=" >> suma3(suma3(1, 2, 3), suma3(4, 5, 6), suma3(7, 8, 9)) ;
>> "cuadrados=" >> suma3(cuadrado(1), cuadrado(2), cuadrado(3)) ;

// el parametro 'n' de cuadrado NO debe tocar la global 'n'
>> "cuadrado(3)=" >> cuadrado(3) ;
>> "n global=" >> n ;

>> "incrementar=" >> incrementar(v) ;
>> "v tras llamada=" >> v ;

>> "factorial(10)=" >> factorial(10) ;
>> "fibonacci(15)=" >> fibonacci(15) ;
>> "ackermann(2,3)=" >> ackermann(2, 3) ;
>> "sumaHasta(1000)=" >> sumaHasta(1000) ;

si (esParRec(10)) {
    >> "10 es par" ;
} aliter {
    >> "10 es impar" ;
} finis;
si (esParRec(7)) {
    >> "7 es par" ;
} aliter {
    >> "7 es impar" ;
} finis;

>> "promedio=" >> promedio(3, 4) ;
>> "mitad=" >> mitad(5) ;
FINIS;
