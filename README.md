# Gestión de tarjetas (JavaFX y MySQL)

Aplicación de escritorio en Java (JavaFX, Maven, MySQL y JUnit 5) para consultar y crear tarjetas de débito y crédito.

## Historia de usuario HU-01

COMO analista de crédito, QUIERO asignar un estado a una tarjeta PARA controlar si puede ser utilizada.

### Criterios de aceptación

- La tarjeta se encuentra en uno de estos estados: Activa, Bloqueada o Cancelada.
- El estado se almacena de forma persistente en la base de datos.
- El estado se visualiza en el formulario principal de la aplicación.
- El estado es obligatorio durante la creación de una tarjeta.
- No es posible guardar una tarjeta sin un estado válido.

## Estimación

| Concepto | Valor |
|---|---|
| Puntos de historia | 8 |
| Esfuerzo real | 8 horas |

Justificación: la historia atraviesa todas las capas de la aplicación (modelo, base de datos, acceso a datos, interfaz y pruebas) y requirió cambiar la representación del estado, que pasó de boolean a un enum. Eso obligó a adaptar clases, consultas, formulario y pruebas existentes, además de preparar el entorno (MySQL y configuración del proyecto).

## Tareas técnicas

1. Crear el enum EstadoTarjeta (ACTIVA, BLOQUEADA, CANCELADA) con su documentación JavaDoc.
2. Reemplazar el atributo boolean estado de Tarjeta por EstadoTarjeta, rechazando el valor nulo, y agregar los métodos cancelar() y puedeUsarse().
3. Adaptar los constructores de Debito y Credito para recibir un EstadoTarjeta.
4. Actualizar el script SQL: columna estado VARCHAR(10) NOT NULL con restricción CHECK que solo acepta los tres estados.
5. Adaptar DAOTarjeta para guardar el estado como texto y leerlo con EstadoTarjeta.desdeBD.
6. Agregar al formulario de creación un ComboBox de estado, sin valor por defecto, y validarlo antes de guardar.
7. Mostrar el estado en el resultado de la consulta.
8. Corregir el formato de la fecha de expedición (MM/yyyy) y validar que no esté vacía.
9. Implementar las pruebas unitarias (TestTarjeta, TestEstadoTarjeta) y de integración (TestDAOTarjeta).
10. Actualizar el diagrama UML de clases.
11. Documentar el código con JavaDoc.
12. Grabar el video y publicar el diagrama UML y el video en la wiki.

## Estructura del repositorio

| Ruta | Contenido |
|---|---|
| examen2_Soto/ | Proyecto Maven con el código fuente y las pruebas |
| ScriptDB.sql | Script de la base de datos MySQL actualizado |

## Base de datos

Ejecutar ScriptDB.sql en MySQL (versión 8.0.16 o superior, por la restricción CHECK). Crea la base examen2_soto con sus tablas y datos de prueba.

## Configuración y ejecución

1. Crear en la carpeta examen2_Soto un archivo llamado .env con estas variables:

```
DB_URL=jdbc:mysql://localhost:3306/examen2_soto
DB_USER=su_usuario
DB_PASSWORD=su_clave
```

2. Encender el servicio de MySQL.
3. Ejecutar la aplicación: clase vista/App.java, con Run As, Java Application.
4. Ejecutar las pruebas: clic derecho sobre la clase de prueba, Run As, JUnit Test. Las de tests/integracion necesitan la base de datos.

## Uso de inteligencia artificial

Se usó Claude como apoyo para analizar el proyecto, proponer el diseño y generar el código base, que luego fue aplicado, probado y ajustado en el entorno de desarrollo.

## Wiki

El diagrama UML actualizado y el video de la entrega están en la wiki del repositorio.
