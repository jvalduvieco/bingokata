(ns bingokata.column)

(defn create [start end size]
  (->>
    (range start end)
    shuffle
    (take size)))