(ns build
  (:require
   [clojure.tools.build.api :as b]
   [org.corfield.build :as bb] ; https://github.com/seancorfield/build-clj
 ))


(def lib 'org.pinkgorilla/ui-sparklines)
(def version (format "0.0.%s" (b/git-count-revs nil)))

(defn jar "build the JAR" [opts]
  (println "Building the JAR")
  (-> opts
      (assoc :lib lib
             :version version
             :transitive true)
      (bb/jar)))


(defn deploy "Deploy the JAR to Clojars." [opts]
  (println "Deploying to Clojars.")
  (-> opts
      (assoc :lib lib 
             :version version)
      (bb/deploy)))
