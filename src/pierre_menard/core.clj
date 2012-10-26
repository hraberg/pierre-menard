(ns pierre-menard.core
  (:require [clojure.walk :as w]
            [clojure.string :as s])
  (:import [clojure.lang Seqable ILookup IFn]))

;; This implements most of (paraphrased):
;; What We Mean When We Say “What’s the Dollar of Mexico?”: Prototypes and Mapping in Concept Space
;; Pentti Kanerva, 2010

;; And some of:
;; Hyperdimensional Computing: An Introduction to Computing in Distributed Representation with High-Dimensional Random Vectors
;; Pentti Kanerva, 2009


(def ^:dynamic *dimensions* 10000)
(def ^:dynamic *density* 0.5)

;; This should probably be a proper auto assocative memory like SDR
(def cleanup-memory (atom #{}))

(defn binary [x] (apply str x))
(declare unbind)

(deftype hvec [vector name]
  Seqable
  (seq [this] (seq vector))
  IFn
  (invoke [this key] (unbind this key))
  ILookup
  (valAt [this key] (this key))
  Object
  (toString [this] (str name ": " (binary (take 20 vector))  " ... " (binary (take-last 20 vector))))
  (equals [this o] (and (instance? hvec o) (= vector (.vector o))))
  (hashCode [this] (.hashCode vector)))

;; Obviously extremely space (and time) inefficient way using one long per bit.
(defn hypervector []
  (loop [x (vec (repeat *dimensions* 0))
         ones (int (* *dimensions* *density*))]
    (if (zero? ones)
      x
      (let [r (rand-int *dimensions*)]
        (if (zero? (x r))
          (recur (assoc x r 1) (dec ones))
          (recur x ones))))))

(defn create-hv [name]
  (let [v (hvec. (hypervector) name)]
    (swap! cleanup-memory conj v)
    v))
(alter-var-root #'create-hv memoize)

(defmacro hv [name]
  `(create-hv '~name))

(defn hamming-distance [x y]
  (count (filter pos? (mapv bit-xor x y))))

(defn inverse [x]
  (hvec. (mapv #(bit-and 1 (bit-not %)) x) (.name x)))
(def ¬ inverse)

(defn similar [x xs]
  (let [hd (group-by (partial hamming-distance x) xs)]
    (first (hd (first (sort (keys hd)))))))

(defn by-similarity
  ([x] (by-similarity x @cleanup-memory))
  ([x xs]
     (let [hd (group-by (partial hamming-distance x) xs)]
       (map #(vec [(-> % hd first) %]) (sort (keys hd))))))

(defn cleanup
  ([x] (cleanup x @cleanup-memory))
  ([x cleanup-memory]
     (if-let [exact (cleanup-memory x)]
       exact
       (similar (inverse x) cleanup-memory))))

(defn merge-name [xs]
  (s/join "-" (map #(.name %) xs)))

(defn bind [x y]
  (hvec. (mapv bit-xor x y) (merge-name [x y])))
(def ⊗ bind)

(defn unbind [x y]
  (cleanup (bind x y)))

(defn interpret [x y]
  (bind (¬ x) y))
(def ¬⊗ interpret)

(defn group [& xs]
  (let [c (/ (count xs) 2)
        v (->> xs
               (apply mapv +)
               (mapv #(cond (< c %) 0
                            (> c %) 1
                            :else (rand-int 2)))
               vec)]
    (hvec. v (merge-name xs))))
(def ++ group)

(defn hypervector-map [m]
  (apply group (map #(apply bind %) m)))

(defmacro defhv
  ([name]
     `(defhv ~name (hypervector)))
  ([name init]
     `(def ~name (let [x# (hvec. ~(w/postwalk #(cond
                                                 (map? %) (list 'hypervector-map %)
                                                 (and (symbol? %) (.startsWith (clojure.core/name %) "?")) (list 'hv %)
                                                 :else %) init) '~name)]
                   (swap! cleanup-memory conj x#)
                   x#))))

;; Examples

(defhv capital)
(defhv geographical-location)
(defhv currency)

(defhv paris)
(defhv western-europe)
(defhv euro)

(defhv stockholm)
(defhv scandinavia)
(defhv krona)

(defhv mexico-city)
(defhv latin-america)
(defhv peso)

(assert (= stockholm ((⊗ capital stockholm) capital)))

(defhv france {capital paris geographical-location western-europe currency euro})
(assert (= paris (france capital)))
(assert (= capital (paris france)))

(defhv sweden {capital stockholm geographical-location scandinavia currency krona})
(assert (= stockholm (-> paris france sweden)))

(defhv holistic-mapping {paris stockholm western-europe scandinavia euro krona})
(assert (= sweden (holistic-mapping france)))
(assert (= stockholm (holistic-mapping paris)))
(assert (= western-europe (scandinavia holistic-mapping)))

(assert (= krona (-> france sweden euro)))
(defhv france-to-sweden (¬⊗ france sweden))
(assert (= krona (france-to-sweden euro)))
(assert (= paris (france-to-sweden stockholm)))

(defhv mexico {capital mexico-city geographical-location latin-america currency peso})
(assert (= currency (mexico peso)))

(defhv mexico-to-france (¬⊗ (¬⊗ mexico sweden) france-to-sweden))
(assert (= euro (mexico-to-france peso)))
(assert (= mexico-city (mexico-to-france paris)))

(defhv mother)
(defhv father)
(defhv child)
(defhv grandmother)
(defhv grandchild)

(defhv x)
(defhv y)
(defhv z)

(defhv mother-of {mother x child y})
(defhv father-of {father y child z})

(defhv grandmother-of (⊗ {grandmother x grandchild z} (++ mother-of father-of)))

(defhv anna)
(defhv bill)
(defhv cid)

(defhv anna-is-the-mother-of-bill {mother anna child bill})
(assert (= anna (anna-is-the-mother-of-bill mother)))
(assert (= bill (anna-is-the-mother-of-bill child)))

(assert (= anna (x (¬⊗ mother-of anna-is-the-mother-of-bill))))
(assert (= bill (y (¬⊗ mother-of anna-is-the-mother-of-bill))))

(defhv bill-is-the-father-of-cid {father bill child cid})
(assert (= father (bill-is-the-father-of-cid bill)))
(assert (= child (bill-is-the-father-of-cid cid)))

;; This is unstable
(defhv anna-is-the-grandmother-of-cid (⊗ grandmother-of (++ {mother anna child bill} {father bill child cid})))
(assert (= grandmother (anna-is-the-grandmother-of-cid anna)))
(println (by-similarity cid))
(println (by-similarity grandchild))
(println (by-similarity anna-is-the-grandmother-of-cid))
(println (anna-is-the-grandmother-of-cid cid))
(println (by-similarity (bind anna-is-the-grandmother-of-cid cid)))
(println (count (by-similarity (bind anna-is-the-grandmother-of-cid cid))) (count @cleanup-memory))
(assert (= grandchild (anna-is-the-grandmother-of-cid cid)))
