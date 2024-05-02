# Proyecto Mascotita: Sistema de Gestión para Tiendas de Mascotas y Veterinarios

Este proyecto, denominado **Mascotita**, es un sistema de gestión desarrollado en Java que permite a una cadena de tiendas de mascotas y veterinarios llevar el control de sus ventas de productos, adopciones de clientes y consultas médicas, vacunación o servicios de higiene programadas a domicilio.

## Funcionalidades Principales

El sistema ofrece las siguientes funcionalidades:

1. **Alta de cliente**
2. **Alta de mascota**
3. **Alta y Baja de veterinarios o asistente personal**
4. **Alta de gerente en sucursal**
5. **Registro de citas de veterinarios a domicilio**
6. **Alta de paquetes** (cortes, baño, desparasitación, esterilización, etc.)
7. **Adopción o devolución de mascotas**
8. **Pago de paquetes**
9. **Consulta de citas de veterinarios**
10. **Consulta de paquetes**
11. **Consulta de adopciones**
12. **Consulta de veterinarios**
13. **Escritura a archivo de citas**

## Requisitos del Sistema

- Java JDK (versión 21.0.1 o superior)

## Instalación y Uso

1. Clona este repositorio en tu máquina local.
2. Abre el proyecto en tu IDE de preferencia.
3. Compila y ejecuta el programa.

## Estructura del Proyecto

- **src**: Contiene los archivos fuente del proyecto.
- **README.md**: Este archivo que estás leyendo.
- **README.txt**: Documentación con los nombres de los alumnos y la declaración de autoría.

## Estructura de las Clases

- **Persona**: Clase base que contiene los atributos comunes para clientes, veterinarios y gerentes.
- **Cliente**: Clase que hereda de Persona y representa a los clientes de la tienda.
- **Veterinario**: Clase que hereda de Persona y representa a los veterinarios.
- **Gerente**: Clase que hereda de Persona y representa a los gerentes de sucursal.
- **Mascota**: Clase que representa a las mascotas, con atributos como nombre, raza, etc.
- **Sucursal**: Enumeración que define las sucursales y sus zonas.
- **Cita**: Clase que representa las citas de veterinarios a domicilio.
- **Tarjeta**: Clase que representa la información de la tarjeta de crédito para los pagos de servicios.
- **Adopcion**: Clase que maneja el proceso de adopción de mascotas.

## Notas Adicionales

- Se emplea el paradigma orientado a objetos, haciendo uso de **herencia**, **polimorfismo**, **colecciones**, **enumeraciones**, **excepciones personalizadas**, **interfaces**, y **ordenamiento**.
- El código fuente contiene comentarios que indican la autoría de cada alumno y explican la funcionalidad de los métodos.
- Se ha incluido un archivo `README.txt` que contiene la declaración de autoría.

## Contribución

Las contribuciones son bienvenidas. Si deseas contribuir a este proyecto, sigue estos pasos:

1. Haz un fork del proyecto.
2. Crea una nueva rama (`git checkout -b feature/nueva-caracteristica`).
3. Realiza tus cambios y haz commit de ellos (`git commit -am 'Agrega una nueva característica'`).
4. Haz push de tu rama (`git push origin feature/nueva-caracteristica`).
5. Abre un pull request.

## Licencia

Este proyecto se encuentra bajo la [Licencia Creative Commons Atribución-CompartirIgual 4.0 Internacional](https://creativecommons.org/licenses/by-sa/4.0/).

Puedes:

- **Compartir**: copiar y redistribuir el material en cualquier medio o formato.
- **Adaptar**: remezclar, transformar y construir a partir del material para cualquier propósito, incluso comercialmente.

Bajo los siguientes términos:

- **Atribución**: debes dar crédito de manera adecuada, proporcionar un enlace a la licencia e indicar si se han realizado cambios. Puedes hacerlo de cualquier manera razonable, pero no de manera que sugiera que el licenciante respalda tu uso.
- CompartirIgual: si remezclas, transformas o construyes a partir del material, debes distribuir tus contribuciones bajo la misma licencia que el original.

Para más detalles, consulta el archivo [LICENSE.md](LICENSE.md). 


## Contacto

Para preguntas o sugerencias sobre el proyecto, puedes contactar a [nombre del equipo por definir] en [correo electrónico por definir :V].

