%estructuras
estructura Buf:
    entero n
    entero datos[3]

%funciones
definir poner({} Buf b, entero i, entero v):
    b.datos[i] = v
