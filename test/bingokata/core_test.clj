(ns bingokata.core-test
  (:require [clojure.test :refer :all]
            [bingokata.core :refer :all]
            [bingokata.bingo :as game]
            [clojure.pprint :refer :all]
            [clojure.spec.alpha :as s]))

(deftest given_a_bingo_caller_when_I_call_a_number_then_the_number_is_between_1_and_75
  (testing "game/call-number result is bigger than 0"
    (is (< 0 (:current (game/call-number (game/create))))))
  (testing "game/call-number result is smaller than 76"
    (is (> 76 (:current (game/call-number (game/create)))))))

(deftest when_i_call_a_number_75_times_then_all_numbers_between_1_and_75_are_present_and_there_are_no_repeats
  (testing "calling game/call-number repeatedly does not repeat numbers"
    (let [initial-state (game/create)
          first-state (game/call-number initial-state)
          second-state (game/call-number first-state)]
      (is (not= (:current first-state) (:current second-state)))))

  (testing "calling game/call-number 75 times must not repeat numbers"
    (let [
          initial-state (assoc (game/create) :called-balls #{})
          f-state (fn [state i]
                    (let
                      [result (game/call-number state)]
                      (assoc result :called-balls (conj (:called-balls state) (:current result)))))

          final-state (reduce f-state initial-state (range 1 76))]
      (is (= (count
               (:called-balls final-state))
             75)))))

(deftest when_i_start_a_game
    (testing "there are 75 balls available"
      (is (= 75 (count (:available-balls (game/create))))))
    (testing "is different from another one"
      (is (not= (game/create) (game/create)))))

