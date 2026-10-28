# Casos reproducibles por investigar

Copias aisladas de pruebas que compilaron y mostraron una diferencia o un error al ejecutar el ensamblador generado. Los originales permanecen en sus carpetas de origen. Cada directorio conserva su `.pig` principal, módulos importados y salida esperada para poder compilarlo de forma independiente.

| Caso | Resultado observado | Punto de investigación |
|---|---|---|
| `Y02_struct_con_arreglos` | RARS termina con acceso no alineado al leer/escribir el arreglo en el struct. | Direcciones y layout de arreglos dentro de structs Y. |
| `Y05_struct_anidado` | Los campos del struct anidado producen valores incorrectos. | Direcciones/copia de structs anidados. |
| `Y06_llamadas_y_recursion` | RARS rechaza un `sw` generado al ejecutar el caso de diez parámetros. | Convención de llamada y límite actual de argumentos (>8 no soportado). |
| `Y10_struct_init_copia_o_referencia` | `b2.datos[0]` resulta `0`; el esperado supone copia del arreglo y da `1`. | Semántica y copia de arreglos durante inicialización de structs. |
| `Y12_arreglo_de_structs` | RARS accede a la dirección cero durante la ejecución. | Inicialización/acceso de arreglos de structs. |
| `01_tictactoe` | La partida termina antes de los turnos 8 y 9; el tablero queda incompleto. | Flujo de juego/estado de objetos y despacho durante la partida. |
| `04_control` | Imprime `Prefijo/postfijo: 74`; el esperado dice `73`. | Semántica de incremento prefijo/postfijo. |

## Ejecutar

Desde el proyecto, usar cada `.pig` principal con el compilador y luego ejecutar el `.s` generado en RARS. Las carpetas son copias independientes; los archivos `compiled/` que puedan venir incluidos son artefactos de referencia y no sustituyen una compilación nueva.

## Contexto de las salidas

Los esperados de algunos casos contienen flotantes con seis decimales, aunque el generador RISC-V actual imprime valores como `3.5`. Esa diferencia de formato se observó en otros casos, pero no se incluye aquí como defecto confirmado del compilador: el README original indica que esos esperados se escribieron pensando en el backend C.

## Correcciones y aclaraciones

- **Y02:** se conserva la dirección de un campo-arreglo al pasarlo por referencia. Los inicializadores de structs ahora copian sus campos arreglo inline. También se corrigió la conversión de flotantes a texto: `1.5` se mostraba como `2` por redondear la parte entera.
- **Y05:** tamaños, offsets e inicializadores contemplan structs anidados por valor.
- **Y06:** argumentos posteriores a `a7` se envían por stack, con alineación de 16 bytes, y se recuperan en el marco del llamado.
- **Y10:** los arreglos usados para inicializar un campo se copian; los dos structs conservan almacenamiento independiente.
- **Y12:** cada elemento reserva el tamaño completo del struct; el acceso calcula su dirección sin cargar el primer campo como puntero. Se inicializan las celdas de los arreglos locales a cero.
- **TicTacToe:** el compilador era correcto: en el turno 7 ya existe una diagonal ganadora. Se corrigió únicamente el esperado de esta copia.
- **Control:** el compilador era correcto: al retornar, `a=6`, `b=7`, `k=7`; `a*10+b+k` da **74**. Se corrigió únicamente el esperado de esta copia.

Los esperados de **Y02/Y06** se adaptaron al formato decimal del backend RARS (sin seis ceros obligatorios). En **Y12** se corrigió el salto de línea: los operandos encadenados de un solo `>>` se imprimen en la misma línea. Los fuentes y esperados originales de `fase_2/` permanecen intactos.

## Verificación realizada

- `mvn -q compile`: correcto.
- Los **7 casos** compilan mediante `CompilerService.compile(Path)` y ejecutan en `lib/rars1_6.jar`; sus salidas coinciden exactamente con los esperados de esta carpeta.
- Los **14 `.pig`** de `testfile/` fuera de `Archivos_prueba/` compilan y conservan la salida de la ejecución base con las mismas entradas.
- Se probaron además recorridos interactivos de calculadora (suma, historial y factorial), lista doble (insertar, invertir, ordenar y estadísticas), laberinto (movimientos y dos rondas) y ahorcado (adivinar `python`). Todos terminan sin errores de RARS. La única diferencia en estos recorridos es una corrección: el historial de la calculadora ahora concatena `3.75`, antes imprimía `4`.
- Casos adicionales temporales verificaron decimales negativos concatenados, nueve parámetros explícitos en constructor/método Z, dimensiones ocultas junto a argumentos enviados por stack y matrices de structs compartidas entre Lat/Y.

Las compilaciones de verificación se hicieron sobre copias temporales para conservar los archivos `output/` originales.
