(ns pivicl.svg
  (:require-macros [hiccups.core :as hiccups])
  (:require [hiccups.runtime :as hiccupsrt]))

(defn multi-render [shape ks]
  (fn [args settings]
    (for [shape-args (partition (count ks) args)]
      [shape (merge settings (zipmap ks shape-args))])))

(def render-circle
  (multi-render :circle [:cx :cy :r]))

(def render-lines
  (multi-render :line [:x1 :y1 :x2 :y2]))

(def svg-cmds {:circle render-circle
               :circles render-circle
               :line render-lines
               :lines render-lines})

(defn conj* [col items]
  (if (seq? items)
    (apply conj col items)
    (conj col items)))

(defn emit-svg [cmd args settings]
  (let [emitter (get svg-cmds cmd (constantly ()))]
    (emitter args settings)))

(def empty-svg [:svg])

(defn svg->stdout [svg]
  (println (hiccups/html svg)))

(defn svg-reducer [state next-command]
  (let [{:keys [cmd args settings]} next-command
        new-settings (merge (:settings state) settings)]
    (case cmd
      :set (assoc state :settings new-settings)
      :newframe (do (svg->stdout (:svg state))
                    (assoc state :svg empty-svg))
      (update-in state [:svg] conj*
                 (emit-svg cmd args new-settings)))))

(defn reduce-to-svg [cmd-list]
  (reduce svg-reducer {:svg empty-svg
                       :settings {}} cmd-list))

(defn svg [cmd-list]
  (hiccups/html
   (-> cmd-list
       reduce-to-svg
       :svg)))
