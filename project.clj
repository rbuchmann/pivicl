(defproject pivicl "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.7.0-beta3"]
                 [org.clojure/clojurescript "0.0-3269"]
                 [org.clojure/core.async "0.1.346.0-17112a-alpha"]
                 [hiccups "0.3.0"]]

  :plugins [[lein-cljsbuild "1.0.6"]]
  :cljsbuild {:builds
              {:main {:source-paths ["src"]
                      :compiler {:output-to "main.js"
                                 :optimizations :simple
                                 :target :nodejs}}}}
  :profiles {:dev {:plugins [[com.cemerick/austin "0.1.6"]]}})
