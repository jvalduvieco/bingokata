(ns bingokata.game-test
  (:require
    [clojure.test :refer :all]
    [bingokata.bingo :as bingo]))

(deftest a-turn
  (let [game {:players [{:b [13 11 3 10 9],
                         :i [26 23 24 25 27],
                         :n [44 33 38 41],
                         :g [60 58 59 56 55],
                         :o [68 63 73 74 71]}
                        {:b [14 15 13 9 3],
                         :i [23 30 17 24 28],
                         :n [44 41 45 32],
                         :g [59 53 46 54 56],
                         :o [70 66 69 75 74]}]
              :available-balls [1]
              :called-balls []}]
    (is (= "1\n" (with-out-str
                   (is (= [] (:available-balls (bingo/turn game)))))))
    (is (= "2\n" (with-out-str
                   (is (= [3] (:available-balls (bingo/turn (assoc game :available-balls [2 3]))))))))
    (is (= "2\n" (with-out-str
                   (is (= [2] (:called-balls (bingo/turn (assoc game :available-balls [2 3]))))))))
    (is (= "13\n11\n3\n10\n9\n26\n23\n24\n25\n27\n44\n33\n38\n41\n60\n58\n59\n56\n55\n68\n63\n73\n74\n71\n"
           (with-out-str (is (= [] (:available-balls (first (drop 24 (iterate
                                                                       bingo/turn
                                                                       (assoc game :available-balls [13 11 3 10 9 26 23 24 25 27 44 33 38 41 60 58 59 56 55 68 63 73 74 71]))))))))))
    (is (= "13\n11\n3\n10\n9\n26\n23\n24\n25\n27\n44\n33\n38\n41\n60\n58\n59\n56\n55\n68\n63\n73\n74\n71\nBINGO!\n(true false)\n"
           (with-out-str (is (= [] (:available-balls (bingo/play (assoc game :available-balls [13 11 3 10 9 26 23 24 25 27 44 33 38 41 60 58 59 56 55 68 63 73 74 71]))))))))))

