(ns bingokata.bingo-column-spec-test
  (:require [clojure.test :refer :all])
  (:require [bingokata.bingo-card-spec :as card-spec])
  (:require [clojure.spec.alpha :as s]))

(s/def ::arbitrary-bingo-column (card-spec/create-column-spec :min 0 :max 16 :n-elements 5))

(deftest when_i_call_a_number_75_times_then_all_numbers_between_1_and_75_are_present_and_there_are_no_repeats
  (testing "calling get-number repeatedly does not repeat numbers"
    (is (s/valid? ::arbitrary-bingo-column [1 2 3 4 5]))
    (is (not (s/valid? ::arbitrary-bingo-column [1 1 2 3 4])))
    (is (not (s/valid? ::arbitrary-bingo-column [-1 1 2 3 4])))
    (is (not (s/valid? ::arbitrary-bingo-column [1 2 3 4 16])))
    (is (not (s/valid? ::arbitrary-bingo-column [1 2 3 4])))
    (is (not (s/valid? ::arbitrary-bingo-column  {:a 4})))
    (is (not (s/valid? ::arbitrary-bingo-column  4)))))




