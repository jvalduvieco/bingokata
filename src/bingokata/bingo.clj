(ns bingokata.bingo)

(defn call-number [{:keys [available-balls]}]
  {:current (first available-balls)
   :available-balls (rest available-balls)})

(defn create []
  {:available-balls (-> (range 1 76)
                        shuffle)})

(defn turn [game]
  (let [{:keys [current available-balls]} (call-number game)]
    (println current)
    (assoc game :available-balls available-balls)))
