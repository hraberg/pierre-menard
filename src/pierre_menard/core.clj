(ns pierre-menard.core
  (:require [clojure.string :as s])
  (:import [java.math BigInteger]
           [java.util Random]))

(def ^:private rnd (Random.))
(def ^:dynamic *dimensions* 10000)

(defn bsc [] (BigInteger. *dimensions* rnd))

(defn binary [x]
  (let [s (.toString x 2)]
    (str (apply str (repeat (- *dimensions* (count s)) 0)) s)))

(defn bind [x y] (.xor x y))
(def unbind bind)

(defn ^:private bsc-vec [^BigInteger x]
  (loop [idx 0 acc []]
    (if (= idx *dimensions*)
      acc
      (recur (inc idx) (assoc acc idx (if (.testBit x idx) 1 0))))))

(defn ^:private bundle-vec [^BigInteger x]
  (loop [idx 0 acc []]
    (if (= idx *dimensions*)
      acc
      (recur (inc idx) (assoc acc idx (if (.testBit x idx) 1 0))))))

(defn bundle [x y & zs]
  (let [vs (concat [x y] zs)
        c (/ (count vs) 2)
        v (->> vs
               (map bsc-vec)
               (apply map +)
               (map #(cond (< c %) 0
                           (> c %) 1
                           :else (rand-int 2))))]
    (BigInteger. (apply str v) 2)))
