; Sun Dec 04 18:58:22 CET 2011
; 
;+ (version "3.4.7")
;+ (build "Build 620")


(defclass %3ACLIPS_TOP_LEVEL_SLOT_CLASS "Fake class to save top-level slot information"
	(is-a USER)
	(role abstract)
	(single-slot Name
		(type STRING)
;+		(cardinality 1 1)
		(create-accessor read-write))
	(single-slot Quadrimestre
		(type INTEGER)
;+		(cardinality 1 1)
		(create-accessor read-write))
	(single-slot Nombre
		(type STRING)
;+		(cardinality 1 1)
		(create-accessor read-write))
	(multislot Prerrequisitos
		(type INSTANCE)
;+		(allowed-classes Assignatura)
		(cardinality 0 5)
		(create-accessor read-write))
	(single-slot KB_747722_Class15
		(type STRING)
;+		(cardinality 0 1)
		(create-accessor read-write))
	(single-slot Porcentaje+Aprovados
		(type INTEGER)
;+		(cardinality 1 1)
		(create-accessor read-write))
	(multislot Tema
		(type INSTANCE)
;+		(allowed-classes Temas)
		(cardinality 0 4)
		(create-accessor read-write))
	(single-slot Carga+Teoria
		(type SYMBOL)
		(allowed-values MuyAlta Alta Media Baja)
;+		(cardinality 1 1)
		(create-accessor read-write))
	(single-slot CargaProblemas
		(type SYMBOL)
		(allowed-values Alta MuyAlta Media Baja)
;+		(cardinality 1 1)
		(create-accessor read-write))
	(single-slot Creditos+ECTS
		(type FLOAT)
;+		(cardinality 1 1)
		(create-accessor read-write))
	(single-slot EsProyecto
		(type SYMBOL)
		(allowed-values FALSE TRUE)
;+		(cardinality 1 1)
		(create-accessor read-write))
	(single-slot Horario
		(type SYMBOL)
		(allowed-values M T MT)
;+		(cardinality 1 1)
		(create-accessor read-write))
	(single-slot Assig
		(type INSTANCE)
;+		(allowed-classes Assignatura)
;+		(cardinality 1 1)
		(create-accessor read-write))
	(single-slot Tematica
		(type INSTANCE)
;+		(allowed-classes Especializado)
;+		(cardinality 1 1)
		(create-accessor read-write))
	(multislot Convocatorias
		(type INSTANCE)
;+		(allowed-classes Convocatoria)
		(cardinality 0 50)
		(create-accessor read-write))
	(single-slot Nota
		(type FLOAT)
;+		(cardinality 1 1)
		(create-accessor read-write))
	(single-slot Departament
		(type SYMBOL)
		(allowed-values LSI AC EIO ESAII ESSI FME MAII)
;+		(cardinality 1 1)
		(create-accessor read-write))
	(multislot Assignaturas
		(type INSTANCE)
;+		(allowed-classes Assignatura)
		(cardinality 0 10)
		(create-accessor read-write))
	(single-slot Tipo
		(type STRING)
;+		(cardinality 1 1)
		(create-accessor read-write))
	(single-slot Curso
		(type INTEGER)
		(range 1 5)
;+		(cardinality 1 1)
		(create-accessor read-write))
	(single-slot HorarioMatriculado
		(type SYMBOL)
		(allowed-values M T)
;+		(cardinality 1 1)
		(create-accessor read-write))
	(single-slot NomPerfil
		(type STRING)
;+		(cardinality 1 1)
		(create-accessor read-write))
	(multislot Asignatura
		(type INSTANCE)
;+		(allowed-classes Assignatura)
		(cardinality 0 6)
		(create-accessor read-write))
	(single-slot Carga+Labo
		(type SYMBOL)
		(allowed-values Alta Media Baja MuyAlta)
;+		(cardinality 1 1)
		(create-accessor read-write))
	(single-slot Afi
		(type INSTANCE)
;+		(allowed-classes Especializado)
;+		(cardinality 0 1)
		(create-accessor read-write))
	(single-slot EsPresencial
		(type SYMBOL)
		(allowed-values FALSE TRUE)
;+		(cardinality 1 1)
		(create-accessor read-write))
	(single-slot Alumno
		(type STRING)
;+		(cardinality 1 1)
		(create-accessor read-write)))

(defclass Perfil
	(is-a USER)
	(role concrete)
	(single-slot NomPerfil
		(type STRING)
;+		(cardinality 1 1)
		(create-accessor read-write))
	(multislot Assignaturas
		(type INSTANCE)
;+		(allowed-classes Assignatura)
		(cardinality 0 10)
		(create-accessor read-write))
	(single-slot Tematica
		(type INSTANCE)
;+		(allowed-classes Especializado)
;+		(cardinality 1 1)
		(create-accessor read-write)))

(defclass Expediente
	(is-a USER)
	(role concrete)
	(multislot Convocatorias
		(type INSTANCE)
;+		(allowed-classes Convocatoria)
		(cardinality 0 50)
		(create-accessor read-write))
	(single-slot Alumno
		(type STRING)
;+		(cardinality 1 1)
		(create-accessor read-write)))

(defclass Convocatoria
	(is-a USER)
	(role concrete)
	(single-slot Nota
		(type FLOAT)
;+		(cardinality 1 1)
		(create-accessor read-write))
	(single-slot Assig
		(type INSTANCE)
;+		(allowed-classes Assignatura)
;+		(cardinality 1 1)
		(create-accessor read-write))
	(single-slot HorarioMatriculado
		(type SYMBOL)
		(allowed-values M T)
;+		(cardinality 1 1)
		(create-accessor read-write))
	(single-slot Quadrimestre
		(type INTEGER)
;+		(cardinality 1 1)
		(create-accessor read-write)))

(defclass Assignatura
	(is-a USER)
	(role concrete)
	(multislot Tema
		(type INSTANCE)
;+		(allowed-classes Temas)
		(cardinality 0 4)
		(create-accessor read-write))
	(single-slot Carga+Teoria
		(type SYMBOL)
		(allowed-values MuyAlta Alta Media Baja)
;+		(cardinality 1 1)
		(create-accessor read-write))
	(single-slot CargaProblemas
		(type SYMBOL)
		(allowed-values Alta MuyAlta Media Baja)
;+		(cardinality 1 1)
		(create-accessor read-write))
	(single-slot Creditos+ECTS
		(type FLOAT)
;+		(cardinality 1 1)
		(create-accessor read-write))
	(single-slot Carga+Labo
		(type SYMBOL)
		(allowed-values Alta Media Baja MuyAlta)
;+		(cardinality 1 1)
		(create-accessor read-write))
	(single-slot EsProyecto
		(type SYMBOL)
		(allowed-values FALSE TRUE)
;+		(cardinality 1 1)
		(create-accessor read-write))
	(single-slot Departament
		(type SYMBOL)
		(allowed-values LSI AC EIO ESAII ESSI FME MAII)
;+		(cardinality 1 1)
		(create-accessor read-write))
	(single-slot Nombre
		(type STRING)
;+		(cardinality 1 1)
		(create-accessor read-write))
	(multislot Prerrequisitos
		(type INSTANCE)
;+		(allowed-classes Assignatura)
		(cardinality 0 5)
		(create-accessor read-write))
	(single-slot Horario
		(type SYMBOL)
		(allowed-values M T MT)
;+		(cardinality 1 1)
		(create-accessor read-write))
	(single-slot Curso
		(type INTEGER)
		(range 1 5)
;+		(cardinality 1 1)
		(create-accessor read-write))
	(single-slot Porcentaje+Aprovados
		(type INTEGER)
;+		(cardinality 1 1)
		(create-accessor read-write)))

(defclass Troncal
	(is-a Assignatura)
	(role concrete))

