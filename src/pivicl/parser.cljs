(ns pivicl.parser
  (:require [clojure.string :as str]
            [cljs.reader :as r]))

(defn float-args [args]
  (map #(js/parseFloat %) args))

(defn kw-args [args]
  (into {} (for [[k w] (partition 2 args)]
             [(keyword k) w])))

(defn read-list [s]
  (r/read-string (str "(" s ")")))

(defn split-arg-types [col]
  (let [[fst & rst] col
        cmd fst
        [args kvs] (split-with number? rst)]
    [cmd args kvs]))

(defn parse-line [l]
  (let [[cmd args kvs] (->> l
                        str/lower-case
                        read-list
                        flatten
                        split-arg-types)]
    {:cmd (keyword cmd)
     :args (float-args args)
     :settings (kw-args kvs)}))
