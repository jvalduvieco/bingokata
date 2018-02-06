(ns bingokata.card-checking-test
  (:require
    [clojure.test :refer :all]
    [bingokata.bingo-card :as bingo-card]))


(deftest checking-a-acard
  (let [a-card {:b [15 9 8 13 1],
                :i [23 25 27 16 30],
                :n [44 31 43 39],
                :g [54 55 56 57 58],
                :o [71 64 74 68 62]}]
       (is (bingo-card/bingo?
             a-card
             [15 9 8 13 1
              23 25 27 16 30
              44 31 43 39
              54 55 56 57 58
              71 64 74 68 62]))
       (is (not
             (bingo-card/bingo?
               a-card
               [15 9 13 1
                44 43 39
                54 55 57 58
                71 64 74 68 62])))
       (is (not
             (bingo-card/bingo?
               a-card
               [])))))
