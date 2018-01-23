(ns bingokata.core-test
  (:require [clojure.test :refer :all]
            [bingokata.core :refer :all]
            [clojure.pprint :refer :all]))

(defn get-number [{[first & rest] :available-balls}]
  {:current first :available-balls rest})

(deftest given_a_bingo_caller_when_I_call_a_number_then_the_number_is_between_1_and_75
  (testing "get-number result is bigger than 0"
    (is (< 0 (:current (get-number {:available-balls [1]})))))
  (testing "get-number result is smaller than 76"
    (is (> 76 (:current (get-number {:available-balls [2]}))))))

(deftest when_i_call_a_number_75_times_then_all_numbers_between_1_and_75_are_present_and_there_are_no_repeats
  (testing "calling get-number repeatedly does not repeat numbers"
    (let [initial-state {:available-balls [1 2]}
          first-state (get-number initial-state)
          second-state (get-number first-state)]
         (is (not= (:current first-state) (:current second-state)))))

  (testing "calling get-numer 75 times must not repeat numbers"
    (let [
          initial-state {:available-balls (range 1 76) :called-balls #{}}
          f-state (fn [state i]
                    (let
                      [ result (get-number state)]
                      (assoc result :called-balls (conj (:called-balls state) (:current result)))))

          final-state (reduce f-state  initial-state (range 1 76))]
         (pprint final-state)
         (is (= (count
                  (:called-balls final-state))
                75)))))