(defclass Optativa
	(is-a Assignatura)
	(role concrete))

(defclass ALE
	(is-a Assignatura)
	(role concrete)
	(single-slot EsPresencial
		(type SYMBOL)
		(allowed-values FALSE TRUE)
;+		(cardinality 1 1)
		(create-accessor read-write)))

(defclass Temas
	(is-a USER)
	(role concrete)
	(single-slot Name
		(type STRING)
;+		(cardinality 1 1)
		(create-accessor read-write)))

(defclass General
	(is-a Temas)
	(role concrete))

(defclass Especializado
	(is-a Temas)
	(role concrete)
	(single-slot Afi
		(type INSTANCE)
;+		(allowed-classes Especializado)
;+		(cardinality 0 1)
		(create-accessor read-write)))

(defclass No+Informatico
	(is-a Temas)
	(role concrete))
	
	
(definstances instancies
; Fri Dec 23 18:26:13 CET 2011
; 
;+ (version "3.4.7")
;+ (build "Build 620")

([assignatures_Class1] of  Troncal

	(Carga+Labo Baja)
	(Carga+Teoria MuyAlta)
	(CargaProblemas Media)
	(Creditos+ECTS 9.0)
	(Curso 2)
	(Departament MAII)
	(EsProyecto FALSE)
	(Horario MT)
	(Nombre "MATD")
	(Porcentaje+Aprovados 25)
	(Prerrequisitos
		[KB_747722_Class26]
		[KB_747722_Class23])
	(Tema [KB_747722_Class24]))

([assignatures_Class10000] of  Optativa

	(Carga+Labo Baja)
	(Carga+Teoria Baja)
	(CargaProblemas Baja)
	(Creditos+ECTS 7.5)
	(Curso 2)
	(Departament ESSI)
	(EsProyecto FALSE)
	(Horario M)
	(Nombre "ASAI")
	(Porcentaje+Aprovados 100)
	(Tema [assignatures_Class20001]))

([assignatures_Class20001] of  No+Informatico

	(Name "Historia de la Informatica"))

([assignatures_Class20003] of  Troncal

	(Carga+Labo MuyAlta)
	(Carga+Teoria Baja)
	(CargaProblemas Baja)
	(Creditos+ECTS 9.0)
	(Curso 1)
	(Departament LSI)
	(EsProyecto FALSE)
	(Horario MT)
	(Nombre "P1")
	(Porcentaje+Aprovados 62)
	(Tema [assignatures_Class20004]))

([assignatures_Class20004] of  General

	(Name "Programacion"))

([assignatures_Class20005] of  Troncal

	(Carga+Labo MuyAlta)
	(Carga+Teoria Baja)
	(CargaProblemas Baja)
	(Creditos+ECTS 9.0)
	(Curso 1)
	(Departament LSI)
	(EsProyecto FALSE)
	(Horario MT)
	(Nombre "PRAP")
	(Porcentaje+Aprovados 70)
	(Prerrequisitos [assignatures_Class20003])
	(Tema [assignatures_Class20004]))

([assignatures_Class20006] of  Troncal

	(Carga+Labo Media)
	(Carga+Teoria Media)
	(CargaProblemas Alta)
	(Creditos+ECTS 9.0)
	(Curso 1)
	(Departament AC)
	(EsProyecto FALSE)
	(Horario MT)
	(Nombre "IC")
	(Porcentaje+Aprovados 54)
	(Tema [assignatures_Class20007]))

([assignatures_Class20007] of  General

	(Name "Arquitectura de Computadores"))

([assignatures_Class20008] of  Troncal

	(Carga+Labo Media)
	(Carga+Teoria Media)
	(CargaProblemas Media)
	(Creditos+ECTS 9.0)
	(Curso 1)
	(Departament AC)
	(EsProyecto FALSE)
	(Horario MT)
	(Nombre "EC1")
	(Porcentaje+Aprovados 75)
	(Prerrequisitos [assignatures_Class20006])
	(Tema [assignatures_Class20007]))

([assignatures_Class20009] of  Troncal

	(Carga+Labo Baja)
	(Carga+Teoria Alta)
	(CargaProblemas Media)
	(Creditos+ECTS 9.0)
	(Curso 1)
	(Departament FME)
	(EsProyecto FALSE)
	(Horario MT)
	(Nombre "F")
	(Porcentaje+Aprovados 62)
	(Tema [assignatures_Class20010]))

([assignatures_Class20010] of  General

	(Name "Fisica"))

([assignatures_Class20011] of  Troncal

	(Carga+Labo Baja)
	(Carga+Teoria MuyAlta)
	(CargaProblemas Alta)
	(Creditos+ECTS 7.5)
	(Curso 1)
	(Departament LSI)
	(EsProyecto FALSE)
	(Horario MT)
	(Nombre "IL")
	(Porcentaje+Aprovados 35)
	(Prerrequisitos [assignatures_Class20009])
	(Tema [assignatures_Class20012]))

([assignatures_Class20012] of  General

	(Name "Logica"))

([assignatures_Class20013] of  Troncal

	(Carga+Labo Media)
	(Carga+Teoria Baja)
	(CargaProblemas Baja)
	(Creditos+ECTS 9.0)
	(Curso 2)
	(Departament LSI)
	(EsProyecto FALSE)
	(Horario MT)
	(Nombre "PRED")
	(Porcentaje+Aprovados 80)
	(Prerrequisitos
		[assignatures_Class20005]
		[assignatures_Class20003])
	(Tema [assignatures_Class20004]))

([assignatures_Class20014] of  Troncal

	(Carga+Labo Media)
	(Carga+Teoria Media)
	(CargaProblemas Baja)
	(Creditos+ECTS 9.0)
	(Curso 2)
	(Departament EIO)
	(EsProyecto FALSE)
	(Horario MT)
	(Nombre "EST")
	(Porcentaje+Aprovados 65)
	(Prerrequisitos
		[KB_747722_Class26]
		[KB_747722_Class23])
	(Tema [KB_747722_Class24]))

([assignatures_Class30000] of  Troncal

	(Carga+Labo Baja)
	(Carga+Teoria Media)
	(CargaProblemas Alta)
	(Creditos+ECTS 7.5)
	(Curso 2)
	(Departament ESSI)
	(EsProyecto FALSE)
	(Horario MT)
	(Nombre "ES1")
	(Porcentaje+Aprovados 60)
	(Prerrequisitos [assignatures_Class20013])
	(Tema [assignatures_Class40003]))

([assignatures_Class40000] of  Optativa

	(Carga+Labo Media)
	(Carga+Teoria Media)
	(CargaProblemas Media)
	(Creditos+ECTS 7.5)
	(Curso 2)
	(Departament MAII)
	(EsProyecto FALSE)
	(Horario M)
	(Nombre "CDI")
	(Porcentaje+Aprovados 60)
	(Prerrequisitos [KB_747722_Class26])
	(Tema [KB_747722_Class24]))

([assignatures_Class40002] of  Perfil

	(Assignaturas
		[assignatures_Class10000]
		[assignatures_Class20003])
	(NomPerfil "Gestio i Explotacio de la Informacio")
	(Tematica [assignatures_Class40003]))

([assignatures_Class40003] of  Especializado

	(Afi [assignatures_Class40004])
	(Name "Enginyeria del Software"))

([assignatures_Class40004] of  Especializado

	(Name "Data Mining"))

([assignatures_Class50001] of  Optativa

	(Carga+Labo Media)
	(Carga+Teoria Alta)
	(CargaProblemas Baja)
	(Creditos+ECTS 7.5)
	(Curso 3)
	(Departament AC)
	(EsProyecto FALSE)
	(Horario M)
	(Nombre "PIAM")
	(Porcentaje+Aprovados 50)
	(Prerrequisitos [assignatures_Class60017])
	(Tema [assignatures_Class60018]))

([assignatures_Class50009] of  Troncal

	(Carga+Labo Media)
	(Carga+Teoria Media)
	(CargaProblemas Media)
	(Creditos+ECTS 9.0)
	(Curso 2)
	(Departament AC)
	(EsProyecto FALSE)
	(Horario MT)
	(Nombre "EC2")
	(Porcentaje+Aprovados 67)
	(Prerrequisitos
		[assignatures_Class20008]
		[assignatures_Class20006])
	(Tema [assignatures_Class20007]))

([assignatures_Class50010] of  Troncal

	(Carga+Labo Alta)
	(Carga+Teoria Media)
	(CargaProblemas Media)
	(Creditos+ECTS 6.0)
	(Curso 2)
	(Departament ESAII)
	(EsProyecto FALSE)
	(Horario MT)
	(Nombre "PI")
	(Porcentaje+Aprovados 55)
	(Prerrequisitos
		[assignatures_Class20009]
		[assignatures_Class20006]
		[assignatures_Class20008])
	(Tema [assignatures_Class20007]))

([assignatures_Class60002] of  Optativa

	(Carga+Labo Alta)
	(Carga+Teoria Alta)
	(CargaProblemas Media)
	(Creditos+ECTS 7.5)
	(Curso 2)
	(Departament MAII)
	(EsProyecto FALSE)
	(Horario T)
	(Nombre "C")
	(Porcentaje+Aprovados 65)
	(Prerrequisitos [KB_747722_Class26])
	(Tema [KB_747722_Class24]))

([assignatures_Class60003] of  Troncal

	(Carga+Labo Baja)
	(Carga+Teoria Media)
	(CargaProblemas Alta)
	(Creditos+ECTS 9.0)
	(Curso 3)
	(Departament ESSI)
	(EsProyecto FALSE)
	(Horario MT)
	(Nombre "ES2")
	(Porcentaje+Aprovados 70)
	(Prerrequisitos
		[assignatures_Class30000]
		[assignatures_Class60004])
	(Tema [assignatures_Class40003]))

([assignatures_Class60004] of  Troncal

	(Carga+Labo Alta)
	(Carga+Teoria Alta)
	(CargaProblemas Baja)
	(Creditos+ECTS 9.0)
	(Curso 2)
	(Departament ESSI)
	(EsProyecto FALSE)
	(Horario MT)
	(Nombre "BD")
	(Porcentaje+Aprovados 65)
	(Prerrequisitos [assignatures_Class20013])
	(Tema [assignatures_Class40003]))

([assignatures_Class60005] of  Troncal

	(Carga+Labo MuyAlta)
	(Carga+Teoria Baja)
	(CargaProblemas Baja)
	(Creditos+ECTS 6.0)
	(Curso 2)
	(Departament ESSI)
	(EsProyecto TRUE)
	(Horario MT)
	(Nombre "PROP")
	(Porcentaje+Aprovados 95)
	(Prerrequisitos [assignatures_Class20013])
	(Tema [assignatures_Class20004]))

([assignatures_Class60006] of  Troncal

	(Carga+Labo Media)
	(Carga+Teoria MuyAlta)
	(CargaProblemas Alta)
	(Creditos+ECTS 9.0)
	(Curso 2)
	(Departament LSI)
	(EsProyecto FALSE)
	(Horario MT)
	(Nombre "ADA")
	(Porcentaje+Aprovados 30)
	(Prerrequisitos
		[assignatures_Class20005]
		[assignatures_Class20013])
	(Tema
		[assignatures_Class20004]
		[assignatures_Class20012]))

([assignatures_Class60007] of  Troncal

	(Carga+Labo Alta)
	(Carga+Teoria Alta)
	(CargaProblemas Media)
	(Creditos+ECTS 9.0)
	(Curso 2)
	(Departament AC)
	(EsProyecto FALSE)
	(Horario MT)
	(Nombre "SO")
	(Porcentaje+Aprovados 65)
	(Prerrequisitos
		[assignatures_Class20013]
		[assignatures_Class50009])
	(Tema [assignatures_Class60008]))

([assignatures_Class60008] of  Especializado

	(Afi [assignatures_Class60018])
	(Name "Sistemas Operativos"))

([assignatures_Class60009] of  Troncal

	(Carga+Labo Baja)
	(Carga+Teoria Media)
	(CargaProblemas Baja)
	(Creditos+ECTS 7.5)
	(Curso 3)
	(Departament ESAII)
	(EsProyecto FALSE)
	(Horario MT)
	(Nombre "E3")
	(Porcentaje+Aprovados 90)
	(Prerrequisitos
		[assignatures_Class60006]
		[assignatures_Class30000]
		[assignatures_Class20013]
		[assignatures_Class60005]
		[assignatures_Class60004])
	(Tema [assignatures_Class60010]))

([assignatures_Class60010] of  No+Informatico

	(Name "Economia"))

([assignatures_Class60011] of  Troncal

	(Carga+Labo Alta)
	(Carga+Teoria Media)
	(CargaProblemas Baja)
	(Creditos+ECTS 7.5)
	(Curso 3)
	(Departament LSI)
	(EsProyecto FALSE)
	(Horario MT)
	(Nombre "VIG")
	(Porcentaje+Aprovados 70)
	(Prerrequisitos
		[assignatures_Class60006]
		[assignatures_Class20013]
		[assignatures_Class60005])
	(Tema [assignatures_Class60012]))

([assignatures_Class60012] of  Especializado

	(Name "Graficos"))

([assignatures_Class60013] of  Troncal

	(Carga+Labo MuyAlta)
	(Carga+Teoria Baja)
	(CargaProblemas Baja)
	(Creditos+ECTS 7.5)
	(Curso 3)
	(Departament AC)
	(EsProyecto TRUE)
	(Horario MT)
	(Nombre "PROSO")
	(Porcentaje+Aprovados 75)
	(Prerrequisitos
		[assignatures_Class50009]
		[assignatures_Class20013]
		[assignatures_Class60007])
	(Tema [assignatures_Class60008]))

([assignatures_Class60014] of  Troncal

	(Carga+Labo Alta)
	(Carga+Teoria Baja)
	(CargaProblemas Baja)
	(Creditos+ECTS 7.5)
	(Curso 4)
	(Departament ESSI)
	(EsProyecto TRUE)
	(Horario MT)
	(Nombre "PESBD")
	(Porcentaje+Aprovados 95)
	(Prerrequisitos
		[assignatures_Class60004]
		[assignatures_Class30000]
		[assignatures_Class60003]
		[assignatures_Class60005]
		[assignatures_Class60006])
	(Tema [assignatures_Class40003]))

([assignatures_Class60015] of  Troncal

	(Carga+Labo Baja)
	(Carga+Teoria MuyAlta)
	(CargaProblemas Alta)
	(Creditos+ECTS 9.0)
	(Curso 4)
	(Departament LSI)
	(EsProyecto FALSE)
	(Horario MT)
	(Nombre "TC")
	(Porcentaje+Aprovados 20)
	(Prerrequisitos
		[assignatures_Class60006]
		[assignatures_Class1])
	(Tema [assignatures_Class20012]))

([assignatures_Class60016] of  Troncal

	(Carga+Labo Media)
	(Carga+Teoria MuyAlta)
	(CargaProblemas MuyAlta)
	(Creditos+ECTS 9.0)
	(Curso 4)
	(Departament AC)
	(EsProyecto FALSE)
	(Horario MT)
	(Nombre "AC")
	(Porcentaje+Aprovados 20)
	(Prerrequisitos
		[assignatures_Class50009]
		[assignatures_Class20014]
		[assignatures_Class60007])
	(Tema [assignatures_Class20007]))

([assignatures_Class60017] of  Troncal

	(Carga+Labo Alta)
	(Carga+Teoria Alta)
	(CargaProblemas Media)
	(Creditos+ECTS 9.0)
	(Curso 4)
	(Departament AC)
	(EsProyecto FALSE)
	(Horario MT)
	(Nombre "XC")
	(Porcentaje+Aprovados 60)
	(Prerrequisitos [assignatures_Class60007])
	(Tema [assignatures_Class60018]))

([assignatures_Class60018] of  Especializado

	(Afi [assignatures_Class60008])
	(Name "Xarxes"))

([assignatures_Class60019] of  Troncal

	(Carga+Labo Alta)
	(Carga+Teoria Alta)
	(CargaProblemas Media)
	(Creditos+ECTS 9.0)
	(Curso 5)
	(Departament LSI)
	(EsProyecto FALSE)
	(Horario MT)
	(Nombre "IA")
	(Porcentaje+Aprovados 50)
	(Prerrequisitos
		[assignatures_Class60006]
		[assignatures_Class20011])
	(Tema [assignatures_Class60020]))

([assignatures_Class60020] of  Especializado

	(Name "Inteligencia Artificial"))

([assignatures_Class60021] of  Troncal

	(Carga+Labo Alta)
	(Carga+Teoria Alta)
	(CargaProblemas Media)
	(Creditos+ECTS 9.0)
	(Curso 5)
	(Departament LSI)
	(EsProyecto FALSE)
	(Horario MT)
	(Nombre "CL")
	(Porcentaje+Aprovados 50)
	(Prerrequisitos
		[assignatures_Class20008]
		[assignatures_Class20013]
		[assignatures_Class60015])
	(Tema
		[assignatures_Class20012]
		[KB_747722_Class24]
		[assignatures_Class20004]))

([assignatures_Class60022] of  Troncal

	(Carga+Labo MuyAlta)
	(Carga+Teoria Baja)
	(CargaProblemas Baja)
	(Creditos+ECTS 6.0)
	(Curso 5)
	(Departament AC)
	(EsProyecto TRUE)
	(Horario MT)
	(Nombre "PXC")
	(Porcentaje+Aprovados 90)
	(Prerrequisitos [assignatures_Class60017])
	(Tema
		[assignatures_Class60018]
		[assignatures_Class20004]))

([assignatures_Class60023] of  Optativa

	(Carga+Labo Alta)
	(Carga+Teoria Media)
	(CargaProblemas Media)
	(Creditos+ECTS 7.5)
	(Curso 5)
	(Departament LSI)
	(EsProyecto FALSE)
	(Horario M)
	(Nombre "AIA")
	(Porcentaje+Aprovados 70)
	(Prerrequisitos [assignatures_Class60019])
	(Tema [assignatures_Class60020]))

([assignatures_Class60024] of  Optativa

	(Carga+Labo Baja)
	(Carga+Teoria Media)
	(CargaProblemas Media)
	(Creditos+ECTS 7.5)
	(Curso 3)
	(Departament LSI)
	(EsProyecto FALSE)
	(Horario T)
	(Nombre "ALG")
	(Porcentaje+Aprovados 70)
	(Prerrequisitos
		[assignatures_Class60006]
		[assignatures_Class20014])
	(Tema
		[assignatures_Class20012]
		[assignatures_Class20004]))

([assignatures_Class60026] of  Expediente

	(Alumno "Victor"))

([assignatures_Class60027] of  Perfil

	(NomPerfil "Arquitectures i computacio alt rendiment"))

([assignatures_Class70003] of  Optativa

	(Carga+Labo Baja)
	(Carga+Teoria Media)
	(CargaProblemas Media)
	(Creditos+ECTS 7.5)
	(Curso 3)
	(Departament EIO)
	(Horario T)
	(Nombre "PGPSI")
	(Porcentaje+Aprovados 80)
	(Prerrequisitos [assignatures_Class60009])
	(Tema [assignatures_Class70004]))

([assignatures_Class70004] of  General

	(Name "Economia"))

([assignatures_Class70005] of  Optativa

	(Carga+Labo Media)
	(Carga+Teoria Alta)
	(CargaProblemas Media)
	(Creditos+ECTS 7.5)
	(Curso 4)
	(Departament LSI)
	(EsProyecto FALSE)
	(Horario M)
	(Nombre "COM")
	(Porcentaje+Aprovados 70)
	(Prerrequisitos [assignatures_Class60015])
	(Tema [assignatures_Class20012]))

([assignatures_Class70006] of  Temas

	(Name "Complejidad"))

([assignatures_Class70007] of  Optativa

	(Carga+Labo MuyAlta)
	(Carga+Teoria Media)
	(CargaProblemas Media)
	(Creditos+ECTS 7.5)
	(Curso 3)
	(Departament LSI)
	(EsProyecto FALSE)
	(Horario MT)
	(Nombre "DABD")
	(Porcentaje+Aprovados 60)
	(Prerrequisitos [assignatures_Class60004])
	(Tema [assignatures_Class40003]))

([assignatures_Class70008] of  Optativa

	(Carga+Labo Baja)
	(Carga+Teoria Media)
	(CargaProblemas Alta)
	(Creditos+ECTS 7.5)
	(Curso 2)
	(Departament FME)
	(EsProyecto FALSE)
	(Horario M)
	(Nombre "FFTI")
	(Porcentaje+Aprovados 90)
	(Prerrequisitos
		[assignatures_Class20009]
		[assignatures_Class20014])
	(Tema [assignatures_Class20010]))

([assignatures_Class70026] of  ALE

	(Carga+Labo Baja)
	(Carga+Teoria Media)
	(CargaProblemas Baja)
	(Creditos+ECTS 3.0)
	(Curso 2)
	(Departament EIO)
	(EsPresencial TRUE)
	(EsProyecto FALSE)
	(Horario T)
	(Nombre "CAICA")
	(Porcentaje+Aprovados 100)
	(Tema [assignatures_Class70004]))

([assignatures_Class70027] of  ALE

	(Carga+Labo Alta)
	(Carga+Teoria Media)
	(CargaProblemas Baja)
	(Creditos+ECTS 6.0)
	(Curso 2)
	(Departament AC)
	(Horario MT)
	(Nombre "APC")
	(Porcentaje+Aprovados 67)
	(Prerrequisitos [assignatures_Class50009])
	(Tema [assignatures_Class20007]))

([KB_747722_Class23] of  Troncal

	(Carga+Labo Baja)
	(Carga+Teoria Alta)
	(CargaProblemas Media)
	(Creditos+ECTS 9.0)
	(Curso 1)
	(Departament MAII)
	(EsProyecto FALSE)
	(Horario MT)
	(Nombre "M1")
	(Porcentaje+Aprovados 70)
	(Tema [KB_747722_Class24]))

([KB_747722_Class24] of  General

	(Name "Matematicas"))

([KB_747722_Class26] of  Troncal

	(Carga+Labo Media)
	(Carga+Teoria Alta)
	(CargaProblemas Alta)
	(Creditos+ECTS 9.0)
	(Curso 1)
	(Departament MAII)
	(EsProyecto FALSE)
	(Horario MT)
	(Nombre "M2")
	(Porcentaje+Aprovados 67)
	(Prerrequisitos [KB_747722_Class23])
	(Tema [KB_747722_Class24]))



)	

