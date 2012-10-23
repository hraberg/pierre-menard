(ns pierre-menard.core
  (:require [clojure.walk :as w]))

;; This implements most of (paraphrased):
;; What We Mean When We Say “What’s the Dollar of Mexico?”: Prototypes and Mapping in Concept Space
;; Pentti Kanerva, 2010

;; And some of:
;; Hyperdimensional Computing: An Introduction to Computing in Distributed Representation with High-Dimensional Random Vectors
;; Pentti Kanerva, 2009


(def ^:dynamic *dimensions* 10000)
(def ^:dynamic *density* 0.5)

;; This should be an auto assocative memory like SDR
(def cleanup-memory (atom #{}))

(defn hypervector []
  (loop [x (vec (repeat *dimensions* 0))
         ones (int (* *dimensions* *density*))]
    (if (zero? ones)
      x
      (let [r (rand-int *dimensions*)]
        (if (zero? (x r))
          (recur (assoc x r 1) (dec ones))
          (recur x ones))))))

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
     (if-let [exact (cleanup-memory x)]
       exact
       (similar x cleanup-memory last))))

(defn merge-name [xs]
  (or (apply merge-with #(symbol (str %1 "-" %2)) (map meta xs)) {}))

(defn bind [x y]
  (with-meta (mapv bit-xor x y) (merge-name [x y])))

(defn unbind [x y]
  (cleanup (bind x y)))

(defn unbind-symbol [x y]
  (-> (unbind x y) meta :name))

(defn interpret [x y]
  (bind (inverse x) y))

(defn bundle [& xs]
  (let [c (/ (count xs) 2)
        v (->> xs
               (apply mapv +)
               (mapv #(cond (< c %) 0
                            (> c %) 1
                            :else (rand-int 2)))
               vec)]
    (with-meta v (merge-name xs))))

(defn hypervector-map [m]
  (apply bundle (map #(apply bind %) m)))

(defmacro defhv
  ([name]
     `(defhv ~name (hypervector)))
  ([name init]
     `(def ~name (let [x# (with-meta ~(w/postwalk #(if (map? %)
                                                     (list 'hypervector-map %)
                                                     %) init)
                            {:name '~name})]
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

(assert (= stockholm (unbind (bind capital stockholm) capital)))

(defhv france {capital paris geographical-location western-europe currency euro})
(assert (= paris (unbind capital france)))
(assert (= capital (unbind paris france)))

(defhv sweden {capital stockholm geographical-location scandinavia currency krona})
(assert (= stockholm (unbind (unbind paris france) sweden)))

(defhv m {paris stockholm western-europe scandinavia euro krona})
(assert (= sweden (unbind france m)))
(assert (= stockholm (unbind paris m)))
(assert (= western-europe (unbind scandinavia m)))

(defhv france-to-sweden (interpret france sweden))
(assert (= krona (unbind france-to-sweden euro)))
(assert (= paris (unbind france-to-sweden stockholm)))

(defhv mexico {capital mexico-city geographical-location latin-america currency peso})
(assert (= currency (unbind mexico peso)))

(defhv mexico-to-france (interpret (interpret mexico sweden) france-to-sweden))
(assert (= euro (unbind mexico-to-france peso)))
(assert (= mexico-city (unbind mexico-to-france paris)))

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

(defhv grandmother-of (interpret {grandmother x grandchild z} (bundle mother-of father-of)))

(defhv anna)
(defhv bill)
(defhv cid)

(defhv anna-is-the-mother-of-bill {mother anna child bill})
(assert (= anna (unbind anna-is-the-mother-of-bill mother)))
(assert (= bill (unbind anna-is-the-mother-of-bill child)))

(assert (= anna (unbind x (interpret mother-of anna-is-the-mother-of-bill))))
(assert (= bill (unbind y (interpret mother-of anna-is-the-mother-of-bill))))

(defhv bill-is-the-father-of-cid {father bill child cid})
(assert (= father (unbind bill-is-the-father-of-cid bill)))
(assert (= child (unbind bill-is-the-father-of-cid cid)))

;; This is unstable
(defhv anna-is-the-grandmother-of-cid (interpret grandmother-of (bundle {mother anna child bill} {father bill child cid})))
(assert (= grandmother (unbind anna-is-the-grandmother-of-cid anna)))
(assert (= grandchild (unbind anna-is-the-grandmother-of-cid cid)))
