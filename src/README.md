# School-API
Una API para instituciones educativas.
Puede ser consumida con POSTMAN.

Al dia del ultimo push, funcionan los endpoints de localhost:8080/v1/api : 
/alumnos -> [GET] Devuelve el listado de todos los alumnos
/alumnos?name=unNombre ->[GET] Devuelve si existe un usuario con el nombre unNombre
/alumnos/id -> [GET] Reemplazando el id por el numero de id de un usuario devuelve al mismo.
/alumnos/id -> [PUT] Enviando un body json, reemplaza el original
/alumnos ->[POST] Enviando un body json crea un alumno
/cursos ->[GET] Devuelve todos los cursos
/cursos?name=unNombre ->[GET] Devuelve si existe un curso con el nombre unNombre