(defmodule MAIN (export ?ALL))

(deftemplate infoDemanada
	(slot volProjecte)
	(slot horariPreferible)
	(slot volOptatives)
	(slot volALE)
)
(deftemplate infoInferidaUsuari
	(slot dificultatAsumible)
	(slot horariFrequent)
	(slot cursActual)
	(slot recomanarOptatives)
	(slot departamentPreferit)
	(slot perfilPreferit)
	(slot tipusAssignaturaPreferida)
)
(deftemplate temaInteressat
	(slot nomTema)
)
	
(deftemplate recomanacio 
	(slot nom) 
	(slot puntuacio)
)

(deftemplate aBorrar
	(slot nameAssig)
)	

(deftemplate recomanacioRefinada 
	(slot nom) 
	(slot nivell)
)

;;; Obte una resposta a una pregunta booleana
(deffunction si-o-no (?pregunta)
	(printout t (str-cat ?pregunta " (si/no)? "))
	(bind ?resposta (read))
	(if(eq ?resposta si)
		then TRUE 
		else FALSE
	)
)

;;; Obte una resposta a la pregunta d'entre un conjunt de possibles valors
(deffunction pregunta (?pregunta $?valors)
	(printout t crlf ?pregunta)
	(bind ?resposta (read))
	(while (not (member ?resposta ?valors)) do
		(printout t ?pregunta)
		(bind ?resposta (read))
	)
	?resposta
)

