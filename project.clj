(defproject cljhdf #=(clojure.string/trim #=(slurp "CLJHDF_VERSION"))
  :description "A clojure library for accessing HDF5 files."
  :url "http://github.com/rinx/cljhdf"
  :license {:name "EPL-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  :deploy-repositories [["clojars" {:sign-releases false :url "https://clojars.org/repo"}]]
  :dependencies [[org.clojure/clojure "1.10.2-alpha1"]
                 [org.clojure/spec.alpha "0.2.187"]
                 [io.jhdf/jhdf "0.5.7"]])
