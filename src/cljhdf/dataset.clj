(ns cljhdf.dataset
  (:require
   [clojure.spec.alpha :as spec]
   [cljhdf.node :as node])
  (:import
   [io.jhdf.api Dataset])
  (:refer-clojure :exclude [empty?]))

(defn get-size
  [^Dataset ds]
  (-> ds
      (.getSize)))

(defn get-disk-size
  [^Dataset ds]
  (-> ds
      (.getDiskSize)))

(defn get-dimensions
  [^Dataset ds]
  (-> ds
      (.getDimensions)
      (seq)))

(defn scalar?
  [^Dataset ds]
  (-> ds
      (.isScalar)))

(defn empty?
  [^Dataset ds]
  (-> ds
      (.isEmpty)))

(defn compound?
  [^Dataset ds]
  (-> ds
      (.isCompound)))

(defn variable-length?
  [^Dataset ds]
  (-> ds
      (.isVariableLength)))

(defn get-max-size
  [^Dataset ds]
  (-> ds
      (.getMaxSize)
      (seq)))

(defn get-data-layout
  [^Dataset ds]
  (-> ds
      (.getDataLayout)))

(defn get-data
  [^Dataset ds]
  (-> ds
      (.getData)))

(defn get-java-type
  [^Dataset ds]
  (-> ds
      (.getJavaType)))

(defn get-data-type
  [^Dataset ds]
  (-> ds
      (.getDataType)))

(defn get-fill-value
  [^Dataset ds]
  (-> ds
      (.getFillValue)))

(defn ->map
  [^Dataset ds]
  {:size (get-size ds)
   :disk-size (get-disk-size ds)
   :dimensions (get-dimensions ds)
   :scalar? (scalar? ds)
   :empty? (empty? ds)
   :compound? (compound? ds)
   :variable-length? (variable-length? ds)
   :max-size (get-max-size ds)
   :data-layout (get-data-layout ds)
   :data (get-data ds)
   :java-type (get-java-type ds)
   :data-type (get-data-type ds)
   :fill-value (get-fill-value ds)})

(defn ->clj
  [ds]
  (let [ds (if (map? ds)
             ds
             (->map ds))
        dims (:dimensions ds)
        java-type (:java-type ds)
        class (-> (partial make-array java-type)
                  (apply dims)
                  (class))]
    (->> (:data ds)
         (cast class)
         (seq))))
