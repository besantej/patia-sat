(define (domain logistics)

  (:requirements :strips :typing)

  (:types robot place - object  )

  (:predicates 	(at ?r - robot ?p - place) )


  (:action move
    :parameters (?r - robot ?x - place ?y - place)
    :precondition  (at ?r ?x)
    :effect (and (not (at ?r ?x)) (at ?r ?y))
  )
)
