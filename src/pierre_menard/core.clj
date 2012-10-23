(ns pierre-menard.core
  (:require [clojure.string :as s])
  (:import [java.math BigInteger]
           [java.util Random]))

(def ^:dynamic *dimensions* 10000)
(def ^:dynamic *density* 0.5)

;; This should be an auto assocative memory like SDR
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

(defn inverse [x]
  (vec (replace {1 0 0 1} x)))

(defn similar
  ([x xs] (similar x xs first))
  ([x xs which]
     (let [hd (group-by (partial hamming-distance x) xs)]
       (first (hd (which (sort (keys hd))))))))

(defn cleanup
  ([x] (cleanup x @cleanup-memory))
  ([x cleanup-memory]
     ;; Why is last needed/working here?
     (similar x cleanup-memory last)))

(defn bind [x y]
  (let [v (mapv bit-xor x y)]
    (swap! cleanup-memory conj v)
    v))

(defn unbind [x y]
  (cleanup (bind x y)))

(defn interpret [x y]
  (bind (inverse x) y))

(defn bundle [x y & zs]
  (let [vs (concat [x y] zs)
        c (/ (count vs) 2)
        v (->> vs
               (apply map +)
               (map #(cond (< c %) 0
                           (> c %) 1
                           :else (rand-int 2)))
               vec)]
    (swap! cleanup-memory conj v)
    v))

(defn bsc-map [m]
  (apply bundle (map #(apply bind %) m)))

;; examples

(def capital (bsc))
(def geographical-location (bsc))
(def currency (bsc))

(def paris (bsc))
(def western-europe (bsc))
(def euro (bsc))

(def stockholm (bsc))
(def scandinavia (bsc))
(def krona (bsc))

(def mexico-city (bsc))
(def latin-america (bsc))
(def peso (bsc))

(assert (= (unbind (bind capital stockholm) capital)))

(def france (bsc-map {capital paris geographical-location western-europe currency euro}))
(assert (= paris (unbind capital france)))
(assert (= capital (unbind paris france)))

(def sweden (bsc-map {capital stockholm geographical-location scandinavia currency krona}))
(assert (= stockholm (unbind (unbind paris france) sweden)))

(def m (bsc-map {paris stockholm western-europe scandinavia euro krona}))
(assert (= sweden (unbind france m)))
(assert (= stockholm (unbind paris m)))
(assert (= western-europe (unbind scandinavia m)))

(def france-to-sweden (interpret france sweden))
(assert (= krona (unbind france-to-sweden euro)))
(assert (= paris (unbind france-to-sweden stockholm)))

(def mexico (bsc-map {capital mexico-city geographical-location latin-america currency peso}))
(assert (= currency (unbind mexico peso)))

(def mexico-to-france (interpret (interpret mexico sweden) france-to-sweden))
(assert (= euro (unbind mexico-to-france peso)))
(assert (= mexico-city (unbind mexico-to-france paris)))