;;;
;;; Pantalla d'inici
;;;

(defrule welcome
	(declare (salience 100))
	=>
	(printout t "/***************************************************/" crlf)
	(printout t "            		  Benvingut!!                     " crlf)
	(printout t "            Classificador de Assignatures            " crlf)
	(printout t "     Juan Soler Company     Marc Velmer Montoliu     " crlf)
	(printout t "/***************************************************/" crlf)
	(printout t crlf)
	(focus InfoRecollida)
)


;;;
;;; Modul de recollida d'informacio mitjansant preguntes directes a l'usuari.
;;; 

(defmodule InfoRecollida
	(export ?ALL)
	(import MAIN ?ALL)
)
(defglobal ?*alumne* = "Undefined")

(defrule informacioDemanada
	(declare (salience 100))
	=>

	(bind ?*alumne* (pregunta "Quin expedient vols analitzar?(Victor)?" Victor))
 	(bind ?volProjecte (si-o-no "Vols Cursar Assignatures de projecte?"))
	(bind ?volOptatives (si-o-no "Vols Cursar Assignatures Optatives?"))
	(bind ?volALE (si-o-no "Vols Cursar Assignatures ALE?"))
	(bind ?horariPreferit (pregunta "Quin es el teu horari preferit (Mati/Tarda/Indiferent)?" Mati Tarda Indiferent))
	
	(assert (infoDemanada 
		(volProjecte ?volProjecte)
		(horariPreferible ?horariPreferit)
		(volOptatives ?volOptatives)
		(volALE ?volALE)
	))
)
(defrule descartar-expedients

	?exp <- (object (is-a Expediente)(Alumno ?alumn)(Convocatorias $?convs))
	=>
	(bind ?i 1)
	(if(!= 0 (str-compare ?alumn ?*alumne*))
	then
		(while(<= ?i (length$ $?convs)) do
			(bind ?conv (nth$ ?i $?convs))
			(send (instance-address * ?conv) delete)
			(bind ?i (+ ?i 1))
		)
	)		
)

