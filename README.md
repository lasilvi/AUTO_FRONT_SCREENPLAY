# Automatización Front — Patrón Screenplay (Serenity BDD + Cucumber)

Proyecto de automatización end-to-end para la aplicación web de gestión de pedidos de un restaurante. Implementa el patrón **Screenplay** con **Serenity BDD** y **Cucumber**, cubriendo el ciclo de vida completo de un pedido: desde la selección de mesa hasta la confirmación del estado final en cocina.

---

## Flujo del escenario automatizado

```
Cliente selecciona mesa
       ↓
Cliente agrega productos del menú
       ↓
Cliente confirma el pedido (→ captura el orderId de la URL)
       ↓
Cocina inicia sesión con PIN y gestiona la orden en el board
  [Iniciar] → [Marcar listo]  (localizados por shortId para evitar colisiones)
       ↓
Cliente consulta el estado en /client/status/:orderId
       ↓
Verifica que el estado sea "Listo" ✓
```

---

## Arquitectura del proyecto

```
src/test/
├── java/com/restaurante/
│   ├── runners/
│   │   └── CucumberTestRunner.java       ← Punto de entrada JUnit 5 + Cucumber
│   ├── hooks/
│   │   └── Hooks.java                    ← @Before/@After: Stage y Actor
│   ├── stepdefinitions/
│   │   └── PedidoStepDefinitions.java    ← Glue Cucumber → Screenplay
│   ├── tasks/
│   │   ├── SeleccionarMesa.java          ← Clic en la mesa del grid
│   │   ├── AgregarProducto.java          ← Clic en "Agregar" del menú
│   │   ├── IrAlCarrito.java              ← Navega al carrito
│   │   ├── ConfirmarPedido.java          ← Confirma y espera la página de confirmación
│   │   ├── IngresarALaCocina.java        ← Login cocina con PIN
│   │   ├── PrepararPedido.java           ← Iniciar + Marcar listo (por shortId)
│   │   └── ConsultarEstadoPedido.java    ← Navega a /client/status/:orderId
│   ├── questions/
│   │   └── EstadoDelPedido.java          ← Lee el badge de estado
│   ├── ui/                               ← Page Objects (solo locators, sin lógica)
│   │   ├── TableSelectionPage.java
│   │   ├── MenuPage.java
│   │   ├── OrderConfirmationPage.java
│   │   ├── KitchenPage.java
│   │   └── OrderStatusPage.java
│   └── util/
│       └── Urls.java                     ← URLs base configurables
└── resources/
    ├── features/
    │   └── pedido.feature                ← Escenario BDD
    ├── serenity.conf                     ← Configuración Serenity/WebDriver
    └── logback-test.xml                  ← Configuración de logs
```

---

## Prerrequisitos

| Herramienta | Versión mínima | Notas |
|---|---|---|
| Java (JDK) | 17 | Variable `JAVA_HOME` configurada |
| Google Chrome | Cualquier estable | ChromeDriver se gestiona automáticamente |
| Aplicación web | — | Debe estar corriendo en `http://localhost:5173` |

> La aplicación frontend del restaurante debe estar levantada **antes** de ejecutar los tests. Si corre en otro puerto, actualiza `webdriver.base.url` en [serenity.properties](serenity.properties).

---

## Cómo ejecutar los tests

### Comando principal (desde la raíz del proyecto)

```powershell
.\gradlew.bat clean test aggregate
```
```
.\gradlew.bat aggregate
```

```
.\gradlew.bat clean test aggregate
```

| Tarea | Descripción |
|---|---|
| `clean` | Elimina compilados y reportes anteriores |
| `test` | Compila y ejecuta los escenarios Cucumber |
| `aggregate` | Genera el reporte HTML de Serenity |

### Solo ejecutar (sin limpiar)

```powershell
.\gradlew.bat test aggregate
```

### En Linux / macOS

```bash
./gradlew clean test aggregate
```

---

## Reporte de resultados

Tras la ejecución, el reporte interactivo de Serenity queda en:

```
target/site/serenity/index.html
```

Ábrelo en el navegador para ver:
- Resultado por escenario y paso
- Capturas de pantalla de cada acción
- Timeline de ejecución

---

## Configuración

### `serenity.properties` (raíz del proyecto)

```properties
webdriver.driver=chrome
webdriver.base.url=http://localhost:5173
headless.mode=false
default.wait.for.timeout=20000
```

- Cambia `webdriver.base.url` si la app corre en otro host/puerto.
- Cambia `headless.mode=true` para ejecutar sin abrir el navegador (útil en CI).

### PIN de cocina

El PIN de acceso al panel de cocina está configurado en `PedidoStepDefinitions.java`:

```java
IngresarALaCocina.conPin("cocina123")
```

Actualízalo si el PIN de la aplicación cambia.

---

## Decisiones técnicas relevantes

### Captura del `orderId` desde la URL
En lugar de leer el texto del DOM (que tiene animación de entrada en React), el `orderId` se extrae directamente de la URL de confirmación:
```
/client/confirm/a63dfa87-729c-44b5-a7f3-6331aaccb9cb
```
Esto evita condiciones de carrera con animaciones de componentes.

### Localización de la card en el board por `shortId`
El board de cocina puede acumular múltiples pedidos de la misma mesa de runs anteriores. Los locators usan los primeros 8 caracteres del `orderId` (`#a63dfa87`) que son únicos por pedido, en lugar del número de mesa que puede repetirse.

### `scrollIntoView` antes de cada click en el board
El header sticky de la cocina puede interceptar clicks en cards que quedan en la parte superior del viewport. Se usa `JavascriptExecutor` para centrar el elemento antes de interactuar con él.

### XPath con `normalize-space()` en lugar de `text()`
React puede generar nodos de texto fragmentados al interpolar variables (`"Mesa " + {id}`). Usar `normalize-space()` sin argumento concatena todo el texto visible y garantiza el match correcto.

---

## Estructura de dependencias

Definidas en [build.gradle](build.gradle):

- `serenity-core`, `serenity-cucumber`, `serenity-screenplay`, `serenity-screenplay-webdriver` — Framework principal
- `cucumber-junit-platform-engine` — Motor Cucumber para JUnit 5
- `junit-platform-suite`, `junit-jupiter-engine` — Suite runner
- `logback-classic` — Logging
