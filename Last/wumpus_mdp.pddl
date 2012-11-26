(define (domain wumpus-adl)
  (:requirements :adl :typing)

  ;; object types
  (:types agent wumpus gold arrow square)

  (:predicates
   (adj ?square-1 ?square-2 - square)
   (pit ?square - square)
   (at ?what ?square)
   (have ?who ?what)
   (alive ?who))

  (:action move
    :parameters (?who - agent ?from - square ?to - square)
    :precondition (and (alive ?who)
		       (at ?who ?from)
		       (adj ?from ?to)
                        )
    :effect (and (not (at ?who ?from))
		 (at ?who ?to)

		 (when (pit ?to)
		   (and (not (alive ?who))))

		 (when (exists (?w - wumpus) (and (at ?w ?to) (alive ?w)))
		   (and (not (alive ?who)))))
    )

  (:action take
    :parameters (?who - agent ?where - square ?what)
    :precondition (and (alive ?who)
		       (at ?who ?where)
		       (at ?what ?where))
    :effect (and (have ?who ?what)
		 (not (at ?what ?where)))
    )

  (:action shoot
    :parameters (?who - agent ?where - square ?with-arrow - arrow
		 ?victim - wumpus ?where-victim - square)
    :precondition (and (alive ?who)
		       (have ?who ?with-arrow)
		       (at ?who ?where)
		       (alive ?victim)
		       (at ?victim ?where-victim)
		       (adj ?where ?where-victim))
    :effect (and (not (alive ?victim))
		 (not (have ?who ?with-arrow)))
    )
)

(define (problem wumpus-adl-1)
  (:domain wumpus-adl)

  (:objects
   sq-1-1 sq-1-2 sq-1-3 sq-2-1 sq-2-2 sq-2-3 - square
   the-gold - gold
   the-arrow - arrow
   agent-1 - agent
   wumpus-1 - wumpus)

  (:init (adj sq-1-1 sq-1-2) (adj sq-1-2 sq-1-1)
	 (adj sq-1-2 sq-1-3) (adj sq-1-3 sq-1-2)
	 (adj sq-2-1 sq-2-2) (adj sq-2-2 sq-2-1)
	 (adj sq-2-2 sq-2-3) (adj sq-2-3 sq-2-2)
	 (adj sq-1-1 sq-2-1) (adj sq-2-1 sq-1-1)
	 (adj sq-1-2 sq-2-2) (adj sq-2-2 sq-1-2)
	 (adj sq-1-3 sq-2-3) (adj sq-2-3 sq-1-3)
	 (pit sq-1-2)
	 (at the-gold sq-1-3)
	 (at agent-1 sq-1-1)
	 (alive agent-1)
	 (have agent-1 the-arrow)
	 (at wumpus-1 sq-2-3)
	 (alive wumpus-1))

  (:goal (and (have agent-1 the-gold) (at agent-1 sq-1-1) (alive agent-1)))
  )