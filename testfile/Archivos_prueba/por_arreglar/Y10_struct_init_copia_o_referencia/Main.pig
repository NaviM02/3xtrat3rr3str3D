##
    Y10: DUDA DE DISENO. Un struct inicializado con una variable 'series'
    {3, base} ... ¿copia el arreglo o guarda la referencia?
    El esperado asume COPIA (el arreglo vive dentro del struct).
    Si tu diseno es por REFERENCIA, cambia las lineas marcadas en esperado.txt.
##
import lib.Buffers.y

VARIABILES>
series base[3] : numerus {1, 2, 3};
esto b1 : Buf {3, base};
esto b2 : Buf {3, base};

MAIOR>
poner(b1, 0, 99);
>> "b1.datos[0]=" >> b1.datos[0] ;
>> "base[0]=" >> base[0] ;
>> "b2.datos[0]=" >> b2.datos[0] ;
FINIS;
