DROP DATABASE   control;

CREATE DATABASE control
  DEFAULT CHARACTER SET utf8
  DEFAULT COLLATE utf8_general_ci;

USE control;

-- inicio de creacion de la base de datos

CREATE TABLE maestros
(
  idMaestros      int         not null AUTO_INCREMENT,
  nombre          varchar(40) not null,
  apellidoPaterno varchar(20) not null,
  apellidoMaterno varchar(20) not null,
  orientador      varchar(1),
  PRIMARY KEY (idMaestros)
) ENGINE = InnoDB;

CREATE TABLE encuestas
(
  idEncuesta  int not null AUTO_INCREMENT,
  descripcion text,
  PRIMARY KEY (idEncuesta)
) ENGINE = InnoDB;

CREATE TABLE grupos
(
  idGrupo      int         not null,
  turno        varchar(10) not null,
  maxAlumnos   int         not null,
  orientador   int         not null,
  jefeGrupo    int,
  enAutorizado int,
  PRIMARY KEY (idGrupo),
  FOREIGN KEY (orientador)   REFERENCES maestros(idMaestros ) ON DELETE CASCADE,
  FOREIGN KEY (enAutorizado) REFERENCES encuestas(idEncuesta) ON DELETE CASCADE
) ENGINE = InnoDB;

CREATE TABLE alumnos
(
  matricula       int         not null AUTO_INCREMENT,
  nombre          varchar(40) not null,
  apellidoPaterno varchar(20),
  apellidoMaterno varchar(20),
  capacitacion    int,
  email           varchar(20),
  telefono        int,
  promedio        real        not null,
  grupo           int         not null,
  PRIMARY KEY  (matricula),
  FOREIGN KEY (grupo)        REFERENCES grupos(idGrupo)  ON DELETE CASCADE,
  FOREIGN KEY (capacitacion) REFERENCES grupos(idGrupo)  ON DELETE CASCADE
) ENGINE = InnoDB;

CREATE TABLE respuestas
(
  idRespuesta int  not null AUTO_INCREMENT,
  respuesta   text not null,
  alumno      int  not null,
  encuesta    int  not null,
  siguiente   int,
  PRIMARY KEY (idRespuesta),
  FOREIGN KEY (siguiente  ) REFERENCES respuestas(idRespuesta) ON DELETE CASCADE,
  FOREIGN KEY ( alumno    ) REFERENCES alumnos(matricula)      ON DELETE CASCADE
) ENGINE = InnoDB;

CREATE TABLE materias
(
  idMateria int         not null AUTO_INCREMENT,
  nombre     varchar(40) not null,
  maxFaltas  int         not null,
  tipo       varchar(20) not null,
  PRIMARY KEY (idMateria)
) ENGINE = InnoDB;

CREATE TABLE documentos
(
  idDocumentos    int  not null,
  causa           text not null,
  fechaExpedicion date not null,
  orientador      int  not null,
  PRIMARY KEY (idDocumentos),
  FOREIGN KEY (orientador) REFERENCES maestros(idMaestros)
) ENGINE = InnoDB;

CREATE TABLE justificantes
(
  idJustificantes int      not null,
  fechaInicio     date     not null,
  fechaFinal      date     not null,
  PRIMARY KEY (idJustificantes),
  FOREIGN KEY (idJustificantes) REFERENCES documentos(idDocumentos) ON DELETE CASCADE
) ENGINE = InnoDB;
    
CREATE TABLE reportes
(
  idReportes        int not null,
  gravedad varchar(20) not null,
  sancion  varchar(20) not null,
  PRIMARY KEY (idReportes),
  FOREIGN KEY (idReportes) REFERENCES documentos(idDocumentos) ON DELETE CASCADE
) ENGINE = InnoDB;

CREATE TABLE faltas
(
  idFalta      int  not null AUTO_INCREMENT,
  alumno       int  not null,
  materia      int  not null,
  fecha        date not null,
  justificante int ,
  PRIMARY KEY (idFalta),
  FOREIGN KEY (justificante) REFERENCES justificantes(idJustificantes) ON DELETE CASCADE,
  FOREIGN KEY (alumno)       REFERENCES alumnos(matricula)             ON DELETE CASCADE,
  FOREIGN KEY (materia)      REFERENCES materias(idMateria)            ON DELETE CASCADE 
) ENGINE = InnoDB;

