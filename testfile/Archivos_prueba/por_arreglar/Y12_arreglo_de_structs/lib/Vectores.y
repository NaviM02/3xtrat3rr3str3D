%estructuras
estructura Punto:
    entero x
    entero y

%funciones
definir probar() -> entero:
    Punto arr[3]
    arr[1].x = 42
    arr[1].y = 7
    arr[2].x = 99
    retornar arr[1].x * 1000 + arr[1].y * 10 + arr[0].x + arr[2].x
