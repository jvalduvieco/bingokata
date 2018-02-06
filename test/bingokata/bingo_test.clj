(ns bingokata.bingo-test
  (:require [clojure.test :refer :all])
  (:require [bingokata.bingo :refer [create]])
  (:require [bingokata.bingo-card :as card]))

(deftest when_I_generate_a_Bingo_card
  (testing "the generated card has 25 unique spaces"
    (testing "And column B only contains numbers between 1 and 15 inclusive"
      (let [b-column (:b (card/create))]
        (is (> 16 (apply max b-column) 0))))
    (testing "And column B contains 5 numbers"
      (let [b-column (:b (card/create))]
        (is (= 5 (count b-column)))))
    (testing "And column I only contains numbers between 16 and 30 inclusive"
      (let [i-column (:i (card/create))]
        (is (> 31 (apply max i-column) 15))))
    (testing "And column I contains 5 numbers"
      (let [i-column (:i (card/create))]
        (is (= 5 (count i-column)))))
    (testing "And the generated card has 1 FREE space in the middle"
      (let [n-column (:n (card/create))]
        (is (= 4 (count n-column)))))))
