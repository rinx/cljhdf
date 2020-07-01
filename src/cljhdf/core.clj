(ns cljhdf.core
  (:require
   [clojure.spec.alpha :as spec]
   [cljhdf.attribute :as attr]
   [cljhdf.dataset :as ds]
   [cljhdf.node :as node])
  (:import
   [java.io File]
   [io.jhdf HdfFile]
   [io.jhdf.api Dataset]))

(set! *warn-on-reflection* true)

(defn open-hdf5
  [^File file]
  (HdfFile. file))

(defn open
  [^String path]
  (-> (File. path)
      (open-hdf5)))

(defn close
  [^HdfFile hdf]
  (-> hdf
      (.close)))

(defn get-name
  [^HdfFile hdf]
  (-> hdf
      (.getName)))

(defn get-path
  [^HdfFile hdf]
  (-> hdf
      (.getPath)))

(defn get-attributes
  [^HdfFile hdf]
  (->> hdf
       (.getAttributes)))

(defn get-attributes-map
  [^HdfFile hdf]
  (->> hdf
       (get-attributes)
       (map (fn [[k v]]
              {(keyword k) (attr/->map v)}))
       (into {})))

(defn get-children
  [^HdfFile hdf]
  (->> hdf
       (.getChildren)))

(defn get-children-map
  [^HdfFile hdf]
  (->> hdf
       (get-children)
       (map (fn [[k v]]
              {(keyword k) (node/->map v)}))
       (into {})))

(defn get-by-path
  [^HdfFile hdf ^String path]
  (-> hdf
      (.getByPath path)))

(defn get-dataset-by-path
  [^HdfFile hdf ^String path]
  (-> hdf
      (.getDatasetByPath path)))

(defn size
  [^HdfFile hdf]
  (-> hdf
      (.size)))

(comment
  (def hdf
    (open "/root/works/hdf5s/fashion-mnist-784-euclidean.hdf5"))

  (get-name hdf)
  (get-path hdf)

  (get-attributes hdf)
  (get-attributes-map hdf)
  (get-children hdf)
  (get-children-map hdf)

  (->> (get-dataset-by-path hdf "test")
       (ds/->clj)
       (take 3)
       (map seq))

  (size hdf)
  (close hdf)
  )