(defrule cap-inferencia

	(declare(salience -100))
	=>
	(focus Inferencia)
)	

;;;
;;; Modul de Inferencia de l'expedient!
;;; 
(defmodule Inferencia (export ?ALL) (import MAIN ?ALL) )

(defrule welcome-inferencia
	(declare (salience 100))
	=>
	(printout t crlf)
	(printout t "/***************************************************/" crlf)
	(printout t "               Analisi de l'expedient!               " crlf)
	(printout t "/***************************************************/" crlf)
	(printout t crlf)
)

(defglobal ?*aprovades* = 0)
(defglobal ?*suspeses* = 0)
(defglobal ?*totalConvocatories* = 1)

(defrule aprovades
	?conv <- (object (is-a Convocatoria))
	(test (>= (send ?conv get-Nota) 5.0))
	=>
	(bind ?*aprovades* (+ ?*aprovades* 1))
	(bind ?*totalConvocatories* (+ ?*totalConvocatories* 1))
	
)
(defrule suspeses
	?conv <- (object (is-a Convocatoria))
	(test (< (send ?conv get-Nota) 5.0))
	=>
	(bind ?*suspeses* (+ ?*suspeses* 1))
	(bind ?*totalConvocatories* (+ ?*totalConvocatories* 1))
	
)

(defglobal ?*matriculadoMananas* = 0)
(defglobal ?*matriculadoTardes* = 0)

(defrule horariPreferit

	?conv <- (object (is-a Convocatoria))
	=>
	(bind ?horario (send ?conv get-HorarioMatriculado))
	(if(eq ?horario M)
		then
			(bind ?*matriculadoMananas* (+ ?*matriculadoMananas* 1))
		else
			(bind ?*matriculadoTardes* (+ ?*matriculadoTardes* 1))
	)
)


; Tota assignatura a la que hagi tret mes d'un 7, assumirem que es de un tema que li interessa

(defrule temesInteressants

	?conv <- (object (is-a Convocatoria)(Assig ?assig))
	(test (>= (send ?conv get-Nota) 7.0))
	=>
	(bind ?tema (nth 1(send (instance-address * ?assig) get-Tema)))
	(bind ?nomTema (send (instance-address * ?tema) get-Name))
	(assert (temaInteressat 
				(nomTema ?nomTema)
			)
	)

)
(defglobal ?*asignaturaTeoria* = 0)
(defglobal ?*asignaturaProblemas* = 0)
(defglobal ?*asignaturaLaboratorio* = 0)

; De les assignatures aprovades amb bona nota, mirem de quin tipus son

(defrule tipusAssignaturaPreferida

	?conv<- (object (is-a Convocatoria)(Assig ?assig))
	(test (>= (send ?conv get-Nota) 7.0))
	=>
	(bind ?cargaLabo (send (instance-address * ?assig) get-Carga+Labo))
	(bind ?cargaTeoria (send (instance-address * ?assig) get-Carga+Teoria))
	(bind ?cargaProblemas (send (instance-address * ?assig) get-CargaProblemas))
	
	(if (or (= 0 (str-compare ?cargaLabo "Alta"))(= 0 (str-compare ?cargaLabo "MuyAlta")))
		then
			(bind ?*asignaturaLaboratorio* (+ ?*asignaturaLaboratorio* 1))
	)
	(if (or (= 0 (str-compare ?cargaTeoria "Alta"))(= 0 (str-compare ?cargaTeoria "MuyAlta")))
		then
			(bind ?*asignaturaTeoria* (+ ?*asignaturaTeoria* 1))
	)
	(if (or (= 0 (str-compare ?cargaProblemas "Alta"))(= 0 (str-compare ?cargaProblemas "MuyAlta")))
		then
			(bind ?*asignaturaProblemas* (+ ?*asignaturaProblemas* 1))
	)
)

(defglobal ?*optativesLSI* = 0)
(defglobal ?*optativesESAII* = 0)
(defglobal ?*optativesMAII* = 0)
(defglobal ?*optativesEIO* = 0)
(defglobal ?*optativesFME* = 0)
(defglobal ?*optativesESSI* = 0)
(defglobal ?*optativesAC* = 0)

(defrule departamentPreferit

	?conv<- (object (is-a Convocatoria)(Assig ?assig))
	(test (>= (send ?conv get-Nota) 5.0))
	=>
	(if (= 0 (str-compare "Optativa" (type (instance-address * ?assig)))) 
		then
			(bind ?dpt (send (instance-address * ?assig) get-Departament))
			
			(switch ?dpt
			  (case "LSI" then (bind ?*optativesLSI* (+ ?*optativesLSI* 1)))
			  (case "AC" then (bind ?*optativesAC* (+ ?*optativesAC* 1)))
			  (case "ESSI" then (bind ?*optativesESSI* (+ ?*optativesESSI* 1)))
			  (case "EIO" then (bind ?*optativesEIO* (+ ?*optativesEIO* 1)))
			  (case "ESAII" then (bind ?*optativesESAII* (+ ?*optativesESAII* 1)))
			  (case "FME" then (bind ?*optativesFME* (+ ?*optativesFME* 1)))
			  (case "MAII" then (bind ?*optativesMAII* (+ ?*optativesMAII* 1)))
			  (default none)
			)
				
	)
)

