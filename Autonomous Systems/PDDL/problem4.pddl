; Only one person. The final goal is the second state that will be visited.

(define (problem p)
(:domain hitchhikers)
(:objects
    t1 - car
    p1 - person
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
    l10 - location
    l11 - location
)

(:init
    (= (total-cost) 0)
    (at t1 l0)
    (free t1)
    (at p1 l10)
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
    (connected l5 l6)
    (= (time-to l5 l6) 4)
    (connected l6 l7)
    (= (time-to l6 l7) 4)
    (connected l7 l5)
    (= (time-to l7 l5) 1)
    (connected l4 l7)
    (= (time-to l4 l7) 8)
    (connected l5 l7)
    (= (time-to l5 l7) 8)
    (connected l7 l6)
    (= (time-to l7 l6) 1)
    (connected l6 l8)
    (= (time-to l6 l8) 9)
    (connected l8 l6)
    (= (time-to l8 l6) 2)
    (connected l7 l8)
    (= (time-to l7 l8) 9)
    (connected l8 l10)
    (= (time-to l8 l10) 15)
    (connected l10 l8)
    (= (time-to l10 l8) 1)
    (connected l1 l9)
    (= (time-to l1 l9) 1)
    (connected l9 l1)
    (= (time-to l9 l1) 1)
    (connected l10 l11)
    (= (time-to l10 l11) 1)
    (connected l11 l4)
    (= (time-to l11 l4) 5)
    (= (time-to l1 l2) 2)
    (= (time-to l0 l1) 1)
    (= (time-to l1 l3) 3)
    (= (time-to l2 l1) 4)
    (= (time-to l3 l1) 1)
    (= (time-to l2 l3) 5)
    (= (time-to l3 l2) 3)
    (= (time-to l1 l4) 10)
    (= (time-to l2 l4) 4)
    (= (time-to l3 l4) 6)
    (= (time-to l4 l1) 7)
    (= (time-to l4 l2) 3)
    (= (time-to l4 l3) 2)
)
(:goal 

    (and 
        (at t1 l1)
        (picked p1)
        (dropped p1)  
    )
)
(:metric minimize (total-cost))
)