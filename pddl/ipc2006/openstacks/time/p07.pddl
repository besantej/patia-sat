
(define (problem os-time-wbop_20_20-21-fixed18)
        (:domain openstacks-time)
        (:objects n0 n1 n2 n3 n4 n5 n6 n7 n8 n9 n10 n11 n12 n13 n14 n15 n16 n17
         n18 - count o1 o2 o3 o4 o5 o6 o7 o8 o9 o10 o11 o12 o13 o14 o15 o16 o17
         o18 o19 o20 - order p1 p2 p3 p4 p5 p6 p7 p8 p9 p10 p11 p12 p13 p14 p15
         p16 p17 p18 p19 p20 - product)
        (:init (next-count n0 n1) (next-count n1 n2) (next-count n2 n3)
         (next-count n3 n4) (next-count n4 n5) (next-count n5 n6)
         (next-count n6 n7) (next-count n7 n8) (next-count n8 n9)
         (next-count n9 n10) (next-count n10 n11) (next-count n11 n12)
         (next-count n12 n13) (next-count n13 n14) (next-count n14 n15)
         (next-count n15 n16) (next-count n16 n17) (next-count n17 n18)
         (stacks-avail n18) (waiting o1) (includes o1 p11) (includes o1 p13)
         (includes o1 p16) (includes o1 p19) (waiting o2) (includes o2 p7)
         (includes o2 p9) (includes o2 p12) (includes o2 p18) (waiting o3)
         (includes o3 p2) (includes o3 p3) (includes o3 p6) (includes o3 p16)
         (waiting o4) (includes o4 p1) (includes o4 p3) (includes o4 p14)
         (includes o4 p15) (waiting o5) (includes o5 p1) (includes o5 p6)
         (includes o5 p13) (includes o5 p15) (waiting o6) (includes o6 p1)
         (includes o6 p8) (includes o6 p16) (includes o6 p19) (waiting o7)
         (includes o7 p1) (includes o7 p3) (includes o7 p9) (includes o7 p11)
         (waiting o8) (includes o8 p4) (includes o8 p10) (includes o8 p18)
         (includes o8 p20) (waiting o9) (includes o9 p4) (includes o9 p8)
         (includes o9 p14) (includes o9 p19) (waiting o10) (includes o10 p2)
         (includes o10 p4) (includes o10 p6) (includes o10 p17) (waiting o11)
         (includes o11 p10) (includes o11 p12) (includes o11 p17)
         (includes o11 p19) (waiting o12) (includes o12 p7) (includes o12 p13)
         (includes o12 p15) (includes o12 p20) (waiting o13) (includes o13 p7)
         (includes o13 p10) (includes o13 p12) (includes o13 p20) (waiting o14)
         (includes o14 p5) (includes o14 p8) (includes o14 p11)
         (includes o14 p18) (waiting o15) (includes o15 p3) (includes o15 p4)
         (includes o15 p5) (includes o15 p16) (waiting o16) (includes o16 p9)
         (includes o16 p13) (includes o16 p15) (includes o16 p18) (waiting o17)
         (includes o17 p2) (includes o17 p5) (includes o17 p14)
         (includes o17 p17) (waiting o18) (includes o18 p6) (includes o18 p8)
         (includes o18 p9) (includes o18 p11) (waiting o19) (includes o19 p7)
         (includes o19 p12) (includes o19 p14) (includes o19 p20) (waiting o20)
         (includes o20 p2) (includes o20 p5) (includes o20 p10)
         (includes o20 p17) (= (make-time p1) 20) (= (make-time p2) 180)
         (= (make-time p3) 80) (= (make-time p4) 180) (= (make-time p5) 80)
         (= (make-time p6) 200) (= (make-time p7) 160) (= (make-time p8) 40)
         (= (make-time p9) 60) (= (make-time p10) 140) (= (make-time p11) 180)
         (= (make-time p12) 100) (= (make-time p13) 180) (= (make-time p14) 80)
         (= (make-time p15) 160) (= (make-time p16) 40) (= (make-time p17) 40)
         (= (make-time p18) 60) (= (make-time p19) 140) (= (make-time p20) 20))
        (:goal
         (and (shipped o1)
              (shipped o2)
              (shipped o3)
              (shipped o4)
              (shipped o5)
              (shipped o6)
              (shipped o7)
              (shipped o8)
              (shipped o9)
              (shipped o10)
              (shipped o11)
              (shipped o12)
              (shipped o13)
              (shipped o14)
              (shipped o15)
              (shipped o16)
              (shipped o17)
              (shipped o18)
              (shipped o19)
              (shipped o20)))
        (:metric minimize (total-time)))