(deffunction obtenirDepartamentPreferit ()
	
	(bind ?dpt (max ?*optativesESAII* ?*optativesLSI* ?*optativesEIO* ?*optativesMAII* ?*optativesFME* ?*optativesAC* ?*optativesESSI*))
	(if (> ?dpt 0)
		then
			(if(= ?dpt ?*optativesLSI*)
				then 
					(return "LSI")
			)
			(if(= ?dpt ?*optativesESAII*)
				then 
					(return "ESAII")
			)
			(if(= ?dpt ?*optativesEIO*)
				then 
					(return "EIO")
			)
			(if(= ?dpt ?*optativesAC*)
				then 
					(return "AC")
			)
			(if(= ?dpt ?*optativesFME*)
				then 
					(return "FME")
			)
			(if(= ?dpt ?*optativesMAII*)
				then 
					(return "MAII")
			)
			(if(= ?dpt ?*optativesESSI*)
				then 
					(return "ESSI")
			)
		else
			(return "Indiferent")
	)
)

(defglobal ?*cursActual* = 1)

(defrule cursActual

	?conv<- (object (is-a Convocatoria)(Assig ?assig))
	=>
	(bind ?curs (send (instance-address * ?assig) get-Curso))
	
	(if(> ?curs ?*cursActual*)
		then
			(bind ?*cursActual* ?curs)
	)
)

(deffunction dificultatAsumibleUser (?percentAprovades)
	(bind ?dificultatAsumible 1)
	(if (>= ?percentAprovades 0.8)
		then
			(bind ?dificultatAsumible "Molt Alta")
		else 
			(if (< ?percentAprovades 0.8)
				then 
					(if(>= ?percentAprovades 0.7)
						then
							(bind ?dificultatAsumible "Alta")
					)
				else
					(if(< ?percentAprovades 0.7)
						then
							(if(>= ?percentAprovades 0.5)
								then
									(bind ?dificultatAsumible "Mitja")
							)
						else
							(if (< ?percentAprovades 0.5)
								then
									(bind ?dificultatAsumible "Baixa")
							)
					)
			)
	)	
	?dificultatAsumible
)

(deffunction tipusAssigPreferida ()

	(bind ?tipusAssig "Indiferent")
		(if (> ?*asignaturaTeoria* ?*asignaturaProblemas*)
			then
				(if (> ?*asignaturaTeoria* ?*asignaturaLaboratorio*)
					then
						(bind ?tipusAssig "Teorica")
					else
						(bind ?tipusAssig "Laboratorio")
				)
			else
				(if(> ?*asignaturaProblemas* ?*asignaturaLaboratorio*)
					then
						(bind ?tipusAssig "Problemas")
					else
						(bind ?tipusAssig "Laboratorio")
				)		
		)
		
	?tipusAssig
)
;una variable global per perfil. es dura sa conta de es numero de assignatures de aquell perfil que s'han aprovat
(defglobal ?*perfilGEI* = 0)
(defglobal ?*perfilACAR* = 0)
(defglobal ?*perfilFC* = 0)
(defglobal ?*perfilIIS* = 0)
(defglobal ?*perfilTAP* = 0)
(defglobal ?*perfilXTSO* = 0)
(defglobal ?*perfilESSI* = 0)

(defrule obtenirPerfilPreferit

	?conv<- (object (is-a Convocatoria)(Assig ?assig))
	(test (>= (send ?conv get-Nota) 5.0))
	=>
	(do-for-all-instances ((?perfil Perfil)) TRUE
	
		(bind ?assigs (send ?perfil get-Assignaturas))
		(if (member ?assig ?assigs) 
			then 
				(bind ?nomPerfil (send ?perfil get-NomPerfil))
				
				(switch ?nomPerfil
						(case "Gestio i Explotacio de la Informacio" then (bind ?*perfilGEI* (+ ?*perfilGEI* 1)))
						(case "Enginyeria del software i sistemes d'informacio" then (bind ?*perfilESSI* (+ ?*perfilESSI* 1)))
						(case "Fonaments de la computacio" then (bind ?*perfilFC* (+ ?*perfilFC* 1)))
						(case "Arquitectures i computacio alt rendiment" then (bind ?*perfilACAR* (+ ?*perfilACAR* 1)))
    					(case "Interficies i integracio de sistemes" then (bind ?*perfilIIS* (+ ?*perfilIIS* 1)))
						(case "Tecniques avancades de programacio" then (bind ?*perfilTAP* (+ ?*perfilTAP* 1)))
					    (case "Xarxes telematiques i sistemes operatius" then (bind ?*perfilXTSO* (+ ?*perfilXTSO* 1)))
					  (default none)
				)
		)
	)
)

(deffunction obtenirPerfilPreferit ()
	;On hi ha es zero, s'hauran de posar ses variables globals referents a cada un des perfils
	(bind ?perfilMax (max ?*perfilGEI* ?*perfilACAR* ?*perfilFC* ?*perfilIIS* ?*perfilTAP* ?*perfilXTSO* ?*perfilESSI*))
	(if (> ?perfilMax 0)
		then
			(if(= ?perfilMax ?*perfilGEI*)
				then 
					(return "Gestio i Explotacio de la Informacio")
			)
			(if(= ?perfilMax ?*perfilACAR*)
				then 
					(return "Arquitectures i computacio alt rendiment")
			)
			(if(= ?perfilMax ?*perfilFC*)
				then 
					(return "Fonaments de la computacio")
			)
			(if(= ?perfilMax ?*perfilIIS*)
				then 
					(return "Interficies i integracio de sistemes")
			)
			(if(= ?perfilMax ?*perfilTAP*)
				then 
					(return "Tecniques avancades de programacio")
			)
			(if(= ?perfilMax ?*perfilXTSO*)
				then 
					(return "Xarxes telematiques i sistemes operatius")
			)
			(if(= ?perfilMax ?*perfilESSI*)
				then 
					(return "Enginyeria del software i sistemes d'informacio")
			)
			
	)
	(return "Indiferent")
)


(defrule guardarInfoInferida
	(declare(salience -100))
	=>
	(bind ?percent (/ ?*aprovades* ?*totalConvocatories*))
	(bind ?dificultat (dificultatAsumibleUser ?percent))
	(bind ?tipusAssigPreferida (tipusAssigPreferida))
	(bind ?dptPrefe (obtenirDepartamentPreferit))
	(bind ?perfilPrefe(obtenirPerfilPreferit))
	(if(> ?*matriculadoMananas* ?*matriculadoTardes*)
		then
			(bind ?horariPreferit M)
		else
			(bind ?horariPreferit T)
	)
	
	(if(= ?*cursActual* 1)
		then
			(bind ?opt FALSE)
		else
			(bind ?opt TRUE)
	)
	
	(assert(infoInferidaUsuari
			(dificultatAsumible ?dificultat)
			(horariFrequent ?horariPreferit)
			(cursActual ?*cursActual*)
			(tipusAssignaturaPreferida ?tipusAssigPreferida)
			(perfilPreferit ?perfilPrefe)
			(departamentPreferit ?dptPrefe)
			(recomanarOptatives ?opt)
	))
	(focus Descartar)
)

;;;
;;; Modul per Descartar les assignatures que no es poden fer
;;; 
(defmodule Descartar (export ?ALL) (import MAIN ?ALL) )

(defrule welcome-descartar
	(declare (salience 100))
	=>
	(printout t crlf)
	(printout t "/***************************************************/" crlf)
	(printout t "               Descartant Assignatures!              " crlf)
	(printout t "/***************************************************/" crlf)
	(printout t crlf)
)

