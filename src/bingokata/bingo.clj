(ns bingokata.bingo
  (:require [bingokata.bingo-card :as card]))

(defn call-number [{:keys [available-balls]}]
  {:current (first available-balls)
   :available-balls (rest available-balls)})

(defn create []
  {:available-balls (-> (range 1 76)
                        shuffle)})

(defn turn [game]
  (let [{:keys [current available-balls]} (call-number game)]
    (println current)
    (assoc game :available-balls available-balls :called-balls (conj (:called-balls game) current))))

(defn winner? [game card]
  (card/bingo? card (:called-balls game)))

(defn end-of-game? [game]
  (some (partial winner? game) (:players game)))

(defn play [game]
  (let [result (first (drop-while (complement end-of-game?)
                                  (iterate turn game)))]
    (println "BINGO!")
    (println (map (partial winner? result) (:players result)))
    result))
