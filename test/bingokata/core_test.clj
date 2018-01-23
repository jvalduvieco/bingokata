(ns bingokata.core-test
  (:require [clojure.test :refer :all]
            [bingokata.core :refer :all]
            [clojure.pprint :refer :all]))

(defn get-number [{[first & rest] :available-balls}]
  {:current first :available-balls rest})

(defn create-game []
  {:available-balls (-> (range 1 76)
                        shuffle)
   })

(deftest given_a_bingo_caller_when_I_call_a_number_then_the_number_is_between_1_and_75
  (testing "get-number result is bigger than 0"
    (is (< 0 (:current (get-number (create-game))))))
  (testing "get-number result is smaller than 76"
    (is (> 76 (:current (get-number (create-game)))))))

(deftest when_i_call_a_number_75_times_then_all_numbers_between_1_and_75_are_present_and_there_are_no_repeats
  (testing "calling get-number repeatedly does not repeat numbers"
    (let [initial-state (create-game)
          first-state (get-number initial-state)
          second-state (get-number first-state)]
      (is (not= (:current first-state) (:current second-state)))))

  (testing "calling get-numer 75 times must not repeat numbers"
    (let [
          initial-state (assoc (create-game) :called-balls #{})
          f-state (fn [state i]
                    (let
                      [result (get-number state)]
                      (assoc result :called-balls (conj (:called-balls state) (:current result)))))

          final-state (reduce f-state initial-state (range 1 76))]
      (pprint final-state)
      (is (= (count
               (:called-balls final-state))
             75)))))

(deftest when_i_start_a_game
  (testing "there are 75 balls available"
    (is (= 75 (count (:available-balls (create-game))))))
  (testing "is different from another one"
    (is (not= (create-game) (create-game)))))

(defn create-column [start end size]
  (->>
    (range start end)
    shuffle
    (take size)))

(defn create-bingo-card []
  {:B (create-column 1 16 5)
   :I (create-column 16 31 5)
   :N (create-column 31 46 4)
   :G (create-column 46 61 5)
   :O (create-column 61 76 5)})

(deftest when_I_generate_a_Bingo_card
  (testing "the generated card has 25 unique spaces"
    (testing "And column B only contains numbers between 1 and 15 inclusive"
      (let [b-column (:B (create-bingo-card))]
        (is (> 16 (apply max b-column) 0))))
    (testing "And column B contains 5 numbers"
      (let [b-column (:B (create-bingo-card))]
        (is (= 5 (count b-column)))))
    (testing "And column I only contains numbers between 16 and 30 inclusive"
      (let [i-column (:I (create-bingo-card))]
        (is (> 31 (apply max i-column) 15))))
    (testing "And column I contains 5 numbers"
      (let [i-column (:I (create-bingo-card))]
        (is (= 5 (count i-column)))))
    (testing "And the generated card has 1 FREE space in the middle"
      (let [n-column (:N (create-bingo-card))]
        (is (= 4 (count n-column)))))))