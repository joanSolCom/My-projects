; Unsolvable problem


(define (problem p)
(:domain hitchhikers)
(:objects
    t1 - car
    p1 - person
    p2 - person
    p3 - person
    l0 - location
    l1 - location
    l2 - location
    l3 - location
    l4 - location
)

(:init
    (= (total-cost) 0)
    (at t1 l0)
    (free t1)
    (at p1 l1)
    (at p2 l2)
    (at p3 l3)
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
        (at t1 l0)
        (picked p1)
        (dropped p1)
        (picked p2)
        (dropped p2)
        (picked p3)
        (dropped p3)  
    )
)
(:metric minimize (total-cost))
)