(define (domain hitchhikers) 
(:requirements :strips :action-costs :typing :adl)  

(:types 
  location locatable - object
  car person - locatable
) 

(:predicates 

      (at ?x - locatable ?l - location)       
      (in ?p - person ?t - car)       
      (connected ?x ?y - location)
      (free ?t - car)
      (picked ?p - person)
      (dropped ?p - person)
)

(:functions
    (time-to ?l - location ?l1 - location) - Number
)

(:action pickup
 :parameters (?p - person ?t - car ?l - location)
 :precondition (and (at ?t ?l) (at ?p ?l) (free ?t) (not (picked ?p)))
 :effect (and (not (at ?p ?l)) (not (free ?t)) (in ?p ?t) (picked ?p))
)

(:action drop
 :parameters (?p - person ?t - car ?l - location)
 :precondition (and (at ?t ?l) (in ?p ?t) (not (dropped ?p)) (picked ?p) (not (free ?t)))
 :effect (and (not (in ?p ?t)) (free ?t) (at ?p ?l) (dropped ?p))
)

(:action drive
 :parameters (?t - car ?from ?to - location)
 :precondition (and (at ?t ?from) (connected ?from ?to))
 :effect 
        (and 
            (not (at ?t ?from)) 
            (at ?t ?to)
            (increase (total-cost) (time-to ?from ?to))
        )
  ) 
)
