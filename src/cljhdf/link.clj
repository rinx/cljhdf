(ns cljhdf.link
  (:require
   [clojure.spec.alpha :as spec])
  (:import
   [io.jhdf.api Link]))

(defn get-target
  [^Link link]
  (-> link
      (.getTarget)))

(defn get-target-path
  [^Link link]
  (-> link
      (.getTargetPath)))

(defn broken-link?
  [^Link link]
  (-> link
      (.isBrokenLink)))
