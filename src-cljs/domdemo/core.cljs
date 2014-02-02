(ns domdemo.core
  (:require [dommy.utils :as utils]
            [dommy.core :as dommy])
  (:use-macros [dommy.macros :only [node sel sel1 by-id deftemplate]]))



;; --------------------
(defn some-number []
  (inc (rand-int 6)))

(defn update-box [e]
  (when-let [box-id (dommy/attr e "data-box")]
    (-> (sel1 (str "#" box-id))
        (dommy/set-text! (str "[" (some-number) "]")))))

(defn handle-click [e]
  (update-box (.-target e)))


;; --------------------
;; templates

(deftemplate box-for [box-id]
  [:span {:id box-id} "[-]"])

(deftemplate clickme-button [box-id]
  [:button.clickme {:type "button" :data-box box-id}
                   (str box-id)])

(deftemplate box-template [uniq-id]
  (box-for uniq-id)
  (-> (clickme-button uniq-id)
      (dommy/listen! :click handle-click))
  (node [:br]))

;; --------------------

(defn update-all []
  (doseq [clickme (sel :.clickme)]
    (update-box clickme)))

(defn make-box []
  (dommy/append! (sel1 :body)
                 (box-template (gensym))))

;; --------------------

(defn ready []
  (dommy/set-text! (sel1 :h1.title) "clojurescript!")

  (dommy/listen! (sel1 :.addbox)    :click make-box)
  (dommy/listen! (sel1 :.updateall) :click update-all))


(ready)
