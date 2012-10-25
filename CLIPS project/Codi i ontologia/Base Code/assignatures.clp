; Modul Principal del sistema

(defmodule MAIN (export ?ALL))

; Estructures on guardarem la informacio que hem de manegar en el proces de inferencia i de clasificacio heuristica

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
; Template per a esborrar les assignatures, ho fem en 2 pasos. Primer marquem mitjançant aquest template, i despres esborram totes les 
; Assignatures que hagin estat inserides dins aquests templates

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

	(bind ?*alumne* (pregunta "Quin expedient vols analitzar?(Guillem,Joan)?" Guillem Joan))
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
;Variables globals per a calcular la dificultat asumible de lusuari
(defglobal ?*aprovades* = 0)
(defglobal ?*suspeses* = 0)
(defglobal ?*totalConvocatories* = 0)

; Contem aprovades i suspeses

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
; Globals per averiguar el horari mes frequent
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

; De les assignatures aprovades amb bona nota, mirem de quin tipus son i ho guardem

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
; Comptadors de optatives matriculades per departament, per descobrir quin es el dpt preferit de l'alumne

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

; Regla que guarda tot el que hem inferit en aquest modul en el template

(defrule guardarInfoInferida
	(declare(salience -100))
	=>
	; Calculem la dificultat asumible per l'usuari
	(bind ?percent (/ ?*aprovades* ?*totalConvocatories*))
	(bind ?dificultat (dificultatAsumibleUser ?percent))
	
	(bind ?tipusAssigPreferida (tipusAssigPreferida))
	(bind ?dptPrefe (obtenirDepartamentPreferit))
	(bind ?perfilPrefe(obtenirPerfilPreferit))
	
	;Mirem quin horari es el mes frequent
	
	(if(> ?*matriculadoMananas* ?*matriculadoTardes*)
		then
			(bind ?horariPreferit M)
		else
			(bind ?horariPreferit T)
	)
	
	; Si es de primer, marquem que no hem de recomanar optatives
	
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

;Si l'usuari no vol optatives o ales, les descartem. Si es de primer, no recomanem optatives

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
; Descartem tota assignatura que tengui un horari diferent al exigit per lusuari. Si li es indiferent, no descartam

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
; Borrem assignatures que no podem fer per falta de prerequisits

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
		;Marquem l'assignatura per a borrar posteriorment. Si no es fa aixi, hi ha problemes, ja que
		; mes d'una assignatura pot tenir la mateixa com a prerequisit, i si ja lhem borrada, CLIPS es queixa
		(if(not ?ok)
			then
				(assert(borra ?assig))
		)
		(bind ?i (+ ?i 1))
	)
	
)
; borrem definitivament, posem prioritat negativa per a que s'executi despres del proces de marcat.
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

; Printam les assignatures que encara queden
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

;Regla principal del sistema, agafem la informacio que ens ha contestat i la que hem inferit, i a partir d'aixo i els nostres criteris, puntuem totes les assignatures que queden a la base de fets
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
	; Si una assignatura no la hem descartat i esta a les convocatories, es que esta suspesa, per tant, pujem molt la puntuacio
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
	;sumem si forma part del nostre perfil preferit
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
;puntuem segons els temes que interessen a lalumne. Utilitzem un template per a marcar i no entrar mes cops de la conta.
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
	; printem les coses que tenim sobre el user
	
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
; Refinem
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
; Printem per nivells de recomanacio
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





	