; Borrem les assignatures que ja estan aprovades

(defrule  borrar-fetes
	(declare(salience 50))
	?conv <- (object (is-a Convocatoria)(Assig ?assig))
	(test (>= (send ?conv get-Nota) 5.0))
	=>
	(send (instance-address * ?assig) delete)
)

;Si l'usuari no vol Projectes, els descartem

(defrule descartar-projectes
	
	?info <- (infoDemanada (volProjecte ?volProjecte))
	=>
	(do-for-all-instances ((?assig Assignatura)) TRUE
		(if(not ?volProjecte)
			then
			(if(send ?assig get-EsProyecto)
				then
					(send ?assig delete)
			)
		)
	)
)

;Si l'usuari no vol optatives o ales, les descartem. Si es de primer, no recomanem ni optatives ni ales!

(defrule descartar-optatives-ales

	?info <- (infoDemanada (volOptatives ?volOptatives)(volALE ?volALE))
	?inferida <- (infoInferidaUsuari (recomanarOptatives ?recomanarOptatives))
	=>
	(do-for-all-instances ((?assig Assignatura)) TRUE
	
		(if(= 0 (str-compare (type ?assig) "Optativa"))
			then
					(if(or(not ?volOptatives) (not ?recomanarOptatives))
						then
							(send ?assig delete)
					)	
			else		
				(if(= 0 (str-compare (type ?assig) "ALE"))
						then
		
							(if(not ?volALE)
								then
									(send ?assig delete)
							)
					)	
		)
			
	)

)
;Al loro! posar totes ses obligatories amb horari MT, que sino es lia parda

(defrule descartar-horaris-inadequats

	?info <- (infoDemanada (horariPreferible ?horariPreferit))
	=>
	(if(!= 0 (str-compare ?horariPreferit "Indiferent"))
		then
			(do-for-all-instances ((?assig Assignatura)) TRUE
				(if(= 0 (str-compare ?horariPreferit "Mati"))
					then
						(if(= 0 (str-compare(send ?assig get-Horario) "T"))
							then
								(send ?assig delete)
						)
					else
						(if(= 0 (str-compare(send ?assig get-Horario) "M"))
							then
								(send ?assig delete)
						
						)
				)
			
			)
	)		
)

(defrule  borrar-falta-prereq

	?assig <- (object (is-a Assignatura))
	=>
	(bind ?i 1)
	(while(<= ?i (length$ (send ?assig get-Prerrequisitos))) do
		(bind ?prereq (nth$ ?i (send ?assig get-Prerrequisitos)))
		(bind ?ok FALSE)
		(do-for-all-instances ((?conv Convocatoria)) TRUE
			
				(bind ?convAssig (send ?conv get-Assig))
				(if(eq ?prereq ?convAssig)
					then
						(if(>= (send ?conv get-Nota) 5.0)
							then
								(bind ?ok TRUE)
						)
				)
		)
		(if(not ?ok)
			then
				(assert(borra ?assig))
		)
		(bind ?i (+ ?i 1))
	)
	
)

(defrule borrar-prereq
	(declare(salience -50))
	(borra ?assig)
	=>
	(if(instance-existp ?assig)
		then
			(send ?assig delete)
	)		
)

(defrule cap-a-asociacioHeuristica

	(declare(salience -100))
	=>
	(focus AssignacioHeuristica)

)


;;;
;;; Modul de la Assignacio Heuristica
;;; 
(defmodule AssignacioHeuristica (export ?ALL) (import MAIN ?ALL))

(defrule asignatures-vives

	(declare (salience 200))
	?assig <- (object (is-a Assignatura))
	=>
	(printout t "Asignatura Posible: " (send ?assig get-Nombre) crlf)


)


(defrule welcome-assigHeuristica
	(declare (salience 100))
	=>
	(printout t crlf)
	(printout t "/***************************************************/" crlf)
	(printout t "              Assignacio Heuristica!!                " crlf)
	(printout t "/***************************************************/" crlf)
	(printout t crlf)
)

(defrule puntuar-assigs

	?assig <- (object(is-a Assignatura))
	?info <- (infoInferidaUsuari (dificultatAsumible ?dificultat)(horariFrequent ?horari) (cursActual ?curs) (departamentPreferit ?dpt) (perfilPreferit ?perfil) (tipusAssignaturaPreferida ?tipus))
	?preferencia <- (infoDemanada (horariPreferible ?horariPreferit))
	=>
	
	(bind ?puntuacio 0)
	(bind ?dptAssig (send ?assig get-Departament))
	
	;Puntuem segons el dpt
	(if(= 0 (str-compare ?dpt ?dptAssig))
		then
			(bind ?puntuacio (+ ?puntuacio 1))
	)
	
	;Puntuem segons tipus d'assignatura
	(bind ?cursAssig (send ?assig get-Curso))
	(if(eq ?cursAssig ?curs)
		then
			(bind ?puntuacio (+ ?puntuacio 1))
	)
	(if(= 0 (str-compare (type ?assig) "Troncal"))
		then
			(bind ?puntuacio (+ ?puntuacio 4))
		else
			(if(= 0 (str-compare (type ?assig) "Optativa"))
				then	
					(bind ?puntuacio (+ ?puntuacio 2))
			)
	)
	
	;Puntuem segons capacitat y %aprovats
	
	(bind ?aprovats (send ?assig get-Porcentaje+Aprovados))
	(switch ?dificultat
		  (case "Molt Alta" 
				then 
					(bind ?puntuacio (+ ?puntuacio 2))		
		  )
		  (case "Alta" then 
					(if(>= ?aprovats 80)
						then
							(bind ?puntuacio (+ ?puntuacio 2))
						else
							(if(and (< ?aprovats 80) (>= ?aprovats 70))
								then
									(bind ?puntuacio (+ ?puntuacio 1.6))
								else
									(if(and (< ?aprovats 70) (>= ?aprovats 55))
										then
											(bind ?puntuacio (+ ?puntuacio 1.3))
										else
											(if(< ?aprovats 55)
												then
													(bind ?puntuacio (+ ?puntuacio 1))
											)		
									)
							)		
					)
					
		  )
		  (case "Mitja" then 
					(if(>= ?aprovats 80)
						then
							(bind ?puntuacio (+ ?puntuacio 2))
						else
							(if(and (< ?aprovats 80) (>= ?aprovats 70))
								then
									(bind ?puntuacio (+ ?puntuacio 1.2))
								else
									(if(and (< ?aprovats 70) (>= ?aprovats 55))
										then
											(bind ?puntuacio (+ ?puntuacio 0.5))
										else
											(if(< ?aprovats 55)
												then
													(bind ?puntuacio (+ ?puntuacio 0.25))
											)		
									)
							)		
					)	  
		  )
		  (case "Baixa" then 
					(if(>= ?aprovats 80)
						then
							(bind ?puntuacio (+ ?puntuacio 2))
						else
							(if(and (< ?aprovats 80) (>= ?aprovats 70))
								then
									(bind ?puntuacio (+ ?puntuacio 1.5))
								else
									(if(and (< ?aprovats 70) (>= ?aprovats 55))
										then
											(bind ?puntuacio (+ ?puntuacio 0.25))
										else
											(if(< ?aprovats 55)
												then
													(bind ?puntuacio (+ ?puntuacio 0))
											)		
									)
							)		
					)		  
		  )
		  (default none)
	)
	
	(bind ?cargaLabo (send ?assig get-Carga+Labo))
	(bind ?cargaTeoria (send ?assig get-Carga+Teoria))
	(bind ?cargaProblemas (send ?assig get-CargaProblemas))
	
	;Puntuam segons carrega de assignatura
	(if(= 0 (str-compare ?tipus	"Laboratorio"))
		then
			(if(or(= 0 (str-compare ?cargaLabo "Alta"))(= 0 (str-compare ?cargaLabo "MuyAlta")))
				then
					(bind ?puntuacio (+ ?puntuacio 1))
			)
	)
	(if(= 0 (str-compare ?tipus	"Teorica"))
		then
			(if(or(= 0 (str-compare ?cargaTeoria "Alta"))(= 0 (str-compare ?cargaTeoria "MuyAlta")))
				then
					(bind ?puntuacio (+ ?puntuacio 1))
			)
	)
	(if(= 0 (str-compare ?tipus	"Problemas"))
		then
			(if(or(= 0 (str-compare ?cargaProblemas "Alta"))(= 0 (str-compare ?cargaProblemas "MuyAlta")))
				then
					(bind ?puntuacio (+ ?puntuacio 1))
			)
	)
	
	;Puntuam segons horari preferit
	(if(= 0 (str-compare ?horariPreferit "Indiferent"))
		then
			(bind ?horariAssig (send ?assig get-Horario))
			(if(= 0 (str-compare ?horariAssig "MT"))
				then

					(bind ?puntuacio (+ ?puntuacio 0.2))
				else
					(if(= 0 (str-compare ?horariAssig "T"))
						then
							(if(= 0 (str-compare ?horari "Tarda"))
								then
									
									(bind ?puntuacio (+ ?puntuacio 0.2))
							)
						else
							(if(= 0 (str-compare ?horari "Mati"))
								then
									(bind ?puntuacio (+ ?puntuacio 0.2))
							)
							
					)
			)
			
	)
	(do-for-all-instances ((?conv Convocatoria)) TRUE
			
		(bind ?convAssig (send ?conv get-Assig))
		(bind ?nomA (send ?assig get-Nombre))
		(if (eq ?convAssig (instance-name ?assig))
			then
				(if(= 0 (str-compare (type ?assig) "Troncal"))
					then
					(bind ?puntuacio (+ ?puntuacio 100))
				)
		)

	)
	
	(do-for-all-instances ((?perf Perfil)) TRUE
		(bind ?pNom (send ?perf get-NomPerfil))
		(if(= 0 (str-compare ?pNom ?perfil))
			then
			(bind ?assigs (send ?perf get-Assignaturas))
			(if (member ?assig ?assigs)
				then
					(bind ?puntuacio (+ ?puntuacio 2))
			)
		)
	
	)
	
	(assert(recomanacio
		(nom (send ?assig get-Nombre))
		(puntuacio ?puntuacio)
	))
)

