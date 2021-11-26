# School-API
Una API para instituciones educativas.
Puede ser consumida con POSTMAN.

Al dia del ultimo push, funcionan los endpoints de localhost:8080/v1/api : 
/alumnos -> [GET] Devuelve el listado de todos los alumnos
/alumnos?name=unNombre ->[GET] Devuelve si existe un usuario con el nombre unNombre
/alumnos/id -> [GET] Reemplazando el id por el numero de id de un usuario devuelve al mismo.
/alumnos/?dni=_numeroDNI_ -> [GET] Se obtiene el usuario con el numero de DNI numeroDNI.
/alumnos/id -> [PUT] Enviando un body json, reemplaza el original
/alumnos/id/cursos -> [GET] Se obtiene la lista de cursos del usuario del id
/alumnos/idAlumno/cursos/idCurso -> Se agrega el curso del idCurso (debe estar creado previamente) al alumno del idAlumno
/alumnos ->[POST] Enviando un body json crea un alumno
/cursos ->[GET] Devuelve todos los cursos
/cursos?name=unNombre ->[GET] Devuelve si existe un curso con el nombre unNombre
/cursos/id -> [GET] Devuelve el curso con el id
/cursos -> [POST] Pasandole un cuerpo de objeto Curso lo crea
/cursos/id ->[PATCH] Pasandole un cuerpo de objeto Curso, modifica al objeto de id
/rrss ->[GET] Devuelve todas las redes sociales
/rrss?name=unNombre ->[GET] Devuelve si existe una red social con el nombre unNombre
/rrss -> [POST] Pasandole un cuerpo de objeto Red Social lo crea
/rrss/id ->[PATCH] Pasandole un cuerpo de objeto RedSocial, modifica al objeto de id
