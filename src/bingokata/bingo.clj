(ns bingokata.bingo)

(defn call-number [{[first & rest] :available-balls}]
  {:current first :available-balls rest})

(defn create []
  {:available-balls (-> (range 1 76)
                        shuffle)})