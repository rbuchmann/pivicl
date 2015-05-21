(ns pivicl.core
  (:require-macros [cljs.core.async.macros :refer [go go-loop]])
  (:require [cljs.nodejs :as node]
            [cljs.core.async :refer [<! >! put! chan buffer] :as async]
            [pivicl.parser :as p]
            [pivicl.svg :as svg]))

(node/enable-util-print!)

(def readline (node/require "readline"));

(defn stdin->chan [c]
  (doto (.createInterface readline
                          (clj->js {:input (.-stdin js/process)
                                    :terminal false}))
    (.on "line" #(put! c %))
    (.on "close" #(async/close! c)))
  c)

(defn -main [& args]
  (let [stdin (stdin->chan (chan (buffer 1) (map p/parse-line)))]
    (go (let [cmd-list (<! (async/into [] stdin))]
          #_(println cmd-list)
          (println (svg/svg cmd-list))))))

(set! *main-cli-fn* -main)
