(ns bingokata.bingo-card
  (:require [bingokata.column :as column]))

(defn create []
  {:b (column/create 1 16 5)
   :i (column/create 16 31 5)
   :n (column/create 31 46 4)
   :g (column/create 46 61 5)
   :o (column/create 61 76 5)})