(ns cljhdf.attribute
  (:require
   [clojure.spec.alpha :as spec])
  (:import
   [io.jhdf.api Attribute])
  (:refer-clojure :exclude [empty?]))

(defn get-node
  [^Attribute attr]
  (-> attr
      (.getNode)))

(defn get-name
  [^Attribute attr]
  (-> attr
      (.getName)))

(defn get-size
  [^Attribute attr]
  (-> attr
      (.getSize)))

(defn get-disk-size
  [^Attribute attr]
  (-> attr
      (.getDiskSize)))

(defn get-dimensions
  [^Attribute attr]
  (-> attr
      (.getDimensions)
      (seq)))

(defn get-data
  [^Attribute attr]
  (-> attr
      (.getData)))

(defn get-java-type
  [^Attribute attr]
  (-> attr
      (.getJavaType)))

(defn scalar?
  [^Attribute attr]
  (-> attr
      (.isScalar)))

(defn empty?
  [^Attribute attr]
  (-> attr
      (.isEmpty)))

(defn get-buffer
  [^Attribute attr]
  (-> attr
      (.getBuffer)))

(defn ->map
  [^Attribute attr]
  {:node (get-node attr)
   :name (get-name attr)
   :size (get-size attr)
   :disk-size (get-disk-size attr)
   :dimensions (get-dimensions attr)
   :data (get-data attr)
   :java-type (get-java-type attr)
   :scalar? (scalar? attr)
   :empty? (empty? attr)
   :buffer (get-buffer attr)})

(defn ->clj
  [attr]
  (let [attr (if (map? attr)
               attr
               (->map attr))
        dims (:dimensions attr)
        java-type (:java-type attr)
        class (-> (partial make-array java-type)
                  (apply dims)
                  (class))]
    (->> (:data attr)
         (cast class)
         (seq))))
