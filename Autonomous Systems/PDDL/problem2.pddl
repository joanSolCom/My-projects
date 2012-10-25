;Initial state, then not fully connected graph
;more locations than people

(define (problem p)
(:domain hitchhikers)
(:objects
    t1 - car
    p1 - person
    p2 - person
    p3 - person
    p4 - person
    p5 - person
    l0 - location
    l1 - location
    l2 - location
    l3 - location
    l4 - location
    l5 - location
    l6 - location
    l7 - location
    l8 - location
    l9 - location
)

(:init
    (= (total-cost) 0)
    (at t1 l0)
    (free t1)
    (at p1 l1)
    (at p2 l2)
    (at p3 l3)
    (at p4 l4)
    (at p5 l7)
    (connected l0 l1)
    (connected l1 l2)
    (connected l1 l3)
    (connected l2 l1)
    (connected l2 l3)
    (connected l3 l1)
    (connected l3 l2)
    (connected l1 l4)
    (connected l2 l4)
    (connected l3 l4)
    (connected l4 l3)
    (connected l4 l2)
    (connected l4 l1)
    (connected l4 l5)
    (= (time-to l4 l5) 3)
    (connected l5 l4)
    (= (time-to l5 l4) 3)
    (connected l3 l5)
    (= (time-to l3 l5) 4)
    (connected l5 l3)
    (= (time-to l5 l3) 5)
    (connected l2 l5)
    (= (time-to l2 l5) 8)
    (connected l5 l6)
    (= (time-to l5 l6) 2)
    (connected l1 l6)
    (= (time-to l1 l6) 7)
    (connected l4 l6)
    (= (time-to l4 l6) 1)
    (connected l3 l7)
    (= (time-to l3 l7) 3)
    (connected l5 l7)
    (= (time-to l5 l7) 5)
    (connected l7 l5)
    (= (time-to l7 l5) 2)
    (connected l7 l8)
    (= (time-to l7 l8) 1)
    (connected l1 l8)
    (= (time-to l1 l8) 12)
    (connected l5 l8)
    (= (time-to l5 l8) 1)
    (connected l8 l1)
    (= (time-to l8 l1) 6)
    (connected l4 l9)
    (= (time-to l4 l9) 7)
    (connected l6 l9)
    (= (time-to l6 l9) 3)
    (connected l9 l6)
    (= (time-to l9 l6) 9)
    (connected l8 l9)
    (= (time-to l8 l9) 2)
    (= (time-to l1 l2) 5)
    (= (time-to l0 l1) 1)
    (= (time-to l1 l3) 9)
    (= (time-to l2 l1) 3)
    (= (time-to l3 l1) 7)
    (= (time-to l2 l3) 2)
    (= (time-to l3 l2) 2)
    (= (time-to l1 l4) 10)
    (= (time-to l2 l4) 8)
    (= (time-to l3 l4) 3)
    (= (time-to l4 l1) 10)
    (= (time-to l4 l2) 5)
    (= (time-to l4 l3) 6)
)
(:goal 

    (and 
        (at t1 l9)
        (picked p1)
        (dropped p1)
        (picked p2)
        (dropped p2)
        (picked p3)
        (dropped p3)
        (picked p4)
        (dropped p4)
        (picked p5)
        (dropped p5)  
    )
)
(:metric minimize (total-cost))
)