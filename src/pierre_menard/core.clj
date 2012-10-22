(ns pierre-menard.core
  (:require [clojure.string :as s])
  (:import [java.math BigInteger]
           [java.util Random]))

(def ^:dynamic *dimensions* 10000)

(defn bsc [] (vec (repeatedly 10000 #(rand-int 2))))
(defn bind [x y]
  (vec (map bit-xor x y)))
(def unbind bind)

(defn binary [x] (apply str x))

(defn bundle [x y & zs]
  (let [vs (concat [x y] zs)
        c (/ (count vs) 2)]
    (->> vs
         (apply map +)
         (map #(cond (< c %) 0
                     (> c %) 1
                     :else (rand-int 2))))))