(defrule temas
	(declare(salience -100))
	?recomanacio <- (recomanacio(nom ?nom)(puntuacio ?punts))
	?tema <- (temaInteressat(nomTema ?nTema))
	?assig <- (object (is-a Assignatura))
	(test (= 0 (str-compare (send ?assig get-Nombre) ?nom)))
	(not (temaAgradaMirat ?nom ?tema))
	=>

	(bind $?temes (send ?assig get-Tema))
	(bind ?temi (nth$ 1 $?temes))
	(bind ?temi (send (instance-address * ?temi) get-Name))
	
	(if (eq ?nTema ?temi) 
		then (modify ?recomanacio (puntuacio (+ 2 ?punts)))
	)	
	(assert (temaAgradaMirat ?nom ?tema))
	
)

(defrule a-refinament

	(declare(salience -400))
	=>
	(focus Refinament)

)


;;;
;;; Modul del Refinament
;;; 
(defmodule Refinament (export ?ALL) (import MAIN ?ALL) )

(defrule welcome-refinament
	(declare (salience 100))
	?info <- (infoInferidaUsuari (dificultatAsumible ?dificultat)(horariFrequent ?horari) (cursActual ?curs) (recomanarOptatives ?recomOpt)(departamentPreferit ?dpt) (perfilPreferit ?perfil) (tipusAssignaturaPreferida ?tipus))
	?preferencia <- (infoDemanada (horariPreferible ?horariPreferit) (volOptatives ?volOpt)(volALE ?volALE) (volProjecte ?volProj))
	=>

	(printout t crlf)
	(printout t "/***************************************************/" crlf)
	(printout t "           Informacio que ens has proporcionat!      " crlf)
	(printout t "/***************************************************/" crlf)
	(printout t crlf)
	(printout t "El teu horari Preferible es: " ?horariPreferit crlf)
	(printout t "Vols fer optatives?: " ?volOpt crlf)
	(printout t "Vols fer ALEs?: " ?volALE crlf)
	(printout t "Vols fer projectes?: " ?volProj crlf)
	
	(printout t crlf)
	(printout t "/***************************************************/" crlf)
	(printout t "               Informacio que hem Inferit!!	      " crlf)
	(printout t "/***************************************************/" crlf)
	(printout t crlf)
	
	(printout t "La teva dificultat Assumible es: " ?dificultat crlf)
	(printout t "El teu curs actual es: " ?curs crlf)
	(printout t "Et podem recomanar optatives/ALEs?: " ?recomOpt crlf)
	(printout t "El teu departament preferit es: " ?dpt crlf)
	(printout t "El teu perfil preferit es: " ?perfil crlf)
	(printout t "El teu tipus d'assignatura preferida es: " ?tipus crlf)
	(printout t "El teu horari mes frequent es: " ?horari crlf)
	
	(printout t crlf)
	(printout t "/***************************************************/" crlf)
	(printout t "               Refinant  Recomanacions!              " crlf)
	(printout t "/***************************************************/" crlf)
	(printout t crlf)
	
)

(defrule refinar
	(declare (salience 50))
	?recomanacio <- (recomanacio (nom ?nom)(puntuacio ?punts))
	=>
	(if(>= ?punts 100)
		then
			(assert(recomanacioRefinada (nom ?nom)(nivell "A MATRICULAR SEGUR")))
	)
	(if (and (< ?punts 100)(>= ?punts 7.5))
		then
			(assert(recomanacioRefinada (nom ?nom)(nivell "ALTA")))
	)
	(if (and(< ?punts 7.5)(>= ?punts 5))
		then
			(assert(recomanacioRefinada (nom ?nom)(nivell "MITJA")))
	)
	(if (< ?punts 5)
		then
			(assert(recomanacioRefinada (nom ?nom)(nivell "BAIXA")))
	)
)

(defrule printar-recomanacions-obligatories
	(declare(salience -100))
	?recomanacio <- (recomanacioRefinada (nom ?nom)(nivell ?niv))
	=>
	(if(= 0 (str-compare ?niv "A MATRICULAR SEGUR"))
		then
			(printout t "Assignatura: " ?nom " Nivell de recomanacio: " ?niv crlf)
	)
	
)
(defrule printar-recomanacions-alta
	(declare(salience -200))
	?recomanacio <- (recomanacioRefinada (nom ?nom)(nivell ?niv))
	=>
	(if(= 0 (str-compare ?niv "ALTA"))
		then
			(printout t "Assignatura: " ?nom " Nivell de recomanacio: " ?niv crlf)
	)
	
)
(defrule printar-recomanacions-mitja
	(declare(salience -400))
	?recomanacio <- (recomanacioRefinada (nom ?nom)(nivell ?niv))
	=>
	(if(= 0 (str-compare ?niv "MITJA"))
		then
			(printout t "Assignatura: " ?nom " Nivell de recomanacio: " ?niv crlf)
	)
	
)
(defrule printar-recomanacions-baixa
	(declare(salience -800))
	?recomanacio <- (recomanacioRefinada (nom ?nom)(nivell ?niv))
	=>
	(if(= 0 (str-compare ?niv "BAIXA"))
		then
			(printout t "Assignatura: " ?nom " Nivell de recomanacio: " ?niv crlf)
	)
	
)





	