(ns pierre-menard.core)

;; This implements most of:
;; What We Mean When We Say “What’s the Dollar of Mexico?”: Prototypes and Mapping in Concept Space
;; Pentti Kanerva, 2010

(def ^:dynamic *dimensions* 10000)
(def ^:dynamic *density* 0.5)

;; This should be an auto assocative memory like SDR
(def cleanup-memory (atom #{}))

(defn bsc []
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

(defn bsc-map [m]
  (apply bundle (map #(apply bind %) m)))

(defmacro defb
  ([name]
     `(defb ~name (bsc)))
  ([name init]
     `(def ~name (let [x# (with-meta ~(if (map? init)
                                        (list 'bsc-map init)
                                        init)
                            {:name '~name})]
                   (swap! cleanup-memory conj x#)
                   x#))))

;; Examples

(defb capital)
(defb geographical-location)
(defb currency)

(defb paris)
(defb western-europe)
(defb euro)

(defb stockholm)
(defb scandinavia)
(defb krona)

(defb mexico-city)
(defb latin-america)
(defb peso)

(assert (= stockholm (unbind (bind capital stockholm) capital)))

(defb france {capital paris geographical-location western-europe currency euro})
(assert (= paris (unbind capital france)))
(assert (= capital (unbind paris france)))

(defb sweden {capital stockholm geographical-location scandinavia currency krona})
(assert (= stockholm (unbind (unbind paris france) sweden)))

(defb m {paris stockholm western-europe scandinavia euro krona})
(assert (= sweden (unbind france m)))
(assert (= stockholm (unbind paris m)))
(assert (= western-europe (unbind scandinavia m)))

(defb france-to-sweden (interpret france sweden))
(assert (= krona (unbind france-to-sweden euro)))
(assert (= paris (unbind france-to-sweden stockholm)))

(defb mexico {capital mexico-city geographical-location latin-america currency peso})
(assert (= currency (unbind mexico peso)))

(defb mexico-to-france (interpret (interpret mexico sweden) france-to-sweden))
(assert (= euro (unbind mexico-to-france peso)))
(assert (= mexico-city (unbind mexico-to-france paris)))
