%estructuras
estructura Lote:
    entero cantidad
    entero items[5]
    flotante precios[5]

%funciones

definir agregar({} Lote l, entero item, flotante precio):
    si(l.cantidad < 5) entonces
        l.items[l.cantidad] = item
        l.precios[l.cantidad] = precio
        l.cantidad++
    contrario
        imprimir("Lote lleno")

definir totalPrecios({} Lote l) -> flotante:
    flotante s = 0.0
    para(entero i = 0; i < l.cantidad; i++):
        s = s + l.precios[i]
    retornar s

definir mostrar({} Lote l):
    imprimir("Lote con " + l.cantidad + " items")
    para(entero i = 0; i < l.cantidad; i++):
        imprimir("  [" + i + "] item=" + l.items[i] + " precio=" + l.precios[i])

definir duplicarItems({} Lote l):
    para(entero i = 0; i < l.cantidad; i++):
        l.items[i] = l.items[i] * 2

definir invertirItems({} Lote l):
    entero i = 0
    entero j = l.cantidad - 1
    entero t = 0
    mientras(i < j) hacer
        t = l.items[i]
        l.items[i] = l.items[j]
        l.items[j] = t
        i++
        j = j - 1

definir sumaItemsDirecto({} Lote l) -> entero:
    entero s = 0
    para(entero i = 0; i < l.cantidad; i++):
        s = s + l.items[i]
    retornar s

// DUDA: pasar un arreglo que es CAMPO de un struct como argumento [] entero
definir sumaItems({} Lote l) -> entero:
    retornar sumarEnteros(l.items, l.cantidad)

definir llenarSecuencia([] entero a, entero n, entero inicio):
    para(entero i = 0; i < n; i++):
        a[i] = inicio + i

definir sumarEnteros([] entero a, entero n) -> entero:
    entero s = 0
    para(entero i = 0; i < n; i++):
        s = s + a[i]
    retornar s