CREATE TABLE asignacion
(
  id      int not null AUTO_INCREMENT,
  maestro int not null,
  materia int not null,
  grupo   int not null,
  CONSTRAINT asignacion_unica UNIQUE(maestro, materia, grupo),
  PRIMARY KEY (id),
  FOREIGN KEY (maestro) REFERENCES maestros(idMaestros) ON DELETE CASCADE,
  FOREIGN KEY (materia) REFERENCES materias(idMateria)  ON DELETE CASCADE,
  FOREIGN KEY (grupo)   REFERENCES grupos  (idGrupo)    ON DELETE CASCADE
) ENGINE = InnoDB;

CREATE TABLE documentos_alumnos
(
  alumno    int,
  documento int,
  FOREIGN KEY (alumno)    REFERENCES alumnos (matricula)      ON DELETE CASCADE,
  FOREIGN KEY (documento) REFERENCES documentos(idDocumentos) ON DELETE CASCADE
) ENGINE = InnoDB;

-- Inicio de las variables de prueva

INSERT INTO maestros (nombre, apellidoPaterno, apellidoMaterno) VALUES
("Iosdy Idhali", "Pardo", "Campos"),
("Alejandro","Villa", "Renteria"),
("Jesus", "Garcia", "Chavez"),
("Fernando", "Hernandez","Rodriguez"),
("Mauricio","Mendoza","Carrillo"),
("Misael", "Cardona", "Mariñelarena");

INSERT INTO maestros (nombre, apellidoPaterno, apellidoMaterno, orientador) VALUES
("Daniela", "Alvarez", "Quezada", "A"),
("Heidy"  , "Zapata", "Sosa", "A");

INSERT INTO encuestas (descripcion) VALUES
("encuestas a 2dos"),
("encuestas a 4tos");

INSERT INTO grupos (idGrupo, turno, maxAlumnos, orientador) VALUES
(201,"Matutino",20,1),
(202,"Matutino",20,2),
(203,"Matutino",20,1),
(401,"Matutino",20,1),
(402,"Matutino",20,2),
(403,"Matutino",20,2),
(601,"Matutino",20,1),
(602,"Matutino",20,2);

INSERT INTO materias (nombre, maxFaltas, tipo) VALUES
("Matematicas I"  ,16,"Basica"),
("Matematicas III",16,"Basica"),
("Calculo I"      ,16,"Propedeutica"),
("Estructura Socioeconomica de Mexico" ,16,"Basica"),
("Introduccion a las Ciencias Sociales",16,"Basica"),
("Filosofia"  ,16,"Basica"),
("Derecho I",16,"Propedeutica"),
("Administracion",16,"Capacitacion"),
("Contabilidad",16,"Capacitacion"),
("Economia",16,"Propedeutica"),
("Taller de lectura y redaccion",16,"Basica");

INSERT INTO asignacion (maestro, materia, grupo) VALUES
(1,1,201),
(3,5,201),
(5,11,201),
(2,1,202),
(4,5,202),
(6,11,202),
(1,1,203),
(4,5,203),
(7,11,203);

INSERT INTO alumnos (matricula, nombre, apellidoPaterno, apellidoMaterno, promedio, grupo) VALUES
(12030162, "Gabriela", "Bustamante", "Espinoza", 8.5, 201),
(12030163, "Jorge",    "Orozco",     "Terrazas", 8.0, 202),
(12030164, "Alan", "Garcia", "Pardo", 8.5, 203),
(11030162, "Gerardo", "Bustamante", "Espinoza", 8.5, 401),
(11030163, "Julio", "Bustamante", "Espinoza", 8.5, 402),
(11030164, "Andres", "Bustamante", "Espinoza", 8.5, 403),
(10030162, "Geronimo", "Bustamante", "Espinoza", 8.5, 601);

INSERT INTO documentos (idDocumentos, causa, fechaExpedicion, orientador) VALUES
(214215789, 'Infestacion de piojos', '2014-09-22', 8);

INSERT INTO justificantes (idJustificantes, fechaInicio, fechaFinal) VALUES
(214215789, '2014-09-18', '2014-09-25');

INSERT INTO faltas (alumno, materia, fecha, justificante) VALUES
(12030162, 5, '2014-09-17', null),
(12030162, 5, '2014-09-18', 214215789);