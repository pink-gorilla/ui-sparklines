(ns build
  (:require
   [clojure.tools.build.api :as b]
   [org.corfield.build :as bb] ; https://github.com/seancorfield/build-clj
 ))


(def lib 'org.pinkgorilla/ui-sparklines)
(def version (format "0.0.%s" (b/git-count-revs nil)))

(def pom-template
  [[:licenses
    [:license
     [:name "Eclipse Public License"]
     [:url "https://www.eclipse.org/legal/epl-v10.html"]]]
   [:developers
    [:developer
     [:name "pink-gorilla"]]]
   [:scm
    [:url "https://github.com/pink-gorilla/ui-sparklines/"]
    [:connection "scm:git:git://github.com/pink-gorilla/ui-sparklines.git"]
    [:developerConnection "scm:git:ssh://git@github.com/pink-gorilla/ui-sparklines.git"]]])


(defn jar "build the JAR" [opts]
  (println "Building the JAR")
  (-> opts
      (assoc :lib lib
             :version version
             :pom-data pom-template
             :transitive true)
      (bb/jar)))


(defn deploy "Deploy the JAR to Clojars." [opts]
  (println "Deploying to Clojars.")
  (-> opts
      (assoc :lib lib 
             :version version)
      (bb/deploy)))
