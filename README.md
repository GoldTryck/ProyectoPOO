# Para hacer mejores mensajes de commits:

1. Crea un archivo llamado `commitMsg.txt`en la raíz del proyecto

2. Dentro del archivo `commitMsg.txt` coloca la siguiente información:

    Esta información deberas adaptarla antes de hacer cada commit

    ~~~
    [<type>][<scope>][<author>] : <brief description>

    <body>

    <footer>

    ~~~

    - `<type>` Representa el tipo de cambio realizado en el commit (como "feat" para una nueva característica, "fix" para una corrección de error, etc.).
    - `<scope>` podría representar el alcance del cambio (por ejemplo, el módulo o componente afectado), es opcional.
    - `<author>` El nombre del autor del commit.
    - `<brief description>` Breve descripción del cambio realizado.
    - `<body>` Cuerpo opcional que proporciona más detalles sobre el cambio.
    - `<footer>` Secciones opcionales adicionales para metadatos, como referencias a problemas o solicitudes de extracción, es opcional.

    Ejemplo:
    ~~~
    [DELETE][JANE DOE] : Clases no utiles

    -clase01.java
    -clase02.java
    -otraClase.java

    ~~~

3. Realiza algún cambio en el repositorio.
4. Agrega los cambios al stage: `git add .`
5. Realiza el commit usando el archivo que creaste con la descripcion:
    - En linux: `git commit -am "$(cat commitMsg.txt)"`
    - En windows: ` git commit -m "$(Get-Content -Raw ./commitMsg.txt)"`
6. Finalmete sube tu commit usando: `git push origin dev`

