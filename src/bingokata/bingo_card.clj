(ns bingokata.bingo-card
  (:require
    [bingokata.bingo-card-spec :as card-spec]
    [bingokata.column :as column]
    [clojure.test.check.generators :as gen]
    [clojure.spec.alpha :as s]))

(defn create []
  (gen/generate (s/gen ::card-spec/bingo-card)))

(defn bingo? [card numbers]
  (->> card
       vals
       flatten
       (every? (set numbers))))

