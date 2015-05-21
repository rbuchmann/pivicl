(ns pivicl.parser
  (:require [clojure.string :as str]))

(defn float-args [args]
  (map #(js/parseFloat %) args))

(defn kw-args [args]
  (into {} (for [[k w] (partition 2 args)]
             [(keyword k) w])))

(def floaty-cmds [:circle :circles
                  :line :lines
                  :point :points
                  :polyline])

(def argparsers (into {:set kw-args} (for [cmd floaty-cmds] [cmd float-args])))

(defn parse-line [l]
  (let [[cmd & args] (-> l
                         str/lower-case
                         (str/replace #"\(|\)|," " ")
                         (str/split #"\s+"))
        cmd (keyword cmd)
        argparse (get argparsers cmd identity)
        args (argparse args)]
    {:cmd cmd
     :args args}))
