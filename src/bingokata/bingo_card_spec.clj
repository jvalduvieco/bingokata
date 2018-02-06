(ns bingokata.bingo-card-spec
  (:require [clojure.spec.alpha :as s]))

(defn create-column-spec [& {:keys [min max n-elements]}]
  (s/coll-of (s/int-in min max) :kind vector? :count n-elements :distinct true))

(s/def ::b (create-column-spec :min 0 :max 16 :n-elements 5))
(s/def ::i (create-column-spec :min 16 :max 31 :n-elements 5))
(s/def ::n (create-column-spec :min 31 :max 46 :n-elements 4))
(s/def ::g (create-column-spec :min 46 :max 61 :n-elements 5))
(s/def ::o (create-column-spec :min 61 :max 76 :n-elements 5))
(s/def ::bingo-card (s/keys :req-un [::b ::i ::n ::g ::o]))