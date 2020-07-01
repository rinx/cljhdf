(ns cljhdf.node
  (:require
   [clojure.spec.alpha :as spec]
   [cljhdf.attribute :as attr])
  (:import
   [io.jhdf.api Node]))

(defn get-parent
  [^Node node]
  (-> node
      (.getParent)))

(defn get-name
  [^Node node]
  (-> node
      (.getName)))

(defn get-path
  [^Node node]
  (-> node
      (.getPath)))

(defn get-attributes
  [^Node node]
  (->> node
      (.getAttributes)))

(defn get-attributes-map
  [^Node node]
  (->> node
      (get-attributes)
       (map (fn [[k v]]
              {(keyword k) (attr/->map v)}))
       (into {})))

(defn get-type
  [^Node node]
  (-> node
      (.getType)))

(defn group?
  [^Node node]
  (-> node
      (.isGroup)))

(defn get-file
  [^Node node]
  (-> node
      (.getFile)))

(defn get-hdf-file
  [^Node node]
  (-> node
      (.getHdfFile)))

(defn get-address
  [^Node node]
  (-> node
      (.getAddress)))

(defn link?
  [^Node node]
  (-> node
      (.isLink)))

(defn attribute-creation-order-tracked?
  [^Node node]
  (-> node
      (.isAttributeCreationOrderTracked)))

(defn ->map
  [^Node node]
  {:parent (get-parent node)
   :name (get-name node)
   :path (get-path node)
   :attributes (get-attributes-map node)
   :type (get-type node)
   :group? (group? node)
   :file (get-file node)
   :hdf-file (get-hdf-file node)
   :address (get-address node)
   :link? (link? node)
   :attribute-creation-order-tracked? (attribute-creation-order-tracked? node)})
