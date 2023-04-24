(define (problem pb_logistics)
  (:domain logistics)

  (:objects
     p1 p2 - place
     robot1 - robot
     b - box
     )

  (:init 
  	(at robot1 p1)
  	(store b p1)
  )

  (:goal (store b p2))
)
