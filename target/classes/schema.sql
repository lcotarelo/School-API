insert into Curso(DESCRIPCION ,NOMBRE_CURSO ) values ('Java Empresarial','Java EE'),('Java Standard','Java SE'),('Js','Javascript'),('C#','C-Sharp');
insert into profesor (dni, domicilio, email, nombre) values ('34243642', 'Bouchard', 'ddominick0@jimdo.com', 'Leandro Cotarelo');
insert into alumno (id_alumno, dni, domicilio, email, nombre) values (1, '34071597', '61 Graceland Point', 'mmebius1@constantcontact.com', 'Mahmoud Mebius');
insert into alumno (id_alumno, dni, domicilio, email, nombre) values (2, '2dd240fa-182d-4513-8fde-f4f871fa7479', '89248 Coleman Point', 'ddominick0@jimdo.com', 'Durante Dominick');
insert into ALUMNO_CURSOS(ALUMNOS_ID_ALUMNO ,CURSOS_ID_CURSO ) values (1,1),(1,2),(2,1),(2,3);
insert into RED_SOCIAL (ICONO ,NOMBRE ) values ('Icono1','Linkedin'),('Icono2','Facebook')