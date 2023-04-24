(define (domain logistics)

  (:requirements :strips :typing)

  (:types robot place box - object  )

  (:predicates 	(at ?r - robot ?p - place)
  	(store ?b - box ?p - place)
  	(hold ?r - robot ?b - box)
   )


  (:action move
    :parameters (?r - robot ?x - place ?y - place)
    :precondition  (at ?r ?x)
    :effect (and (not (at ?r ?x)) (at ?r ?y))
  )
  
  (:action take
    :parameters (?r - robot ?b - box ?x - place)
    :precondition  (and(at ?r ?x)(store ?b ?x))
    :effect (and (not (store ?b ?x)) (at ?r ?x)(hold ?r ?b))
  )
  (:action drop
    :parameters (?r - robot ?b - box ?x - place)
    :precondition  (and(at ?r ?x)(hold ?r ?b))
    :effect (and (not (hold ?r ?b)) (store ?b ?x))
  )
)
