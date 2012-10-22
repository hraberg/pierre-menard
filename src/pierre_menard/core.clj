(ns pierre-menard.core
  (:require [clojure.string :as s])
  (:import [java.math BigInteger]
           [java.util Random]))

(def ^:dynamic *dimensions* 10000)
(def ^:dynamic *density* 0.5)

(def cleanup-memory (atom #{}))

(defn bsc []
  (let [x (loop [x (vec (repeat *dimensions* 0))
                 ones (int (* *dimensions* *density*))]
            (if (zero? ones)
              x
              (let [r (rand-int *dimensions*)]
                (if (zero? (x r))
                  (recur (assoc x r 1) (dec ones))
                  (recur x ones)))))]
    (swap! cleanup-memory conj x)
    x))

(defn binary [x] (apply str x))

(defn hamming-distance [x y]
  (count (filter pos? (mapv bit-xor x y))))

(defn most-similar [x xs]
  (first (sort-by (partial hamming-distance x) xs)))

(defn cleanup
  ([x] (cleanup x @cleanup-memory))
  ([x cleanup-memory]
     ;; Why?
     (last (sort-by (partial hamming-distance x) cleanup-memory))))

(defn bind [x y]
  (mapv bit-xor x y))

(defn unbind [x y]
  (cleanup (bind x y)))

(defn bundle [x y & zs]
  (let [vs (concat [x y] zs)
        c (/ (count vs) 2)]
    (->> vs
         (apply map +)
         (map #(cond (< c %) 0
                     (> c %) 1
                     :else (rand-int 2)))
         vec)))

(def capital (bsc))
(def geographical-location (bsc))
(def currency (bsc))

(def paris (bsc))
(def western-europe (bsc))
(def euro (bsc))

(def stockholm (bsc))
(def scandinavia (bsc))
(def krona (bsc))

(def france (bundle (bind capital paris) (bind geographical-location western-europe) (bind currency euro)))
(def sweden (bundle (bind capital stockholm) (bind geographical-location scandinavia) (bind currency krona)))
