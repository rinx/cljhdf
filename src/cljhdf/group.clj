(ns cljhdf.group
  (:require
   [clojure.spec.alpha :as spec]
   [cljhdf.node :as node])
  (:import
   [io.jhdf.api Group]))

(defn get-children
  [^Group group]
  (->> group
       (.getChildren)))

(defn get-children-map
  [^Group group]
  (->> group
       (get-children)
       (map (fn [[k v]]
              {(keyword k) (node/->map v)}))
       (into {})))

(defn get-by-path
  [^Group group ^String path]
  (-> group
      (.getByPath path)))

(defn get-dataset-by-path
  [^Group group ^String path]
  (-> group
      (.getDatasetByPath path)))

(defn link-creation-order-tracked?
  [^Group group]
  (-> group
      (.isLinkCreationOrderTracked)))
