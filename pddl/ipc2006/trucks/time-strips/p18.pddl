; Time: 0.09 seconds
(define (problem GROUNDED-TRUCK-18)
(:domain GROUNDED-TRUCKS-TIME)
(:init
(FOO)
(at_package20_l3)
(at_package19_l3)
(at_package18_l3)
(at_package17_l3)
(at_package16_l4)
(at_package15_l4)
(at_package14_l4)
(at_package13_l4)
(at_package12_l2)
(at_package11_l2)
(at_package10_l2)
(at_package9_l2)
(at_package8_l3)
(at_package7_l3)
(at_package6_l3)
(at_package5_l3)
(at_package4_l1)
(at_package3_l1)
(at_package2_l1)
(at_package1_l1)
(free_a4_truck3)
(free_a3_truck3)
(free_a2_truck3)
(free_a1_truck3)
(free_a4_truck2)
(free_a3_truck2)
(free_a2_truck2)
(free_a1_truck2)
(free_a4_truck1)
(free_a3_truck1)
(free_a2_truck1)
(free_a1_truck1)
(at_truck3_l4)
(at_truck2_l1)
(at_truck1_l1)
)
(:goal
(and
(delivered_package20_l5)
(delivered_package19_l1)
(delivered_package18_l1)
(delivered_package17_l5)
(delivered_package16_l2)
(delivered_package15_l2)
(delivered_package14_l3)
(delivered_package13_l2)
(delivered_package12_l4)
(delivered_package11_l1)
(delivered_package10_l1)
(delivered_package9_l1)
(delivered_package8_l4)
(delivered_package7_l2)
(delivered_package6_l2)
(delivered_package5_l5)
(delivered_package4_l3)
(delivered_package3_l5)
(delivered_package2_l5)
(delivered_package1_l3)
)
)
(:metric MINIMIZE (TOTAL-TIME))
)